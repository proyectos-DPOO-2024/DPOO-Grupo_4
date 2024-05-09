package galeria.persistencia;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.inventario.Galeria;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Empleado;

public class SalvadorGaleria
{

	protected void guardarGaleria(Galeria galeria, JSONObject jGaleria) throws Exception {

		salvarEmpleados(galeria, jGaleria);

		SalvadorPiezas salvadorPiezas = new SalvadorPiezas();
		salvadorPiezas.salvarPiezas(galeria, jGaleria);

		salvarClientes(galeria, jGaleria);
	}

	private void salvarEmpleados(Galeria galeria, JSONObject jGaleria) {
		JSONArray jEmpleados = new JSONArray();
		for (Empleado empleado : Empleado.getEmpleados()) {
			JSONObject jEmpleado = new JSONObject();

			jEmpleado.put("rol", empleado.getRol());
			jEmpleado.put("login", empleado.getLogin());
			jEmpleado.put("password", empleado.getPassword());
			jEmpleado.put("nombre", empleado.getNombre());
			jEmpleado.put("cellphone", empleado.getCellphone());

			jEmpleados.put(jEmpleado);
		}

		jGaleria.put("empleados", jEmpleados);
	}

	private void salvarClientes(Galeria galeria, JSONObject jGaleria) {
		JSONArray jClientes = new JSONArray();
		for (Cliente cliente : Cliente.getClientes()) {
			JSONObject jCliente = new JSONObject();

			jCliente.put("login", cliente.getLogin());
			jCliente.put("password", cliente.getPassword());
			jCliente.put("esComprador", cliente.isComprador());
			jCliente.put("esPropietario", cliente.getLogin());
			jCliente.put("cellphone", cliente.getCellphone());
			jCliente.put("valorMaximoCompras", cliente.getValorMaximoCompras());
			jCliente.put("esVerificado", cliente.isVerificado());

			jClientes.put(jCliente);
		}

		jGaleria.put("clientes", jClientes);
	}
}