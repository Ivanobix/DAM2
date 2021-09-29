import java.io.File;

public class VerArchivosEnProyecto {
	public static void main(String[] args) {
		String dir = ".";
		File f = new File(dir);
		String[] archivos = f.list();
		System.out.println("---- ARCHIVOS EN EL DIRECTORIO ACTUAL -----");
		for (int i = 0; i < archivos.length; i++) {
			System.out.println("Nombre: " + archivos[i]);
		}
		dir = "bin";
		f = new File(dir);
		archivos = f.list();
		System.out.println("---- ARCHIVOS EN EL DIRECTORIO BIN -----");
		for (int i = 0; i < archivos.length; i++) {
			System.out.println("Nombre: " + archivos[i]);
		}
	}
}