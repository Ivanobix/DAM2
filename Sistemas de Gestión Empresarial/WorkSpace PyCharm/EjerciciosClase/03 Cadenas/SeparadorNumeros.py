numero = input("Introduce un número: ");
aDevolver = "";
contador = 0;

for i in range(len(numero)):
    if contador == 3:
        aDevolver += ".";
        contador = 0;
    aDevolver += str(numero[i]);
    contador += 1
print(aDevolver);
