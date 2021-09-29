<html>
	<body>
		<?php
			$clase[] = array('Alfonso', 8, 7, 7);
			$clase[] = array('Rebeca', 7, 6, 8);
			$clase[] = array('Ana', 9, 6, 3);
			$clase[] = array('Rodrigo', 5, 5, 6);
			$clase[] = array('Manuel', 3, 9, 4);
			
			
			
			//A - Alumno con mayor nota en cada materia.
			$mates = array('Nadie', 0);
			$lengua = array('Nadie', 0);
			$dibujo = array('Nadie', 0);
			foreach($clase as $alumno) {
				if($alumno[1] >= $mates[1]) {
					$mates[0] = $alumno[0];
					$mates[1] = $alumno[1];
				}
				if($alumno[2] >= $lengua[1]) {
					$lengua[0] = $alumno[0];
					$lengua[1] = $alumno[2];
				}
				if($alumno[3] >= $dibujo[1]) {
					$dibujo[0] = $alumno[0];
					$dibujo[1] = $alumno[3];
				}
			}
			echo "El alumno con mas nota en mates es $mates[0] con un $mates[1]. <br>";
			echo "El alumno con mas nota en lengua es $lengua[0] con un $lengua[1]. <br>";
			echo "El alumno con mas nota en dibujo es $dibujo[0] con un $dibujo[1]. <br>";
			
			
			
			//B - Nota media de cada alumno en las 3 asignaturas.
			echo '<br><br><br><br><br>';
			foreach($clase as $alumno) {
				$nota = round(($alumno[1] + $alumno[2] + $alumno[3]) / 3, 2);
				echo "La nota media de $alumno[0] es de $nota. <br>";
			}
			
			
			
			// C - Suspensos de cada alumno.
			echo '<br><br><br><br><br>';
			foreach($clase as $alumno) {
				if($alumno[1] < 5) {
					echo "$alumno[0] ha suspendido mates con un $alumno[1]. <br>";
				}
				if($alumno[2] < 5) {
					echo "$alumno[0] ha suspendido lengua con un $alumno[2]. <br>";
				}
				if($alumno[3] < 5) {
					echo "$alumno[0] ha suspendido dibujo con un $alumno[3]. <br>";
				}
			}
			
			
			
			// D - Nota media de todos los alumnos por asignatura.
			echo '<br><br><br><br><br>';
			$mediaMates = 0;
			$mediaLengua = 0;
			$mediaDibujo = 0;
			foreach($clase as $alumno) {
				$mediaMates += $alumno[1];
				$mediaLengua += $alumno[2];
				$mediaDibujo += $alumno[3];
			}
			$mediaMates = round($mediaMates / count($clase));
			$mediaLengua = round($mediaLengua / count($clase));
			$mediaDibujo = round($mediaDibujo / count($clase));
			echo "La nota media de los alumnos en mates es de $mediaMates. <br>";
			echo "La nota media de los alumnos en lengua es de $mediaLengua. <br>";
			echo "La nota media de los alumnos en dibujo es de $mediaDibujo. <br>";
		?>
	</body>
</html>