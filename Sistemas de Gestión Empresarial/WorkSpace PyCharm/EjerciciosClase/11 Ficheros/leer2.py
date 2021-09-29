# Método 2
# No lee caracteres especiales
fh = open('c:\\archivo.txt', 'r');
contenido = fh.readlines();
print(contenido);
fh.close();
