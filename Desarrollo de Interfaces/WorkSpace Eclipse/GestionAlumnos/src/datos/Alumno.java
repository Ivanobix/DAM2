package datos;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumno implements Serializable{

	private static final long serialVersionUID = -8437585587048563607L;
	private String dni;
	private String nombre;
	private LocalDate fechaNacimiento;

	public Alumno(String dni, String nombre, LocalDate fechaNacimiento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return dni + "     -     " + nombre;
	}

}
