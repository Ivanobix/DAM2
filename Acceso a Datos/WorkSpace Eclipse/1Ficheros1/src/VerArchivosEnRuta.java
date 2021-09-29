import java.io.File;

public class VerArchivosEnRuta {
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("No has introducido un directorio válido.");
			System.out.println(args[0]);
		} else {
			String dir = args[0];
			File f = new File(dir);
			if (f.isDirectory()) {
				String[] archivos = f.list();
				System.out.println("---- ARCHIVOS EN EL DIRECTORIO ACTUAL -----");
				for (int i = 0; i < archivos.length; i++) {
					System.out.println("Nombre: " + archivos[i]);
				}
			} else {
				System.out.println("La ruta introducida no existe.");
			}
		}
	}
}