package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import datos.Cafe;
import datos.Fabricante;
import datos.Modelo;
import util.Util;

/**
 * Controlador Gesti�n Caf�. Controlador para la ventana de gesti�n de caf�
 * dedicado a la recogida de eventos, as� como la comprobaci�n de datos
 * introducidos e insercci�n de los datos en cada campo de texto (en el caso de
 * modificaci�n).
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 *
 */
public class ControladorGestionCafe implements ActionListener {

	private Modelo modelo;
	private ControladorPrincipal controladorPrincipal;
	private VentanaGestionCafe ventanaGestionCafe;

	/**
	 * Constructor.
	 * 
	 * @param modelo               Modelo de la aplicaci�n.
	 * @param ventanaGestionCafe   Ventana de gesti�n de caf� a controlar.
	 * @param controladorPrincipal Controlador principal de la aplicaci�n
	 */
	public ControladorGestionCafe(Modelo modelo, VentanaGestionCafe ventanaGestionCafe,
			ControladorPrincipal controladorPrincipal) {
		this.modelo = modelo;
		this.ventanaGestionCafe = ventanaGestionCafe;
		this.controladorPrincipal = controladorPrincipal;
		initHandlers();
		llenarListaFabricantes();
	}

	/**
	 * Inicializar Manejadores. Inicializa todos los manejadores de eventos
	 * necesarios para el correcto funcionamiento de la aplicaci�n.
	 */
	private void initHandlers() {
		ventanaGestionCafe.btnAnadir.addActionListener(this);
		ventanaGestionCafe.btnCancelar.addActionListener(this);
	}

	/**
	 * Insercci�n de valores en el men� de selecci�n de fabricante.
	 */
	private void llenarListaFabricantes() {
		ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
		for (Fabricante fabricante : fabricantes) {
			ventanaGestionCafe.dcbm.addElement(fabricante);
		}

	}

	/**
	 * Comprobaci�n para saber si un String es un Double.
	 * 
	 * @param aComprobar String a comprobar.
	 * @return Resultado de la comprobaci�n.
	 */
	private boolean comprobarDouble(String aComprobar) {
		boolean aDevolver = true;
		try {
			Double.parseDouble(aComprobar);
		} catch (NumberFormatException nfe) {
			aDevolver = false;
		}
		return aDevolver;
	}

	/**
	 * Comprobaci�n de todos los datos introducidos para la creaci�n o modificaci�n
	 * de un caf�.
	 * 
	 * @return Caf� generado a partir de los datos introducidos en la ventana.
	 */
	private Cafe comprobarDatos() {
		Cafe cafe = null;
		if (comprobarDouble(ventanaGestionCafe.txtArabico.getText())
				&& comprobarDouble(ventanaGestionCafe.txtRobusta.getText())) {
			if (ventanaGestionCafe.txtNombre.getText().replace(" ", "").length() != 0) {
				if (ventanaGestionCafe.cbFabricante.getSelectedItem() != null) {
					if (ventanaGestionCafe.dtFechaEnvasado.getDate() != null) {
						double arabico = Double.parseDouble(ventanaGestionCafe.txtArabico.getText());
						double robusta = Double.parseDouble(ventanaGestionCafe.txtRobusta.getText());
						if (arabico + robusta <= 100 && arabico + robusta >= 0) {
							String nombre = ventanaGestionCafe.txtNombre.getText();
							LocalDate fechaEnvasado = ventanaGestionCafe.dtFechaEnvasado.getDate();
							Fabricante fabricante = (Fabricante) ventanaGestionCafe.cbFabricante.getSelectedItem();
							cafe = new Cafe(nombre, fechaEnvasado, arabico, robusta, fabricante);

						} else {
							Util.mostrarError(
									"La suma de los ingredientes no puede ser mayor al 100% ni menor al 0%.\n Ejemplo: Ar�bico: 45% Robusta: 30%.");
						}

					} else {
						Util.mostrarError("Debes seleccionar una fecha de envasado.");
					}
				} else {
					Util.mostrarError("Debes seleccionar un fabricante.");
				}
			} else {
				Util.mostrarError("El nombre no puede estar vac�o.\n Ejemplo: Lavazza Caf�.");
			}

		} else {
			Util.mostrarError("Los valores de la mezcla no est�n en el formato correcto.\n Ejemplo: 23.2.");
		}
		return cafe;
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
			Cafe nuevoCafe = comprobarDatos();
			if (nuevoCafe != null) {
				modelo.anadirCafe(nuevoCafe);
				ventanaGestionCafe.dispose();
				controladorPrincipal.listar();
			}
			break;
		case "Modificar":
			Cafe modificarCafe = comprobarDatos();
			if (modificarCafe != null) {
				modelo.modificarCafe(ventanaGestionCafe.cafeAModificar, modificarCafe.getNombre(),
						modificarCafe.getFechaEnvasado(), modificarCafe.getPorcentajeArabico(),
						modificarCafe.getPorcentajeRobusta(), modificarCafe.getFabricante());
				ventanaGestionCafe.dispose();
				controladorPrincipal.listar();
			}
			break;
		case "Cancelar":
			ventanaGestionCafe.dispose();
			break;
		}

	}

}
