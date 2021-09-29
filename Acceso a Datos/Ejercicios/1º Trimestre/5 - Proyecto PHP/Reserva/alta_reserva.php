<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Reservar Viaje</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Reservar </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['altaReserva'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$vuelo = $_POST["vuelo"];
						$turista = $_POST["turista"];
						$clase = $_POST["clase"];
						if (strcmp($vuelo, 'vacio') != 0 and strcmp($turista, 'vacio') != 0) {
							mysqli_query($link, "INSERT INTO reserva VALUES ($vuelo, '$turista', $clase);");
							mysqli_close($link);
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= alta_reserva.php">';
							exit;
						}
						else {
							echo '<center>Rellena todos los campos.</center>';
							echo '<meta http-equiv="refresh" content="2; url= alta_reserva.php">';
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
					<table style="margin: 0 auto;"><tbody><tr><td>
						<!-------------------- Identificación Cliente -------------------->
						<fieldset name="identificacion" id="Identificacion" class="clasificacionPequeñaUnica">
						<legend>Datos Personales</legend>
							<label for="Turista">Turista:</label>						
							<select name="turista" id="Turista">
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
											$result = mysqli_query($link, "SELECT * from turista;");
											mysqli_data_seek($result, 0);
											while(($fila = mysqli_fetch_array($result))!=null) {
												echo '<option value="'.$fila[Cod_Turista].'">'.$fila[Nombre].' - '.$fila[Apellidos].'</option>';
											}
											mysqli_free_result($result);
											mysqli_close($link);
										}
									}
								?>
							</select>
						</fieldset></td><td>
						<!-------------------- Selección de Vuelo y Clase -------------------->
						<fieldset name="seleccionVuelo" id="SeleccionVuelo" class="clasificacionMedia">
						<legend>Selección de Vuelo</legend>
							<fieldset name="vuelos" id="Vuelos">
							<label for="Vuelo">Vuelo:</label>
							<select name="vuelo" id="Vuelo">
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
											$result = mysqli_query($link, "SELECT * from vuelo;");
											mysqli_data_seek($result, 0);
											while(($fila = mysqli_fetch_array($result))!=null) {
												echo '<option value="'.$fila[Num_Vuelo].'">'.$fila[Origen].' - '.$fila[Destino].'</option>';
											}
											mysqli_free_result($result);
											mysqli_close($link);
										}
									}
								?>
							</select>
						</fieldset>	
							
							<fieldset name="preferencias" id="Preferencias">
							<legend>Preferencias</legend>
								<label>Turista</label><input type="radio" name="clase" id= "Clase" value= 0 checked="checked">
								<label>Primera</label><input type="radio" name="clase" id= "Clase" value= 1>
							</fieldset>
						</fieldset></td></tr></tbody>
					</table>

					<!-------------------- Botones Reserva -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="altaReserva" class="boton animado" >Reservar</button>
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