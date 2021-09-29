<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Sucursal</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Sucursal </span></div>
			<!-------------------- Cuerpo -------------------->
			<div id = "ContenidoGeneral">		
				<form METHOD="post" ACTION="datos_sucursal.php">
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
											//Selección de la BBDD.
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
						<button type="submit" name="seleccionarSucursal" class="boton animado" >Modificar</button>
						<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
					</div>
				</form>
			</div>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>