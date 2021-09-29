# Modos: r+, a, w (L/E, Append, Escritura)
fh = open("archivo.txt", 'a')
contenido = fh.write("Pedro López, 32\n");
contenido = fh.write("Ana Caña, 27\n");
contenido = fh.write("Luis Árias, 58\n");
contenido = fh.write("Sandra Pérez, 25\n");
fh.close();
