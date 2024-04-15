package Casa_subastas.Interface;

import java.util.List;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;

public class MenuCliente extends ConsolaBasica {
	
    public static Galeria galeria = MenuPrincipal.galeria;


    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuCliente() {
        String[] opcionesMenuCliente = new String[]{"Ver catálogo", "Hacer oferta (Valor fijo)", "Hacer oferta (Subasta)", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú cliente", opcionesMenuCliente);
        switch (opcionSeleccionada) {
            case 1:
                verCatalogo();
                break;
            case 2:
                hacerOfertaValorFijo();
                break;
            case 4:
                System.out.println("Saliendo del menú cliente...");
                break;
        }
    }

    private void verCatalogo() {
    	
        System.out.println("Mostrando catálogo...");
        List<Pieza> piezas = galeria.darPiezas();
        System.out.println(piezas);

    }

    private void hacerOfertaValorFijo() {
        System.out.println("Haciendo oferta con valor fijo...");
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        String nombreCliente = pedirCadenaAlUsuario("Ingrese el nombre del ofertante:");
        galeria.crearOfertaValorFijo(nombreCliente, nombrePieza);
        System.out.println("Oferta realizada");

    }
    
}
