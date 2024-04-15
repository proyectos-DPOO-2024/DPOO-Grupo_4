package Casa_subastas.Interface;

import Casa_subastas.modelo.Inventario.Galeria;

public class MenuCajero extends ConsolaBasica {

    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuCajero() {
        String[] opcionesMenuCajero = new String[]{"Recibir pago", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú cajero", opcionesMenuCajero);
        switch (opcionSeleccionada) {
            case 1:
                recibirPago();
                break;
            case 2:
                System.out.println("Saliendo del menú cajero...");
                break;
        }
    }

    private void recibirPago() {
        System.out.println("Recibiendo pago...");
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza:");
        String metodoPago = pedirCadenaAlUsuario("Ingrese el metodo de Pago:");
    	Galeria galeria = MenuPrincipal.galeria;
        galeria.realizarPago(metodoPago, nombrePieza);
        System.out.println("Pago recibido");
    }
}
