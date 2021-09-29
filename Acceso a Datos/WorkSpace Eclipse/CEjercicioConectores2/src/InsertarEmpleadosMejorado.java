import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class InsertarEmpleadosMejorado {

	public static void main(String[] args) {
		// Forma correcta, por argumentos y con comprobaciones

		if (args.length != 8) {
			System.out.println("Faltan datos.");
		} else {
			String fecha_alt = args[4];
			if (!fecha_alt.equals(LocalDate.now().toString())) {
				System.out.println("Debes introducir la fecha de hoy.");
			} else {
				// Comprobar salario
				String salario = args[5];
				if (Integer.parseInt(salario) <= 0) {
					System.out.println("El salario debe ser mayor a 0€");
				} else {
					// Comprobar apellido
					String apellido = args[1];
					if (apellido.equals("null") || apellido.equals("")) {
						System.out.println("El apellido no puede ser nulo.");
					} else {
						// Comprobar oficio
						String oficio = args[2];
						if (oficio.equals("null") || oficio.equals("")) {
							System.out.println("El oficio no puede ser nulo.");
						} else {
							// Realizar la conexión
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								// La conexion es con empresa2, pero está como empresa para que le funcione a
								// Marta
								Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa2",
										"root", "");
								Statement sentencia = conexion.createStatement();

								String departamento = args[7];
								// Comprobar departamento
								String comprobar = "SELECT count(dept_no) FROM departamentos WHERE dept_no ="
										+ departamento + ";";
								ResultSet result = sentencia.executeQuery(comprobar);
								int existe = 0;
								while (result.next()) {
									existe = result.getInt(1);
								}
								if (existe == 0) {
									System.out.println("El departamento no existe.");
								} else {
									// Comprobar numero de empleado
									String emp_no = args[0];
									comprobar = "SELECT count(emp_no) FROM empleados WHERE emp_no = " + emp_no + ";";
									result = sentencia.executeQuery(comprobar);
									existe = 0;
									while (result.next()) {
										existe = result.getInt(1);
									}
									if (existe == 1) {
										System.out.println("El número de empleado ya existe.");
									} else {
										// Comprobar director
										String dir = args[3];
										comprobar = "SELECT count(emp_no) from empleados where emp_no = " + dir + ";";
										result = sentencia.executeQuery(comprobar);
										existe = 0;
										while (result.next()) {
											existe = result.getInt(1);
										}
										if (existe == 1) {
											System.out.println("El director no existe");
										} else {
											// Realizar insercción
											String comision = args[6];
											String insert = "INSERT INTO empleados VALUES ('" + emp_no + "', '"
													+ apellido + "', '" + oficio + "', '" + dir + "', '" + fecha_alt
													+ "', '" + salario + "', '" + comision + "', '" + departamento
													+ "');";
											int resultado = sentencia.executeUpdate(insert);

											System.out.println("Filas afectadas: " + resultado);
										}
									}
								}
								sentencia.close();
								conexion.close();

							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
