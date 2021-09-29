package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Modelo. Clase encargada del almacenamiento y tratamiento de los datos (Caf�s
 * y Fabricantes), as� como el control de cambios realizados.
 * 
 * @author Iv�n Garc�a Prieto
 * @version 13.11.2020
 *
 */
public class Modelo {

	private ArrayList<Cafe> cafes;
	private ArrayList<Fabricante> fabricantes;
	private boolean cambios;

	/**
	 * Constructor.
	 */
	public Modelo() {
		cafes = new ArrayList<>();
		fabricantes = new ArrayList<>();
		setCambios(false);
	}

	/**
	 * Devuelve la lista de caf�s.
	 * 
	 * @return Lista de caf�s.
	 */
	public ArrayList<Cafe> getCafes() {
		return cafes;
	}

	/**
	 * Devuelve la lista de fabricantes.
	 * 
	 * @return Lista de fabricantes.
	 */
	public ArrayList<Fabricante> getFabricantes() {
		return fabricantes;
	}

	/**
	 * Devuelve la existencia de cambios.
	 * 
	 * @return Existencia o no de cambios en los datos de la aplicaci�n.
	 */
	public boolean getCambios() {
		return cambios;
	}

	/**
	 * Establece la existencia de cambios.
	 * 
	 * @param cambios Existencia o no de cambios en los datos de la aplicaci�n.
	 */
	public void setCambios(boolean cambios) {
		this.cambios = cambios;
	}

	/**
	 * Insercci�n de un caf� en la lista.
	 * 
	 * @param cafe Caf� a a�adir en la lista.
	 */
	public void anadirCafe(Cafe cafe) {
		cafes.add(cafe);
		cambios = true;
	}

	/**
	 * Eliminaci�n de un caf� determinado de la lista.
	 * 
	 * @param cafe Caf� a eliminar de la lista.
	 */
	public void eliminarCafe(Cafe cafe) {
		cafes.remove(cafe);
		cambios = true;
	}

	/**
	 * Modificaci�n de los atributos de un caf� determinado.
	 * 
	 * @param cafe              Caf� a modificar.
	 * @param nombre            Nuevo nombre del caf�.
	 * @param fechaEnvasado     Nueva fecha de envasado del caf�.
	 * @param porcentajeArabico Nuevo porcentaje de caf� arabico en la mezcla del
	 *                          caf�.
	 * @param porcentajeRobusta Nuevo porcentaje de caf� robusta en la mezcla del
	 *                          caf�.
	 * @param fabricante        Nuevo fabricante del caf�.
	 */
	public void modificarCafe(Cafe cafe, String nombre, LocalDate fechaEnvasado, double porcentajeArabico,
			double porcentajeRobusta, Fabricante fabricante) {
		cafe.setNombre(nombre);
		cafe.setFechaEnvasado(fechaEnvasado);
		cafe.setPorcentajeArabico(porcentajeArabico);
		cafe.setPorcentajeRobusta(porcentajeRobusta);
		cafe.setFabricante(fabricante);
		cambios = true;
	}

	/**
	 * Insercci�n de un fabricante en la lista.
	 * 
	 * @param fabricante Fabricante a a�adir en la lista.
	 */
	public void anadirFabricante(Fabricante fabricante) {
		fabricantes.add(fabricante);
		cambios = true;
	}

	/**
	 * Eliminaci�n de un fabricante de la lista y todos los caf�s producidos por
	 * este.
	 * 
	 * @param fabricante Fabricante a eliminar de la lista.
	 */
	public void eliminarFabricante(Fabricante fabricante) {
		fabricantes.remove(fabricante);
		Iterator<Cafe> it = cafes.iterator();
		while (it.hasNext()) {
			if (it.next().getFabricante().equals(fabricante)) {
				it.remove();
			}
		}
		cambios = true;
	}

	/**
	 * Modificaci�n de los atributos de un fabricante determinado.
	 * 
	 * @param fabricante    Fabricante a modificar.
	 * @param nombre        Nuevo nombre del fabricante.
	 * @param direccion     Nueva direcci�n del fabricante.
	 * @param trabajadores  Nuevo cantidad de trabajadores del fabricante.
	 * @param fechaCreacion Nueva fecha de creaci�n de la empresa.
	 * @param internacional Nuevo �mbito del fabricante.
	 */
	public void modificarFabricante(Fabricante fabricante, String nombre, String direccion, int trabajadores,
			LocalDate fechaCreacion, boolean internacional) {
		fabricante.setNombre(nombre);
		fabricante.setDireccion(direccion);
		fabricante.setTrabajadores(trabajadores);
		fabricante.setFechaCreacion(fechaCreacion);
		fabricante.setInternacional(internacional);
		cambios = true;
	}

	/**
	 * Eliminaci�n de todos los datos almacenados en la aplicaci�n.
	 */
	public void reiniciarDatos() {
		cafes.clear();
		fabricantes.clear();
		cambios = true;
	}

	/**
	 * Guardado de todos los datos almacenados de la aplicaci�n en un fichero
	 * externo.
	 * 
	 * @param fichero Ubicaci�n del archivo de guardado.
	 * @throws IOException Error de E/S.
	 */
	public void guardarDatos(File fichero) throws IOException {
		FileOutputStream flujoSalida = new FileOutputStream(fichero);
		ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
		serializador.writeObject(cafes);
		serializador.writeObject(fabricantes);
		serializador.close();
		cambios = false;
	}

	/**
	 * Carga de todos los datos almacenados en un fichero externo.
	 * 
	 * @param fichero Fichero a cargar.
	 * @throws ClassNotFoundException Clase no encontrada.
	 * @throws IOException            Error de E/S.
	 */
	@SuppressWarnings("unchecked")
	public void cargarDatos(File fichero) throws IOException, ClassNotFoundException {
		FileInputStream flujoEntrada = new FileInputStream(fichero);
		ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
		cafes = (ArrayList<Cafe>) deserializador.readObject();
		fabricantes = (ArrayList<Fabricante>) deserializador.readObject();
		deserializador.close();
		cambios = false;
	}

}
