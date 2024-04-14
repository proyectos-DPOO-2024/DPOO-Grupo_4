package Casa_subastas.modelo.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Casa_subastas.modelo.Inventario.Piezas;

public class Cliente extends Usuario {
	
	
	
	private boolean verificado;
	private boolean comprador;
	private boolean propietario;
	private List<Piezas> compras;
	private long valorMaximoCompras;
	private long valorComprasActuales;
	private List<Piezas> propiedadesActuales;
	private Map<Piezas, List> propiedadesPasadas;
	
public Cliente (String login, String password, boolean esComprador, boolean esPropietario) {
		
		super(login, password);
		

		
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
	compras = new ArrayList<Piezas>();
	valorComprasActuales = 0;
}

public void otorgarPermisosPropietario() {
	
	propietario = true;
	propiedadesActuales = new ArrayList<Piezas>();
	propiedadesPasadas = new HashMap<Piezas, List>();
}

public void verificar(long valorMaximoCompras) {
	
	this.valorMaximoCompras = valorMaximoCompras;
	this.verificado = true;
	
}

public void extenderValorMaximoCompras(long nuevoTope) {
	
	this.valorMaximoCompras = nuevoTope;
	
}

public void registrarCompra (Piezas piezaComprada, long valorPagado) {
	
	compras.add(piezaComprada);
	valorComprasActuales = valorComprasActuales + valorPagado;
	
}

public void registrarVenta (Piezas piezaVendida, Cliente nuevoPropietario) {
	
	propiedadesActuales.remove(piezaVendida);
	List<Cliente> Clientes = new ArrayList<Cliente>();
	Clientes.add(nuevoPropietario);
	propiedadesPasadas.put(piezaVendida, Clientes);
	
}

public void actualizarHistorialPiezaPasada (Piezas pieza, Cliente nuevoPropietario) {
	
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
