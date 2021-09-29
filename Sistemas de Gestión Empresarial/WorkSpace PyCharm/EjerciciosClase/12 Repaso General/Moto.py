from Vehiculo import Vehiculo;


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
