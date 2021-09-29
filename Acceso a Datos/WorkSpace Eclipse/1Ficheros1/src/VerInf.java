import java.io.File;

public class VerInf {
	public static void main(String[] args) {
		String dir = ".";
		File f = new File(dir);
		String[] archivos = f.list();
		for (int i = 0; i < archivos.length; i++) {
			f = new File(dir + "/" + archivos[i]);
			System.out.println("Nombre: " + f.getName() + "\nRuta: " + f.getPath() + "\nRuta Absoluta: "
					+ f.getAbsolutePath() + "\n¿Es de escritura?: " + f.canWrite() + "\nTamaño: " + f.getTotalSpace()
					+ "\n¿Es un fichero?: " + f.isFile() + "\n¿Es un directorio?: " + f.isDirectory() + "\nNombre Padre: "
					+ f.getParent());
			System.out.println("--------");
		}
	}
}
