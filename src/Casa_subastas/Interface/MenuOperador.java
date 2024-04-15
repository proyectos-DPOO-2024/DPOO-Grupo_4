package Casa_subastas.Interface;

import Casa_subastas.modelo.Inventario.Galeria;

public class MenuOperador extends ConsolaBasica {

    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuOperador() {
        String[] opcionesMenuOperador = new String[]{"Crear oferta (Subasta)", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú operador", opcionesMenuOperador);
        switch (opcionSeleccionada) {
            case 1:
                crearOfertaSubasta();
                break;
            case 2:
                System.out.println("Saliendo del menú operador...");
                break;
        }
    }

    private void crearOfertaSubasta() {
    	Galeria galeria = MenuPrincipal.galeria;
        System.out.println("Creando oferta en subasta...");
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        String nombreCliente = pedirCadenaAlUsuario("Ingrese el nombre del ofertante:");
        int valor = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el nombre del ofertante:"));
        galeria.crearOfertaSubasta(nombrePieza,nombreCliente, valor);
        System.out.println("Su oferta ha sido creada");
    }
    
    private void mostrarOfertasSubasta() {
    }
}