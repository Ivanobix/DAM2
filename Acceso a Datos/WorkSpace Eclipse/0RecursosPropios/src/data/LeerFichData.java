package data;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerFichData {

	public static void main(String[] args) throws IOException {
		File fichero = new File("prueba3.txt");
		FileInputStream filein = new FileInputStream(fichero);
		DataInputStream dataIS = new DataInputStream(filein);

		String n;
		int e;
		try {
			// Hacer siempre hasta que salte una excpción porque no hay más datos que leer
			while (true) {
				n = dataIS.readUTF();
				e = dataIS.readInt();
				System.out.println("Nombre: " + n + ", edad. " + e);
			}
			// Cuando se acabe los datos muestra Fin de la Lectura
		} catch (EOFException eo) {
			System.out.println("Fin de la lectura.");
		}
		dataIS.close();
	}

}
