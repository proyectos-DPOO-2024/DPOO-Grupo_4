package galeria.modelo.inventario;

public class Escultura extends Pieza
{

	private String materialConstruccion;
	private float alto;
	private float ancho;
	private float profundo;

	public Escultura(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta, String materialconstruccion,
			float alto, float ancho, float profundo)
	{
		super(nombrePieza, nombreArtista, Pieza.ESCULTURA, loginPropietario, fechaTerminoConsignacion, precioVentaFija,
				precioInicioSubasta, precioMinimoSubasta);
		this.alto = alto;
		this.ancho = ancho;
		this.profundo = profundo;
		this.materialConstruccion = materialconstruccion;
	}

	public String getMaterialConstruccion() {
		return materialConstruccion;
	}

	public float getAlto() {
		return alto;
	}

	public float getAncho() {
		return ancho;
	}

	public float getProfundo() {
		return profundo;
	}

}
