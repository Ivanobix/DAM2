<html>
<body>
<?php

  $a = 1;
  function ver_a() {
      global $a;
      echo $a."<BR>"; // imprimir� el valor de $a
      $a += 1; // sumamos 1 a $a
   }
   echo ver_a(); // imprimir� 1
   echo ver_a(); // imprimir� 2
   $a = 7;
   echo ver_a(); // imprimir� 7
   echo ver_a(); // imprimir� 8
?>
</body>
</html>


