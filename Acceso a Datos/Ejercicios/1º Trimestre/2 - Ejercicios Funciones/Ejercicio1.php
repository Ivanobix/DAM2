<html>
	<body>
		<?php
			$valorAComprobar = 12;
			function parOImpar($var) {
				if($var%2 == 0) {
					echo "$var es un numero par.";
				}
				else {
					echo "$var es un numero impar.";
				}
			}
			parOImpar($valorAComprobar);
		?>
	</body>
</html>