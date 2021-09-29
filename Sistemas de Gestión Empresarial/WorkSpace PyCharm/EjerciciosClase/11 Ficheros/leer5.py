# Método 5
# No es necesario abrir ni cerrar el fichero, lo hace solo
# No lee caracteres especiales
with open('c:\\archivo.txt', 'r') as fh:
    for contenido in fh:
        print(contenido);
