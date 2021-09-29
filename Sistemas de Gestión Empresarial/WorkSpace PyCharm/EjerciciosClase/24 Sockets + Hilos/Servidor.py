import socket
from _thread import start_new_thread

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

IP_address = input("¿Cual es la dirección IP del Servidor?: ")
Port = int(input("¿Cual es la dirección Puerto del Servidor?: "))

server.bind((IP_address, Port))
server.listen(100)
list_of_clients = []


def clientthread(conn, addr):
    conn.send("Bienvenido a mi Chat!")
    while True:
        try:
            message = conn.recv(2048)
            if message:
                print("<" + addr[0] + "> " + message)
                message_to_send = "<" + addr[0] + "> " + message
                broadcast(message_to_send, conn)
            else:
                remove(conn)
        except:
            continue


def broadcast(message, connection):
    for clients in list_of_clients:
        if clients != connection:
            try:
                clients.send(message.encode())
            except:
                clients.close()
                remove(clients)


def remove(connection):
    if connection in list_of_clients:
        list_of_clients.remove(connection)


while True:
    conn, addr = server.accept()
    list_of_clients.append(conn)
    print(addr[0] + " conectado.")
    start_new_thread(clientthread, (conn, addr))

conn.close()
server.close()
