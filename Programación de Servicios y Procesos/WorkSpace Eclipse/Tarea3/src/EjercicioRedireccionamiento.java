import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class EjercicioRedireccionamiento {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura");
			File directorio = new File(".\\bin");
			pb.directory(directorio);
			pb.redirectOutput(new File("salidaTarea3.txt"));
			pb.redirectError(new File("erroresTarea3.txt"));
			Process p = pb.start();

			OutputStream os = p.getOutputStream();
			os.write("Hola Manuel\n".getBytes());
			os.flush();

			int errCode = p.waitFor();
			System.out.println("Valor de salida: " + (errCode == 0 ? "0" : "1"));

		} catch (IOException e) {
			System.err.println("Error de E/S.");
		} catch (InterruptedException ex) {
			System.err.println("Problema con los hilos.");
		}
	}

}
