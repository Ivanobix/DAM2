import MySQLdb


def crearConexion():
    return MySQLdb.connect(user='root', password='', host='127.0.0.1', database='test')


def mostrarMenu():
    print("\n\n-----------------------------------------------------")
    print("1. Listar todos los registros.")
    print("2. Insertar registro.")
    print("3. Eliminar registro.")
    print("4. Modificar registro.")
    print("5. Salir.")
    eleccion = input("Introduce la operación que desees realizar: ")
    if eleccion == "1":
        listar()
    elif eleccion == "2":
        insertar()
    elif eleccion == "3":
        eliminar()
    elif eleccion == "4":
        modificar()
    elif eleccion == "5":
        exit()
    else:
        print("Operación no válida.")
        mostrarMenu()


def listar():
    print("\n---------------------")
    cursor = crearConexion().cursor()
    cursor.execute("SELECT * FROM persona")
    row = cursor.fetchone()
    contador = 1
    while row is not None:
        print(str(contador) + ". " + str(row))
        row = cursor.fetchone()
        contador += 1
    mostrarMenu()


def insertar():
    print("\n---------------------")
    conn = crearConexion()
    cursor = conn.cursor()
    dni = input("Introduce el DNI: ")
    nombre = input("Introduce el nombre: ")
    apellidos = input("Introduce los apellidos: ")
    edad = input("Introduce la edad: ")
    sql = "INSERT INTO persona (DNI, Nombre, Apellidos, Edad) VALUES ('" + dni + "','" + nombre + "','" + apellidos + "'," + str(
        edad) + ");"
    filasAfectadas = cursor.execute(sql)
    if filasAfectadas > 0:
        conn.commit()
        print("Registro insertado.")
    else:
        print("No se ha podido insertar el registro.")
    mostrarMenu()


def eliminar():
    print("\n---------------------")
    metodoDeEliminacion = input("¿Desea borrar por DNI o por posición? (1/2): ")
    if metodoDeEliminacion == "1" or metodoDeEliminacion == "2":
        dni = ""
        if metodoDeEliminacion == "1":
            dni = input("Introduce el DNI: ")
        elif metodoDeEliminacion == "2":
            posicion = input("Introduce la posición: ")
            while not comprobarPosicion(posicion):
                posicion = input("Introduce la posición: ")

            dni = obtenerDniPorPosicion(int(posicion))

        sql = "DELETE FROM persona WHERE DNI = '" + dni + "';"

        conn = crearConexion()
        cursor = conn.cursor()
        filasAfectadas = cursor.execute(sql)
        if filasAfectadas > 0:
            conn.commit()
            print("Registro eliminado.")
        else:
            print("No se ha podido eliminar el registro.")
        mostrarMenu()
    else:
        print("La operación no es válida.")
        eliminar()


def modificar():
    print("\n---------------------")
    metodoDeModificacion = input("¿Desea modificar por DNI o por posición? (1/2): ")
    if metodoDeModificacion == "1" or metodoDeModificacion == "2":
        dni = ""
        if metodoDeModificacion == "1":
            dni = input("Introduce el DNI: ")

        elif metodoDeModificacion == "2":
            posicion = input("Introduce la posición: ")
            while not comprobarPosicion(posicion):
                posicion = input("Introduce la posición: ")
            dni = obtenerDniPorPosicion(int(posicion))

        nombre = input("Introduce el nuevo nombre: ")
        apellidos = input("Introduce los nuevos apellidos: ")
        edad = input("Introduce la nueva edad: ")

        sql = "UPDATE persona SET  Nombre = '" + str(nombre) + "', Apellidos = '" + str(
            apellidos) + "', Edad = " + str(edad) + " WHERE DNI = '" + str(dni) + "';"

        conn = crearConexion()
        cursor = conn.cursor()
        filasAfectadas = cursor.execute(sql)
        if filasAfectadas > 0:
            conn.commit()
            print("Registro modificado.")
        else:
            print("No se ha podido modificar el registro.")
        mostrarMenu()

    else:
        print("La operación no es válida.")
        modificar()


def obtenerDniPorPosicion(pos):
    registros = []
    cursor = crearConexion().cursor()
    cursor.execute("select * from persona")
    row = cursor.fetchone()
    while row is not None:
        registros.append(row)
        row = cursor.fetchone()
    return registros[pos - 1][0]


def obtenerNumeroDeRegistros():
    cursor = crearConexion().cursor()
    cursor.execute("select count(DNI) from persona")
    row = cursor.fetchone()
    return int(row[0])


def comprobarPosicion(posicion):
    aDevolver = False
    if int(posicion) > obtenerNumeroDeRegistros() or int(posicion) < 1:
        print("Introduce una posición válida.")
    else:
        aDevolver = True
    return aDevolver


if __name__ == '__main__':
    mostrarMenu()
