class Persona:
    nombre = "";
    edad = 0;
    ciudad = "";

    def __init__(self, nombre, edad, ciudad):
        self.nombre = nombre;
        self.edad = edad;
        self.ciudad = ciudad;

    def get_nombre(self):
        return self.nombre;

    def get_edad(self):
        return self.edad;

    def get_ciudad(self):
        return self.ciudad;

    def set_nombre(self, nombre):
        self.nombre = nombre;

    def set_edad(self, edad):
        self.edad = edad;

    def set_ciudad(self, ciudad):
        self.ciudad = ciudad;

    def toString(self):
        return self.get_nombre() + " " + str(self.get_edad()) + " " + self.get_ciudad() + ".";


persona = Persona("Iván", 19, "León");
print(persona.toString());
