package com.example.ejemcomparable;

public class Videojuego implements Comparable {
    String nombre;
    String empresa;
    String anoSalida;
    String trabajadores;

    public Videojuego(String nombre, String empresa, String anoSalida, String trabajadores) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.anoSalida = String.valueOf(anoSalida);
        this.trabajadores = trabajadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getAnoSalida() {
        return Integer.parseInt(anoSalida);
    }

    public void setAnoSalida(int anoSalida) {
        this.anoSalida = String.valueOf(anoSalida);
    }

    public String getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(String trabajadores) {
        this.trabajadores = trabajadores;
    }

    @Override
    public int compareTo(Object o) {
        Videojuego videojuego = (Videojuego) o;
        if (!this.anoSalida.equals(videojuego.anoSalida)) {
            return this.anoSalida.compareTo(videojuego.anoSalida);
        } else if (!this.empresa.equals(videojuego.getEmpresa())) {
            return this.empresa.compareTo(videojuego.empresa);
        } else if (this.trabajadores != trabajadores) {
            return this.trabajadores.compareTo(videojuego.trabajadores);
        } else {
            return this.nombre.compareTo(videojuego.nombre);
        }
    }

    @Override
    public String toString() {
        return nombre + "  " + empresa + "  " + anoSalida + "  " + trabajadores;
    }
}
