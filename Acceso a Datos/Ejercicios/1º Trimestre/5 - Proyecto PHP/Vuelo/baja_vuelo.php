<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?> 
		<title>Baja Vuelo</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Baja Vuelo </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['bajaVuelo'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$identificador = $_POST['vuelo'];
						if(strcmp($identificador, 'vacio') != 0) {
							mysqli_query($link, "delete from vuelo where Num_Vuelo = '$identificador';");
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_vuelo.php">';
							exit;
						}
						else {
							echo '<center>No se ha seleccionado ningún vuelo.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_vuelo.php">';
							exit;
						}
						mysqli_close($link);
					}
				}
			}
			else {
		?>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="<?php echo $_SERVER['PHP_SELF']; ?>">
					<!-------------------- Identificación Vuelo -------------------->
					<fieldset name="seleccionVuelo" id="SeleccionVuelo" class="clasificacionUnicaPequeña">
					<legend>Selección de Vuelo</legend>
							<fieldset name="vuelos" id="Vuelos">
							<legend>Vuelos</legend>					
								<label for="Vuelos">Vuelo:</label>
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
								</select><br/>
							</fieldset>	
					</fieldset>

					<!-------------------- Botones Vuelo -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button onclick="return deleletconfig()" name="bajaVuelo" class="boton animado" >Dar de Baja</button>
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