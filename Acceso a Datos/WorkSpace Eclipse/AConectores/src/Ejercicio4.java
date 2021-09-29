import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ejercicio4 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM empleados where dir = 7698;";
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
