/**
 * 
 */
package galeria.interfaz;

import java.util.List;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.Subasta;
import galeria.modelo.centroventas.Transaccion;
import galeria.modelo.inventario.Pieza;

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
	/**
	 * Esta función registra una oferta de subasta como un pago con modalidad venta
	 * directa.
	 */
	private void registrarPagoPorVentaDirecta() {
	    // Obtener los detalles del pago
	    String tituloPieza = pedirCadenaAlUsuario("Ingrese el título de la pieza vendida");
	    long valorPago = pedirLongAlUsuario("Ingrese el valor de la venta");
	    String loginComprador = pedirCadenaAlUsuario("Ingrese el login del comprador");

	    // Verificar si la pieza existe en la galería
	    if (!galeria.existePieza(tituloPieza)) {
	        System.out.println("La pieza especificada no existe en la galería.");
	        return;
	    }

	    // Obtener la pieza desde la galería
	    Pieza piezaVendida = galeria.getPieza(tituloPieza);
	    String loginVendedor = piezaVendida.getLoginPropietario();

	    // Crear una nueva instancia de Pago utilizando la clase Transaccion
	    Pago nuevoPago = new Pago(piezaVendida, valorPago, loginComprador, loginVendedor, Transaccion.VENTA_DIRECTA, Pago.EFECTIVO, "");

	    // Agregar el nuevo pago al historial de pagos por pieza
	    galeria.getCentroDeVentas().getHistorialPieza(tituloPieza).add(nuevoPago);

	    // Agregar el nuevo pago al historial de compras del comprador
	    galeria.getCentroDeVentas().getHistorialCompras(loginComprador).add(nuevoPago);

	    // Agregar el nuevo pago al historial de ventas del vendedor
	    galeria.getCentroDeVentas().getHistorialVentas(loginVendedor).add(nuevoPago);

	    System.out.println("Pago registrado correctamente.");
	}


	/**
	 * Esta función registra una oferta de subasta como un pago con modalidad
	 * subasta
	 */
	private void registrarPagoPorSubasta() {
	    // Obtener el título de la pieza subastada
	    String tituloPieza = pedirCadenaAlUsuario("Ingrese el título de la pieza subastada");

	    // Verificar si la pieza existe en la galería
	    if (!galeria.existePieza(tituloPieza)) {
	        System.out.println("La pieza especificada no existe en la galería.");
	        return;
	    }

	    // Obtener la subasta correspondiente desde la galería
	    Subasta subasta = null;
	    for (Subasta s : galeria.getCentroDeVentas().getListaDeSubastas()) {
	        if (s.getTituloPiezaSubastada().equals(tituloPieza)) {
	            subasta = s;
	            break;
	        }
	    }

	    // Verificar si se encontró la subasta
	    if (subasta == null) {
	        System.out.println("No se encontró ninguna subasta para la pieza especificada.");
	        return;
	    }

	    // Obtener la oferta ganadora de la subasta
	    Oferta ofertaGanadora = subasta.finalizarSubasta();

	    // Crear un nuevo objeto Pago utilizando la oferta ganadora y el tipo SUBASTA
	    Pago nuevoPago = new Pago(ofertaGanadora.getPieza(), ofertaGanadora.getValor(), ofertaGanadora.getLoginComprador(),
	            ofertaGanadora.getLoginVendedor(), Transaccion.SUBASTA, Pago.EFECTIVO, "");

	    // Agregar el nuevo pago al historial de pagos por pieza
	    galeria.getCentroDeVentas().getHistorialPieza(tituloPieza).add(nuevoPago);

	    // Agregar el nuevo pago al historial de compras del comprador
	    galeria.getCentroDeVentas().getHistorialCompras(ofertaGanadora.getLoginComprador()).add(nuevoPago);

	    // Agregar el nuevo pago al historial de ventas del vendedor
	    galeria.getCentroDeVentas().getHistorialVentas(ofertaGanadora.getLoginVendedor()).add(nuevoPago);

	    System.out.println("Pago registrado correctamente.");
	}


	/**
	 * Métodos Auxiliares
	 */

	/**
	 * Esta función agrega un pago a la lista de pagos del administrador. Se debe
	 * ejecutar al final de RegistrarPagoPorXXX.
	 */

}
