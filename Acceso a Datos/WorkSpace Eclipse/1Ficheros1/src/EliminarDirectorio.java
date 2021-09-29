import java.io.File;
import java.io.IOException;

public class EliminarDirectorio {

	public static void main(String[] args) {
		File directorio = new File(".", "DirABorrar");
		File f1 = new File("DirABorrar", "Archivo1.txt");
		File f2 = new File("DirABorrar", "Archivo2.txt");
		File f3 = new File("DirABorrar", "Archivo3.txt");

		// Creaci�n del directorio
		if (directorio.mkdir()) {
			System.out.println("El directorio ha sido creado correctamente.");
		} else {
			System.out.println("Error en la creaci�n del directorio.");
		}

		// Creaci�n de los ficheros e insercci�n de los mismos en el directorio anterior
		try {
			if (f1.createNewFile() && f2.createNewFile() && f3.createNewFile()) {
				System.out.println("Todos los archivos han sido creados.");
			} else {
				System.out.println("Error en la creaci�n de los ficheros.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Eliminaci�n de todos los ficheros en el directorio
		String[] archivos = directorio.list();
		for (int i = 0; i < archivos.length; i++) {
			File aBorrar = new File("./DirABorrar", archivos[i]);
			if (aBorrar.delete()) {
				System.out.println("Archivo eliminado: " + archivos[i]);
			}
		}

		// Eliminaci�n del directorio
		if (directorio.delete()) {
			System.out.println("El directorio ha sido eliminado correctamente.");
		} else {
			System.out.println("Error en la eliminaci�n del directorio.");
		}

	}
}
