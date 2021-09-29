package texto;

import java.io.*;

public class EscrituraSobreescribir {

	public static void main(String[] args) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("ejemplo2.txt"));
			escribeFichero(bw);
			bw.flush();

		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}
	}

	public static void escribeFichero(BufferedWriter bw) throws IOException {
		bw.write("Esto es una prueba usando Buffered");
		bw.newLine();
		bw.write("Seguimos usando Buffered");
	}

}
