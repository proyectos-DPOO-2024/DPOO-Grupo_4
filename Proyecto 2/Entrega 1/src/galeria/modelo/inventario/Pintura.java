package galeria.modelo.inventario;

public class Pintura extends Pieza
{

	private String estilo;
	private float alto;
	private float ancho;

	
	public Pintura(String titulo, String nombreArtista, String loginPropietario,
			String estilo, float alto, float ancho)
	{
		super(titulo, nombreArtista, Pieza.PINTURA, loginPropietario);
		this.estilo = estilo;
		this.alto = alto;
		this.ancho = ancho;
	}
	
	//Este constructor copia otra pintura
	public Pintura(Pintura otraPintura) {
		super(otraPintura);
		estilo = otraPintura.estilo;
		alto = otraPintura.alto;
		ancho = otraPintura.ancho;
	}
	
	//Este constructor se debe usar únicamente en la carga y en las pruebas
	public Pintura(String titulo, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaDirecta, long precioInicioSubasta, long precioMinimoSubasta, 
			long precioUltimaVenta, boolean bloqueada, boolean enSubasta, boolean enBodega, boolean enPosesion, 
			String estilo, float alto, float ancho)
	{
		super(titulo, nombreArtista, Pieza.PINTURA, loginPropietario, fechaTerminoConsignacion, precioVentaDirecta,
				precioInicioSubasta, precioMinimoSubasta, precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion);
		this.estilo = estilo;
		this.alto = alto;
		this.ancho = ancho;
	}
	

	public float getAlto() {
		return alto;
	}

	public float getAncho() {
		return ancho;
	}

	public String getEstilo() {
		return estilo;
	}
}
