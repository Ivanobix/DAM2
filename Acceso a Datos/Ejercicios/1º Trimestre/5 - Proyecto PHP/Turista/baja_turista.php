<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Baja Turista</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Baja Turista </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['bajaTurista'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$identificador = $_POST['turista'];
						if(strcmp($identificador, 'vacio') != 0) {
							mysqli_query($link, "delete from turista where Cod_Turista = '$identificador';");
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_turista.php">';
							exit;
						}
						else {
							echo '<center>No se ha seleccionado ningún turista.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_turista.php">';
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
					<!-------------------- Identificación Turista -------------------->
					<fieldset name="seleccionTurista" id="SeleccionTurista" class="clasificacionUnicaPequeña">
					<legend>Selección de Turista</legend>
							<fieldset name="turistas" id="Turistas">
							<legend>Turistas</legend>					
								<label for="Turista">Turista:</label><select name="turista" id="Turista">
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
													echo '<option value="'.$fila[Cod_Turista].'">'.$fila[Nombre].' // '.$fila[Apellidos].'</option>';
												}
												mysqli_free_result($result);
												mysqli_close($link);
											}
										}
									?>
								</select><br/>
							</fieldset>	
					</fieldset>
					<!-------------------- Botones Turista -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button onclick="return deleletconfig()" name="bajaTurista" class="boton animado" >Dar de Baja</button>
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