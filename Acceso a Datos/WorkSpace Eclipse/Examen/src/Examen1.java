import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Examen1 {

	private final String rutaExamen = "C:/Examen/Examen.txt";
	private final String rutaCopia = "C:/Examen/Copia.txt";
	private Scanner sc;

	public static void main(String[] args) {
		new Examen1();

	}

	public Examen1() {
		mostrarMenu();
	}

	private void mostrarMenu() {
		System.out.println("1. PARES");
		System.out.println("2. COPIA");
		System.out.println("3. FIRMA");
		System.out.println("4. MOSTRAR");
		System.out.println("5. SALIR");

		sc = new Scanner(System.in);
		String opcion = sc.nextLine();
		String parametros[] = opcion.split(" ");

		switch (parametros[0]) {
		case "1":
			if (parametros.length == 3)
				pares(parametros[1], parametros[2]);
			else {
				System.out.println("Faltan datos.");
				mostrarMenu();
			}
			break;
		case "2":
			copia();
			break;
		case "3":
			firma();
			break;
		case "4":
			mostrar();
			break;
		case "5":
			salir();
			break;
		default:
			System.out.println("Indica una opción válida.");
			mostrarMenu();
			break;
		}

	}

	private void pares(String cantidadNumeros, String numeroBase) {
		if (Integer.parseInt(numeroBase) > 100) {
			System.out.println("El número base debe ser menor de 100.");
		} else {
			try {
				String aEscribir = "";
				int paresIntroducidos = 0;
				int ultimoNumero = Integer.parseInt(numeroBase);

				if (Integer.parseInt(numeroBase) % 2 == 0) {
					aEscribir = numeroBase + ", ";
					paresIntroducidos++;
				} else {
					aEscribir = ultimoNumero + 1 + ", ";
					ultimoNumero++;
				}

				while (paresIntroducidos < Integer.parseInt(cantidadNumeros) - 1) {
					aEscribir += ultimoNumero + 2 + ", ";
					ultimoNumero += 2;
					paresIntroducidos++;
				}
				aEscribir = aEscribir.substring(0, aEscribir.length() - 2);

				File fichero = new File(rutaExamen);
				if (!fichero.exists())
					fichero.createNewFile();

				FileWriter fw = new FileWriter(rutaExamen, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(aEscribir + "\n");
				bw.close();
				fw.close();

			} catch (NumberFormatException pe) {
				System.out.println("Los datos introducidos no son correctos.");
			} catch (IOException io) {
				System.out.println("Error de E/S.");
			} catch (Exception e) {
				System.out.println("Error no controlado.");
			}
		}

		mostrarMenu();
	}

	private void copia() {
		try {
			File fichero = new File(rutaExamen);
			File fichero2 = new File(rutaCopia);

			if (fichero.exists() && fichero2.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(rutaExamen));
				String texto = br.readLine();
				String textoAEscribir = "";
				if (texto != null)
					textoAEscribir = texto + "\n";
				while (texto != null) {
					texto = br.readLine();
					if (texto != null)
						textoAEscribir += texto + "\n";
				}

				br.close();

				BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCopia));

				bw.write(textoAEscribir);
				bw.flush();
				bw.close();
			} else {
				if (!fichero.exists()) {
					fichero.createNewFile();
				}
				if (!fichero2.exists()) {
					fichero2.createNewFile();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de E/S.");
		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}

		mostrarMenu();

	}

	private void firma() {
		try {
			FileWriter fw = new FileWriter(rutaExamen, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("\nIván García Prieto");
			bw.flush();
			bw.close();

		} catch (IOException io) {
			System.out.println("Error de E/S.");
		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}
		mostrarMenu();

	}

	private void mostrar() {
		try {
			File fichero = new File(rutaExamen);
			if (!fichero.exists()) {
				System.out.println("Examen.txt no existe");
			} else {
				String textoExamen = "";
				BufferedReader br = new BufferedReader(new FileReader(rutaExamen));
				String texto = br.readLine();
				while (texto != null) {
					textoExamen += texto + "\n";
					texto = br.readLine();
				}
				br.close();

				if (textoExamen.isEmpty())
					System.out.println("Examen.txt es vacío.");
				else
					System.out.println(textoExamen);
			}

			System.out.println("*************************");

			File fichero2 = new File(rutaCopia);
			if (!fichero2.exists()) {
				System.out.println("Copia.txt no existe");
			} else {
				String textoCopia = "";
				BufferedReader br = new BufferedReader(new FileReader(rutaCopia));
				String texto = br.readLine();
				while (texto != null) {
					textoCopia += texto + "\n";
					texto = br.readLine();
				}
				br.close();
				if (textoCopia.isEmpty())
					System.out.println("Copia.txt es vacío.");
				else
					System.out.println(textoCopia);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de E/S.");
		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}

		mostrarMenu();

	}

	private void salir() {
		System.out.println("¿Desea conservar Examen.txt? (y/n)");
		String opcion = sc.nextLine().toLowerCase();
		if (opcion.equals("n")) {
			File fichero1 = new File(rutaExamen);
			fichero1.delete();
		}

		System.out.println("¿Desea conservar Copia.txt? (y/n)");
		opcion = sc.nextLine().toLowerCase();
		if (opcion.equals("n")) {
			File fichero2 = new File(rutaCopia);
			fichero2.delete();
		}

		System.exit(0);
	}
}
