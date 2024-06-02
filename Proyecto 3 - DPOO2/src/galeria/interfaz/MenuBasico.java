package galeria.interfaz;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import galeria.modelo.inventario.Galeria;
import galeria.persistencia.CentralPersistencia;

/**
 * Esta es una clase abstracta que implementa métodos útiles para todos los
 * menús de la aplicación.
 */
public abstract class MenuBasico {

    public static final String MENSAJE_PREDETERMINADO = "Escoja la opción deseada";

    /**
     * Le pide al usuario que ingrese una cadena de caracteres
     * 
     * @param mensaje El mensaje con el que se solicita la información
     * @return La cadena introducida por el usuario
     */
    protected String pedirCadenaAlUsuario(String mensaje) {
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setResizable(true);
        String input = JOptionPane.showInputDialog(dialog, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE);
        dialog.dispose();
        return input;
    }

    /**
     * Le pide confirmación al usuario, indicándole que debe responder 'si' o 'no'.
     * 
     * @param mensaje El mensaje con el que se solicita la información
     * @return Retorna true únicamente si el usuario responde 'sí', 'si' o 'si',
     *         independientemente de las minúsculas y las mayúsculas. Retorna false
     *         en cualquier otro caso.
     */
    protected boolean pedirConfirmacionAlUsuario(String mensaje) {
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setResizable(true);
        int respuesta = JOptionPane.showConfirmDialog(dialog, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
        dialog.dispose();
        return respuesta == JOptionPane.YES_OPTION;
    }
    /**
     * Le pide al usuario que ingrese un número que no puede tener cifras decimales
     * 
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected int pedirEnteroAlUsuario(String mensaje) {
        while (true) {
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            dialog.setResizable(true);
            String input = JOptionPane.showInputDialog(dialog, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE);
            dialog.dispose();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor digitado no es un entero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Le pide al usuario que ingrese un número que puede tener cifras decimales
     * 
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected double pedirDoubleAlUsuario(String mensaje) {
        while (true) {
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            dialog.setResizable(true);
            String input = JOptionPane.showInputDialog(dialog, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE);
            dialog.dispose();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor digitado no es un número decimal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    protected long pedirLongAlUsuario(String mensaje) {
        while (true) {
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            dialog.setResizable(true);
            String input = JOptionPane.showInputDialog(dialog, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE);
            dialog.dispose();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor digitado no es un número entero largo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Le pide al usuario que seleccione una de las opciones que se le presenten
     * 
     * @param coleccionOpciones
     * @return Retorna la opción seleccionada (el valor, no su posición).
     */
    protected String pedirOpcionAlUsuario(Collection<? extends Object> coleccionOpciones) {
        String[] opciones = new String[coleccionOpciones.size()];
        int pos = 0;
        for (Iterator<? extends Object> iterator = coleccionOpciones.iterator(); iterator.hasNext(); pos++) {
            opciones[pos] = iterator.next().toString();
        }

        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setResizable(true);
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(dialog, "Seleccione una opción:", "Opciones",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        dialog.dispose();

        if (opcionSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna opción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return pedirOpcionAlUsuario(coleccionOpciones);
        }
        return opcionSeleccionada;
    }


    /**
     * Muestra un menú y le pide al usuario que seleccione una opción
     * 
     * @param nombreMenu El nombre del menu
     * @param opciones   Las opciones que se le presentan al usuario
     * @return El número de la opción seleccionada por el usuario, contando desde 1
     */
    protected int mostrarMenu(String nombreMenu, String[] opciones, String textoOpciones) {
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setResizable(true);
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(dialog, textoOpciones, nombreMenu,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        dialog.dispose();

        if (opcionSeleccionada == null) {
        	return 0;
        }

        for (int i = 0; i < opciones.length; i++) {
            if (opcionSeleccionada.equals(opciones[i])) {
                return i + 1;
            }
        }

        JOptionPane.showMessageDialog(null, "Esa no es una opción válida", "Error", JOptionPane.ERROR_MESSAGE);
        return mostrarMenu(nombreMenu, opciones, textoOpciones);
    }


    /**
     * Al cerrar la aplicación, se guarda la información del programa en un archivo
     * llamado nueva_galeria.json en la carpeta datos usando la clase Saver.
     * Posteriormente, se detiene la ejecución del programa.
     * 
     * @throws Exception
     */
    protected void cerrarAplicacion(Galeria galeria) throws Exception {
        CentralPersistencia persistencia = new CentralPersistencia();
        persistencia.guardarPrograma(galeria);

        JOptionPane.showMessageDialog(null, "Saliendo...\nGuardando información...");
        System.exit(0);
    }
}
