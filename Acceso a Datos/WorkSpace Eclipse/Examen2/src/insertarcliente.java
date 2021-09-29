import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class insertarcliente {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/clinica", "root", "");

			String numReg = args[0];
			String nombre = args[1];
			String apellidos = args[2] + " " + args[3];
			int numColegiado = 0;

			Statement sentencia = conexion.createStatement();
			String sql = "SELECT NUMCOLEGIADO FROM medicos where NOMBRE = '" + args[4] + "' and APELLIDO1 = '" + args[5]
					+ "';";
			ResultSet resultado = sentencia.executeQuery(sql);
			resultado.next();
			numColegiado = resultado.getInt(1);
			

			String select = "INSERT INTO clientes VALUES (?, ?, ?, ?);";
			PreparedStatement sentenciaInsert = conexion.prepareStatement(select);
			sentenciaInsert.setInt(1, Integer.parseInt(numReg));
			sentenciaInsert.setString(2, nombre);
			sentenciaInsert.setString(3, apellidos);
			sentenciaInsert.setInt(4, numColegiado);

			sentenciaInsert.execute();

			resultado.close();
			sentencia.close();
			sentenciaInsert.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
