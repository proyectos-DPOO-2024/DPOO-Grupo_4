package galeria.modelo.inventario.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.inventario.Artista;

public class ArtistaTest
{
	private Artista artista;

	@BeforeEach
	void setUp() {
		artista = new Artista("nombreArtista");
	}
    @AfterEach
    void tearDown( )
    {
    }

	@Test
    void testArtista( )
    {
        assertEquals( "nombreArtista", artista.getNombre( ), "El nombre del artista no es el correcto" );
        assertEquals( 0, artista.getNombrePiezas().size(), "El nuevo artista no debería tener piezas" );
    }
	
	@Test
    void testAgregarPieza( )
    {
		artista.agregarPieza("Rey Felipe");
		LinkedList<String> nombrePiezas = new LinkedList<>(List.of("Rey Felipe"));
        assertEquals(nombrePiezas , artista.getNombrePiezas(), "Las listas no son iguales" );
    }
	

}