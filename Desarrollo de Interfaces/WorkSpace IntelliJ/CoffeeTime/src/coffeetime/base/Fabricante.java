package coffeetime.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Fabricante. Clase dedicada a la construcción de objetos tipo "Fabricante"
 * para su posterior almacenamiento o tratamiento por diferentes clases.
 *
 * @author Iván García Prieto
 * @version 13.01.2021
 */
public class Fabricante implements Serializable {

    private static final long serialVersionUID = 6529685098267096520L;

    private String identificador;
    private String nombre;
    private String direccion;
    private int trabajadores;
    private boolean internacional;
    private LocalDate fechaAlta;


    /**
     * Constructor.
     *
     * @param nombre        Nombre del fabricante.
     * @param direccion     Direccion del fabricante.
     * @param trabajadores  Trabajadores de la empresa.
     * @param fechaAlta     Fecha de creación de la empresa.
     * @param internacional Ambito del fabricante.
     */
    public Fabricante(String nombre, String direccion, int trabajadores, LocalDate fechaAlta,
                      boolean internacional) {
        super();
        identificador = UUID.randomUUID().toString().substring(0, 10);
        this.nombre = nombre;
        this.setDireccion(direccion);
        this.trabajadores = trabajadores;
        this.fechaAlta = fechaAlta;
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
     * Devuelve la dirección del fabricante.
     *
     * @return Dirección del fabricante.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del fabricante.
     *
     * @param direccion Dirección del fabricante.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el número de trabajadores del fabricante.
     *
     * @return Trabajadores de la empresa.
     */
    public int getTrabajadores() {
        return trabajadores;
    }

    /**
     * Establece el número de trabajadores del fabricante.
     *
     * @param trabajadores Trabajadores de la empresa.
     */
    public void setTrabajadores(int trabajadores) {
        this.trabajadores = trabajadores;
    }

    /**
     * Devuelve la fecha de alta de la empresa.
     *
     * @return Fecha de alta de la empresa.
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece la fecha de alta de la empresa.
     *
     * @param fechaAlta Fecha de alta de la empresa.
     */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Devuelve el ámbito del fabricante.
     *
     * @return Ambito del fabricante.
     */
    public boolean isInternacional() {
        return internacional;
    }

    /**
     * Establece el ámbito del fabricante.
     *
     * @param internacional Ambito del fabricante.
     */
    public void setInternacional(boolean internacional) {
        this.internacional = internacional;
    }

    /**
     * Devuelve una cadena con la información básica de un fabricante.
     */
    @Override
    public String toString() {
        return "Identificador: " + identificador + "   //   Nombre: " + nombre;
    }

}