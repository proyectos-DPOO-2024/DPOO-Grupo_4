/**
 * 
 */
package galeria.modelo.centroventas;

import galeria.modelo.inventario.Pieza;

/**
 * 
 */
public abstract class Transaccion {
	
	public static final int VENTA_DIRECTA = 0;
	public static final int SUBASTA = 1;
	
	private Pieza pieza;
	private long valor;
	private String loginComprador;
	private String loginVendedor;
	private int tipo;
	
	
	public Transaccion(Pieza pieza, long valor, String loginComprador, String loginVendedor, int tipo) {
		
		this.pieza = pieza;
		this.valor = valor;
		this.loginComprador = loginComprador;
		this.loginVendedor = loginVendedor;
		this.tipo = tipo;
	}
	
	
	public Pieza getPieza() {
		return pieza;
	}
	public long getValor() {
		return valor;
	}
	public String getLoginComprador() {
		return loginComprador;
	}
	public String getLoginVendedor() {
		return loginVendedor;
	}
	public int getTipo() {
		return tipo;
	}

}
