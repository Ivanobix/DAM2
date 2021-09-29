import MySQLdb

try:
    conn = MySQLdb.connect(user='root', password='', host='127.0.0.1', database='test')
    cursor = conn.cursor()
    sql = "delete from persona where DNI = '123q';"
    cursor.execute(sql)
    conn.commit()
except:
    print("Error")
finally:
    cursor.close()
    conn.close()
