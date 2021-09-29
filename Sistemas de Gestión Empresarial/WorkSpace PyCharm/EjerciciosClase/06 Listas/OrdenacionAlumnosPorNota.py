# Se da por hecho que todos los datos que introduce el usuario son correctos al igual que en los ejercicios de clase.
# Se da por hecho que los valores almacenados en la lista no serán usados luego,
# este programa simplemente muestra el resultado de la ordenación, pero no ordena la lista.
listaNombres = [];
listaNotas = [];
numAlumnos = int(input("Introduce el número de alumnos: "));
for i in range(numAlumnos):
    nombre = input("Introduce el nombre de un alumno: ");
    listaNombres.append(nombre);
    nota = float(input("Introduce la nota media de ese alumno: "));
    listaNotas.append(nota);
orden = input("Orden ascendente (A) o descendente (D): ");
contadorVueltasOrdenacion = 0;
if orden.upper() == "D":
    for i in range(numAlumnos - 1):
        mediaMasAlta = 0;
        posicionACambiar = -1;
        for j in range(i, numAlumnos):
            contadorVueltasOrdenacion += 1;
            if listaNotas[j] >= mediaMasAlta:
                posicionACambiar = j;
                mediaMasAlta = listaNotas[j];
        if posicionACambiar != -1:
            print(str(listaNombres[posicionACambiar]) + " " + str(listaNotas[posicionACambiar]));
            listaNombres[posicionACambiar] = listaNombres[i];
            listaNotas[posicionACambiar] = listaNotas[i];
else:
    for i in range(numAlumnos - 1):
        mediaMasBaja = 10;
        posicionACambiar = -1;
        for j in range(i, numAlumnos):
            contadorVueltasOrdenacion += 1;
            if listaNotas[j] <= mediaMasBaja:
                posicionACambiar = j;
                mediaMasBaja = listaNotas[j];
        if posicionACambiar != -1:
            print(str(listaNombres[posicionACambiar]) + " " + str(listaNotas[posicionACambiar]));
            listaNombres[posicionACambiar] = listaNombres[i];
            listaNotas[posicionACambiar] = listaNotas[i];
print(str(listaNombres[-1]) + " " + str(listaNotas[-1]));
print("\nNúmero de repeticiones en los bucles para la toma de datos: " + str(numAlumnos));
print("Número de repeticiones en los bucles para ordenar: " + str(contadorVueltasOrdenacion));
print("Total: " + str(numAlumnos + contadorVueltasOrdenacion));
