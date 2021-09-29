package principal;

import gui.ControladorPrincipal;
import loginPersonalizado.Login;
import datos.Modelo;
import gui.VentanaPrincipal;

/**
 * Gestión de Cafés. Aplicación diseñada para la gestión y relación de cafés y
 * sus fabricantes. Permite la insercción, eliminación, modificación y
 * visualización de dichos elementos. Para una mayor productividad permite a su
 * vez guardar y cargar elementos, así como modificar el aspecto visual de la
 * misma.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 */
public class Principal {

	/**
	 * Constructor. Inicializa la aplicación. Tras un inicio de sesión satisfactorio
	 * muestra la ventana principal desde la cual se accede a todas las
	 * funcionalidades de la aplicación.
	 * 
	 * @param args Argumentos inicialización.
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
