package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

import base.Coche;
import base.Conductor;

/** Clase que almacena los datos que registra la aplicacion
 * Como nuestra aplicacion se centra en el uso y diseño de la gui,
 * esta clase carece de demasiada funcionalidad
 * 
 * @author Fer
 *
 */
public class Modelo {

	private HashSet<Coche> coches;
	private LinkedList<Conductor> conductores;
	
	public Modelo() {
		coches = new HashSet<Coche>();
		conductores = new LinkedList<Conductor>();
	}
	
	public HashSet<Coche> getCoches(){
		return coches;
	}
	
	public LinkedList<Conductor> getConductores(){
		return conductores;
	}
	
	public void altaCoche(String matricula, String modelo, double kms, LocalDate fechaFabricacion, Conductor conductor){
		coches.add(new Coche(matricula, modelo, kms, fechaFabricacion, conductor));
	}
	
	public void eliminarCoche(Coche eliminado) {
		coches.remove(eliminado);
	}
	
	public void altaConductor(String dni, String nombre, String apellidos, boolean novel, int annosExperiencia) {
		Conductor conductor = new Conductor(dni, nombre, apellidos, novel, annosExperiencia);	
		conductores.add(conductor);
	}
	
	public void eliminarConductor(Conductor conductor) {
		conductores.remove(conductor);
	}

	public void guardarDatos(File fichero) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(fichero);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(coches);
		oos.writeObject(conductores);
		
		oos.close();
	}
	
	@SuppressWarnings({ "unchecked"})
	public void cargarDatos(File fichero) throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fichero);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		coches = (HashSet<Coche>) ois.readObject();
		conductores = (LinkedList<Conductor>) ois.readObject();
		
		ois.close();
	}
	
}
