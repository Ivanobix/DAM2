package ejercicio2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		new Cliente();
	}

	public Cliente() {
		try {
			Socket miSocket = new Socket("localhost", 6000);
			System.out.println("Puerto local: " + miSocket.getLocalPort());
			System.out.println("Puerto remoto: " + miSocket.getPort());
			System.out.println("Puerto Host/IP: " + miSocket.getInetAddress());
			
			DataOutputStream flujoDeSalida = new DataOutputStream(miSocket.getOutputStream());
            flujoDeSalida.writeUTF("Hola");
            
			miSocket.close();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
