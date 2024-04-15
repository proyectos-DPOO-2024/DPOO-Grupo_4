package Casa_subastas.modelo.Centro_compras;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Casa_subastas.modelo.Inventario.Pieza;
import Casa_subastas.modelo.usuarios.Cliente;

public class Subasta {

	///Atributos\\\
	
	private long valorMinimo;
	
	private long valorInicial;
	
	private long valorActual;
	
	private Pieza piezaSubastar;
	
	private List<Oferta> trazaOfertas;
	
	private Boolean finalizada;
	
	private Cliente compradorGanador;
	
	///Metodos\\\
	
	///Constructores\\\

	/*
	 * Este constructor se usa cuando se crea una nueva subasta desde galería
	*/
	
	public Subasta(long valorMinimo, long valorActual, Pieza piezaSubastar)
	{
		this.valorMinimo = valorMinimo;
		this.valorInicial = valorActual;
		this.valorActual = valorActual;
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

	public long getValorMinimo() {
		return valorMinimo;
	}
	
	public long getValorInicial() {
		return valorInicial;
	}

	public long getValorActual() {
		return valorActual;
	}

	public void setValorActual(long valor){
		valorActual = valor;
	}

	public Pieza getPiezaSubastar() {
		return piezaSubastar;
	}
	
	public List<Oferta> getTrazaOfertas() {
		return trazaOfertas;
	}
	
	///Metodos importantes\\\
	
	/*
	 * Esta función finaliza la subasta, y devuelve la oferta ganadora.
	 */
	public Oferta finalizarSubasta() { 
		this.finalizada = true;
		Oferta ofertaGanadora = trazaOfertas.getLast();
		return ofertaGanadora;
		
	}
	
	public void añadirOferta(Oferta oferta) {
		trazaOfertas.add(oferta);
	}
	
	
	

}
