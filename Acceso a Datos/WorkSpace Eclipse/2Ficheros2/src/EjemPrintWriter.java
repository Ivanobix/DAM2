import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EjemPrintWriter {

	public static void main(String[] args) throws IOException {
		PrintWriter fichero = new PrintWriter(new FileWriter("FichTexto.txt"));
		for (int i = 1; i < 11; i++) {
			// Se usa println se usa para escribir con saltos de l�nea
			// Para escribirlo todo seguido usar print
			fichero.println("Fila n�mero: " + i);
		}
		fichero.close();
	}
}
