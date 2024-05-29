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

/**
 * 
 */
public class SalvadorCentroDeVentas {

	protected void guardarCentroDeVentas(Galeria galeria, JSONObject jCentroDeVentas) {
	
	salvarListaOfertasPorVentaDirecta(galeria, jCentroDeVentas);
	salvarSubastas(galeria, jCentroDeVentas);
	salvarPagos(galeria, jCentroDeVentas);
	
	}
	
	
	private void salvarListaOfertasPorVentaDirecta( Galeria galeria, JSONObject jCentroDeVentas )
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
	
	private void salvarSubastas( Galeria galeria, JSONObject jCentroDeVentas )
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
        		trazaOfertas.put(oferta);
        	}
        	
        	jSubasta.put( "trazaOfertas", trazaOfertas );
        	
        	jSubastas.put( jSubasta );
        }

        jCentroDeVentas.put( "listaSubastas", jSubastas );
    }
	private JSONArray salvarOfertasSubasta( Galeria galeria, List<Oferta> trazaOfertas )
    {
        JSONArray jOfertas = new JSONArray( );
        for( Oferta oferta : trazaOfertas )
        {
        	JSONObject jOferta = new JSONObject( );
        	
        	jOferta.put( "loginCliente", oferta.getLoginComprador());
        	jOferta.put( "nombrePieza", oferta.getPieza().getTitulo() );
        	jOferta.put( "valor", oferta.getValor() );
        	
        	jOfertas.put( jOferta );
        }
        
        return jOfertas;

    }
	
	

	private void salvarPagos( Galeria galeria, JSONObject jobject )
    {
		/*
        JSONArray jPagos = new JSONArray( );
        for( Pago pago : galeria.getCentroDeVentas().getHistorialDePagosPorPieza().values().)
        {
        	JSONObject jPago = new JSONObject( );
        	
        	jPago.put( "metodoPago", pago.getMetodoPago() );
        	jPago.put( "nombrePieza", pago.getMetodoPago() );
        	
        	jPagos.put( jPago );
        }

        jobject.put( "pagos", jPagos );
        */
    }
	
}
