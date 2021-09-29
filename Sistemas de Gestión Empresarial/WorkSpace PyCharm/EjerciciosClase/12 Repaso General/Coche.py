from Vehiculo import Vehiculo;


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
