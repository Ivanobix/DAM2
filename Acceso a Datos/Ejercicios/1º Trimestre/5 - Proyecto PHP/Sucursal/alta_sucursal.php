<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Alta Sucursal</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Alta Sucursal </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['altaSucursal'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$codSucursal = $_POST["identificadorSucursal"];
						$director = $_POST["director"];
						$trabajadores = $_POST["trabajadores"];
						$direccion = $_POST["direccion"];
						$telefono = $_POST["telefono"];
						mysqli_query($link, "INSERT INTO sucursal values('$codSucursal', '$director', $trabajadores, '$direccion', '$telefono');");
						mysqli_close($link);
						echo '<center>Se ha realizado la operación correctamente.</center>';
						echo '<meta http-equiv="refresh" content="2; url= alta_sucursal.php">';
						exit;
					}
				}
			}
			else {
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
						<!-------------------- Identificación Sucursal -------------------->
						<fieldset name="datosSucursal" id="DatosSucursal" class="clasificacionUnicaGrande">
						<legend>Datos Sucursal</legend>				

							<label for="Director">Director:</label>
							<input type="text" class = "CampoDatos" name="director" placeholder="Director" size="30" id="Director" maxlength = 30 required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})+([ ]{1})+([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})"><br/>
							
							<label for="Direccion">Dirección:</label>
							<input type="text" class = "CampoDatos" name="direccion" placeholder="Dirección" size="40" maxlength = 40 required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{10,40}" id="Direccion"><br/>
							
							<label for="Telefono">Teléfono:</label>
							<input type="text" class = "CampoDatos" name="telefono" placeholder="Teléfono" size="40" maxlength= 9 required pattern="[0-9]{9}" id="Telefono">
							
							<label for="Trabajadores">Trabajadores:</label>
							<input type="number" class = "CampoDatos" name="trabajadores" placeholder="Trabajadores" size="40" min= 0 max= 999999 value= 0 id="Trabajadores">
							
							<label for="Identificador">Identificador:</label>
							<input type="text" class = "CampoDatos" name="identificadorSucursal" value="<?php echo substr(uniqid(), 3, 13);?>" size="40" maxlength = 10 required pattern = "[a-zA-Z0-9]{10,10}" readonly id="IdentificadorSucursal"><br/>
							
						</fieldset>
				
					<!-------------------- Botones Sucursal -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="altaSucursal" class="boton animado" >Dar de Alta</button>
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