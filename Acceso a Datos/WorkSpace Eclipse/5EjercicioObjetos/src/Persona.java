import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = -5046577359557334793L;
	String nombre;
	int edad;
	int telefono;

	public Persona(String nombre, int edad, int telefono) {
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nEdad: " + edad + "\nTelefono: " + telefono;
	}

}
