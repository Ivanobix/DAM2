# Método 1
# No lee caracteres especiales
fh = open('c:\\archivo.txt', 'r');
contenido = fh.read();
print(contenido);
fh.close();
