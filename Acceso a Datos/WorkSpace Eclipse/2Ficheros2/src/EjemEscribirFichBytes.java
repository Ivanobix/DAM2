import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemEscribirFichBytes {

	public static void main(String[] args) throws IOException {
		File fichero = new File(
				"D:\\DAM 2\\Acceso a Datos\\WorkSpace Eclipse\\AccesoADatosTemporal2\\src\\ejemplo.dat");
		// Creo flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		// Creo flujo de entrada
		FileInputStream filein = new FileInputStream(fichero);
		int i;
		// Escribir del 1 al 99 incluidos
		for (i = 1; i < 100; i++) {
			fileout.write(i);
		}
		fileout.close();
		// Mostrar por pantalla el resultado de la escritura anterior (leyendo el fichero)
		while ((i = filein.read()) != -1) {
			System.out.println(i);
		}
		filein.close();
	}

}
