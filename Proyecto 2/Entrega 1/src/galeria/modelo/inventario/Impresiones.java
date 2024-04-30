package galeria.modelo.inventario;

public class Impresiones extends Pieza {

	private boolean original;
	private String metodoCreacion;
	private float alto;
	private float ancho;

	
	public Impresiones(String nombrePieza, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion, long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta,
			boolean original, String metodocreacion, float alto, float ancho) {
		super(nombrePieza, nombreArtista, Pieza.IMPRESION, loginPropietario, fechaTerminoConsignacion, precioVentaFija, precioInicioSubasta, precioMinimoSubasta);
		this.original = original;
		this.metodoCreacion = metodocreacion;
		this.alto = alto;
		this.ancho = ancho;
	}

	
	
	public boolean isOriginal() {
		return original;
	}
	public String getMetodoCreacion() {
		return metodoCreacion;
	}
	public float getAlto() {
		return alto;
	}
	public float getAncho() {
		return ancho;
	}
	
	
}
