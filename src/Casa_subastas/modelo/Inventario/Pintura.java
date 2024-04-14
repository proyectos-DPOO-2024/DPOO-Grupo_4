package Casa_subastas.modelo.Inventario;

public class Pintura extends Pieza {


	private float Alto;
	private float Ancho;
	private String Estilo;
	
	public Pintura(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, String estilo) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada);
		this.Alto = alto;
		this.Ancho = ancho;
		this.Estilo = estilo;
	}
}
