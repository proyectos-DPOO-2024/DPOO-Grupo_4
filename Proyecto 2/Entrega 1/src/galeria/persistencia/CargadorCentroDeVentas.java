/**
 * 
 */
package galeria.persistencia;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.centroventas.CentroDeVentas;
import galeria.modelo.centroventas.Subasta;
import galeria.modelo.inventario.Galeria;

/**
 * 
 */
public class CargadorCentroDeVentas {

	protected void cargarCentroDeVentas(JSONObject raizCentroDeVentas, Galeria galeria) {
		
		try {
			cargarOfertas(galeria, raizCentroDeVentas.getJSONArray("ofertas"));
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
	
	private void cargarOfertas(Galeria galeria, JSONArray jOfertas) throws Exception {
		int numOfertas = jOfertas.length();
		for (int i = 0; i < numOfertas; i++) {
			JSONObject oferta = jOfertas.getJSONObject(i);

			String loginCliente = oferta.getString("loginCliente");
			String nombrePieza = oferta.getString("nombrePieza");
			boolean ofertaVerificada = oferta.getBoolean("ofertaVerificada");

			galeria.crearOfertaValorFijo(loginCliente, nombrePieza);

			if (ofertaVerificada) {
				galeria.verificarOfertaValorFijo(nombrePieza);
			}
		}

	}
	
	private void cargarSubastas(Galeria galeria, JSONArray jSubastas) throws Exception {
		int numSubastas = jSubastas.length();
		for (int i = 0; i < numSubastas; i++) {
			JSONObject subasta = jSubastas.getJSONObject(i);

			long valorMinimo = subasta.getLong("valorMinimo");
			long valorInicial = subasta.getLong("valorInicial");
			String nombrePieza = subasta.getString("nombrePieza");

			galeria.crearSubasta(nombrePieza, valorMinimo, valorInicial);

			Map<String, Subasta> mapaSubastas = galeria.getMapaSubastas();

			Subasta subastaObj = mapaSubastas.get(nombrePieza);

			JSONArray trazaDeOfertas = subasta.getJSONArray("trazaDeOfertas");

			if (!trazaDeOfertas.isEmpty()) {
				this.cargarOfertasSubasta(galeria, trazaDeOfertas, subastaObj);
			}

			boolean finalizada = subasta.getBoolean("finalizada");

			if (finalizada) {
				galeria.cerrarSubasta(nombrePieza);
			}

		}

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


