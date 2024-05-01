/**
 * 
 */
package galeria.interfaz;

import galeria.modelo.usuarios.Cliente;

/**
 * 
 */
public class MenuCliente extends MenuUsuario
{

	private Cliente esteCliente;

	protected MenuCliente(MenuPrincipal menuPrincipal, String loginCliente)
	{
		super(menuPrincipal);

		esteCliente = menuPrincipal.galeria.getCliente(loginCliente);

		this.mostrarMenuCliente();
	}

	private void mostrarMenuCliente() {

		String[] opciones = new String[9];

		opciones[0] = "Consignar nueva pieza";
		opciones[1] = "Comprar pieza por venta directa";
		opciones[2] = "Realizar oferta en subasta";
		opciones[3] = "Aplicar para verificación de comprador";
		opciones[4] = "Solicitar extensión de tope de compras";
		opciones[5] = "Ver historial de compras";
		opciones[6] = "Ver historia de pieza";
		opciones[7] = "Ver historia de artista";
		opciones[8] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		if (opcionEscogida == 1) {
			this.consignarNuevaPieza();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 2) {
			this.comprarPiezaVentaDirecta();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 3) {
			this.realizarOfertaSubasta();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 4) {
			this.aplicarParaVerificacionComprador();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 5) {
			this.solicitarExtensionTopeDeCompras();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 6) {
			this.verHistorialCliente(esteCliente.getLogin());
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 7) {
			this.verHistoriaPieza();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 8) {
			this.verHistoriaArtista();
			this.mostrarMenuCliente();
		}

		else
			this.cerrarSesion(menuPrincipal);
	}

	private void consignarNuevaPieza() {
		// TODO
	}

	private void comprarPiezaVentaDirecta() {
		// TODO
	}

	private void realizarOfertaSubasta() {
		// TODO
	}

	private void aplicarParaVerificacionComprador() {
		// TODO
	}

	private void solicitarExtensionTopeDeCompras() {
		// TODO
	}

}
