import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EjercicioFileReader1 {

	public static void main(String[] args) throws IOException {
		String ruta = "src/ejemplo.txt";
		File fichero = new File(ruta);
		FileReader fic = new FileReader(fichero);
		char b[] = new char[500];
		while ((fic.read(b)) != -1) {
			System.out.println(b);
		}
		fic.close();

	}

}
