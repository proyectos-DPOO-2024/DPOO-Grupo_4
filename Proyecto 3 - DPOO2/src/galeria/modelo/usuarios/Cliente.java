package galeria.modelo.usuarios;

public class Cliente extends Usuario
{

	private boolean verificado;
	private long topeCompras;

	public Cliente(String login, String password, int telefono)
	{
		super(login, password, telefono, Usuario.CLIENTE);
	}
	
	public Cliente(String login, String password, int telefono, boolean verificado, long topeCompras)
	{
		super(login, password, telefono, Usuario.CLIENTE);
		
		this.verificado = verificado;
		this.topeCompras = topeCompras;
	}
	

	public void verificarComoComprador(long tope) {
		verificado = true;
		topeCompras = tope;
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