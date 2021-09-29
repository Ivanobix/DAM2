<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Turista</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Turista </span></div>
		<?php
			if(!$link = mysqli_connect("localhost", "root", "")) {
				die("Error: No se pudo conectar");
			}
			else {
				if(!mysqli_select_db($link, "agencia2")) {
					die ("Error: No existe la base de datos");
				}
				else {
					if(isset($_POST['modificarTurista'])) {
						$identificador = $_POST["identificadorGuardado"];
						$codTurista = $_POST["identificadorTurista"];
						$nombre = $_POST["nombre"];
						$apellidos = $_POST["apellidos"];
						$direccion = $_POST["direccion"];
						$telefono = $_POST["telefono"];
						mysqli_query($link, "UPDATE turista SET Cod_Turista = '$codTurista', Nombre = '$nombre', Apellidos = '$apellidos', Direccion = '$direccion', Telefono = '$telefono' where Cod_Turista = '$identificador';");
						mysqli_close($link);
						echo '<center>Se ha realizado la operación correctamente.</center>';
						echo '<meta http-equiv="refresh" content="2; url= modificar_turista.php">';
						exit;
						
					}
					else {
						$identificador = $_POST["turista"];
						if(strcmp($identificador, 'vacio') == 0) {
							echo '<center>No se ha seleccionado ningún turista.</center>';
							echo '<meta http-equiv="refresh" content="2; url= modificar_turista.php">';
							exit;
						}
						else {
							$result = mysqli_query($link, "SELECT * from turista where Cod_turista = '$identificador';");
							$fila = mysqli_fetch_array($result);
							$nombre = $fila['Nombre'];
							$apellidos = $fila["Apellidos"];
							$direccion = $fila["Direccion"];
							$telefono = $fila["Telefono"];
							mysqli_free_result($result);
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Modificación Turista -------------------->
					<fieldset name="identificacion" id="Identificacion" class="clasificacionUnicaGrande">
					<legend>Datos Turista</legend>		
						<input type="hidden" name= "identificadorGuardado" value='<?php echo $_POST["turista"]; ?>'>	

						<label for="Nombre">Nombre:</label>
						<input type="text" class = "CampoDatos" id="Nombre" name="nombre" placeholder="Nombre" size="20"  maxlength = 20 value='<?php echo "$nombre" ?>' required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,20})"><br/>
						
						<label for="Apellidos">Apellidos:</label>
						<input type="text" class = "CampoDatos" name="apellidos" placeholder="Apellidos" size="30" id="Apellidos" maxlength = 30 value='<?php echo "$apellidos" ?>' required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})+([ ]{1})+([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})"><br/>
						
						<label for="Direccion">Dirección:</label>
						<input type="text" class = "CampoDatos" name="direccion" placeholder="Dirección" size="40" maxlength = 40 value='<?php echo "$direccion" ?>' required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{10,40}" id="Direccion"><br/>
						
						<label for="Telefono">Teléfono:</label>
						<input type="text" class = "CampoDatos" name="telefono" placeholder="Teléfono" size="40" value='<?php echo "$telefono" ?>' maxlength = 9 required pattern="[0-9]{9}" id="Telefono">
						
						<label for="Direccion">Identificador:</label>
						<input type="text" class = "CampoDatos" name="identificadorTurista" placeholder="Identificador" size="40" maxlength = 10 readonly value='<?php echo "$identificador" ?>' required pattern = "[a-zA-Z0-9]{10,10}" id="Identificadorturista"><br/>

					</fieldset>
					<!-------------------- Botones turista -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="modificarTurista" class="boton animado" >Modificar</button>
						<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
					</div>
				</form>
			</div>
		<?php
			}}}}
		?>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>