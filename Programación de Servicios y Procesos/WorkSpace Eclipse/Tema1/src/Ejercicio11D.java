import java.io.IOException;

public class Ejercicio11D {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Creamos el proceso NOTEPAD...");
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("NOTEPAD");
		Process p = pb.start();
		System.out.println("El PID del proceso NOTEPAD es: " + p.pid());
		p.waitFor();
		System.out.println("Hemos cerrado el NOTEPAD");
		System.out.println("Retorno NOTEPAD: " + p.exitValue());
	}

}
