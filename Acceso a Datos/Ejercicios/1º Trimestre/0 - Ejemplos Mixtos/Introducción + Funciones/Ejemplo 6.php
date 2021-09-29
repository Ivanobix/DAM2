<html>
	<body>
		<?php
		$semana = array("lunes", "martes", "mi&eacute;rcoles", "jueves", "viernes", "sabado", "domingo");
		echo count($semana) ." en la semana <BR>";
		reset ($semana);
		echo current($semana)."<BR>";
		next($semana);
		echo pos($semana)."<BR>";
		end ($semana);
		echo pos ($semana)."<BR>";
		prev($semana);
		echo current($semana);
		
		//current y pos es lo mismo
		
		?>
	</body>
</html>			