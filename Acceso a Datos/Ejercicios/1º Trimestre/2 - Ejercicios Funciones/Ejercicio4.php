<html>
	<body>
		<?php
			$valorUno = 14;
			$valorDos = 16;
			$valorTres = 324;
			$valorCuatro = 545439;
			//Estos datos podrían ser guardados en un Array si la cadena fuera más larga, pero solo 2 no merece la pena.
			function calcularMedia($valorPrimero, $valorSegundo) {
				$resultado = 0;
				$resultado = ($valorPrimero + $valorSegundo) / 2;
				echo "La media es $resultado. <br>";
			}
			calcularMedia($valorUno, $valorDos);
			calcularMedia($valorTres, $valorCuatro);
		?>
	</body>
</html>