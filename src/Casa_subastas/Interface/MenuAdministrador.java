package Casa_subastas.Interface;

import java.util.Arrays;
import Casa_subastas.modelo.Centro_compras.Oferta;
import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;

public class MenuAdministrador extends ConsolaBasica {

    @Override
    protected void mostrarMenuPrincipal() {
    }

    protected void mostrarMenuAdministrador() throws Exception {
    	Galeria galeria = MenuPrincipal.galeria;
        String[] opcionesMenuAdmin = new String[]{"Ingreso de pieza", "Asignar máximo", "Confirmar oferta","Crear Subasta","Cerrar subasta","Agregar cliente", "Salir"};
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
                crearSubasta(galeria);
                break;
            case 5:
                cerrarSubasta(galeria);
                break;
            case 6:
                agregarCliente(galeria);
                break;
            case 7:
                System.out.println("Saliendo del menú administrador...");
                break;
        }
    }
    

    private void ingresoPieza(Galeria galeria) {
        System.out.println("Ingresando nueva pieza...");
        String[] opcionesTipoPieza = {"Pintura", "Escultura", "Fotografía", "Impresión", "Video"};
        String tipoPieza = pedirOpcionAlUsuario("¿Qué tipo de pieza es?", opcionesTipoPieza);
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
        String propietario = pedirCadenaAlUsuario("Ingrese el nombre del propietario");
        int precio = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el precio"));
        int diasConsignacion = Integer.parseInt(pedirCadenaAlUsuario("Ingrese los dias de consignación"));
        boolean paraVentaFijo = Boolean.parseBoolean(pedirCadenaAlUsuario("Es para venta por valor fija? (ingrese true o false)"));
        boolean bloqueada = false;
        boolean comprada = false;
        
        Pieza pieza = null;
        
        if(tipoPieza == "Pintura")
        {
            float alto = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la altura de la pieza en cm"));
            float ancho = Float.parseFloat(pedirCadenaAlUsuario("Ingrese el ancho de la pieza en cm"));
            String estilo = pedirCadenaAlUsuario("Ingrese el estilo de la obra");
        	pieza = galeria.crearPintura(nombrePieza, precio, propietario, diasConsignacion, paraVentaFijo, bloqueada, comprada, alto, ancho, estilo);
        	System.out.println("pieza "+ pieza.getNombrepieza()+"se ha añadido correctamente");
        }
        if(tipoPieza == "Escultura")
        {
            float alto = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la altura de la pieza en cm"));
            float ancho = Float.parseFloat(pedirCadenaAlUsuario("Ingrese el ancho de la pieza en cm"));
            float profundidad = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la profundidad de la pieza en cm"));
            String materialDeContrsuccion = pedirCadenaAlUsuario("Ingrese el material del que esta hecho la obra");
        	pieza = galeria.crearEscultura(nombrePieza, precio, propietario, diasConsignacion, paraVentaFijo, bloqueada, comprada, alto, ancho, profundidad, materialDeContrsuccion);
        }
        if (tipoPieza == "Fotografía") {
        	float alto = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la altura de la pieza en cm"));
            float ancho = Float.parseFloat(pedirCadenaAlUsuario("Ingrese el ancho de la pieza en cm"));
            Boolean color = Boolean.parseBoolean(pedirCadenaAlUsuario("Ingrese si tienes color (true = si y false = no"));   
        	pieza = galeria.crearFotografia(nombrePieza, precio, propietario, diasConsignacion, paraVentaFijo, bloqueada, comprada, alto, ancho, color);
        }
        if (tipoPieza == "Impresión")
        {
        	float alto = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la altura de la pieza en cm"));
            float ancho = Float.parseFloat(pedirCadenaAlUsuario("Ingrese el ancho de la pieza en cm"));
            String original = pedirCadenaAlUsuario("es original");
            String metodocracion = pedirCadenaAlUsuario("que metodo se suo para crearla");
            pieza = galeria.crearImpresiones(nombrePieza, precio, propietario, diasConsignacion, paraVentaFijo, bloqueada, comprada, original, metodocracion, alto, ancho);
        }
        if (tipoPieza == "Video")
        {
        	float duracion = Float.parseFloat(pedirCadenaAlUsuario("Ingrese la duración"));
        	Boolean color = Boolean.parseBoolean(pedirCadenaAlUsuario("Ingrese si tienes color (true = si y false = no"));
        	double memoria = Double.parseDouble(pedirCadenaAlUsuario("Ingrese la duración"));
        	pieza = galeria.crearVideo(nombrePieza, precio, propietario, diasConsignacion,  paraVentaFijo, bloqueada, comprada, duracion, color, memoria);
        }
        System.out.println("pieza "+ pieza.getNombrepieza()+" se ha añadido correctamente");
        
        galeria.agregarPieza(pieza, propietario);
        

    }

  

    public void asignarMaximo(Galeria galeria) {
        System.out.println("Asignando máximo valor de compra de un cliente...");
        String nombreCliente = pedirCadenaAlUsuario("Ingrese el nombre del Cliente");
        long valor = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el valor de compra maximo"));
        galeria.asignarMaximo(nombreCliente, valor);
    }
    
    

    public void confirmarOferta(Galeria galeria) {
        System.out.println("Confirmando oferta...");
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
        galeria.verificarOfertaValorFijo(nombrePieza);
    }
    
    public void crearSubasta(Galeria galeria)
    {
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
        long valorMinimo = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el valor minimo para vender la pieza"));
        long valorInicial = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el valor inicial de la subasta"));
        galeria.crearSubasta(nombrePieza, valorMinimo, valorInicial);
        System.out.println("Subasta creada");

    }
    
    public void cerrarSubasta(Galeria galeria) {
        String nombrePieza = pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
    	Cliente ganador = galeria.cerrarSubasta(nombrePieza);
    	galeria.cambiarPropietarioPieza(nombrePieza, ganador.getLogin());
    	
    }
    
    public void agregarCliente(Galeria galeria) throws Exception {
    	
        String nombreCliente = pedirCadenaAlUsuario("Ingrese el nombre(login) del cliente");
        String pasword = pedirCadenaAlUsuario("Ingrese la contraseña del cliente");
        boolean esComprador = Boolean.parseBoolean(pedirCadenaAlUsuario("El cliente es comprador?"));
        boolean espropietario = Boolean.parseBoolean(pedirCadenaAlUsuario("El cliente es propietario?"));
        int cellphone = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el numero de telefono"));
        long valorMaximo = Integer.parseInt(pedirCadenaAlUsuario("Ingrese el valor maximo para compras"));
        boolean esVerificado = Boolean.parseBoolean(pedirCadenaAlUsuario("El cliente es puede participar en subastas?"));

    	galeria.agregarCliente(nombreCliente, pasword, esComprador, espropietario, cellphone, valorMaximo, esVerificado);
    }

}

