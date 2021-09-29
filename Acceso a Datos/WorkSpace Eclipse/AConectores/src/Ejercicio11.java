import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio11 {

	private DatabaseMetaData dbmd;

	public static void main(String[] args) {
		new Ejercicio11();

	}

	public Ejercicio11() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:D:/DB/SQLITE/ejemplo.db");
			dbmd = conexion.getMetaData();

			mostrarDatosBBDD();

			String nombreDeLaTablaActual = "";
			ResultSet tablas = dbmd.getTables("empresa", null, null, null);

			while (tablas.next()) {
				nombreDeLaTablaActual = tablas.getString(3);
				System.out.println(
						"\n=====================" + nombreDeLaTablaActual.toUpperCase() + "========================");
				System.out.println("CLAVES PRIMARIAS:");
				mostrarPrimaryKeys(nombreDeLaTablaActual);

				System.out.println("CLAVES EXPORTADAS:");
				mostrarExportedKeys(nombreDeLaTablaActual);

				System.out.println("CLAVES IMPORTADAS:");
				mostrarImportedKeys(nombreDeLaTablaActual);

			}

			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void mostrarDatosBBDD() {
		try {
			System.out.println("=====================BBDD========================");
			System.out.println("Nombre: " + dbmd.getDatabaseProductName());
			System.out.println("Driver: " + dbmd.getDriverName());
			System.out.println("URL: " + dbmd.getURL());
			System.out.println("Usuario: " + dbmd.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void mostrarPrimaryKeys(String nombreDeLaTablaActual) {
		try {
			ResultSet resul = dbmd.getPrimaryKeys(null, "empresa", nombreDeLaTablaActual);
			while (resul.next()) {
				System.out.println("	Nombre: " + resul.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void mostrarImportedKeys(String nombreDeLaTablaActual) {
		try {
			ResultSet resultSet = dbmd.getImportedKeys(null, "empresa", nombreDeLaTablaActual);
			mostrarInfoImportedYExportedKeys(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarExportedKeys(String nombreDeLaTablaActual) {
		try {
			ResultSet resultSet = dbmd.getExportedKeys(null, "empresa", nombreDeLaTablaActual);
			mostrarInfoImportedYExportedKeys(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void mostrarInfoImportedYExportedKeys(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			System.out.println("	Tabla: " + resultSet.getString("PKTABLE_NAME") + " Clave: "
					+ resultSet.getString("PKCOLUMN_NAME") + " --> Tabla: " + resultSet.getString("FKTABLE_NAME")
					+ " Clave: " + resultSet.getString("FKCOLUMN_NAME"));
		}
	}

}
