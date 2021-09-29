class Vehiculo:
    tipo = "";
    ruedas = 0;
    color = "";
    puertas = 0;
    combustible = "";
    abs = False;

    def __init__(self, tipo, ruedas, color, puertas, combustible, abs):
        self.tipo = tipo;
        self.ruedas = ruedas;
        self.color = color;
        self.puertas = puertas;
        self.combustible = combustible;
        self.abs = abs;

    def getTipo(self):
        return self.tipo;

    def getRuedas(self):
        return self.ruedas;

    def getColor(self):
        return self.color;

    def getPuertas(self):
        return self.puertas;

    def getCombustible(self):
        return self.combustible;

    def getAbs(self):
        return self.abs;

    def setTipo(self, tipo):
        self.tipo = tipo;

    def setRuedas(self, ruedas):
        self.ruedas = ruedas;

    def setColor(self, color):
        self.color = color;

    def setPuertas(self, puertas):
        self.puertas = puertas;

    def setCombustible(self, combustible):
        self.combustible = combustible;

    def setAbs(self, abs):
        self.abs = abs;

    def toString(self):
        tieneAbs = "NO.";
        if self.getAbs():
            tieneAbs = "SI.";
        return "Tipo: " + self.getTipo() + " Ruedas: " + str(
            self.getRuedas()) + " Color: " + self.getColor() + " Puertas: " + str(
            self.getPuertas()) + " Combustible: " + self.getCombustible() + " ABS: " + tieneAbs;


if __name__ == '__main__':
    print("--- GESTION DE VEHICULOS ---");
    vehiculos = [];
    salir = False;


    def listar():
        print("  LISTA DE VEHICULOS:\n");
        for vehiculo in vehiculos:
            print("    " + vehiculo.toString());


    def crearVehiculo():
        print("  NUEVO VEHICULO:");
        tipo = input("    Tipo: ");
        color = input("    Color: ");
        combustible = input("    Combustible: ");
        ruedas = int(input("    Ruedas: "));
        puertas = int(input("    Puertas: "));
        abs = False;
        absCorrecto = False;
        while absCorrecto == False:
            abs = input("    ABS (True/False): ").lower();
            if abs == "true":
                absCorrecto = True;
                abs = True;
            elif abs == "false":
                absCorrecto = True;
                abs = False;
            else:
                print("    El valor introducido no es válido.");

        vehiculo = Vehiculo(tipo, ruedas, color, puertas, combustible, abs);
        vehiculos.append(vehiculo);


    def eliminar():
        posicion = int(input("    Posicion del coche a eliminar: "));
        if len(vehiculos) >= posicion and posicion >= 1:
            print("    Coche eliminado.");
            vehiculos.pop(posicion - 1);
        else:
            print("    Posición incorrecta.");


    while salir == False:
        operacion = -1;
        while operacion == -1:
            try:
                operacion = int(input("\n  0 -> Salir // 1 -> Crear // 2 -> Listar // 3 -> Eliminar: "));
            except ValueError:
                print("    No has introducido un número.");

        if operacion == 0:
            print("  FIN DEL PROGRAMA");
            salir = True;
        elif operacion == 1:
            crearVehiculo();

        elif operacion == 2:
            listar();

        elif operacion == 3:
            eliminar();
        else:
            print("    La operación no existe.");
