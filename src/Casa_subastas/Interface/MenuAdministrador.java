package Casa_subastas.Interface;

public class MenuAdministrador extends ConsolaBasica {

    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuAdministrador() {
        String[] opcionesMenuAdmin = new String[]{"Ingreso de pieza", "Asignar máximo", "Confirmar oferta", "Salir"};
        int opcionSeleccionada = mostrarMenu("Menú administrador", opcionesMenuAdmin);
        switch (opcionSeleccionada) {
            case 1:
                ingresoPieza();
                break;
            case 2:
                asignarMaximo();
                break;
            case 3:
                confirmarOferta();
                break;
            case 4:
                System.out.println("Saliendo del menú administrador...");
                break;
        }
    }

    private void ingresoPieza() {
        System.out.println("Ingresando nueva pieza...");
    }

    private void asignarMaximo() {
        System.out.println("Asignando máximo a subasta...");
    }

    private void confirmarOferta() {
        System.out.println("Confirmando oferta...");
    }
}
