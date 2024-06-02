package galeria.modelo.centroventas;

import galeria.modelo.inventario.Pieza;

public class Pago extends Transaccion
{

	public static final int EFECTIVO = 0;
	public static final int TARJETA_CREDITO = 1;
	public static final int TARJETA_DEBITO = 2;
	public static final int CHEQUE = 3;

	private int metodoPago;
	/**
	 * La fecha se va a tomar en el formato DD/MM/AAAA
	 */
	private Fecha fecha;

	public Pago(Pieza pieza, long valor, String loginComprador, String loginVendedor, int tipo, int metodoPago, Fecha fecha)
	{
		super(pieza, valor, loginComprador, loginVendedor, tipo);

		this.metodoPago = metodoPago;
		this.fecha = fecha;

	}

	public int getMetodoPago() {
		return metodoPago;
	}

	public Fecha getFecha() {
		return fecha;
	}

}
