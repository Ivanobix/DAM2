lista = input("Introduce numeros separados por comas: ").split(",");
mayor = 0;
menor = int(lista[0]);
media = 0;
for i in lista:
    i = int(i);
    if i > mayor:
        mayor = i;
    if i < menor:
        menor = i;
    media = media + i;
media /= len(lista);
print("Máximo: " + str(mayor) + " // Mínimo: " + str(menor) + " // Media: " + str(media));
