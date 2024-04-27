package galeria.modelo.usuarios;

public abstract class Usuario {
	
	public static final int ADMINISTRADOR = 1;
	public static final int CAJERO = 2;
	public static final int OPERADOR = 3;
	public static final int CLIENTE = 4;

	private String login;
	private String password;
	private int telefono;
	private int rol;
	
	public Usuario(String login, String password, int telefono, int rol) {
		
		this.login = login;
		this.password = password;
		this.telefono = telefono;
		this.rol = rol;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int getTelefono() {
		return telefono;
	}
	
	public int getRol() {
		return rol;
	}
	
	
}
