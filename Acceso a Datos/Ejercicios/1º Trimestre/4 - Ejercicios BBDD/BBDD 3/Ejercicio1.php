<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Ejercicio1</title>
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
					//Resultado de la consulta.
					$result = mysqli_query($link, "SELECT * from agenda2 where edad >= 20 and altura >= 1.75;");
					//Posición del puntero al inicio (se puede omitir).
					mysqli_data_seek($result, 0);
					$aDevolver = "";
					//Recorrer todos los elementos desde donde puse el puntero hasta que valga null (el final de la lista).
					while(($fila = mysqli_fetch_array($result))!=null) {
						$aDevolver .= $fila['nombre'] . ' ' . $fila['edad'] . ' ' . $fila['altura'] . '<br>';
					}
					echo nl2br($aDevolver);
					//Vaciar la variable para ahorrar memoria.
					mysqli_free_result($result);
					//Cerrar la BBDD
					mysqli_close($link);
				}
			}
		?>
	</body>
	<footer></footer>
</html>