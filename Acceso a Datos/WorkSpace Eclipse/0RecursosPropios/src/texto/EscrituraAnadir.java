package texto;

import java.io.*;

public class EscrituraAnadir {

	public static void main(String[] args) {
		String data = "Probando";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {

			fw = new FileWriter("ejemplo2.txt", true);
			bw = new BufferedWriter(fw);
			bw.write(data);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
