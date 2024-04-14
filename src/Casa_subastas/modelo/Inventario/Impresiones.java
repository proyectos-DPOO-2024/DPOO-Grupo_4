package Casa_subastas.modelo.Inventario;

public class Impresiones extends Pieza {

	private String Original;
	private String metodoCreacion;
	private float Alto;
	private float Ancho;

	public Impresiones(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, String original, String metodocreacion, float alto, float ancho) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada);
		this.Original = original;
		this.metodoCreacion = metodocreacion;
		this.Alto = alto;
		this.Ancho = ancho;
	}

}
