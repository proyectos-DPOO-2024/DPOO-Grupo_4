package Casa_subastas.Interface;

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
    }
}
