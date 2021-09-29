import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarEmpleados2 {

	public static void main(String[] args) {
		// Forma decente, por argumentos
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// La conexion es con empresa2, pero está como empresa para que le funcione a Marta
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement();

			String insert = "INSERT INTO empleados VALUES ('" + args[0] + "', '" + args[1] + "', '" + args[2] + "', '"
					+ args[3] + "', '" + args[4] + "', '" + args[5] + "', '" + args[6] + "', '" + args[7] + "');";
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
