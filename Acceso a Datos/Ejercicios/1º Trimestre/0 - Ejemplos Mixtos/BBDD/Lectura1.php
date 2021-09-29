<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Lectura 1</title>
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
					$tildes = $link->query("SET NAMES 'utf8'");
					$result = mysqli_query($link, "SELECT * FROM agenda");
					mysqli_data_seek($result, 0);
					$extraido = mysqli_fetch_array($result);
					echo "- Nombre: ".$extraido['nombre']. "<br/>";
					echo "- DNI: ".$extraido['dni']. "<br/>";
					echo "- Apellidos: ".$extraido['apellidos']. "<br/>";
					echo "- Dirección: ".$extraido['direccion']. "<br/>";
					echo "- Tfno: ".$extraido['telefono']. "<br/>";
					echo "- Edad: ".$extraido['edad']. "<br/>";
					mysqli_free_result($result);
					mysqli_close($link);
				}
			}
		?>
	</body>
	<footer></footer>
</html>