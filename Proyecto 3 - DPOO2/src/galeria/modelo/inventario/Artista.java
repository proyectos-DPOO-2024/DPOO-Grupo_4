/**
 * 
 */
package galeria.modelo.inventario;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase representando el artista que crea las piezas
 */
public class Artista
{

	private String nombre;
	private List<String> nombrePiezas;

	public Artista(String nombre)
	{

		this.nombre = nombre;
		LinkedList<String> nombrePiezas = new LinkedList<String>();
		this.nombrePiezas = nombrePiezas;
	}

	public void agregarPieza(String nombrePieza) {
		this.nombrePiezas.add(nombrePieza);
	}

	public String getNombre() {
		return nombre;
	}

	public List<String> getNombrePiezas() {
		return nombrePiezas;
	}

}