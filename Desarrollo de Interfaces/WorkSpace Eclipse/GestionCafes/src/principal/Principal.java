package principal;

import gui.ControladorPrincipal;
import loginPersonalizado.Login;
import datos.Modelo;
import gui.VentanaPrincipal;

/**
 * Gesti�n de Caf�s. Aplicaci�n dise�ada para la gesti�n y relaci�n de caf�s y
 * sus fabricantes. Permite la insercci�n, eliminaci�n, modificaci�n y
 * visualizaci�n de dichos elementos. Para una mayor productividad permite a su
 * vez guardar y cargar elementos, as� como modificar el aspecto visual de la
 * misma.
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 */
public class Principal {

	/**
	 * Constructor. Inicializa la aplicaci�n. Tras un inicio de sesi�n satisfactorio
	 * muestra la ventana principal desde la cual se accede a todas las
	 * funcionalidades de la aplicaci�n.
	 * 
	 * @param args Argumentos inicializaci�n.
	 */
	public static void main(String[] args) {
		Login login = new Login("admin", "admin");
		login.setModal(true);
		login.setUndecorated(true);
		login.setVisible(true);

		VentanaPrincipal vista = new VentanaPrincipal();
		vista.setVisible(true);
		Modelo modelo = new Modelo();
		@SuppressWarnings("unused")
		ControladorPrincipal controlador = new ControladorPrincipal(modelo, vista);
	}
}
