def ord_burbuja(lista):
    intercambios = True
    numPasada = len(lista) - 1
    while numPasada > 0 and intercambios:
        intercambios = False
        for i in range(numPasada):
            if lista[i] > lista[i + 1]:
                intercambios = True
                temp = lista[i]
                lista[i] = lista[i + 1]
                lista[i + 1] = temp
        numPasada = numPasada - 1


if __name__ == '__main__':
    lista = [3, 2, -1, 5, 0, 2]
    ord_burbuja(lista)
    print(lista)
