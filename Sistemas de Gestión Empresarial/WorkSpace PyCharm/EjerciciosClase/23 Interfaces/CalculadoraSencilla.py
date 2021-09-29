from tkinter import Tk, W, E
from tkinter.ttk import Frame, Button, Entry, Style


class Calculadora(Frame):
    entry = None

    def __init__(self):
        super().__init__()
        self.initComponents()

    def initComponents(self):
        self.master.title("Calculadora Iván García")
        self.definirTabla()
        self.crearCampoDeTexto()
        self.establecerEstilo()
        self.crearBotonesNumericos()
        self.crearBotonesOperacionales()
        self.pack()

    def establecerEstilo(self):
        Style().configure("TButton", font='20')

    def definirTabla(self):
        self.columnconfigure(0, pad=3)
        self.columnconfigure(1, pad=3)
        self.columnconfigure(2, pad=3)
        self.columnconfigure(3, pad=3)

        self.rowconfigure(0, pad=3)
        self.rowconfigure(1, pad=3)
        self.rowconfigure(2, pad=3)
        self.rowconfigure(3, pad=3)
        self.rowconfigure(4, pad=3)

    def crearCampoDeTexto(self):
        self.entry = Entry(self, font=20)
        self.entry.grid(row=0, columnspan=4, sticky=W + E)

    def crearBotonesNumericos(self):
        cero = Button(self, text="0", command=lambda: self.introducirCaracter('0'))
        cero.grid(row=5, column=0)

        uno = Button(self, text="1", command=lambda: self.introducirCaracter('1'))
        uno.grid(row=4, column=0)

        dos = Button(self, text="2", command=lambda: self.introducirCaracter('2'))
        dos.grid(row=4, column=1)

        tres = Button(self, text="3", command=lambda: self.introducirCaracter('3'))
        tres.grid(row=4, column=2)

        cuatro = Button(self, text="4", command=lambda: self.introducirCaracter('4'))
        cuatro.grid(row=3, column=0)

        cinco = Button(self, text="5", command=lambda: self.introducirCaracter('5'))
        cinco.grid(row=3, column=1)

        seis = Button(self, text="6", command=lambda: self.introducirCaracter('6'))
        seis.grid(row=3, column=2)

        siete = Button(self, text="7", command=lambda: self.introducirCaracter('7'))
        siete.grid(row=2, column=0)

        ocho = Button(self, text="8", command=lambda: self.introducirCaracter('8'))
        ocho.grid(row=2, column=1)

        nueve = Button(self, text="9", command=lambda: self.introducirCaracter('9'))
        nueve.grid(row=2, column=2)

        punto = Button(self, text=".", command=lambda: self.introducirCaracter('.'))
        punto.grid(row=5, column=1)

    def crearBotonesOperacionales(self):
        sumar = Button(self, text="+", command=lambda: self.introducirCaracter('+'))
        sumar.grid(row=5, column=3)

        restar = Button(self, text="-", command=lambda: self.introducirCaracter('-'))
        restar.grid(row=4, column=3)

        multiplicar = Button(self, text="*", command=lambda: self.introducirCaracter('*'))
        multiplicar.grid(row=3, column=3)

        dividir = Button(self, text="/", command=lambda: self.introducirCaracter('/'))
        dividir.grid(row=2, column=3)

        limpiar = Button(self, text="Limpiar", command=self.limpiar)
        limpiar.grid(row=1, column=0)

        retroceder = Button(self, text="←", command=self.retroceder)
        retroceder.grid(row=1, column=1)

        igual = Button(self, text="=", command=self.mostrarResultado)
        igual.grid(row=5, column=2)

    def mostrarResultado(self):
        try:
            resultado = eval(self.entry.get())
            self.limpiar()
            self.introducirCaracter(resultado)
        except SyntaxError:
            self.limpiar()
            self.introducirCaracter("Buen intento, Roberto.")
        except ZeroDivisionError:
            self.limpiar()
            self.introducirCaracter("No cuela, Roberto.")
        except:
            self.limpiar()
            self.introducirCaracter("Va a ser que no.")

    def introducirCaracter(self, caracter):
        self.entry.insert(len(self.entry.get()), caracter)

    def limpiar(self):
        self.entry.delete(0, len(self.entry.get()))

    def retroceder(self):
        operacion = self.entry.get()
        self.limpiar()
        self.introducirCaracter(operacion[:-1])


if __name__ == '__main__':
    root = Tk()
    app = Calculadora()
    root.mainloop()
