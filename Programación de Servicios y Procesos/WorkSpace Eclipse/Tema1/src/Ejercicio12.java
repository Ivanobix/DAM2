import java.io.IOException;

public class Ejercicio12 {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder();

		// Para el directorio del programa
		pb.command("CMD", "/C", "start", "DIR");

		// Para un directorio concreto
		// pb.command("CMD", "/C", "start", "DIR", "c:\\Program Files");

		pb.start();

		// ¿¿¿¿¿El /C es para cerrar el proceso y /K para mantenerlo??????
	}

}
