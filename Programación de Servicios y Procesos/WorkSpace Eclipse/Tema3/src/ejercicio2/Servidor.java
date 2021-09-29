package ejercicio2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		new Servidor();
	}

	public Servidor() {
		try {
			ServerSocket servidor = new ServerSocket(6000);
			Socket miSocket = servidor.accept();

			System.out.println("Escuchando en el puerto: " + miSocket.getLocalPort());

			DataInputStream flujoDeEntrada = new DataInputStream(miSocket.getInputStream());
			String mensaje = flujoDeEntrada.readUTF();
			System.out.println(mensaje);

			miSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
