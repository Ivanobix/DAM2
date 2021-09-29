package com.example.ejem_contentprovider;

import android.net.Uri;

public class Contacto {
    public static final Uri contentUri =
            Uri.parse("content://com.example.ejem_bbdd2.ContactoProvider");
    public static final String COL_ID = "ID";
    public static final String COL_NOMBRE = "NOMBRE";
    public static final String COL_APE1 = "APELLIDO1";
    public static final String COL_APE2 = "APELLIDO2";
    public static final String COL_TLF = "TELEFONO";
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

    // Este método genera un String con el contenido de un objeto Contacto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre + "\n" + apellido1 + " " + apellido2 + "\n" + telefono);
        return sb.toString();
    }
}
