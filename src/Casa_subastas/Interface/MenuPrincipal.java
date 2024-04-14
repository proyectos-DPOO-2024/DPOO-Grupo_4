package Casa_subastas.Interface;
import java.util.HashMap;
import java.util.Map;

public class MenuPrincipal extends ConsolaBasica {
    private final String[] opcionesMenuPrincipal = new String[]{"Iniciar sesión", "Salir"};
    private Map<String, String> usuariosRegistrados;

    public MenuPrincipal() {
        usuariosRegistrados = new HashMap<>();
        usuariosRegistrados.put("usuario1", "contraseña1");
        usuariosRegistrados.put("usuario2", "contraseña2");
        usuariosRegistrados.put("usuario3", "contraseña3");
    }

    @Override
    protected void mostrarMenuPrincipal() {
        int opcionSeleccionada = mostrarMenu("Menú principal", opcionesMenuPrincipal);
        switch (opcionSeleccionada) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                System.out.println("Saliendo ...");
                System.exit(0);
                break;
        }
        mostrarMenuPrincipal();
    }

    private void iniciarSesion() {
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

    private void mostrarMenuSesion() {
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
                menuAdministrador.mostrarMenuAdministrador();
                break;
            case 5:
                System.out.println("Saliendo ...");
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        MenuPrincipal c = new MenuPrincipal();
        c.mostrarMenuPrincipal();
    }
}
