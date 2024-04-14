package Casa_subastas.Interface;
import java.util.HashMap;
import java.util.Map;

public class MenuPrincipal extends ConsolaBasica {
    private final String[] opcionesMenuPrincipal = new String[]{"Iniciar sesión", "Salir"};
    private Map<String, String> usuariosRegistrados;

    public MenuPrincipal() {
        usuariosRegistrados = new HashMap<>();
        // Simulación de usuarios registrados
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
        String[] opcionesMenuSesion = new String[]{"Cliente", "Empleado", "Salir"};
        int opcionSeleccionada = mostrarMenu("¿Cómo desea iniciar sesión?", opcionesMenuSesion);
        switch (opcionSeleccionada) {
            case 1:
                // Lógica para iniciar sesión como cliente
                System.out.println("Iniciando sesión como cliente...");
                break;
            case 2:
                // Lógica para iniciar sesión como empleado
                System.out.println("Iniciando sesión como empleado...");
                break;
            case 3:
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
