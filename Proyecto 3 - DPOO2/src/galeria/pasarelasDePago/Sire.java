package galeria.pasarelasDePago;

public class Sire extends PasarelaDePago
{

	public Sire(String loginComprador, long numeroTarjeta, long valorPagar, long numeroTransacción)
	{
		super(loginComprador, numeroTarjeta, valorPagar, numeroTransacción, PasarelaDePago.SIRE);
	}
}
