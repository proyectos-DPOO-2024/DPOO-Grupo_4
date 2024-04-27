/**
 * 
 */
package galeria.interfaz;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;

/**
 * Este es el menú que verá un administrador al iniciar sesión.
 * La clase también implementa los métodos correspondientes para comunicarse con las clases Galería y CasaDeSubasta.
 */
public class MenuAdministrador extends MenuEmpleado {
	
	public static final int LISTA_PAGOS = 0;
	public static final int LISTA_NUEVOS_COMPRADORES = 1;
	public static final int LISTA_SOLICITUDES_TOPE = 2;
	
	public static Map<Integer, List> notificacionesAdmin;

	protected MenuAdministrador(MenuPrincipal menuPrincipal) {
		super(menuPrincipal);
		
		if (notificacionesAdmin.isEmpty()){
			
			/**
			 * La lista de pagos se utiliza para aprobar la entrega de piezas ya pagas.
			 * La lista de compradores se utiliza para verificar nuevos compradores (se les debe asignar un tope en este proceso).
			 * La lista de ofertas se utiliza para aprobar nuevos topes a los compradores.
			 */
			LinkedList<Pago> listaPagos = new LinkedList<Pago>();
			LinkedList<Cliente> listaCompradores = new LinkedList<Cliente>();
			LinkedList<Oferta> listaSolicitudesTope = new LinkedList<Oferta>();
			
			notificacionesAdmin.put(LISTA_PAGOS, listaPagos);
			notificacionesAdmin.put(LISTA_NUEVOS_COMPRADORES, listaCompradores);
			notificacionesAdmin.put(LISTA_SOLICITUDES_TOPE, listaSolicitudesTope);
		}
		
		this.mostrarMenuAdministrador();
	}
	
	
	/**
	 * Esta función muestra el menú del administrador y ejecuta las funciones correspondientes según la respuesta del usuario.
	 */
	private void mostrarMenuAdministrador() {
		
		String[] opciones = new String[12];
		
		opciones[0] = "Confirmar nueva pieza por consignación";
		opciones[1] = "Verificar nuevo comprador";
		opciones[2] = "Confirmar entrega de pieza pagada";
		opciones[3] = "Ampliar tope de comprador";
		opciones[4] = "Realizar devolución de pieza";
		opciones[5] = "Agregar nuevo empleado";
		opciones[6] = "Ver historia pieza";
		opciones[7] = "Ver historia artista";
		opciones[8] = "Ver historia cliente";
		opciones[9] = "Exhibir pieza";
		opciones[10] = "Guardar pieza en bodega";
		opciones[11] = "Cerrar Sesión";
		
		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);
		
		if (opcionEscogida == 1) {
			this.confirmarNuevaPieza();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 2) {
			this.verificarComprador();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 3) {
			this.confirmarEntrega();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 4) {
			this.ampliarTope();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 5) {
			this.realizarDevolución();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 6) {
			this.agregarEmpleado();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 7) {
			this.verHistoriaPieza();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 8) {
			this.verHistoriaArtista();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 9) {
			String loginCliente = this.pedirCadenaAlUsuario("Por favor ingrese el login del cliente que desea consultar: ");
			
			if (menuPrincipal.galeria.existeCliente(loginCliente)) verHistorialCliente(loginCliente);
			else System.out.println("No existe ningún cliente con el login seleccionado.");
			
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 10) {
			this.exhibirPieza();
			this.mostrarMenuAdministrador();
		}
		
		if (opcionEscogida == 11) {
			this.guardarPiezaEnBodega();
			this.mostrarMenuAdministrador();
		}
		
		else this.cerrarSesion(menuPrincipal);
	}
	
	/**
	 * Esta función verifica un cliente para ser comprador.
	 * Se le debe preguntar al administrador el tope de compras que se le asignará al nuevo comprador.
	 */
	protected void verificarComprador() {
		//TODO
	}
	
	
	/**
	 * Esta función agrega un nuevo empleado.
	 * Se le debe preguntar al administrador el login, password, número de contacto y tipo de empleado que se agregará.
	 * Se debe verificar que el login sea único (De esto se debe encargar la clase galería).
	 */
	protected void agregarEmpleado() {
		//TODO
	}
	
	
	/**
	 * Esta función amplía el tope de un comprador.
	 * Se le debe preguntar al administrador a qué cantidad ampliará el tope.
	 * Se le debe mostrar al administrador el tope mínimo que solicita el usuario (cantidadMinimaSolicitada).
	 */
	protected void ampliarTope() {
		//TODO
	}
	
	
	/**
	 * Esta función le solicita confirmación al administrador para agregar una nueva pieza.
	 * La función regresa true si el administrador aprobó el ingreso de la pieza.
	 * Se retorna false en caso contrario.
	 */
	protected void confirmarNuevaPieza() {
		//TODO
	}
	
	
	
	/**
	 * Esta función le solicita al administrador que confirme la entrega de una pieza (para este punto un cajero ya confirmó el pago).
	 * Si el administrador confirma, procede el proceso de entrega con normalidad (se llama a entregarPieza).
	 * Si el administrador no autoriza, el pago permanece en la cola
	 */
	protected void confirmarEntrega() {
		//TODO
	}
	
	
	/**
	 * Esta función le permite al administrador devolver una pieza.
	 */
	protected void realizarDevolución() {
		//TODO
	}
	
	
	
	
	
	/**
	 * Métodos Auxiliares
	 */
	
	
	/**
	 * Esta función entrega una pieza a un propietario.
	 * Se debe verificar si el "nuevo propietario" es igual al propietario actual, pues en este caso se trata de una devolución (no una venta).
	 * Esta función es llamada por confirmarVenta y realizarDevolución
	 * @param pieza
	 * @param propietario
	 */
	private void entregarPieza(Pieza pieza, Cliente propietario) {
		//TODO
	}
	
	
	/**
	 * Esta función se encarga de mostrar la información de una pieza.
	 */
	private void mostrarPieza(Pieza pieza) {
		//TODO
	}
}
