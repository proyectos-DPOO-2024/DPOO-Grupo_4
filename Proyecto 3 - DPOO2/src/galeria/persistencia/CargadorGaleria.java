package galeria.persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.inventario.Galeria;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Empleado;


public class CargadorGaleria
{	
	protected Galeria cargarGaleria(JSONObject raizGaleria) {
		
		Galeria galeria = new Galeria();
		
		try {
			cargarEmpleados(galeria, raizGaleria.getJSONArray("empleados"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarClientes(galeria, raizGaleria.getJSONArray("clientes"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			JSONArray piezas = raizGaleria.getJSONArray("piezas");

			CargadorPiezas cargPiezas = new CargadorPiezas();
			cargPiezas.cargarPiezas(galeria, piezas);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarPiezasPasadasPropietario(galeria, raizGaleria.getJSONArray("piezasPasadasPropietario"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return galeria;
	}

	private void cargarEmpleados(Galeria galeria, JSONArray jEmpleados) throws Exception {
		int numEmpleados = jEmpleados.length();
		for (int i = 0; i < numEmpleados; i++) {
			JSONObject empleado = jEmpleados.getJSONObject(i);

			int rol = empleado.getInt("rol");
			String login = empleado.getString("login");
			String password = empleado.getString("password");
			int telefono = empleado.getInt("telefono");
			String nombre = empleado.getString("nombre");

			if (rol == Empleado.ADMINISTRADOR || rol == Empleado.CAJERO || rol == Empleado.OPERADOR) {
				galeria.agregarNuevoEmpleado(login, password, telefono, nombre, rol);
			} else {
				Exception e = new Exception("Un empleado en el archivo no tiene un rol válido.");
				throw e;
			}
		}
	}

	private void cargarClientes(Galeria galeria, JSONArray jClientes) throws Exception {
		int numClientes = jClientes.length();
		for (int i = 0; i < numClientes; i++) {
			JSONObject cliente = jClientes.getJSONObject(i);

			String login = cliente.getString("login");
			String password = cliente.getString("password");
			int telefono = cliente.getInt("telefono");
			boolean verificado = cliente.getBoolean("verificado");
			long topeCompras = cliente.getLong("topeCompras");
			
			Cliente clienteObj = new Cliente(login, password, telefono, verificado, topeCompras);

			galeria.agregarCliente(clienteObj);
		}
	}
	
	private void cargarPiezasPasadasPropietario(Galeria galeria, JSONArray jPiezasPasadasPropietario) {
		
		Map<String, List<String>> mapaPiezasPasadasPropietario = new HashMap<String, List<String>>();
		
		int numPropietarios = jPiezasPasadasPropietario.length();
		for (int i = 0; i < numPropietarios; i++) {
			JSONObject parejaPiezasPropietario = jPiezasPasadasPropietario.getJSONObject(i);
			String propietario = parejaPiezasPropietario.getString("propietario");
			JSONArray listaPiezasPasadasJSON = parejaPiezasPropietario.getJSONArray("listaPiezasPasadas");
			List<String> listaPiezasPasadas = cargarListaPiezasPasadas(listaPiezasPasadasJSON);
			
			mapaPiezasPasadasPropietario.put(propietario, listaPiezasPasadas);
		}
		
		galeria.setPiezasPasadasPropietarios(mapaPiezasPasadasPropietario);
	}
	
	private List<String> cargarListaPiezasPasadas(JSONArray listaPiezasPasadasJSON) {
		
		List<String> listaPiezasPasadas = new LinkedList<String>();
		for (int i = 0; i < listaPiezasPasadasJSON.length(); i++) {
			String tituloPieza = listaPiezasPasadasJSON.getString(i);
			listaPiezasPasadas.add(tituloPieza);
		}
		
		return listaPiezasPasadas;
	}
}