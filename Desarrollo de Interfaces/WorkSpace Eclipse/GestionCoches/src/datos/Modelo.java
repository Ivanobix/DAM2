package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class Modelo {

	private HashSet<Coche> coches;

	public Modelo() {
		coches = new HashSet<>();
	}

	public HashSet<Coche> getCoches() {
		return coches;
	}

	public void introducirCoche(String matricula, String modelo, double kilometros, LocalDate anoFabricacion) {
		Coche coche = new Coche(matricula, modelo, kilometros, anoFabricacion);
		coches.add(coche);
	}

	public void eliminarCoche(Coche coche) {
		Iterator<Coche> it = coches.iterator();
		while (it.hasNext()) {
			if (it.next().equals(coche)) {
				it.remove();
			}
		}
	}

	public void guardarDatos() throws IOException {
		FileOutputStream flujoSalida = new FileOutputStream("datos.bin");
		ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
		serializador.writeObject(coches);
		serializador.close();
	}

	@SuppressWarnings("unchecked")
	public void cargarDatos() throws ClassNotFoundException, IOException {
		FileInputStream flujoEntrada = new FileInputStream("datos.bin");
		ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
		coches = (HashSet<Coche>) deserializador.readObject();
		deserializador.close();
	}
}
