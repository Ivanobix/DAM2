import csv
import os


class MiFichero:
    rutaFichero = ""
    esCsv = False

    def __init__(self, rutaFichero, esCsv):
        self.rutaFichero = rutaFichero
        self.esCsv = esCsv

    def existeFichero(self):
        aDevolver = False
        if os.path.isfile(self.rutaFichero):
            aDevolver = True

        return aDevolver

    def devuelveLinea(self):
        aDevolver = ""
        with open(self.rutaFichero, 'r', encoding='utf-8') as fh:
            if self.esCsv:
                lns = csv.reader(fh, delimiter=";")
                for linea in lns:
                    aDevolver = linea
                    break
            else:
                aDevolver = fh.readline()
        return aDevolver

    def devuelveLineas(self):
        aDevolver = []
        with open(self.rutaFichero, 'r', encoding='utf-8') as fh:
            if self.esCsv:
                lns = csv.reader(fh, delimiter=";")
                for line in lns:
                    aDevolver.append(line)
            else:
                for line in fh:
                    aDevolver.append(line)
        return aDevolver

    def devuelveLineaN(self, lineaABuscar):
        aDevolver = -1
        with open(self.rutaFichero, 'r', encoding='utf-8') as fh:
            contadorLineas = 1
            if self.esCsv:
                lns = csv.reader(fh, delimiter=";")
                for line in lns:
                    if contadorLineas == lineaABuscar:
                        aDevolver = line
                        break
            else:
                for line in fh:
                    if contadorLineas == lineaABuscar:
                        aDevolver = line
                        break
                    contadorLineas += 1
        return aDevolver

    def escribeLinea(self, linea, sobreescribir):
        metodo = 'a'
        if sobreescribir:
            metodo = 'w'
        with open(self.rutaFichero, metodo, encoding='utf-8') as fh:
            if self.esCsv:
                aEscribir = ""
                for valor in linea:
                    aEscribir += str(valor) + ";"
                aEscribir = aEscribir[:-1]
                fh.write(aEscribir)
            else:
                fh.write(linea)

    def escribeLineas(self, lineas, sobreescribir):
        metodo = 'a'
        if sobreescribir:
            metodo = 'w'
        with open(self.rutaFichero, metodo, encoding='utf-8') as fh:
            if self.esCsv:
                aEscribir = []
                for valor in lineas:
                    linea = ""
                    for registro in valor:
                        linea += str(registro) + ";"
                        linea = aEscribir[:-1]
                        aEscribir.append(linea)
                fh.writelines(aEscribir)
            else:
                fh.writelines(lineas)
