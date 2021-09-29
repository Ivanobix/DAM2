package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import componentes.PropiedadesCafe;
import datos.Cafe;
import datos.Fabricante;
import datos.Modelo;
import util.Util;

/**
 * Controlador Principal. Controlador principal de la aplicación dedicado a la
 * recogida de eventos así como la inicialización de las demás ventanas y
 * operaciones.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class ControladorPrincipal
		implements ActionListener, ChangeListener, KeyListener, ItemListener, WindowListener, ContainerListener {
	public static Color COLOR_FONDO = Color.WHITE;
	private Modelo modelo;
	private VentanaPrincipal vista;

	/**
	 * Constructor.
	 * 
	 * @param modelo Modelo de la aplicación.
	 * @param vista  Ventana principal de la aplicación.
	 */
	public ControladorPrincipal(Modelo modelo, VentanaPrincipal vista) {
		this.modelo = modelo;
		this.vista = vista;
		initHandlers();
	}

	/**
	 * Inicializar Manejadores. Inicializa todos los manejadores de eventos
	 * necesarios para el correcto funcionamiento de la aplicación.
	 */
	private void initHandlers() {
		vista.btnBuscar.addActionListener(this);
		vista.btnEliminar.addActionListener(this);
		vista.btnModificar.addActionListener(this);
		vista.btnNuevo.addActionListener(this);
		vista.mnitCargar.addActionListener(this);
		vista.mnitGuardar.addActionListener(this);
		vista.mnitNuevo.addActionListener(this);
		vista.mnitColorDeFondo.addActionListener(this);
		vista.rbtnIdentificador.addActionListener(this);
		vista.rbtnNombre.addActionListener(this);

		vista.listCafe.addKeyListener(this);
		vista.listFabricantes.addKeyListener(this);
		vista.txtBuscar.addKeyListener(this);

		vista.tabbedPane.addChangeListener(this);

		vista.cbInspeccionar.addItemListener(this);

		vista.pnVentas.addContainerListener(this);

		vista.addWindowListener(this);

	}

	/**
	 * Listar. Actualización de los valores en las listas de elementos mostrados.
	 */
	void listar() {
		int pestana = confirmarPestana();
		if (pestana == 0) {
			vista.dlmCafe.clear();
			ArrayList<Cafe> cafes = modelo.getCafes();
			for (Cafe cafe : cafes) {
				vista.dlmCafe.addElement(cafe);
			}

		} else {
			vista.dlmFabricante.clear();
			ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
			for (Fabricante fabricante : fabricantes) {
				vista.dlmFabricante.addElement(fabricante);
			}
		}
	}

	/**
	 * Añadir Elemento. Creación de la ventana y el controlador correspondiente a la
	 * creación del tipo de elemento seleccionado.
	 */
	@SuppressWarnings("unused")
	private void anadirElemento() {
		int pestana = confirmarPestana();
		if (pestana == 0) {
			VentanaGestionCafe nuevoCafe = new VentanaGestionCafe();
			nuevoCafe.setVisible(true);
			ControladorGestionCafe controladorGestionCafe = new ControladorGestionCafe(modelo, nuevoCafe, this);
		} else {
			VentanaGestionFabricante nuevoFabricante = new VentanaGestionFabricante();
			nuevoFabricante.setVisible(true);
			ControladorGestionFabricante controladorGestionFabricante = new ControladorGestionFabricante(modelo,
					nuevoFabricante, this);
		}
	}

	/**
	 * Eliminar Elemento. Eliminación del elemento seleccionado.
	 */
	private void eliminarElemento() {
		int pestana = confirmarPestana();
		if (vista.listCafe.getSelectedValue() != null || vista.listFabricantes.getSelectedValue() != null) {
			if (Util.mostrarConfirmacion("¿Estás seguro de querer eliminar este elemento?") == Util.ACEPTAR) {
				if (pestana == 0) {
					modelo.eliminarCafe(vista.listCafe.getSelectedValue());
				} else {
					modelo.eliminarFabricante(vista.listFabricantes.getSelectedValue());
					vista.tabbedPane.setSelectedIndex(0);
					listar();
					vista.tabbedPane.setSelectedIndex(pestana);
				}
				listar();
			}
		}
	}

	/**
	 * Eliminar Elemento. Eliminación de un café específico desde la pestaña de
	 * ventas.
	 * 
	 * @param cafe Café a eliminar.
	 */
	public void eliminarElemento(Cafe cafe) {
		modelo.eliminarCafe(cafe);
		int fabricanteMostrado = vista.cbInspeccionar.getSelectedIndex();
		vista.dlmCafe.clear();
		vista.tabbedPane.setSelectedIndex(0);
		listar();
		vista.tabbedPane.setSelectedIndex(2);
		vista.cbInspeccionar.setSelectedIndex(fabricanteMostrado);
	}

	/**
	 * Modificar Elemento. Creación de la ventana y el controlador correspondiente a
	 * la modificación del elemento seleccionado.
	 */
	@SuppressWarnings("unused")
	private void modificarElemento() {
		int pestana = confirmarPestana();
		if (vista.listCafe.getSelectedValue() != null || vista.listFabricantes.getSelectedValue() != null) {
			if (Util.mostrarConfirmacion("¿Estás seguro de querer modificar este elemento?") == Util.ACEPTAR) {
				if (pestana == 0) {
					VentanaGestionCafe modificarCafe = new VentanaGestionCafe(vista.listCafe.getSelectedValue());
					modificarCafe.setVisible(true);
					ControladorGestionCafe controladorGestionCafe = new ControladorGestionCafe(modelo, modificarCafe,
							this);
					modificarCafe.cbFabricante.setSelectedItem(vista.listCafe.getSelectedValue().getFabricante());
				} else {
					VentanaGestionFabricante modificarFabricante = new VentanaGestionFabricante(
							vista.listFabricantes.getSelectedValue());
					modificarFabricante.setVisible(true);
					ControladorGestionFabricante controladorGestionFabricante = new ControladorGestionFabricante(modelo,
							modificarFabricante, this);
				}
			}
		}
	}

	/**
	 * Buscar Elemento. Creación de la ventana y el controlador correspondiente a la
	 * visualización del elemento seleccionado.
	 */
	private void buscarElemento() {
		int pestana = confirmarPestana();
		if (vista.listCafe.getSelectedValue() != null || vista.listFabricantes.getSelectedValue() != null) {
			if (pestana == 0) {
				VisualizarDatosCafe visualizarDatosCafe = new VisualizarDatosCafe(vista.listCafe.getSelectedValue());
				visualizarDatosCafe.setVisible(true);
			} else {
				VisualizarDatosFabricante visualizarDatosFabricante = new VisualizarDatosFabricante(
						vista.listFabricantes.getSelectedValue());
				visualizarDatosFabricante.setVisible(true);
			}
		}
	}

	/**
	 * Filtrar Lista. Filtrado de los elementos mostrados en función de un
	 * determinado filtro.
	 */
	private void filtrarLista() {
		int pestana = confirmarPestana();
		String filtrado = vista.txtBuscar.getText().toLowerCase();
		if (!filtrado.replace(" ", "").equals("")) {
			if (pestana == 0) {
				vista.dlmCafe.clear();
				for (Cafe cafe : modelo.getCafes()) {
					String aBuscar = "";
					if (vista.rbtnIdentificador.isSelected())
						aBuscar = cafe.getIdentificador();
					else
						aBuscar = cafe.getNombre();
					if (aBuscar.trim().toLowerCase().indexOf(filtrado) != -1)
						vista.dlmCafe.addElement(cafe);
				}

			} else {
				vista.dlmFabricante.clear();
				for (Fabricante fabricante : modelo.getFabricantes()) {
					String aBuscar = "";
					if (vista.rbtnIdentificador.isSelected())
						aBuscar = fabricante.getIdentificador();
					else
						aBuscar = fabricante.getNombre();
					if (aBuscar.trim().toLowerCase().indexOf(filtrado) != -1)
						vista.dlmFabricante.addElement(fabricante);
				}

			}
		} else {
			listar();
		}

	}

	/**
	 * Confirmar Pestaña. Comprobación de pestaña en la que el usuario está situado.
	 * 
	 * @return Pestaña actual.
	 */
	private int confirmarPestana() {
		int pestana = vista.tabbedPane.getSelectedIndex();
		return pestana;
	}

	/**
	 * Guardar Datos. Guardado de datos.
	 */
	private void guardarDatos() {
		JFileChooser selector = new JFileChooser();
		int seleccion = selector.showSaveDialog(vista);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = selector.getSelectedFile();
			try {
				modelo.guardarDatos(fichero);
			} catch (IOException e1) {
				Util.mostrarError("Error al guardar el fichero");
			}
		}
	}

	/**
	 * Cargar Datos. Carga de datos.
	 */
	private void cargarDatos() {
		JFileChooser selectorCarga = new JFileChooser();
		int seleccionCarga = selectorCarga.showOpenDialog(vista);
		if (seleccionCarga == JFileChooser.APPROVE_OPTION) {
			File ficheroCarga = selectorCarga.getSelectedFile();
			try {
				modelo.cargarDatos(ficheroCarga);
			} catch (ClassNotFoundException | IOException e1) {
				Util.mostrarError("Error al abrir el fichero");
			}
		}
		vista.tabbedPane.setSelectedIndex(1);
		listar();
		vista.tabbedPane.setSelectedIndex(0);
		listar();

	}

	/**
	 * Cambiar Tema. Establecimiento de un nuevo tema para la aplicación.
	 */
	private void cambiarTema() {
		Color colorNuevo = JColorChooser.showDialog(null, "Escoge un color:", Color.WHITE);
		if (colorNuevo != null) {
			COLOR_FONDO = colorNuevo;

			vista.btnNuevo.setBackground(colorNuevo);
			vista.btnEliminar.setBackground(colorNuevo);
			vista.btnModificar.setBackground(colorNuevo);
			vista.btnBuscar.setBackground(colorNuevo);

			vista.rbtnIdentificador.setBackground(colorNuevo);
			vista.rbtnNombre.setBackground(colorNuevo);

			vista.panelPrincipal.setBackground(colorNuevo);
			vista.pnCopyright.setBackground(colorNuevo);
			vista.pnHerramientas.setBackground(colorNuevo);
		}

	}

	/**
	 * LLenar Fabricantes Ventas. Insercción de valores en el menú de selección de
	 * fabricante.
	 */
	private void llenarFabVentas() {
		ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
		vista.dcbm.removeAllElements();
		for (Fabricante fabricante : fabricantes) {
			vista.dcbm.addElement(fabricante);
		}

	}

	/**
	 * Crear Ventas. Creación de los paneles con propiedades de los cafés e
	 * insercción de los mismos en el panel de ventas.
	 */
	private void crearVentas() {
		Fabricante fabricante = (Fabricante) vista.cbInspeccionar.getSelectedItem();
		ArrayList<Cafe> cafes = modelo.getCafes();
		vista.pnVentas.removeAll();
		for (Cafe cafe : cafes) {
			if (cafe.getFabricante().equals(fabricante)) {
				vista.pnVentas.add(new PropiedadesCafe(ControladorPrincipal.this, cafe));
			}
		}
		vista.revalidate();
		vista.repaint();
	}

	/**
	 * Reiniciar Elementos. Reinicia todos los datos de la aplicación.
	 */
	private void reiniciarElementos() {
		if (Util.mostrarConfirmacion("¿Estás seguro de querer eliminar todos los datos?") == Util.ACEPTAR) {
			modelo.reiniciarDatos();
			vista.txtBuscar.setText("");
			vista.tabbedPane.setSelectedIndex(0);
			vista.dlmCafe.clear();
			vista.dlmFabricante.clear();
			vista.dcbm.removeAllElements();
			vista.pnVentas.removeAll();
		}
	}

	/**
	 * Procedimientos a seguir en caso de que un botón haya sido pulsado. (Añadir,
	 * Eliminar, Modificar, Visualizar, Filtrar ,Reiniciar, Guardar, Cargar y
	 * Cambiar tema de fondo.)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		case "Anadir":
			anadirElemento();
			break;
		case "Eliminar":
			eliminarElemento();
			break;
		case "Modificar":
			modificarElemento();
			break;
		case "Buscar":
			buscarElemento();
			break;
		case "Nuevo":
			reiniciarElementos();
			break;
		case "Guardar":
			guardarDatos();
			break;
		case "Cargar":
			cargarDatos();
			break;
		case "ColorFondo":
			cambiarTema();
			break;
		case "CambiarFiltrado":
			filtrarLista();
			break;
		}

	}

	/**
	 * Procedimientos a seguir en caso de que cambiemos de pestaña en la ventana
	 * principal. (Visibilidad de los botones.)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		Object comando = e.getSource();
		if (comando.equals(vista.tabbedPane)) {
			vista.listCafe.clearSelection();
			vista.listFabricantes.clearSelection();
			vista.txtBuscar.setText("");
			if (confirmarPestana() == 0 || confirmarPestana() == 1) {
				vista.btnNuevo.setVisible(true);
				vista.btnEliminar.setVisible(true);
				vista.btnModificar.setVisible(true);
				vista.btnBuscar.setVisible(true);
				vista.txtBuscar.setVisible(true);
				vista.rbtnIdentificador.setVisible(true);
				vista.rbtnNombre.setVisible(true);
				vista.cbInspeccionar.setVisible(false);
			} else {
				vista.btnNuevo.setVisible(false);
				vista.btnEliminar.setVisible(false);
				vista.btnModificar.setVisible(false);
				vista.btnBuscar.setVisible(false);
				vista.txtBuscar.setVisible(false);
				vista.rbtnIdentificador.setVisible(false);
				vista.rbtnNombre.setVisible(false);
				vista.horizontalStrut.setVisible(false);
				vista.cbInspeccionar.setVisible(true);
				if (modelo.getFabricantes().size() > 0) {
					vista.cbInspeccionar.setVisible(true);
					llenarFabVentas();
				} else {
					vista.cbInspeccionar.setVisible(false);
				}

			}
		}
	}

	/**
	 * Procedimientos a seguir en caso de que una tecla haya sido pulsada. (Filtrado
	 * o eliminación)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			ActionEvent borrar = new ActionEvent(vista.btnEliminar, 0, "Eliminar");
			actionPerformed(borrar);
		} else {
			filtrarLista();
		}
	}

	/**
	 * Procedimientos a seguir en caso de que el usuario trate de cerrar la ventana.
	 * (Control de cambios guardados)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (modelo.getCambios()) {
			int decision = Util.mostrarConfirmacion("¿Desea guardar los cambios realizados?");
			if (decision == JOptionPane.YES_OPTION) {
				guardarDatos();
			} else {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}

	/**
	 * Procedimientos a seguir en caso de que la selección de un ComboBox cambie.
	 * (Selección de fabricante en la pestaña de ventas.)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			crearVentas();
		}
	}

	/**
	 * Procedimientos a seguir en caso de que un componente de la ventana principal
	 * haya sido eliminado. (Eliminación de un panel de venta.)
	 */
	@Override
	public void componentRemoved(ContainerEvent e) {
		PropiedadesCafe panelAEliminar = (PropiedadesCafe) e.getChild();
		vista.pnVentas.remove(panelAEliminar);
		vista.revalidate();
		vista.repaint();
	}

	// Métodos no usados.

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void componentAdded(ContainerEvent e) {

	}

}
