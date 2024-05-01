package galeria.modelo.inventario.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.inventario.Artista;

public class ArtistaTest
{
	private Artista artista;

	@BeforeEach
	void setUp() throws Exception {
		artista = new Artista("nombreArtista");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
