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
        return self.marca;

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
