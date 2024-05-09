/**
 * 
 */
package galeria.interfaz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.usuarios.Cliente;
import galeria.modelo.inventario.Escultura;
import galeria.modelo.inventario.Fotografia;
import galeria.modelo.inventario.Impresion;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.inventario.Video;

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

		opciones[0] = "Registrar nueva pieza";
		opciones[1] = "Consignar nueva pieza"; //La pieza ya debe estar registrada y no puede estar en posesión (esto querría decir que ya está consignada)
		opciones[2] = "Comprar pieza por venta directa";
		opciones[3] = "Realizar oferta en subasta";
		opciones[4] = "Aplicar para verificación de comprador";
		opciones[5] = "Solicitar extensión de tope de compras";
		opciones[6] = "Ver historial de compras";
		opciones[7] = "Ver historia de pieza";
		opciones[8] = "Ver historia de artista";
		opciones[9] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		
		if (opcionEscogida == 1) {
			this.registrarNuevaPieza();
			this.mostrarMenuCliente();
		}
		if (opcionEscogida == 2) {
			this.consignarNuevaPieza();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 3) {
			this.comprarPiezaVentaDirecta();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 4) {
			this.realizarOfertaSubasta();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 5) {
			this.aplicarParaVerificacionComprador();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 6) {
			this.solicitarExtensionTopeDeCompras();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 7) {
			this.verHistorialCliente(esteCliente.getLogin());
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 8) {
			this.verHistoriaPieza();
			this.mostrarMenuCliente();
		}

		if (opcionEscogida == 9) {
			this.verHistoriaArtista();
			this.mostrarMenuCliente();
		}

		else
			this.cerrarSesion(menuPrincipal);
	}
	
	private void registrarNuevaPieza() {
		//TODO
	}
	
	private void consignarNuevaPieza() {
		List<String> piezasCliente = galeria.getPiezasActuales(esteCliente.getLogin());
		List<Pieza> piezasPorConsignar = new LinkedList<Pieza>();
		
		Iterator<String> it = piezasCliente.iterator();
		
		if ( piezasCliente.isEmpty() ) {
			System.out.println("No tiene piezas registradas");
		}
		else {
			while ( it.hasNext() ) {
				String tituloPieza = it.next();
				Pieza pieza = galeria.getPieza(tituloPieza);
				if ( !pieza.isEnPosesion() ) {
					piezasPorConsignar.add(pieza);
				}
			}
			if ( piezasPorConsignar.isEmpty() ) {
				System.out.println("Todas sus piezas registradas ya están consignadas.");
			}
			else {
				int numPiezas = piezasPorConsignar.size();
				String[] opciones = new String[numPiezas+1];
				
				Iterator<Pieza> itP = piezasPorConsignar.iterator();
				int i = 0;
				
				while ( itP.hasNext() ) {
					opciones[i] = itP.next().getTitulo();
					i++;
				}
				opciones[numPiezas] = "Cancelar";
				int opcionSeleccionada = mostrarMenu("Piezas disponibles para consignación", opciones,
						MENSAJE_PREDETERMINADO);
				if (opcionSeleccionada == numPiezas + 1) {
				} 
				else {
					Pieza piezaSeleccionada = piezasPorConsignar.get(opcionSeleccionada - 1);
					String fechaTerminoConsignacion = pedirCadenaAlUsuario("Por favor ingrese la fecha hasta la cual desea consignar (formato: DD/MM/AAAA)");
					long precioVentaDirecta = -1;
					boolean ventaDirecta = pedirConfirmacionAlUsuario("¿Desea que la pieza esté disponible para venta directa?");
					if ( ventaDirecta ) {
						precioVentaDirecta = pedirLongAlUsuario("Por favor ingrese el precio para venta directa");
					}
					long precioInicioSubasta = pedirLongAlUsuario("Por favor ingrese el de inicio para una subasta");
					long precioMinimoSubasta = pedirLongAlUsuario("Por favor ingrese el precio mínimo para cerrar una subasta");
					
					Pieza piezaPorConsignar = null;
					
					if ( piezaSeleccionada.getTipo() == Pieza.PINTURA ) {
						piezaPorConsignar = new Pintura((Pintura) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.IMPRESION ) {
						piezaPorConsignar = new Impresion((Impresion) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.ESCULTURA ) {
						piezaPorConsignar = new Escultura((Escultura) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.FOTOGRAFIA ) {
						piezaPorConsignar = new Fotografia((Fotografia) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.VIDEO ) {
						piezaPorConsignar = new Video((Video) piezaSeleccionada);
					}
					
					piezaPorConsignar.setFechaTerminoConsignacion(fechaTerminoConsignacion);
					piezaPorConsignar.setPrecioVentaDirecta(precioVentaDirecta);
					piezaPorConsignar.setPrecioInicioSubasta(precioInicioSubasta);
					piezaPorConsignar.setPrecioMinimoSubasta(precioMinimoSubasta);
					
					MenuAdministrador.listaPiezasPorIngresar.add(piezaPorConsignar);
					
				}
				mostrarMenuCliente();
			}
		}
		
	}

	private void comprarPiezaVentaDirecta() {
	    // Obtener la lista de piezas disponibles para venta directa
	    List<Pieza> piezasDisponibles = galeria.getPiezasDisponiblesVentaDirecta();

	    // Verificar si hay piezas disponibles
	    if (piezasDisponibles.isEmpty()) {
	        System.out.println("No hay piezas disponibles para venta directa en este momento.");
	        return;
	    }

	    // Mostrar las piezas disponibles para venta directa al cliente
	    System.out.println("Piezas disponibles para venta directa:");
	    for (int i = 0; i < piezasDisponibles.size(); i++) {
	        System.out.println((i + 1) + ". " + piezasDisponibles.get(i));
	    }

	    // Solicitar al cliente que elija una pieza para comprar
	    int opcionSeleccionada = pedirEnteroAlUsuario("Seleccione el número de la pieza que desea comprar:");

	    // Verificar la validez de la opción seleccionada
	    if (opcionSeleccionada < 1 || opcionSeleccionada > piezasDisponibles.size()) {
	        System.out.println("Opción inválida. Por favor seleccione un número de pieza válido.");
	        return;
	    }

	    // Obtener la pieza seleccionada por el cliente
	    Pieza piezaSeleccionada = piezasDisponibles.get(opcionSeleccionada - 1);

	    // Realizar la compra de la pieza
	    boolean compraExitosa = galeria.comprarPiezaVentaDirecta(piezaSeleccionada, esteCliente);

	    // Verificar si la compra fue exitosa
	    if (compraExitosa) {
	        System.out.println("¡La compra se realizó con éxito!");
	    } else {
	        System.out.println("Lo siento, no se pudo completar la compra en este momento.");
	    }
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
