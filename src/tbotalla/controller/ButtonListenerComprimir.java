package tbotalla.controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ButtonListenerComprimir implements ButtonListener, ActionListener {
	private JFrame ventana;
	private JFileChooser fileChooser;
	private ControladorCompresion controlador;
	private TextField txtCantidadVS;
	private TextField txtNombreArchivoSalida;

	public ButtonListenerComprimir(JFrame ventana, JFileChooser fc, TextField tfCantidadVS,
			TextField tfNombreArchivoSalida) {
		this.ventana = ventana;
		this.fileChooser = fc;
		this.txtCantidadVS = tfCantidadVS;
		this.txtNombreArchivoSalida = tfNombreArchivoSalida;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Se CLIKEO en comprimir!");
//		File file = fileChooser.getSelectedFile();
//		if (!file.exists()) {
//			System.out.println("El archivo no existe");
//		}

		this.controlador = new ControladorCompresion(ventana, fileChooser, txtCantidadVS, txtNombreArchivoSalida);

	}
}
