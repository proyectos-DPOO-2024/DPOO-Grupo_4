package galeria.modelo.centroventas;

import java.util.Stack;

public class Subasta
{

	private String tituloPiezaSubastada;
	private long valorMinimo;
	private long valorActual;

	private Stack<Oferta> trazaOfertas;

	public Subasta(String tituloPiezaSubastada, long valorInicial, long valorMinimo)
	{
		this.tituloPiezaSubastada = tituloPiezaSubastada;
		valorActual = valorInicial;
		this.valorMinimo = valorMinimo;
	}

	/*
	 * Esta función devuelve la oferta ganadora.
	 */
	public Oferta finalizarSubasta() {
		return trazaOfertas.pop();
	}

	/*
	 * Esta función añade una nueva oferta a la cima de la pila de ofertas. Además,
	 * se actualiza el valor actual.
	 */
	public void añadirOferta(Oferta oferta) {
		trazaOfertas.push(oferta);
		valorActual = oferta.getValor();
	}

	public String getTituloPiezaSubastada() {
		return tituloPiezaSubastada;
	}

	public long getValorMinimo() {
		return valorMinimo;
	}

	public long getValorActual() {
		return valorActual;
	}

	public Stack<Oferta> getTrazaOfertas() {
		return trazaOfertas;
	}

}