import java.io.File;
import java.io.FileWriter;

public class EjercicioFileWriter1 {

	public static void main(String[] args) {
		File fichero = new File(".\\ejemplo2.txt");
		try {
			FileWriter fic = new FileWriter(fichero);
			String[] provincias = { "Leon", "Avila", "Salamanca", "Burgos", "Segovia", "Soria", "Valladolid", "Zamora",
					"Palencia" };
			String[] provinciasModificado = new String[provincias.length];

			// RECORRER CADA PROVINCIA DEL ARRAY
			for (int i = 0; i < provincias.length; i++) {
				fic.write(provincias[i] + " ");
				char[] letrasAComprobar = provincias[i].toCharArray();
				String palabraModificada = "";

				// RECORRER CADA LETRA DE LA PROVINCIA ACTUAL
				for (int j = 0; j < provincias[i].length(); j++) {
					// SELECCIONAR LA LETRA A COMPROBAR
					char letraAComprobar = letrasAComprobar[j];
					// COMPROBAR LA LETRA SELECCIONADA
					switch (letraAComprobar) {
					case 'a':
						palabraModificada += "e";
						break;
					case 'e':
						palabraModificada += "i";
						break;
					case 'i':
						palabraModificada += "o";
						break;
					case 'o':
						palabraModificada += "u";
						break;
					case 'u':
						palabraModificada += "a";
						break;
					case 'A':
						palabraModificada += "E";
						break;
					case 'E':
						palabraModificada += "I";
						break;
					case 'I':
						palabraModificada += "O";
						break;
					case 'O':
						palabraModificada += "U";
						break;
					case 'U':
						palabraModificada += "A";
						break;
					default:
						palabraModificada += letraAComprobar;
						break;
					}
				}
				// AÑADIR LA PALABRA MODIFICADA A UN ARRAY
				provinciasModificado[i] = palabraModificada;
			}

			// AÑADIR SALTOS DE LÍNEA PARA UNA MEJOR VISUALIZACIÓN DEL RESULTADO
			fic.write("\n\n");

			// ESCRIBIR TODAS LAS PROVINCIAS MODIFICADAS
			for (String provincia : provinciasModificado) {
				fic.write(provincia + " ");
			}

			fic.close();
		} catch (Exception e) {
			System.out.println("Algo no ha ido bien.");
		}

	}
}
