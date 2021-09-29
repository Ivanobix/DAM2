def ord_insercion(lista):
    # i va desde la primera hasta la penúltima posición de la lista
    for i in range(len(lista) - 1):
        # Si el elemento de la posición i+1 está desordenado respecto
        # al de la posición i, reubicarlo dentro del segmento (0:i]
        if lista[i + 1] < lista[i]:
            reubicar(lista, i + 1)


def reubicar(lista, p):
    # v es el valor a reubicar
    v = lista[p]

    # Recorre el segmento (0:p-1] de derecha a izquierda hasta
    # encontrar la posición j tal que lista(j-1] <= v < lista(j].
    j = p
    while j > 0 and v < lista[j - 1]:
        # Desplaza los elementos hacia la derecha, dejando lugar
        # para insertar el elemento v donde corresponda.
        lista[j] = lista[j - 1]
        # Se mueve un lugar a la izquierda
        j -= 1

    # Ubica el valor v en su nueva posición
    lista[j] = v


if __name__ == '__main__':
    lista = [3, 2, -1, 5, 0, 2]
    ord_insercion(lista)
    print(lista)
