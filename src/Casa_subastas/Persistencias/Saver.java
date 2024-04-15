package Casa_subastas.Persistencias;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Centro_compras.Oferta;
import Casa_subastas.modelo.Centro_compras.Pago;
import Casa_subastas.modelo.Centro_compras.Subasta;
import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;
import Casa_subastas.modelo.usuarios.Empleado;


public class Saver {

	public void salvarGaleria( String archivo, Galeria galeria ) throws Exception
    {
        JSONObject jobject = new JSONObject( );

        salvarEmpleados( galeria, jobject );
        
        SalvadorPiezas salvadorPiezas = new SalvadorPiezas();
        salvadorPiezas.salvarPiezas( galeria, jobject );
        
        salvarClientes( galeria, jobject );
        
        salvarOfertas( galeria, jobject );
        
        salvarSubastas( galeria, jobject );
        
        salvarPagos( galeria, jobject );

        
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }
	
	private void salvarEmpleados( Galeria galeria, JSONObject jobject )
    {
        JSONArray jEmpleados = new JSONArray( );
        for( Empleado empleado : Empleado.getEmpleados() )
        {
        	JSONObject jEmpleado = new JSONObject( );
        	
        	jEmpleado.put( "rol", empleado.getRol() );
        	jEmpleado.put( "login", empleado.getLogin() );
        	jEmpleado.put( "password", empleado.getPassword() );
        	jEmpleado.put( "nombre", empleado.getNombre() );
        	jEmpleado.put( "cellphone", empleado.getCellphone() );
        	
        	jEmpleados.put( jEmpleado );
        }

        jobject.put( "empleados", jEmpleados );
    }
	
	private void salvarClientes( Galeria galeria, JSONObject jobject )
    {
        JSONArray jClientes = new JSONArray( );
        for( Cliente cliente : Cliente.getClientes() )
        {
        	JSONObject jCliente = new JSONObject( );
        	
        	jCliente.put( "login", cliente.getLogin() );
        	jCliente.put( "password", cliente.getPassword() );
        	jCliente.put( "esComprador", cliente.isComprador() );
        	jCliente.put( "esPropietario", cliente.getLogin() );
        	jCliente.put( "cellphone", cliente.getCellphone() );
        	jCliente.put( "valorMaximoCompras", cliente.getValorMaximoCompras() );
        	jCliente.put( "esVerificado", cliente.isVerificado() );
        	
        	jClientes.put( jCliente );
        }

        jobject.put( "clientes", jClientes );
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