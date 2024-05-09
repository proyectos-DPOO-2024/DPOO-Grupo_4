/**
 * 
 */
package galeria.modelo.centroventas;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;

/**
 * 
 */
public class CentroDeVentas
{

	/**
	 * El mapa de historial de pagos por piezas relaciona el nombre de una pieza con
	 * la lista de pagos donde la pieza sse ha visto involucrada. El mapa de piezas
	 * actuales de propietarios relaciona el login de un cliente con la lista del
	 * nombre de las piezas que posee actualmente. El mapa de piezas pasadas de
	 * propietarios relaciona el login de un cliente con la lista del nombre de las
	 * piezas que poseyó en el pasado.
	 */

	private Map<String, List<Pago>> historialDePagosPorPieza;

	private Map<String, List<Pago>> historialComprasComprador;
	private Map<String, List<Pago>> historialVentasPropietario;
	
	private Map<String,Subasta> mapaSubastas;
	private Map<String, Oferta> mapaOfertasVentaDirecta;
	
	private Galeria galeria;
	
	public CentroDeVentas(Galeria gale) {
		historialDePagosPorPieza = new HashMap<String, List<Pago>>();
		historialComprasComprador = new HashMap<String, List<Pago>>();
		mapaSubastas = new HashMap<String, Subasta>();
		mapaOfertasVentaDirecta = new HashMap<String, Oferta>();
	}
	
	public CentroDeVentas(Galeria gale, Map<String, Subasta> mapaDeSubastas, Map<String, Oferta> mapaDeOfertasVentaDirecta, Map<String, List<Pago>> historialDePagosPorPieza,
			Map<String, List<Pago>> historialComprasComprador, Map<String, List<Pago>> historialVentasPropietario) {
		
		this.mapaSubastas = mapaDeSubastas;
		this.mapaOfertasVentaDirecta = mapaDeOfertasVentaDirecta;
		this.historialDePagosPorPieza = historialDePagosPorPieza;
		this.historialComprasComprador = historialComprasComprador;
		this.historialVentasPropietario = historialVentasPropietario;
		
		this.galeria = gale; 
	}

	public List<Pago> getHistorialPieza(String nombrePieza) {
		return historialDePagosPorPieza.get(nombrePieza);
	}

	public List<Pago> getHistorialCompras(String loginComprador) {
		return historialComprasComprador.get(loginComprador);
	}

	public List<Pago> getHistorialVentas(String loginPropietario) {
		return historialVentasPropietario.get(loginPropietario);
	}
	
	
	//Setters
	public void setHistorialDePagosPorPieza(Map<String, List<Pago>> historialDePagosPorPieza) {
		this.historialDePagosPorPieza = historialDePagosPorPieza;
	}

	public void setHistorialComprasComprador(Map<String, List<Pago>> historialComprasComprador) {
		this.historialComprasComprador = historialComprasComprador;
	}

	public void setHistorialVentasPropietario(Map<String, List<Pago>> historialVentasPropietario) {
		this.historialVentasPropietario = historialVentasPropietario;
	}

	public void setMapaSubastas(Map<String, Subasta> mapaDeSubastas) {
		this.mapaSubastas = mapaDeSubastas;
	}

	public void setMapaOfertasVentaDirecta(Map<String, Oferta> mapaDeOfertasVentaDirecta) {
		this.mapaOfertasVentaDirecta = mapaDeOfertasVentaDirecta;
	}
	
	
	public void crearOfertaValorFijo(String nombreCliente, String nombrePieza) throws Exception {
	    Cliente cliente = galeria.getCliente(nombreCliente);
	    Pieza pieza = galeria.getPieza(nombrePieza);
	    
	    if(pieza.getPrecioVentaDirecta() == 0 ) {
	        if(cliente.getTopeCompras() > pieza.getPrecioVentaDirecta()) {
	            if(!pieza.isBloqueada()) {
	                Oferta oferta = new Oferta(pieza, pieza.getPrecioVentaDirecta(), nombreCliente, pieza.getLoginPropietario(), pieza.getTipo());
	                this.mapaOfertasVentaDirecta.put(pieza.getTitulo(), oferta);
	                pieza.bloquear();
	            }
	            else {
	                throw new Exception("La pieza está bloqueada hasta que el administrador verifique la oferta");
	            }
	        }
	        else {
	            throw new Exception("La pieza no está disponible para venta por valor fijo");
	        }
	    }
	    else {
	        throw new Exception("El valor de la compra es mayor a lo que tiene permitido por el administrador");
	    }
	}
	
