import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class OrdenarNombre {
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
				nombreEdadesMenores.add(nombres[i] + " " + edades[i]);
			} else {
				nombreEdadesMayores.add(nombres[i] + " " + edades[i]);
			}
		}

		// Ordenación en función de su edad y nombre
		Collator metodoOrdenado = Collator.getInstance(new Locale("es"));
		metodoOrdenado.setStrength(Collator.PRIMARY);
		nombreEdadesMenores.sort(metodoOrdenado);
		nombreEdadesMayores.sort(metodoOrdenado);

		// Formateo de la información que se introduce en los ficheros
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
