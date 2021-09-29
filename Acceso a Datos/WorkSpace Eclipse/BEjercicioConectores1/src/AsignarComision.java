import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AsignarComision {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// La conexion es con empresa2, pero está como empresa para que le funcione a Marta
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement();

			String departamentoVentas = "(SELECT dept_no from departamentos WHERE dnombre = 'VENTAS')";
			String update1 = "UPDATE empleados SET salario = salario + 200 WHERE departamento = " + departamentoVentas
					+ " AND dir = 7698";
			String update2 = "UPDATE empleados SET salario = salario + 100 WHERE departamento = " + departamentoVentas
					+ " AND dir != 7698 AND oficio != 'JEFE'";

			int resultado = sentencia.executeUpdate(update1);
			resultado += sentencia.executeUpdate(update2);

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
