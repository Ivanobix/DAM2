import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejercicio3 {

	public static void main(String[] args) throws IOException {
		// Creamos el enlace al directorio donde está el directorio
		File directorio = new File(".\\bin");
		// Ejecutamos con el comando java el Ejemplo2 que hay en el directorio que le he pasado
		ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo2");
		// Cambiamos el direcotrio del process builder
		pb.directory(directorio);
		// Mostrar la ruta del directorio actual
		System.out.printf("Directorio de trabajo : %s%n", pb.directory());
		// Iniciar proceso
		Process p = pb.start();

		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
