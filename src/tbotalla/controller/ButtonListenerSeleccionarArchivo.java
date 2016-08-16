package tbotalla.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ButtonListenerSeleccionarArchivo implements ButtonListener, ActionListener{
	private JFrame ventana;
	private JFileChooser fileChooser;
	
	public ButtonListenerSeleccionarArchivo(JFrame ventana, JFileChooser fc){
		this.ventana = ventana;
		this.fileChooser = fc;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("CLICK EN SELECT ARCHIVO");
		int result = fileChooser.showOpenDialog(ventana);
		if (result == JFileChooser.APPROVE_OPTION) {
		    System.out.println("Selecciono un archivo!");
		}

		
//		((CardLayout) ventana.getContentPane().getLayout()).show(ventana.getContentPane(), "vistaSelecArchivo");
	}
}
