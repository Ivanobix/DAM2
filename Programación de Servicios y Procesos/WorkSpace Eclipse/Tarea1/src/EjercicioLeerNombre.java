import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class EjercicioLeerNombre {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre", "Iván");
			File directorio = new File(".\\bin");
			pb.directory(directorio);
			Process p = pb.start();

			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();

			int errCode = p.waitFor();
			System.out.println("Error al ejecutar el comando? " + (errCode == 1 ? "No (-1)" : "Sí (-1)"));

		} catch (IOException e) {
			System.err.println("Error de E/S.");
		} catch (InterruptedException ex) {
			System.err.println("Problema con los hilos.");
		}

	}

}
