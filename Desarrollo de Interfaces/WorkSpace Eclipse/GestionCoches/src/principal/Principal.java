package principal;

import datos.Modelo;
import ventana.Controlador;
import ventana.Vista;

public class Principal {

	public static void main(String[] args) {
		Vista vista = new Vista();
		vista.setVisible(true);
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, vista);

	}

}
