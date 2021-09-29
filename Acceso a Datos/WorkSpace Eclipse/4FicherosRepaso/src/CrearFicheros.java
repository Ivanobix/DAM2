import java.io.File;

public class CrearFicheros {

	public static void main(String[] args) {
		try {
			// Teor�a
			File file = new File("Teor�a");
			file.mkdir();
			file = new File("Teor�a", "tema1");
			file.createNewFile();

			// Pr�ctica
			file = new File("Pr�ctica");
			file.mkdir();
			for (int i = 1; i < 4; i++) {
				file = new File("Pr�ctica", "prac" + i);
				file.createNewFile();
			}
		} catch (Exception e) {
			System.err.println("Algo ha ido mal en la escritura.");
		}

	}

}
