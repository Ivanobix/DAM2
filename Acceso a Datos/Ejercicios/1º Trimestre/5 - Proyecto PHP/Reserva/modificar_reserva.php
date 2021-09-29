<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Reserva</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Reserva </span></div>
		<!-------------------- Cuerpo -------------------->
		<div id = "ContenidoGeneral">		
			<form METHOD="post" ACTION="datos_reserva.php">
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
											echo '<option value="'.$fila[Cod_Turista].'/'.$fila[Num_Vuelo].'/'.$fila[Nombre].'/'.$fila[Apellidos].'/'.$fila[Origen].'/'.$fila[Destino].'">'.$fila[Nombre].' '.$fila[Apellidos].' // '.$fila[Origen].' - '.$fila[Destino].'</option>';
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
					<button type="submit" name="seleccionarReserva" class="boton animado" >Modificar</button>
					<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
				</div>
			</form>
		</div>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>