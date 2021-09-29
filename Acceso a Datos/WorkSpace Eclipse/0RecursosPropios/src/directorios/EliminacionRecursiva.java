package directorios;

import java.io.File;

public class EliminacionRecursiva {
	public static void main(String[] args) {
		File file = new File("Iconos Simples");
		eliminarDirectorio(file);
	}

	private static void eliminarDirectorio(File directorioAEliminar) {
		if (directorioAEliminar.isDirectory()) {
			if (directorioAEliminar.list().length == 0)
				directorioAEliminar.delete();

			else {
				for (String temp : directorioAEliminar.list()) {
					File fileDelete = new File(directorioAEliminar, temp);
					eliminarDirectorio(fileDelete);
				}

				if (directorioAEliminar.list().length == 0)
					directorioAEliminar.delete();
			}

		} else {
			directorioAEliminar.delete();
		}
	}
}
