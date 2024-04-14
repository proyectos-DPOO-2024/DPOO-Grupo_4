package Casa_subastas.modelo.Inventario;

import javax.swing.Spring;

public abstract class Pieza {

	private String nombrePieza;
	private int Precio;
	private String nombrePropietario;
	private int diasConsignacion;
	private Boolean paraVentafijo;
	private Boolean Bloqueada;
	private Boolean Comprada;
	
	public Pieza (String nombrePieza, int Precio, 
			String nombrePropietario, int diasConsignacion, 
			Boolean paraVentafijo, Boolean Bloqueada, Boolean Comprada) {
		this.nombrePieza = nombrePieza;
		this.Precio = Precio;
		this.nombrePropietario = nombrePropietario;
		this.diasConsignacion = diasConsignacion;
		this.paraVentafijo = paraVentafijo;
		this.Bloqueada = Bloqueada;
		this.Comprada = Comprada;
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
