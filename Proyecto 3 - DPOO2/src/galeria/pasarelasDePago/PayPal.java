package galeria.pasarelasDePago;

public class PayPal extends PasarelaDePago
{

	public PayPal(String loginComprador, long numeroTarjeta, long valorPagar, long numeroTransacción)
	{
		super(loginComprador, numeroTarjeta, valorPagar, numeroTransacción, PasarelaDePago.PAYPAL);
	}
	
}
