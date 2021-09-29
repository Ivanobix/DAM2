package data;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFichData {

	public static void main(String[] args) throws IOException {
		File fichero = new File("prueba3.txt");
		FileOutputStream fileout = new FileOutputStream(fichero);
		DataOutputStream dataOS = new DataOutputStream(fileout);
		String nombres[] = { "Ana", "Luis Miguel", "Pedro", "Sebastian", "Julio", "Antonio" };
		int edades[] = { 14, 15, 23, 56, 10, 3 };

		for (int i = 0; i < edades.length; i++) {
			dataOS.writeUTF(nombres[i]);
			dataOS.writeInt(edades[i]);
		}
		fileout.close();
		dataOS.close();
	}

}
