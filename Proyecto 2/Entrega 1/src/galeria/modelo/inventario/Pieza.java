/**
 * 
 */
package galeria.modelo.inventario;

/**
 * Esta clase cobija los elementos comunes a todos los tipos de pieza Además, se
 * le asigna un entero a cada tipo de pieza que ayuda la hora de cargar y
 * guardar las piezas.
 */
public abstract class Pieza
{

	public static final int PINTURA = 0;
	public static final int IMPRESION = 1;
	public static final int ESCULTURA = 2;
	public static final int FOTOGRAFIA = 3;
	public static final int VIDEO = 4;

	/**
	 * Si una pieza tiene precioVentaDirecta = -1, quiere decir que no está
	 * disponible para venta directa. Si una pieza está bloqueada, quiere decir que
	 * hay una oferta directa o, en caso de que esté siendo subastada, una nueva
	 * puja siendo procesada. El indicador enPosesion indica si una pieza está
	 * actualmente en posesión de la galería o fue vendida (pieza pasada).
	 */
	private String titulo;
	private int tipo;
	private String nombreArtista;
	private String loginPropietario;
	private String fechaTerminoConsignacion;
	private long precioVentaDirecta;
	private long precioInicioSubasta;
	private long precioMinimoSubasta;
	private long precioUltimaVenta;
	private boolean bloqueada;
	private boolean enSubasta;
	private boolean enBodega;
	private boolean enPosesion;

	public Pieza(String titulo, String nombreArtista, int tipo, String loginPropietario,
			String fechaTerminoConsignacion, long precioVentaDirecta, long precioInicioSubasta,
			long precioMinimoSubasta)
	{

		this.titulo = titulo;
		this.tipo = tipo;
		this.nombreArtista = nombreArtista;
		this.loginPropietario = loginPropietario;
		this.fechaTerminoConsignacion = fechaTerminoConsignacion;
		this.precioVentaDirecta = precioVentaDirecta;
		this.precioInicioSubasta = precioInicioSubasta;
		this.precioMinimoSubasta = precioMinimoSubasta;
		precioUltimaVenta = -1;
		bloqueada = false;
		enSubasta = false;
		enBodega = true;
		enPosesion = true;
	}
	
	
	public Pieza(String titulo, String nombreArtista, int tipo, String loginPropietario,
			String fechaTerminoConsignacion, long precioVentaDirecta, long precioInicioSubasta,
			long precioMinimoSubasta, long precioUltimaVenta, boolean bloqueada, boolean enSubasta, boolean enBodega, boolean enPosesion)
	{

		this.titulo = titulo;
		this.tipo = tipo;
		this.nombreArtista = nombreArtista;
		this.loginPropietario = loginPropietario;
		this.fechaTerminoConsignacion = fechaTerminoConsignacion;
		this.precioVentaDirecta = precioVentaDirecta;
		this.precioInicioSubasta = precioInicioSubasta;
		this.precioMinimoSubasta = precioMinimoSubasta;
		this.precioUltimaVenta = precioUltimaVenta;
		this.bloqueada = bloqueada;
		this.enSubasta = enSubasta;
		this.enBodega = enBodega;
		this.enPosesion = enPosesion;
	}

	public void cambiarPropietario(String loginNuevoPropietario) {
		loginPropietario = loginNuevoPropietario;
	}

	public void bloquear() {
		bloqueada = true;
	}

	public void desbloquear() {
		bloqueada = false;
	}

	public void guardarEnBodega() {
		enBodega = true;
	}

	public void exhibir() {
		enBodega = false;
	}

	public void abrirSubasta() {
		enSubasta = true;
	}

	public void cerrarSubasta() {
		enSubasta = false;
	}

	public void cambiarEstadoPosesion() {
		if (enPosesion)
			enPosesion = false;
		else
			enPosesion = true;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getTipo() {
		return tipo;
	}

	public String getNombreArtista() {
		return nombreArtista;
	}

	public String getLoginPropietario() {
		return loginPropietario;
	}

	public String getFechaTerminoConsignacion() {
		return fechaTerminoConsignacion;
	}

	public long getPrecioVentaDirecta() {
		return precioVentaDirecta;
	}

	public long getPrecioInicioSubasta() {
		return precioInicioSubasta;
	}

	public long getPrecioMinimoSubasta() {
		return precioMinimoSubasta;
	}

	public long getPrecioUltimaVenta() {
		return precioUltimaVenta;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public boolean isEnSubasta() {
		return enSubasta;
	}

	public boolean isEnBodega() {
		return enBodega;
	}

	public boolean isEnPosesion() {
		return enPosesion;
	}

	public void setFechaTerminoConsignacion(String fechaTerminoConsignacion) {
		this.fechaTerminoConsignacion = fechaTerminoConsignacion;
	}

	public void setPrecioVentaDirecta(long precioVentaDirecta) {
		this.precioVentaDirecta = precioVentaDirecta;
	}

	public void setPrecioInicioSubasta(long precioInicioSubasta) {
		this.precioInicioSubasta = precioInicioSubasta;
	}

	public void setPrecioMinimoSubasta(long precioMinimoSubasta) {
		this.precioMinimoSubasta = precioMinimoSubasta;
	}

	public void setPrecioUltimaVenta(long precioUltimaVenta) {
		this.precioUltimaVenta = precioUltimaVenta;
	}

}
