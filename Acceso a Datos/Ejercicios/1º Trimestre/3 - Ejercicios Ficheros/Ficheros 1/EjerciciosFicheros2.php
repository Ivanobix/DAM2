<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>EjercicioFicheros2</title>
	</head>
	<body>
		<?php
			function leerFichero($ruta) {
				$texto = file_get_contents($ruta);
				$texto = nl2br($texto);
				echo $texto;
			}
			leerFichero("archivo.txt");
		?>
	</body>
	<footer></footer>
</html>