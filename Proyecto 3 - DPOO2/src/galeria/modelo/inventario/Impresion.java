package galeria.modelo.inventario;

public class Impresion extends Pieza
{

	private boolean original;
	private String metodoCreacion;
	private float alto;
	private float ancho;

	public Impresion(String titulo, String nombreArtista, String loginPropietario,
			boolean original, String metodocreacion, float alto, float ancho)
	{
		super(titulo, nombreArtista, Pieza.IMPRESION, loginPropietario);
		this.original = original;
		this.metodoCreacion = metodocreacion;
		this.alto = alto;
		this.ancho = ancho;
	}
	
	//Este constructor copia otra impresión
	public Impresion(Impresion otraImpresion) {
		super(otraImpresion);
		original = otraImpresion.original;
		metodoCreacion = otraImpresion.metodoCreacion;
		alto = otraImpresion.alto;
		ancho = otraImpresion.ancho;
	}
	
	//Este constructor se debe usar únicamente en la carga y en las pruebas
	public Impresion(String titulo, String nombreArtista, String loginPropietario, String fechaTerminoConsignacion,
			long precioVentaDirecta, long precioInicioSubasta, long precioMinimoSubasta, 
			long precioUltimaVenta, boolean bloqueada, boolean enSubasta, boolean enBodega, boolean enPosesion, 
			boolean original, String metodocreacion, float alto, float ancho)
	{
		super(titulo, nombreArtista, Pieza.IMPRESION, loginPropietario, fechaTerminoConsignacion, precioVentaDirecta,
				precioInicioSubasta, precioMinimoSubasta, precioUltimaVenta, bloqueada, enSubasta, enBodega, enPosesion);
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
