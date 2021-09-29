import java.io.File;

public class EliminarFicheros {

	public static void main(String[] args) {
		try {
			File directorio = new File("Teoría");
			String[] archivos = directorio.list();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					File aBorrar = new File(directorio, archivos[i]);
					aBorrar.delete();
				}
			}

			if (directorio.delete()) {
				System.out.println("El directorio ha sido eliminado correctamente.");
			} else {
				System.out.println("Error en la eliminación del directorio.");
			}

			directorio = new File("Práctica");
			archivos = directorio.list();
			for (int i = 0; i < archivos.length; i++) {
				File aBorrar = new File(directorio, archivos[i]);
				if (aBorrar.getName().equals("prac2") && aBorrar.delete()) {
					System.out.println("Archivo eliminado: " + archivos[i]);
					break;
				}
			}

		} catch (Exception e) {
			System.err.println("Algo ha ido mal.");
		}

	}

}
