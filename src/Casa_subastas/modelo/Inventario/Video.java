package Casa_subastas.modelo.Inventario;

public class Video extends Pieza {

	private float Duracion;
	private Boolean Color;
	private Double Memoria;
	
	public Video(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion, Boolean paraVentafijo,
			Boolean Bloqueada, Boolean Comprada, float duracion, Boolean color, Double memoria) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada);
		this.Duracion = duracion;
		this.Color = color;
		this.Memoria = memoria;
	}

}
