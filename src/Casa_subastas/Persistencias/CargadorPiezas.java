package Casa_subastas.Persistencias;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Impresiones;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.Inventario.Pintura;
import Casa_subastas.modelo.usuarios.Empleado;

public class CargadorPiezas {

	private void cargarPiezas( Galeria galeria, JSONArray jPiezas ) throws Exception
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
            	String metodoDeCreacion = pieza.getString( "metodoDeCreacion" );
            	
            	piezaObj = new Escultura(nombrePieza, precio, propietario, diasConsignacion,
            			paraVentaFijo, bloqueada, comprada, original, metodoDeCreacion, alto, ancho);
            }
            
        }
    }
    }
}
