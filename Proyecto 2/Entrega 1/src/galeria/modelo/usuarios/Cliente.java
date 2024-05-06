package galeria.modelo.usuarios;

public class Cliente extends Usuario
{

	private boolean verificado;
	private long topeCompras;

	public Cliente(String login, String password, int cellphone)
	{
		super(login, password, cellphone, Usuario.CLIENTE);
	}

	public void verificarComoComprador() {
		verificado = true;
	}

	public void asignarTopeCompras(long nuevoTope) {

		topeCompras = nuevoTope;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public long getTopeCompras() {
		return topeCompras;
	}

}