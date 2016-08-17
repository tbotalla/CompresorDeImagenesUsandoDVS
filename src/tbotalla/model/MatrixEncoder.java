package tbotalla.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.ejml.simple.SimpleMatrix;

import com.opencsv.CSVWriter;

public class MatrixEncoder {

	public MatrixEncoder() {

	}

	public SimpleMatrix[] decodeMatrix() {
		System.out.println("Decodificando la imagen");

		Path path = Paths.get("codificado");
		byte[] bytes;
		try {
			// Decompressing
			bytes = Files.readAllBytes(path);
			byte[] bytesDecompressed = null;
			try {
				bytesDecompressed = CompressionUtils.decompress(bytes);
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
		System.out.println("Codificando la imagen");
		try {
			// Encoding
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
			ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
			objectOut.writeObject(matriz);
			objectOut.close();
			byte[] bytes = baos.toByteArray();

			// Compressing
			byte[] bytesCompressed = CompressionUtils.compress(bytes);

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
