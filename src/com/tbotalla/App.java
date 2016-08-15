package com.tbotalla;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.ejml.simple.SimpleMatrix;

/* Compresor de imagenes utilizando la descomposicion en valores singulares (SVD) haciendo uso de la libreria EJML para el manejo algebraico*/
public class App {
	static String fileName = null;
	static String fileOutputName = null;
	static String tipoFormato = "png";
	static int k;
	static boolean debeSeguirIngresando = true;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferIO = new BufferedReader(new InputStreamReader(System.in));

		// Validacion de los datos de entrada
		while (debeSeguirIngresando) {
			System.out.println("----------------------------------------------------");
			System.out.println("Compresor de im\u00e1genes utilizando DVS");
			System.out.println("Formatos de entrada: jpg, png");
			System.out.println("----------------------------------------------------");
			System.out.println("Formatos de salida: png");
			System.out.println("----------------------------------------------------");
			System.out.println(
					"Ingrese el nombre de la imagen a comprimir: (debe estar en el mismo directorio que este jar)");
			System.out.println("Ejemplo: compress.jpg");

			fileName = bufferIO.readLine();
			debeSeguirIngresando = (!Utils.archivoValido(fileName)) || (!Utils.formatoEntradaValido(fileName));

			System.out.println("Ingrese la cantidad de valores singulares a utilizar para la compresion");

			String kString = bufferIO.readLine();
			debeSeguirIngresando = debeSeguirIngresando || !Utils.isNumeric(kString);
			if (!debeSeguirIngresando)
				k = Integer.valueOf(kString);

			System.out.println("Ingrese el nombre del archivo de salida:");
			System.out.println("Ejemplo: salida.png");

			fileOutputName = Utils.forzarNombre(bufferIO.readLine());
		}

		Image imagen = new ImageIcon(fileName).getImage();
		SimpleMatrix[] matrizImagen = ConversorImagenes.imagenAMatriz(ConversorImagenes.cargarImagen(imagen));

		Object[] data = DVS.calcular(k, matrizImagen);
		SimpleMatrix[] nuevaMatrizImagen = (SimpleMatrix[]) data[0];
		BufferedImage bufferNuevaImagen = ConversorImagenes.matrizAImagen(nuevaMatrizImagen);

		try {
			ImageIO.write(bufferNuevaImagen, tipoFormato, new File(fileOutputName));
			System.out.println("Fin de la compresion");
			System.out.println("Se creo el archivo " + fileOutputName);
		} catch (IOException oie) {
			System.err.println("IOException al escribir la imagen " + fileOutputName);
		}
	}

}
