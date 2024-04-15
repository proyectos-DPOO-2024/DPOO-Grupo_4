package Casa_subastas.Interface;
import java.util.HashMap;
import java.util.Map;

import Casa_subastas.Persistencias.Loader;
import Casa_subastas.modelo.Inventario.Galeria;

public class MenuPrincipal extends ConsolaBasica {
    private final String[] opcionesMenuPrincipal = new String[]{"Iniciar sesión", "Salir"};
    private Map<String, String> usuariosRegistrados;

    public MenuPrincipal() {
        usuariosRegistrados = new HashMap<>();
        usuariosRegistrados.put("usuario1", "contraseña1");
        usuariosRegistrados.put("usuario2", "contraseña2");
        usuariosRegistrados.put("usuario3", "contraseña3");
    }
    
    protected void mostrarMenuPrincipal(Galeria galeria) {
        int opcionSeleccionada = mostrarMenu("Menú principal", opcionesMenuPrincipal);
        switch (opcionSeleccionada) {
            case 1:
                iniciarSesion(galeria);
                break;
            case 2:
                System.out.println("Saliendo ...");
                System.exit(0);
                break;
        }
        mostrarMenuPrincipal(galeria);
    }

    private void iniciarSesion(Galeria galeria) {
        System.out.println("Iniciar sesión");
        String usuario = pedirUsuario();
        String contraseña = pedirContraseña();
        if (usuariosRegistrados.containsKey(usuario) && usuariosRegistrados.get(usuario).equals(contraseña)) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario + "!");
            mostrarMenuSesion(galeria);
        } else {
            System.out.println("Credenciales incorrectas. Inténtalo de nuevo.");
        }
    }

<<<<<<< Updated upstream
    private void mostrarMenuSesion() {
        String[] opcionesMenuSesion = new String[]{"Cliente", "Cajero", "Operador", "Administrador", "Salir"};
=======
    private void mostrarMenuSesion(Galeria galeria) {
        String[] opcionesMenuSesion = new String[]{"Cliente", "Empleado", "Salir"};
>>>>>>> Stashed changes
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
                menuAdministrador.mostrarMenuAdministrador();
                break;
            case 5:
                System.out.println("Saliendo ...");
                System.exit(0);
                break;
        }
    }
    
    private Galeria cargar() throws Exception{
        
    	
        String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de la galeria" );
        Loader cargador = new Loader();

        return cargador.cargarGaleria("./datos/" + archivo);
    }

    public static void main(String[] args) throws Exception {
        MenuPrincipal c = new MenuPrincipal();
        c.mostrarMenuPrincipal(c.cargar());
    }
}
