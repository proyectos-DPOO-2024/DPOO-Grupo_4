/**
 * 
 */
package galeria.modelo.centroventas;

/**
 * Esta clase representa una solicitud de extensión de tope de compras. Se debe
 * crear y guardar en la lista correspondiente (en el menú administrador) cuando
 * un cliente solicite una extensión.
 */
public class SolicitudTope
{

	private String loginComprador;
	private long topeSolicitado;

	public SolicitudTope(String loginComprador, long topeSolicitado)
	{
		this.loginComprador = loginComprador;
		this.topeSolicitado = topeSolicitado;
	}

	public String getLoginComprador() {
		return loginComprador;
	}

	public long getTopeSolicitado() {
		return topeSolicitado;
	}

}
