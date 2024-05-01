package galeria.modelo.inventario;

public class Fotografia extends Pieza
{

	private float alto;
	private float ancho;
	private Boolean color;

	public Fotografia(String nombrePieza, String nombreArtista, String loginPropietario,
			String fechaTerminoConsignacion, long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta,
			float alto, float ancho, Boolean color)
	{
		super(nombrePieza, nombreArtista, Pieza.FOTOGRAFIA, loginPropietario, fechaTerminoConsignacion, precioVentaFija,
				precioInicioSubasta, precioMinimoSubasta);
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
