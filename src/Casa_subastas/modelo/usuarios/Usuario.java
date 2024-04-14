package Casa_subastas.modelo.usuarios;

public abstract class Usuario {

	private String login;
	private String password;
	
	public Usuario (String login, String password) {
		
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	
}
