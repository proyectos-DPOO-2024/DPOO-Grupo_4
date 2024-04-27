package galeria.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
				
				Map<String, Pieza> piezas = galeria.getMapaPiezas();
			    List<Pieza> piezasPropietario  = new ArrayList<>(piezas.values());

				
				Iterator <Pieza> it = piezasPropietario.iterator();
				
				while(it.hasNext()) 
				{
					JSONObject jPieza = new JSONObject( );
		        	
					Pieza pieza = it.next();
					
		        	jPieza.put( "tipo", pieza.getTipo() );
		        	jPieza.put( "nombrePieza", pieza.getNombrepieza() );
		        	jPieza.put( "precio", pieza.getCosto());
		        	jPieza.put( "propietario", loginPropietario);
		        	jPieza.put( "diasConsignacion", pieza.getDiasConsignacion());
		        	jPieza.put( "paraVentaFijo", pieza.getParaVentaValorFijo());
		        	jPieza.put( "paraVentaFijo", pieza.getBloqueada());
		        	jPieza.put( "paraVentaFijo", pieza.isComprada());
		        	
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
