package Casa_subastas.Interface;

import java.util.Arrays;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;

public class MenuAdministrador extends ConsolaBasica {

    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuAdministrador(Galeria galeria) {
        String[] opcionesMenuAdmin = new String[]{"Ingreso de pieza", "Asignar máximo", "Confirmar oferta", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú administrador", opcionesMenuAdmin);
        switch (opcionSeleccionada) {
            case 1:
                ingresoPieza(galeria);
                break;
            case 2:
                asignarMaximo(galeria);
                break;
            case 3:
                confirmarOferta(galeria);
                break;
            case 4:
                System.out.println("Saliendo del menú administrador...");
                break;
        }
    }
    

    private void ingresoPieza(Galeria galeria) {
        System.out.println("Ingresando nueva pieza...");
        String[] opcionesTipoPieza = {"Pintura", "Escultura", "Fotografía", "Impresión", "Video"};
        String tipoPieza = pedirOpcionAlUsuario("¿Qué tipo de pieza es?", opcionesTipoPieza);
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        String propietario = pedirCadenaAlUsuario("Ingrese el nombre del propietario:");
        int precio = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el precio:"));
        String nombrePropietario = pedirCadenaAlUsuario("Ingrese el nombre del propietario:");
        int diasConsignacion = Integer.parseInt(pedirCadenaAlUsuario("Ingrese los dias de consignación:"));
        boolean paraVentaFijo = Boolean.parseBoolean(pedirCadenaAlUsuario("Es para venta por valor fija?:"));
        boolean bloqueada = false;
        boolean comprada = false;
        

        
        if(tipoPieza == "Pintura")
        {
            double alto = Double.parseDouble(pedirCadenaAlUsuario("¿Qué tipo de pieza es?", opcionesTipoPieza);
            double ancho = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
            String estilo = pedirCadenaAlUsuario("Ingrese el nombre del propietario:");
 
        	Pieza pieza = galeria.crearPintura(nombrePieza, precio, nombrePropietario, diasConsignacion, paraVentaFijo, bloqueada , comprada, alto, ancho, estilo );
        }
        
        
        
        galeria.agregarPieza(pieza, propietario);
        
        // Lógica para agregar la nueva pieza a la galería
    }

  

    private void asignarMaximo(Galeria galeria) {
        System.out.println("Asignando máximo a subasta...");
    }

    private void confirmarOferta(Galeria galeria) {
        System.out.println("Confirmando oferta...");
    }
    

}
