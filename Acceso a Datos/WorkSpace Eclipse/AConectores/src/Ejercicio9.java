import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Ejercicio9 {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			DatabaseMetaData dbmd = conexion.getMetaData();

			// Exported key
			System.out.println("=====================EXPORTED========================");
			ResultSet resul = dbmd.getExportedKeys(null, "empresa", "departamentos");

			while (resul.next()) {
				String pk_name = resul.getString("PKCOLUMN_NAME");
				String fk_name = resul.getString("FKCOLUMN_NAME");

				String pk_tablename = resul.getString("PKTABLE_NAME");
				String fk_tablename = resul.getString("FKTABLE_NAME");

				System.out.printf("Tabla PK: %s, Clave Primaria: %s %n", pk_tablename, pk_name);
				System.out.printf("Tabla FK: %s, Clave Ajena: %s %n", fk_tablename, fk_name);
			}

			// Imported key
			System.out.println("=====================IMPORTED========================");
			resul = dbmd.getImportedKeys(null, "empresa", "empleados");
			while (resul.next()) {
				String pk_name = resul.getString("PKCOLUMN_NAME");
				String fk_name = resul.getString("FKCOLUMN_NAME");

				String pk_tablename = resul.getString("PKTABLE_NAME");
				String fk_tablename = resul.getString("FKTABLE_NAME");

				System.out.printf("Tabla PK: %s, Clave Primaria: %s %n", pk_tablename, pk_name);
				System.out.printf("Tabla FK: %s, Clave Ajena: %s %n", fk_tablename, fk_name);
			}

			conexion.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
