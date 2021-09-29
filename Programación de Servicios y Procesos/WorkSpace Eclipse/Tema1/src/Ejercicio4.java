import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejercicio4 {

	public static void main(String[] args) throws IOException {
		// Para que este programa funcione debemos ejecutarlo desde un cmd con
		// privilegios de administrador.

		Process p = new ProcessBuilder("CMD", "/C", "DATE").start();

		// Escritura: Envia entrada a DATE
		OutputStream os = p.getOutputStream();
		os.write("01-12-17".getBytes());
		// Optimización: Vaciar memoria del buffer
		os.flush();

		// Lectura: Obtiene la salida de DATE
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}
		is.close();

		// Comprobación de error, si sale 0 es que ha ido bien, si sale 1 ha ido mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// En caso de que haya ocurrido un error muestra la descripción del mismo
		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			System.out.println("---------------");
			while ((liner = brer.readLine()) != null) {
				System.out.println(liner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
