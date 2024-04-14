package Casa_subastas.modelo.Inventario;

public class Fotografia extends Pieza {

	private float Alto;
	private float Ancho;
	private Boolean Color;
	
	public Fotografia(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, Boolean color) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada, Pieza.FOTOGRAFIA);
		this.Alto = alto;
		this.Ancho = ancho;
		this.Color = color;
	}

	
}
