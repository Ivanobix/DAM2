class PersonaCompleto:
    nombre = "";
    edad = 0;
    dni = "";
    sexo = "";
    peso = 0;
    altura = 0;

    def __init__(self, nombre, edad, dni, sexo, peso, altura):
        self.nombre = nombre;
        self.edad = edad;
        self.dni = dni;
        self.sexo = sexo;
        self.peso = peso;
        self.altura = altura;

    def get_nombre(self):
        return self.nombre;

    def get_edad(self):
        return self.edad;

    def get_dni(self):
        return self.dni;

    def get_sexo(self):
        return self.sexo;

    def get_peso(self):
        return self.peso;

    def get_altura(self):
        return self.altura;

    def set_nombre(self, nombre):
        self.nombre = nombre;

    def set_edad(self, edad):
        self.edad = edad;

    def set_dni(self, dni):
        self.dni = dni;

    def set_edad(self, sexo):
        self.sexo = sexo;

    def set_edad(self, peso):
        self.peso = peso;

    def set_edad(self, altura):
        self.altura = altura;

    def toString(self):
        return self.get_nombre() + " " + str(
            self.get_edad()) + " " + self.get_dni() + " " + self.get_sexo() + " " + str(self.get_peso()) + " " + str(
            self.get_altura()) + ".";

    def calcularIMC(self):
        return (self.peso / 1000) / ((self.altura / 100) ** 2)

    def esMayorDeEdad(self):
        aDevolver = False;
        if self.edad >= 18:
            aDevolver = True;
        return aDevolver;


persona = PersonaCompleto("Iván", 19, "71724191W", "Masculino", 90, 180);
print(persona.toString());
mayorDeEdad = "No";
if persona.esMayorDeEdad():
    mayorDeEdad = "Si"
print("Es mayor de edad: " + mayorDeEdad);
print("IMC: " + str(persona.calcularIMC()));
