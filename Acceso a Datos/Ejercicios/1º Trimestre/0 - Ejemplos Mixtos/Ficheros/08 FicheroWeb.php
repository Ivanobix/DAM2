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
			$texto1 = file_get_contents("Formulario2.php");
			$archivo = fopen("texto.txt", "w");
			fwrite($archivo, $texto1);
			fclose($archivo);
			echo "<h2> El codigo recuperado es el siguiente: </h2><div style ='border: solid 2px black'>";
			echo highlight_file("texto.txt");
			echo "</div>";
		?>
	</body>
	<footer></footer>
</html>