# He usado una función simple para evitar repetir código, pero podría pegar el código sin más si aún no se pueden usar.

print("--- JUEGO DEL AHORCADO MULTIJUGADOR ---");
jugadores = int(input("\n¿Cuántos jugadores desean participar?: "));
nombreJugadores = [];
palabras = [];
palabrasDescubiertas = [];
vidas = [];
ganadores = 0;


def borrar_jugador():
    global jugadorActual;
    nombreJugadores.pop(jugadorActual);
    palabras.pop(jugadorActual);
    palabrasDescubiertas.pop(jugadorActual);
    vidas.pop(jugadorActual);
    jugadorActual -= 1;


for i in range(1, jugadores + 1):
    jugador = input("Introduce el nombre del jugador " + str(i) + ": ");
    nombreJugadores.append(jugador);
    palabra = input("Introduce una palabra: ").upper();
    palabras.append(palabra);
    palabraDescubierta = "_" * len(palabra);
    palabrasDescubiertas.append(palabraDescubierta);
    vidas.append(11);

jugadorActual = 0;
while len(nombreJugadores) > 0:
    print("\n\nEs el turno de " + nombreJugadores[jugadorActual] + ".");
    print("Su solución actual es: " + palabrasDescubiertas[jugadorActual]);
    letra = input("Introduce una letra: ").upper();
    letraExiste = -1;
    palabra = palabras[jugadorActual];
    palabraDescubierta = palabrasDescubiertas[jugadorActual];
    if letra not in palabraDescubierta:
        for i in range(len(palabra)):
            if palabra[i] == letra:
                palabrasDescubiertas[jugadorActual] = palabraDescubierta[:i] + palabra[i] + palabraDescubierta[i + 1:];
                letraExiste = 1;
    if letraExiste == -1:
        print("La palabra no contiene la letra " + letra + ".");
        vidas[jugadorActual] -= 1;
        if vidas[jugadorActual] == 0:
            print("¡Has perdido!");
            borrar_jugador();
    elif letraExiste == 1:
        print("¡La palabra contiene la letra " + letra + "!.");
        if palabrasDescubiertas[jugadorActual] == palabras[jugadorActual]:
            print("¡Has ganado!");
            ganadores += 1;
            borrar_jugador();
    else:
        print("¡La letra ya había sido encontrada!.");
        vidas[jugadorActual] -= 1;

    if jugadorActual == len(nombreJugadores) - 1:
        jugadorActual = 0;
    else:
        jugadorActual += 1;

print("\n\nGanadores: " + str(ganadores));
print("Perdedores: " + str(jugadores - ganadores));
