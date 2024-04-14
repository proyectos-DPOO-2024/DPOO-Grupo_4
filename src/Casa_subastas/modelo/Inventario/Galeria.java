package Casa_subastas.modelo.Inventario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Casa_subastas.modelo.Centro_compras.Oferta;
import Casa_subastas.modelo.usuarios.Cliente;


public class Galeria 
{
	public List<Pieza> piezas;
	public List<Pieza> piezasVendidas;
	public Map<String, List<Pieza>> piezasPropietarios;
	public Map<String, List<Pieza>> piezasPasadospropietarios;
	public Map<String, Pieza> mapaPiezas;
	public Map<String, Oferta> mapaOfertas;
	public List<Pago> pagos;

	public Galeria () {
		this.piezas = new LinkedList<>();
		this.piezasVendidas = new LinkedList<>();
		this.piezasPropietarios = new HashMap<>();
		this.piezasPasadospropietarios = new HashMap<>();
		this.mapaPiezas = new HashMap<>();
		this.mapaOfertas = new HashMap<>();
		this.pagos = new ArrayList<>();
	}
	
	
	public void agregarPieza (Pieza pieza, String propietario) {
		this.piezas.add(pieza);
		this.mapaPiezas.put(pieza.getNombrepieza(), pieza);
		 if (piezasPropietarios.containsKey(propietario)) {
			 List<Pieza> piezasDelCliente = piezasPropietarios.get(propietario);
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		 else {
			 List<Pieza> piezasDelCliente = new LinkedList<>();
			 piezasDelCliente.add(pieza);
			 piezasPropietarios.put(propietario, piezasDelCliente);
		 }
		this.piezasPropietarios.put(propietario, piezas);
	}
	
	public void asignarMaximo (Cliente propietario, int valor) {
		propietario.setValorMaximoCompras(valor);
	}
	
	/*
	 * Crea una oferta de valor fijo
	 */
	
	public void crearOfertaValorFijo(Cliente cliente, Pieza pieza){
		Oferta oferta = new Oferta(pieza, cliente, false);
		this.mapaOfertas.put(pieza.getNombrepieza(), oferta);
	}
	
	/*
	 * Verifica una oferta de valor fijo
	 */
	public void verificarOfertaValorFijo(String nombrePieza)
	{
		Oferta oferta = mapaOfertas.get(nombrePieza);
		oferta.confirmarOferta();
	}
		
	/*
	 * realiza el pago dada una oferta ya verificada
	 */
	
	public void realizarPago(String metodoPago, String nombrePieza) {
		Oferta oferta = mapaOfertas.get(nombrePieza);
		
		
	}
	
	public void cambiarPropietarioPieza() {
		
	}
	
}
	
	
	
	
	
