package com.example.garciaprietoivan_20210218;

public class Articulo {

    protected int idArticulo;
    protected String nombre;
    protected String descripcion;
    protected String categoria;
    protected String estado;
    protected String telefono;
    protected Float precio;

    public Articulo () {    }

    public Articulo (String nom, String des, String cat, String est, String tlf, float pre) {
        nombre = nom;
        descripcion = des;
        categoria = cat;
        estado = est;
        telefono = tlf;
        precio = pre;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Float getPrecio() {
        return precio;
    }

    public String getPrecioTexto() {
        String precioTxt = String.format("%.2f", precio) + " €";
        return precioTxt;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

}
