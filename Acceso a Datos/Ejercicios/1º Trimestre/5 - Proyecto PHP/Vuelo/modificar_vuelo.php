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
		<!-------------------- Cuerpo -------------------->
		<div id = "ContenidoGeneral">		
			<form METHOD="post" ACTION="datos_vuelo.php">
				<!-------------------- Identificación Vuelo -------------------->
				<fieldset name="seleccionVuelo" id="SeleccionVuelo" class="clasificacionUnicaPequeña">
				<legend>Selección de Vuelo</legend>
					<fieldset name="vuelos" id="Vuelos">
					<legend>Vuelos</legend>					
						<label for="Vuelos">Vuelo:</label>
						<select name="vuelo" id="Vuelo">
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
						</select><br/>
					</fieldset>	
				</fieldset>
				<!-------------------- Botones Vuelo -------------------->
				<div id = "BotonesEnviarLimpiar">
					<button type="submit" name="seleccionarVuelo" class="boton animado" >Modificar</button>
					<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
				</div>
			</form>
		</div>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>