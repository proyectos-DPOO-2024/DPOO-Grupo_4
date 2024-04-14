package Casa_subastas.Interface;

import java.io.IOException;

import Casa_subastas.Persistencias.Loader;

public class MenuPrincipal extends ConsolaBasica {

	public static void main() {
		MenuPrincipal me = new MenuPrincipal( );
        me.empezarAplicacion( );
	}
	
	private void empezarAplicacion() {
		try
        {
            String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de la galería " );
            Loader.cargarGaleria(archivo);
        }
        catch( Exception e )
		{
            e.printStackTrace( );
        }
    }
	}

