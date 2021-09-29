import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EjercicioAmpliacion2 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT empleados.apellido, empleados.salario, departamentos.dnombre FROM empleados, departamentos"
					+ " where empleados.dep_no = departamentos.dept_no;";
			ResultSet resultado = sentencia.executeQuery(sql);

			float salarioMasGrande = 0;
			while (resultado.next()) {
				float salario = resultado.getFloat(2);
				if (salario >= salarioMasGrande) {
					salarioMasGrande = salario;
				}
			}

			resultado.beforeFirst();
			while (resultado.next()) {
				float salario = resultado.getFloat(2);
				if (salario == salarioMasGrande) {
					System.out.println(resultado.getString(1) + " " + salario + " " + resultado.getString(3));
				}

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
