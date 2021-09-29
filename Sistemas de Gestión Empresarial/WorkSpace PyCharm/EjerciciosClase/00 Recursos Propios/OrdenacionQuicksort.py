def ord_quicksort(lista):
    izquierda = []
    centro = []
    derecha = []
    if len(lista) > 1:
        pivote = lista[0]
        for i in lista:
            if i < pivote:
                izquierda.append(i)
            elif i == pivote:
                centro.append(i)
            elif i > pivote:
                derecha.append(i)
        return ord_quicksort(izquierda) + centro + ord_quicksort(derecha)
    else:
        return lista


if __name__ == '__main__':
    lista = [3, 2, -1, 5, 0, 2]
    lista = ord_quicksort(lista)
    print(lista)

    lista2 = ['hola', 'pedro', 'zazi', 'alo']
    lista2 = ord_quicksort(lista2)
    print(lista2)

    texto = "hola buenas tardes"
    lista3 = ord_quicksort(texto.split())
    print(lista3)

