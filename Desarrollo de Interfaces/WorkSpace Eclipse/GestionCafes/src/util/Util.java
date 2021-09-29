package util;

import javax.swing.JOptionPane;

/**
 * Util. Clase dedicada a la creaci�n de JOptionPanels personalizados para su
 * uso en la aplicaci�n principal, permitiendo as� una mayor accesibilidad y la
 * simplificaci�n del c�digo.
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 */
public class Util {

	public static final int ACEPTAR = JOptionPane.OK_OPTION;
	public static final int CANCELAR = JOptionPane.CANCEL_OPTION;

	/**
	 * Mensaje de Error. Muestra por pantalla una ventana indicando un error
	 * determinado.
	 * 
	 * @param error Mensaje de error a mostrar al usuario.
	 */
	public static void mostrarError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Confirmaci�n. Muestra por pantalla una ventana que permite al usuario escoger
	 * entre dos posibles acciones.
	 * 
	 * @param mensaje Mensaje de confirmaci�n a mostrar al ususario.
	 * @return Mensaje de confirmaci�n generado.
	 */
	public static int mostrarConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmaci�n", JOptionPane.YES_NO_OPTION);
	}
}
