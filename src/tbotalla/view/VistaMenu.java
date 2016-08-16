package tbotalla.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tbotalla.controller.ButtonListener;
import tbotalla.controller.ButtonListenerComprimir;
import tbotalla.controller.ButtonListenerSeleccionarArchivo;

public class VistaMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2173469442054443775L;
	private static JButton btnSeleccionarArchivo;
	private ButtonListener blSeleccionarArchivo;
	private static JButton btnComprimir;
	private ButtonListener blComprimir;
//	private TextField txtNombreArchivoEntrada;
	private TextField txtCantidadVS;
	private TextField txtNombreArchivoSalida;
	private static JFileChooser fc;

	public VistaMenu(JFrame ventana) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel labelIngresoArchivoEntrada = new JLabel("Imagen a comprimir (PNG,JPG): ");
		labelIngresoArchivoEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoArchivoEntrada);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		// Create a file chooser
		// final JFileChooser fc = new JFileChooser();
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.setMaximumSize(new Dimension(400, 200));
		// this.add(fc);

		btnSeleccionarArchivo = addAButton("Seleccionar archivo", this);
		blSeleccionarArchivo = new ButtonListenerSeleccionarArchivo(ventana, fc);
		btnSeleccionarArchivo.addActionListener((ActionListener) blSeleccionarArchivo);
		this.add(Box.createRigidArea(new Dimension(0, 30)));

		JLabel labelIngresoVS = new JLabel("Cantidad de valores singulares a usar: ");
		labelIngresoVS.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoVS);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		txtCantidadVS = addATextField("", this);
		this.add(Box.createRigidArea(new Dimension(0, 30)));

		JLabel labelIngresoArchivoSalida = new JLabel("Nombre archivo de salida (PNG): ");
		labelIngresoArchivoSalida.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoArchivoSalida);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		txtNombreArchivoSalida = addATextField("", this);
		this.add(Box.createRigidArea(new Dimension(0, 30)));

		btnComprimir = addAButton("Comprimir", this);
		blComprimir = new ButtonListenerComprimir(ventana, fc, txtCantidadVS,
				txtNombreArchivoSalida);
		btnComprimir.addActionListener((ActionListener) blComprimir);

	}

	private static JButton addAButton(String text, Container container) {
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}

	private static TextField addATextField(String text, Container container) {
		TextField textField = new TextField(text);
		textField.setMaximumSize(new Dimension(100, 20));
		// textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(textField);
		return textField;
	}

}
