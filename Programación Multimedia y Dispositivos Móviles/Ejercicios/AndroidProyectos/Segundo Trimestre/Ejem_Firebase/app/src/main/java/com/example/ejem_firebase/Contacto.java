package com.example.ejem_firebase;

public class Contacto {
    public String nombre;
    public String apellido;
    public long telefono;

    public Contacto() {
        // Constructor requerido
    }

    public Contacto(String nom, String ape, long tlf) {
        this.nombre = nom;
        this.apellido = ape;
        this.telefono = tlf;
    }
}
