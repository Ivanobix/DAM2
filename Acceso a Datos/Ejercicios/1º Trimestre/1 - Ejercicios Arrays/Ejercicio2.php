<html>
	<body>
		<?php
			$numeros[] = array(2, 3, 4);
			$numeros[] = array(5, 6, 7);
			$numeros[] = array(1, 8, 9);
			$matriz = '';
			$suma = 0;
			foreach ($numeros as $valor) {
				$matriz .= "$valor[0] $valor[1] $valor[2] <br>";
				$suma += $valor[0] + $valor[1] + $valor[2];
			}
			echo $matriz;
			echo "<br><br> La suma de la matriz es $suma.";
		?>
	</body>
</html>