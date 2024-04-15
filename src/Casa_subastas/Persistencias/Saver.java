package Casa_subastas.Persistencias;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Centro_compras.Oferta;
import Casa_subastas.modelo.Centro_compras.Subasta;
import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;
import Casa_subastas.modelo.usuarios.Empleado;


public class Saver {

	public void salvarGaleria( String archivo, Galeria galeria ) throws IOException
    {
        JSONObject jobject = new JSONObject( );

        salvarEmpleados( galeria, jobject );
       

        // Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }
	
	private void salvarEmpleados( Galeria galeria, JSONObject jobject )
    {
        JSONArray jEmpleados = new JSONArray( );
        for( Empleado empleado : Empleado.getEmpleados() )
        {
        	JSONObject jEmpleado = new JSONObject( );
        	
        	jEmpleado.put( "rol", empleado.getRol() );
        	jEmpleado.put( "login", empleado.getLogin() );
        	jEmpleado.put( "password", empleado.getPassword() );
        	jEmpleado.put( "nombre", empleado.getNombre() );
        	jEmpleado.put( "cellphone", empleado.getCellphone() );
        	
        	jEmpleados.put( jEmpleado );
        }

        jobject.put( "empleados", jEmpleados );
    }
}