
package Casa_subastas.modelo.Inventario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Casa_subastas.modelo.Centro_compras.Oferta;
import Casa_subastas.modelo.Centro_compras.Pago;
import Casa_subastas.modelo.Centro_compras.Subasta;
import Casa_subastas.modelo.usuarios.Cliente;
import Casa_subastas.modelo.usuarios.Empleado;


public class Galeria 
{
	private List<Pieza> piezas;
	private Map<String, List<Pieza>> piezasPropietarios;
	private Map<String, Pieza> mapaPiezas;
	private Map<String, Cliente>mapaClientes;
	private Map<String, Oferta> mapaOfertas;
	private Map<String, Subasta>mapaSubastas;
	private List<Pago> pagos;

	public Galeria () {
		this.piezas = new LinkedList<>();
		this.piezasPropietarios = new HashMap<>();
		this.mapaPiezas = new HashMap<>();
		this.mapaClientes = new HashMap<>();
		this.mapaOfertas = new HashMap<>();
		this.mapaSubastas = new HashMap<>();
		this.pagos = new ArrayList<>();
	}
	
	public Pieza crearPintura(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, String estilo) {		
		
			Pieza pieza = new Pintura(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada, alto, ancho, estilo );
			return pieza;
		}
	
	public Pieza crearImpresiones(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, String original, String metodocreacion, float alto, float ancho) {		

			Pieza pieza = new Impresiones(nombrePieza, Precio, nombrePropietario,  diasConsignacion,
					 paraVentafijo,  Bloqueada, Comprada,  original,  metodocreacion, alto,  ancho);
			return pieza;
		}	

