def criba(n):
    # Crear criba
    cribaInicial = [False] * 2 + [True] * (n - 1);
    # Recorrer criba y cambiar sus valores
    for (numero, primo) in enumerate(cribaInicial):
        if primo:
            for i in range(numero * numero, n, numero):
                cribaInicial[i] = False;

    cribaFinal = [];
    for (numero, primo) in enumerate(cribaInicial):
        if primo:
            cribaFinal.append(numero);

    return cribaFinal;


print(criba(500));
