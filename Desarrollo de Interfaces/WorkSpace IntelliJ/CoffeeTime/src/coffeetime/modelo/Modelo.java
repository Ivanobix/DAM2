package coffeetime.modelo;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Modelo. Clase encargada del almacenamiento y tratamiento de los datos (Cafés, Lotes
 * y Fabricantes), así como el control de cambios realizados.
 *
 * @author Iván García Prieto
 * @version 13.01.2021
 */
public class Modelo {

    private ArrayList<Cafe> cafes;
    private ArrayList<Lote> lotes;
    private ArrayList<Fabricante> fabricantes;
    private boolean cambios;

    /**
     * Constructor.
     */
    public Modelo() {
        cafes = new ArrayList<>();
        lotes = new ArrayList<>();
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
     * Devuelve la lista de lotes.
     *
     * @return Lista de lotes.
     */
    public ArrayList<Lote> getLotes() {
        return lotes;
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
        for (Lote lote : lotes) {
            if (lote.getCafes().contains(cafe)) {
                lote.deleteCafe(cafe);
            }
        }
        cambios = true;
    }

    /**
     * Modificación de los atributos de un café determinado.
     *
     * @param cafe              Café a modificar.
     * @param nombre            Nuevo nombre del café.
     * @param imagenPromocional Nueva imagen promocional del café.
     * @param porcentajeArabico Nuevo porcentaje de café arabico en la mezcla del
     *                          café.
     * @param porcentajeRobusta Nuevo porcentaje de café robusta en la mezcla del
     *                          café.
     */
    public void modificarCafe(Cafe cafe, String nombre, String imagenPromocional, double porcentajeArabico,
                              double porcentajeRobusta) {
        cafe.setNombre(nombre);
        cafe.setImagenPromocional(imagenPromocional);
        cafe.setPorcentajeArabico(porcentajeArabico);
        cafe.setPorcentajeRobusta(porcentajeRobusta);
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
     * Eliminación de un fabricante de la lista y todos los lotes producidos por
     * este.
     *
     * @param fabricante Fabricante a eliminar de la lista.
     */
    public void eliminarFabricante(Fabricante fabricante) {
        //Eliminar todos los lotes del fabricante
        lotes.removeIf(lote -> lote.getFabricante().equals(fabricante));

        //Eliminar fabricante
        fabricantes.remove(fabricante);
        cambios = true;
    }

    /**
     * Modificación de los atributos de un fabricante determinado.
     *
     * @param fabricante    Fabricante a modificar.
     * @param nombre        Nuevo nombre del fabricante.
     * @param direccion     Nueva dirección del fabricante.
     * @param trabajadores  Nuevo cantidad de trabajadores del fabricante.
     * @param fechaAlta     Nueva fecha de alta de la empresa.
     * @param internacional Nuevo ámbito del fabricante.
     */
    public void modificarFabricante(Fabricante fabricante, String nombre, String direccion, int trabajadores,
                                    LocalDate fechaAlta, boolean internacional) {
        fabricante.setNombre(nombre);
        fabricante.setDireccion(direccion);
        fabricante.setTrabajadores(trabajadores);
        fabricante.setFechaAlta(fechaAlta);
        fabricante.setInternacional(internacional);
        cambios = true;
    }

    /**
     * Insercción de un lote en la lista.
     *
     * @param lote Lote a añadir en la lista.
     */
    public void anadirLote(Lote lote) {
        lotes.add(lote);
        cambios = true;
    }

    /**
     * Eliminación de un lote de la lista.
     *
     * @param lote Lote a eliminar de la lista.
     */
    public void eliminarLote(Lote lote) {
        lotes.remove(lote);
        cambios = true;
    }

    /**
     * Modificación de los atributos de un lote determinado.
     *
     * @param lote             Lote a modificar.
     * @param numeroUnidades   Nuevo número de unidades.
     * @param costeTotal       Nuevo coste total.
     * @param fechaDeEnvasado  Nueva fecha de envasado.
     * @param fechaDeCaducidad Nueva fecha de caducidad estimada.
     * @param fabricante       Nuevo fabricante.
     */
    public void modificarLote(Lote lote, int numeroUnidades, double costeTotal, LocalDate fechaDeEnvasado, LocalDate fechaDeCaducidad, Fabricante fabricante) {
        lote.setNumeroUnidades(numeroUnidades);
        lote.setCosteTotal(costeTotal);
        lote.setFechaDeEnvasado(fechaDeEnvasado);
        lote.setFechaDeCaducidad(fechaDeCaducidad);
        lote.setFabricante(fabricante);
        cambios = true;
    }

    /**
     * Eliminación de todos los datos almacenados en la aplicación.
     */
    public void reiniciarDatos() {
        cafes.clear();
        lotes.clear();
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
        serializador.writeObject(lotes);
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
        lotes = (ArrayList<Lote>) deserializador.readObject();
        fabricantes = (ArrayList<Fabricante>) deserializador.readObject();
        deserializador.close();
        cambios = false;
    }

}
