import MySQLdb

try:
    conn = MySQLdb.connect(user='root', password='', host='127.0.0.1', database='test')
    cursor = conn.cursor()
    cursor.execute("select * from persona")
    row = cursor.fetchone()
    while row is not None:
        print(row)
        row = cursor.fetchone()
except:
    print("Error")
finally:
    cursor.close()
    conn.close()
