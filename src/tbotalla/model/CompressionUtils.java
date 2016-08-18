package tbotalla.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.log4j.Logger;

import tbotalla.view.App;

public class CompressionUtils {
	private final static Logger logger = Logger.getLogger(App.class); // Log4j

	public byte[] compress(byte[] data) throws IOException {
		Deflater deflater = new Deflater();
		deflater.setLevel(9);
		deflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated
													// code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		logger.info("Original: " + data.length / 1024 + " Kb");
		logger.info("Compressed: " + output.length / 1024 + " Kb");
		return output;
	}

	public byte[] decompress(byte[] data) throws IOException, DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		logger.info("Original: " + data.length);
		logger.info("Compressed: " + output.length);
		return output;
	}
}
