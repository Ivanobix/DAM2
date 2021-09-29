package gui.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import base.Coche;
import base.Conductor;
import gui.Modelo;
import gui.componentes.PanelConductor;
import util.Util;

/**
 * Clase controlador, que mantiene la relación entre la clase Vista 
 * y la clase Modelo
 * 
 * @author Fer
 *
 */
public class Controlador implements ActionListener, ListSelectionListener, KeyListener , WindowListener{

	private Vista vista;
	private Modelo modelo;

	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;

		addActionListeners(this);
		addListListeners(this);
		addKeyListeners(this);
		addWindowListeners(this);
	}

	
	/**
	 * Metodo que asocia componentes graficos con el manejador
	 * de eventos de tipo WindowEvent
	 * 
	 * @param listener el objeto WindowListener encargado de la gestion
	 * de eventos WindowEvent
	 */
	private void addWindowListeners(WindowListener listener) {
		vista.addWindowListener(listener);
		
	}


	/**
	 * Metodo que asocia componentes graficos con el manejador
	 * de eventos de tipo KeyEvent
	 * 
	 * @param listener el objeto KeyListener encargado de la gestion
	 * de eventos KeyEvent
	 */
	private void addKeyListeners(KeyListener listener) {
		vista.listCoches.addKeyListener(listener);
		vista.txtBuscarVehiculo.addKeyListener(listener);
	}

	/**
	 * Metodo que asocia componentes graficos con el manejador
	 * de eventos de tipo ListSelectionEvent
	 * 
	 * @param listener el objeto ListSelectionListener encargado de la gestion
	 * de eventos ListSelectionEvent
	 */
	private void addListListeners(ListSelectionListener listener) {
		vista.listCoches.addListSelectionListener(listener);
	}

	/**
	 * Metodo que vincula el objeto listener (esta propia clase) con los componentes
	 * gráficos sobre los que quiero escucar eventos (botones en este caso)
	 * 
	 * @param listener objeto que actua como ActionListener
	 */
	private void addActionListeners(ActionListener listener) {
		vista.btnEliminarCoche.addActionListener(listener);
		vista.btnNuevoCoche.addActionListener(listener);
		vista.btnModificar.addActionListener(listener);
		vista.btnNuevoConductor.addActionListener(listener);

		vista.menuItemCargar.addActionListener(listener);
		vista.menuItemGuardar.addActionListener(listener);
	}

	/**
	 * Metodo que se invoca al producirse un evento sobre un boton Da el igual el
	 * boton que pulse que siempre se ejecuta Por lo tanto, el metodo debe
	 * diferenciar el boton pulsado Para ello hago uso de la propiedad ActionCommand
	 * que tiene cada boton. Accedo a ella mediante el metodo getActionComando del
	 * evento capturado.
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		switch (comando) {

		case "NuevoCoche": {
			String matricula = vista.txtMatricula.getText();
			String modelo = vista.txtModelo.getText();
			String kms = vista.txtKilometros.getText();
			LocalDate fechaFabricacion = vista.datePickerCoche.getDate();
			Conductor conductor = (Conductor) vista.cbConductorCoche.getSelectedItem();
			
			if (comprobarAltaCoche(matricula, modelo, kms, fechaFabricacion)) {

				this.modelo.altaCoche(matricula, modelo, Double.parseDouble(kms), fechaFabricacion, conductor);
			} else {
				Util.mostrarDialogoError("Debes introducir todos los datos de forma correcta");
			}
			
			vista.barraEstado.setMensajeInfo("Cambios sin guardar");
		}
			break;
		case "EliminarCoche": {
			Coche cocheEliminado = vista.listCoches.getSelectedValue();
			modelo.eliminarCoche(cocheEliminado);

		}
			break;

		case "Guardar": {

			JFileChooser selector = new JFileChooser();
			int opt = selector.showSaveDialog(vista);

			if (opt == JFileChooser.APPROVE_OPTION) {
				File fichero = selector.getSelectedFile();

				try {
					modelo.guardarDatos(fichero);

				} catch (IOException e1) {

					e1.printStackTrace();
					Util.mostrarDialogoError("Error al guardar el fichero");
				}
			}
			vista.barraEstado.setMensajeInfo("");
		}
			break;

		case "Cargar": {
			JFileChooser selector = new JFileChooser();
			int opt = selector.showOpenDialog(vista);

			if (opt == JFileChooser.APPROVE_OPTION) {
				File fichero = selector.getSelectedFile();

				try {
					modelo.cargarDatos(fichero);

				} catch (IOException | ClassNotFoundException e1) {

					e1.printStackTrace();
					Util.mostrarDialogoError("Error al abrir el fichero");
				}
			}
			refrescarComboBoxCoches();
			refrescarPanelConductores();
		}
			break;
			
		case "Modificar":{
			Coche coche = vista.listCoches.getSelectedValue();
			modificarDatosCoche(coche);
		}
		break;
		
		case "NuevoConductor":{
			String nombre = vista.txtNombreConductor.getText();
			String apellidos = vista.txtApellidosConductor.getText();
			String dni = vista.txtDniConductor.getText();
			boolean novel = vista.chboxNovel.isSelected();
			int annosExperiencia = (Integer)vista.spinnerExperiencia.getValue();
		
			modelo.altaConductor(dni, nombre, apellidos, novel, annosExperiencia);
			
			refrescarComboBoxCoches();
			refrescarPanelConductores();
		}
		}

		refrescarLista();
	}

	private void refrescarPanelConductores() {
		vista.panelDinamico.removeAll();
		for(Conductor conductor : modelo.getConductores()) {
			PanelConductor panelConductor = new PanelConductor(conductor);
			vista.panelDinamico.add(panelConductor);
		}
		//Debo revalidar cuando añado un componente a un contenedor
		vista.panelDinamico.revalidate();
		
	}


	/**
	 * Metodo que lista los conductores en el elemento JComboBox de la seccion
	 * de vehiculos. Añade como primer elemento un elemento en blanco (null)
	 */
	private void refrescarComboBoxCoches() {
		
		vista.dcbmConductorCoche.removeAllElements();
		
		vista.dcbmConductorCoche.addElement(null);
		
		for(Conductor conductor : modelo.getConductores()) {
			vista.dcbmConductorCoche.addElement(conductor);
		}
		
	}



	/**
	 * Metodo empleado para modificar los datos de un coche
	 * 
	 * Se invoca desde el boton de modificar, y obtiene los datos 
	 * de los campos de texto de la seccion coches y se los asigna
	 * al coche seleccionado en el JList
	 * 
	 * @param coche el coche seleccionado cuyos datos debo modificar
	 */
	private void modificarDatosCoche(Coche coche) {
		coche.setConductor((Conductor)vista.dcbmConductorCoche.getSelectedItem());
		coche.setFechaFabricacion(vista.datePickerCoche.getDate());
		coche.setKms(Double.parseDouble(vista.txtKilometros.getText()));
		coche.setMatricula(vista.txtMatricula.getText());
		coche.setModelo(vista.txtModelo.getText());
	}



	/**
	 * Metodo que comprueba la entrada de datos del usuario.
	 * Se asegura de que los datos recibidos por parametros son correctos: 
	 * no hay valores nulos, son del tipo correcto, y tienen la longitud necesaria
	 * 
	 * @param matricula cadena que representa la matricula, debe tener 4 caracteres minimo
	 * @param modelo2 cadena que representa el modelo de coche
	 * @param kms cadena que representa un dato de tipo double
	 * @param fechaFabricacion fecha de fabricación de coche
	 * @return
	 */
	private boolean comprobarAltaCoche(String matricula, String modelo2, String kms, LocalDate fechaFabricacion) {

		if (matricula.length() < 4) {
			return false;
		}
		if (modelo2.length() == 0) {
			return false;
		}
		try {
			Double.parseDouble(kms);
		} catch (NumberFormatException e) {
			return false;
		}

		if (fechaFabricacion == null) {
			return false;
		}

		return true;
	}

	/**
	 * Metodo para listar. Usa El DefaultListModel asociado a JList de coches para
	 * añadir ahí los coches.
	 */
	private void refrescarLista() {
		vista.dlmCoches.clear();

		for (Coche coche : modelo.getCoches()) {
			vista.dlmCoches.addElement(coche);
		}
	}

	/** Metodo que se ejecuta cuando selecciono un elemento en un JList
	 * 
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		Coche coche = vista.listCoches.getSelectedValue();
		if (coche != null) {
			mostrarValoresCoche(coche);
		}

	}

	/**
	 * Metodo que muestra los datos del coche en los campos de texto y otros 
	 * elementos de la seccion de coches. Se usa cada vez que se pulsa sobre 
	 * un coche en el JList de esa seccion, y es invocado por el metodo
	 * valueChanged() del ListSelectionListener
	 * 
	 * @param coche el coche cuyos datos muestro
	 */
	private void mostrarValoresCoche(Coche coche) {

		vista.txtMatricula.setText(coche.getMatricula());
		vista.txtModelo.setText(coche.getModelo());
		vista.txtKilometros.setText(String.valueOf(coche.getKms()));
		vista.datePickerCoche.setDate(coche.getFechaFabricacion());
		vista.cbConductorCoche.setSelectedItem(coche.getConductor());
		
	}

	/**
	 * Metodo que se ejecuta cuando se captura un evento de teclado
	 * KeyEvent desde la clase controlador
	 */
	@Override
	public void keyReleased(KeyEvent event) {

		// Compruebo que elemento a producido el evento
		if (event.getSource() == vista.listCoches) {
			// Compruebo si la tecvla pulsada es suprimir
			if (event.getKeyCode() == KeyEvent.VK_DELETE) {
				Coche cocheEliminado = vista.listCoches.getSelectedValue();
				modelo.eliminarCoche(cocheEliminado);
				refrescarLista();
			}
		} else {
			if (vista.txtBuscarVehiculo.getText().length() >= 3) {
				String textoBusqueda = vista.txtBuscarVehiculo.getText();
				listarPorMatricula(textoBusqueda);
			} else {
				refrescarLista();
			}
		}

	}

	/**
	 * Metodo que permite listar los vehiculos que contienen en su
	 * matricula un texto pasado como parametro
	 * 
	 * @param textoBusqueda el texto que debe contener la matricula 
	 * para ser listado en e Jlist
	 */
	private void listarPorMatricula(String textoBusqueda) {
		vista.dlmCoches.clear();

		for (Coche coche : modelo.getCoches()) {
			if (coche.getMatricula().contains(textoBusqueda)) {
				vista.dlmCoches.addElement(coche);
			}
		}

	}

	/**
	 * Metodo invocado al capturar un evento de tipo WindowEvent,
	 * en concreto cuando se intenta cerrar la ventana pulsando la X
	 * de la ventana
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		
		int opt = Util.mostrarDialogoSiNo("¿Desea cerrar la aplicación");
		if(opt == Util.ACEPTAR) {
			System.exit(0);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * Metodos de la interface KeyListener sin usar
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	/*
	 * (non-Javadoc)
	 * Metodos de la interface WindowListener sin usar
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
