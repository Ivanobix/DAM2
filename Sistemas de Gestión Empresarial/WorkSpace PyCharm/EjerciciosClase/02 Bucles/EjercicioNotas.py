notaMinima = 10;
notaMaxima = 0;
notaMedia = 0;
for cont in range(1, 9):
    nota = float(input("Introduce la nota " + str(cont) + ": "));
    if nota > notaMaxima:
        notaMaxima = nota;
    if nota < notaMinima:
        notaMinima = nota;
    notaMedia += nota;
print("Nota mínima: " + str(notaMinima) + " Nota máxima: " + str(notaMaxima) + " Nota media: " + str(notaMedia / 8));
