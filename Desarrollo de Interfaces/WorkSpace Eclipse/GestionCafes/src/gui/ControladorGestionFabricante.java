package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import datos.Fabricante;
import datos.Modelo;
import util.Util;

/**
 * Controlador Gesti�n Fabricante. Controlador para la ventana de gesti�n de
 * fabricante dedicado a la recogida de eventos, as� como la comprobaci�n de
 * datos introducidos e insercci�n de los datos en cada campo de texto (en el
 * caso de modificaci�n).
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 *
 */
public class ControladorGestionFabricante implements ActionListener {

	private Modelo modelo;
	private ControladorPrincipal controladorPrincipal;
	private VentanaGestionFabricante ventanaGestionFabricante;

	/**
	 * Constructor.
	 * 
	 * @param modelo                   Modelo de la aplicaci�n.
	 * @param ventanaGestionFabricante Ventana de gesti�n de fabricante a controlar.
	 * @param controladorPrincipal     Controlador principal de la aplicaci�n
	 */
	public ControladorGestionFabricante(Modelo modelo, VentanaGestionFabricante ventanaGestionFabricante,
			ControladorPrincipal controladorPrincipal) {
		this.modelo = modelo;
		this.ventanaGestionFabricante = ventanaGestionFabricante;
		this.controladorPrincipal = controladorPrincipal;
		initHandlers();
	}

	/**
	 * Inicializar Manejadores. Inicializa todos los manejadores de eventos
	 * necesarios para el correcto funcionamiento de la aplicaci�n.
	 */
	private void initHandlers() {
		ventanaGestionFabricante.btnAnadir.addActionListener(this);
		ventanaGestionFabricante.btnCancelar.addActionListener(this);
	}

	/**
	 * Comprobaci�n para saber si un String es un Int.
	 * 
	 * @param aComprobar String a comprobar.
	 * @return Resultado de la comprobaci�n.
	 */
	private boolean comprobarInt(String aComprobar) {
		boolean aDevolver = true;
		try {
			Integer.parseInt(aComprobar);
		} catch (NumberFormatException nfe) {
			aDevolver = false;
		}
		return aDevolver;
	}

	/**
	 * Comprobaci�n de todos los datos introducidos para la creaci�n o modificaci�n
	 * de un fabricante.
	 * 
	 * @return Fabricante generado a partir de los datos introducidos en la ventana.
	 */
	private Fabricante comprobarDatos() {
		Fabricante fabricante = null;
		if (comprobarInt(ventanaGestionFabricante.txtTrabajadores.getText())) {
			if (ventanaGestionFabricante.txtNombre.getText().replace(" ", "").length() != 0) {
				if (ventanaGestionFabricante.txtDireccion.getText().replace(" ", "").length() != 0) {
					if (ventanaGestionFabricante.dtFechaCreacion.getDate() != null) {
						String nombre = ventanaGestionFabricante.txtNombre.getText();
						String direccion = ventanaGestionFabricante.txtDireccion.getText();
						int trabajadores = Integer.parseInt(ventanaGestionFabricante.txtTrabajadores.getText());
						LocalDate fechaCreacion = ventanaGestionFabricante.dtFechaCreacion.getDate();
						boolean internacional = false;
						if (ventanaGestionFabricante.rbtnSi.isSelected()) {
							internacional = true;
						}
						fabricante = new Fabricante(nombre, direccion, trabajadores, fechaCreacion, internacional);

					} else {
						Util.mostrarError("Debes seleccionar una fecha de creaci�n.");
					}
				} else {
					Util.mostrarError(
							"La direcci�n no puede estar vac�a.\n Ejemplo: Avenida Ram�n y Cajal, 5, 24002, Le�n.");
				}
			} else {
				Util.mostrarError("El nombre no puede estar vac�o.\n Ejemplo: Granell.");
			}

		} else {
			Util.mostrarError("El valor de los trabajadores no est� en el formato correcto.\n Ejemplo: 25");
		}

		return fabricante;
	}

	/**
	 * Procedimientos a seguir en caso de que un bot�n haya sido pulsado. (A�adir,
	 * Modificar y Cancelar.)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		case "Anadir":
			Fabricante nuevoFabricante = comprobarDatos();
			if (nuevoFabricante != null) {
				modelo.anadirFabricante(nuevoFabricante);
				ventanaGestionFabricante.dispose();
				controladorPrincipal.listar();
			}
			break;
		case "Modificar":
			Fabricante modificarFabricante = comprobarDatos();
			if (modificarFabricante != null) {
				modelo.modificarFabricante(ventanaGestionFabricante.fabricanteAModificar,
						modificarFabricante.getNombre(), modificarFabricante.getDireccion(),
						modificarFabricante.getTrabajadores(), modificarFabricante.getFechaCreacion(),
						modificarFabricante.isInternacional());
				ventanaGestionFabricante.dispose();
				controladorPrincipal.listar();
			}
			break;

		case "Cancelar":
			ventanaGestionFabricante.dispose();
			break;
		}

	}

}
