<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<title>Modificar Turista</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Modificar Turista </span></div>
		<!-------------------- Cuerpo -------------------->
		<div id = "ContenidoGeneral">		
			<form METHOD="post" ACTION="datos_turista.php">
				<!-------------------- Identificación Turista -------------------->
					<fieldset name="seleccionTurista" id="SeleccionTurista" class="clasificacionUnicaPequeña">
					<legend>Selección de Turista</legend>
							<fieldset name="turistas" id="Turistas">
							<legend>Turistas</legend>					
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
					<button type="submit" name="seleccionarTurista" class="boton animado" >Modificar</button>
					<button type="reset" name="limpiar" class="boton animado" >Limpiar</button>
				</div>
			</form>
		</div>
	<!-------------------- Footer -------------------->
	<?php include("../Includes/footer.html");?>
</html>