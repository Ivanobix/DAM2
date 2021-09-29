<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Baja Sucursal</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Baja Sucursal </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['bajaSucursal'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$identificador = $_POST['sucursal'];
						if(strcmp($identificador, 'vacio') != 0) {
							mysqli_query($link, "delete from sucursal where Cod_Sucursal = '$identificador';");
							echo '<center>Se ha realizado la operación correctamente.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_sucursal.php">';
							exit;
						}
						else {
							echo '<center>No se ha seleccionado ninguna sucursal.</center>';
							echo '<meta http-equiv="refresh" content="2; url= baja_sucursal.php">';
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
					<!-------------------- Identificación Sucursal -------------------->
					<fieldset name="seleccionSucursal" id="SeleccionSucursal" class="clasificacionUnicaPequeña">
					
					<legend>Selección de Sucursal</legend>
							<fieldset name="sucursales"  class= "clasificacionunicaPequeña" id="Sucursales">
							<legend>Sucursales</legend>					
								<label for="Sucursal">Sucursal:</label>
								<select name="sucursal" id="Sucursal">
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
					</fieldset>

					<!-------------------- Botones Sucursal -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button onclick="return deleletconfig()" name="bajaSucursal" class="boton animado" >Dar de Baja</button>
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