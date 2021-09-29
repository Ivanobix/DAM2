package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Caf�. Clase dedicada a la construcci�n de objetos tipo "Caf�" para su
 * posterior almacenamiento o tratamiento por diferentes clases.
 * 
 * @author Iv�n Garc�a Prieto
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
	 * @param nombre            Nombre del caf�.
	 * @param fechaEnvasado     Fecha de envasado del caf�.
	 * @param porcentajeArabico Porcentaje de caf� ar�bico en la mezcla.
	 * @param porcentajeRobusta Porcentaje de caf� robusta en la mezcla.
	 * @param fabricante        Fabricante del caf�.
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
	 * Devuelve el identificador del caf�.
	 * 
	 * @return Identificador del caf�.
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Establece el identificador del caf�.
	 * 
	 * @param identificador Identificador del caf�.
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Devuelve el nombre del caf�.
	 * 
	 * @return Nombre del caf�.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del caf�.
	 * 
	 * @param nombre Nombre del caf�.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la fecha de envasado del caf�.
	 * 
	 * @return Fecha de envasado del caf�.
	 */
	public LocalDate getFechaEnvasado() {
		return fechaEnvasado;
	}

	/**
	 * Establece la fecha de envasado del caf�.
	 * 
	 * @param fechaEnvasado Fecha de envasado del caf�.
	 */
	public void setFechaEnvasado(LocalDate fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	/**
	 * Devuelve el porcentaje de caf� arabico en la mezcla.
	 * 
	 * @return Porcentaje de caf� tipo arabico en la mezcla.
	 */
	public double getPorcentajeArabico() {
		return porcentajeArabico;
	}

	/**
	 * Establece el porcentaje de caf� arabico en la mezcla.
	 * 
	 * @param porcentajeArabico Porcentaje de caf� tipo arabico en la mezcla.
	 */
	public void setPorcentajeArabico(double porcentajeArabico) {
		this.porcentajeArabico = porcentajeArabico;
	}

	/**
	 * Devuelve el porcentaje de caf� robusta en la mezcla.
	 * 
	 * @return Porcentaje de caf� tipo robusta en la mezcla.
	 */
	public double getPorcentajeRobusta() {
		return porcentajeRobusta;
	}

	/**
	 * Establece el porcentaje de caf� robusta en la mezcla.
	 * 
	 * @param porcentajeRobusta Porcentaje de caf� tipo robusta en la mezcla.
	 */
	public void setPorcentajeRobusta(double porcentajeRobusta) {
		this.porcentajeRobusta = porcentajeRobusta;
	}

	/**
	 * Devuelve el fabricante del caf�.
	 * 
	 * @return Fabricante del caf�.
	 */
	public Fabricante getFabricante() {
		return fabricante;
	}

	/**
	 * Establece el fabricante del caf�.
	 * 
	 * @param fabricante Fabricante del caf�.
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * Devuelve una cadena con la informaci�n b�sica de un caf�.
	 */
	@Override
	public String toString() {
		return "Identificador: " + identificador + "   //   Nombre: " + nombre;
	}

}
