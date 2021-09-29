lista = [1, 2, 45, 123, 2, 7];
mayor = 0;
menor = lista[0];
media = 0;
for i in lista:
    if i > mayor:
        mayor = i;
    if i < menor:
        menor = i;
    media = media + i;
media /= len(lista);
print("El maximo es: " + str(mayor));
print("El minimo es: " + str(menor));
print("La media es: " + str(media));
