package galeria.pasarelasDePago;

public class PayU extends PasarelaDePago
{

	public PayU(String loginComprador, long numeroTarjeta, long valorPagar, long numeroTransacción)
	{
		super(loginComprador, numeroTarjeta, valorPagar, numeroTransacción, PasarelaDePago.PAYU);
	}

}
