import java.io.File;

public class VerFicheros {

	public static void main(String[] args) {
		try {
			System.out.println("---- ARCHIVOS EN EL DIRECTORIO ACTUAL -----");
			File f = new File(".");
			String[] archivos = f.list();
			for (int i = 0; i < archivos.length; i++) {
				System.out.println("Nombre: " + archivos[i]);
			}

			System.out.println("\n---- ARCHIVOS EN TEORÍA -----");
			f = new File(".\\Teoría");
			archivos = f.list();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					System.out.println("Nombre: " + archivos[i]);
				}
			} else {
				System.err.println("El directorio no contiene elementos.");
			}

			System.out.println("\n---- ARCHIVOS EN PRÁCTICA -----");
			f = new File(".\\Práctica");
			archivos = f.list();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					System.out.println("Nombre: " + archivos[i]);
				}
			} else {
				System.err.println("El directorio no contiene elementos.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Algo ha ido mal en la lectura.");
		}

	}

}
