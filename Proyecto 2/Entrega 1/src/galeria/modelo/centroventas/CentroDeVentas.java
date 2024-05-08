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

	private Map<String, Subasta> mapaDeSubastas;
	private Map<String, Oferta> mapaDeOfertasVentaDirecta;
	
	public CentroDeVentas() {
		historialDePagosPorPieza = new HashMap<String, List<Pago>>();
		historialComprasComprador = new HashMap<String, List<Pago>>();
		mapaDeSubastas = new HashMap<String, Subasta>();
		mapaDeOfertasVentaDirecta = new HashMap<String, Oferta>();
	}
	
	public CentroDeVentas(Map<String, Subasta> mapaDeSubastas, Map<String, Oferta> mapaDeOfertasVentaDirecta, Map<String, List<Pago>> historialDePagosPorPieza,
			Map<String, List<Pago>> historialComprasComprador, Map<String, List<Pago>> historialVentasPropietario) {
		
		this.mapaDeSubastas = mapaDeSubastas;
		this.mapaDeOfertasVentaDirecta = mapaDeOfertasVentaDirecta;
		this.historialDePagosPorPieza = historialDePagosPorPieza;
		this.historialComprasComprador = historialComprasComprador;
		this.historialVentasPropietario = historialVentasPropietario;
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
	
	
	//Setters
	public void setHistorialDePagosPorPieza(Map<String, List<Pago>> historialDePagosPorPieza) {
		this.historialDePagosPorPieza = historialDePagosPorPieza;
	}

	public void setHistorialComprasComprador(Map<String, List<Pago>> historialComprasComprador) {
		this.historialComprasComprador = historialComprasComprador;
	}

	public void setHistorialVentasPropietario(Map<String, List<Pago>> historialVentasPropietario) {
		this.historialVentasPropietario = historialVentasPropietario;
	}

	public void setMapaDeSubastas(Map<String, Subasta> mapaDeSubastas) {
		this.mapaDeSubastas = mapaDeSubastas;
	}

	public void setMapaDeOfertasVentaDirecta(Map<String, Oferta> mapaDeOfertasVentaDirecta) {
		this.mapaDeOfertasVentaDirecta = mapaDeOfertasVentaDirecta;
	}
	
	
}
