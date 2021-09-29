import java.io.IOException;
import java.io.InputStream;

public class Ejemplo2B {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		//Cambiamos dir por un comando cualquiera que sea incorrecto y muestre un error (en mi caso comandoCualquiera) 
		pb.command("CMD", "/C", "start", "comandoCualquiera");
		Process p = pb.start();

		try {
			// Cambiamos p.getInputStream() por p.getErrorStream() de manera que nos muestre el error por consola.
			InputStream is = p.getErrorStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();

			// Versión línea a línea, más rápida
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

		// Comprobación de error, si sale 0 es que ha ido bien, si sale 1 ha ido mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
