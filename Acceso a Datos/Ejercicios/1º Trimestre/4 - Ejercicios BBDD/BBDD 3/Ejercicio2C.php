<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Ejercicio2C</title>
	</head>
	<body>
		<?php
			if(!$link = mysqli_connect("localhost", "root", "")) {
				die("Error: No se pudo conectar");
			}
			else {
				if(!mysqli_select_db($link, "empresa")) {
					die ("Error: No existe la base de datos");
				}
				else {
					mysqli_query($link, "insert into agenda3 values('Iván', 'García Prieto', 'C/Av La Libertad', 601100518, 19, 1.80, '71789091Y');");
					mysqli_query($link, "insert into agenda3 values('Diego', 'García Prieto', 'C/Av La Libertad', 601100518, 14, 1.70, '71712341P');");
					$result = mysqli_query($link, "SELECT * from agenda3;");
					mysqli_data_seek($result, 0);
					while(($fila = mysqli_fetch_array($result))!=null) {
						echo implode(' // ',$fila) . '<br>';
					}
					mysqli_free_result($result);
					mysqli_close($link);
				}
			}
		?>
	</body>
	<footer></footer>
</html>