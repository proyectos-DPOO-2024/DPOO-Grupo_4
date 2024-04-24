package Casa_subastas.Interface;

import java.util.List;
import java.util.Map;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;

public class MenuCliente extends ConsolaBasica {
	
    public static Galeria galeria = MenuPrincipal.galeria;


    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuCliente() {
        String[] opcionesMenuCliente = new String[]{"Ver catálogo","Ver datos de una pieza", "Hacer oferta (Valor fijo)", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú cliente", opcionesMenuCliente);
        switch (opcionSeleccionada) {
            case 1:
                verCatalogo();
                break;
            case 2:
                verDatosPieza();
                break;
            case 3:
                hacerOfertaValorFijo();
                break;
            case 4:
                System.out.println("Saliendo del menú cliente...");
                break;
        }
    }

    private void verCatalogo() {
        System.out.println("Mostrando catálogo...");
        Map<String, Pieza> piezas = galeria.getMapaPiezas();
        
        for (String llave : piezas.keySet()) {
            System.out.println(llave);
        }
    }
    
    private void verDatosPieza() {
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        Pieza pieza = galeria.getPieza(nombrePieza);
        System.out.println("Nombre: " + pieza.getNombrepieza());
        System.out.println("Costo: " + pieza.getCosto());
        System.out.println("Propietario: " + pieza.getPropietario());
        System.out.println("Esta disponible para compra por valor fijo: " + (pieza.getParaVentaValorFijo() ? "Sí" : "No"));
        System.out.println("Esta bloqueada por que alguien oferto por ella: " + (pieza.getBloqueada() ? "Sí" : "No"));






        
    	
    }


    private void hacerOfertaValorFijo() {
        System.out.println("Haciendo oferta con valor fijo...");
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        String nombreCliente = pedirCadenaAlUsuario("Ingrese el nombre del ofertante:");
        galeria.crearOfertaValorFijo(nombreCliente, nombrePieza);
        System.out.println("Oferta realizada");

    }
    
}
