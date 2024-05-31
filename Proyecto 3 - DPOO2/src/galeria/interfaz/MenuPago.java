package galeria.interfaz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


import galeria.pasarelasDePago.PasarelaDePago;
import galeria.pasarelasDePago.PayPal;
import galeria.pasarelasDePago.PayU;
import galeria.pasarelasDePago.Sire;

public class MenuPago extends MenuBasico 
{
	
	private PasarelaDePago miPasarela;

	
	public MenuPago(String pasarela)
	{
		this.mostrarMenuPago(pasarela);
	}

	private void mostrarMenuPago(String pasarela) 
	{		
		
		String loginComprador = super.pedirCadenaAlUsuario("La pasarela "+pasarela+" necesita que ingrese el login del comprador");
		long numeroTarjeta = super.pedirLongAlUsuario("La pasarela "+pasarela+" necesita que ingrese el numero de tarjeta");
		long valorPagar = super.pedirLongAlUsuario("La pasarela "+pasarela+" necesita que inserte el valor a pagar por el Usuario");
		int pasarelaDePagoNumero = 1;
		//String pasarelaNombre = esxtraerNombrePasarela(pasarela);
		long numeroTransaccion = ObtenerNumeroTransaccion(pasarelaDePagoNumero);
		PasarelaDePago pasarelaObjeto = getPasarela(pasarela,loginComprador, numeroTarjeta, valorPagar, numeroTransaccion, pasarelaDePagoNumero);
		salvarTransaccion(pasarelaObjeto);
	}

	private void salvarTransaccion(PasarelaDePago pasarelaObjeto) {
		long numeroTransaccion= pasarelaObjeto.getNumeroTransacción();
		long numeroTarjeta = pasarelaObjeto.getNumeroTarjeta();
		long valorPagar = pasarelaObjeto.getValorPagar();
		int pasarelaDePagoNumero = pasarelaObjeto.getPasarelaDePago();
		String loginComprador = pasarelaObjeto.getLoginComprador();
		
		String archivo = "transacciones.txt"; // Nombre del archivo

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) { // 'true' para agregar al final del archivo
            writer.write("Número de Transacción: " + numeroTransaccion);
            writer.newLine();
            writer.write("Número de Tarjeta: " + numeroTarjeta);
            writer.newLine();
            writer.write("Valor a Pagar: " + valorPagar);
            writer.newLine();
            writer.write("Pasarela de Pago: " + pasarelaDePagoNumero);
            writer.newLine();
            writer.write("Login del Comprador: " + loginComprador);
            writer.newLine();
            writer.newLine(); // Línea en blanco para separar transacciones

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	private long ObtenerNumeroTransaccion(int pasarelaDePagoNumero) {
		long numeroTransaccion = (long) (pasarelaDePagoNumero*1000 + Math.random()*1000);
		return numeroTransaccion;
	}

	private PasarelaDePago getPasarela(String pasarelaNombre, String loginComprador, long numeroTarjeta, long valorPagar, long numeroTransaccion ,int pasarelaDePagoNumero) {

		
    	Class clase= null;
    	try {
			clase = Class.forName(pasarelaNombre);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Constructor<PasarelaDePago> constructor = null;
		try {
			constructor =  clase.getDeclaredConstructor(String.class, long.class, long.class, long.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			miPasarela = constructor.newInstance(loginComprador, numeroTarjeta , valorPagar, numeroTransaccion);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return miPasarela;
	}

    public String extraerNombrePasarela(String linea) {
        // Divide la cadena por el carácter '.'
        String[] partes = linea.split("\\.");
        // Devuelve la última parte, que es el nombre de la pasarela
        return partes[partes.length - 1];
     }
    
}
