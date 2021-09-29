import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ejemplo7 {

	public static void main(String[] args) {
		// Para mostrar los argumentos de un comando(resultado del while)

		ProcessBuilder test = new ProcessBuilder();
		Map entorno = test.environment();
		System.out.println("Variables de entorno:");
		System.out.println(entorno);

		test = new ProcessBuilder("java", "EjemploLectura", "Hola");

		List l = test.command();
		Iterator iter = l.iterator();
		System.out.println("\nArgumentos del comando");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		test = test.command("CMD", "/C", "DIR");
		try {
			Process p = test.start();
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
