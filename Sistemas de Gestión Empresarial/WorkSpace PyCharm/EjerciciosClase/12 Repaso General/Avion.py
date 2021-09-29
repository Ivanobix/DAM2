from Vehiculo import Vehiculo;


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
        return self.subsonico;

    def getNumRuedas(self):
        return self.numRuedas;

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
            self.getNumPasajeros()) + "\nSubsonico: " + subsonico + "\nNúmero de ruedas: " + str(self.getNumRuedas());
