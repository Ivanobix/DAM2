import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Ejercicio7 {

	public static void main(String[] args) {
		// EjemploDatabaseMetadata
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet resul = null;

			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.println("Nombre: " + nombre);
			System.out.println("Driver: " + driver);
			System.out.println("URL: " + url);
			System.out.println("Usuario: " + usuario);

			// No me funciona como en el libro, debería ser null, empresa, null, null
			resul = dbmd.getTables("empresa", null, null, null);

			while (resul.next()) {
				String catalogo = resul.getString(1);
				String esquema = resul.getString(2);
				String tabla = resul.getString(3);
				String tipo = resul.getString(4);

				System.out.printf("%s - Catálogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
			}
			conexion.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
