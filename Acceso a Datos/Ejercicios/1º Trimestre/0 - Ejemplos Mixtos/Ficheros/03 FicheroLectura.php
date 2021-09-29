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
			$file = fopen("apr2/fichero.txt", "r");
			while (!feof($file)) {
				echo fgets($file) . "<br>";
			}
			fclose($file);
		?>
	</body>
	<footer></footer>
</html>