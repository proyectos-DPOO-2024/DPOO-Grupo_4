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
import galeria.modelo.inventario.Galeria;

/**
 * 
 */
public class SalvadorCentroDeVentas {

	protected void guardarCentroDeVentas(Galeria galeria, JSONObject jCentroDeVentas) {
	
	salvarOfertas(galeria, jCentroDeVentas);
	salvarSubastas(galeria, jCentroDeVentas);
	salvarPagos(galeria, jCentroDeVentas);
	
	}
	
	
	private void salvarOfertas( Galeria galeria, JSONObject jobject )
    {
        JSONArray jOfertas = new JSONArray( );
        for( Oferta oferta : galeria.getOfertas() )
        {
        	JSONObject jOferta = new JSONObject( );
        	
        	jOferta.put( "loginCliente", oferta.getComprador().getLogin() );
        	jOferta.put( "nombrePieza", oferta.getPieza().getNombrepieza() );
        	jOferta.put( "ofertaVerificada", oferta.esConfirmada() );
        	
        	jOfertas.put( jOferta );
        }

        jobject.put( "ofertas", jOfertas );
    }
	
	private void salvarSubastas( Galeria galeria, JSONObject jobject )
    {
        JSONArray jSubastas = new JSONArray( );
        for( Subasta subasta : galeria.getSubastas() )
        {
        	JSONObject jSubasta = new JSONObject( );
        	
        	jSubasta.put( "valorMinimo", subasta.getValorMinimo() );
        	jSubasta.put( "valorInicial", subasta.getValorInicial() );
        	jSubasta.put( "nombrePieza", subasta.getPiezaSubastar().getNombrepieza() );
        	
        	List<Oferta> trazaDeOfertas = subasta.getTrazaOfertas();
        	JSONArray jTrazaDeOfertas = this.salvarOfertasSubasta(galeria, trazaDeOfertas);
        	jSubasta.put( "trazaDeOfertas", jTrazaDeOfertas );
        	
        	jSubasta.put( "finalizada", subasta.getFinalizada() );
        	
        	jSubastas.put( jSubasta );
        }

        jobject.put( "subastas", jSubastas );
    }
	
	private JSONArray salvarOfertasSubasta( Galeria galeria, List<Oferta> trazaOfertas )
    {
        JSONArray jOfertas = new JSONArray( );
        for( Oferta oferta : trazaOfertas )
        {
        	JSONObject jOferta = new JSONObject( );
        	
        	jOferta.put( "loginCliente", oferta.getComprador().getLogin() );
        	jOferta.put( "nombrePieza", oferta.getPieza().getNombrepieza() );
        	jOferta.put( "valor", oferta.getValorPieza() );
        	
        	jOfertas.put( jOferta );
        }
        
        return jOfertas;

    }
	
	private void salvarPagos( Galeria galeria, JSONObject jobject )
    {
        JSONArray jPagos = new JSONArray( );
        for( Pago pago : galeria.getPagos() )
        {
        	JSONObject jPago = new JSONObject( );
        	
        	jPago.put( "metodoPago", pago.getMetodoPago() );
        	jPago.put( "nombrePieza", pago.getMetodoPago() );
        	
        	jPagos.put( jPago );
        }

        jobject.put( "pagos", jPagos );
    }
}
