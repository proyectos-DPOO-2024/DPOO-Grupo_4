package galeria.modelo.inventario;

public class Video extends Pieza {

	private float duracion;
	private Boolean color;
	private double memoria;
	
	
	public Video(String nombrePieza, String nombrePropietario, String fechaTerminoConsignacion, long precioVentaFija, long precioInicioSubasta, long precioMinimoSubasta,
			float duracion, Boolean color, double memoria) {
		super(nombrePieza, Pieza.VIDEO, nombrePropietario, fechaTerminoConsignacion, precioVentaFija, precioInicioSubasta, precioMinimoSubasta);
		this.duracion = duracion;
		this.color = color;
		this.memoria = memoria;
	}
	

	public float getDuracion() {
		return duracion;
	}

	public Boolean getColor() {
		return color;
	}

	public double getMemoria() {
		return memoria;
	}

	
}
