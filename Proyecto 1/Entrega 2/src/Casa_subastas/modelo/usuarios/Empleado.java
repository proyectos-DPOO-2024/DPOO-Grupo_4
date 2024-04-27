package Casa_subastas.modelo.usuarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Empleado extends Usuario {
	public static final String Administrador = "administrador";
	public static final String Cajero = "cajero";
	public static final String Operador = "operador";
	private String rol;
	private String nombre;
	
	private static Map<String, Empleado> Empleados = new HashMap<String,Empleado>();
	
	public static Empleado getEmpleado(String login) {
		
		return Empleados.get(login);
	}
	
	public static Collection<Empleado> getEmpleados() {
		
		return Empleados.values();
	}
	
	public Empleado (String login, String password, String rol, String nombre, int cellphone) {
		
		super(login, password, cellphone);
		
		this.rol = rol;
		this.nombre = nombre;
		
		Empleados.put(login, this);
	}

	public static String getAdministrador() {
		return Administrador;
	}

	public static String getCajero() {
		return Cajero;
	}

	public static String getOperador() {
		return Operador;
	}

	public String getRol() {
		return rol;
	}

	public String getNombre() {
		return nombre;
	}
	
}
