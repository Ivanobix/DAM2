<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>AAAA</title>
	</head>
	<body>
		<?php
			$file = fopen("archivo.txt", "w");
			fputs($file, "Prueba de escrituraaaa" . PHP_EOL);
			fputs($file, "Esto es una nueva linea de texto" . PHP_EOL);
			fputs($file, "Otra mas" . PHP_EOL);
			fclose($file);
		?>
	</body>
	<footer></footer>
</html>