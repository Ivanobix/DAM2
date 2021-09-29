import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ejercicio5 {

	public static void main(String[] args) {

		// Este ejercicio está hecho con HSQLDB

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:file:D:/DB/hsqldb/data/ejemplo/ejemplo");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * from empleados limit 1";
			ResultSet resultado = sentencia.executeQuery(sql);

			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + " " + resultado.getString(2) + " " + resultado.getString(3)
						+ " " + resultado.getInt(4) + " " + resultado.getDate(5) + " " + resultado.getFloat(6) + " "
						+ resultado.getFloat(7) + " " + resultado.getInt(8));
			}

			resultado.close();
			sentencia.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Algo ha ido mal.");
			e.printStackTrace();
		}

	}

}
