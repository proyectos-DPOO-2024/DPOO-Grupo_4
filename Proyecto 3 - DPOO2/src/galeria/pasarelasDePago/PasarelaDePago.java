package galeria.pasarelasDePago;

public abstract class PasarelaDePago 
{
	public static final int PAYPAL = 1;
	public static final int PAYU = 2;
	public static final int SIRE = 3;
	
	private String loginComprador;
	private long numeroTarjeta;
	private long valorPagar;
	private long numeroTransacción;
	private int pasarelaDePago;
	
	
	public PasarelaDePago(String loginComprador, long numeroTarjeta, long valorPagar, long numeroTransacción, int pasarelaDePago)
	{
		super();
		this.loginComprador = loginComprador;
		this.numeroTarjeta = numeroTarjeta;
		this.valorPagar = valorPagar;
		this.numeroTransacción = numeroTransacción;
		this.pasarelaDePago = pasarelaDePago;
	}


	public String getLoginComprador() {
		return loginComprador;
	}


	public void setLoginComprador(String loginComprador) {
		this.loginComprador = loginComprador;
	}


	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public void setNumeroTarjeta(long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}


	public long getValorPagar() {
		return valorPagar;
	}


	public void setValorPagar(long valorPagar) {
		this.valorPagar = valorPagar;
	}


	public long getNumeroTransacción() {
		return numeroTransacción;
	}


	public void setNumeroTransacción(long numeroTransacción) {
		this.numeroTransacción = numeroTransacción;
	}


	public int getPasarelaDePago() {
		return pasarelaDePago;
	}
	
	
}
