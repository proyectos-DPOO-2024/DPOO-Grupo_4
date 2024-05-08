package galeria.modelo.inventario;

public class Fotografia extends Pieza
{

	private float alto;
	private float ancho;
	private Boolean color;

	public Fotografia(String nombrePieza, String nombreArtista, String loginPropietario,
			float alto, float ancho, Boolean color)
	{
		super(nombrePieza, nombreArtista, Pieza.FOTOGRAFIA, loginPropietario);
		this.alto = alto;
		this.ancho = ancho;
		this.color = color;
	}
	
	//Este constructor copia otra fotografía
	public Fotografia(Fotografia otraFotografia) {
		super(otraFotografia);
		alto = otraFotografia.alto;
		ancho = otraFotografia.ancho;
		color = otraFotografia.color;
	}
	
	//Este constructor se debe usar únicamente en la carga y en las pruebas
	public Fotografia(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaDirecta, long precioInicioSubasta, long precioMinimoSubasta, 
			long precioUltimaVenta, boolean bloqueada, boolean enSubasta, boolean enBodega, boolean enPosesion, 
			float alto, float ancho, Boolean color)
	{
		super(nombrePieza, nombreArtista, Pieza.FOTOGRAFIA, loginPropietario, fechaTerminoConsignacion, precioVentaDirecta,
				precioInicioSubasta, precioMinimoSubasta, precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion);
		this.alto = alto;
		this.ancho = ancho;
		this.color = color;
	}
	

	public float getAlto() {
		return alto;
	}

	public float getAncho() {
		return ancho;
	}

	public Boolean getColor() {
		return color;
	}

}
