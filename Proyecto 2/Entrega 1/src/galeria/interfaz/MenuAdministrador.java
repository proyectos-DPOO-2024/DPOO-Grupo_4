/**
 * 
 */
package galeria.interfaz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.SolicitudTope;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;
import galeria.modelo.usuarios.Usuario;

/**
 * Este es el menú que verá un administrador al iniciar sesión. La clase también
 * implementa los métodos correspondientes para comunicarse con las clases
 * Galería y CasaDeSubasta.
 */
public class MenuAdministrador extends MenuEmpleado
{

	/**
	 * La lista de pagos se utiliza para aprobar la entrega de piezas ya pagas. La
	 * lista de piezas por ingresar se utiliza para guardar las propuestas de
	 * consignación hechas por propietarios. La lista de compradores se utiliza para
	 * verificar nuevos compradores (se les debe asignar un tope en este proceso).
	 * La lista de solicitudes guarda los nombres de los clientes que solicitaron
	 * aprobación nuevos topes a los compradores.
	 */
	public static List<Pago> listaPagos = new LinkedList<Pago>();
	public static List<Pieza> listaPiezasPorIngresar = new LinkedList<Pieza>();
	public static List<String> listaCompradores = new LinkedList<String>();
	public static List<SolicitudTope> listaSolicitudesTope = new LinkedList<SolicitudTope>();

	protected MenuAdministrador(MenuPrincipal menuPrincipal)
	{
		super(menuPrincipal);

		mostrarMenuAdministrador();
	}

	/**
	 * Esta función muestra el menú del administrador y ejecuta las funciones
	 * correspondientes según la respuesta del usuario.
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

		int opcionEscogida = mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		if (opcionEscogida == 1) {
			confirmarNuevaPieza();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 2) {
			verificarComprador();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 3) {
			confirmarEntrega();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 4) {
			ampliarTope();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 5) {
			realizarDevolución();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 6) {
			agregarEmpleado();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 7) {
			verHistoriaPieza();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 8) {
			verHistoriaArtista();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 9) {
			String loginCliente = pedirCadenaAlUsuario(
					"Por favor ingrese el nombre de usuario del cliente que desea consultar");

			if (galeria.existeCliente(loginCliente))
				verHistorialCliente(loginCliente);
			else
				System.out.println("No existe ningún cliente con el login seleccionado.");

			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 10) {
			exhibirPieza();
			mostrarMenuAdministrador();
		}

		if (opcionEscogida == 11) {
			guardarPiezaEnBodega();
			mostrarMenuAdministrador();
		}

		else
			cerrarSesion(menuPrincipal);
	}

	/**
	 * Esta función verifica un cliente para ser comprador. Se le debe preguntar al
	 * administrador el tope de compras que se le asignará al nuevo comprador.
	 */
	protected void verificarComprador() {
		if (listaCompradores.isEmpty()) {
			System.out.println("No hay solicitudes de verificación pendientes.");
			boolean busquedaManual = pedirConfirmacionAlUsuario("¿Desea verificar un cliente manualmente?");

			if (busquedaManual) {
				String login = pedirCadenaAlUsuario(
						"Por favor ingrese el nombre de usuario del cliente que desea verificar");
				boolean existe = galeria.existeCliente(login);
				if (!existe)
					System.out.println("El nombre de usuario ingresado no corresponde a ningún cliente registrado");
				else {
					Cliente cliente = galeria.getCliente(login);
					if (cliente.isVerificado())
						System.out.println("El cliente ingresado ya está verificado");
					else {
						long tope = pedirLongAlUsuario("Por favor ingrese el tope de compras que desea asignar");
						galeria.verificarNuevoComprador(cliente, tope);
					}
				}
			}
		} else {
			int numClientes = listaCompradores.size();
			String[] opciones = new String[numClientes + 1];

			Iterator<String> it = listaCompradores.iterator();
			for (int i = 0; i < numClientes && it.hasNext(); i++) {
				opciones[i] = it.next();
			}
			opciones[numClientes] = "Cancelar";
			int opcionSeleccionada = mostrarMenu("Solicitudes de verificación pendientes", opciones,
					MENSAJE_PREDETERMINADO);
			if (opcionSeleccionada == numClientes + 1) {
			} else {
				Cliente clienteSeleccionado = galeria.getCliente(listaCompradores.get(opcionSeleccionada - 1));
				long tope = pedirLongAlUsuario("Por favor ingrese el tope de compras que desea asignar");
				galeria.verificarNuevoComprador(clienteSeleccionado, tope);
			}
		}
		mostrarMenuAdministrador();
	}

