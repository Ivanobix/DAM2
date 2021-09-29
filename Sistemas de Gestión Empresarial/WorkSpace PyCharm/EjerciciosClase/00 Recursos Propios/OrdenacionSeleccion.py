def ord_seleccion(lista):
    # n = posicion final del segmento a tratar, comienza en len(lista)-1*
    n = len(lista) - 1

    # cicla mientras haya elementos para ordenar (2 o más elementos)
    while n > 0:
        # p es la posicion del mayor valor del segmento
        p = buscar_max(lista, 0, n)

        # intercambia el valor que está en p con el valor que
        # está en la última posición del segmento
        lista[p], lista[n] = lista[n], lista[p]

        # reduce el segmento en 1
        n = n - 1


def buscar_max(lista, ini, fin):
    pos_max = ini
    for i in range(ini + 1, fin + 1):
        if lista[i] > lista[pos_max]:
            pos_max = i
    return pos_max


if __name__ == '__main__':
    lista = [3, 2, -1, 5, 0, 2]
    ord_seleccion(lista)
    print(lista)
