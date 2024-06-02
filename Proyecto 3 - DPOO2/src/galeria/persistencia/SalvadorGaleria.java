package galeria.persistencia;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Empleado;

public class SalvadorGaleria
{

	protected void guardarGaleria(Galeria galeria, JSONObject jGaleria) throws Exception {

		guardarEmpleados(galeria, jGaleria);

		SalvadorPiezas salvadorPiezas = new SalvadorPiezas();
		salvadorPiezas.guardarPiezas(galeria, jGaleria);

		guardarClientes(galeria, jGaleria);
		guardarPiezasPasadasPropietario(galeria, jGaleria);
	}

	private void guardarEmpleados(Galeria galeria, JSONObject jGaleria) {
		JSONArray jEmpleados = new JSONArray();
		for (Empleado empleado : galeria.getMapaEmpleados().values()) {
			JSONObject jEmpleado = new JSONObject();

			jEmpleado.put("rol", empleado.getRol());
			jEmpleado.put("login", empleado.getLogin());
			jEmpleado.put("password", empleado.getPassword());
			jEmpleado.put("telefono", empleado.getTelefono());
			jEmpleado.put("nombre", empleado.getNombre());

			jEmpleados.put(jEmpleado);
		}

		jGaleria.put("empleados", jEmpleados);
	}
	

	private void guardarClientes(Galeria galeria, JSONObject jGaleria) {
		JSONArray jClientes = new JSONArray();
		for (Cliente cliente : galeria.getMapaClientes().values()) {
			JSONObject jCliente = new JSONObject();

			jCliente.put("login", cliente.getLogin());
			jCliente.put("password", cliente.getPassword());
			jCliente.put("telefono", cliente.getTelefono());
			jCliente.put("verificado", cliente.isVerificado());
			jCliente.put("topeCompras", cliente.getTopeCompras());

			jClientes.put(jCliente);
		}

		jGaleria.put("clientes", jClientes);
	}
	
	
	private void guardarPiezasPasadasPropietario(Galeria galeria, JSONObject jGaleria) {
		JSONArray jParejasPropietarioPiezas = new JSONArray();
		for (Cliente cliente : galeria.getMapaClientes().values()) {
			JSONObject jParejaPropietarioPiezas = new JSONObject();
			
			List<String> piezasPasadas = galeria.getPiezasPasadas(cliente.getLogin());

			jParejaPropietarioPiezas.put("propietario", cliente.getLogin());
			
			JSONArray listaPiezas = new JSONArray(piezasPasadas.size());
        	for (int i = 0; i < piezasPasadas.size(); i++) {
        		String tituloPieza = piezasPasadas.get(i);
        		listaPiezas.put(tituloPieza);
        	}
        	
			jParejaPropietarioPiezas.put("listaPiezasPasadas", listaPiezas);

			jParejasPropietarioPiezas.put(jParejaPropietarioPiezas);
		}

		jGaleria.put("piezasPasadasPropietario", jParejasPropietarioPiezas);
	}
}