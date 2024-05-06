package galeria.modelo.usuarios.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import galeria.modelo.usuarios.Cliente;

public class ClienteTest {

    private Cliente cliente;

    @Before
    public void setUp() {
        cliente = new Cliente("testUser", "password123", 123456789);
    }

    @Test
    public void testVerificarCliente() {
        assertFalse(cliente.isVerificado());
        cliente.verificarComoComprador();
        assertTrue(cliente.isVerificado());
    }

    @Test
    public void testAsignarTopeCompras() {
        cliente.asignarTopeCompras(1000); // Asignar tope de compras de 1000
        assertEquals(1000, cliente.getTopeCompras());
    }

    @Test
    public void testIsVerificado() {
        assertFalse(cliente.isVerificado()); // Por defecto no verificado
        cliente.verificarComoComprador(); // Verificar
        assertTrue(cliente.isVerificado()); // Debería estar verificado ahora
    }

    @Test
    public void testGetTopeCompras() {
        cliente.asignarTopeCompras(2000); // Asignar tope de compras
        assertEquals(2000, cliente.getTopeCompras()); // Verificar tope asignado
    }
}
