import csv


def obtenerValoresDescifrado():
    listaTraducciones = []
    listaClaves = []
    with open('cifrado.csv', 'r', encoding='utf-8') as fh:
        contadorLineas = 1
        lns = csv.reader(fh, delimiter=";")
        for line in lns:
            if contadorLineas == 1:
                listaTraducciones = line
                contadorLineas = 2
            else:
                listaClaves = line
    return dict(zip(listaClaves, listaTraducciones))


def leerFicheroCifrado():
    aDevolver = []
    with open('Fichero-Cifrado.txt', 'r', encoding='utf-8') as fh:
        for line in fh:
            aDevolver.append(line)
    return aDevolver


def descifrarFichero(textoCifrado, diccionario):
    aDevolver = []
    for linea in textoCifrado:
        lineaTraducida = ""
        for letra in linea:
            lineaTraducida += diccionario.get(letra, letra)
        aDevolver.append(lineaTraducida)
    return aDevolver


def escribirFicheroDescifrado(lineasDescifradas):
    with open('textoDescifrado.txt', 'w', encoding='utf-8') as fh:
        fh.writelines(lineasDescifradas)


if __name__ == '__main__':
    escribirFicheroDescifrado(descifrarFichero(leerFicheroCifrado(), obtenerValoresDescifrado()))
