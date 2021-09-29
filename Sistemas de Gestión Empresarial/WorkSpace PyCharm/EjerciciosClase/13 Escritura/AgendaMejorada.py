import os


class AgendaSimple:
    nombreArchivo = ""
    salir = False

    def getSalir(self):
        return self.salir

    def obtenerRutaFichero(self):
        return os.path.dirname(os.path.abspath(__file__)) + "\\" + self.nombreArchivo

    def mostrarMenu(self):
        print(
            "Agenda: \n  1. Indicar nombre de archivo.\n"
            "  2. Leer archivo.\n"
            "  3. Mostrar personas.\n"
            "  4. Mostrar datos de una persona.\n"
            "  5. Salir.\n"
            "  6. Modificar.")
        entrada = input("Indique opción: ")
        if entrada == "1":
            self.seleccionarArchivo()
        elif entrada == "2":
            self.leerArchivo()
        elif entrada == "3":
            self.mostrarPersonas()
        elif entrada == "4":
            self.mostrarDatosPersona()
        elif entrada == "5":
            self.exit()
        elif entrada == "6":
            self.modificarPersona()
        else:
            print("La opción marcada no existe.\n")
            self.mostrarMenu()

    def seleccionarArchivo(self):
        self.nombreArchivo = input("Introduce el nombre del archivo: ")
        if not os.path.isfile(self.obtenerRutaFichero()):
            self.nombreArchivo = ""
            print("El archivo no existe.")

        print("\n")

    def leerArchivo(self):
        if self.nombreArchivo != "":
            rutaFichero = self.obtenerRutaFichero()
            contador = 1
            with open(rutaFichero, 'r', encoding='utf-8') as fh:
                for contenido in fh:
                    print("Contacto " + str(contador) + ": " + contenido)
                    contador += 1
        else:
            print("No has seleccionado ningún fichero.")

        print("\n")

    def mostrarPersonas(self):
        if self.nombreArchivo != "":
            rutaFichero = self.obtenerRutaFichero()
            contador = 1
            with open(rutaFichero, 'r', encoding='utf-8') as fh:
                for linea in fh:
                    datos = linea.split(";")
                    nombre = datos[0]
                    print("Contacto " + str(contador) + ": " + nombre)
                    contador += 1
        else:
            print("No has seleccionado ningún fichero.")

        print("\n")

    def mostrarDatosPersona(self):
        if self.nombreArchivo != "":
            self.mostrarPersonas()
            numero = input("¿Qué contacto desea buscar? (Número de contacto): ")
            rutaFichero = self.obtenerRutaFichero()
            contador = 1
            with open(rutaFichero, 'r', encoding='utf-8') as fh:
                for linea in fh:
                    datos = linea.split(";")
                    if str(contador) == numero:
                        contador = -1
                        nombre = datos[0]
                        direccion = datos[1]
                        telefono = datos[2]
                        print("Nombre: " + nombre + " // Dirección: " + direccion + " // Teléfono: " + telefono)
                    else:
                        contador += 1
            if contador == -1:
                print("No existe el contacto indicado.")
        else:
            print("No has seleccionado ningún fichero.")

        print("\n")

    def exit(self):
        print("Hasta luego.")
        self.salir = True

    def modificarPersona(self):
        if self.nombreArchivo != "":
            self.mostrarPersonas()
            personaAModificar = input("¿Qué persona deseas modificar? (Número de contacto):")
            campoAModificar = input(" 0 -> Nombre \n 1 -> Dirección \n 2 -> Teléfono\n")
            nuevoDato = input("Introduzca el nuevo dato: ")

            aDevolver = ""
            rutaFichero = self.obtenerRutaFichero()
            with open(rutaFichero, 'r', encoding='utf-8') as fh:
                contador = 1
                for linea in fh:
                    if str(contador) == personaAModificar:
                        datos = linea.split(";")
                        if campoAModificar == "0":
                            datos[0] = nuevoDato
                        elif campoAModificar == "1":
                            datos[1] = nuevoDato
                        else:
                            datos[2] = nuevoDato

                        aDevolver += datos[0] + ";" + datos[1] + ";" + datos[2]
                    else:
                        aDevolver += linea
                    contador += 1

            with open(rutaFichero, 'w', encoding='utf-8') as fh:
                fh.write(aDevolver)

        else:
            print("No has seleccionado ningún fichero.")


if __name__ == '__main__':
    agenda = AgendaSimple()
    while agenda.getSalir() == False:
        agenda.mostrarMenu()
