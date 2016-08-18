package tbotalla.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import tbotalla.controller.ControladorCompresion;
import tbotalla.model.Utils;

public class App {
	private static JFrame ventana = new JFrame("Conversor de Imagenes");
	private final static Logger logger = Logger.getLogger(App.class); // Log4j

	private static void createAndShowGUI() {
		// Set up the window.
		ventana.setPreferredSize(new Dimension(Utils.DEFAULT_SCREEN_WIDTH, Utils.DEFAULT_SCREEN_HEIGHT));
		ventana.setResizable(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(ventana.getContentPane());

		// Display the window.
		ventana.pack();
		ventana.setLocationRelativeTo(null); // Ventana centrada
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

		((JComponent) pane)
				.setBorder(new EmptyBorder(Utils.DEFAULT_PADDING_TOP_MAIN_PANEL, Utils.DEFAULT_PADDING_LEFT_MAIN_PANEL,
						Utils.DEFAULT_PADDING_BOTTOM_MAIN_PANEL, Utils.DEFAULT_PADDING_RIGHT_MAIN_PANEL)); // padding
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		logger.debug("Inicio GUI-App");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
