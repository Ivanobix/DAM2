
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class OrdenarEdad {

	private static String nombres[] = { "Ana", "Luis Miguel", "Alicia", "Bea", "Manuel", "Alfonso", "Julio", "Ana",
			"Marta" };
	private static int edades[] = { 17, 15, 13, 19, 16, 12, 16, 14, 18 };

	public static void main(String[] args) {

		// Almacenamiento temporal de datos para tratarlos con más facilidad
		ArrayList<String> nombreEdadesMenores = new ArrayList<>();
		ArrayList<String> nombreEdadesMayores = new ArrayList<>();

		// Separación de menores y mayores de edad en su respectivo ArrayList y
		// concatenación de edad y nombre.
		for (int i = 0; i < edades.length; i++) {
			if (edades[i] < 18) {
				nombreEdadesMenores.add(edades[i] + " " + nombres[i]);
			} else {
				nombreEdadesMayores.add(edades[i] + " " + nombres[i]);
			}
		}

		// Ordenación en función de su edad y nombre
		Collator metodoOrdenado = Collator.getInstance(new Locale("es"));
		metodoOrdenado.setStrength(Collator.PRIMARY);
		nombreEdadesMenores.sort(metodoOrdenado);
		nombreEdadesMayores.sort(metodoOrdenado);

		// Formateo de los valores del ArrayList de mayores
		int contadorMayores = 0;
		for (String persona : nombreEdadesMayores) {
			// Guardar únicamente el nombre sin edad
			String nombre = persona.replaceAll("[^A-Za-z ]", "").trim();
			// Guardar únicamente la edad sin el nombre
			int edad = Integer.parseInt(persona.replaceAll("[^0-9]", ""));
			// Concatenación de nombre y edad
			nombreEdadesMayores.set(contadorMayores, nombre + " " + edad);
			contadorMayores++;
		}

		// Formateo de los valores del ArrayList de mayores
		int contadorMenores = 0;
		for (String persona : nombreEdadesMenores) {
			// Guardar únicamente el nombre sin edad
			String nombre = persona.replaceAll("[^A-Za-z ]", "").trim();
			// Guardar únicamente la edad sin el nombre
			int edad = Integer.parseInt(persona.replaceAll("[^0-9]", ""));
			// Concatenación de nombre y edad
			nombreEdadesMenores.set(contadorMenores, nombre + " " + edad);
			contadorMenores++;
		}

		// Formateo final de la información que se introduce en los ficheros
		String resultadoMenores = nombreEdadesMenores.toString().replace("[", "").replace("]", "");
		String resultadoMayores = nombreEdadesMayores.toString().replace("[", "").replace("]", "");

		// Devolución del resultado por consola
		System.out.println("Menores: " + resultadoMenores + ".");
		System.out.println("Mayores: " + resultadoMayores + ".");

		try {
			// Creación de los ficheros
			File menores = new File("menores.dat");
			File mayores = new File("mayores.dat");

			// Creación de los flujos de salida
			FileOutputStream menoresout = new FileOutputStream(menores);
			FileOutputStream mayoresout = new FileOutputStream(mayores);

			// Escritura de los datos en los ficheros
			menoresout.write(resultadoMenores.getBytes());
			mayoresout.write(resultadoMayores.getBytes());

			// Liberación de los flujos de salida
			menoresout.close();
			mayoresout.close();

		} catch (IOException e) {
			System.out.println("Algo no ha ido bien");
		}

	}
}
