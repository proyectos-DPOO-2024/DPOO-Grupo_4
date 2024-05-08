package galeria.modelo.centroventas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.usuarios.Cliente;

class CentroDeVentasTest
{	
	Galeria galeria;
	CentroDeVentas centroDeVentas;

	@BeforeEach
	void setUp() 
	{
		galeria = new Galeria();
		centroDeVentas = new CentroDeVentas(galeria);

	}
    @AfterEach
    void tearDown( )
    {
    }
    
    void testCrearOfertaValorFijo

}
