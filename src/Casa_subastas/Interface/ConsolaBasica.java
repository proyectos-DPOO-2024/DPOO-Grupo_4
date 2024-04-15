package Casa_subastas.Interface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

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

    protected abstract void mostrarMenuPrincipal();
    
    protected int pedirEnteroAlUsuario(String mensaje) {
        int valorResultado = Integer.MIN_VALUE;
        while (valorResultado == Integer.MIN_VALUE) {
            try {
                System.out.print(mensaje);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine();
                int numero = Integer.parseInt(input);
                valorResultado = numero;
            } catch (NumberFormatException nfe) {
                System.out.println("El valor digitado no es un entero");
            } catch (IOException e) {
                System.out.println("Error leyendo de la consola");
            }
        }
        return valorResultado;
    }

    /**
     * Le pide al usuario que seleccione una de las opciones que se le presenten
     * @param coleccionOpciones La colección de opciones disponibles
     * @return La opción seleccionada por el usuario
     */
    protected String pedirOpcionAlUsuario(String mensaje, String[] opciones) {
        System.out.println(mensaje);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        int opcionSeleccionada = pedirEnteroAlUsuario("Seleccione una opción: ");
        while (opcionSeleccionada < 1 || opcionSeleccionada > opciones.length) {
            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            opcionSeleccionada = pedirEnteroAlUsuario("Seleccione una opción: ");
        }
        return opciones[opcionSeleccionada - 1];
    }
}