	public Pieza crearEscultura(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, float profundo, String materialconstruccion) {		
		
			Pieza pieza = new Escultura(nombrePieza,  Precio ,nombrePropietario,  diasConsignacion,
					paraVentafijo,  Bloqueada, Comprada,  alto,  ancho,  profundo, materialconstruccion );
			return pieza;
		
		}	
	public Pieza crearFotografia(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, Boolean color) {		
		
			Pieza pieza = new Fotografia( nombrePieza,  Precio, nombrePropietario, diasConsignacion,
					 paraVentafijo, Bloqueada,  Comprada,  alto,  ancho,  color);
			return pieza;

		}	
	public Pieza crearVideo(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion, Boolean paraVentafijo,
			Boolean Bloqueada, Boolean Comprada, float duracion, Boolean color, double memoria) {		
		
			Pieza pieza = new Video(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo,
					 Bloqueada, Comprada, duracion, color, memoria);
			return pieza;
		}	


			
		public void agregarPieza(Pieza pieza, String propietario) {
			
			
		this.piezas.add(pieza);
		this.mapaPiezas.put(pieza.getNombrepieza(), pieza);
		 if (piezasPropietarios.containsKey(propietario)) {
			 List<Pieza> piezasDelCliente = piezasPropietarios.get(propietario);
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		 else {
			 List<Pieza> piezasDelCliente = new LinkedList<>();
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		this.piezasPropietarios.put(propietario, piezas);
	}
	
	public void agregarCliente(String login, String password, boolean esComprador, boolean esPropietario, int cellphone, long valorMaximo, boolean isVerificado ) throws Exception {
		Cliente existeLoginCliente = Cliente.getCliente(login);
		Empleado existeLoginEmpleado = Empleado.getEmpleado(login);
		
		if (existeLoginCliente == null && existeLoginEmpleado == null) {
			Cliente cliente = new Cliente(login, password, esComprador, esPropietario, cellphone, valorMaximo, isVerificado );
			mapaClientes.put(login, cliente);
		}
		else {
			Exception e = new Exception("El login ingresado ya está en uso.");
			throw e;
		}
		
	}
	
	
	public void asignarMaximo (Cliente propietario, long valor) {
		propietario.extenderValorMaximoCompras(valor);
	}
	
	/*
	 * Crea una oferta de valor fijo, para eso primero se busca los objetos de pieza y cliente
	 * y despues se miran los requisitos. Cliente valor maximo de compras sea mayor al precio de la pieza
	 * y la pieza este disponible para venta por valor fijo. Despues se crea una oferta, se guarda en el 
	 * hashmap y se bloquea la pieza para que nadie mas pueda comprarla mientras el administrador revisa
	 */
	
	public void crearOfertaValorFijo(String nombreCliente, String nombrePieza)
	{
		Cliente cliente = mapaClientes.get(nombreCliente);
		Pieza pieza = mapaPiezas.get(nombrePieza);
		
		if(cliente.getValorMaximoCompras() > pieza.getCosto()) {
			if(pieza.getParaVentaValorFijo()) {
				if(!pieza.getBloqueada()) {
				
					Oferta oferta = new Oferta(pieza, cliente, false);
					this.mapaOfertas.put(pieza.getNombrepieza(), oferta);
					pieza.Bloquear();
					
				}
				else {
					System.out.println("La pieza esta bloqueada hasta que el administrador verifique la oferta");
				}
				
			}
			else {
				System.out.println("La pieza no esta disponible para venta por valor fijo");
			}
		}
		else {
		    System.out.println("El valor de la compra es mayor a lo que tiene permitido por el administrador");
		}
	}
	
	/*
	 * Verifica una oferta de valor fijo
	 */
	public void verificarOfertaValorFijo(String nombrePieza)
	{
		Oferta oferta = mapaOfertas.get(nombrePieza);
		oferta.confirmarOferta();
	}
		
	/*
	 * realiza el pago dada una oferta ya verificada. Crea un nuevo pago. Lo adiciona a los pagos 
	 * y le descuenta al cliente el saldo. Finalmente cambia de propietario la Pieza.
	 */
	
	public void realizarPago(String metodoPago, String nombrePieza) {
		Oferta oferta = mapaOfertas.get(nombrePieza);
		long valorPagado = oferta.getValorPieza();
		Cliente comprador = oferta.getComprador();
		Pieza pieza = oferta.getPieza();
		
		Pago pago = new Pago(metodoPago, valorPagado, comprador, pieza);
		pagos.add(pago);
		
		oferta.pagar();
		
		comprador.registrarCompra(pieza, valorPagado);
		
		}
	
	public void cambiarPropietarioPieza(String nombrePieza, String nombreClienteNuevo) {
		
		Cliente clienteNuevo = mapaClientes.get(nombreClienteNuevo);
		Pieza pieza = mapaPiezas.get(nombrePieza);
		String nombreClienteAntiguo = pieza.getPropietario(); 
		Cliente clienteAntiguo = mapaClientes.get(nombreClienteAntiguo);
		
		clienteAntiguo.registrarVenta(pieza, clienteNuevo );
		
	}
	
	public void crearSubasta(String nombrePieza, long valorMinimo, long valorInicial) {
		
	Pieza pieza = mapaPiezas.get(nombrePieza);
	pieza.Bloquear();
	Subasta subasta = new Subasta(valorMinimo, valorInicial, pieza);
	mapaSubastas.put(nombrePieza, subasta);
	
	}
	
	
	/*
	 * Esta función es de suma importancia, revisa si el cliente esta verificado, despues si
	 * el valor maximo de compras de el es mayor al que ofrecio y mayor al actual de la pieza
	 * si todo esto se cumple hace la oferta y se actualiza el valor actual de la pieza La oferta se guarda en la trazaOfertas de la 
	 * subasta. La subasta esta guardada en mapaSubastas
	 */
	public void crearOfertaSubasta(String nombrePieza, String nombreCliente, int valor) {
		Pieza pieza = mapaPiezas.get(nombrePieza);
		Cliente cliente = mapaClientes.get(nombreCliente);
		Subasta subasta = mapaSubastas.get(nombrePieza);
		
		if((cliente.getValorMaximoCompras() > valor) && (valor > subasta.getValorActual())) {
			if(cliente.isVerificado()) {
				Oferta oferta = new Oferta(pieza, valor ,cliente);
				subasta.añadirOferta(oferta);
				subasta.setValorActual(valor);
				subasta.setCompradorGanador(cliente);
			}
			else {
				System.out.println("Usuario no verificado");
			}
		}
		else {
			System.out.println("En este momento el valor de la pieza es mayor al que ofertaste");

		}
	}
	
	
	/*
	 *Esta función cierra la subasta bajo las ordenes de un operador o un administrador.
	 *Al cerrar la subasta se verifica que la ultima oferta sea mayor al valor minimo de la 
	 *subasta. Si es así, la ultima oferta gana la subasta. El siguiente paso, el cual no se realiza en este metodo, es realizar 
	 *el pago y hacer cambio de propietarios.
	 */
	public Oferta cerrarSubasta(String nombrePieza) {
		Pieza pieza = mapaPiezas.get(nombrePieza);
		
		Oferta ofertaGanadora = null;
		Subasta subasta = mapaSubastas.get(nombrePieza);
		if(subasta.getValorActual()> subasta.getValorMinimo()) {
		
			ofertaGanadora = subasta.finalizarSubasta();
			
		}
		else {
			System.out.println("En esta subasta la pieza no alcanzo el precio maximo para ser vendida");
			ofertaGanadora = null;
		}
		return ofertaGanadora;
		
	}
	public List<Pago> getPagos()
	{
		return pagos;
	}
	
	public List<Pieza> getPiezas(){
		return piezas;
	}
	
	public Map<String,Subasta> getMapaSubastas(){
		return mapaSubastas;
	}
	
	public Collection<Oferta> getOfertas(){
		return mapaOfertas.values();
	}
}

	
	
	
	
	
