package Casa_subastas.Interface;
import java.util.HashMap;
import java.util.Map;

import Casa_subastas.Persistencias.Loader;
import Casa_subastas.Persistencias.Saver;
import Casa_subastas.modelo.Inventario.Galeria;

public class MenuPrincipal extends ConsolaBasica {
	
    private final String[] opcionesMenuPrincipal = new String[]{"Iniciar sesión", "Salir"};
    private Map<String, String> usuariosRegistrados;
    
    public static Galeria galeria;

    public MenuPrincipal() {
        usuariosRegistrados = new HashMap<>();
        usuariosRegistrados.put("usuario1", "contraseña1");
        usuariosRegistrados.put("usuario2", "contraseña2");
        usuariosRegistrados.put("usuario3", "contraseña3");
    }
    
    protected void mostrarMenuPrincipal() throws Exception {
        int opcionSeleccionada = mostrarMenu("Menú principal", opcionesMenuPrincipal);
        try {
			switch (opcionSeleccionada) {
			    case 1:
			        iniciarSesion();
			        break;
			    case 2:
			        System.out.println("Saliendo ...");
			        this.guardar();
			        System.exit(0);
			        break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        mostrarMenuPrincipal();
    }

    private void iniciarSesion() throws Exception {
        System.out.println("Iniciar sesión");
        String usuario = pedirUsuario();
        String contraseña = pedirContraseña();
        if (usuariosRegistrados.containsKey(usuario) && usuariosRegistrados.get(usuario).equals(contraseña)) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario + "!");
            mostrarMenuSesion();
        } else {
            System.out.println("Credenciales incorrectas. Inténtalo de nuevo.");
        }
    }

    private void mostrarMenuSesion() throws Exception {
        String[] opcionesMenuSesion = new String[]{"Cliente", "Cajero", "Operador", "Administrador", "Salir"};
        int opcionSeleccionada = mostrarMenu("¿Cómo desea iniciar sesión?", opcionesMenuSesion);
        switch (opcionSeleccionada) {
            case 1:
                System.out.println("Iniciando sesión como cliente...");
                MenuCliente menuCliente = new MenuCliente();
                menuCliente.mostrarMenuCliente();
                break;
            case 2:              
            	System.out.println("Iniciando sesión como cajero...");
                MenuCajero menuCajero = new MenuCajero();
                menuCajero.mostrarMenuCajero();
                break;
            case 3:      
                System.out.println("Iniciando sesión como operador...");
                MenuOperador menuOperador = new MenuOperador();
                menuOperador.mostrarMenuOperador();
                break;
            case 4:           
                System.out.println("Iniciando sesión como administrador...");
                MenuAdministrador menuAdministrador = new MenuAdministrador();
                menuAdministrador.mostrarMenuAdministrador(null);
                break;
            case 5:
                System.out.println("Saliendo ...");
                this.guardar();
                System.exit(0);
                break;
        }
    }
    
    private Galeria cargar() throws Exception{
        
    	
        //String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de la galeria (sin la extensión). Debe estar guardado en la carpeta datos " );
    	String archivo = "galeria";
        Loader cargador = new Loader();

        return cargador.cargarGaleria("./datos/" + archivo + ".json");
    }
    
    private void guardar() throws Exception{
        
        Saver salvador = new Saver();

        salvador.salvarGaleria("./datos/" + "nuevaGaleria" + ".json", galeria);
    }

    public static void main(String[] args) throws Exception {
        MenuPrincipal c = new MenuPrincipal();
        galeria = c.cargar();
        c.mostrarMenuPrincipal();
    }
}
