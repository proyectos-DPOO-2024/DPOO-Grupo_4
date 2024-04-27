package galeria.modelo.usuarios;

public abstract class Empleado extends Usuario {

	private String nombre;
	
	public Empleado (String login, String password, int cellphone, String nombre, int rol) {
		super(login, password, cellphone, rol);
		
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
}
