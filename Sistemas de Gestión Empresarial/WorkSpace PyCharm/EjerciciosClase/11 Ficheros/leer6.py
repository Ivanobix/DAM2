# Método 6
# No es necesario abrir ni cerrar el fichero, lo hace solo
with open('c:\\archivo2.txt', 'r', encoding='utf-8') as fh:
    for contenido in fh:
        print(contenido);
