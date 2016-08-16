package tbotalla.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class UI {
	private static JFrame ventana = new JFrame("Conversor de Imagenes");

	private static void createAndShowGUI() {
		// Set up the window.
		ventana.setPreferredSize(new Dimension(800, 600));
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false); // tamanio fijo
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(ventana.getContentPane());

		// Display the window.
		ventana.pack();
		ventana.setVisible(true);

		mostrarVistaMenu();
	}

	private static void mostrarVistaMenu() {
		((CardLayout) ventana.getContentPane().getLayout()).show(ventana.getContentPane(), "vistaMenu");
	}

	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new CardLayout());
		VistaMenu vistaMenu = new VistaMenu(ventana);

		pane.add(vistaMenu, "vistaMenu");

		((JComponent) pane).setBorder(new EmptyBorder(80, 10, 10, 10)); // padding
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
