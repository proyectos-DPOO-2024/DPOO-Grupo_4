/**
 * 
 */
package galeria.modelo.usuarios;

/**
 * 
 */
public class Cajero extends Empleado {

	public Cajero (String login, String password, int cellphone, String nombre) {
		super(login, password, cellphone, nombre, Usuario.CAJERO);
	}
}
