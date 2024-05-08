/**
 * 
 */
package galeria.persistencia;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.centroventas.Oferta;
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
			cargarSubastas(galeria, raizCentroDeVentas.getJSONArray("subastas"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		
	}
	
	private void cargarOfertasVentaDirecta(Galeria galeria, JSONArray jOfertas) throws Exception {
		
		List<Oferta> listaDeOfertasVentaDirecta = new LinkedList<Oferta>();
		
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
			
			listaDeOfertasVentaDirecta.add(ofertaObj);
		}
		
		galeria.getCentroDeVentas().setListaDeOfertasVentaDirecta(listaDeOfertasVentaDirecta);
	}
	
	private void cargarSubastas(Galeria galeria, JSONArray jSubastas) throws Exception {
		
		
		List<Oferta> listaDeOfertasVentaDirecta = new LinkedList<Oferta>();
		
		int numSubastas = jSubastas.length();
		for (int i = 0; i < numSubastas; i++) {
			JSONObject subasta = jSubastas.getJSONObject(i);

			String tituloPiezaSubastada = subasta.getString("tituloPiezaSubastada");
			long valorActual = subasta.getLong("valorActual");
			long valorMinimo = subasta.getLong("valorMinimo");
			int tipo = Transaccion.VENTA_DIRECTA;
			
			Pieza pieza = galeria.getPieza(tituloPieza);
			
			Oferta ofertaObj = new Oferta(pieza, valor, loginComprador, loginVendedor, tipo);
			
			listaDeOfertasVentaDirecta.add(ofertaObj);
		}
		
		galeria.getCentroDeVentas().setListaDeOfertasVentaDirecta(listaDeOfertasVentaDirecta);
	}
	
	
	private void cargarOfertasSubasta(Galeria galeria, JSONArray jOfertas, Subasta subasta) throws Exception {
		int numOfertas = jOfertas.length();
		for (int i = 0; i < numOfertas; i++) {
			JSONObject oferta = jOfertas.getJSONObject(i);

			String loginCliente = oferta.getString("loginCliente");
			String nombrePieza = oferta.getString("nombrePieza");
			int valor = oferta.getInt("valor");

			galeria.crearOfertaSubasta(nombrePieza, loginCliente, valor);
		}

	}
	
	
	private void cargarPagos(Galeria galeria, JSONArray jPagos) throws Exception {
		int numPagos = jPagos.length();
		for (int i = 0; i < numPagos; i++) {
			JSONObject pago = jPagos.getJSONObject(i);

			String metodoPago = pago.getString("metodoPago");
			String nombrePieza = pago.getString("nombrePieza");

			galeria.realizarPago(metodoPago, nombrePieza);
		}
	}

}


