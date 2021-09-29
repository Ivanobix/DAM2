package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Café. Clase dedicada a la construcción de objetos tipo "Café" para su
 * posterior almacenamiento o tratamiento por diferentes clases.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 */
public class Cafe implements Serializable {

	private static final long serialVersionUID = -7840825314022352487L;
	private String identificador;
	private String nombre;
	private Fabricante fabricante;
	private double porcentajeArabico;
	private double porcentajeRobusta;
	private LocalDate fechaEnvasado;

	/**
	 * Constructor.
	 * 
	 * @param nombre            Nombre del café.
	 * @param fechaEnvasado     Fecha de envasado del café.
	 * @param porcentajeArabico Porcentaje de café arábico en la mezcla.
	 * @param porcentajeRobusta Porcentaje de café robusta en la mezcla.
	 * @param fabricante        Fabricante del café.
	 */
	public Cafe(String nombre, LocalDate fechaEnvasado, double porcentajeArabico, double porcentajeRobusta,
			Fabricante fabricante) {
		super();
		identificador = UUID.randomUUID().toString().substring(0, 10);
		this.nombre = nombre;
		this.fechaEnvasado = fechaEnvasado;
		this.porcentajeArabico = porcentajeArabico;
		this.porcentajeRobusta = porcentajeRobusta;
		this.fabricante = fabricante;
	}

	/**
	 * Devuelve el identificador del café.
	 * 
	 * @return Identificador del café.
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Establece el identificador del café.
	 * 
	 * @param identificador Identificador del café.
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Devuelve el nombre del café.
	 * 
	 * @return Nombre del café.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del café.
	 * 
	 * @param nombre Nombre del café.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la fecha de envasado del café.
	 * 
	 * @return Fecha de envasado del café.
	 */
	public LocalDate getFechaEnvasado() {
		return fechaEnvasado;
	}

	/**
	 * Establece la fecha de envasado del café.
	 * 
	 * @param fechaEnvasado Fecha de envasado del café.
	 */
	public void setFechaEnvasado(LocalDate fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	/**
	 * Devuelve el porcentaje de café arabico en la mezcla.
	 * 
	 * @return Porcentaje de café tipo arabico en la mezcla.
	 */
	public double getPorcentajeArabico() {
		return porcentajeArabico;
	}

	/**
	 * Establece el porcentaje de café arabico en la mezcla.
	 * 
	 * @param porcentajeArabico Porcentaje de café tipo arabico en la mezcla.
	 */
	public void setPorcentajeArabico(double porcentajeArabico) {
		this.porcentajeArabico = porcentajeArabico;
	}

	/**
	 * Devuelve el porcentaje de café robusta en la mezcla.
	 * 
	 * @return Porcentaje de café tipo robusta en la mezcla.
	 */
	public double getPorcentajeRobusta() {
		return porcentajeRobusta;
	}

	/**
	 * Establece el porcentaje de café robusta en la mezcla.
	 * 
	 * @param porcentajeRobusta Porcentaje de café tipo robusta en la mezcla.
	 */
	public void setPorcentajeRobusta(double porcentajeRobusta) {
		this.porcentajeRobusta = porcentajeRobusta;
	}

	/**
	 * Devuelve el fabricante del café.
	 * 
	 * @return Fabricante del café.
	 */
	public Fabricante getFabricante() {
		return fabricante;
	}

	/**
	 * Establece el fabricante del café.
	 * 
	 * @param fabricante Fabricante del café.
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * Devuelve una cadena con la información básica de un café.
	 */
	@Override
	public String toString() {
		return "Identificador: " + identificador + "   //   Nombre: " + nombre;
	}

}
