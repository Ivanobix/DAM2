<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Alta Vuelo</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Alta Vuelo </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['altaVuelo'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$fecha = $_POST["fecha"];
						$hora = $_POST["hora"];
						$origen = $_POST["origen"];
						$destino = $_POST["destino"];
						$plazasTotales = $_POST["plazasTotales"];
						$plazasTurista = $_POST["plazasTurista"];
						$codSucursal = $_POST["codSucursal"];
						if (strcmp($codSucursal, 'vacio') != 0) {
							if($plazasTotales >= $plazasTurista) {
								mysqli_query($link, "INSERT INTO vuelo VALUES (DEFAULT, '$fecha', '$hora', '$origen', '$destino', $plazasTotales, $plazasTurista, '$codSucursal');");			
								mysqli_close($link);
								echo '<center>Se ha realizado la operación correctamente.</center>';
								echo '<meta http-equiv="refresh" content="2; url= alta_vuelo.php">';
								exit;
							}
							else {
								echo '<center>El número de plazas totales es inferior al de plazas tipo turista.</center>';
								echo '<meta http-equiv="refresh" content="2; url= alta_vuelo.php">';
								exit;
							}
						}
						else {
							echo '<center>No se ha seleccionado ninguna sucursal.</center>';
							echo '<meta http-equiv="refresh" content="2; url= alta_vuelo.php">';
							exit;
						}
						
					}
				}
			}
			else {
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Identificación Vuelo -------------------->
					<fieldset name="datosVuelo" id="DatosVuelo" class="clasificacionUnicaGigante">
					<legend>Datos Vuelo</legend>				
						<label for="Fecha">Fecha:</label>
						<input type="date" name="fecha" id="Fecha" class = "CampoDatos" min='<?php echo date("Y-m-d");?>' value= '<?php echo date("Y-m-d");?>' />
						
						<label for="Hora">Hora:</label>
						<input type="time" name="hora" id="Hora" value="00:00" class = "CampoDatos" />
						
						<label for="Origen">Origen:</label>
						<input type="text" class = "CampoDatos" name="origen" placeholder="Origen" size="40" maxlength = 20 required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{4,20}" id="Origen"><br/>
						
						<label for="Destino">Destino:</label>
						<input type="text" class = "CampoDatos" name="destino" placeholder="Destino" size="40" maxlength = 20 required pattern = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜºª ]{4,20}" id="Destino"><br/>
						
						<label for="PlazasTotales">Plazas Totales:</label>
						<input type="number" class = "CampoDatos" name="plazasTotales" placeholder="Plazas Totales" size="40" min= 0 max= 1000 value= 0 id="PlazasTotales">
						
						<label for="PlazasTurista">Plazas Turista:</label>
						<input type="number" class = "CampoDatos" name="plazasTurista" placeholder="Plazas Turista" size="40" min = 0 max= 1000 value= 0 id="PlazasTurista">
						
						<label for="Sucursal">Sucursal:</label>
						<select name="codSucursal" id="Sucursal">
							<option value="vacio" ></option>
							<?php
								if(!$link = mysqli_connect("localhost", "root", "")) {
									die("Error: No se pudo conectar");
								}
								else {
									if(!mysqli_select_db($link, "agencia2")) {
										die ("Error: No existe la base de datos");
									}
									else {
										$result = mysqli_query($link, "SELECT * from sucursal;");
										mysqli_data_seek($result, 0);
										while(($fila = mysqli_fetch_array($result))!=null) {
											echo '<option value="'.$fila[Cod_Sucursal].'">'.$fila[Director].' // '.$fila[Direccion].'</option>';
										}
										mysqli_free_result($result);
										mysqli_close($link);
									}
								}
							?>
						</select><br/>
						
					</fieldset>
				
					<!-------------------- Botones Vuelo -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="altaVuelo" class="boton animado" >Dar de Alta</button>
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