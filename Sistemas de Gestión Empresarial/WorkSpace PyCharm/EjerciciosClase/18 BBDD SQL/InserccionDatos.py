import MySQLdb

try:
    conn = MySQLdb.connect(user='root', password='', host='127.0.0.1', database='test')
    cursor = conn.cursor()
    sql = "insert into persona (DNI, Nombre, Apellidos, Edad) values ('11q','1','1',1), ('12q','12','2',2), ('123q','12','2',2);"
    cursor.execute(sql)
    conn.commit()
except:
    print("Error")
finally:
    cursor.close()
    conn.close()
