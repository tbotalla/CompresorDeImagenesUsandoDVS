package com.tbotalla;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.ejml.simple.SimpleMatrix;

public class ConversorImagenes {
	// Devuelve un Image cargado de la ruta que recibe como parametro
	public static BufferedImage cargarImagenDesdeArchivo(String imagenACargar) {
		Image imagenCargada = new ImageIcon(imagenACargar).getImage();
		return cargarImagen(imagenCargada);
	}

	// Devuelve un BufferedImage a partir de un objeto tipo Image
	public static BufferedImage cargarImagen(Image imagenCargada) {
		BufferedImage bufferImagen = new BufferedImage(imagenCargada.getWidth(null), imagenCargada.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		// Dibuja a partir del (0,0) la imagen que recibio
		bufferImagen.createGraphics().drawImage(imagenCargada, 0, 0, null);
		return bufferImagen;
	}

	// Devuelve un array de 4 matrices para alpha, red, green, blue a partir del
	// buffer de la imagen
	public static SimpleMatrix[] imagenAMatriz(BufferedImage bufferImagen) {
		int ancho = bufferImagen.getWidth();
		int alto = bufferImagen.getHeight();

		int[] rgbs = new int[ancho * alto];
		bufferImagen.getRGB(0, 0, ancho, alto, rgbs, 0, ancho); // Carga un
																// array con
																// todos los rgb
																// de la matriz
		return arrayAMatriz(rgbs, ancho, alto);
	}

	// Devuelve un array de 4 matrices para alpha, red, green, blue a partir de
	// un array con los rgb para cada posicion de la matriz
	private static SimpleMatrix[] arrayAMatriz(int[] rgbs, int ancho, int alto) {
		SimpleMatrix alphaMatrix = new SimpleMatrix(ancho, alto);
		SimpleMatrix redMatrix = new SimpleMatrix(ancho, alto);
		SimpleMatrix greenMatrix = new SimpleMatrix(ancho, alto);
		SimpleMatrix blueMatrix = new SimpleMatrix(ancho, alto);

		SimpleMatrix[] RGBmatrices = new SimpleMatrix[4];
		RGBmatrices[0] = alphaMatrix;
		RGBmatrices[1] = redMatrix;
		RGBmatrices[2] = greenMatrix;
		RGBmatrices[3] = blueMatrix;

		int k = 0, alpha, red, green, blue;
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				// bits: 0...8 = blue, 9...15 = green, 16...23 = red, 24...31 =
				// alpha
				alpha = (rgbs[k] >> 24) & 255;
				RGBmatrices[0].set(i, j, alpha);
				red = (rgbs[k] >> 16) & 255;
				RGBmatrices[1].set(i, j, red);
				green = (rgbs[k] >> 8) & 255;
				RGBmatrices[2].set(i, j, green);
				blue = rgbs[k] & 255;
				RGBmatrices[3].set(i, j, blue);

				k++;
			}
		}
		return RGBmatrices;
	}

	private static void correctDouble(SimpleMatrix m, int i, int j) {
		if (m.get(i, j) > 255) {
			m.set(i, j, 255);
		} else if (m.get(i, j) < 0) {
			m.set(i, j, 0);
		}
	}

	// Devuelve un BufferedImage a partir de un array de matrices con los argb
	public static BufferedImage matrizAImagen(SimpleMatrix[] matrices) {
		int alto = matrices[0].numCols();
		int ancho = matrices[0].numRows();
		int[] rgbs = new int[ancho * alto];

		int k = 0, argb;
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				// bits: 0...8 = blue, 9...15 = green, 16...23 = red, 24...31 =
				// alpha
				correctDouble(matrices[0], i, j);
				argb = ((int) matrices[0].get(i, j)) << 24;
				correctDouble(matrices[1], i, j);
				argb += ((int) matrices[1].get(i, j)) << 16;
				correctDouble(matrices[2], i, j);
				argb += ((int) matrices[2].get(i, j)) << 8;
				correctDouble(matrices[3], i, j);
				argb += (int) matrices[3].get(i, j);

				rgbs[k] = argb;
				k++;
			}
		}
		BufferedImage bufferImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
		bufferImagen.setRGB(0, 0, ancho, alto, rgbs, 0, ancho);
		return bufferImagen;
	}
}
