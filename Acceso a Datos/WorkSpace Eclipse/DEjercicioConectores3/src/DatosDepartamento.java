import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosDepartamento {

	public static void main(String[] args) {
		// Prepared Statement
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa2", "root", "");

			String select = "SELECT dnombre, loc from departamentos where dept_no= ? ";
			PreparedStatement sentencia = conexion.prepareStatement(select);
			sentencia.setInt(1, Integer.parseInt(args[0]));

			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				System.out.println(
						"Nombre: " + resultado.getString("dnombre") + " // Localidad: " + resultado.getString("loc"));
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

}
