turismos = 32;
todoterrenos = 11;

litrosTurismo = 40;
litrosTodoterrenos = 65;

precioGasolina = 1.005;

resultado = ((turismos * litrosTurismo) + (todoterrenos * litrosTodoterrenos)) * precioGasolina;
print("La empresa gastará: " + str(resultado) + " $ en combustible.");