	/*
	 * realiza el pago dada una oferta ya verificada. Crea un nuevo pago. Lo adiciona a los pagos 
	 * y le descuenta al cliente el saldo. Finalmente cambia de propietario la Pieza.
	 */
	
	public void realizarPago(int metodoPago, Fecha fecha ,String nombrePieza)
	{
		Oferta oferta = mapaOfertasVentaDirecta.get(nombrePieza);
		long valorPagado = oferta.getValor();
		String loginComprador = oferta.getLoginComprador();
		Pieza pieza = oferta.getPieza();
		
		Pago pago = new Pago(pieza, valorPagado, loginComprador, pieza.getLoginPropietario(), pieza.getTipo(), metodoPago, fecha);
		
		//Se añade el pago a los diferentes maps que hay
		historialDePagosPorPieza.get(nombrePieza).add(pago);
		historialComprasComprador.get(loginComprador).add(pago);
		historialVentasPropietario.get(pieza.getLoginPropietario()).add(pago);
		
		// cambiar propietario
		galeria.entregarPieza(pieza, loginComprador);
		
	}
	
	
	/**
	 * Crea una nueva subasta para una pieza si esta no está actualmente en subasta.
	 * 
	 * @param nombrePieza El nombre de la pieza para la cual se creará la subasta.
	 * @throws Exception Si la pieza ya está siendo subastada.
	 */
	public void crearSubasta(String nombrePieza) throws Exception {
	    Pieza pieza = galeria.getPieza(nombrePieza);
	    
	    if (!pieza.isEnSubasta()) {
	        pieza.bloquear();
	        Subasta subasta = new Subasta(pieza.getTitulo(), pieza.getPrecioInicioSubasta(), pieza.getPrecioMinimoSubasta());
	        mapaSubastas.put(nombrePieza, subasta);
	    } else {
	        throw new Exception("Esta pieza ya está siendo subastada");
	    }
	}

	/*
	 * Esta función es de suma importancia, revisa si el cliente esta verificado, despues si
	 * el valor maximo de compras de el es mayor al que ofrecio y mayor al actual de la pieza
	 * si todo esto se cumple hace la oferta y se actualiza el valor actual de la pieza La oferta se guarda en la trazaOfertas de la 
	 * subasta. La subasta esta guardada en mapaSubastas
	 */
	public void crearOfertaSubasta(String nombrePieza, String nombreCliente, int valor) throws Exception {
	    Pieza pieza = galeria.getPieza(nombrePieza);
	    Cliente cliente = galeria.getCliente(nombreCliente);
	    Subasta subasta = mapaSubastas.get(nombrePieza);
	    
	    if ((cliente.getTopeCompras() > valor) && (valor > subasta.getValorActual()))
	    {
	        if (cliente.isVerificado()) 
	        {
	            Oferta oferta = new Oferta(pieza, pieza.getPrecioVentaDirecta(), nombreCliente, pieza.getLoginPropietario(), pieza.getTipo());
	            subasta.añadirOferta(oferta);
	            subasta.serValorActual(valor);
	        } 
	        else 
	        {
	            throw new Exception("Usuario no verificado, es decir, no habilitado para subastar");
	        }
	    } 
	    else 
	    {
	        throw new Exception("En este momento el valor de la pieza es mayor al que ofertaste");
	    }
	}
	
}
	
	
	
	
	

