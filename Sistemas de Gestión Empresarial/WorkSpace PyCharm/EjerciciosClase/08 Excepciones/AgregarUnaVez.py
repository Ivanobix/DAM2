listaPrincipal = [];


def agregar_una_vez(lista, elemento):
    try:
        if elemento in lista:
            raise ValueError;
        else:
            lista.append(elemento);
    except ValueError:
        print("Error: Imposible añadir elementos duplicados => " + str(elemento) + ".");


while True:
    elementoAIntroducir = input("Introduce una cadena: ");
    agregar_una_vez(listaPrincipal, elementoAIntroducir);
