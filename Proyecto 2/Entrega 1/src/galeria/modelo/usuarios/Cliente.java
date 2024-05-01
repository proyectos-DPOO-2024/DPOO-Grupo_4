package galeria.modelo.usuarios;

public class Cliente extends Usuario
{

	private boolean verificado;
	private long topeCompras;

	public Cliente(String login, String password, int cellphone)
	{
		super(login, password, cellphone, Usuario.CLIENTE);
	}

	public void verificarComoComprador(long topeCompras) {

		this.topeCompras = topeCompras;
		verificado = true;
	}

	public void extenderTopeCompras(long nuevoTope) {

		topeCompras = nuevoTope;
	}

	public void reducirTopeCompras(long reduccionTope) {

		topeCompras -= reduccionTope;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public long getTopeCompras() {
		return topeCompras;
	}

}