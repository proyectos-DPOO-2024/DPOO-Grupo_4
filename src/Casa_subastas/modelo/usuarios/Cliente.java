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
	private List<Pieza> propiedadesActuales;
	private List<Pieza> propiedadesPasadas;
	
	private static Map<String, Cliente> Clientes;
	
public static Cliente getCliente(String login) {
	
	return Clientes.get(login);
}
	
public Cliente (String login, String password, boolean esComprador, boolean esPropietario, int cellphone, boolean esVerificado, long valorMaximoCompras) {
		
		super(login, password, cellphone);
		
		if (esComprador) {
			otorgarPermisosComprador(valorMaximoCompras);
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
		
		verificado = esVerificado;
		
		Clientes.put(login, this);
	}

public void otorgarPermisosComprador (long valorMaximoCompras) {
	
	comprador = true;
	compras = new ArrayList<Pieza>();
	this.valorMaximoCompras = valorMaximoCompras;
}

public void otorgarPermisosPropietario() {
	
	propietario = true;
	propiedadesActuales = new ArrayList<Pieza>();
	propiedadesPasadas = new ArrayList<Pieza>();
}

public void verificar(long valorMaximoCompras) {
	
	this.valorMaximoCompras = valorMaximoCompras;
	this.verificado = true;
	
}

public void setValorMaximoCompras(long nuevoTope) {
	
	this.valorMaximoCompras = nuevoTope;
	
}

public void registrarCompra (Pieza piezaComprada, long valorPagado) {
	
	compras.add(piezaComprada);
	valorMaximoCompras = valorMaximoCompras - valorPagado;
	
}

public void registrarVenta (Pieza piezaVendida, Cliente nuevoPropietario) {
	
	propiedadesActuales.remove(piezaVendida);
	List<Cliente> Clientes = new ArrayList<Cliente>();
	Clientes.add(nuevoPropietario);
	propiedadesPasadas.add(piezaVendida);
	
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


public List getPropiedadesActuales() {
	return propiedadesActuales;
}

public List getPropiedadesPasadas() {
	return propiedadesPasadas;
}

}
