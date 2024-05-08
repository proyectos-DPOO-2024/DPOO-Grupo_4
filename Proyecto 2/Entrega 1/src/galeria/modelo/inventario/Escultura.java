package galeria.modelo.inventario;

public class Escultura extends Pieza
{

	private String materialConstruccion;
	private float alto;
	private float ancho;
	private float profundidad;

	public Escultura(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta, String materialconstruccion,
			float alto, float ancho, float profundidad)
	{
		super(nombrePieza, nombreArtista, Pieza.ESCULTURA, loginPropietario, fechaTerminoConsignacion, precioVentaFija,
				precioInicioSubasta, precioMinimoSubasta);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.materialConstruccion = materialconstruccion;
	}
	
	//Este constructor se debe usar únicamente en la carga y en las pruebas
	public Escultura(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaDirecta, long precioInicioSubasta, long precioMinimoSubasta, 
			long precioUltimaVenta, boolean bloqueada, boolean enSubasta, boolean enBodega, boolean enPosesion, 
			String materialconstruccion, float alto, float ancho, float profundidad)
	{
		super(nombrePieza, nombreArtista, Pieza.ESCULTURA, loginPropietario, fechaTerminoConsignacion, precioVentaDirecta,
				precioInicioSubasta, precioMinimoSubasta, precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
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
		return profundidad;
	}

}
