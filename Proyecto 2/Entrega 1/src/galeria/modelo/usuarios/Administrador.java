/**
 * 
 */
package galeria.modelo.usuarios;

/**
 * 
 */
public class Administrador extends Empleado
{

	public Administrador(String login, String password, int cellphone, String nombre)
	{
		super(login, password, cellphone, nombre, Usuario.ADMINISTRADOR);
	}
}
