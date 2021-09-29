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
 * Modelo. Clase encargada del almacenamiento y tratamiento de los datos (Cafés
 * y Fabricantes), así como el control de cambios realizados.
 * 
 * @author Iván García Prieto
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
	 * Devuelve la lista de cafés.
	 * 
	 * @return Lista de cafés.
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
	 * @return Existencia o no de cambios en los datos de la aplicación.
	 */
	public boolean getCambios() {
		return cambios;
	}

	/**
	 * Establece la existencia de cambios.
	 * 
	 * @param cambios Existencia o no de cambios en los datos de la aplicación.
	 */
	public void setCambios(boolean cambios) {
		this.cambios = cambios;
	}

	/**
	 * Insercción de un café en la lista.
	 * 
	 * @param cafe Café a añadir en la lista.
	 */
	public void anadirCafe(Cafe cafe) {
		cafes.add(cafe);
		cambios = true;
	}

	/**
	 * Eliminación de un café determinado de la lista.
	 * 
	 * @param cafe Café a eliminar de la lista.
	 */
	public void eliminarCafe(Cafe cafe) {
		cafes.remove(cafe);
		cambios = true;
	}

	/**
	 * Modificación de los atributos de un café determinado.
	 * 
	 * @param cafe              Café a modificar.
	 * @param nombre            Nuevo nombre del café.
	 * @param fechaEnvasado     Nueva fecha de envasado del café.
	 * @param porcentajeArabico Nuevo porcentaje de café arabico en la mezcla del
	 *                          café.
	 * @param porcentajeRobusta Nuevo porcentaje de café robusta en la mezcla del
	 *                          café.
	 * @param fabricante        Nuevo fabricante del café.
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
	 * Insercción de un fabricante en la lista.
	 * 
	 * @param fabricante Fabricante a añadir en la lista.
	 */
	public void anadirFabricante(Fabricante fabricante) {
		fabricantes.add(fabricante);
		cambios = true;
	}

	/**
	 * Eliminación de un fabricante de la lista y todos los cafés producidos por
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
	 * Modificación de los atributos de un fabricante determinado.
	 * 
	 * @param fabricante    Fabricante a modificar.
	 * @param nombre        Nuevo nombre del fabricante.
	 * @param direccion     Nueva dirección del fabricante.
	 * @param trabajadores  Nuevo cantidad de trabajadores del fabricante.
	 * @param fechaCreacion Nueva fecha de creación de la empresa.
	 * @param internacional Nuevo ámbito del fabricante.
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
	 * Eliminación de todos los datos almacenados en la aplicación.
	 */
	public void reiniciarDatos() {
		cafes.clear();
		fabricantes.clear();
		cambios = true;
	}

	/**
	 * Guardado de todos los datos almacenados de la aplicación en un fichero
	 * externo.
	 * 
	 * @param fichero Ubicación del archivo de guardado.
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
