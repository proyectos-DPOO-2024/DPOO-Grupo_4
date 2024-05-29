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
	
	//Este constructor debe utilizarse únicamente para el constructor o pruebas
	public Subasta(String tituloPiezaSubastada, long valorActual, long valorMinimo, Stack<Oferta> trazaOfertas)
	{
		this.tituloPiezaSubastada = tituloPiezaSubastada;
		this.valorActual = valorActual;
		this.valorMinimo = valorMinimo;
		this.trazaOfertas = trazaOfertas;
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
	
	public void serValorActual(long valor) {
		valorActual = valor;
	}

	public Stack<Oferta> getTrazaOfertas() {
		return trazaOfertas;
	}

}