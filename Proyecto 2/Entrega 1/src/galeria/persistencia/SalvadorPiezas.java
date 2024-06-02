package galeria.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.inventario.Escultura;
import galeria.modelo.inventario.Fotografia;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Impresion;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.inventario.Video;
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
	
			if ( pieza.getTipo() == Pieza.PINTURA) { 
				
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "estilo", pintura.getEstilo() );
				jPieza.put( "alto", pintura.getAlto() );
				jPieza.put( "ancho", pintura.getAncho() );
			}
			
			if ( pieza.getTipo() == Pieza.IMPRESION) { 
				
				Impresion impresion = (Impresion) pieza;
				jPieza.put( "original", impresion.isOriginal() );
				jPieza.put( "metodoDeCreacion", impresion.getMetodoCreacion() );
				jPieza.put( "alto", impresion.getAlto() );
				jPieza.put( "ancho", impresion.getAncho() );
			}
			
			if ( pieza.getTipo() == Pieza.ESCULTURA) { 
				
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "materialDeConstruccion", escultura.getMaterialConstruccion() );
				jPieza.put( "alto", escultura.getAlto() );
				jPieza.put( "ancho", escultura.getAncho() );
				jPieza.put( "profundidad", escultura.getProfundidad() );
			}
			
			if ( pieza.getTipo() == Pieza.FOTOGRAFIA) { 
				
				Fotografia fotografia = (Fotografia) pieza;
				jPieza.put( "color", fotografia.isColor() );
				jPieza.put( "alto", fotografia.getAlto() );
				jPieza.put( "ancho", fotografia.getAncho() );
			}
			
			if ( pieza.getTipo() == Pieza.VIDEO) { 
				
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "color", video.isColor() );
				jPieza.put( "memoria", video.getMemoria() );
			}
	
			jPiezas.put(jPieza);
		}
		jobject.put("piezas", jPiezas);
	}
}
