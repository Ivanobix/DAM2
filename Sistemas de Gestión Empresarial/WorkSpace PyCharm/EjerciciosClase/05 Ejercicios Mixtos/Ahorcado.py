print("--- JUEGO DEL AHORCADO ---");
vidas = 11;

palabra = input("Introduce una palabra: ").upper();
palabraDescubierta = "_" * len(palabra);
print(palabraDescubierta);

continuarJuego = True;
while vidas > 0 and continuarJuego:
    letra = input("\n\nIntroduce una letra: ").upper();
    letraExiste = False;
    for i in range(len(palabra)):
        if palabra[i] == letra:
            palabraDescubierta = palabraDescubierta[:i] + palabra[i] + palabraDescubierta[i + 1:];
            letraExiste = True;
    if letraExiste == False:
        vidas -= 1;
        print("La palabra no contiene la letra " + letra);
    elif palabraDescubierta == palabra:
        continuarJuego = False;
    print(palabraDescubierta);
if continuarJuego == False:
    print("¡Has ganado!");
else:
    print("¡Te has quedado sin vidas!");
