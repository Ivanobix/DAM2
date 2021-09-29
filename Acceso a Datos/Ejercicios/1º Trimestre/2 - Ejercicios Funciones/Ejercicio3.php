<html>
	<body>
		<?php
			$valorUno = 1000;
			$aumentoUno = 8;
			$valorDos = 10;
			$aumentoDos = 0;
			//Estos datos podrían ser guardados en un Array si la cadena fuera más larga, pero solo 2 no merece la pena.
			function calcularIVA($valor, $aumento) {
				$resultado = 0;
				if ($aumento > 0) {
					$resultado = ($valor*$aumento)/100;
				}
				else {
					$resultado = $valor * 0.21;
				}
				echo "El IVA es $resultado. <br>";
			}
			calcularIVA($valorUno, $aumentoUno);
			calcularIVA($valorDos, $aumentoDos);
		?>
	</body>
</html>