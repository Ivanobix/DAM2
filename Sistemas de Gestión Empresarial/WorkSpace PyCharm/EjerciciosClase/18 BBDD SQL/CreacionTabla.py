import MySQLdb

try:
    conn = MySQLdb.connect(user='root', password='', host='127.0.0.1', database='test')
    cursor = conn.cursor()
    sql = "create table persona2(dni varchar(9) not null primary key, Nombre varchar(25) not null, Apellidos varchar(50) not null, Edad int(11) not null);"
    cursor.execute(sql)
except:
    print("Error")
finally:
    cursor.close()
    conn.close()
