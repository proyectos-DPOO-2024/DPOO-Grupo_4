/**
 * 
 */
package galeria.modelo.centroventas;

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
	private List<Subasta> listaDeOfertasVentaDirecta;

	public List<Pago> getHistorialPieza(String nombrePieza) {
		return historialDePagosPorPieza.get(nombrePieza);
	}

	public List<Pago> getHistorialCompras(String loginComprador) {
		return historialComprasComprador.get(loginComprador);
	}

	public List<Pago> getHistorialVentas(String loginPropietario) {
		return historialVentasPropietario.get(loginPropietario);
	}

}
