/**
 * 
 */
package galeria.modelo.centroventas;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public class CentroDeVentas
{

	/**
	 * El mapa de historial de pagos por piezas relaciona el nombre de una pieza con
	 * la lista de pagos donde la pieza sse ha visto involucrada. El mapa de piezas
	 * actuales de propietarios relaciona el login de un cliente con la lista del
	 * nombre de las piezas que posee actualmente. El mapa de piezas pasadas de
	 * propietarios relaciona el login de un cliente con la lista del nombre de las
	 * piezas que poseyó en el pasado.
	 */

	private Map<String, List<Pago>> historialDePagosPorPieza;

	private Map<String, List<Pago>> historialComprasComprador;
	private Map<String, List<Pago>> historialVentasPropietario;

	private List<Subasta> listaDeSubastas;
	private List<Oferta> listaDeOfertasVentaDirecta;
	
	public CentroDeVentas() {
		historialDePagosPorPieza = new HashMap<String, List<Pago>>();
		historialComprasComprador = new HashMap<String, List<Pago>>();
		listaDeSubastas = new LinkedList<Subasta>();
		listaDeOfertasVentaDirecta = new LinkedList<Oferta>();
	}

	public List<Pago> getHistorialPieza(String nombrePieza) {
		return historialDePagosPorPieza.get(nombrePieza);
	}

	public List<Pago> getHistorialCompras(String loginComprador) {
		return historialComprasComprador.get(loginComprador);
	}

	public List<Pago> getHistorialVentas(String loginPropietario) {
		return historialVentasPropietario.get(loginPropietario);
	}
	public List<Subasta> getListaDeSubastas() {
	    return listaDeSubastas;
	}
	public List<Oferta> getListaDeOfertasVentaDirecta() {
	    return listaDeOfertasVentaDirecta;
	}
}
