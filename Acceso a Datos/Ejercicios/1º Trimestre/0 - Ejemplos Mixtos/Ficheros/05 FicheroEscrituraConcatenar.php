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
			$file = fopen("archivo.txt", "a");
			fputs($file, "Agregamos un 1" . PHP_EOL);
			fwrite($file, "Agregamos un 2" . PHP_EOL);
			fclose($file);
		?>
	</body>
	<footer></footer>
</html>