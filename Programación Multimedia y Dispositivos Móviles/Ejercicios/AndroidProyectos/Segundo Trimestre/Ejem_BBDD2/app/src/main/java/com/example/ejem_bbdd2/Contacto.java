package com.example.ejem_bbdd2;

public class Contacto {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;

    public Contacto() {
    }

    public Contacto(String nombre, String ape1, String ape2, String tlf) {
        this.nombre = nombre;
        this.apellido1 = ape1;
        this.apellido2 = ape2;
        this.telefono = tlf;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String ape1) {
        this.apellido1 = ape1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String ape2) {
        this.apellido2 = ape2;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String tlf) {
        this.telefono = tlf;
    }

    @Override
    public String toString() {
        return nombre + "\n" + apellido1 + " " + apellido2 + "\n" + telefono;
    }
}

