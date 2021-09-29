print("PARES E IMPARES");
numero1 = int(input("Escriba un número entero: "));
numero2 = int(input("Escriba un número entero mayor o igual que " + str(numero1) + ": "));
if numero2 >= numero1:
    for i in range(numero1, numero2 + 1):
        if i % 2 == 0:
            print("El número " + str(i) + " es par.");
        else:
            print("El número " + str(i) + " es impar.");
else:
    print("¡Te he pedido un número entero mayor o igual que " + str(numero1) + "!");
