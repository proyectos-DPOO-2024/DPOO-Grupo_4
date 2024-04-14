package Casa_subastas.modelo.Inventario;

public class Video extends Pieza {

	private float Duracion;
	private Boolean Color;
	private double Memoria;
	
	public Video(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion, Boolean paraVentafijo,
			Boolean Bloqueada, Boolean Comprada, float duracion, Boolean color, double memoria) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada, Pieza.VIDEO);
		this.Duracion = duracion;
		this.Color = color;
		this.Memoria = memoria;
	}

}
