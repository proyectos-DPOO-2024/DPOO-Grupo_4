/**
 * 
 */
package galeria.interfaz;

import java.util.Iterator;
import java.util.List;

import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.Transaccion;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;

/**
 * El objetivo de esta clase es implementar ciertos métodos que son comunes para todos los Usuarios (Empleados y Clientes).
 */
public abstract class MenuUsuario extends MenuBasico {

	MenuPrincipal menuPrincipal;
	Galeria galeria;
	
	protected MenuUsuario(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
		galeria = menuPrincipal.galeria;
	}
	
	
	protected void verHistoriaPieza() {
		String nombrePieza = pedirCadenaAlUsuario("Ingrese el título de la pieza que desea buscar");
		
		if (menuPrincipal.galeria.existePieza(nombrePieza)) {
			mostrarTransaccionesPieza(nombrePieza);
		}
		else {
			System.out.println(nombrePieza + " no ha sido registrada en la galería.");
		}
	}
	
	protected void verHistoriaArtista() {
		String nombreArtista = pedirCadenaAlUsuario("Ingrese el nombre del artista que desea buscar");
		
		if (menuPrincipal.galeria.existeArtista(nombreArtista)) {
			List<String> nombresPiezasArtista = menuPrincipal.galeria.getArtista(nombreArtista).getNombrePiezas();
			
			if (nombresPiezasArtista.isEmpty()) {
				System.out.println(nombreArtista + " no tiene piezas registradas");
			}
			else {
				menuMostrarTransaccionesVariasPiezas(nombreArtista, nombresPiezasArtista);
;			}
		}
		else {
			System.out.println(nombreArtista + " no ha sido registrado en la galería.");
		}
	}
	
	protected void verHistorialCliente(String loginCliente) {
		
		Cliente cliente = menuPrincipal.galeria.getCliente(loginCliente);
		
		if (cliente.isVerificado()) {
			List<Pago> listaCompras = menuPrincipal.galeria.getCentroDeVentas().getHistorialCompras(loginCliente);
			
			if (!listaCompras.isEmpty()) {
				Iterator<Pago> it = listaCompras.iterator();
				long totalCompras = 0;
				while (it.hasNext());{
					Pago pago = it.next();
					totalCompras += pago.getValor();
					mostrarInformacionPago(pago, true);
				}
				System.out.println("\nValor total de compras: " + Long.toString(totalCompras) + "\n");
			}
			else {
				System.out.println("El cliente ingresado no ha comprado piezas.\n");
			}
		}
		else {
			System.out.println("El cliente ingresado no es un comprador verificado.\n");
		}
		
		List<Pago> listaVentas = menuPrincipal.galeria.getCentroDeVentas().getHistorialVentas(loginCliente);
		
		if (!listaVentas.isEmpty()) {
			Iterator<Pago> it = listaVentas.iterator();
			long totalVentas = 0;
			while (it.hasNext());{
				Pago pago = it.next();
				totalVentas += pago.getValor();
				mostrarInformacionPago(pago, true);
			}
			System.out.println("\nValor total de ventas: " + Long.toString(totalVentas) + "\n");
		}
		else {
			System.out.println("El cliente ingresado no ha vendido piezas.\n");
		}
		
		List<String> listaNombresPiezasActuales = menuPrincipal.galeria.getPiezasActuales(loginCliente);
		
		if (!listaNombresPiezasActuales.isEmpty()) {
			Iterator<String> it = listaNombresPiezasActuales.iterator();
			long totalColeccion = 0;
			while (it.hasNext());{
				String nombrePieza = it.next();
				Pieza pieza = menuPrincipal.galeria.getPieza(nombrePieza);
				if (pieza.getPrecioUltimaVenta() < 0) {
					if (pieza.getPrecioVentaDirecta() < 0) {
						totalColeccion += pieza.getPrecioMinimoSubasta();
					}
					else {
						totalColeccion += pieza.getPrecioVentaDirecta();
					}
				}
				else {
					totalColeccion += pieza.getPrecioUltimaVenta();
				}
			}
			System.out.println("\nValor total de colección: " + Long.toString(totalColeccion) + "\n");
			menuMostrarInformacionVariasPiezas(loginCliente, listaNombresPiezasActuales, false, true);
		}
		else {
			System.out.println("El cliente ingresado no posee piezas.\n");
		}
		
		
	}
	
	protected void cerrarSesion(MenuPrincipal menuPrincipal) {}
	
	
	
	
	
