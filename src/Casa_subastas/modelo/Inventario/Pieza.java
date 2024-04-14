package Casa_subastas.modelo.Inventario;

import javax.swing.Spring;

import Casa_subastas.modelo.usuarios.Cliente;

public abstract class Pieza {

	public static final int PINTURA = 0;
	public static final int IMPRESIONES = 1;
	public static final int ESCULTURA = 2;
	public static final int FOTOGRAFIA = 3;
	public static final int VIDEO = 4;
	
	private String nombrePieza;
	private int tipo;
	private int precio;
	private String nombrePropietario;
	private int diasConsignacion;
	private Boolean paraVentafijo;
	private Boolean bloqueada;
	private Boolean comprada;
	
	public Pieza (String nombrePieza, int Precio, 
			String nombrePropietario, int diasConsignacion, 
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, int Tipo) {
		this.nombrePieza = nombrePieza;
		this.precio = Precio;
		this.nombrePropietario = nombrePropietario;
		this.diasConsignacion = diasConsignacion;
		this.paraVentafijo = paraVentafijo;
		this.tipo = Tipo;
		this.bloqueada = Bloqueada;
		this.comprada = Comprada;
	}
	public String getNombrepieza() {
		return this.nombrePieza;
	}
	public Cliente getPropietario() {
		return null;
	}
	public boolean getParaVentaValorFijo() {
		return this.paraVentafijo;
	}
	public boolean getBloqueada() {
		return this.bloqueada;
	}
	public Cliente cambiarPropietario(Cliente nuevoPropietario) {
		return null;
	}
	public void Comprado() {
		this.comprada = true;
	}
	public void Bloquear() {
		this.bloqueada = true;
	}
	public void desbloquear() {
		this.bloqueada = false;
	}
	public abstract int getCosto();
}
