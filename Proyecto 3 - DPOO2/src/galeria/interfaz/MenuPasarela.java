package galeria.interfaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import galeria.pasarelasDePago.PasarelaDePago;

public class MenuPasarela extends MenuBasico
{
    private List<String> pasarelas = new ArrayList<>();
	private String pasarelaElegida;

    
	public MenuPasarela() 
	{
		this.mostrarMenuPasarela();
		pasarelaElegida= null;
	}

	private void mostrarMenuPasarela()
	{
		leerPasarelas();
		int numeroPasarelas = getNumeroPasarelas();
		
		String[] opciones = new String[numeroPasarelas];

		for (int i = 0; i < pasarelas.size(); i++) {
		    opciones[i] = pasarelas.get(i);
		}
		
		opciones[opciones.length] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		String pasarela = pasarelas.get(opcionEscogida - 1);
		pasarelaElegida = pasarela;
		
		
		
	}

	private void leerPasarelas()
	{
        String archivo = "pasarelas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                pasarelas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public String getPasarela() {
		// TODO Auto-generated method stub
		return pasarelaElegida;
	}
	
	public int getNumeroPasarelas() {
		return pasarelas.size();
	}
}
