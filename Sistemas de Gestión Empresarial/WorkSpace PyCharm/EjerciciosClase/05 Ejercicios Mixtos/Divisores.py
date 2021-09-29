print("DIVISORES");
numero = int(input("Escriba un número mayor que cero: "));
if numero < 0:
    print("Te he pedido un número mayor que 0...");
else:
    aDevolver = "";
    dividendo = 7;
    while dividendo > 1:
        if numero % dividendo == 0:
            numero //= dividendo;
            aDevolver += str(dividendo) + " ";
            dividendo = 7;
        elif dividendo == 7 or dividendo == 5:
            dividendo -= 2;
        else:
            dividendo -= 1;
    print("Los divisores son: " + aDevolver + str(numero));
