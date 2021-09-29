import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FuncionDatosDep {

	public static void main(String[] args) {
		// Funcion con parametros de entrada y salida
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Cambiar empresa por empresa2 después de entregar
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa2", "root", "");

			String codEmpleado = args[0];
			String sql = "{ ? = call getApellidoFunc (?) } ";

			CallableStatement llamada = conexion.prepareCall(sql);
			llamada.registerOutParameter(1, Types.VARCHAR);
			llamada.setInt(2, Integer.parseInt(codEmpleado));

			ResultSet resultado = llamada.executeQuery();
			while (resultado.next()) {
				System.out.println("Apellido: " + resultado.getString(1));
			}

			resultado.close();
			llamada.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
