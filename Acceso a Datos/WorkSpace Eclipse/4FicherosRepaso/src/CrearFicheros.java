import java.io.File;

public class CrearFicheros {

	public static void main(String[] args) {
		try {
			// Teoría
			File file = new File("Teoría");
			file.mkdir();
			file = new File("Teoría", "tema1");
			file.createNewFile();

			// Práctica
			file = new File("Práctica");
			file.mkdir();
			for (int i = 1; i < 4; i++) {
				file = new File("Práctica", "prac" + i);
				file.createNewFile();
			}
		} catch (Exception e) {
			System.err.println("Algo ha ido mal en la escritura.");
		}

	}

}
