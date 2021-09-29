import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ejercicio6 {

	public static void main(String[] args) {

		// Este ejercicio etá hecho con Derby

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection conexion = DriverManager.getConnection("jdbc:derby:D:/DB/Derby/ejemplo");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * from empleados where dept_no = 30";
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
