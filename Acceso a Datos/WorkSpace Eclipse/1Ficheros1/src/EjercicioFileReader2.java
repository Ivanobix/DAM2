import java.io.File;
import java.io.FileReader;

public class EjercicioFileReader2 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("No has introducido un directorio válido.");
		} else {
			try {
				File fichero = new File(args[0]);
				FileReader fic = new FileReader(fichero);
				char b[] = new char[20];
				while ((fic.read(b)) != -1) {
					System.out.println(b);
				}
				fic.close();
			} catch (Exception e) {
				System.out.println("Algo no ha ido bien.");
			}

		}

	}

}