	/**
	 * Métodos Auxiliares
	 */
	
	
	/**
	 * Este método muestra un menú continuo en el cual se pueden seleccionar piezas para ver sus transacciones repetidamente.
	 * @param nombreMensaje
	 * @param nombresPiezas
	 */
	protected void menuMostrarTransaccionesVariasPiezas(String nombreMensaje, List<String> nombresPiezas) {
		int numPiezas = nombresPiezas.size();
		String[] opciones = new String[numPiezas+1];
		Iterator<String> it = nombresPiezas.iterator();
		for (int i = 0; i < numPiezas && it.hasNext(); i++) {
			opciones[i] = it.next();
		}
		opciones[numPiezas+1] = "Salir";
		
		int opcionSeleccionada = mostrarMenu("Piezas de " + nombreMensaje, opciones, "Escoja la pieza de la que desee ver más información");
		
		if (opcionSeleccionada == numPiezas+1) {}
		
		else {
			mostrarTransaccionesPieza(nombresPiezas.get(opcionSeleccionada-1));
			menuMostrarTransaccionesVariasPiezas(nombreMensaje, nombresPiezas);
		}
	}
	
	
	/**
	 * Este método muestra un menú continuo en el cual se pueden seleccionar piezas para ver su información repetidamente.
	 * @param nombreMensaje
	 * @param nombresPiezas
	 */
	protected void menuMostrarInformacionVariasPiezas(String nombreMensaje, List<String> nombresPiezas, boolean incluirPropietario,
			boolean incluirPrecioMinimoSubasta) {
		int numPiezas = nombresPiezas.size();
		String[] opciones = new String[numPiezas+1];
		Iterator<String> it = nombresPiezas.iterator();
		for (int i = 0; i < numPiezas && it.hasNext(); i++) {
			opciones[i] = it.next();
		}
		opciones[numPiezas+1] = "Salir";
		
		int opcionSeleccionada = mostrarMenu("Piezas de " + nombreMensaje, opciones, "Escoja la pieza de la que desee ver más información");
		
		if (opcionSeleccionada == numPiezas+1) {}
		
		else {
			mostrarInformacionPieza(nombresPiezas.get(opcionSeleccionada-1), incluirPropietario, incluirPrecioMinimoSubasta);
			menuMostrarTransaccionesVariasPiezas(nombreMensaje, nombresPiezas);
		}
	}
	
	
	/**
	 * Este método muestra la información de una pieza
	 */
	protected void mostrarInformacionPieza(String nombrePieza, boolean incluirPropietario, boolean incluirPrecioMinimoSubasta) {
		
		Pieza pieza = menuPrincipal.galeria.getPieza(nombrePieza);
		
		System.out.println("Título: " + pieza.getTitulo());
		if (incluirPropietario) System.out.println("Propietario: " + pieza.getPropietario());
		if (pieza.getTipo() == Pieza.PINTURA) System.out.println("Tipo: Pintura");
		if (pieza.getTipo() == Pieza.IMPRESION) System.out.println("Tipo: Impresión");
		if (pieza.getTipo() == Pieza.ESCULTURA) System.out.println("Tipo: Escultura");
		if (pieza.getTipo() == Pieza.FOTOGRAFIA) System.out.println("Tipo: Fotografía");
		if (pieza.getTipo() == Pieza.VIDEO) System.out.println("Tipo: Video");
		if (pieza.isEnPosesion()){
			System.out.println("Pieza en posesión de la galería");
			System.out.println("Fecha término consignación: " + pieza.getFechaTerminoConsignacion());
			if (pieza.getPrecioVentaDirecta() >= 0) {
				System.out.println("Precio venta directa: " + Long.toString(pieza.getPrecioVentaDirecta()));
			}
			if (pieza.getPrecioUltimaVenta() >= 0) {
				System.out.println("Precio por el cual la pieza se vendió por última vez: " + Long.toString(pieza.getPrecioUltimaVenta()));
			}
			if (incluirPrecioMinimoSubasta) System.out.println("Precio mínimo subasta: " + pieza.getPrecioMinimoSubasta());
			System.out.println("Precio inicio subasta: " + pieza.getPrecioInicioSubasta());
			if (pieza.isBloqueada()) System.out.println("La pieza tiene una oferta en proceso");
			if (pieza.isEnSubasta()) System.out.println("La pieza tiene una subasta en proceso");
			if (pieza.isEnBodega()) System.out.println("La pieza se encuentra en la bodega");
			else System.out.println("La pieza está siendo exhibida");
		}
		else {
			System.out.println("La pieza ya no está en posesión de la galería");
		}
	}
	
	
	/**
	 * Este método muestra las transacciones de una pieza
	 */
	protected void mostrarTransaccionesPieza(String nombrePieza) {
		
		List<Pago> historialPieza = menuPrincipal.galeria.getCentroDeVentas().getHistorialPieza(nombrePieza);
		
		if (historialPieza == null) {
			System.out.println(nombrePieza + " no ha sido comprada/vendida");
		}
		else {
			System.out.println("Historial de transacciones de " + nombrePieza + ":\n");
			
			for (int i = 0; i < historialPieza.size(); i++) {
				System.out.println("Transacción #" + Integer.toString(i+1));
				mostrarInformacionPago(historialPieza.get(i), false);
			}
		}
	}
	
	
	/**
	 * Este método muestra la información de un pago
	 */
	protected void mostrarInformacionPago(Pago pago, boolean incluirNombrePieza) {
		
		mostrarInformacionTransaccion(pago, incluirNombrePieza);
		System.out.println("Método de pago: " + pago.getMetodoPago());
		System.out.println("Fecha: " + pago.getFecha());
	}
	
	
	/**
	 * Este método muestra la información de una transacción
	 */
	protected void mostrarInformacionTransaccion(Transaccion transaccion, boolean incluirNombrePieza) {
		
		if (incluirNombrePieza) System.out.println("Pieza: " + transaccion.getPieza().getTitulo());
		System.out.println("Valor: " + transaccion.getValor());
		System.out.println("Comprador: " + transaccion.getLoginComprador());
		System.out.println("Vendedor: " + transaccion.getLoginVendedor());
		
		int tipo = transaccion.getTipo();
		
		if (tipo == Transaccion.SUBASTA) System.out.println("Tipo: Subasta");
		if (tipo == Transaccion.VENTA_DIRECTA) System.out.println("Tipo: Venta directa");
	}
	
	
	/**
	 * Este método ayuda a llevar a cabo la creación de un nuevo login único.
	 */
	protected String crearNuevoLogin() {
		
		boolean unico = false;
		String login = "";
		while (!unico) {
			login = pedirCadenaAlUsuario("Por favor ingrese el login del nuevo empleado");
			unico = galeria.comprobarLoginUnico(login);
		}
		return login;
	}
	
	
}