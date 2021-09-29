<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Lectura 4</title>
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
					$result = mysqli_query($link, "SELECT nombre from agenda where nombre like('M%') ");
					//Posición del puntero al inicio (se puede omitir).
					mysqli_data_seek($result, 0);
					$cont = 0;
					//Recorrer todos los elementos desde donde puse el puntero hasta que valga null (el final de la lista).
					while(($fila = mysqli_fetch_array($result))!=null) {					
						echo $fila['nombre']."<br/>";
						echo "<br/>";
						$cont++;
					}
					echo " En la base de datos los nombres que empiezan por M son: $cont";
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