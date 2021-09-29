nombre = " ";
nombres = [];
while nombre != "":
    nombre = input("Introduce un nombre: ");
    nombres.append(nombre);
nombres.pop();
while True:
    try:
        posicionAMostrar = int(input("Posición del nombre a buscar: "));
        print("El nombre seleccionado es: " + nombres[posicionAMostrar - 1]);
    except (IndexError, ValueError):
        print("El valor introducido no es válido.");
