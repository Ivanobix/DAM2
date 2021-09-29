package base;

public class Sucursal {

	private String codSucursal;
	private String director;
	private String direccion;
	private String telefono;
	private int numTrabajadores;

	public Sucursal(String codSucursal, String director, int numTrabajadores, String direccion, String telefono) {
		this.codSucursal = codSucursal;
		this.director = director;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numTrabajadores = numTrabajadores;
	}

	public String getCodSucursal() {
		return codSucursal;
	}

	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getNumTrabajadores() {
		return numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	@Override
	public String toString() {
		return "Sucursal [codSucursal=" + codSucursal + ", director=" + director + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", numTrabajadores=" + numTrabajadores + "]";
	}

}
