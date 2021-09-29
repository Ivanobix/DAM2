<html>
<body>
<?php

  $a = 1;
  function ver_a() {
      global $a;
      echo $a."<BR>"; // imprimirá el valor de $a
      $a += 1; // sumamos 1 a $a
   }
   echo ver_a(); // imprimirá 1
   echo ver_a(); // imprimirá 2
   $a = 7;
   echo ver_a(); // imprimirá 7
   echo ver_a(); // imprimirá 8
?>
</body>
</html>


