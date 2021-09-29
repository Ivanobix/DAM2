package aleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFichAleatorio {

	public static void main(String[] args) throws IOException {
		File fichero = new File("AleatorioEmple.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		String apellido[] = { "GIL", "GARRIDO", "REY" };
		int dep[] = { 1, 4, 5 };
		double salario[] = { 12.0, 567.0, 2342.0 };
		StringBuffer buffer = null;

		int n = apellido.length;
		for (int i = 0; i < n; i++) {
			file.writeInt(i + 1);
			buffer = new StringBuffer(apellido[i]);
			buffer.setLength(10);
			file.writeChars(buffer.toString());
			file.writeInt(dep[i]);
			file.writeDouble(salario[i]);
		}
		file.close();
	}

}
