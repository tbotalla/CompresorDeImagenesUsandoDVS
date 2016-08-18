package tbotalla.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;
import tbotalla.view.App;

public class MatrixEncoder {
	private final static Logger logger = Logger.getLogger(App.class); // Log4j

	public MatrixEncoder() {

	}

	public SimpleMatrix[] decodeMatrix() {
		logger.debug("Decodificando la imagen");

		Path path = Paths.get("codificado");
		byte[] bytes;
		try {
			// Decompressing
			bytes = Files.readAllBytes(path);
			byte[] bytesDecompressed = null;
			try {
				CompressionUtils compresor = new CompressionUtils();
				bytesDecompressed = compresor.decompress(bytes);
				logger.info("bytesDecompressed: " + bytesDecompressed.length / 1024 + " Kb");
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytesDecompressed);
			GZIPInputStream gzipIn = new GZIPInputStream(bais);
			ObjectInputStream objectIn = new ObjectInputStream(gzipIn);

			SimpleMatrix[] arrayMatrices = (SimpleMatrix[]) objectIn.readObject();
			objectIn.close();
			return arrayMatrices;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void encodeMatrix(SimpleMatrix[] matriz) {
		logger.debug("Codificando la imagen");
		try {
			// Encoding
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
			ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
			objectOut.writeObject(matriz);
			objectOut.close();
			byte[] bytes = baos.toByteArray();

			// Compressing
			CompressionUtils compresor = new CompressionUtils();
			byte[] bytesCompressed = compresor.compress(bytes);
			logger.info("bytesCompressed: " + bytesCompressed.length / 1024 + " Kb");

			FileOutputStream stream = new FileOutputStream("codificado");
			try {
				// stream.write(bytes);
				stream.write(bytesCompressed);
			} finally {
				stream.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
