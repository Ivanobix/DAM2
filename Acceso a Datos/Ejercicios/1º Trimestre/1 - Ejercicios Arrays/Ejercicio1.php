<html>
	<body>
		<?php
			$cursos[] = array (25, 22, 26);
			$cursos[] = array (21, 12, 14);			
			for ($i = 0; $i < 3; $i++) {
				reset($cursos);
				switch ($i) {
					case 0:
						$rama = 'SMR';
						break;
					case 1:
						$rama = 'ASIR';
						break;
					case 2:
						$rama = 'DAM';
						break;
				}
				$cadena = '';
				$nivel = 'primero';
				$valor = $cursos[0];
				for ($h = 0; $h < 2; $h++) {
					$cadena .= "El numero de alumnos en el nivel $nivel, ciclo $rama, son $valor[$i]. <br>";
					$nivel = 'segundo';
					$valor = $cursos[1];
				}
				echo $cadena;
			}
		?>
	</body>
</html>