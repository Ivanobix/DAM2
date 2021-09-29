numero = int(input("Introduce un número: "));
factorial = 1
contador = 1
while contador <= numero:
    factorial *= contador;
    contador += 1;
print("El resultado es: ", factorial);
