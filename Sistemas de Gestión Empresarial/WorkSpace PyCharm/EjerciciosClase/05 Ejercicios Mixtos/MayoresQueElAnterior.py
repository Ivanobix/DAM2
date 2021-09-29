print("MAYORES QUE EL ANTERIOR");
cantidad = int(input("¿Cuántos valores vas a introducir? "));
if cantidad <= 1:
    print("¡Imposible!");
else:
    numeroAComparar = int(input("Escriba un número: "));
    for i in range(cantidad - 1):
        numero = int(input("Escriba un número mayor que " + str(numeroAComparar) + ": "));
        if numero <= numeroAComparar:
            print("¡" + str(numero) + " no es mayor que " + str(numeroAComparar) + "!");
        numeroAComparar = numero;
    print("Gracias por su colaboración.");
