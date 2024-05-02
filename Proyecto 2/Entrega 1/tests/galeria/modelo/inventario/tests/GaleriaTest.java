package galeria.modelo.inventario.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.inventario.Artista;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;

public class GaleriaTest
{	
	Galeria galeria;
	Pieza pieza;
	Artista artista;
	
	@BeforeEach
	void setUp() {
		galeria = new Galeria();
		pieza = new Pintura("Las Meninas", "Diego Velázquez", "miLogin", "2024-05-31", 1500000, 800000, 500000,
				"Barroco", 300.0f, 200.0f);
		artista = new Artista("nombreArtista");
		
	}
    @AfterEach
    void tearDown( )
    {
    }
    
    
	@Test
	void agregarPieza()
	{
		galeria.agregarPiezaNueva(pieza);
		assertTrue(galeria.existePieza("Las Meninas"), "La pieza no esta en el mapa, deberia");
	}
	
	@Test
	void agregarArtista()
	{
		galeria.agregarArtista("nombreArtista");
		assertTrue(galeria.existeArtista("nombreArtista"), "El artista no esta en el mapa de artistas, debería");
	}
	
	@Test
	void agregarEmpleado()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 321231232, "juan", 1);;
		assertTrue(galeria.existeEmpleado("juanLogin"), "El empleado no esta en el mapa empleados, deberia");
	}
	
	@Test
	void testConfigurarValoresEntrega()
	{
		galeria.configurarValoresDeEntregaDePieza(pieza);
		assertFalse(pieza.isEnPosesion(), "Esta en posesion pero no deberia, pues se esta entregando");
		assertFalse(pieza.isEnSubasta(), "No deberia seguir en modo subasta");
		assertFalse(pieza.isBloqueada(), "No deberia estar bloqueada");
		assertEquals(null,pieza.getFechaTerminoConsignacion(), "Deberia ser null pero esta consignada");
		
	}
	
	
	

}
