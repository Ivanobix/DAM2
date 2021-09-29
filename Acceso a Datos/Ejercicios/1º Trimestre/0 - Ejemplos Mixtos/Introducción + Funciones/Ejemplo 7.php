<html>
	<body>
		<?php
		$visitas = array("lunes"=>200, "martes"=>186, "miercoles"=>190, "jueves"=>175);
		reset($visitas);
		while (list($clave, $valor) = each ($visitas)) 
		{
			echo "el $clave ha tenido $valor visitadas<BR>";
		}
		
		?>
	</body>
</html>

