<!DOCTYPE html>
<html lang="es">
	<head>
		<?php include("../Includes/head.html");?>
		<link rel="stylesheet" type="text/css" href="../CSS/Listados.css">
		<title>Mis Reservas</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<?php include("../Includes/header.html");?>
		<div class="separador"><span> Mis Reservas </span></div>
		<!-------------------- PHP -------------------->
		<?php
			if(isset($_POST['listarReserva'])) {
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
							$result = mysqli_query($link, "SELECT reserva.Num_Vuelo, vuelo.Origen, vuelo.Destino, reserva.Cod_Turista, CONCAT_WS(' ', turista.Nombre, turista.Apellidos) Nombre, reserva.Clase from reserva, turista, vuelo where reserva.Num_Vuelo = vuelo.Num_Vuelo and turista.Cod_Turista = reserva.Cod_Turista;");
						}
						else if (strcmp($filtrado,'Nombre') == 0 or strcmp($filtrado,'Apellidos') == 0) {
							$result = mysqli_query($link, "SELECT reserva.Num_Vuelo, vuelo.Origen, vuelo.Destino, reserva.Cod_Turista, CONCAT_WS(' ', turista.Nombre, turista.Apellidos) Nombre, reserva.Clase from reserva, turista, vuelo where reserva.Num_Vuelo = vuelo.Num_Vuelo and turista.Cod_Turista = reserva.Cod_Turista and turista.$filtrado like('%$filtro%');");
						}
						else if (strcmp($filtrado,'Origen') == 0 or strcmp($filtrado,'Destino') == 0) {
							$result = mysqli_query($link, "SELECT reserva.Num_Vuelo, vuelo.Origen, vuelo.Destino, reserva.Cod_Turista, CONCAT_WS(' ', turista.Nombre, turista.Apellidos) Nombre, reserva.Clase from reserva, turista, vuelo where reserva.Num_Vuelo = vuelo.Num_Vuelo and turista.Cod_Turista = reserva.Cod_Turista and vuelo.$filtrado like('%$filtro%');");
						}
						else {
							$result = mysqli_query($link, "SELECT reserva.Num_Vuelo, vuelo.Origen, vuelo.Destino, reserva.Cod_Turista, CONCAT_WS(' ', turista.Nombre, turista.Apellidos) Nombre, reserva.Clase from reserva, turista, vuelo where reserva.Num_Vuelo = vuelo.Num_Vuelo and turista.Cod_Turista = reserva.Cod_Turista and $filtrado like('%$filtro%');");
						}
						echo "
							<table class='listado'>
								<tr>
									<th>Identificador Vuelo</th>
									<th>Origen</th>
									<th>Destino</th>
									<th>Identificador Turista</th>
									<th>Nombre</th>
									<th>Clase</th>
								</tr>";
						if($result != false) {
							while(($fila = mysqli_fetch_array($result))!=null){
								echo "
									<tr>
										<td>".$fila['Num_Vuelo']."</td>
										<td>".$fila['Origen']."</td>
										<td>".$fila['Destino']."</td>
										<td>".$fila['Cod_Turista']."</td>
										<td>".$fila['Nombre']."</td>
										<td>".$fila['Clase']."</td>
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
					<!-------------------- Identificación Reserva -------------------->
					<fieldset name="seleccionReserva" id="SeleccionReserva" class="clasificacionUnicaPequeña">
					<legend>Búsqueda de Reserva</legend>
							<label for="Filtrado">Filtrar:</label>
							<select name="filtrado" id="Filtrado">
								<option value="vacio" > TODO </option>
								<option value="Num_Vuelo" > Identificador Vuelo </option>
								<option value="Cod_Turista" > Identificador Turista </option>
								<option value="Clase" > Clase (0 Turista, 1 Primera) </option>
								<option value="Nombre" > Nombre Turista </option>
								<option value="Apellidos" > Apellidos Turista </option>
								<option value="Origen" > Origen Vuelo </option>
								<option value="Destino" > Destino Vuelo </option>
							</select><br/><br/>	
							
							<label for="Filtrado">Condición:</label>
							<input type="text" class = "CampoDatos" name="filtro" size="30" id="Filtro"><br/>
					</fieldset>
			
					<!-------------------- Botones Reserva -------------------->
					<div id = "BotonesEnviarLimpiar">
						<button type="submit" name="listarReserva" class="boton animado" >Consultar</button>
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