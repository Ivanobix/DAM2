<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Alta Turista</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Alta Turista </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['altaTurista'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$codTurista = $_POST["identificadorTurista"];
						$nombre = $_POST["nombre"];
						$apellidos = $_POST["apellidos"];
						$direccion = $_POST["direccion"];
						$telefono = $_POST["telefono"];
						mysqli_query($link, "INSERT INTO turista values('$codTurista', '$nombre', '$apellidos', '$direccion', '$telefono');");			
						mysqli_close($link);
						echo '<center>Se ha realizado la operación correctamente.</center>';
						echo '<meta http-equiv="refresh" content="2; url= alta_turista.php">';
						exit;
					}
				}
			}
			else {
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Identificación Vuelo -------------------->
					<fieldset name="identificacion" id="Identificacion" class="clasificacionUnicaGrande">
						<legend>Datos Personales</legend>				
							<label for="Nombre">Nombre:</label>
							<input type="text" class = "CampoDatos" id="Nombre" name="nombre" placeholder="Nombre" size="20"  maxlength = 20 required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,20})"><br/>
							
							<label for="Apellidos">Apellidos:</label>
							<input type="text" class = "CampoDatos" name="apellidos" placeholder="Apellidos" size="30" id="Apellidos" maxlength = 30 required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})+([ ]{1})+([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})"><br/>
							
							<label for="Direccion">Dirección:</label>
							<input type="text" class = "CampoDatos" name="direccion" placeholder="Dirección" size="40" maxlength = 40 required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{10,40}" id="Direccion"><br/>
							
							<label for="Telefono">Teléfono:</label>
							<input type="text" class = "CampoDatos" name="telefono" placeholder="Teléfono" size="40" required pattern="[0-9]{9}" maxlength= 9 id="Telefono">
							
							<label for="Direccion">Identificador:</label>
							<input type="text" class = "CampoDatos" name="identificadorTurista" value="<?php echo substr(uniqid(), 0, 10);?>" size="40" maxlength = 10 required pattern = "[a-zA-Z0-9]{10,10}" readonly id="IdentificadorTurista"><br/>
					</fieldset>
				
					<!-------------------- Botones Turista -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="altaTurista" class="boton animado" >Dar de Alta</button>
						<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
					</div>
				</form>
			</div>
		<?php
			}
		?>	
	</body>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>