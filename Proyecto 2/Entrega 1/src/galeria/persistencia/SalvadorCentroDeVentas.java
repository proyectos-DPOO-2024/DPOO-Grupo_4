/**
 * 
 */
package galeria.persistencia;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.centroventas.Oferta;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.Subasta;
import galeria.modelo.centroventas.Transaccion;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;

/**
 * 
 */
public class SalvadorCentroDeVentas {

	protected void guardarCentroDeVentas(Galeria galeria, JSONObject jCentroDeVentas) {
	
	guardarListaOfertasPorVentaDirecta(galeria, jCentroDeVentas);
	guardarSubastas(galeria, jCentroDeVentas);
	guardarHistorialDePagosPorPieza(galeria, jCentroDeVentas);
	guardarHistorialDeComprasComprador(galeria, jCentroDeVentas);
	guardarHistorialDeVentasPropietario(galeria, jCentroDeVentas);
	
	}
	
	
	private void guardarListaOfertasPorVentaDirecta( Galeria galeria, JSONObject jCentroDeVentas )
    {
        JSONArray jOfertas = new JSONArray( );
        for( Oferta oferta : galeria.getCentroDeVentas().getMapaOfertasVentaDirecta().values() )
        {
        	JSONObject jOferta = new JSONObject( );
        	
        	jOferta.put( "tituloPieza", oferta.getPieza().getTitulo() );
        	jOferta.put( "valor", oferta.getValor() );
        	jOferta.put( "loginComprador", oferta.getLoginComprador() );
        	jOferta.put( "loginVendedor", oferta.getLoginVendedor() );
        	jOferta.put( "tipo", Transaccion.VENTA_DIRECTA );
        	
        	jOfertas.put( jOferta );
        }

        jCentroDeVentas.put( "listaOfertasVentaDirecta", jOfertas );
    }
	
	private void guardarSubastas( Galeria galeria, JSONObject jCentroDeVentas )
    {
        JSONArray jSubastas = new JSONArray( );
        for( Subasta subasta : galeria.getCentroDeVentas().getMapaSubastas().values() )
        {
        	JSONObject jSubasta = new JSONObject( );
        	
        	jSubasta.put( "tituloPiezaSubastada", subasta.getTituloPiezaSubastada() );
        	jSubasta.put( "valorActual", subasta.getValorActual() );
        	jSubasta.put( "valorMinimo", subasta.getValorMinimo() );
        	
        	int tamanoTrazaOfertas = subasta.getTrazaOfertas().size();
        	JSONArray trazaOfertas = new JSONArray(tamanoTrazaOfertas);
        	for (int i = 0; i < tamanoTrazaOfertas; i++) {
        		Oferta oferta = subasta.getTrazaOfertas().pop();
        		
        		JSONObject jOferta = new JSONObject();
        		
        		jOferta.put( "tituloPieza", oferta.getPieza().getTitulo() );
            	jOferta.put( "valor", oferta.getValor() );
            	jOferta.put( "loginComprador", oferta.getLoginComprador() );
            	jOferta.put( "loginVendedor", oferta.getLoginVendedor() );
            	jOferta.put( "tipo", Transaccion.SUBASTA );
        		
        		trazaOfertas.put(oferta);
        	}
        	
        	jSubasta.put( "trazaOfertas", trazaOfertas );
        	
        	jSubastas.put( jSubasta );
        }

        jCentroDeVentas.put( "listaSubastas", jSubastas );
    }
	
	private void guardarHistorialDePagosPorPieza( Galeria galeria, JSONObject jCentroDeVentas )
    {
        JSONArray jParejasPiezaPagos = new JSONArray( );
        for( Pieza pieza : galeria.getMapaPiezas().values())
        {
        	JSONObject jParejaPiezaPagos = new JSONObject( );
        	
        	List<Pago> pagos = galeria.getCentroDeVentas().getHistorialPieza(pieza.getTitulo());
        	
        	jParejaPiezaPagos.put( "tituloPieza", pieza.getTitulo() );
        	
        	JSONArray jListaPagos = guardarPagos(pagos);
        	
        	jParejaPiezaPagos.put( "listaPagos", jListaPagos );
        	
        	jParejasPiezaPagos.put(jParejaPiezaPagos);
        }

        jCentroDeVentas.put( "historialDePagosPorPieza", jParejasPiezaPagos );
    }
	
	private void guardarHistorialDeComprasComprador( Galeria galeria, JSONObject jCentroDeVentas )
    {
        JSONArray jParejasCompradorPagos = new JSONArray( );
        for( Cliente comprador : galeria.getMapaClientes().values())
        {
        	JSONObject jParejaCompradorPagos = new JSONObject( );
        	
        	List<Pago> pagos = galeria.getCentroDeVentas().getHistorialCompras(comprador.getLogin());
        	
        	jParejaCompradorPagos.put( "loginComprador", comprador.getLogin() );
        	
        	JSONArray jListaPagos = guardarPagos(pagos);
        	
        	jParejaCompradorPagos.put( "listaPagos", jListaPagos );
        	
        	jParejasCompradorPagos.put(jParejaCompradorPagos);
        }

        jCentroDeVentas.put( "historialDeComprasComprador", jParejasCompradorPagos );
    }
	
	private void guardarHistorialDeVentasPropietario( Galeria galeria, JSONObject jCentroDeVentas )
    {
        JSONArray jParejasPropietarioVentas = new JSONArray( );
        for( Cliente propietario : galeria.getMapaClientes().values())
        {
        	JSONObject jParejaPropietarioVentas = new JSONObject( );
        	
        	List<Pago> pagos = galeria.getCentroDeVentas().getHistorialCompras(propietario.getLogin());
        	
        	jParejaPropietarioVentas.put( "loginPropietario", propietario.getLogin() );
        	
        	JSONArray jListaPagos = guardarPagos(pagos);
        	
        	jParejaPropietarioVentas.put( "listaPagos", jListaPagos );
        	
        	jParejasPropietarioVentas.put(jParejaPropietarioVentas);
        }

        jCentroDeVentas.put( "historialDeVentasPropietario", jParejasPropietarioVentas );
    }
	
	
	
	//Funciones Auxiliares
	
	
	private JSONArray guardarPagos(List<Pago> pagos)
    {
        JSONArray jPagos = new JSONArray( );
        
        if ( pagos.equals(null) ) {
        	return jPagos;
        }
        
        else {
	        for( Pago pago : pagos )
	        {
	        	JSONObject jPago = new JSONObject( );
	        	
	        	jPago.put( "tituloPieza", pago.getPieza().getTitulo() );
	        	jPago.put( "valor", pago.getValor() );
	        	jPago.put( "loginComprador", pago.getLoginComprador() );
	        	jPago.put( "loginVendedor", pago.getLoginVendedor() );
	        	jPago.put( "tipo", pago.getTipo() );
	        	jPago.put( "metodoDePago", pago.getMetodoPago() );
	        	
	        	JSONArray fecha = new JSONArray(3);
	        	
	        	fecha.put(pago.getFecha().getDia());
	        	fecha.put(pago.getFecha().getMes());
	        	fecha.put(pago.getFecha().getAnio());
	        	
	        	jPago.put( "fecha", fecha );
	        	
	        	
	        	jPagos.put( jPago );
	        }
	
	        return jPagos;
        }
    }
}
