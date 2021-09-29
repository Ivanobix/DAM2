package base;

import java.io.Serializable;

/**
 * Clase que representa un Conductor. Implementa Serializable
 * para que sus instancias puedan ser guardadas en un fichero
 * binario
 * 
 * @author Fer
 *
 */
public class Conductor implements Serializable{

	private static final long serialVersionUID = -1970451451756350527L;
	private String dni;
	private String nombre;
	private String apellidos;
	private boolean novel;
	private int annosExperiencia;
	
	public Conductor(String dni, String nombre, String apellidos, boolean novel, int annosExperiencia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.novel = novel;
		this.annosExperiencia = annosExperiencia;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean isNovel() {
		return novel;
	}

	public void setNovel(boolean novel) {
		this.novel = novel;
	}

	public int getAnnosExperiencia() {
		return annosExperiencia;
	}

	public void setAnnosExperiencia(int annosExperiencia) {
		this.annosExperiencia = annosExperiencia;
	}

	@Override
	public String toString() {
		return dni + " - " + nombre;
	}
	
	
	
}
