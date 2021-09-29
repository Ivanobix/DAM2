package base;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un Coche. Implementa Serializable
 * para que sus instancias puedan ser guardadas en un fichero
 * binario
 * 
 * @author Fer
 *
 */
public class Coche implements Serializable{
	

	private static final long serialVersionUID = -4543580538445584293L;
	private String matricula;
	private String modelo;
	private double kms;
	private LocalDate fechaFabricacion;
	private Conductor conductor;
	
	public Coche(String matricula, String modelo, double kms, LocalDate fechaFabricacion, Conductor conductor) {
		super();
		this.matricula = matricula;
		this.modelo = modelo;
		this.kms = kms;
		this.fechaFabricacion = fechaFabricacion;
		this.conductor = conductor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}

	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(LocalDate fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	@Override
	public String toString() {
		return matricula + " " + modelo;
	}
	
	
}
