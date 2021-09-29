import os;


# Se da por hecho, ya que en el enunciado se especifica así, que solo se debe comprobar si faltan números.
# Por tanto, en caso de que un número sea incorrecto simplemente se ignora, si están todos los números necesarios el resultado será indicado como correcto de todas formas.

def obtenerNumerosPrimos(n):
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


if __name__ == '__main__':
    # Abrir fichero
    ficheroValido = False;
    rutaFichero = "";
    while not ficheroValido:
        nombreFichero = input("Introduce el nombre del fichero: ");
        rutaFichero = os.path.dirname(os.path.abspath(__file__)) + "\\" + nombreFichero;
        if not os.path.isfile(rutaFichero):
            print("El archivo no existe.");
        else:
            ficheroValido = True;

    # Leer y almacenar solo los numeros menores que el primero
    numerosLeidos = [];
    with open(rutaFichero, 'r', encoding='utf-8') as fh:
        primerNumero = int(fh.readline());
        numerosLeidos.append(primerNumero);
        for linea in fh:
            if int(linea) <= primerNumero:
                numerosLeidos.append(int(linea));

    # Comprobar numeros que faltan
    cribaCorrecta = obtenerNumerosPrimos(numerosLeidos[0]);
    numerosQueFaltan = "";
    for i in cribaCorrecta:
        if not i in numerosLeidos:
            numerosQueFaltan += str(i) + ",";

    # Dar formato al resultado y mostrarlo
    if len(numerosQueFaltan) > 0:
        numerosQueFaltan = numerosQueFaltan[:-1];
        print("No son los números de la criba.");
        print("Faltan: " + numerosQueFaltan);
    else:
        print("Son los números de la criba.");
        print("No falta ningún número.");
