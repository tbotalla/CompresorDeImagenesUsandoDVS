package tbotalla.model;

import java.io.File;

public class Utils {

	public final static String jpeg = "jpeg";
	public final static String jpg = "jpg";
	public final static String gif = "gif";
	public final static String tiff = "tiff";
	public final static String tif = "tif";
	public final static String png = "png";

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

	/*
	 * Get the extension of a file.
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

}
