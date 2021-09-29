class Persona:
    nombre = "";
    ciudad = "";
    edad = 0;

    def __init__(self, nombre, ciudad, edad):
        self.nombre = nombre;
        self.ciudad = ciudad;
        self.edad =edad;

    # Métodos getter, setter y toString
    def setNombre(self, nombre):
        self.nombre = nombre;

    def getNombre(self):
        return self.nombre;

    def setCiudad(self, ciudad):
        self.ciudad = ciudad;

    def getCiudad(self):
        return self.ciudad;

    def setEdad(self, edad):
        self.edad = edad;

    def getEdad(self):
        return self.edad;

    def toString(self):
        aDevolver = self.getNombre() + ", " + self.getCiudad() + ", " + str(self.getEdad());
        return aDevolver;