cadena = input("Introduce una cadena: ");
vocal = input("Introduce una vocal: ");
print("La longitud de la cadena es: " + str(len(cadena)));
for aReemplazar in "aeiouAEIOU":
    cadena = cadena.replace(aReemplazar, vocal);
print("La nueva cadena es: " + cadena);
