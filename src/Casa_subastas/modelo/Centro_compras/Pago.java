package Casa_subastas.modelo.Centro_compras;

import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;

public class Pago {
	
	///atributos\\\
	private String metodoPago;
	
	private long valorPagado;
	
	private Cliente comprador;
	
	private Pieza pieza;

	public Pago(String metodoPago, long valorPagado, Cliente comprador, Pieza pieza) {
		super();
		this.metodoPago = metodoPago;
		this.valorPagado = valorPagado;
		this.comprador = comprador;
		this.pieza = pieza;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public long getValorPagado() {
		return valorPagado;
	}

	public Cliente getComprador() {
		return comprador;
	}

	public Pieza getPieza() {
		return pieza;
	}
	
	///\\\
	
	
	
	
	

}
