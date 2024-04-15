package Casa_subastas.Persistencias;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Casa_subastas.modelo.Inventario.Galeria;
import Casa_subastas.modelo.Inventario.Escultura;
import Casa_subastas.modelo.Inventario.Fotografia;
import Casa_subastas.modelo.Inventario.Impresiones;
import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.Inventario.Pintura;
import Casa_subastas.modelo.Inventario.Video;
import Casa_subastas.modelo.usuarios.Cliente;
import Casa_subastas.modelo.usuarios.Empleado;

public class SalvadorPiezas {

	protected void salvarPiezas(Galeria galeria, JSONObject jobject) throws Exception {
		
		JSONArray jPiezas = new JSONArray( );
		
		for( Cliente cliente : Cliente.getClientes() )
        {
			if (cliente.isPropietario())
			{
				String loginPropietario = cliente.getLogin();
				
				List<Pieza> piezasPropietario = galeria.getPiezas();
				
				Iterator <Pieza> it = piezasPropietario.iterator();
				
				while(it.hasNext()) 
				{
					JSONObject jPieza = new JSONObject( );
		        	
		        	jPieza.put( "tipo", it.next().getTipo() );
		        	jPieza.put( "nombrePieza", it.next().getNombrepieza() );
		        	jPieza.put( "precio", it.next().getCosto());
		        	jPieza.put( "propietario", loginPropietario);
		        	jPieza.put( "diasConsignacion", it.next().getDiasConsignacion());
		        	jPieza.put( "paraVentaFijo", it.next().getParaVentaValorFijo());
		        	jPieza.put( "paraVentaFijo", it.next().getBloqueada());
		        	jPieza.put( "paraVentaFijo", it.next().isComprada());
		        	
		        	/*
		        	if ( it.next().getTipo() == Pieza.ESCULTURA) {
		        		jPieza.put( "alto", it.next().);
		        	}
		        	*/
		        	
		        	jPiezas.put( jPieza );
				}
				
			}
        }
		jobject.put( "piezas", jPiezas );
	}
}
