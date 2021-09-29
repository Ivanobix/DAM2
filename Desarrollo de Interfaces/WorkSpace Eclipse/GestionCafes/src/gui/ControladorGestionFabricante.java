package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import datos.Fabricante;
import datos.Modelo;
import util.Util;

/**
 * Controlador Gestión Fabricante. Controlador para la ventana de gestión de
 * fabricante dedicado a la recogida de eventos, así como la comprobación de
 * datos introducidos e insercción de los datos en cada campo de texto (en el
 * caso de modificación).
 * 
 * @author Iván García Prieto
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
	 * @param modelo                   Modelo de la aplicación.
	 * @param ventanaGestionFabricante Ventana de gestión de fabricante a controlar.
	 * @param controladorPrincipal     Controlador principal de la aplicación
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
	 * necesarios para el correcto funcionamiento de la aplicación.
	 */
	private void initHandlers() {
		ventanaGestionFabricante.btnAnadir.addActionListener(this);
		ventanaGestionFabricante.btnCancelar.addActionListener(this);
	}

	/**
	 * Comprobación para saber si un String es un Int.
	 * 
	 * @param aComprobar String a comprobar.
	 * @return Resultado de la comprobación.
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
	 * Comprobación de todos los datos introducidos para la creación o modificación
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
						Util.mostrarError("Debes seleccionar una fecha de creación.");
					}
				} else {
					Util.mostrarError(
							"La dirección no puede estar vacía.\n Ejemplo: Avenida Ramón y Cajal, 5, 24002, León.");
				}
			} else {
				Util.mostrarError("El nombre no puede estar vacío.\n Ejemplo: Granell.");
			}

		} else {
			Util.mostrarError("El valor de los trabajadores no está en el formato correcto.\n Ejemplo: 25");
		}

		return fabricante;
	}

	/**
	 * Procedimientos a seguir en caso de que un botón haya sido pulsado. (Añadir,
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
