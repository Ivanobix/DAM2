import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeerFichTexto20en20 {
	public static void main(String[] args) throws IOException {
		String ruta = "D:\\DAM 2\\Acceso a Datos\\WorkSpace Eclipse\\AccesoADatosTemporal\\src\\ejemplo.txt";
		File fichero = new File(ruta);
		FileReader fic = new FileReader(fichero);
		char b[] = new char[20];
		while ((fic.read(b)) != -1) {
			System.out.println(b);
		}
		fic.close();

	}
}
