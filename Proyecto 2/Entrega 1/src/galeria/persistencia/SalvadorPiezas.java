package galeria.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;


public class SalvadorPiezas
{

	protected void guardarPiezas(Galeria galeria, JSONObject jobject) throws Exception {

		JSONArray jPiezas = new JSONArray();

		for (Pieza pieza : galeria.getMapaPiezas().values()) {
			JSONObject jPieza = new JSONObject();
	
			jPieza.put("titulo", pieza.getTitulo());
			jPieza.put("tipo", pieza.getTipo());
			jPieza.put("nombreArtista", pieza.getNombreArtista());
			jPieza.put("loginPropietario", pieza.getLoginPropietario());
			jPieza.put("precioVentaDirecta", pieza.getPrecioVentaDirecta());
			jPieza.put("precioInicioSubasta", pieza.getPrecioInicioSubasta());
			jPieza.put("precioMinimoSubasta", pieza.getPrecioMinimoSubasta());
			jPieza.put("precioUltimaVenta", pieza.getPrecioUltimaVenta());
			jPieza.put("bloqueada", pieza.isBloqueada());
			jPieza.put("enSubasta", pieza.isEnSubasta());
			jPieza.put("enBodega", pieza.isEnBodega());
			jPieza.put("enPosesion", pieza.isEnPosesion());
	
			/*
			 * if ( it.next().getTipo() == Pieza.ESCULTURA) { jPieza.put( "alto",
			 * it.next().); }
			 */
	
			jPiezas.put(jPieza);
		}
		jobject.put("piezas", jPiezas);
	}
}
