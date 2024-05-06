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
    public void verificarComoComprador_Test() {
        assertFalse(cliente.isVerificado());
        cliente.verificarComoComprador(1000); // Verificar con tope de compras de 1000
        assertTrue(cliente.isVerificado());
        assertEquals(1000, cliente.getTopeCompras());
    }

    @Test
    public void extenderTopeCompras_Test() {
        cliente.verificarComoComprador(1000); // Verificar primero
        assertEquals(1000, cliente.getTopeCompras());
        cliente.extenderTopeCompras(1500); // Extender el tope a 1500
        assertEquals(1500, cliente.getTopeCompras());
    }

    @Test
    public void reducirTopeCompras_Test() {
        cliente.verificarComoComprador(2000); // Verificar primero
        assertEquals(2000, cliente.getTopeCompras());
        cliente.reducirTopeCompras(500); // Reducir el tope en 500
        assertEquals(1500, cliente.getTopeCompras());
    }

    @Test
    public void isVerificado_Test() {
        assertFalse(cliente.isVerificado()); // Por defecto no verificado
        cliente.verificarComoComprador(1000); // Verificar
        assertTrue(cliente.isVerificado()); // Debería estar verificado ahora
    }

    @Test
    public void getTopeCompras_Test() {
        cliente.verificarComoComprador(2000); // Verificar primero
        assertEquals(2000, cliente.getTopeCompras()); // Verificar tope inicial
        cliente.extenderTopeCompras(3000); // Extender tope
        assertEquals(3000, cliente.getTopeCompras()); // Verificar tope extendido
    }
}
