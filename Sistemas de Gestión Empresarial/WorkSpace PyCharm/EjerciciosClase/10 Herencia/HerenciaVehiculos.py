class Vehiculo:
    tipo = "";
    combustible = "";
    marca = "";
    modelo = "";

    def __init__(self, tipo, combustible, marca, modelo):
        self.tipo = tipo;
        self.combustible = combustible;
        self.marca = marca;
        self.modelo = modelo;

    def getTipo(self):
        return self.tipo;

    def getCombustible(self):
        return self.combustible;

    def getMarca(self):
        return self.tipo;

    def getModelo(self):
        return self.modelo;

    def setTipo(self, tipo):
        self.tipo = tipo;

    def setMarca(self, marca):
        self.marca = marca;

    def setModelo(self, modelo):
        self.modelo = modelo;

    def setCombustible(self, combustible):
        self.combustible = combustible;

    def toString(self):
        return "Tipo: " + self.getTipo() + "\nCombustible: " + self.getCombustible() + "\nMarca: " + self.getMarca() + "\nModelo: " + self.getModelo();


class Avion(Vehiculo):
    numPasajeros = 0;
    subsonico = False;
    numRuedas = 0;

    def __init__(self, combustible, marca, modelo, numPasajeros, subsonico, numRuedas):
        super().__init__("Avión", combustible, marca, modelo);
        self.numPasajeros = numPasajeros;
        self.subsonico = subsonico;
        self.numRuedas = numRuedas;

    def getNumPasajeros(self):
        return self.numPasajeros;

    def getSubsonico(self):
        return self.numPasajeros;

    def getNumRuedas(self):
        return self.numPasajeros;

    def setNumPasajeros(self, numPasajeros):
        self.numPasajeros = numPasajeros;

    def setSubsonico(self, subsonico):
        self.subsonico = subsonico;

    def setNuRuedas(self, numRuedas):
        self.numRuedas = numRuedas;

    def toString(self):
        subsonico = "No";
        if self.getSubsonico() == True:
            subsonico = "Si";

        return super().toString() + "\nNúmero de pasajeros: " + str(
            self.getNumPasajeros()) + "\Subsonico: " + subsonico + "\nNúmero de ruedas: " + str(self.getNumRuedas());


class Moto(Vehiculo):
    estilo = "";
    cc = 0;

    def __init__(self, combustible, marca, modelo, estilo, cc):
        super().__init__("Moto", combustible, marca, modelo);
        self.estilo = estilo;
        self.cc = cc;

    def getEstilo(self):
        return self.estilo;

    def getCc(self):
        return self.cc;

    def setEstilo(self, estilo):
        self.estilo = estilo;

    def setCc(self, cc):
        self.cc = cc;

    def toString(self):
        return super().toString() + "\nEstilo: " + self.getEstilo() + "\nCC: " + str(self.getCc());


class Coche(Vehiculo):
    puertas = 0;
    tipoMotor = "";
    turbo = False;

    def __init__(self, combustible, marca, modelo, puertas, tipoMotor, turbo):
        super().__init__("Coche", combustible, marca, modelo);
        self.puertas = puertas;
        self.tipoMotor = tipoMotor;
        self.turbo = turbo;

    def getPuertas(self):
        return self.puertas;

    def getTipoMotor(self):
        return self.tipoMotor;

    def getTurbo(self):
        return self.turbo;

    def setPuertas(self, puertas):
        self.puertas = puertas;

    def setTipoMotor(self, tipoMotor):
        self.tipoMotor = tipoMotor;

    def setTurbo(self, turbo):
        self.turbo = turbo;

    def toString(self):
        turbo = "No";
        if self.getTurbo() == True:
            turbo = "Si";

        return super().toString() + "\nPuertas: " + str(
            self.getPuertas()) + "\nTurbo: " + turbo + "\nTipo de motor: " + self.getTipoMotor();


class Camion(Vehiculo):
    numEjes = 0;
    asistenciaConduccion = False;
    tacografo = False;

    def __init__(self, combustible, marca, modelo, numEjes, asistenciaConduccion, tacografo):
        super().__init__("Camión", combustible, marca, modelo);
        self.numEjes = numEjes;
        self.asistenciaConduccion = asistenciaConduccion;
        self.tacografo = tacografo;

    def getNumEjes(self):
        return self.numEjes;

    def getAsistenciaConduccion(self):
        return self.asistenciaConduccion;

    def getTacografo(self):
        return self.tacografo;

    def setNumEjes(self, numEjes):
        self.numEjes = numEjes;

    def setAsistenciaConduccion(self, asistenciaConduccion):
        self.asistenciaConduccion = asistenciaConduccion;

    def setTacografo(self, tacografo):
        self.tacografo = tacografo;

    def toString(self):
        asistencia = "No";
        tacografo = "No";
        if self.getAsistenciaConduccion() == True:
            asistencia = "Si";
        if self.getTacografo() == True:
            tacografo = "Si";

        return super().toString() + "\nNúmero de ejes: " + str(
            self.getNumEjes()) + "\nAsistencia: " + asistencia + "\nTacografo: " + tacografo;


avion = Avion("Quroseno", "Airbus", "A380", 200, True, 2);

moto = Moto("Gasolina", "Yamaha", "A3", "Moderno", 240);

coche = Coche("Diesel", "Audi", "A4", 4, "V-12", True);

camion = Camion("Gasolina", "Mercedes", "B87L9", 4, True, True);

print(avion.toString());
print("\n----------\n");
print(moto.toString());
print("\n----------\n");
print(coche.toString());
print("\n----------\n");
print(camion.toString());
