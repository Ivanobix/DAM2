import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class consultarmedico {

	private final static int REPETIDO = 1;
	private final static int NO_REPETIDO = 2;
	private final static int NO_EXISTE = 3;

	private Connection conexion;

	public static void main(String[] args) {
		new consultarmedico(args);

	}

	public consultarmedico(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/clinica", "root", "");

			int numColegiado = obtenerNumColegiado(args[0]);

			String aMostrar = "";
			int contadorClientes = 0;
			String select = "SELECT clientes.NOMBRE, clientes.APELLIDOS FROM clientes, medicos WHERE medicos.NUMCOLEGIADO = clientes.NUMCOLEGIADO and medicos.NUMCOLEGIADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(select);
			sentencia.setInt(1, numColegiado);

			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				aMostrar += resultado.getString(1) + " " + resultado.getString(2) + "\n";
				contadorClientes++;
			}

			System.out.println("Número de clientes: " + contadorClientes);
			if (contadorClientes != 0) {
				System.out.println(aMostrar);
			} else {
				select = "SELECT ESPECIALIDAD, MAXCLIENTES FROM medicos WHERE NUMCOLEGIADO = ?;";
				sentencia = conexion.prepareStatement(select);
				sentencia.setInt(1, numColegiado);

				resultado = sentencia.executeQuery();
				resultado.next();
				System.out.println("Especialidad: " + resultado.getString(1));
				System.out.println("Número máximo de clientes: " + resultado.getInt(2));

			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int comprobarRepetidos(String nombre) {
		int aDevolver = NO_EXISTE;
		try {
			String select = "SELECT count(NUMCOLEGIADO) FROM medicos WHERE NOMBRE = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(select);
			sentencia.setString(1, nombre);
			ResultSet resultado = sentencia.executeQuery();
			resultado.next();
			int coincidencias = resultado.getInt(1);

			if (coincidencias == 1) {
				aDevolver = NO_REPETIDO;
			} else if (coincidencias > 1) {
				aDevolver = REPETIDO;
			}

			resultado.close();
			sentencia.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	private int obtenerNumColegiado(String nombre) {
		int aDevolver = -1;
		try {
			int resultadoComprobacion = comprobarRepetidos(nombre);
			String select;
			PreparedStatement sentencia;
			ResultSet resultado;
			if (resultadoComprobacion == NO_REPETIDO) {
				select = "SELECT NUMCOLEGIADO FROM medicos WHERE NOMBRE = ?;";
				sentencia = conexion.prepareStatement(select);
				sentencia.setString(1, nombre);
				resultado = sentencia.executeQuery();

				resultado.next();
				aDevolver = resultado.getInt(1);

				resultado.close();
				sentencia.close();
			} else if (resultadoComprobacion == REPETIDO) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Inserte el apellido del médico: ");
				String apellido = sc.nextLine();
				sc.close();

				select = "SELECT NUMCOLEGIADO FROM medicos WHERE NOMBRE = ? and APELLIDO1 = ?;";
				sentencia = conexion.prepareStatement(select);
				sentencia.setString(1, nombre);
				sentencia.setString(2, apellido);
				resultado = sentencia.executeQuery();

				boolean existe = resultado.next();
				if (existe) {
					aDevolver = resultado.getInt(1);
				} else {
					System.out.println("El médico no existe.");
					System.exit(0);
				}

				resultado.close();
				sentencia.close();
			} else {
				System.out.println("El médico no existe.");
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return aDevolver;
	}

}
