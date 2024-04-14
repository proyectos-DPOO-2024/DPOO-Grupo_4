package Casa_subastas.modelo.usuarios;

public class Empleado extends Usuario {
	public static final String Administrador = "administrador";
	public static final String Cajero = "cajero";
	public static final String Operador = "operador";
	
	private String rol;
	private int cellphone;
	
	public Empleado (String login, String password, String rol, int cellphone) {
		
		super(login, password);
		
		this.rol = rol;
		this.cellphone = cellphone;
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

	public int getCellphone() {
		return cellphone;
	}
	
	
}
