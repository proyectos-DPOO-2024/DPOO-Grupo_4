/**
 * 
 */
package galeria.persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.centroventas.Fecha;
import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.Subasta;
import galeria.modelo.centroventas.Transaccion;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;

/**
 * 
 */
public class CargadorCentroDeVentas {

	protected void cargarCentroDeVentas(JSONObject raizCentroDeVentas, Galeria galeria) {
		
		try {
			cargarOfertasVentaDirecta(galeria, raizCentroDeVentas.getJSONArray("listaOfertasVentaDirecta"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarSubastas(galeria, raizCentroDeVentas.getJSONArray("listaSubastas"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarHistorialDePagosPorPieza(galeria, raizCentroDeVentas.getJSONArray("historialDePagosPorPieza"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarHistorialDeComprasComprador(galeria, raizCentroDeVentas.getJSONArray("historialDeComprasComprador"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		try {
			cargarHistorialDeVentasPropietario(galeria, raizCentroDeVentas.getJSONArray("historialDeVentasPropietario"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		
	}
	
	private void cargarOfertasVentaDirecta(Galeria galeria, JSONArray jOfertas) throws Exception {
		
		Map<String, Oferta> mapaDeOfertasVentaDirecta = new HashMap<String, Oferta>();
		
		int numOfertas = jOfertas.length();
		for (int i = 0; i < numOfertas; i++) {
			JSONObject oferta = jOfertas.getJSONObject(i);

			String tituloPieza = oferta.getString("tituloPieza");
			long valor = oferta.getLong("valor");
			String loginComprador = oferta.getString("loginComprador");
			String loginVendedor = oferta.getString("loginVendedor");
			int tipo = Transaccion.VENTA_DIRECTA;
			
			Pieza pieza = galeria.getPieza(tituloPieza);
			
			Oferta ofertaObj = new Oferta(pieza, valor, loginComprador, loginVendedor, tipo);
			
			mapaDeOfertasVentaDirecta.put(tituloPieza, ofertaObj);
		}
		
		galeria.getCentroDeVentas().setMapaOfertasVentaDirecta(mapaDeOfertasVentaDirecta);
	}
	
	private void cargarSubastas(Galeria galeria, JSONArray jSubastas) throws Exception {
		
		
		Map<String, Subasta> mapaDeSubastas = new HashMap<String, Subasta>();
		
		int numSubastas = jSubastas.length();
		for (int i = 0; i < numSubastas; i++) {
			JSONObject subasta = jSubastas.getJSONObject(i);

			String tituloPiezaSubastada = subasta.getString("tituloPiezaSubastada");
			long valorActual = subasta.getLong("valorActual");
			long valorMinimo = subasta.getLong("valorMinimo");
			
			JSONArray trazaOfertas = subasta.getJSONArray("trazaOfertas");
			
			Stack<Oferta> trazaOfertasObj = cargarTrazaOfertas(galeria, trazaOfertas);
			
			Subasta subastaObj = new Subasta(tituloPiezaSubastada, valorActual, valorMinimo, trazaOfertasObj);
			
			mapaDeSubastas.put(tituloPiezaSubastada, subastaObj);
			
		}
		
		galeria.getCentroDeVentas().setMapaSubastas(mapaDeSubastas);
	}
	
	
	private void cargarHistorialDePagosPorPieza(Galeria galeria, JSONArray jPagos) throws Exception {
		
		
		Map<String, List<Pago>> mapaDePagos = new HashMap<String, List<Pago>>();
		
		int numPiezas = jPagos.length();
		for (int i = 0; i < numPiezas; i++) {
			JSONObject parejaPiezaPagos = jPagos.getJSONObject(i);

			String tituloPieza = parejaPiezaPagos.getString("tituloPieza");
			
			JSONArray listaPagos = parejaPiezaPagos.getJSONArray("listaPagos");
			
			List<Pago> listaPagosObj = cargarListaPagos(galeria, listaPagos);
			
			mapaDePagos.put(tituloPieza, listaPagosObj);
		}
		
		galeria.getCentroDeVentas().setHistorialDePagosPorPieza(mapaDePagos);
	}
	
	
	private void cargarHistorialDeComprasComprador(Galeria galeria, JSONArray jPagos) throws Exception {
		
		
		Map<String, List<Pago>> mapaDePagos = new HashMap<String, List<Pago>>();
		
		int numCompradores = jPagos.length();
		for (int i = 0; i < numCompradores; i++) {
			JSONObject parejaCompradoresPagos = jPagos.getJSONObject(i);

			String loginComprador = parejaCompradoresPagos.getString("loginComprador");
			
			JSONArray listaPagos = parejaCompradoresPagos.getJSONArray("listaPagos");
			
			List<Pago> listaPagosObj = cargarListaPagos(galeria, listaPagos);
			
			mapaDePagos.put(loginComprador, listaPagosObj);
		}
		
		galeria.getCentroDeVentas().setHistorialDePagosPorPieza(mapaDePagos);
	}
	
	
	private void cargarHistorialDeVentasPropietario(Galeria galeria, JSONArray jPagos) throws Exception {
		
		
		Map<String, List<Pago>> mapaDePagos = new HashMap<String, List<Pago>>();
		
		int numVendedores = jPagos.length();
		for (int i = 0; i < numVendedores; i++) {
			JSONObject parejaPropietariosPagos = jPagos.getJSONObject(i);

			String loginVendedor = parejaPropietariosPagos.getString("loginComprador");
			
			JSONArray listaPagos = parejaPropietariosPagos.getJSONArray("listaPagos");
			
			List<Pago> listaPagosObj = cargarListaPagos(galeria, listaPagos);
			
			mapaDePagos.put(loginVendedor, listaPagosObj);
		}
		
		galeria.getCentroDeVentas().setHistorialDePagosPorPieza(mapaDePagos);
	}

	
	
	
	//Funciones auxiliares
	
	private Stack<Oferta> cargarTrazaOfertas(Galeria galeria, JSONArray trazaOfertas) {
		
		Stack<Oferta> trazaDesorganizada = new Stack<Oferta>();
		Stack<Oferta> trazaOrganizada = new Stack<Oferta>();
		
		for (int i = 0; i < trazaOfertas.length(); i++) {
			JSONObject oferta = trazaOfertas.getJSONObject(i);
			
			String tituloPieza = oferta.getString("tituloPieza");
			long valor = oferta.getLong("valor");
			String loginComprador = oferta.getString("loginComprador");
			String loginVendedor = oferta.getString("loginVendedor");
			int tipo = Transaccion.SUBASTA;
			
			Pieza pieza = galeria.getPieza(tituloPieza);
			
			Oferta ofertaObj = new Oferta(pieza, valor, loginComprador, loginVendedor, tipo);
			
			trazaDesorganizada.push(ofertaObj);
		}
		
		int tamanoTrazaDesorganizada = trazaDesorganizada.size();
		
		for (int i = 0; i < tamanoTrazaDesorganizada; i++) {
			Oferta ofertaObj = trazaDesorganizada.pop();
			trazaOrganizada.push(ofertaObj);
		}
		
		return trazaOrganizada;
	}
	
	private List<Pago> cargarListaPagos(Galeria galeria, JSONArray listaPagos) throws Exception {
		
		List<Pago> listaPagosObj = new LinkedList<Pago>();
		
		int numPagos = listaPagos.length();
		for (int i = 0; i < numPagos; i++) {
			
			JSONObject pago = listaPagos.getJSONObject(i);
			
			String tituloPieza = pago.getString("tituloPieza");
			long valor = pago.getLong("valor");
			String loginComprador = pago.getString("loginComprador");
			String loginVendedor = pago.getString("loginVendedor");
			int tipo = pago.getInt("tipo");
			int metodoDePago = pago.getInt("metodoDePago");
			
			JSONArray fecha = pago.getJSONArray("fecha");
			int dia = fecha.getInt(0);
			int mes = fecha.getInt(1);
			int anio = fecha.getInt(2);
			
			Fecha fechaObj = new Fecha(dia, mes, anio);
			
			Pieza pieza = galeria.getPieza(tituloPieza);
			
			Pago pagoObj = new Pago(pieza, valor, loginComprador, loginVendedor, tipo, metodoDePago, fechaObj);
			listaPagosObj.add(pagoObj);
		}
		
		return listaPagosObj;
	}
}


