<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Vuelo</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Vuelo </span></div>
		<?php
			if(!$link = mysqli_connect("localhost", "root", "")) {
				die("Error: No se pudo conectar");
			}
			else {
				if(!mysqli_select_db($link, "agencia2")) {
					die ("Error: No existe la base de datos");
				}
				else {
					if(isset($_POST['modificarVuelo'])) {
						$identificador = $_POST["identificadorGuardado"];
						$codVuelo = $_POST["identificadorVuelo"];
						$fecha = $_POST["fecha"];
						$hora = $_POST["hora"];
						$origen = $_POST["origen"];
						$destino = $_POST["destino"];
						$plazasTotales = $_POST["plazasTotales"];
						$plazasTuristas = $_POST["plazasTuristas"];
						$codSucursal = $_POST["codSucursal"];
						if($plazasTotales >= $plazasTuristas) {
							mysqli_query($link, "UPDATE vuelo SET Origen = '$origen', Destino = '$destino', Fecha = '$fecha', Hora = '$hora', Plazas_Totales = $plazasTotales, Plazas_Turistas = $plazasTuristas, Cod_Sucursal = '$codSucursal' where Num_Vuelo = '$identificador';");
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= modificar_vuelo.php">';
							exit;
							mysqli_close($link);
						}
						else {
							echo '<center>El número de plazas totales es inferior al de plazas tipo turista.</center>';
							echo '<meta http-equiv="refresh" content="2; url= modificar_vuelo.php">';
							exit;
						}
						
					}
					else {
						$identificador = $_POST["vuelo"];
						$result = mysqli_query($link, "SELECT * from vuelo where Num_Vuelo = '$identificador';");
						$fila = mysqli_fetch_array($result);
						$fecha = $fila["Fecha"];
						$hora = $fila["Hora"];
						$origen = $fila["Origen"];
						$destino = $fila["Destino"];
						$plazasTotales = $fila["Plazas_Totales"];
						$plazasTuristas = $fila["Plazas_Turistas"];
						$codSucursal = $fila["Cod_Sucursal"];
						mysqli_free_result($result);
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Modificación Vuelo -------------------->
					<fieldset name="datosVuelo" id="DatosVuelo" class="clasificacionUnicaGigante">
					<legend>Datos Vuelo</legend>	
						<input type="hidden" name= "identificadorGuardado" value='<?php echo $_POST["vuelo"]; ?>'>
						
						<label for="PlazasTotales">Identificador:</label>
						<input type="number" class = "CampoDatos" name="identificadorVuelo" placeholder="Identificador Vuelo" size="40" max= 99999999999 readonly value='<?php echo "$identificador" ?>' id="PlazasTotales">
					
						<label for="Fecha">Fecha:</label>
						<input type="date" name="fecha" id="Fecha" class = "CampoDatos" value='<?php echo "$fecha" ?>' min='<?php echo date("Y-m-d");?>' />
						
						<label for="Hora">Hora:</label>
						<input type="time" name="hora" id="Hora" class = "CampoDatos" value='<?php echo "$hora" ?>'/>
						
						<label for="Origen">Origen:</label>
						<input type="text" class = "CampoDatos" name="origen" placeholder="Origen" size="40" maxlength = 20 value='<?php echo "$origen" ?>' required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{4,20}" id="Origen"><br/>
						
						<label for="Destino">Destino:</label>
						<input type="text" class = "CampoDatos" name="destino" placeholder="Destino" size="40" maxlength = 20 value='<?php echo "$destino" ?>' required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{4,20}" id="Destino"><br/>
						
						<label for="PlazasTotales">Plazas Totales:</label>
						<input type="number" class = "CampoDatos" name="plazasTotales" placeholder="Plazas Totales" size="40" max=1000 value='<?php echo "$plazasTotales" ?>' id="PlazasTotales">
						
						<label for="PlazasTurista">Plazas Turista:</label>
						<input type="number" class = "CampoDatos" name="plazasTuristas" placeholder="Plazas Turista" size="40" max=1000 value='<?php echo "$plazasTuristas" ?>' id="PlazasTurista">
						
						<label for="Sucursal">Sucursal:</label>
						<select name="codSucursal" id="Sucursal">
							<?php
								$result = mysqli_query($link, "SELECT * from sucursal where Cod_Sucursal != '$codSucursal';");
								mysqli_data_seek($result, 0);
								while(($fila = mysqli_fetch_array($result))!=null) {
									echo '<option value="'.$fila[Cod_Sucursal].'">'.$fila[Director].' // '.$fila[Direccion].'</option>';
								}
								$result = mysqli_query($link, "SELECT * from sucursal where Cod_Sucursal = '$codSucursal';");
								mysqli_data_seek($result, 0);
								$fila = mysqli_fetch_array($result);
								echo '<option value="'.$fila[Cod_Sucursal].'" selected >'.$fila[Director].' // '.$fila[Direccion].'</option>';
								mysqli_free_result($result);
								mysqli_close($link);
							?>
						</select><br/>
					</fieldset>
					<!-------------------- Botones turista -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="modificarVuelo" class="boton animado" >Modificar</button>
						<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
					</div>
				</form>
			</div>
		<?php
			}}}
		?>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>