/**
 * 
 */
package galeria.interfaz;

import galeria.modelo.inventario.Pieza;

/**
 * 
 */
public abstract class MenuEmpleado extends MenuUsuario
{

	protected MenuEmpleado(MenuPrincipal menuPrincipal)
	{
		super(menuPrincipal);
	}

	public MenuEmpleado(MenuPrincipal1 menuPrincipal1)
	{
		super(menuPrincipal1);

	}

	/**
	 * Este método pasa una pieza (que está siendo exhibida) a la bodega
	 */
	protected void guardarPiezaEnBodega() {

		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza que desea guardar en bodega: ");

		Pieza pieza = menuPrincipal.galeria.getPieza(nombrePieza);

		if (!pieza.isEnBodega()) {
			pieza.guardarEnBodega();
		} else {
			System.out.println("La pieza seleccionada ya está en bodega");
		}
	}

	/**
	 * Este método pasa una pieza (que está en bodega) a la exhibición.
	 */
	protected void exhibirPieza() {

		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza que desea exhibir: ");

		Pieza pieza = menuPrincipal.galeria.getPieza(nombrePieza);

		if (pieza.isEnBodega()) {
			pieza.exhibir();
		} else {
			System.out.println("La pieza seleccionada ya está siendo exhibida");
		}
	}

}
