package tbotalla.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;
import tbotalla.model.ConversorImagenes;
import tbotalla.model.DVS;
import tbotalla.model.MatrixEncoder;
import tbotalla.model.Utils;

public class ControladorCompresion {

	private JFrame ventana;
	private JFileChooser fileChooser;
	private TextField txtCantidadVS;
	private TextField txtNombreArchivoSalida;
	private final static Logger logger = Logger.getLogger(ControladorCompresion.class); // Log4j

	// Se encarga de procesar los datos ingresados y comprimir la imagen
	public ControladorCompresion(JFrame ventana, JFileChooser fileChooser, TextField txtCantidadVS,
			TextField txtNombreArchivoSalida) {
		super();
		this.ventana = ventana;
		this.fileChooser = fileChooser;
		this.txtCantidadVS = txtCantidadVS;
		this.txtNombreArchivoSalida = txtNombreArchivoSalida;

		ArrayList<String> datosIngresados = obtenerDatosIngresados();
		if (validarDatosIngresados(datosIngresados)) {
			// Datos OK
			logger.debug("Datos OK");
			Image imagen = new ImageIcon(fileChooser.getSelectedFile().toString()).getImage();
			ConversorImagenes conversor = new ConversorImagenes();
			SimpleMatrix[] matrizImagen = conversor.imagenAMatriz(conversor.cargarImagen(imagen));

			// TODO, prueba pasando por el encoder
			MatrixEncoder encoder = new MatrixEncoder();
			encoder.encodeMatrix(matrizImagen);
			SimpleMatrix[] matricesImgComprimidas = encoder.decodeMatrix();
			// Object[] data =
			// DVS.calcular(Integer.valueOf(txtCantidadVS.getText()),
			// matrizImagen);
			DVS dvs = new DVS();
			Object[] data = dvs.calcular(Integer.valueOf(txtCantidadVS.getText()), matricesImgComprimidas); // prueba
			SimpleMatrix[] nuevaMatrizImagen = (SimpleMatrix[]) data[0]; // los
																			// otros
																			// elementos
																			// del
																			// array
																			// data
																			// son
																			// el
																			// primer
																			// y
																			// ultimo
																			// sigma,
																			// no
																			// usado
																			// por
																			// ahora
			BufferedImage bufferNuevaImagen = conversor.matrizAImagen(nuevaMatrizImagen);

			try {

				ImageIO.write(bufferNuevaImagen, "png", new File(txtNombreArchivoSalida.getText()));
				logger.debug("Fin de la compresion");
				logger.debug("Se creo el archivo " + txtNombreArchivoSalida.getText());

				// Muestra del archivo comprimido por GUI
				Image imagenSalida = new ImageIcon(txtNombreArchivoSalida.getText()).getImage();
				ImageIcon icon = new ImageIcon(imagenSalida);
				JLabel label = new JLabel();
				label.setIcon(icon);

				label.removeAll(); // Limpia el panel
				label.updateUI(); // Refresh del panel

				JFrame ventanaImagenComprimida = new JFrame(txtNombreArchivoSalida.getText());
				ventanaImagenComprimida.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
				ventanaImagenComprimida.setLocationRelativeTo(null);
				ventanaImagenComprimida.setResizable(true);

				ventanaImagenComprimida.add(label);
				ventanaImagenComprimida.setResizable(false);
				ventanaImagenComprimida.pack();
				ventanaImagenComprimida.setLocationRelativeTo(null); // Ventana centrada
				ventanaImagenComprimida.setVisible(true);

			} catch (IOException oie) {
				logger.error("IOException al escribir la imagen " + txtNombreArchivoSalida.getText());
			}
		}
	}

	// Usado para la ConsoleApp
	private boolean validarDatosIngresados(ArrayList<String> datosIngresados) {
		File archivoEntrada = new File(datosIngresados.get(0));
		File archivoSalida = new File(datosIngresados.get(2));
		return (archivoEntrada.exists() && isNumeric(datosIngresados.get(1)));
	}

	public ArrayList<String> obtenerDatosIngresados() {
		String archivoEntrada;
		String cantVS;
		String archivoSalida;
		if ((fileChooser.getSelectedFile() != null) && (txtCantidadVS != null) && (txtNombreArchivoSalida != null)) {
			// Datos no nulos
			archivoEntrada = fileChooser.getSelectedFile().toString();
			cantVS = txtCantidadVS.getText();
			archivoSalida = Utils.forzarNombre(txtNombreArchivoSalida.getText());

			ArrayList<String> datosIngresados = new ArrayList<String>();
			datosIngresados.add(archivoEntrada);
			datosIngresados.add(cantVS);
			datosIngresados.add(archivoSalida);

			return datosIngresados;
		}
		logger.error("Alguno de los datos es nulo");
		return null;
	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public List<Component> getAllComponents(final Container c) {
		Component[] comps = c.getComponents();
		List<Component> compList = new ArrayList<Component>();
		for (Component comp : comps) {
			compList.add(comp);
			if (comp instanceof Container)
				compList.addAll(getAllComponents((Container) comp));
		}
		return compList;
	}

}
