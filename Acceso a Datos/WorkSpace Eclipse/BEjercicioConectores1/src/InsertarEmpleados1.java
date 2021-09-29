import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarEmpleados1 {

	public static void main(String[] args) {
		// Forma cutre, "a pelo"
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// La conexion es con empresa2, pero está como empresa para que le funcione a Marta
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement();

			String insert = "INSERT INTO empleados VALUES ('7698', 'ABAJO', 'ENCARGADO', '0002', '1990-01-01', '2300', '500', '30');";
			int resultado = sentencia.executeUpdate(insert);

			System.out.println("Filas afectadas: " + resultado);

			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
