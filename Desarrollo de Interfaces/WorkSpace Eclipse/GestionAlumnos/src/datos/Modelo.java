package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Modelo {
	
	private ArrayList<Alumno> alumnos;
	
	public Modelo() {
		alumnos = new ArrayList<>();
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void introducirAlumno(String dni, String nombre, LocalDate fecha) {
		Alumno alumno = new Alumno(dni, nombre, fecha);
		alumnos.add(alumno);
	}

	public void eliminarAlumno(String dni) {
		Iterator<Alumno> it = alumnos.iterator();	
		while (it.hasNext()) {
			if (it.next().getDni().equals(dni)) {
				it.remove();
			}
		}
	}
	
	public void guardarDatos() throws IOException {
		FileOutputStream flujoSalida = new FileOutputStream("datos.bin");
		ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
		serializador.writeObject(alumnos);
		serializador.close();
	}
	
	@SuppressWarnings("unchecked")
	public void cargarDatos() throws ClassNotFoundException, IOException {
		FileInputStream flujoEntrada = new FileInputStream("datos.bin");
		ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
		alumnos = (ArrayList<Alumno>) deserializador.readObject();
		deserializador.close();
	}
}
