package galeria.modelo.centroventas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.centroventas.Fecha;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.usuarios.Cliente;

class CentroDeVentasTest
{	
	Galeria galeria;
	CentroDeVentas centroDeVentas;
	Cliente cliente1;
	Pieza pieza;

	@BeforeEach
	void setUp() 
	{
		galeria = new Galeria();
		centroDeVentas = new CentroDeVentas(galeria);
		cliente1 = new Cliente("miLogin","password",122323);
		pieza = new Pintura("Las Meninas", "Diego Velázquez", "miLogin", "2024-05-31", 1500, 800, 50,
				0, false, false, false, false, "Barroco", 300.0f, 200.0f);
		galeria.agregarCliente(cliente1);
		cliente1.asignarTopeCompras(1000000);
		galeria.agregarPiezaNueva(pieza);

	}
    @AfterEach
    void tearDown( )
    {
    }
    @Test
    void testCrearOfertaValorFijo()
    {
    	try {
    	    centroDeVentas.crearOfertaValorFijo(cliente1.getLogin(), pieza.getTitulo());
    	} catch (Exception e) {
    	    System.out.println("Error: " + e.getMessage());
    	}
    	assertTrue(pieza.isBloqueada(), "No esta bloqueda, pero deberia estar bloqueada");
    }
    @Test
    void testRealizarPago()
    {
		try {
			centroDeVentas.crearOfertaValorFijo(cliente1.getLogin(), pieza.getTitulo());
		} catch (Exception e) {
    	    System.out.println("Error: " + e.getMessage());
		}

    	Fecha fecha = new Fecha(2, 06, 2023);
    	
    	centroDeVentas.realizarPago(0, fecha, pieza.getTitulo());
    	Pago ultimoPago = centroDeVentas.getHistorialCompras(cliente1.getLogin()).getLast();
    	assertEquals(ultimoPago.getFecha(), fecha, "No es la fecha correcta");
    }
    
    

}
