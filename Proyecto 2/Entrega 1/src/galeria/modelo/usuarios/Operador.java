/**
 * 
 */
package galeria.modelo.usuarios;

/**
 * 
 */
public class Operador extends Empleado
{

	public Operador(String login, String password, int cellphone, String nombre)
	{
		super(login, password, cellphone, nombre, Usuario.OPERADOR);
	}
}
