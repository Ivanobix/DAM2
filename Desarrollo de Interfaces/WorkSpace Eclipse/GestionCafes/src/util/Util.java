package util;

import javax.swing.JOptionPane;

/**
 * Util. Clase dedicada a la creación de JOptionPanels personalizados para su
 * uso en la aplicación principal, permitiendo así una mayor accesibilidad y la
 * simplificación del código.
 * 
 * @author Iván García Prieto
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
	 * Confirmación. Muestra por pantalla una ventana que permite al usuario escoger
	 * entre dos posibles acciones.
	 * 
	 * @param mensaje Mensaje de confirmación a mostrar al ususario.
	 * @return Mensaje de confirmación generado.
	 */
	public static int mostrarConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
	}
}
