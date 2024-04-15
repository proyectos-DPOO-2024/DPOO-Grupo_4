package Casa_subastas.Persistencias;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Escultura;
import Casa_subastas.modelo.Inventario.Fotografia;
import Casa_subastas.modelo.Inventario.Impresiones;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.Inventario.Pintura;
import Casa_subastas.modelo.Inventario.Video;

public class CargadorPiezas {

	public void cargarPiezas( Galeria galeria, JSONArray jPiezas ) throws Exception
    {
        int numPiezas = jPiezas.length( );
        for( int i = 0; i < numPiezas; i++ )
        {
            JSONObject pieza = jPiezas.getJSONObject( i );
            
            int tipo = pieza.getInt( "tipo" );
            String nombrePieza = pieza.getString( "nombrePieza" );
            int precio = pieza.getInt( "precio" );
            String propietario = pieza.getString( "propietario" );
            int diasConsignacion = pieza.getInt( "diasConsignacion" );
            boolean paraVentaFijo = pieza.getBoolean( "paraVentaFijo" );
            boolean bloqueada = pieza.getBoolean( "bloqueada" );
            boolean comprada = pieza.getBoolean( "comprada" );
            
            float alto;
            float ancho;
            boolean color;
            Pieza piezaObj;
            
            if (tipo == Pieza.PINTURA) {
            	alto = pieza.getFloat( "alto" );
            	ancho = pieza.getFloat( "ancho" );
            	String estilo = pieza.getString( "estilo" );
            	
            	piezaObj = new Pintura(nombrePieza, precio, propietario, diasConsignacion,
            			paraVentaFijo, bloqueada, comprada, alto, ancho, estilo);
            	
            
            if (tipo == Pieza.IMPRESIONES) {
            	String original = pieza.getString( "original" );
            	String metodoDeCreacion = pieza.getString( "metodoDeCreacion" );
            	alto = pieza.getFloat( "alto" );
            	ancho = pieza.getFloat( "ancho" );
            	
            	piezaObj = new Impresiones(nombrePieza, precio, propietario, diasConsignacion,
            			paraVentaFijo, bloqueada, comprada, original, metodoDeCreacion, alto, ancho);
            }
            
            if (tipo == Pieza.ESCULTURA) {
            	alto = pieza.getFloat( "alto" );
            	ancho = pieza.getFloat( "ancho" );
            	float profundidad = pieza.getFloat( "profundidad" );
            	String materialDeConstruccion = pieza.getString( "materialDeConstruccion" );
            	
            	piezaObj = new Escultura(nombrePieza, precio, propietario, diasConsignacion,
            			paraVentaFijo, bloqueada, comprada, alto, ancho, profundidad, materialDeConstruccion);
            }
            
            if (tipo == Pieza.FOTOGRAFIA) {
            	alto = pieza.getFloat( "alto" );
            	ancho = pieza.getFloat( "ancho" );
            	color = pieza.getBoolean( "color" );
            	
            	piezaObj = new Fotografia(nombrePieza, precio, propietario, diasConsignacion,
            			paraVentaFijo, bloqueada, comprada, alto, ancho, color);
            }
            
            if (tipo == Pieza.VIDEO) {
            	float duracion = pieza.getFloat( "alto" );
            	color = pieza.getBoolean( "color" );
            	double memoria = pieza.getDouble( "memoria" );
            	
            	piezaObj = new Video(nombrePieza, precio, propietario, diasConsignacion, paraVentaFijo,
            			bloqueada, comprada, duracion, color, memoria);
            }
            
            galeria.agregarPieza(piezaObj, propietario);
            
        }
    }
    }
}
