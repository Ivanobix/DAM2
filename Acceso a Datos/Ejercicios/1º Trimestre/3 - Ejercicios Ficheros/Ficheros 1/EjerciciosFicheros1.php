<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>EjercicioFicheros1</title>
	</head>
	<body>
		<?php
			function writeNumeros($listaNumeros, $operacion) {
				$aEscribir = "";
				foreach ($listaNumeros as $valor) {
					$aEscribir .= "$valor\n";
				}
				
				if (strcmp($operacion, "sobreescribir") == 0) {
					file_put_contents("archivo.txt", $aEscribir);
				}
				else if (strcmp($operacion, "agregar") == 0){
					file_put_contents("archivo.txt", $aEscribir, FILE_APPEND);
				}
				else {
					echo "La operación introducida no es valida.";
				}	
			}
			writeNumeros(array(4, 9, 13, 24), "sobreescribir");
		?>
	</body>
	<footer></footer>
</html>