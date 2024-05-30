/**
 * 
 */
package galeria.interfaz;

import java.util.List;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.pasarelasDePago.PasarelaDePago;


/**
 * Este es el menú que verá un cajero al iniciar sesión. La clase también
 * implementa los métodos correspondientes para comunicarse con las clases
 * Galería y CasaDeSubasta.
 */
public class MenuCajero extends MenuEmpleado
{

	public static final int MODALIDAD_VENTA_DIRECTA = 0;
	public static final int MODALIDAD_SUBASTA = 1;

	public static List<Oferta> colaOfertasVentaDirecta;
	public static List<Oferta> colaOfertasSubasta;

	protected MenuCajero(MenuPrincipal menuPrincipal)
	{
		super(menuPrincipal);
		this.mostrarMenuCajero();
	}

	/**
	 * Esta función muestra el menú del cajero y ejecuta las funciones
	 * correspondientes según la respuesta del usuario.
	 */
	private void mostrarMenuCajero() {

		String[] opciones = new String[7];

		opciones[0] = "Registrar pago por venta directa";
		opciones[1] = "Registrar pago por subasta";
		opciones[2] = "Ver historia pieza";
		opciones[3] = "Ver historia artista";
		opciones[4] = "Exhibir pieza";
		opciones[5] = "Guardar pieza en bodega";
		opciones[6] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		if (opcionEscogida == 1) {
			this.registrarPagoPorVentaDirecta();
			this.mostrarMenuCajero();
		}

		if (opcionEscogida == 2) {
			this.registrarPagoPorSubasta();
			this.mostrarMenuCajero();
		}

		if (opcionEscogida == 3) {
			this.verHistoriaPieza();
			this.mostrarMenuCajero();
		}

		if (opcionEscogida == 4) {
			this.verHistoriaArtista();
			this.mostrarMenuCajero();
		}

		if (opcionEscogida == 5) {
			this.exhibirPieza();
			this.mostrarMenuCajero();
		}

		if (opcionEscogida == 6) {
			this.guardarPiezaEnBodega();
			this.mostrarMenuCajero();
		}

		else
			this.cerrarSesion(menuPrincipal);
	}

	/**
	 * Esta función registra una oferta de subasta como un pago con modalidad venta
	 * directa.
	 */
	private void registrarPagoPorVentaDirecta() {
		MenuPasarela menuPasarela = new MenuPasarela();
		PasarelaDePago pasarela = menuPasarela.getPasarela();
		MenuPago menuPago = new MenuPago(pasarela);
		
	}

	/**
	 * Esta función registra una oferta de subasta como un pago con modalidad
	 * subasta
	 */
	private void registrarPagoPorSubasta() {
		// TODO
	}

	/**
	 * Métodos Auxiliares
	 */

	/**
	 * Esta función agrega un pago a la lista de pagos del administrador. Se debe
	 * ejecutar al final de RegistrarPagoPorXXX.
	 */
	private void agregarPagoALista(Pago pago) {
		// TODO
	}
}
