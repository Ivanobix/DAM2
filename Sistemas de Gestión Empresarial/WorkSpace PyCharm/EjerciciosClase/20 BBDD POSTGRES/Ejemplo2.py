import psycopg2

try:
    conn = psycopg2.connect("dbname='arturo_monje' user ='odoosuperuser' host='192.168.202.25' password='1234567890'")
    #cursor = conn.cursor(MySQLdb.cursors.DictCursor)
    cursor = conn.cursor(psycopg2.cursors.DictCursor)
    cursor.execute("SELECT login, password, company_id FROM res_users")
    row = cursor.fetchone()
    while row is not None:
        print(str(row[2]) + " " + str(row[0]) + " " + str(row[1]))
        row = cursor.fetchone()
except:
    print("No se puede conectar")
finally:
    cursor.close()
    conn.close()
