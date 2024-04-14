package Casa_subastas.Persistencias;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Inventario.Galeria;

public class Loader {

	public void cargarGaleria(String archivo) throws Exception
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        Galeria galeria = new Galeria();

        try {
        cargarEmpleados( galeria, raiz.getJSONArray( "clientes" ) );
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
        	e.getStackTrace();
        }
        try {
        cargarTiquetes( aerolinea, raiz.getJSONArray( "tiquetes" ) );
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        	e.getStackTrace();
        }
    }

}
