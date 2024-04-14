package Casa_subastas.modelo.Inventario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Galeria {
	public List<Piezas> piezas;
	public List<Piezas> piezasVendidas;
	public Map<String, List<Piezas>> piezasPropietarios;
	public Map<String, List<Piezas>> piezasPasadospropietarios;

	public Galeria () {
		this.piezas = new LinkedList<>();
		this.piezasVendidas = new LinkedList<>();
		this.piezasPropietarios = new HashMap<>();
		this.piezasPasadospropietarios = new HashMap<>();
	}
	public void agregarPieza (Piezas pieza, String propietario) {
		this.piezas.add(pieza);
		 if (piezasPropietarios.containsKey(propietario)) {
			 List<Piezas> piezasDelCliente = piezasPropietarios.get(propietario);
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		 else {
			 List<Piezas> piezasDelCliente = new LinkedList<>();
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		this.piezasPropietarios.put(propietario, piezas);
	}
	public void asignarMaximo (Cliente propietario) {
		
	}
}
