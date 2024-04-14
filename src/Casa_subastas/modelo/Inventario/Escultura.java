package Casa_subastas.modelo.Inventario;

public class Escultura extends Pieza {

	private float Alto;
	private float Ancho;
	private float Profundo;
	private String materialConstruccion;
	public Escultura(String nombrePieza, int Precio, String nombrePropietario, int diasConsignacion,
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, float alto, float ancho, float profundo, String materialconstruccion) {
		super(nombrePieza, Precio, nombrePropietario, diasConsignacion, paraVentafijo, Bloqueada, Comprada, Pieza.ESCULTURA);
		this.Alto = alto;
		this.Ancho = ancho;
		this.Profundo = profundo;
		this.materialConstruccion= materialconstruccion;
	}

}
