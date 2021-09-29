import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EjercicioAmpliacion1 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT empleados.apellido, empleados.salario, empleados.oficio FROM empleados, departamentos "
					+ "where empleados.dep_no = departamentos.dept_no and departamentos.dnombre = \"RRHH\";";
			ResultSet resultado = sentencia.executeQuery(sql);

			while (resultado.next()) {
				System.out.println(resultado.getString(1) + " " + resultado.getFloat(2) + " " + resultado.getString(3));
			}

			resultado.close();
			sentencia.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Algo ha ido mal.");
			e.printStackTrace();
		}

	}

}
