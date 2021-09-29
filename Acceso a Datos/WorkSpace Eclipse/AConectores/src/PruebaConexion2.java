import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaConexion2 {
	public static void main(String[] args) {
		try {
			// Cargar el driver versión Marta.
			// Class.forName("com.mysql.jdbc.Driver");

			// Cargar el driver versión actualizada aunque es innecesario.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM departamentos";
			ResultSet resultado = sentencia.executeQuery(sql);

			// Ponemos el puntero al final
			resultado.last();
			// Mostramos el número de registros
			System.out.println("NÚMERO DE FILAS: " + resultado.getRow());
			// Ponemos el puntelo al comienzo
			resultado.beforeFirst();

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van mostrando
			while (resultado.next()) {
				System.out.printf("Fila: " + "%d, %d, %s, %s, %n", resultado.getRow(), resultado.getInt(1),
						resultado.getString(2), resultado.getString(3));
			}
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException sqle) {
			sqle.printStackTrace();
		}

	}
}
