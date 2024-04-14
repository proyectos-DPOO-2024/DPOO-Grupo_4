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
        try {
            cargarOfertas( galeria, raiz.getJSONArray( "ofertas" ) );
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
            	Empleado empleadoObj = new Empleado(login, password, rol, nombre, cellphone);
            }
            else {
            	Exception e = new Exception("Un empleado en el archivo no tiene un rol válido.");
            	throw e;
            }
        }
    }
	
	
	private void cargarClientes( Galeria galeria, JSONArray jClientes ) throws Exception
    {
        int numClientes = jClientes.length( );
        for( int i = 0; i < numClientes; i++ )
        {
            JSONObject cliente = jClientes.getJSONObject( i );
            
            String login = cliente.getString( "login" );
            String password = cliente.getString( "password" );
            boolean esComprador = cliente.getBoolean( "esComprador" );
            boolean esPropietario = cliente.getBoolean( "esPropietario" );
            int cellphone = cliente.getInt( "cellphone" );
            long valorMaximoCompras = cliente.getLong( "valorMaximoCompras" );
            boolean esVerificado = cliente.getBoolean( "esVerificado" );
            
            galeria.agregarCliente(login, password, esComprador, esPropietario, cellphone, valorMaximoCompras, esVerificado);
        }

    }
	
	private void cargarOfertas( Galeria galeria, JSONArray jOfertas ) throws Exception
    {
        int numOfertas = jOfertas.length( );
        for( int i = 0; i < numOfertas; i++ )
        {
            JSONObject oferta = jOfertas.getJSONObject( i );
            
            String login = cliente.getString( "login" );
            String password = cliente.getString( "password" );
            boolean esComprador = cliente.getBoolean( "esComprador" );
            boolean esPropietario = cliente.getBoolean( "esPropietario" );
            int cellphone = cliente.getInt( "cellphone" );
            long valorMaximoCompras = cliente.getLong( "valorMaximoCompras" );
            boolean esVerificado = cliente.getBoolean( "esVerificado" );
            
            galeria.agregarCliente(login, password, esComprador, esPropietario, cellphone, valorMaximoCompras, esVerificado);
        }

    }

}

