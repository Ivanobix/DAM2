<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Escritura</title>
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
					//Insercción de datos
					mysqli_query($link, "insert into agenda values('Marcos', 'López Vega', 'C/Juan Nº7', 658444875, 56, 1.90, '71724191A');");
					mysqli_query($link, "insert into agenda values('Martina', 'Manzano Cabezas', 'C/Acederas nº7', 695561002, 19, 1.99, '71724191B');");
					mysqli_query($link, "insert into agenda values('Pelegrina', 'Solano Castro', 'Avda. Luarcato nº22', 833443213, 49, 1.46, '71724191C');");
					mysqli_query($link, "insert into agenda values('Javier', 'Piestro Rozas', 'Avda. Luarcato nº22', 696118822, 25, 1.67, '71724191D');");
					//Cerrar la BBDD
					mysqli_close($link);
				}
			}
		?>
	</body>
	<footer></footer>
</html>