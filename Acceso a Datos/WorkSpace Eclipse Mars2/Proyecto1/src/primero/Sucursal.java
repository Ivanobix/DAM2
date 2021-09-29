package primero;
// Generated 27-ene-2021 8:50:37 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Sucursal generated by hbm2java
 */
public class Sucursal implements java.io.Serializable {

	private String codSucursal;
	private String director;
	private int numTrabajadores;
	private String direccion;
	private String telefono;
	private Set<Vuelo> vuelos = new HashSet<Vuelo>(0);

	public Sucursal() {
	}

	public Sucursal(String codSucursal, String director, int numTrabajadores, String direccion, String telefono) {
		this.codSucursal = codSucursal;
		this.director = director;
		this.numTrabajadores = numTrabajadores;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Sucursal(String codSucursal, String director, int numTrabajadores, String direccion, String telefono,
			Set<Vuelo> vuelos) {
		this.codSucursal = codSucursal;
		this.director = director;
		this.numTrabajadores = numTrabajadores;
		this.direccion = direccion;
		this.telefono = telefono;
		this.vuelos = vuelos;
	}

	public String getCodSucursal() {
		return this.codSucursal;
	}

	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getNumTrabajadores() {
		return this.numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<Vuelo> getVuelos() {
		return this.vuelos;
	}

	public void setVuelos(Set<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

}
