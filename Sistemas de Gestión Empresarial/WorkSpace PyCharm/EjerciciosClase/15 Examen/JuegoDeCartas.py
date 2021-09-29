from random import randrange, shuffle


# Iván García Prieto

class Persona:
    nombre = ""
    apodo = ""
    edad = 0
    mano = []

    def __init__(self, nombre, apodo, edad, mano):
        self.nombre = nombre
        self.apodo = apodo
        self.edad = edad
        self.mano = mano

    def getNombre(self):
        return self.nombre

    def getApodo(self):
        return self.apodo

    def getEdad(self):
        return self.edad

    def getMano(self):
        return self.mano

    def setNombre(self, nombre):
        self.nombre = nombre

    def setApodo(self, apodo):
        self.apodo = apodo

    def setEdad(self, edad):
        self.edad = edad

    def setMano(self, mano):
        self.mano = mano

    def cogerCarta(self, carta):
        self.mano.append(carta)

    def escogerCarta(self):
        return randrange(0, len(self.mano))

    def tirarCarta(self):
        self.mano.pop(0)

    def tirarCartas(self):
        self.mano.clear()

    def toString(self):
        return self.nombre + ": " + self.apodo + ": " + str(self.edad)


class Crupier(Persona):

    def __init__(self, nombre, apodo, edad, mano):
        super().__init__(nombre, apodo, edad, mano)

    def barajarCartas(self):
        shuffle(self.mano)


class Carta:
    palo = ""
    numero = 0
    tipo = ""

    def __init__(self, palo, numero, tipo):
        self.palo = palo
        self.numero = numero
        self.tipo = tipo

    def getPalo(self):
        return self.palo

    def getNumero(self):
        return self.numero

    def getTipo(self):
        return self.tipo

    def setPalo(self, palo):
        self.palo = palo

    def setNumero(self, numero):
        self.numero = numero

    def setTipo(self, tipo):
        self.tipo = tipo

    def toString(self):
        return str(self.numero) + " " + self.palo + " " + self.tipo


class Baraja:
    cartas = []

    def __init__(self, cartas):
        self.cartas = cartas

    def getCartas(self):
        return self.cartas

    def setCartas(self, cartas):
        self.cartas = cartas

    def tieneCartas(self):
        aDevolver = False
        if len(self.cartas) > 0:
            aDevolver = True
        return aDevolver

    def darUnaCarta(self):
        carta = self.cartas[0]
        self.cartas.pop(carta)
        return carta

    def toString(self):
        aDevolver = ""
        for i in self.cartas:
            aDevolver += i + " "
        return aDevolver


if __name__ == '__main__':
    jugadores = []

    contador = 1
    while len(jugadores) < 4:
        nombre = input("Nombre del jugador " + str(contador) + ": ")
        apodo = input("Apodo del jugador " + str(contador) + ": ")
        edad = input("Edad del jugador " + str(contador) + ": ")
        nombreValido = True
        for j in jugadores:
            if j.getNombre() == nombre:
                if j.getApodo() == apodo:
                    nombreValido = False
                    print("Jugador no válido.")

        if nombreValido:
            persona = Persona(nombre, apodo, int(edad), [])
            jugadores.append(persona)
            contador += 1

    nombre = input("Nombre del crupier : ")
    apodo = input("Apodo del crupier: ")
    edad = input("Edad del crupier: ")
    tipoBaraja = input("Tipo de baraja (E/F): ")

    mazo = []
    if tipoBaraja == "E":
        palo = ["copas", "bastos", "oro", "espadas"]
        for i in palo:
            for j in range(12):
                mazo.append(Carta(i, j + 1, "Española"))
    else:
        palo = ["picas", "diamantes", "treboles", "corazones"]
        for i in palo:
            for j in range(12):
                mazo.append(Carta(i, j + 1, "Francesa"))

    crupier = Crupier(nombre, apodo, int(edad), mazo)

    print("\n\n----- Paso 1 -----")
    print("Jugadores:")
    for i in jugadores:
        print(i.toString())

    print("\nCrupier:")
    print(crupier.toString())

    print("\nMazo:")
    for i in mazo:
        print(i.toString())

    print("\n\n----- Paso 2 -----")
    crupier.barajarCartas()
    mazo = crupier.getMano()
    for i in mazo:
        print(i.toString())

    print("\n\n----- Paso 3 -----")
    while len(mazo) > 0:
        for i in jugadores:
            i.cogerCarta(mazo[0])
            crupier.tirarCarta()

    for i in jugadores:
        print("Jugador: " + i.toString())
        for j in i.getMano():
            print(j.toString())
        print("\n")
