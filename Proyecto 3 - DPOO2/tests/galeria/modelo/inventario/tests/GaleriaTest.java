package galeria.modelo.inventario.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.modelo.inventario.Artista;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.usuarios.Cliente;

public class GaleriaTest
{	
	Galeria galeria;
	Pieza pieza;
	Cliente cliente1;
	

	
	@BeforeEach
	void setUp() {
		galeria = new Galeria();
		
		cliente1 = new Cliente("miLogin","password",122323);
		pieza = new Pintura("Las Meninas", "Diego Velázquez", "miLogin", "2024-05-31", 1500000, 800000, 500000,
				0, false, false, false, false, "Barroco", 300.0f, 200.0f);
		
		galeria.agregarCliente(cliente1);
		galeria.agregarPiezaNueva(pieza);

	}
    @AfterEach
    void tearDown( )
    {
    }
    
    
	@Test
	void testAgregarPieza()
	{
		galeria.agregarPiezaNueva(pieza);
		assertTrue(galeria.existePieza("Las Meninas"), "La pieza no esta en el mapa, deberia");
	}
	
	@Test
	void testAgregarArtista()
	{
		galeria.agregarArtista("nombreArtista");
		assertTrue(galeria.existeArtista("nombreArtista"), "El artista no esta en el mapa de artistas, debería");
	}
	
	@Test
	void testAgregarEmpleadoAdmin()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 3212, "juan", 1);
		assertTrue(galeria.existeEmpleado("juanLogin"), "El empleado no esta en el mapa empleados, deberia");
	}
	@Test
	void testAgregarEmpleadoCajero()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 3212, "juan", 2);
		assertTrue(galeria.existeEmpleado("juanLogin"), "El empleado no esta en el mapa empleados, deberia");
	}
	@Test
	void testAgregarEmpleadoOperador()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 3212, "juan", 3);
		assertTrue(galeria.existeEmpleado("juanLogin"), "El empleado no esta en el mapa empleados, deberia");
	}
	
	@Test
	void testAgregarCliente() 
	{
		Cliente cliente = new Cliente("loginCLiente","passwordCleinte",31234234);
		galeria.agregarCliente(cliente);
		assertTrue(galeria.existeCliente(cliente.getLogin()), "El cliente no esta en el mapa, pero lo acabo de meter");
	}	
	// Comprobar Login unico 
	@Test
	void comprobarLoginUnicoNoRepetido()
	{
		//GIVEN:
		galeria.agregarNuevoEmpleado("SantiagoLogin", "ljdk", 321123, "Santiago", 2);
		//WHEN:
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 3212, "juan", 1);
		assertTrue(galeria.comprobarLoginUnico("CarlosLogin"), "Este login ya esta en la lista pero no deberia");
	}
	
	@Test
	void comprobarLoginUnicoRepetido()
	{
		//GIVEN:
		galeria.agregarNuevoEmpleado("SantiagoLogin", "ljdk", 321123, "Santiago", 2);
		//WHEN:
		galeria.agregarNuevoEmpleado("juanLogin", "pablo", 3212, "juan", 1);
		assertFalse(galeria.comprobarLoginUnico("juanLogin"), "Este login no aparece pero si se debio agregar");
	}
	
	// Verificar Login
	@Test
	void verificarLoginAdministrador()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "contra1", 3212, "juan", 1);
		assertEquals(1, galeria.verificarLogin("juanLogin", "contra1"), "El admin no se pudo verificar pero pueso sus credenciales bien");
	}
	
	@Test
	void verificarLoginCajero()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "contra1", 3212, "juan", 2);
		assertEquals(2, galeria.verificarLogin("juanLogin", "contra1"), "El cajero no se pudo verificar pero pueso sus credenciales bien");
	}
	@Test
	void verificarLoginOperador()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "contra1", 3212, "juan", 3);
		assertEquals(3, galeria.verificarLogin("juanLogin", "contra1"), "El operador no se pudo verificar pero pueso sus credenciales bien");
	}
	@Test
	void verificarLoginIncorrecto()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "contra1", 3212, "juan", 1);
		assertEquals(-1, galeria.verificarLogin("juajbibio", "contra1"), "login incorrecto se verificaro, re mal");
	}
	@Test
	void verificarContrasenyaIncorrecto()
	{
		galeria.agregarNuevoEmpleado("juanLogin", "contra2", 3212, "juan", 1);
		assertEquals(0, galeria.verificarLogin("juanLogin", "contra1"), "Entro con la contraseña incorrecta, re mal");
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
	
	// Otras requerimientos de funciones
	
	@Test
	void testVerificarNuevoComprador() 
	{
		Cliente cliente = new Cliente("loginCLiente","passwordCleinte",31234234);
		galeria.agregarCliente(cliente);
		galeria.verificarNuevoComprador(cliente, 100);
		assertTrue(cliente.isVerificado(), "Se supone que deberia estar verificado, pero no lo esta");
		
	}
	
	@Test
	void testAsignarTope() 
	{
		Cliente cliente = new Cliente("loginCLiente","passwordCleinte",31234234);
		galeria.agregarCliente(cliente);
		galeria.asignarTope(cliente, 1000);
		assertEquals(1000, cliente.getTopeCompras(), "No es el mismo tope de compras asignado");		
	}
	
	@Test
	void testEntregarPieza()
	{
		Cliente cliente = new Cliente("loginComprador","passwordCleinte",31234234);
		galeria.agregarCliente(cliente);

		galeria.entregarPieza(pieza,"loginComprador");
		
		assertEquals("loginComprador", pieza.getLoginPropietario(), "El comprador no tiene la pieza, ha sido estafado :(");
		
	}
	

}
