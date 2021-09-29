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
			function leerFichero($ruta) {
				$texto = file_get_contents($ruta);
				$texto = nl2br($texto);
				echo $texto;
			}
			writeNumeros(array(12, 18, 4), "sobreescribir");
			leerFichero("archivo.txt");
			writeNumeros(array(3, 16, 26), "agregar");
			leerFichero("archivo.txt");
			writeNumeros(array(4, 9, 2), "sobreescribir");
			leerFichero("archivo.txt");
			echo "Gracias por usar mi programa, hasta la próxima!";		
		?>
	</body>
	<footer></footer>
</html>