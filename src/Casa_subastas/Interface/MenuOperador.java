package Casa_subastas.Interface;

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
        System.out.println("Creando oferta en subasta...");
    }
}