package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

import javax.swing.JOptionPane;

import datos.Coche;
import datos.Modelo;

public class Controlador implements ActionListener, KeyListener {

	private Modelo modelo;
	private Vista vista;

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		initActionHandlers(this);
		vista.listConsola.addKeyListener(this);
	}

	private void initActionHandlers(ActionListener listener) {
		vista.btnAnadir.addActionListener(listener);
		vista.btnBorrar.addActionListener(listener);
		vista.btnListar.addActionListener(listener);
		vista.mnitCargar.addActionListener(listener);
		vista.mnitGuardar.addActionListener(listener);
		vista.mnitSalir.addActionListener(listener);
	}

	private void listarCoches() {
		vista.dlm.clear();
		HashSet<Coche> coches = modelo.getCoches();
		for (Coche coche : coches) {
			vista.dlm.addElement(coche);
		}

	}

	private void limpiarCampos() {
		vista.txtFMatricula.setText("");
		vista.txtFModelo.setText("");
		vista.txtFKm.setText("");
		vista.dateFab.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		case "Nuevo":
			String matricula = vista.txtFMatricula.getText();
			String modeloCoche = vista.txtFModelo.getText();
			double kilometros = Double.parseDouble(vista.txtFKm.getText());
			LocalDate anoFabricacion = vista.dateFab.getDate();
			modelo.introducirCoche(matricula, modeloCoche, kilometros, anoFabricacion);
			limpiarCampos();
			listarCoches();
			break;

		case "Borrar":
			Coche aEliminar = vista.listConsola.getSelectedValue();
			modelo.eliminarCoche(aEliminar);
			limpiarCampos();
			listarCoches();
			break;

		case "Listar":
			listarCoches();
			break;

		case "Cargar":
			try {
				modelo.cargarDatos();
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "Error al leer fichero.", "Error", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			listarCoches();
			break;

		case "Guardar":
			try {
				modelo.guardarDatos();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		case "Salir":
			System.exit(0);
			break;

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			Coche aEliminar = vista.listConsola.getSelectedValue();
			modelo.eliminarCoche(aEliminar);
			limpiarCampos();
			listarCoches();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
