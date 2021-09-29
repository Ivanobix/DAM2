<html>
<body>
<?php
function foo($var)
{
    $var++;
}

$a=5;
foo($a);
echo $a;
// $a es 6 aquí
?>
</body>
</html>


