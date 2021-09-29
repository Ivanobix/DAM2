package datos;

import java.io.Serializable;
import java.time.LocalDate;

public class Coche implements Serializable {

	private static final long serialVersionUID = -7063378323156216414L;
	private String matricula;
	private String modelo;
	private double kilometros;
	private LocalDate fabricacion;

	public Coche(String matricula, String modelo, double kilometros, LocalDate fabricacion) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.kilometros = kilometros;
		this.fabricacion = fabricacion;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public double getKilometros() {
		return kilometros;
	}

	public LocalDate getFabricacion() {
		return fabricacion;
	}

	@Override
	public String toString() {
		String aDevolver = "Matrícula: " + matricula + " //  Modelo: " + modelo + " // Fecha de Fabricación: "
				+ fabricacion + ".";
		return aDevolver;
	}

}
