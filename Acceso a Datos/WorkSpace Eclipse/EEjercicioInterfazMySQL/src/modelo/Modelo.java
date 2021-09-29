package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import base.Sucursal;

public class Modelo {

	private ArrayList<Sucursal> sucursales;
	private int posicionActual;

	public Modelo() {
		sucursales = new ArrayList<>();
		actualizarDatos();
		posicionActual = 0;
	}

	public void actualizarDatos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/practica", "root", "");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM sucursal";
			ResultSet resultado = sentencia.executeQuery(sql);

			sucursales.clear();
			while (resultado.next()) {
				sucursales.add(new Sucursal(resultado.getString(1), resultado.getString(2), resultado.getInt(3),
						resultado.getString(4), resultado.getString(5)));
			}

			resultado.close();
			sentencia.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Algo ha ido mal.");
			System.err.println("Verifica la versión del driver introducido.");
		}
	}

	public ArrayList<Sucursal> getSucursales() {
		return sucursales;
	}

	public Sucursal obtenerPrimero() {
		posicionActual = 0;
		return sucursales.get(posicionActual);
	}

	public Sucursal obtenerUltimo() {
		posicionActual = sucursales.size() - 1;
		return sucursales.get(posicionActual);
	}

	public Sucursal obtenerSiguiente() {
		if (posicionActual == sucursales.size() - 1) {
			posicionActual = 0;
		} else {
			posicionActual++;
		}
		return sucursales.get(posicionActual);
	}

	public Sucursal obtenerAnterior() {
		if (posicionActual == 0) {
			posicionActual = sucursales.size() - 1;
		} else {
			posicionActual--;
		}
		return sucursales.get(posicionActual);
	}

}
