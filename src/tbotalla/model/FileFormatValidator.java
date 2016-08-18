package tbotalla.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFormatValidator {

	private Pattern patternPNG;
	private Pattern patternPNG_JPG;
	private Matcher matcher;
	private static final String IMAGE_PATTERN_JPG_PNG = "([^\\s]+(\\.(?i)(jpg|png))$)";
	private static final String IMAGE_PATTERN_PNG = "([^\\s]+(\\.(?i)(png))$)";

	public FileFormatValidator() {
		patternPNG = Pattern.compile(IMAGE_PATTERN_PNG);
		patternPNG_JPG = Pattern.compile(IMAGE_PATTERN_JPG_PNG);
	}

	public boolean validatePNG(final String image) {
		matcher = patternPNG.matcher(image);
		return matcher.matches();
	}

	public boolean validatePNGorJPG(final String image) {
		matcher = patternPNG_JPG.matcher(image);
		return matcher.matches();

	}
}
