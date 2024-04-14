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
	private int Tipo;
	private int Precio;
	private String nombrePropietario;
	private int diasConsignacion;
	private Boolean paraVentafijo;
	private Boolean Bloqueada;
	private Boolean Comprada;
	
	public Pieza (String nombrePieza, int Precio, 
			String nombrePropietario, int diasConsignacion, 
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada, int Tipo) {
		this.nombrePieza = nombrePieza;
		this.Precio = Precio;
		this.nombrePropietario = nombrePropietario;
		this.diasConsignacion = diasConsignacion;
		this.paraVentafijo = paraVentafijo;
		this.Bloqueada = Bloqueada;
		this.Comprada = Comprada;
		this.Tipo = Tipo;
	}
	public String getNombrepieza() {
		return this.nombrePieza;
	}
	public Cliente getPropietario() {
		return null;
	}
	public Cliente cambiarPropietario(Cliente nuevoPropietario) {
		return null;
	}
	public void Comprado() {
		this.Comprada = true;
	}
	public void Bloqueado() {
		this.Bloqueada = true;
	}
}
