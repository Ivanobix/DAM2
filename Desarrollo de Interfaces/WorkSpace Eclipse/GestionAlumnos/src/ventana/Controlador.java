package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import datos.Alumno;
import datos.Modelo;

public class Controlador implements ActionListener {

	private Modelo modelo;
	private Ventana vista;

	public Controlador(Modelo modelo, Ventana vista) {
		this.modelo = modelo;
		this.vista = vista;
		initActionHandlers(this);
	}

	private void initActionHandlers(ActionListener listener) {
		vista.btnNuevo.addActionListener(listener);
		vista.btnEliminar.addActionListener(listener);
		vista.btnListar.addActionListener(listener);
		vista.mnitGuardar.addActionListener(listener);
		vista.mnitCargar.addActionListener(listener);
		vista.mnitSalir.addActionListener(listener);
	}

	private void listarAlumnos() {
		vista.txtAComentarios.setText("");
		ArrayList<Alumno> alumnos = modelo.getAlumnos();
		for (Alumno alumno : alumnos) {
			vista.txtAComentarios.append(alumno.toString() + "\n");
		}
	}

	private void limpiarCampos() {
		vista.txtFDni.setText("");
		vista.txtFNombre.setText("");
		vista.datePicker.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		case "Nuevo":
			String dni = vista.txtFDni.getText();
			String nombre = vista.txtFNombre.getText();
			LocalDate fechaNacimiento = vista.datePicker.getDate();

			modelo.introducirAlumno(dni, nombre, fechaNacimiento);
			limpiarCampos();
			listarAlumnos();
			break;
		case "Eliminar":
			String dniAEliminarString = vista.txtFDni.getText();

			modelo.eliminarAlumno(dniAEliminarString);
			limpiarCampos();
			listarAlumnos();
			break;
		case "Listar":
			listarAlumnos();
			break;
		case "Guardar":
			try {
				modelo.guardarDatos();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
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
			break;
		case "Salir":
			System.exit(0);
			break;

		}

	}
}
