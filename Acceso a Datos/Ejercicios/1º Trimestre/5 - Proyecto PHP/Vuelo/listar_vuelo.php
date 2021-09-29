<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<link rel="stylesheet" type="text/css" href="../CSS/Listados.css">
		<title>Listar Vuelo</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Listar Vuelos </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['listarVuelo'])) {
				if(!$link = mysqli_connect("localhost", "root", "")) {
					die("Error: No se pudo conectar");
				}
				else {
					if(!mysqli_select_db($link, "agencia2")) {
						die ("Error: No existe la base de datos");
					}
					else {
						$filtrado = $_POST['filtrado'];
						$filtro = $_POST['filtro'];
						if(strcmp($filtrado,'vacio') == 0) {
							$result = mysqli_query($link, "SELECT * from vuelo;");
						}
						else {
							$result = mysqli_query($link, "SELECT * from vuelo where $filtrado like('%$filtro%');");
						}
						echo "
							<table class='listado'>
								<tr>
									<th>Identificador</th>
									<th>Fecha</th>
									<th>Hora</th>
									<th>Origen</th>
									<th>Destino</th>
									<th>Plazas Totales</th>
									<th>Plazas Turistas</th>
									<th>Sucursal</th>
								</tr>";
						if($result != false) {
							while(($fila = mysqli_fetch_array($result))!=null){
								echo "
									<tr>
										<td>".$fila['Num_Vuelo']."</td>
										<td>".$fila['Fecha']."</td>
										<td>".$fila['Hora']."</td>
										<td>".$fila['Origen']."</td>
										<td>".$fila['Destino']."</td>
										<td>".$fila['Plazas_Totales']."</td>
										<td>".$fila['Plazas_Turistas']."</td>
										<td>".$fila['Cod_Sucursal']."</td>
									</tr>";
							}
							mysqli_free_result($result);
						}
						echo "</table>";
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
					<legend>Búsqueda de Vuelo</legend>
							<label for="Filtrado">Filtrar:</label>
							<select name="filtrado" id="Filtrado">
								<option value="vacio" > TODO </option>
								<option value="Num_Vuelo" > Identificador </option>
								<option value="Fecha" > Fecha </option>
								<option value="Hora" > Hora </option>
								<option value="Origen" > Origen </option>
								<option value="Destino" > Destino </option>
								<option value="Plazas_Totales" > Plazas Totales </option>
								<option value="Plazas_Turistas" > Plazas Turista </option>
								<option value="Sucursal" > Sucursal </option>
							</select><br/><br/>	
							
							<label for="Filtrado">Condición:</label>
							<input type="text" class = "CampoDatos" name="filtro" size="30" id="Filtro"><br/>
					</fieldset>

					<!-------------------- Botones Vuelo -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="listarVuelo" class="boton animado" >Buscar</button>
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