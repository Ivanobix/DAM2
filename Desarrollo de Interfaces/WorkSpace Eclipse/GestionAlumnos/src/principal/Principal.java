package principal;

import datos.Modelo;
import ventana.Controlador;
import ventana.Ventana;

public class Principal {

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setVisible(true);
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, ventana);
		
	}
	

}