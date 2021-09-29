lista = []
lista.append("Pedro Lopez, 32\n")
lista.append("Ana Cana, 27\n")
lista.append("Luis Arias, 58\n")
lista.append("Sandra Perez, 25\n")

fh = open("archivo.txt", 'a', encoding="UTF-8")
fh.writelines(lista)
fh.close()
