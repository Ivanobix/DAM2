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
			$fp = fopen("apr2/fichero.txt", "r");
			$linea = fgets($fp);
			echo $linea;
			fclose($fp);
		?>
	</body>
	<footer></footer>
</html>