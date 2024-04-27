/**
 * 
 */
package galeria.modelo.inventario;

import java.util.List;
import java.util.Map;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Empleado;

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
	public int verificarUsuario(String login, String password) {
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
