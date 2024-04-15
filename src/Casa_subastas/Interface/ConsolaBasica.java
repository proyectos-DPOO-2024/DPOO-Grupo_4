package Casa_subastas.Interface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ConsolaBasica {

    protected String pedirUsuario() {
        System.out.print("Usuario: ");
        return leerEntradaUsuario();
    }

    protected String pedirContraseña() {
        System.out.print("Contraseña: ");
        return leerEntradaUsuario();
    }

    private String leerEntradaUsuario() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola");
            return null;
        }
    }

    protected String pedirCadenaAlUsuario(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola");
            return null;
        }
    }

    protected int mostrarMenu(String nombreMenu, String[] opciones) {
        System.out.println("\n---------------------");
        System.out.println(nombreMenu);
        System.out.println("---------------------");

        for (int i = 1; i <= opciones.length; i++) {
            System.out.println(" " + i + ". " + opciones[i - 1]);
        }
        String opcion = pedirCadenaAlUsuario("Escoja la opción deseada");
        try {
            int opcionSeleccionada = Integer.parseInt(opcion);
            if (opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length)
                return opcionSeleccionada;
            else {
                System.out.println("Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length);
                return mostrarMenu(nombreMenu, opciones);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Esa no es una opción válida. Digite solamente números.");
            return mostrarMenu(nombreMenu, opciones);
        }
    }

}
