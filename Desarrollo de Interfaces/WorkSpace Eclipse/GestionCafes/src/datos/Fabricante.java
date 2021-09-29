package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Fabricante. Clase dedicada a la construcci�n de objetos tipo "Fabricante"
 * para su posterior almacenamiento o tratamiento por diferentes clases.
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 */
public class Fabricante implements Serializable {

	private static final long serialVersionUID = 1716150257855476427L;
	private String identificador;
	private String nombre;
	private String direccion;
	private int trabajadores;
	private LocalDate fechaCreacion;
	private boolean internacional;

	/**
	 * Constructor.
	 * 
	 * @param nombre        Nombre del fabricante.
	 * @param direccion     Direccion del fabricante.
	 * @param trabajadores  Trabajadores de la empresa.
	 * @param fechaCreacion Fecha de creaci�n de la empresa.
	 * @param internacional �mbito del fabricante.
	 */
	public Fabricante(String nombre, String direccion, int trabajadores, LocalDate fechaCreacion,
			boolean internacional) {
		super();
		identificador = UUID.randomUUID().toString().substring(0, 10);
		this.nombre = nombre;
		this.setDireccion(direccion);
		this.trabajadores = trabajadores;
		this.fechaCreacion = fechaCreacion;
		this.internacional = internacional;
	}

	/**
	 * Devuelve el identificador del fabricante.
	 * 
	 * @return Identificador del fabricante.
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Establece el identificador del fabricante.
	 * 
	 * @param identificador Identificador del fabricante.
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Devuelve el nombre del fabricante.
	 * 
	 * @return Nombre del fabricante.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del fabricante.
	 * 
	 * @param nombre Nombre del fabricante.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la direcci�n del fabricante.
	 * 
	 * @return Direcci�n del fabricante.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la direcci�n del fabricante.
	 * 
	 * @param direccion Direcci�n del fabricante.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el n�mero de trabajadores del fabricante.
	 * 
	 * @return Trabajadores de la empresa.
	 */
	public int getTrabajadores() {
		return trabajadores;
	}

	/**
	 * Establece el n�mero de trabajadores del fabricante.
	 * 
	 * @param trabajadores Trabajadores de la empresa.
	 */
	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}

	/**
	 * Devuelve la fecha de creaci�n de la empresa.
	 * 
	 * @return Fecha de creaci�n de la empresa.
	 */
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Establece la fecha de creaci�n de la empresa.
	 * 
	 * @param fechaCreacion Fecha de creaci�n de la empresa.
	 */
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Devuelve el �mbito del fabricante.
	 * 
	 * @return �mbito del fabricante.
	 */
	public boolean isInternacional() {
		return internacional;
	}

	/**
	 * Establece el �mbito del fabricante.
	 * 
	 * @param internacional �mbito del fabricante.
	 */
	public void setInternacional(boolean internacional) {
		this.internacional = internacional;
	}

	/**
	 * Devuelve una cadena con la informaci�n b�sica de un fabricante.
	 */
	@Override
	public String toString() {
		return "Identificador: " + identificador + "   //   Nombre: " + nombre;
	}

}
