from Vehiculo import Vehiculo;


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
