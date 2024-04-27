package Casa_subastas.Interface;

import java.util.List;

import Casa_subastas.modelo.Centro_compras.Pago;
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
                mostrarPagos();
                break;
            case 3:
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
    
    private void mostrarPagos() {
        System.out.println("Mostrando ultimos pagos...");
    	Galeria galeria = MenuPrincipal.galeria;
        List<Pago> pagos = galeria.getPagos();
        System.out.println(pagos);
    }
}
