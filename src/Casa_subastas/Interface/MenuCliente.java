package Casa_subastas.Interface;

public class MenuCliente extends ConsolaBasica {

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
            case 3:
                hacerOfertaSubasta();
                break;
            case 4:
                System.out.println("Saliendo del menú cliente...");
                break;
        }
    }

    private void verCatalogo() {
        System.out.println("Mostrando catálogo...");
    }

    private void hacerOfertaValorFijo() {
        System.out.println("Haciendo oferta con valor fijo...");
    }

    private void hacerOfertaSubasta() {
        System.out.println("Haciendo oferta en subasta...");
    }
}
