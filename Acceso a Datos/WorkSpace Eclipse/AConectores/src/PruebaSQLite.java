import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PruebaSQLite {

	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:D:/DB/SQLITE/ejemplo.db");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * from departamentos";
			ResultSet resultado = sentencia.executeQuery(sql);

			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + " " + resultado.getString(2) + " " + resultado.getString(3));
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
