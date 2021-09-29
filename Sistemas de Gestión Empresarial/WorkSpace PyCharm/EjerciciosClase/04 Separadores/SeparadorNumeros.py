numero = input("Introduce un número: ");
aDevolver = "";
while len(numero) > 3:
    trozo = numero[-3:];
    numero = numero[:-3];
    aDevolver = "." + trozo + aDevolver;
aDevolver = numero + aDevolver;
print(aDevolver);
