<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Cancelar Reserva</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Cancelar Reserva </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['bajaReserva'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$reserva = $_POST['reserva'];
						if (strcmp($reserva, 'vacio') != 0) {
							$reserva = explode("/",$reserva); 
							$identificadorTurista = $reserva[0];
							$identificadorVuelo = $reserva[1];
							mysqli_query($link, "delete from reserva where Num_Vuelo = $identificadorVuelo and Cod_Turista = '$identificadorTurista';");
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_reserva.php">';
							exit;
							mysqli_close($link);
						}
						else {
							echo '<center>No se ha seleccionado ninguna reserva.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_reserva.php">';
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
					<!-------------------- Identificación Reserva -------------------->
					<fieldset name="seleccionReserva" id="SeleccionReserva" class="clasificacionUnicaPequeña">
					<legend>Selección de Reserva</legend>
						<fieldset name="reservas" id="Reservas">
						<legend>Reservas</legend>
							<label for="Reserva">Reserva:</label>						
							<select name="reserva" id="Reserva">
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
											$result = mysqli_query($link, "SELECT turista.Cod_Turista, turista.Nombre, turista.Apellidos, vuelo.Num_Vuelo, vuelo.Origen, vuelo.Destino from reserva, turista, vuelo where reserva.Num_Vuelo = vuelo.Num_Vuelo and reserva.Cod_Turista = turista.Cod_Turista;");
											mysqli_data_seek($result, 0);
											while(($fila = mysqli_fetch_array($result))!=null) {
												echo '<option value="'.$fila[Cod_Turista].'/'.$fila[Num_Vuelo].'">'.$fila[Nombre].' '.$fila[Apellidos].' // '.$fila[Origen].' - '.$fila[Destino].'</option>';
											}
											mysqli_free_result($result);
											mysqli_close($link);
										}
									}
								?>
							</select>
						</fieldset>	
					</fieldset>
					
					<!-------------------- Botones Reserva -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button onclick="return deleletconfig()" name="bajaReserva" class="boton animado" >Anular</button>
						<script>function deleletconfig(){var del=confirm("¿Estás seguro de eliminar este elemento?");return del;}</script>
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