
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class EjercicioEjemploLectura {

	public static void main(String[] args) {

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura");
			File directorio = new File(".\\bin");
			pb.directory(directorio);
			Process p = pb.start();

			OutputStream os = p.getOutputStream();
			os.write("Hola Manuel\n".getBytes());
			os.flush();

			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();

			int errCode = p.waitFor();
			System.out.println("Error al ejecutar el comando? " + (errCode == 0 ? "No" : "Sí"));

		} catch (IOException e) {
			System.err.println("Error de E/S.");
		} catch (InterruptedException ex) {
			System.err.println("Problema con los hilos.");
		}

	}
}
