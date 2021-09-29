<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Sucursal</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Sucursal </span></div>
		<?php
			if(!$link = mysqli_connect("localhost", "root", "")) {
				die("Error: No se pudo conectar");
			}
			else {
				if(!mysqli_select_db($link, "agencia2")) {
					die ("Error: No existe la base de datos");
				}
				else {
					if(isset($_POST['modificarSucursal'])) {
						$identificador = $_POST["identificador"];
						$codSucursal = $_POST["identificadorSucursal"];
						$director = $_POST["director"];
						$trabajadores = $_POST["trabajadores"];
						$direccion = $_POST["direccion"];
						$telefono = $_POST["telefono"];
						mysqli_query($link, "UPDATE sucursal SET Cod_Sucursal = '$codSucursal', Director = '$director', Num_Trabajadores = '$trabajadores', Direccion = '$direccion', Telefono = '$telefono' where Cod_Sucursal = '$identificador';");
						echo '<center>Se ha realizado la operación correctamente.</center>';
						echo '<meta http-equiv="refresh" content="2; url= modificar_sucursal.php">';
						exit;
						mysqli_close($link);
					}
					else {
						$identificador = $_POST["sucursal"];
						if(strcmp($identificador, 'vacio') == 0) {
							echo '<center>No se ha seleccionado ninguna sucursal.</center>';
							echo '<meta http-equiv="refresh" content="2; url= modificar_sucursal.php">';
							exit;
						}
						else {
							$result = mysqli_query($link, "SELECT * from sucursal where Cod_Sucursal = '$identificador';");
							$fila = mysqli_fetch_array($result);
							$director = $fila['Director'];
							$trabajadores = $fila["Num_Trabajadores"];
							$direccion = $fila["Direccion"];
							$telefono = $fila["Telefono"];
							mysqli_free_result($result);
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Modificación Sucursal -------------------->
					<fieldset name="datosSucursal" id="DatosSucursal" class="clasificacionUnicaGrande">
					<legend>Datos Sucursal</legend>				
						<input type="hidden" name= "identificador" value='<?php echo $_POST["sucursal"]; ?>'>

						<label for="Director">Director:</label>
						<input type="text" class = "CampoDatos" name="director" placeholder="Director" size="30" id="Director" maxlength = 30 required pattern="([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})+([ ]{1})+([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]{2,30})" value='<?php echo "$director" ?>'><br/>
						
						<label for="Direccion">Dirección:</label>
						<input type="text" class = "CampoDatos" name="direccion" placeholder="Dirección" size="40" maxlength = 40 required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ']{10,40}" id="Direccion" value='<?php echo "$direccion" ?>'><br/>
						
						<label for="Telefono">Teléfono:</label>
						<input type="text" class = "CampoDatos" name="telefono" placeholder="Teléfono" size="40" required pattern="[0-9]{9}" maxlength= 9 id="Telefono" value='<?php echo "$telefono" ?>'>
						
						<label for="Trabajadores">Trabajadores:</label>
						<input type="number" class = "CampoDatos" name="trabajadores" placeholder="Trabajadores" size="40" min= 0 max= 999999 id="Trabajadores" value='<?php echo "$trabajadores" ?>'>
						
						<label for="Direccion">Identificador:</label>
						<input type="text" class = "CampoDatos" name="identificadorSucursal" placeholder="Identificador" size="40" maxlength = 10 readonly required pattern = "[a-zA-Z0-9]{10,10}" id="IdentificadorSucursal" value='<?php echo "$identificador" ?>'><br/>
					</fieldset>
					<!-------------------- Botones Sucursal -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="modificarSucursal" class="boton animado" >Modificar</button>
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