<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Iván García Prieto"/>
		<meta name="keywords" content="Ivan;Garcia;Prieto;DAM;San;Andres;"/>
		<title>Formulario2</title>
	</head>
	<body>
		<?php
			if(isset($_POST['enviar'])) {
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
			}
			else {
		?>
	
		<FORM METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
			<input name="edad" type="hidden" value=35>
			Este formulario se llamará <b>Formulario1.html</b> y redirigirá los datos a un fichero <b>mis_datos.php</b><br>
			En tu formulario introducirás un campo oculto de edad con 35 años.<br><br>
			
			<label for="Nombre">Tu Nombre </label><input type="text" name="nombre" size="40"><br><br>	
			
			<label for="sistema">Tu Sistema Favorito </label><select name="sistema">
				<option selected value="Linux" > Linux </option>
				<option value="Windows" > Windows </option>
				<option value="MacOS" > MacOS </option>
				<option value="Ninguno" ></option></select><br><br>	

			<legend>¿Tu sexo?</legend>
				<blockquote>
					<label>Hombre</label><input type="radio" name="sexo" checked="checked" value="Hombre"><br/>
					<label>Mujer</label><input type="radio" name="sexo" value="Mujer">
				</blockquote>
				
			<legend>Aficiones</legend>
				<textarea cols="40" rows="5" name="aficiones"></textarea><br><br>
			
			<input type="submit" value="Enviar" name= "enviar"/>
			<input type="reset" value="Limpiar" name = "limpiar"/>
		</FORM>	
		<?php
		}
		?>
	</body>
</html>