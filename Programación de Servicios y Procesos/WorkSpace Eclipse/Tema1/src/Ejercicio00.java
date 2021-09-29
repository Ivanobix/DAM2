import java.io.IOException;

public class Ejercicio00 {

	public static void main(String[] args) throws IOException {
		//Abrir el notepad
		//ProcessBuilder pb = new ProcessBuilder("NOTEPAD");
		//Abrir un determinado archivo con notepad
		ProcessBuilder pb = new ProcessBuilder("NOTEPAD", "C:/Users/ivgap/OneDrive/Escritorio/Prueba.txt");
		pb.start();
		
	}
}
