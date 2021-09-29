import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Agenda {

	public static void main(String[] args) {
		String[] nombre = { "Pedro", "Oscar", "Inés", "Bartolo" };
		int[] edad = { 40, 50, 60, 30 };
		int[] telefono = { 678678720, 656787730, 625678678, 620456730 };

		// Escritura de datos
		try {
			File fichero = new File("Agenda.dat");
			ObjectOutputStream dataOS;
			if (!fichero.exists()) {
				FileOutputStream fileout = new FileOutputStream(fichero);
				dataOS = new ObjectOutputStream(fileout);
			} else {
				dataOS = new MiObjectOutputStream(new FileOutputStream(fichero, true));
			}

			// Cantidad de personas a introducir (máximo 5)
			int repeticiones = edad.length;
			if (repeticiones > 5) {
				repeticiones = 5;
			}
			// Introducción de las personas en la agenda
			for (int i = 0; i < repeticiones; i++) {
				Persona persona = new Persona(nombre[i], edad[i], telefono[i]);
				dataOS.writeObject(persona);
			}
			dataOS.close();

		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la escritura.");
		}

		// Lectura de datos
		try {
			File fichero = new File("Agenda.dat");
			FileInputStream filein = new FileInputStream(fichero);
			ObjectInputStream dataIS = new ObjectInputStream(filein);
			// Lectura de todos los datos contenidos en el archivo
			try {
				while (true) {
					Persona persona = (Persona) dataIS.readObject();
					System.out.println(persona);
					System.out.println("********************");
				}
			} catch (Exception e) {
			}
			dataIS.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la lectura.");
		}
	}

}
