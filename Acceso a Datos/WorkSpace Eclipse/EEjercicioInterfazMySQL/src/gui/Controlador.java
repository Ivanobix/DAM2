package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import base.Sucursal;
import modelo.Modelo;

public class Controlador implements ActionListener {

	private Vista vista;
	private Modelo modelo;

	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		initHandlers();
		irAlPrimero();
	}

	private void initHandlers() {
		vista.btnPrimero.addActionListener(this);
		vista.btnUltimo.addActionListener(this);
		vista.btnSiguiente.addActionListener(this);
		vista.btnAnterior.addActionListener(this);
		vista.btnRefrescar.addActionListener(this);
	}

	private void irAlPrimero() {
		introducirDatos(modelo.obtenerPrimero());
	}

	private void irAlUltimo() {
		introducirDatos(modelo.obtenerUltimo());
	}

	private void irAlSiguiente() {
		introducirDatos(modelo.obtenerSiguiente());
	}

	private void irAlAnterior() {
		introducirDatos(modelo.obtenerAnterior());
	}

	private void refrescar() {
		modelo.actualizarDatos();
		irAlPrimero();
	}

	private void introducirDatos(Sucursal sucursal) {
		vista.txtCodSucursal.setText(sucursal.getCodSucursal());
		vista.txtDireccion.setText(sucursal.getDireccion());
		vista.txtDirector.setText(sucursal.getDirector());
		vista.txtNumTrabajadores.setText(String.valueOf(sucursal.getNumTrabajadores()));
		vista.txtTelefono.setText(sucursal.getTelefono());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "btnPrimero":
			irAlPrimero();
			break;

		case "btnUltimo":
			irAlUltimo();
			break;
		case "btnSiguiente":
			irAlSiguiente();
			break;
		case "btnAnterior":
			irAlAnterior();
			break;
		case "btnRefrescar":
			refrescar();
			break;
		}

	}

}
