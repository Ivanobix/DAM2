package principal;

import gui.Modelo;
import gui.mvc.Controlador;
import gui.mvc.Vista;

public class Principal {

	/**
	 * Crea una instancia de la Vista, y del modelo, y se las pasa a la isntancia
	 * de la clase controlador, para iniciar la aplicacion
	 * 
	 * @param args parametros introducidos desde terminal
	 */
	public static void main(String[] args) {
	
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, modelo);
		
	}

}
