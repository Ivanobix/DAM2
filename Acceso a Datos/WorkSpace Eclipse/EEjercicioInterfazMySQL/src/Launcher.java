import gui.Controlador;
import gui.Vista;
import modelo.Modelo;

public class Launcher {

	public static void main(String[] args) {
		new Controlador(new Vista(), new Modelo());

	}

}
