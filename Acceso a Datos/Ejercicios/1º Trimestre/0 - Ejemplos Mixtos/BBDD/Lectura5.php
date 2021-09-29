<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Lectura 5</title>
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
					$result = mysqli_query($link, "select * from ciudades;");
					//Posición del puntero al inicio (se puede omitir).
					mysqli_data_seek($result, 0);
					$sumHabitantes = 0;
					$sumMetro = 0;
					$nombresPaises = "";
					//Recorrer todos los elementos desde donde puse el puntero hasta que valga null (el final de la lista).
					while(($fila = mysqli_fetch_array($result))!=null) {					
						$nombresPaises .= $fila['ciudad'] . ", ";
						$sumHabitantes += $fila['habitantes'];
						if ($fila['tieneMetro'] == 1) {
							$sumMetro++;
						}
					}
					$nombresPaises = substr($nombresPaises, 0, strlen($nombresPaises)-2);
					echo "La suma de habitantes de $nombresPaises es: $sumHabitantes habitantes. De estas ciudades tienen metro: $sumMetro ciudades.";
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