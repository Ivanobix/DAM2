nombres = [];
nombre = False;
while nombre != "":
    nombre = input("Nombre: ");
    if nombre not in nombres:
        nombres.append(nombre);
nombres.sort();
print(nombres[1:]);
