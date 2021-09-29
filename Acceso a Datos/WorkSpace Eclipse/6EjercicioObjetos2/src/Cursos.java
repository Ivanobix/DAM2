import java.io.File;
import java.io.RandomAccessFile;

public class Cursos {

	public static void main(String[] args) {

		// Escritura de datos.
		try {
			File fichero = new File("Cursos.dat");
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");

			int numero[] = { 10, 23, 51, 25 };
			String nombre[] = { "Office", "PHP", "Android", "Acceso a datos" };
			Double coste[] = { 4000.0, 5000.0, 6000.0, 3000.0 };
			int alumnos[] = { 20, 30, 25, 30 };

			StringBuffer buffer = null;

			int n = numero.length;
			for (int i = 0; i < n; i++) {
				file.writeInt(numero[i]);
				buffer = new StringBuffer(nombre[i]);
				buffer.setLength(25);
				file.writeChars(buffer.toString());
				file.writeDouble(coste[i]);
				file.writeInt(alumnos[i]);
			}
			file.close();

		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la escritura.");
		}

		// Lectura de datos.
		try {
			File fichero = new File("Cursos.dat");
			RandomAccessFile file = new RandomAccessFile(fichero, "r");
			int numero, alumnos, posicion;
			Double coste;
			char nombre[] = new char[25], aux;
			posicion = 0;

			for (;;) {
				file.seek(posicion);
				numero = file.readInt();
				for (int i = 0; i < nombre.length; i++) {
					aux = file.readChar();
					nombre[i] = aux;
				}

				String nombreCompleto = new String(nombre);
				coste = file.readDouble();
				alumnos = file.readInt();

				if (numero > 0) {
					System.out.println("Código: " + numero + " Nombre: " + nombreCompleto + " Precio: " + coste
							+ " Alumnos: " + alumnos);
					posicion = posicion + 66;
				}
				if (file.getFilePointer() == file.length()) {
					break;
				}

			}
			file.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la lectura.");
		}
	}

}
