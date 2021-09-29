<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8"/>
		<meta name="description" content="Agencia de viajes: San Andrés Viajes"/>
		<meta name="keywords" content="Viajes;San;Andrés;Avion;Barco;Tren;Hotel;Destinos"/>
		<meta name="author" content="Iván García Prieto"/>
		<link rel="stylesheet" type="text/css" href="CSS/Cabecera.css">
		<link rel="stylesheet" type="text/css" href="CSS/Botones.css">
		<link rel="stylesheet" type="text/css" href="CSS/Administrar.css">
		<link rel="shortcut icon" href="Media/Icono.png"> 
		<title>Administrar</title>
	</head>
	<body>
		<!-------------------- Banner -------------------->
		<header>
			<div class="wrapper">
				<div class="clip-text clip-text_one">SAN ANDRÉS VIAJES</div>
			</div>
			<nav>
				<a href = "index.html"><button class="boton animado2">|Inicio|</button></a>
				<a href = "Reserva\alta_reserva.php"><button class="boton animado2">|Reservar|</button></a>
				<a href = "Reserva\modificar_reserva.php"><button class="boton animado2">|Modificar Reserva|</button></a>
				<a href = "Reserva\baja_reserva.php"><button class="boton animado2">|Cancelar Reserva|</button></a>
				<a href = "Reserva\listar_reserva.php"><button class="boton animado2">|Mis Reservas|</button></a>
				<a href = "administrar.php"><button class="boton animado2">|Administrar|</button></a>
			</nav>
		</header>
		<div class="separador"><span> Administrar </span></div>
		<!-------------------- Cuerpo -------------------->
		<div id = "ContenidoGeneral">		
			<ul class="menu cf">
				<li>
					<a href="">Reservas</a>
					<ul class="submenu">
						<li><a href="Reserva\alta_reserva.php">Alta</a></li>
						<li><a href="Reserva\baja_reserva.php">Baja</a></li>
						<li><a href="Reserva\modificar_reserva.php">Modificación</a></li>
						<li><a href="Reserva\listar_reserva.php">Listado</a></li>
					</ul>
				</li>
				<li>
					<a href="">Sucursales</a>
					<ul class="submenu">
						<li><a href="Sucursal\alta_sucursal.php">Alta</a></li>
						<li><a href="Sucursal\baja_sucursal.php">Baja</a></li>
						<li><a href="Sucursal\modificar_sucursal.php">Modificación</a></li>
						<li><a href="Sucursal\listar_sucursal.php">Listado</a></li>
					</ul>
				</li>
				<li>
					<a href="">Vuelos</a>
					<ul class="submenu">
						<li><a href="Vuelo\alta_vuelo.php">Alta</a></li>
						<li><a href="Vuelo\baja_vuelo.php">Baja</a></li>
						<li><a href="Vuelo\modificar_vuelo.php">Modificación</a></li>
						<li><a href="Vuelo\listar_vuelo.php">Listado</a></li>
					</ul>
				</li>
				
				<li>
					<a href="">Turistas</a>
					<ul class="submenu">
						<li><a href="Turista\alta_turista.php">Alta</a></li>
						<li><a href="Turista\baja_turista.php">Baja</a></li>
						<li><a href="Turista\modificar_turista.php">Modificación</a></li>
						<li><a href="Turista\listar_turista.php">Listado</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
	<!-------------------- Footer -------------------->
	<footer>
		--- 🌟 Iván García Prieto San Andrés® 🌟 ---
	</footer>
</html>
