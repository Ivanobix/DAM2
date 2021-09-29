<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Ejercicio2D</title>
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
					mysqli_query($link, "insert into agenda3 values('Iván', 'García Prieto', 'C/Av La Libertad', 601100518, 19, 1.80, '71724191W');");
					mysqli_query($link, "insert into agenda3 values('Diego', 'García Prieto', 'C/Av La Libertad', 601200518, 14, 1.70, '717124192A');");
					mysqli_query($link, "insert into agenda3 values('Luis', 'García Gómez', 'C/Av La Libertad', 675087658, 51, 1.84, '09776330L');");
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