package galeria.modelo.centroventas;

import galeria.modelo.inventario.Pieza;

public class Oferta extends Transaccion {
	
	
	public Oferta(Pieza pieza, long valor, String loginComprador, String loginVendedor, int tipo) {
		super(pieza, valor, loginComprador, loginVendedor, tipo);
	}
	
	
}

