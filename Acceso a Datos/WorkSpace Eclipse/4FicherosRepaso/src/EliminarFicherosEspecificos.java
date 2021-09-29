import java.io.File;

public class EliminarFicherosEspecificos {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Los valores introducidos no son válidos (1º Directorio 2º Fichero)");
		} else {
			try {
				File directorio = new File(args[0]);
				String[] archivos = directorio.list();
				if (archivos != null) {
					for (int i = 0; i < archivos.length; i++) {
						File aBorrar = new File(directorio, archivos[i]);
						if (aBorrar.getName().equals(args[1]) && aBorrar.delete()) {
							System.out.println("Archivo eliminado: " + archivos[i]);
							break;
						}
					}
				}

			} catch (Exception e) {
				System.err.println("Algo ha ido mal.");
			}

		}
	}

}
