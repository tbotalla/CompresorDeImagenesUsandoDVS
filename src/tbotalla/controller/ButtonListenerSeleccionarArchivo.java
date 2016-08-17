package tbotalla.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonListenerSeleccionarArchivo implements ButtonListener, ActionListener {
	private JFrame ventana;
	private JFileChooser fileChooser;
	private JPanel panelImagen;

	public ButtonListenerSeleccionarArchivo(JFrame ventana, JFileChooser fc, JPanel panelImagen) {
		this.ventana = ventana;
		this.fileChooser = fc;
		this.panelImagen = panelImagen;
	}

	public void actionPerformed(ActionEvent e) {
		int result = fileChooser.showOpenDialog(ventana);
		String rutaImagen;
		if (result == JFileChooser.APPROVE_OPTION) {
			rutaImagen = fileChooser.getSelectedFile().toString();
			try {
				BufferedImage img = ImageIO.read(new File(rutaImagen));
				ImageIcon icon = new ImageIcon(getScaledImage(img, 200, 200));
				JLabel label = new JLabel(icon);
				label.setMaximumSize(new Dimension(200, 200));
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelImagen.removeAll(); // Limpia el panel
				panelImagen.updateUI(); // Refresh del panel
				panelImagen.add(label);

				// Para el refresh de la imagen
				ventana.revalidate();
				ventana.repaint();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	// Resizea la imagen al tamanio dado ya que Swing no lo hace por defecto
	private static Image getScaledImage(Image srcImg, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();

		return resizedImg;
	}
}
