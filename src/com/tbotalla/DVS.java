package com.tbotalla;

import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.SimpleSVD;

public class DVS {

	// Calcula la DVS para una matriz de m*n
	public static Object[] calcular(int k, SimpleMatrix A) {
		SimpleSVD dvs = A.svd();
		// System.out.println("Condicion de la matriz:" +
		// String.valueOf(A.conditionP2()));
		// System.out.println("Cantidad de autovalores: " +
		// dvs.getSVD().getSingularValues().length);

		double[] valoresSingulares = dvs.getSVD().getSingularValues();
		double primerSigma = valoresSingulares[0];
		double kEsimoSigma = valoresSingulares[k];

		SimpleMatrix Ak = new SimpleMatrix(A.numCols(), A.numRows());

		SimpleMatrix U = dvs.getU(), V = dvs.getV(), W = dvs.getW();
		SimpleMatrix nuevaU = U.extractMatrix(0, U.numRows(), 0, k); // tamanio
																		// n*k
		SimpleMatrix nuevaV = V.extractMatrix(0, V.numRows(), 0, k); // tamanio
																		// k*m
		SimpleMatrix nuevaW = W.extractMatrix(0, k, 0, k); // tamanio k*k

		Ak = (nuevaU.mult(nuevaW)).mult(nuevaV.transpose()); // U * W * Vt
		return new Object[] { Ak, new Double(primerSigma), new Double(kEsimoSigma) };
	}

	// Calcula la DVS para cada una de las matrices y arma un array con las
	// cuatro matrices y los
	// primeros y ultimos valores singulares de cada una
	public static Object[] calcular(int k, SimpleMatrix[] imagen) {
		System.out.println("Tamaño de la imagen: " + imagen[0].numRows() + "x" + imagen[0].numCols());

		SimpleMatrix[] nuevaImagen = new SimpleMatrix[4];
		double[] sigmas = new double[4];
		double[] ultimosSigmas = new double[4];
		Object[] tmp;

		nuevaImagen[0] = (SimpleMatrix) (tmp = calcular(k, imagen[0]))[0];
		sigmas[0] = ((Double) tmp[1]).doubleValue();
		ultimosSigmas[0] = ((Double) tmp[2]).doubleValue();
		nuevaImagen[1] = (SimpleMatrix) (tmp = calcular(k, imagen[1]))[0];
		sigmas[1] = ((Double) tmp[1]).doubleValue();
		ultimosSigmas[1] = ((Double) tmp[2]).doubleValue();
		nuevaImagen[2] = (SimpleMatrix) (tmp = calcular(k, imagen[2]))[0];
		sigmas[2] = ((Double) tmp[1]).doubleValue();
		ultimosSigmas[2] = ((Double) tmp[2]).doubleValue();
		nuevaImagen[3] = (SimpleMatrix) (tmp = calcular(k, imagen[3]))[0];
		sigmas[3] = ((Double) tmp[1]).doubleValue();
		ultimosSigmas[3] = ((Double) tmp[2]).doubleValue();

		return new Object[] { nuevaImagen, sigmas, ultimosSigmas };
	}

}