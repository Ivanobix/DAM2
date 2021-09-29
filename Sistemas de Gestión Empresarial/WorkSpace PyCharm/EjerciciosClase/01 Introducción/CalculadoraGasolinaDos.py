turismos = int(input("¿Cuántos turismos tiene la empresa?: "));
todoterrenos = int(input("¿Cuántos todoterrenos tiene la empresa?: "));

litrosTurismo = float(input("¿Cuántos litros de capacidad tiene un turismo?: "));
litrosTodoterrenos = float(input("¿Cuántos litros de capacidad tiene un todoterreno?: "));

precioGasolina = float(input("¿A cuánto está la gasolina?: "));

resultado = ((turismos * litrosTurismo) + (todoterrenos * litrosTodoterrenos)) * precioGasolina;
print("La empresa gastará: " + str(resultado) + " $ en combustible.");
