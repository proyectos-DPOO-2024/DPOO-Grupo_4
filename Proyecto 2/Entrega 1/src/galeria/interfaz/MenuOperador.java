/**
 * 
 */
package galeria.interfaz;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import galeria.modelo.centroventas.Oferta;

/**
 * Este es el menú que verá un operador al iniciar sesión. La clase también
 * implementa los métodos correspondientes para comunicarse con las clases
 * Galería y CasaDeSubasta.
 */
public class MenuOperador extends MenuEmpleado
{

	public static Queue<Oferta> colaOfertas;

	protected MenuOperador(MenuPrincipal menuPrincipal)
	{
		super(menuPrincipal);

		this.mostrarMenuOperador();
	}

	/**
	 * Esta función muestra el menú del operador y ejecuta las funciones
	 * correspondientes según la respuesta del usuario.
	 */
	private void mostrarMenuOperador() {

		String[] opciones = new String[4];

		opciones[0] = "Agregar nueva oferta en subasta";
		opciones[1] = "Ver historia pieza";
		opciones[2] = "Ver historia artista";
		opciones[3] = "Exhibir pieza";
		opciones[4] = "Guardar pieza en bodega";
		opciones[5] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		if (opcionEscogida == 1) {
			this.agregarNuevaOfertaSubasta();
			this.mostrarMenuOperador();
		}

		if (opcionEscogida == 2) {
			this.verHistoriaPieza();
			this.mostrarMenuOperador();
		}

		if (opcionEscogida == 3) {
			this.verHistoriaArtista();
			this.mostrarMenuOperador();
		}

		if (opcionEscogida == 4) {
			this.exhibirPieza();
			this.mostrarMenuOperador();
		}

		if (opcionEscogida == 5) {
			this.guardarPiezaEnBodega();
			this.mostrarMenuOperador();
		}

		else
			this.cerrarSesion(menuPrincipal);

	}

	/**
	 * Esta función se encarga de registrar una nueva oferta en la subasta como
	 * "oferta máxima". Se pregunta al operador si desea registrar la oferta. Si
	 * aprueba, el nuevo máximo se actuualiza. Si rechaza, la oferta sale de la cola
	 * y la subasta permanece igual.
	 */
	protected void agregarNuevaOfertaSubasta() {
		// TODO
	}

	/**
	 * Métodos Auxiliares
	 */

	/**
	 * 
	 * IGNORAR ESTE MÉTODO
	 * 
	 * Esta función se ejecuta después de agregar una nueva oferta máxima. La idea
	 * es quitar las ofertas que se hayan realizado en esa misma subasta que tengan
	 * un valor ofecido menor al nuevo máximo. En caso de que se decida bloquear la
	 * adición de nuevas ofertas en una subasta cuando se realiza una nueva oferta
	 * máxima (que aún no ha sido agregada por un operador) se puede ignorar esta
	 * función.
	 */
	private void actualizarColaOfertasSubasta(Oferta oferta) {
		// TODO
	}
}
