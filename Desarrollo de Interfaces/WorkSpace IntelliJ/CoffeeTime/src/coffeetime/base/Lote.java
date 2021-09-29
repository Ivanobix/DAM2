package coffeetime.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Lote. Clase dedicada a la construcción de objetos tipo "Lote"
 * para su posterior almacenamiento o tratamiento por diferentes clases.
 *
 * @author Iván García Prieto
 * @version 13.01.2021
 */
public class Lote implements Serializable {

    private static final long serialVersionUID = 6529685652987757690L;

    private String identificador;
    private int numeroUnidades;
    private double costeTotal;
    private LocalDate fechaDeEnvasado;
    private LocalDate fechaDeCaducidad;
    private Fabricante fabricante;
    private ArrayList<Cafe> cafes;

    /**
     * Constructor.
     *
     * @param numeroUnidades   Número de unidades.
     * @param costeTotal       Coste total.
     * @param fechaDeEnvasado  Fecha de envasado.
     * @param fechaDeCaducidad Fecha de caducidad estimada.
     * @param fabricante       Fabricante.
     */
    public Lote(int numeroUnidades, double costeTotal, LocalDate fechaDeEnvasado, LocalDate fechaDeCaducidad, Fabricante fabricante) {
        identificador = UUID.randomUUID().toString().substring(0, 10);
        this.numeroUnidades = numeroUnidades;
        this.costeTotal = costeTotal;
        this.fechaDeEnvasado = fechaDeEnvasado;
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.fabricante = fabricante;
        cafes = new ArrayList<>();
    }

    /**
     * Devuelve el identificador del lote.
     *
     * @return Identificador del lote.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador del lote.
     *
     * @param identificador Identificador del lote.
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Devuelve el número de unidades del lote.
     *
     * @return Número de unidades en el lote.
     */
    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    /**
     * Establece el número de unidades del lote.
     *
     * @param numeroUnidades Número de unidades en el lote.
     */
    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    /**
     * Devuelve el coste total del lote.
     *
     * @return Coste total del lote.
     */
    public double getCosteTotal() {
        return costeTotal;
    }

    /**
     * Establece el coste total del lote.
     *
     * @param costeTotal Coste total del lote.
     */
    public void setCosteTotal(double costeTotal) {
        this.costeTotal = costeTotal;
    }

    /**
     * Devuelve la fecha de envasado del lote.
     *
     * @return Fecha de envasado del lote.
     */
    public LocalDate getFechaDeEnvasado() {
        return fechaDeEnvasado;
    }

    /**
     * Establece la fecha de envasado del lote.
     *
     * @param fechaDeEnvasado Fecha de envasado del lote.
     */
    public void setFechaDeEnvasado(LocalDate fechaDeEnvasado) {
        this.fechaDeEnvasado = fechaDeEnvasado;
    }

    /**
     * Devuelve la fecha de caducidad estimada del lote.
     *
     * @return Fecha de caducidad estimada del lote.
     */
    public LocalDate getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    /**
     * Establece la fecha de caducidad estimada del lote.
     *
     * @param fechaDeCaducidad Fecha de caducidad estimada del lote.
     */
    public void setFechaDeCaducidad(LocalDate fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    /**
     * Devuelve el fabricante del lote.
     *
     * @return Fabricante del lote.
     */
    public Fabricante getFabricante() {
        return fabricante;
    }

    /**
     * Establece el fabricante del lote.
     *
     * @param fabricante Fabricante del lote.
     */
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }


    /**
     * Devuelve los cafés del lote.
     *
     * @return Cafés del lote.
     */
    public ArrayList<Cafe> getCafes() {
        return cafes;
    }

    /**
     * Establece el los cafés que pertenecen al lote.
     *
     * @param cafes Cafés del lote.
     */
    public void setCafes(ArrayList<Cafe> cafes) {
        this.cafes = cafes;
    }

    /**
     * Añade un café a la lista.
     *
     * @param cafe Café a añadir.
     */
    public void addCafe(Cafe cafe) {
        cafes.add(cafe);
    }

    /**
     * Elimina un café de la lista.
     *
     * @param cafe Café a eliminar.
     */
    public void deleteCafe(Cafe cafe) {
        cafes.remove(cafe);
    }

    /**
     * Devuelve una cadena con la información básica de un lote.
     */
    @Override
    public String toString() {
        return "Identificador: " + identificador + "   //   Número de unidades: " + numeroUnidades;
    }
}
