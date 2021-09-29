print("MAYORES QUE EL PRIMERO");
cantidad = int(input("¿Cuántos valores vas a introducir? "));
if cantidad <= 1:
    print("¡Imposible!");
else:
    for i in range(cantidad):
        if i == 0:
            numero1 = int(input("Escriba un número: "));
        else:
            numero2 = int(input("Escriba un número mayor que " + str(numero1) + ": "));
            if numero2 <= numero1:
                print("¡" + str(numero2) + " no es mayor que " + str(numero1) + "!");
    print("Gracias por su colaboración.");
