package galeria.modelo.inventario;

public class Pintura extends Pieza
{

	private String estilo;
	private float alto;
	private float ancho;

	public Pintura(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta, String estilo, float alto,
			float ancho)
	{
		super(nombrePieza, nombreArtista, Pieza.PINTURA, loginPropietario, fechaTerminoConsignacion, precioVentaFija,
				precioInicioSubasta, precioMinimoSubasta);
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
