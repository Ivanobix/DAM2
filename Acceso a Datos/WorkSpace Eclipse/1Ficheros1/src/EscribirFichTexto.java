import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichTexto {

	public static void main(String[] args) throws IOException {
		File fichero = new File ( "D:\\DAM 2\\\\Acceso a Datos\\WorkSpace Eclipse\\AccesoADatosTemporal\\src\\ejemplo.txt");
		
		FileWriter fic = new FileWriter(fichero);
		String cadena = "Esto es una prueba con FIleWriter";
		char[] cad = cadena.toCharArray();
		for (int i = 0; i <cad.length; i++) {
			fic.write(cad[i]);
		}
		
		//Para añadirlo todo usar fic.write(cad)
		fic.append('*');
		fic.close();

	}

}
