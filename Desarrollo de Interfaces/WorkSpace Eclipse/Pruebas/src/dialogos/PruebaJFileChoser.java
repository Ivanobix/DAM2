package dialogos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class PruebaJFileChoser {

	public static void main(String[] args) {
		JFileChooser selector = new JFileChooser(System.getProperty("user.home"));
		//int seleccion = selector.showSaveDialog(null);
		//if (seleccion == JFileChooser.APPROVE_OPTION) {
		//	File fichero = selector.getSelectedFile();
		//	try {
		//		PrintWriter escritor = new PrintWriter(fichero);
		//		escritor.println("Hola");
		//		escritor.close();
		//	} catch (FileNotFoundException e) {
		//		e.printStackTrace();
		//	}
		//}
	}

}