	/**
	 * Esta función agrega un nuevo empleado. Se le debe preguntar al administrador
	 * el login, password, número de contacto y tipo de empleado que se agregará. Se
	 * debe verificar que el login sea único (De esto se debe encargar la clase
	 * galería).
	 */
	protected void agregarEmpleado() {

		String login = crearNuevoLogin();
		String password = pedirCadenaAlUsuario("Por favor ingrese la contraseña del nuevo empleado");
		int cellphone = pedirEnteroAlUsuario("Por favor ingrese el numero telefónico del nuevo empleado");
		String nombre = pedirCadenaAlUsuario("Por favor ingrese el nombre del nuevo empleado");

		String[] opciones = new String[3];
		opciones[Usuario.ADMINISTRADOR - 1] = "Administrador";
		opciones[Usuario.CAJERO - 1] = "Cajero";
		opciones[Usuario.OPERADOR - 1] = "Operador";

		int rolSeleccionado = mostrarMenu("Seleccionar rol", opciones, "Escoja el rol deseado para el nuevo empleado");

		galeria.agregarNuevoEmpleado(login, password, cellphone, nombre, rolSeleccionado);
	}

	/**
	 * Esta función amplía el tope de un comprador. Se le debe preguntar al
	 * administrador a qué cantidad ampliará el tope. Se le debe mostrar al
	 * administrador el tope mínimo que solicita el usuario
	 * (cantidadMinimaSolicitada).
	 */
	protected void ampliarTope() {

		if (listaSolicitudesTope.isEmpty()) {
			System.out.println("No hay solicitudes de extensión de tope de compras pendientes.");
			boolean busquedaManual = pedirConfirmacionAlUsuario("¿Desea extender el tope de un comprador manualmente?");

			if (busquedaManual) {
				String login = pedirCadenaAlUsuario(
						"Por favor ingrese el nombre de usuario del comprador al que desea extender su tope");
				boolean existe = galeria.existeCliente(login);
				if (!existe)
					System.out.println("El nombre de usuario ingresado no corresponde a ningún cliente registrado");
				else {
					Cliente cliente = galeria.getCliente(login);
					if (!cliente.isVerificado())
						System.out.println("El cliente ingresado no está verificado aún");
					else {

						long tope = -1;
						while (tope < 0) {
							tope = pedirLongAlUsuario(
									"Por favor ingrese el nuevo tope de compras que desea asignar (este debe ser mayor al tope actual: "
											+ Long.toString(cliente.getTopeCompras()) + ")");
							if (tope < cliente.getTopeCompras()) {
								System.out.println("El tope ingresado es menor al tope actual");
								tope = -1;
							}
						}
						galeria.asignarTope(cliente, tope);
					}
				}
			}
		} else {
			int numSolicitudes = listaSolicitudesTope.size();
			String[] opciones = new String[numSolicitudes + 1];

			Iterator<SolicitudTope> it = listaSolicitudesTope.iterator();
			for (int i = 0; i < numSolicitudes && it.hasNext(); i++) {
				SolicitudTope solicitud = it.next();
				String loginCliente = solicitud.getLoginComprador();
				opciones[i] = "Cliente #" + Integer.toString(i + 1) + ": " + loginCliente + ". Tope solicitado: "
						+ Long.toString(solicitud.getTopeSolicitado());
			}
			opciones[numSolicitudes] = "Cancelar";
			int opcionSeleccionada = mostrarMenu("Clientes que solicitan extensión de tope", opciones,
					"Seleccione un cliente para ampliar su tope de compras");
			if (opcionSeleccionada == numSolicitudes + 1) {
			} else {
				SolicitudTope solicitudSeleccionada = listaSolicitudesTope.get(opcionSeleccionada - 1);
				Cliente compradorSeleccionado = galeria.getCliente(solicitudSeleccionada.getLoginComprador());
				long tope = Integer.MIN_VALUE;
				long topeMinimo = solicitudSeleccionada.getTopeSolicitado();
				boolean salir = false;
				while (tope < 0 && !salir) {
					tope = pedirLongAlUsuario(
							"Por favor ingrese el nuevo tope de compras que desea asignar (este debe ser mayor o igual al valor solictado por el cliente: "
									+ Long.toString(topeMinimo) + "). Ingrese -1 para salir");
					if (tope == -1) {
						salir = true;
					} else if (tope < topeMinimo) {
						System.out.println("El tope ingresado es menor al valor de la oferta");
						tope = Integer.MIN_VALUE;
					}
				}
				if (tope >= topeMinimo) {
					galeria.asignarTope(compradorSeleccionado, tope);
					listaSolicitudesTope.remove(opcionSeleccionada - 1);
				}
			}
		}
		mostrarMenuAdministrador();
	}

