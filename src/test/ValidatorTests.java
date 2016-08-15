package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tbotalla.Utils;

public class ValidatorTests {

	@Test
	public void testFormatosDeEntrada() {
		assertTrue(Utils.formatoEntradaValido("perro.jpg"));
		assertTrue(Utils.formatoEntradaValido("perro.png"));
	}

	@Test
	public void testFormatoDeSalida() {
		assertFalse(Utils.formatoEntradaValido("perro.qwe"));
		assertFalse(Utils.formatoEntradaValido("perro"));
	}

	@Test
	public void testNumeros() {
		assertTrue(Utils.isNumeric("12"));
		assertTrue(Utils.isNumeric("0"));
		assertTrue(Utils.isNumeric("-12"));
		assertFalse(Utils.isNumeric("perro"));
	}
	
	@Test
	public void testArchivoDeEntrada() {
		assertTrue(Utils.archivoValido("compress.jpg"));
		assertFalse(Utils.archivoValido("tony.md"));
	}
	
	@Test
	public void testForzarNombreArchivo() {
		assertEquals("asd-asd_asd_asd_asd_asd_asd_.asd", Utils.forzarNombre("asd-asd_asd¿asd+asd}asd´asdñ.asd"));
	}

	
}
