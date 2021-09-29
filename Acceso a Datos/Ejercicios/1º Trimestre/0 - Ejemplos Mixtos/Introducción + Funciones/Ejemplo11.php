<html>
<body>
<?php
    function suma (&$x, $y) {
     $x = $x + 1;
     return $x+$y;
    }
    $a = 1;
    $b = 2;
    echo "a vale ".$a;
    echo "  y b vale ". $b;
  
    echo " la suma vale ".suma ($a, $b);    
    echo " y a vale ".$a." y b vale ".$b;   
?>
</body>
</html>


