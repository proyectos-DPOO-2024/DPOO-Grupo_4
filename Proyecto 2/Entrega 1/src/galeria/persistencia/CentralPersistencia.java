/**
 * 
 */
package galeria.persistencia;

import java.io.File;
import java.io.IOException;
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
}
