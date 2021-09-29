import MySQLdb
import psycopg2


class BBDDDam2:
    user = ''
    password = ''
    host = ''
    database = ''
    esPostgres = False

    conn = None
    cur = None

    registroActual = None
    posicionCursor = 0

    ultimaSentencia = ''
    filasAfectadas = 0

    def __init__(self, user, password, host, database, esPostgres):
        self.user = user
        self.password = password
        self.host = host
        self.database = database
        self.esPostgres = esPostgres

    def obtenerConexion(self):
        if self.esPostgres:
            self.conn = psycopg2.connect(user=self.user, password=self.password, host=self.host, database=self.database)
        else:
            self.conn = MySQLdb.connect(user=self.user, passwd=self.password, host=self.host, db=self.database)

    def obtenerCursor(self):
        self.cur = self.conn.cursor()

    def cerrarConexion(self):
        self.conn.close()

    def cerrarCursor(self):
        self.cur.close()

    def comprobarConexionYCursor(self):
        if self.conn is None:
            self.obtenerConexion()
            if self.cur is None:
                self.obtenerCursor()

    def refrescarCursor(self):
        self.comprobarConexionYCursor()
        self.cur = self.conn.cursor()
        self.cur.execute(self.ultimaSentencia)

    def ejecutarSQL(self, sentencia):
        self.comprobarConexionYCursor()
        self.ultimaSentencia = sentencia
        self.cur.execute(sentencia)

    def devolverUnRegistro(self):
        self.comprobarConexionYCursor()
        return self.registroActual

    def devolverSiguiente(self):
        self.comprobarConexionYCursor()
        self.posicionCursor += 1
        self.registroActual = self.cur.fetchone()
        return self.registroActual

    def devolverAnterior(self):
        self.comprobarConexionYCursor()
        self.posicionCursor -= 1
        self.refrescarCursor()
        for i in range(self.posicionCursor):
            self.devolverSiguiente()
        return self.registroActual

    def devolverElRegistro(self, posicion):
        self.comprobarConexionYCursor()
        self.posicionCursor = posicion
        self.refrescarCursor()
        for i in range(posicion):
            self.devolverSiguiente()
        return self.registroActual

    def devolverTodos(self):
        self.comprobarConexionYCursor()
        return self.cur.fetchall()

    def devolverAfectados(self):
        self.comprobarConexionYCursor()
        return self.filasAfectadas

    def insertar(self, tabla, *valores):
        self.comprobarConexionYCursor()
        camposAInsertar = ''
        valoresAInsertar = ''
        contador = 1
        for i in valores:
            if contador % 2 != 0:
                camposAInsertar += str(i) + ","
            else:
                valoresAInsertar += "'" + str(i) + "'" + ","
            contador += 1

        camposAInsertar = camposAInsertar[:-1]
        valoresAInsertar = valoresAInsertar[:-1]

        self.filasAfectadas = self.cur.execute(
            "INSERT INTO " + tabla + " (" + camposAInsertar + ") VALUES (" + valoresAInsertar + ");")
        self.conn.commit()

    def eliminar(self, tabla, *valores):
        self.comprobarConexionYCursor()
        condicionEliminado = ''
        contador = 1
        for i in valores:
            if contador % 2 != 0:
                condicionEliminado += str(i) + " = "
            else:
                condicionEliminado += "'" + str(i) + "'" + " AND "
            contador += 1

        condicionEliminado = condicionEliminado[:-5]

        self.filasAfectadas = self.cur.execute("DELETE FROM " + tabla + " WHERE " + condicionEliminado + ";")
        self.conn.commit()

    def actualizar(self, tabla, *valores):
        self.comprobarConexionYCursor()
        condicionActualizacion = ''
        contador = 1
        for i in valores:
            if contador % 2 != 0:
                condicionActualizacion += str(i) + " = "
            else:
                condicionActualizacion += "'" + str(i) + "'" + ", "
            contador += 1

        condicionActualizacion = condicionActualizacion[:-2]

        self.filasAfectadas = self.cur.execute("UPDATE " + tabla + " SET " + condicionActualizacion + ";")
        self.conn.commit()


# Esto es únicamente para probar
if __name__ == '__main__':
    bbddDam2 = BBDDDam2('root', '', '127.0.0.1', 'test', False)

    bbddDam2.ejecutarSQL("SELECT * FROM examen")
    print(bbddDam2.devolverSiguiente())
    print(bbddDam2.devolverElRegistro(12))
    print(bbddDam2.devolverElRegistro(13))
    bbddDam2.cerrarCursor()
    bbddDam2.cerrarConexion()
