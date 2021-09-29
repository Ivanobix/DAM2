import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Separar {

	public static void main(String[] args) {
		String texto = "";
		String cuentas = "";

		// Lectura de todos los datos y almecenamiento por separado
		try {
			File fichero = new File("articulo.txt");
			FileReader fic = new FileReader(fichero);
			int i;
			while ((i = fic.read()) != -1) {
				if (esOperador((char) i)) {
					cuentas += " " + (char) i + " ";

				} else if (esNumero((char) i)) {
					cuentas += (char) i;
				} else {
					texto += (char) i;
				}

			}
			fic.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la lectura.");
		}

		// Formato del texto y escritura de los archivos
		try {
			// Texto
			File fichero = new File("texto.txt");
			FileWriter fic = new FileWriter(fichero);
			fic.write(texto.replaceAll("\\s+", " "));
			fic.close();

			// Cuentas
			fichero = new File("cuentas.txt");
			fic = new FileWriter(fichero);
			fic.write(cuentas.replaceAll("\\s+", " "));
			fic.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la escritura");
		}

		// Separación de los numeros y los operadores
		ArrayList<String> numeros = new ArrayList<>();
		ArrayList<String> operadores = new ArrayList<>();
		char operacionTotal[] = cuentas.replaceAll(" ", "").toCharArray();
		for (int i = 0; i < operacionTotal.length; i++) {
			if (esNumero(operacionTotal[i])) {
				String numero = "";
				while (i < operacionTotal.length && esNumero(operacionTotal[i])) {
					numero += String.valueOf(operacionTotal[i]);
					i++;
				}
				i--;
				numeros.add(numero);
			} else {
				operadores.add(String.valueOf(operacionTotal[i]));
			}
		}

		// Realización de las operaciones
		float aDevolver = Integer.parseInt(numeros.get(0));
		for (int j = 0; j < operadores.size(); j++) {
			switch (operadores.get(j)) {
			case "+":
				aDevolver += Integer.parseInt(numeros.get(j + 1));
				break;
			case "-":
				aDevolver -= Integer.parseInt(numeros.get(j + 1));
				break;
			case "*":
				aDevolver *= Integer.parseInt(numeros.get(j + 1));
				break;
			case "/":
				aDevolver /= Integer.parseInt(numeros.get(j + 1));
				break;
			}
		}

		System.out.println("El resultado es: " + aDevolver);

	}

	/**
	 * 
	 * @param caracter Caracter a comprobar
	 * @return Devuelve si un carácter es o no un operador
	 */
	public static boolean esOperador(char caracter) {
		boolean aDevolver = false;
		switch (caracter) {
		case '+':
			aDevolver = true;
			break;
		case '-':
			aDevolver = true;
			break;
		case '*':
			aDevolver = true;
			break;
		case '/':
			aDevolver = true;
			break;
		}
		return aDevolver;
	}

	/**
	 * 
	 * @param caracter Caracter a comprobar
	 * @return Devuelve si un caracter es o no numérico
	 */
	public static boolean esNumero(char caracter) {
		boolean aDevolver = true;
		try {
			Integer.parseInt(String.valueOf(caracter));
		} catch (Exception e) {
			aDevolver = false;
		}
		return aDevolver;
	}

}
