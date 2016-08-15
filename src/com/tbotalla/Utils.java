package com.tbotalla;

import java.io.File;

public class Utils {

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean archivoValido(String archivo) {
		File file = new File(archivo);
		if (file.isDirectory() || !file.exists()) {
			return false;
		}
		return true;
	}

	public static boolean formatoEntradaValido(String archivo) {
		FileFormatValidator validator = new FileFormatValidator();
		return validator.validatePNGorJPG(archivo);
	}

	public static boolean formatoSalidaValido(String archivo) {
		FileFormatValidator validator = new FileFormatValidator();
		return validator.validatePNG(archivo);
	}

	public static String forzarNombre(String nombreOriginal) {
		return nombreOriginal.replaceAll("[^a-zA-Z0-9.-]", "_");

	}

}
