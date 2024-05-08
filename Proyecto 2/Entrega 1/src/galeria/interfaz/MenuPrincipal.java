/**
 * 
 */
package galeria.interfaz;

import galeria.modelo.inventario.Galeria;
import galeria.modelo.usuarios.Usuario;
import galeria.persistencia.CentralPersistencia;

/**
 * Este menú es el primero que ve el usuario. El objetivo de esta clase es
 * cargar el archivo con la información de la galería y permitirle al usuario
 * iniciar sesión.
 */
public class MenuPrincipal extends MenuBasico
{

	public static final int ADMINISTRADOR = 0;
	public static final int CAJERO = 1;
	public static final int OPERADOR = 2;
	public static final int CLIENTE = 3;

	protected Galeria galeria;

	/**
	 * @param args Inicia la aplicación cargando el archivo galeria.json en la
	 *             carpeta datos por medio de la clase Loader. Posteriormente,
	 *             muestra el menú principal.
	 */
	public static void main(String[] args) {
		
		String[] archivos = new String[2];
		
		archivos[0] = "./datos/galeria.json";
		archivos[1] = "./datos/centro_ventas.json";
		
		CentralPersistencia persistencia = new CentralPersistencia();
		Galeria galeria = persistencia.cargarPrograma(archivos);
		MenuPrincipal menuPrincipal = new MenuPrincipal(galeria);
	}

	protected MenuPrincipal(Galeria galeria)
	{
		this.galeria = galeria;

		mostrarMenuPrincipal();
	}

	/**
	 * Muestra el menú principal. El menú principal permite: 1. Iniciar sesión 2.
	 * Cerrar la aplicación
	 */
	protected void mostrarMenuPrincipal() {

		String[] opciones = new String[2];
		opciones[0] = "Iniciar Sesión";
		opciones[1] = "Cerrar Aplicación";

		int opcionEscogida = mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		if (opcionEscogida == 1)
			iniciarSesion();
		else
			cerrarAplicacion();
	}

	/**
	 * Esta función permite iniciar sesión. Se le pide al usuario su login y
	 * password La función se comunica con la galería para confirmar si el login y
	 * password coinciden con algún usuario registrado. Si sí coinciden, la galería
	 * debe informar a qué tipo de usuario es y se debe abrir el menú
	 * correspondiente. Si no coinciden, se le debe informar al usuario que el login
	 * y password no están registrados o son incorrectos.
	 */
	@SuppressWarnings("unused")
	private void iniciarSesion() {

		String login = pedirCadenaAlUsuario("Nombre de usuario");
		String password = pedirCadenaAlUsuario("Contraseña");

		int tipoUsuario = galeria.verificarLogin(login, password);

		if (tipoUsuario < 0)
			System.out.println("El nombre de usuario ingresado no existe");
		if (tipoUsuario == 0)
			System.out.println("La contraseña ingresada es incorrecta");

		if (tipoUsuario > 0) {
			if (tipoUsuario == Usuario.ADMINISTRADOR) {
				MenuAdministrador menuAdmin = new MenuAdministrador(this);
			}
			if (tipoUsuario == Usuario.OPERADOR) {
				MenuOperador menuOp = new MenuOperador(this);
			}
			if (tipoUsuario == Usuario.OPERADOR) {
				MenuCajero menuCaj = new MenuCajero(this);
			}
			if (tipoUsuario == Usuario.CLIENTE) {
				MenuCliente menuCli = new MenuCliente(this, login);
			}
		}

		mostrarMenuPrincipal();

	}

}
