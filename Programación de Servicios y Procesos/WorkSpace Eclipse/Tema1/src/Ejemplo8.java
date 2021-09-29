import java.io.File;
import java.io.IOException;

public class Ejemplo8 {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DFSDSV");

		File fOut = new File("salida.txt");
		File fErr = new File("error.txt");

		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		pb.start();
	}

}
