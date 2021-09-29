import java.io.IOException;
import java.util.Scanner;

public class Ejercicio11F {

	public static void main(String[] args) {
		String nombreProceso;

		try (Scanner S = new Scanner(System.in)) {
			System.out.println("Introduce el proceso a lanzar: ");
			nombreProceso = S.next();
		}
		System.out.println("Vamos a lanzar el proceso: " + nombreProceso);

		ProcessBuilder pb = new ProcessBuilder(nombreProceso);
		try {
			Process process = pb.start();
			int retorno = process.waitFor();
			System.out.println("La ejecuci�n de " + nombreProceso + " devuelve " + retorno);

		} catch (IOException ex) {
			System.err.println("Excepci�n de E/S");
			System.exit(-1);
		} catch (InterruptedException ex) {
			System.err.println("El proceso hijo finaliz� de forma incorrecta");
			System.exit(-1);
		}

	}

}
