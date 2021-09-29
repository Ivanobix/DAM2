# Método 3
# No lee caracteres especiales
fh = open('c:\\archivo.txt', 'r');
while True:
    line = fh.readline();
    print(line);
    if line == "":
        break;
fh.close();
