import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copiar20 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("El valor introducido no es válido.");
		} else {
			try {
				BufferedReader contadorLineas = new BufferedReader(new FileReader("Mifichero.txt"));
				// Contador de líneas totales para conocer la longitud del archivo
				int numeroLineas = 0;
				while ((contadorLineas.readLine()) != null) {
					numeroLineas++;
				}
				contadorLineas.close();

				BufferedReader fichero = new BufferedReader(new FileReader("Mifichero.txt"));
				BufferedWriter ficheroFormateado = new BufferedWriter(new FileWriter(args[0]));
				String linea;
				int contador = 0;

				// Tratamiento del texto
				while ((linea = fichero.readLine()) != null) {
					String aEscribir = linea;
					// Condición 1 de 3 (Cambiar "b" por "v" y "c" por "z")
					if (contador < 5) {
						aEscribir = linea.replaceAll("b", "v").replaceAll("c", "z");
						aEscribir = aEscribir.replaceAll("B", "V").replaceAll("C", "Z");
					}
					// Condición 2 de 3 (Añadir "%" al final de cada línea)
					else if (contador < 10) {
						aEscribir = linea + "%";
					}
					// Condición 3 de 3 (Añadir "$" cada 5 caracteres sin contar espacios)
					else if (contador >= (numeroLineas - 10)) {
						char[] letrasLinea = linea.toCharArray();
						String lineaModificada = "";
						int contadorLetras = 0;
						for (char caracter : letrasLinea) {
							lineaModificada += caracter;
							if (caracter != ' ') {
								if (contadorLetras == 5) {
									lineaModificada += '$';
									contadorLetras = 0;
								}
								contadorLetras++;
							}
						}
						aEscribir = lineaModificada;
					}
					ficheroFormateado.write(aEscribir);
					ficheroFormateado.newLine();
					contador++;
				}
				fichero.close();
				ficheroFormateado.close();
			} catch (FileNotFoundException e) {
				System.out.println("No se encuentra el archivo.");
			} catch (IOException e) {
				System.out.println("Error de E/S");
			}
		}
	}

}
