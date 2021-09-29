package texto;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecturaCaracteres {

	public static void main(String[] args) throws IOException {
		String ruta = "src/ejemplo.txt";
		File fichero = new File(ruta);
		FileReader fic = new FileReader(fichero);
		int i;
		while ((i = fic.read()) != -1) {
			System.out.println((char) i);
		}
		fic.close();

	}

}
