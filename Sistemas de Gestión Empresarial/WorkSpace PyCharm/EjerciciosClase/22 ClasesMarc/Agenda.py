from Persona import Persona

personas = []


def crearPersona():
    persona = None
    try:
        nombrePersona = input("Dime el nombre de la persona que desea guardar ")
        ciudadPersona = input("En qué ciudad vive? ")
        edadPersona = int(input("Qué edad tiene? "))

        persona = Persona(nombrePersona, ciudadPersona, edadPersona)
    except:
        print("Los datos introducidos no son validos")
        crearPersona()
    return persona


def anadirPersona(persona):
    personas.append(persona)


def eliminarPersona(persona):
    personas.remove(persona)


def cerrarPrograma():
    print("Chao Pescao")
    exit()


def menu():
    print("1 - Listar personas")
    print("2 - Insertar persona")
    print("3 - Eliminar persona")
    print("4 - Modificar persona")
    print("5 - Salir")
    print("\n")

    opcion = input("Seleccione una opción: ")
    print("\n")

    if opcion == "1":
        print("Listado")
        print("\n")

    elif opcion == "2":
        print("Insertado")
        print("\n")

    elif opcion == "3":
        print("Eliminado")
        print("\n")

    elif opcion == "4":
        print("Modificado")
        print("\n")

    elif opcion == "5":
        cerrarPrograma()
    else:
        print("No has introducido un número válido")
        print("\n")
        menu()


if __name__ == '__main__':
    anadirPersona(crearPersona())
    personas.remove(None)
    print(personas)
