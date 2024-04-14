package Casa_subastas.Persistencias;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.usuarios.Empleado;


public class Loader {

	public void cargarGaleria(String archivo) throws Exception
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        Galeria galeria = new Galeria();

        try {
        cargarEmpleados( galeria, raiz.getJSONArray( "empleados" ) );
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
        	e.getStackTrace();
        }
        try {
        
        JSONArray piezas = raiz.getJSONArray( "piezas" );
        	
        CargadorPiezas cargPiezas = new CargadorPiezas();
        cargPiezas.cargarPiezas(galeria, piezas);
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        	e.getStackTrace();
        }
        try {
            cargarClientes( galeria, raiz.getJSONArray( "clientes" ) );
            }
            catch (Exception e) {
            	System.out.println(e.getMessage());
            	e.getStackTrace();
            }
    }
	
	
	private void cargarEmpleados( Galeria galeria, JSONArray jEmpleados ) throws Exception
    {
        int numEmpleados = jEmpleados.length( );
        for( int i = 0; i < numEmpleados; i++ )
        {
            JSONObject empleado = jEmpleados.getJSONObject( i );
            
            String rol = empleado.getString( "rol" );
            String login = empleado.getString( "login" );
            String password = empleado.getString( "password" );
            String nombre = empleado.getString( "nombre" );
            int cellphone = empleado.getInt( "cellphone" );
            
            if (rol.equals(Empleado.Administrador) || rol.equals(Empleado.Cajero) || rol.equals(Empleado.Operador)) {
            	Empleado administrador = new Empleado(login, password, rol, nombre, cellphone);
            }
            else {
            	Exception e = new Exception("Un empleado en el archivo no tiene un rol válido.");
            	throw e;
            }
        }
    }
	
	
	
	

}

