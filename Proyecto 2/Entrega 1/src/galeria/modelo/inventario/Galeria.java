/**
 * 
 */
package galeria.modelo.inventario;

import java.util.List;
import java.util.Map;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.usuarios.Administrador;
import galeria.modelo.usuarios.Cajero;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Empleado;
import galeria.modelo.usuarios.Operador;
import galeria.modelo.usuarios.Usuario;

/**
 * Esta clase actúa como un controlador que regular la comunicación entre la interfaz y el resto del modelo.
 * Adicionalmente, guarda la siguiente información:
 * - Mapa de piezas (Llave: nombrePieza; Valor: objeto pieza).
 * - Mapa de clientes.
 * - Mapa nombre artista -> artista (objeto)
 * - Casa de subastas correspondiente
 */
public class Galeria {
	
	
	private Map<String, Pieza> mapaPiezas;
	private Map<String, Artista> mapaArtistas;
	private Map<String, Empleado> mapaEmpleados;
	private Map<String, Cliente> mapaClientes;
	private Map<String, List<String>> piezasActualesPropietarios;
	private Map<String, List<String>> piezasPasadasPropietarios;
	
	private CentroDeVentas centroDeVentas;

	
	
	/**
	 * La función verifica si el login y password ingresados corresponden a una cuenta.
	 * Si el login y el password son correctos, el método devuelve el entero correspondiente a el tipo de cuenta.
	 * Si el login existe pero el password es incorrecto, el método devuelve 0.
	 * Si el login no existe, el método devuelve -1.
	 * @return tipoUsuario
	 */
	public int verificarLogin(String login, String password) {
		if (mapaEmpleados.containsKey(login)) {
			Empleado empleado = mapaEmpleados.get(login);
			if (empleado.getPassword().equals(password)){
				return empleado.getRol();
			}
			else {
				return 0;
			}
		}
		else {
			if (mapaClientes.containsKey(login)) {
				Cliente cliente = mapaClientes.get(login);
				if (cliente.getPassword().equals(password)) {
					return cliente.getRol();
				}
				else {
					return 0;
				}
			}
			else {
				return -1;
			}
		}
	}
	
	
	
	/**
	 * Este método comprueba si el login ingresado por parámetro es único.
	 */
	public boolean comprobarLoginUnico(String login) {
		boolean unico = !( mapaClientes.containsKey(login) || mapaEmpleados.containsKey(login) );
		return unico;
	}
	
	
	/**
	 * Esta función media la interacción para verificar un nuevo comprador
	 */
	public void verificarNuevoComprador(Cliente cliente, long tope) {
		cliente.verificarComoComprador(tope);
	}
	
	
	/**
	 * Esta función media la interacción para extender el tope de compras de un comprador
	 */
	public void extenderTope(Cliente cliente, long tope) {
		cliente.extenderTopeCompras(tope);
	}
	
	
	/**
	 * Esta función agrega un nuevo empleado a la base de datos.
	 */
	public void agregarNuevoEmpleado(String login, String password, int cellphone, String nombre, int rol) {
		
		if (rol == Usuario.ADMINISTRADOR) {
			Administrador admin = new Administrador(login, password, cellphone, nombre);
			mapaEmpleados.put(login, admin);
		}
		if (rol == Usuario.CAJERO) {
			Cajero cajero = new Cajero(login, password, cellphone, nombre);
			mapaEmpleados.put(login, cajero);
		}
		if (rol == Usuario.OPERADOR) {
			Operador operador = new Operador(login, password, cellphone, nombre);
			mapaEmpleados.put(login, operador);
		}
	}
	
	
	/**
	 * Getters y funciones para verificar existencia
	 */
	public CentroDeVentas getCentroDeVentas() {
		return centroDeVentas;
	}
	
	public List<String> getPiezasActuales(String loginCliente){
		return piezasActualesPropietarios.get(loginCliente);
	}
	
	public List<String> getPiezasPasadas(String loginCliente){
		return piezasPasadasPropietarios.get(loginCliente);
	}
	
	public boolean existePieza(String nombrePieza) {
		return mapaPiezas.containsKey(nombrePieza);
	}
	
	public Pieza getPieza(String nombrePieza) {
		return mapaPiezas.get(nombrePieza);
	}
	
	public boolean existeEmpleado(String loginEmpleado) {
		return mapaEmpleados.containsKey(loginEmpleado);
	}
	
	public Empleado getEmpleado(String loginEmpleado) {
		return mapaEmpleados.get(loginEmpleado);
	}
	
	public boolean existeCliente(String loginCliente) {
		return mapaClientes.containsKey(loginCliente);
	}
	
	public Cliente getCliente(String loginCliente) {
		return mapaClientes.get(loginCliente);
	}
	
	public boolean existeArtista(String nombreArtista) {
		return mapaArtistas.containsKey(nombreArtista);
	}
	
	public Artista getArtista(String nombreArtista) {
		return mapaArtistas.get(nombreArtista);
	}
}
