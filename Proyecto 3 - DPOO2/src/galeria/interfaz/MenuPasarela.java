package galeria.interfaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import galeria.pasarelasDePago.PasarelaDePago;

public class MenuPasarela extends MenuBasico
{
    private List<String> pasarelas = new ArrayList<>();
	private String pasarelaElegida;

    
	public MenuPasarela() 
	{
		pasarelaElegida= null;
		this.mostrarMenuPasarela();
	}

	private void mostrarMenuPasarela()
	{
		leerPasarelas();
		int numeroPasarelas = getNumeroPasarelas();
		
		String[] opciones = new String[numeroPasarelas+1];

		for (int i = 0; i < pasarelas.size(); i++) {
		    opciones[i] = pasarelas.get(i);
		}
		
		opciones[opciones.length-1] = "Cerrar Sesión";

		int opcionEscogida = this.mostrarMenu("Menú Principal", opciones, MENSAJE_PREDETERMINADO);

		String pasarela = pasarelas.get(opcionEscogida - 1);


		pasarelaElegida = pasarela;
		
		
		
		
	}

    private void leerPasarelas() {
        String archivo = "pasarelas.txt";

        try (InputStream inputStream = getClass().getResourceAsStream(archivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                pasarelas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("El archivo no se pudo encontrar: " + archivo);
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
