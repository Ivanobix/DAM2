<!DOCTYPE html>
<html>
	<head></head>
	<body>
		<?php
			$nombre = $_POST["nombre"];
			$sexo = $_POST["sexo"];
			$edad = $_POST["edad"];
			$sistema = $_POST["sistema"];
			$aficiones = $_POST["aficiones"];
			
			echo "Registrado el usuario <b>" . $nombre . "</b>. <br>\n";
			echo "Eres <b>" . $sexo . "</b>. <br>\n";
			echo "Tienes <b>" . $edad . "</b> años de edad. <br>\n";
			echo "Tu sistema favorito es <b>" . $sistema . "</b>. <br>\n";
			
			if ($aficiones != "") {
				echo "Tus aficiones son: <br>";
				echo nl2br($aficiones);
			}
			else {
				echo "No tienes aficiones.";
			}
			echo "<br><br><a href='Formulario1.html'>VOLVER AL FORMULARIO</a>"
			
		?>
	</body>
	<footer></footer>
</html>
	