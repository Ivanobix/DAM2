<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Vuelo</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Reserva </span></div>
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
						$identificadorVuelo = $_POST["identificadorVueloGuardado"];
						$identificadorTurista = $_POST["identificadorTuristaGuardado"];
						$clase = $_POST["clase"];
						mysqli_query($link, "UPDATE reserva SET Clase = $clase where Num_Vuelo = $identificadorVuelo and Cod_Turista = '$identificadorTurista';");
						mysqli_close($link);
						echo '<center>Se ha realizado la operación correctamente.</center>';
						echo '<meta http-equiv="refresh" content="2; url= modificar_reserva.php">';
						exit;
					}
					else {
						$reserva = $_POST["reserva"];
						if(strcmp($reserva, 'vacio') == 0) {
							echo '<center>No has seleccionado ninguna reserva.</center>';
							echo '<meta http-equiv="refresh" content="2; url= alta_reserva.php">';
							exit;
						}
						else {
							list($identificadorTurista,$identificadorVuelo,$nombreTurista, $apellidosTurista, $origenVuelo, $destinoVuelo) = explode("/",$reserva);
							$result = mysqli_query($link, "SELECT * from reserva where Num_Vuelo = $identificadorVuelo and Cod_Turista = '$identificadorTurista';");
							$fila = mysqli_fetch_array($result);
							$codTurista = $fila["Cod_Turista"];
							$codVuelo = $fila["Num_Vuelo"];
							$clase = $fila["Clase"];
							mysqli_free_result($result);
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<table style="margin: 0 auto;"><tbody><tr><td>
					
						<input type="hidden" name= "identificadorVueloGuardado" value='<?php echo $identificadorVuelo; ?>'>
						<input type="hidden" name= "identificadorTuristaGuardado" value='<?php echo $identificadorTurista; ?>'>
					
						<!-------------------- Identificación Turista -------------------->
						<fieldset name="identificacion" id="Identificacion" class="clasificacionPequeñaUnica">
						<legend>Datos Personales</legend>
							<label for="Turista">Turista:</label>						
							<select name="turista" id="Turista">
								<?php
									echo '<option value="'.$GLOBALS["codTurista"].'" selected >'.$GLOBALS["nombreTurista"].' // '.$GLOBALS["apellidosTurista"].'</option>';
								?>
							</select>
						</fieldset></td><td>
						<!-------------------- Selección de Vuelo -------------------->
						<fieldset name="seleccionVuelo" id="SeleccionVuelo" class="clasificacionMedia">
						<legend>Selección de Vuelo</legend>
							<fieldset name="vuelos" id="Vuelos">
							<label for="Vuelo">Vuelo:</label>
							<select name="vuelo" id="Vuelo">
								<?php
									echo '<option value="'.$GLOBALS["codVuelo"].'" selected >'.$GLOBALS["origenVuelo"].' // '.$GLOBALS["destinoVuelo"].'</option>';
								?>
							</select>
						</fieldset>	
							
							<fieldset name="preferencias" id="Preferencias">
							<legend>Preferencias</legend>
								<label>Turista</label><input type="radio" name="clase" id= "Clase" value= 0 
								<?php if($clase == 0)  echo "checked= 'checked'"  ?> >
								<label>Primera</label><input type="radio" name="clase" id= "Clase" value= 1
								<?php if($clase == 1)  echo "checked= 'checked'"  ?> >
							</fieldset>
						</fieldset></td></tr></tbody>
					</table>
					<!-------------------- Botones turista -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="modificarVuelo" class="boton animado" >Modificar</button>
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