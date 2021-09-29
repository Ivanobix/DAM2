package texto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LecturaLineas {

	public static void main(String[] args) {

		String nombreFichero = "prueba.txt";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(nombreFichero));
			String texto = br.readLine();
			while (texto != null) {
				System.out.println(texto);
				texto = br.readLine();
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		}

		catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}

		finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}

	}

}
