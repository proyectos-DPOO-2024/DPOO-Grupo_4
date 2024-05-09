package galeria.modelo.inventario.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;

class PiezaTest
{
	private Pieza pieza;

	@BeforeEach
	void setUp() {

		pieza = new Pintura("Las Meninas", "Diego Velázquez", "miLogin", "2024-05-31", 1500000, 800000, 500000,
				0, false, false, false, false, "Barroco", 300.0f, 200.0f);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	public void testGetters() {
		assertEquals("Las Meninas", pieza.getTitulo(), "El título no coincide");
		assertEquals("Diego Velázquez", pieza.getNombreArtista(), "El nombre del artista no coincide");
		assertEquals("miLogin", pieza.getLoginPropietario(), "El login del propietario no coincide");
		assertEquals("2024-05-31", pieza.getFechaTerminoConsignacion(),
				"La fecha de término de consignación no coincide");
		assertEquals(1500000, pieza.getPrecioVentaDirecta(), "El precio de venta directa no coincide");
		assertEquals(800000, pieza.getPrecioInicioSubasta(), "El precio de inicio de subasta no coincide");
		assertEquals(500000, pieza.getPrecioMinimoSubasta(), "El precio mínimo de subasta no coincide");
		assertFalse(pieza.isBloqueada(), "La pieza está bloqueada y no debería estarlo(se acaba de poner)");
		assertFalse(pieza.isEnSubasta(), "La pieza está en subasta y no debería estarlo(se acaba de poner)");
	}

	@Test
	public void testSetters() {
		pieza.setFechaTerminoConsignacion("2025-05-31");
		assertEquals("2025-05-31", pieza.getFechaTerminoConsignacion(),
				"La fecha de término de consignación no coincide");

		pieza.setPrecioVentaDirecta(1600000);
		assertEquals(1600000, pieza.getPrecioVentaDirecta(), "El precio de venta directa no coincide");

		pieza.setPrecioInicioSubasta(850000);
		assertEquals(850000, pieza.getPrecioInicioSubasta(), "El precio de inicio de subasta no coincide");

		pieza.setPrecioMinimoSubasta(550000);
		assertEquals(550000, pieza.getPrecioMinimoSubasta(), "El precio mínimo de subasta no coincide");
	}

	@Test
	public void testCambiarPropietario() {
		
		pieza.cambiarPropietario("emanuel");
		assertEquals("emanuel", pieza.getLoginPropietario(),"jumm el propietario no fue cambiado con exito");
		
	}
	
	@Test
	public void testBloquear() {
		
		assertFalse(pieza.isBloqueada(), "La pieza esta bloqueada pero se acaba de crear");
		pieza.bloquear();
		assertTrue(pieza.isBloqueada(), "La pieza no se ha bloqueado correctamente");
		
	}

}
