palabra = input("Introduce una palabra: ");
palabraDescubierta = "_" * len(palabra);
vidas = 11;
continuarJuego = True;
print(palabraDescubierta);
while vidas > 0 and continuarJuego:
    letra = input("Introduce una letra: ");
    letraExiste = False;
    for i in range(len(palabra)):
        if palabra[i] == letra:
            palabraDescubierta = palabraDescubierta[:i] + palabra[i] + palabraDescubierta[i + 1:];
            letraExiste = True;
    if letraExiste == False:
        vidas -= 1;
    print(palabraDescubierta);
    if palabraDescubierta == palabra:
        continuarJuego = False;
if continuarJuego == False:
    print("Has ganado");
else:
    print("Has perdido");