	/**
	 * Esta función le solicita confirmación al administrador para consignar una nueva pieza.
	 */
	protected void confirmarNuevaPieza() {
		int numPiezasPorIngresar = listaPiezasPorIngresar.size();
		String[] opciones = new String[numPiezasPorIngresar + 1];

		Iterator<Pieza> it = listaPiezasPorIngresar.iterator();
		for (int i = 0; i < numPiezasPorIngresar && it.hasNext(); i++) {
			Pieza pieza = it.next();
			int tipo = pieza.getTipo();
			String tipoStr = null;

			if (tipo == Pieza.PINTURA)
				tipoStr = "Pintura";
			if (tipo == Pieza.IMPRESION)
				tipoStr = "Impresión";
			if (tipo == Pieza.ESCULTURA)
				tipoStr = "Escultura";
			if (tipo == Pieza.FOTOGRAFIA)
				tipoStr = "Fotografía";
			if (tipo == Pieza.VIDEO)
				tipoStr = "Video";

			opciones[i] = "Pieza #" + Integer.toString(i + 1) + ": Título: " + pieza.getTitulo() + ". Propietario: "
					+ pieza.getLoginPropietario() + tipoStr;
		}
		opciones[numPiezasPorIngresar] = "Cancelar";
		int opcionSeleccionada = mostrarMenu("Piezas ofrecidas para consignación", opciones,
				MenuBasico.MENSAJE_PREDETERMINADO);
		if (opcionSeleccionada == numPiezasPorIngresar + 1) {
		} else {
			Pieza piezaSeleccionada = listaPiezasPorIngresar.get(opcionSeleccionada - 1);
			galeria.consignarPiezaNueva(piezaSeleccionada);
		}
		mostrarMenuAdministrador();
	}

	/**
	 * Esta función le solicita al administrador que confirme la entrega de una
	 * pieza (para este punto un cajero ya confirmó el pago). Si el administrador
	 * confirma, procede el proceso de entrega con normalidad (se llama a
	 * entregarPieza). Si el administrador no autoriza, el pago permanece en la cola
	 */
	protected void confirmarEntrega() {
		int numPiezasPorEntregar = listaPagos.size();
		String[] opciones = new String[numPiezasPorEntregar + 1];

		Iterator<Pago> it = listaPagos.iterator();
		for (int i = 0; i < numPiezasPorEntregar && it.hasNext(); i++) {
			Pago pago = it.next();
			int tipo = pago.getPieza().getTipo();
			String tipoStr = null;

			if (tipo == Pieza.PINTURA)
				tipoStr = "Pintura";
			if (tipo == Pieza.IMPRESION)
				tipoStr = "Impresión";
			if (tipo == Pieza.ESCULTURA)
				tipoStr = "Escultura";
			if (tipo == Pieza.FOTOGRAFIA)
				tipoStr = "Fotografía";
			if (tipo == Pieza.VIDEO)
				tipoStr = "Video";

			opciones[i] = "Pieza #" + Integer.toString(i + 1) + ": Título: " + pago.getPieza().getTitulo()
					+ ". Propietario Actual: " + pago.getLoginVendedor() + ". Propietario Destino: "
					+ pago.getLoginComprador() + ". Tipo: " + tipoStr;
		}
		opciones[numPiezasPorEntregar] = "Cancelar";
		int opcionSeleccionada = mostrarMenu("Piezas listas para entregar", opciones,
				MenuBasico.MENSAJE_PREDETERMINADO);
		if (opcionSeleccionada == numPiezasPorEntregar + 1) {
		} else {
			Pago pagoSeleccionado = listaPagos.get(opcionSeleccionada - 1);
			galeria.entregarPieza(pagoSeleccionado.getPieza(), pagoSeleccionado.getLoginComprador());
		}
		mostrarMenuAdministrador();
	}

	/**
	 * Esta función le permite al administrador devolver una pieza. La pieza no se
	 * puede devolver si está siendo subastada, está bloqueada o no está en
	 * posesión.
	 */
	protected void realizarDevolución() {
		// TODO
	}

	/**
	 * Métodos Auxiliares
	 */

	/**
	 * Esta función entrega una pieza a un propietario. Se debe verificar si el
	 * "nuevo propietario" es igual al propietario actual, pues en este caso se
	 * trata de una devolución (no una venta). Esta función es llamada por
	 * confirmarVenta y realizarDevolución
	 * 
	 * @param pieza
	 * @param propietario
	 */
	private void entregarPieza(Pieza pieza, Cliente propietario) {
		// TODO
	}

}
