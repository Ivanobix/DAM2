package util;

import javax.swing.JOptionPane;

/**
 * Clase con metodos utilitarios y constantes
 * @author Fer
 *
 */
public class Util {
	
	/**
	 * Valor de pulsado boton de aceptar
	 */
	public static final int ACEPTAR = JOptionPane.OK_OPTION;
	
	/**
	 * Valor de pulsado boton de cancelar
	 */
	public static final int CANCELAR = JOptionPane.CANCEL_OPTION;

	/**
	 * Metodo que muestra un dialogo con mensaje de error mediante la clase
	 * JOptionPane
	 * 
	 * @param mensaje el mensaje que muestra el dialogo
	 */
	public static void mostrarDialogoError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);	
	}
	
	/**
	 * Metodo que muestra un dialogo de confirmacion, ofreciendo las opciones de SI/NO
	 * 
	 * @param mensaje El mensaje que muestra el dialogo
	 * 
	 * @return devuelve Util.Aceptar si se pulsa aceptar o Util.Cancelar si se pulsa cancelar
	 */
	public static int mostrarDialogoSiNo(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
	}
}
