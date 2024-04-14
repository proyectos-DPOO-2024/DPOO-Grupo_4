package Casa_subastas.modelo.Centro_compras;

import java.util.LinkedList;
import java.util.List;

import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;

public class Subasta {

	///Atributos\\\
	
	private int valorMinimo;
	
	private int valorInicial;
	
	private Pieza piezaSubastar;
	
	private List<Oferta> trazaOfertas;
	
	private Boolean finalizada;
	
	private Cliente compradorGanador;
	
	///Metodos\\\
	
	///constructor\\\

	public Subasta(int valorMinimo, int valorInicial, Pieza piezaSubastar)
	{
		this.valorMinimo = valorMinimo;
		this.valorInicial = valorInicial;
		this.piezaSubastar = piezaSubastar;
		this.trazaOfertas = new LinkedList<Oferta>();
		this.finalizada = false;
		this.compradorGanador = null;
	}
	
	///Getters and setters\\\

	public Boolean getFinalizada() {
		return finalizada;
	}

	public Cliente getCompradorGanador() {
		return compradorGanador;
	}

	public void setCompradorGanador(Cliente compradorGanador) {
		this.compradorGanador = compradorGanador;
	}

	public int getValorMinimo() {
		return valorMinimo;
	}

	public int getValorInicial() {
		return valorInicial;
	}

	public Pieza getPiezaSubastar() {
		return piezaSubastar;
	}
	
	///Metodos importantes\\\
	
	/*
	 * Esta función finaliza la subasta, y devuelve la oferta ganadora.
	 */
	public Oferta finalizarSubasta() { 
		this.finalizada = true;
		Oferta ofertaGanadora = trazaOfertas.get(0);
		return ofertaGanadora;
		
	}
	
	public void añadirOferta(Oferta oferta) {
		trazaOfertas.add(0, oferta);
	}
	
	
	
	

}