import java.io.IOException;

public class Ejercicio00a  {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("NOTEPAD", "C:/Users/ivgap/OneDrive/Escritorio/Prueba.txt");
		Process p = pb.start();
		
		//Para saber el PID del proceso, está bien escrito pero no me funciona no se por qué
		System.out.println(p.pid());
		
		//Cerramos el proceso
		p.destroy();

		//Para saber si está vivo
		if (!p.isAlive()) {
			System.out.println("Está muerto.");
		}

	}
}
