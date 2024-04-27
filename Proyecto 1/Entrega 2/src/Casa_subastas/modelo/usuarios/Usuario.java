package Casa_subastas.modelo.usuarios;

import java.util.Map;

public abstract class Usuario {

	private String login;
	private String password;
	private int cellphone;
	
	public Usuario(String login, String password, int cellphone) {
		
		this.login = login;
		this.password = password;
		this.cellphone = cellphone;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int getCellphone() {
		return cellphone;
	}
	
	
}
