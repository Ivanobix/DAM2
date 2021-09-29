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
			//Conexión a BBDD con usuario y contraseña.
			if(!$link = mysqli_connect("localhost", "root", "")) {
				die("Error: No se pudo conectar");
			}
			else {
				//Selección de la BBDD.
				if(!mysqli_select_db($link, "empresa")) {
					die ("Error: No existe la base de datos");
				}
				else {
					mysqli_query($link, "truncate table agenda3");
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