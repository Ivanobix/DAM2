<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<link rel="stylesheet" type="text/css" href="../CSS/Listados.css">
		<title>Listar Sucursal</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Listar Sucursales </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['listarSucursal'])) {
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
							$result = mysqli_query($link, "SELECT * from sucursal;");
						}
						else {
							$result = mysqli_query($link, "SELECT * from sucursal where $filtrado like('%$filtro%');");
						}
						echo "
							<table class='listado'>
								<tr>
									<th>Identificador</th>
									<th>Director</th>
									<th>Trabajadores</th>
									<th>Dirección</th>
									<th>Teléfono</th>
								</tr>";
						if($result != false) {
							while(($fila = mysqli_fetch_array($result))!=null){
								echo "
									<tr>
										<td>".$fila['Cod_Sucursal']."</td>
										<td>".$fila['Director']."</td>
										<td>".$fila['Num_Trabajadores']."</td>
										<td>".$fila['Direccion']."</td>
										<td>".$fila['Telefono']."</td>
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
					<!-------------------- Identificación Sucursal -------------------->	
					<fieldset name="seleccionSucursal" id="SeleccionSucursal" class="clasificacionUnicaPequeña">
					<legend>Búsqueda de Sucursal</legend>
							<label for="Filtrado">Filtrar:</label>
							<select name="filtrado" id="Filtrado">
									<option value="vacio" > TODO </option>
									<option value="Cod_Sucursal" > Identificador </option>
									<option value="Director" > Director </option>
									<option value="Num_Trabajadores" > Trabajadores </option>
									<option value="Direccion" > Dirección </option>
									<option value="Telefono" > Teléfono </option>
								</select><br/><br/>	
							
							<label for="Filtro">Condición:</label>
							<input type="text" class = "CampoDatos" name="filtro" size="30" id="Filtro"><br/>
					</fieldset>

					<!-------------------- Botones Sucursal -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="listarSucursal" class="boton animado" >Buscar</button>
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