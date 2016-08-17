package tbotalla.view;

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

//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.TextField;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;

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
	// private TextField txtNombreArchivoEntrada;
	private TextField txtCantidadVS;
	private TextField txtNombreArchivoSalida;
	private static JFileChooser fc;
	private static JPanel panelImagen;

	public VistaMenu(JFrame ventana) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel labelIngresoArchivoEntrada = new JLabel("Imagen a comprimir (PNG,JPG): ");
		labelIngresoArchivoEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoArchivoEntrada);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		// Create a file chooser
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setMaximumSize(new Dimension(400, 200));
		fc.addChoosableFileFilter(new ImageFilter()); // Solo permitido
														// seleccionar imagenes
		fc.setAcceptAllFileFilterUsed(false);

		// this.add(fc);

		btnSeleccionarArchivo = addAButton("Seleccionar archivo", this);
		this.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel labelIngresoVS = new JLabel("Cantidad de valores singulares a usar: ");
		labelIngresoVS.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoVS);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		txtCantidadVS = addATextField("", this);
		this.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel labelIngresoArchivoSalida = new JLabel("Nombre archivo de salida (PNG): ");
		labelIngresoArchivoSalida.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(labelIngresoArchivoSalida);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		txtNombreArchivoSalida = addATextField("out.png", this);
		this.add(Box.createRigidArea(new Dimension(0, 20)));

		btnComprimir = addAButton("Comprimir", this);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		panelImagen = new JPanel();
		panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.X_AXIS));
		this.add(panelImagen);

		// Seteo de los listeners
		blSeleccionarArchivo = new ButtonListenerSeleccionarArchivo(ventana, fc, panelImagen);
		btnSeleccionarArchivo.addActionListener((ActionListener) blSeleccionarArchivo);

		blComprimir = new ButtonListenerComprimir(ventana, fc, txtCantidadVS, txtNombreArchivoSalida);
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
