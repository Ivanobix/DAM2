<html>
	<body>
		<?php
		$calendario[] = array (1, "enero", 31);
		$calendario[] = array (2, "febrero", 28);
		$calendario[] = array (3, "marzo", 31);
		$calendario[] = array (4, "abril", 30);
		$calendario[] = array (5, "mayo", 31);
		
		while (list($clave, $valor) = each($calendario)) 
		{
		$cadena = $clave . " " . $valor[1];
		$cadena .= " es el mes numero " . $valor[0];
		$cadena .= " y tiene " . $valor[2] . " dias<BR>";
		echo $cadena;
		}
		?>
	</body>
</html>