class NumeroComplejo:
    real = 0;
    imaginario = 0;

    def __init__(self, real, imaginario):
        self.real = real;
        self.imaginario = imaginario;

    def get_real(self):
        return self.real;

    def get_imaginario(self):
        return self.imaginario;

    def set_real(self, real):
        self.real = real;

    def set_imaginario(self, imaginario):
        self.imaginario = imaginario;

    def toString(self):
        return str(self.get_real()) + " " + str(self.get_imaginario()) + "i";

    def suma(self, numeroComplejo):
        self.real = self.get_real() + numeroComplejo.get_real();
        self.complejo = self.get_imaginario() + numeroComplejo.get_imaginario();


a = NumeroComplejo(3, 1);
print("Número 1: " + a.toString());
b = NumeroComplejo(5, -2);
print("Número 2: " + b.toString());

resultado = a.suma(b);
print("Resultado de la suma (Valor del número 1): " + a.toString());
