/**
 * 
 */
package galeria.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONObject;

import galeria.modelo.inventario.Galeria;

/**
 * 
 */
public class CentralPersistencia {

	
	public Galeria cargarPrograma(String[] archivos) throws IOException {
		
		String jsonGaleria = new String(Files.readAllBytes(new File(archivos[0]).toPath()));
		String jsonCentroVentas = new String(Files.readAllBytes(new File(archivos[1]).toPath()));
		
		JSONObject raizGaleria = new JSONObject(jsonGaleria);
		JSONObject raizCentroDeVentas = new JSONObject(jsonCentroVentas);
		
		CargadorGaleria cargadorGaleria  = new CargadorGaleria();
		Galeria galeria = cargadorGaleria.cargarGaleria(raizGaleria);
		
		CargadorCentroDeVentas cargadorCDV = new CargadorCentroDeVentas();
		cargadorCDV.cargarCentroDeVentas(raizCentroDeVentas, galeria);
		
		return galeria;
	}
	
	
	public void guardarPrograma(Galeria galeria) throws Exception {
		
		String archivoGaleria = "./datos/nuevaGaleria.json";
		String archivoCentroDeVentas = "./datos/nuevoCentroDeVentas.json";
		
		JSONObject jGaleria = new JSONObject( );
		JSONObject jCentroDeVentas = new JSONObject( );
		
		SalvadorGaleria salvadorGaleria = new SalvadorGaleria();
		salvadorGaleria.guardarGaleria(galeria, jGaleria);
		
		SalvadorCentroDeVentas salvadorCentroDeVentas = new SalvadorCentroDeVentas();
		salvadorCentroDeVentas.guardarCentroDeVentas(galeria, jCentroDeVentas);
		
		PrintWriter pw = new PrintWriter( archivoGaleria );
        jGaleria.write( pw, 2, 0 );
        pw.close( );
        
        PrintWriter pw2 = new PrintWriter( archivoCentroDeVentas );
        jCentroDeVentas.write( pw, 2, 0 );
        pw2.close( );
		
	}
}
