import java.io.IOException;
import java.io.InputStream;

public class Ejemplo2 {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "start", "DIR");
		Process p = pb.start();

		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();

			// Versi�n l�nea a l�nea, m�s r�pida
			/*
			 * BufferedReader br = new BufferedReader( new InputStreamReader(is)); 
			 * String linea; 
			 * while ((linea = br.readLine()) != null) { 
			 * 		System.out.println(linea); 
			 * }
			 * br.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Comprobaci�n de error 0 bien 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
