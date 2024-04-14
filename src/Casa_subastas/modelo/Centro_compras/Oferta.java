package Casa_subastas.modelo.Centro_compras;

import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;

public class Oferta {

	///atributos\\\
	private Pieza pieza;
	
	private Cliente Comprador;
	
	private int precio;
	
	private boolean ofertaVerificada;
	
	///metodos\\\
	
	//constructor\\

	public Oferta(Pieza pieza, int precio, Cliente comprador) {
		super();
		this.pieza = pieza;
		this.precio = precio;
		this.Comprador = comprador;
		this.ofertaVerificada = false;
	}
	
	public Oferta(Pieza pieza, Cliente comprador, boolean ofertaVerificada) {
		super();
		this.pieza = pieza;
		this.precio = pieza.getCosto();
		this.Comprador = comprador;
		this.ofertaVerificada = false;
	}
	
	///Metodos importantes\\\
	
	public void confirmarOferta() {
		ofertaVerificada = true;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public Cliente getComprador() {
		return Comprador;
	}
	
	public boolean esConfirmada()
	{
		return ofertaVerificada;
	}
	
	
	
	
	
}
