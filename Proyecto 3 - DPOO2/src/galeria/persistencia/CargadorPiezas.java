package galeria.persistencia;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.inventario.Escultura;
import galeria.modelo.inventario.Fotografia;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Impresion;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.inventario.Video;

public class CargadorPiezas
{

	protected void cargarPiezas(Galeria galeria, JSONArray jPiezas) throws Exception {
		int numPiezas = jPiezas.length();
		for (int i = 0; i < numPiezas; i++) {
			JSONObject pieza = jPiezas.getJSONObject(i);

			String titulo = pieza.getString("titulo");
			int tipo = pieza.getInt("tipo");
			String nombreArtista = pieza.getString("nombreArtista");
			String loginPropietario = pieza.getString("loginPropietario");
			String fechaTerminoConsignacion = pieza.getString("fechaTerminoConsignacion");
			long precioVentaDirecta = pieza.getLong("precioVentaDirecta");
			long precioInicioSubasta = pieza.getLong("precioInicioSubasta");
			long precioMinimoSubasta = pieza.getLong("precioMinimoSubasta");
			long precioUltimaVenta = pieza.getLong("precioUltimaVenta");
			boolean bloqueada = pieza.getBoolean("bloqueada");
			boolean enSubasta = pieza.getBoolean("enSubasta");
			boolean enBodega = pieza.getBoolean("enBodega");
			boolean enPosesion = pieza.getBoolean("enPosesion");

			float alto;
			float ancho;
			boolean color;
			Pieza piezaObj;

			if (tipo == Pieza.PINTURA) {
				alto = pieza.getFloat("alto");
				ancho = pieza.getFloat("ancho");
				String estilo = pieza.getString("estilo");

				piezaObj = new Pintura(titulo, nombreArtista, loginPropietario, fechaTerminoConsignacion,
						precioVentaDirecta, precioInicioSubasta, precioMinimoSubasta, 
						precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion, 
						estilo, alto, ancho);

				if (tipo == Pieza.IMPRESION) {
					boolean original = pieza.getBoolean("original");
					String metodoDeCreacion = pieza.getString("metodoDeCreacion");
					alto = pieza.getFloat("alto");
					ancho = pieza.getFloat("ancho");

					piezaObj = new Impresion(titulo, nombreArtista, loginPropietario, fechaTerminoConsignacion,
							precioVentaDirecta, precioInicioSubasta, precioMinimoSubasta, 
							precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion,
							original, metodoDeCreacion, alto, ancho);
				}

				if (tipo == Pieza.ESCULTURA) {
					alto = pieza.getFloat("alto");
					ancho = pieza.getFloat("ancho");
					float profundidad = pieza.getFloat("profundidad");
					String materialDeConstruccion = pieza.getString("materialDeConstruccion");

					piezaObj = new Escultura(titulo, nombreArtista, loginPropietario, fechaTerminoConsignacion,
							precioVentaDirecta, precioInicioSubasta, precioMinimoSubasta, 
							precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion, 
							materialDeConstruccion, alto, ancho, profundidad);
				}

				if (tipo == Pieza.FOTOGRAFIA) {
					alto = pieza.getFloat("alto");
					ancho = pieza.getFloat("ancho");
					color = pieza.getBoolean("color");

					piezaObj = new Fotografia(titulo, nombreArtista, loginPropietario, fechaTerminoConsignacion,
							precioVentaDirecta, precioInicioSubasta, precioMinimoSubasta, 
							precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion, 
							alto, ancho, color);
				}

				if (tipo == Pieza.VIDEO) {
					float duracion = pieza.getFloat("alto");
					color = pieza.getBoolean("color");
					double memoria = pieza.getDouble("memoria");

					piezaObj = new Video(titulo, nombreArtista, loginPropietario, fechaTerminoConsignacion,
							precioVentaDirecta, precioInicioSubasta, precioMinimoSubasta, 
							precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion, 
							duracion, color, memoria);
				}

				galeria.agregarPiezaNueva(piezaObj);

			}
		}
	}
}
