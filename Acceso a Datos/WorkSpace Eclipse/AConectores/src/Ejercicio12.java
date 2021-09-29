import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio12 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM departamentos");
			ResultSetMetaData dbmd = rs.getMetaData();

			String nombre = "";
			String tipo = "";
			String nula = "";
			int numColumnas = dbmd.getColumnCount();
			int maxAncho = 0;

			System.out.println("Tabla departamentos");
			System.out.println("Número de columnas recuperadas: " + numColumnas);

			for (int i = 1; i <= numColumnas; i++) {
				nombre = dbmd.getColumnName(i);
				tipo = dbmd.getColumnTypeName(i);
				nula = (dbmd.isNullable(i) == 1) ? "SI" : "NO";
				maxAncho = dbmd.getColumnDisplaySize(i);
				System.out.println("Columna: " + i);
				System.out.println("	Nombre: " + nombre);
				System.out.println("	Tipo: " + tipo);
				System.out.println("	¿Puede ser nula?: " + nula);
				System.out.println("	Máximo ancho de la columna: " + maxAncho);
			}

			sentencia.close();
			rs.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
