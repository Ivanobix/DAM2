package ejercicio1;

import java.net.*;

public class TestInetAddress {
	public static void main(String[] args) {
		InetAddress dir = null;
		System.out.println("========================================================");
		System.out.println("SALIDA PARA LOCALHOST: ");
		try {
			// LOCALHOST
			dir = InetAddress.getByName("localhost");
			pruebaMetodos(dir);//

			// URL www.google.es
			System.out.println("========================================================");
			System.out.println("SALIDA PARA UNA URL:");
			dir = InetAddress.getByName("www.google.es");
			pruebaMetodos(dir);
			System.out.println("========================================================");

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}// main

	private static void pruebaMetodos(InetAddress dir) {
		InetAddress dir2;
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// USAMOS METODOS DE LA CLASE
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}// pruebaMetodos

}// fin
