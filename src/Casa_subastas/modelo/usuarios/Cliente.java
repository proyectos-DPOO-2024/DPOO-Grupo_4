package Casa_subastas.modelo.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Casa_subastas.modelo.Inventario.Pieza;

public class Cliente extends Usuario {
	
	
	
	private boolean verificado;
	private boolean comprador;
	private boolean propietario;
	private List<Pieza> compras;
	private long valorMaximoCompras;
	private long valorComprasActuales;
	private List<Pieza> propiedadesActuales;
	private Map<Pieza, List> propiedadesPasadas;
	
public Cliente (String login, String password, int cellphone, boolean esComprador, boolean esPropietario) {
		
		super(login, password, cellphone);
		

		
		if (esComprador) {
			otorgarPermisosComprador();
		}
		else {
			comprador = false;
		}
		
		
		if (esPropietario) {
			otorgarPermisosPropietario();
		}
		else {
			propietario = false;
		}
	}

public void otorgarPermisosComprador () {
	
	comprador = true;
	compras = new ArrayList<Pieza>();
	valorComprasActuales = 0;
}

public void otorgarPermisosPropietario() {
	
	propietario = true;
	propiedadesActuales = new ArrayList<Pieza>();
	propiedadesPasadas = new HashMap<Pieza, List>();
}

public void verificar(long valorMaximoCompras) {
	
	this.valorMaximoCompras = valorMaximoCompras;
	this.verificado = true;
	
}

public void extenderValorMaximoCompras(long nuevoTope) {
	
	this.valorMaximoCompras = nuevoTope;
	
}

public void registrarCompra (Pieza piezaComprada, long valorPagado) {
	
	compras.add(piezaComprada);
	valorComprasActuales = valorComprasActuales + valorPagado;
	
}

public void registrarVenta (Pieza piezaVendida, Cliente nuevoPropietario) {
	
	propiedadesActuales.remove(piezaVendida);
	List<Cliente> Clientes = new ArrayList<Cliente>();
	Clientes.add(nuevoPropietario);
	propiedadesPasadas.put(piezaVendida, Clientes);
	
}

public void actualizarHistorialPiezaPasada (Pieza pieza, Cliente nuevoPropietario) {
	
	propiedadesPasadas.get(pieza).add(nuevoPropietario);
	
}

public boolean isVerificado() {
	return verificado;
}

public boolean isComprador() {
	return comprador;
}

public boolean isPropietario() {
	return propietario;
}

public List getCompras() {
	return compras;
}

public long getValorMaximoCompras() {
	return valorMaximoCompras;
}

public long getValorComprasActuales() {
	return valorComprasActuales;
}

public List getPropiedadesActuales() {
	return propiedadesActuales;
}

public Set getPropiedadesPasadas() {
	return propiedadesPasadas.keySet();
}



}
