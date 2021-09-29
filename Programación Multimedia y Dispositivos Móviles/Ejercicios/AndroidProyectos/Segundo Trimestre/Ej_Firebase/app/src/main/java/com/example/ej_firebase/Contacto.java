package com.example.ej_firebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    private String id;
    private String nombre;
    private String apellido;
    private long telefono;

    public Contacto() {
        // Constructor requerido
    }

    public Contacto(String id, String nom, String ape, long tlf) {
        this.id = id;
        this.nombre = nom;
        this.apellido = ape;
        this.telefono = tlf;
    }

    protected Contacto(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        telefono = in.readLong();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeLong(telefono);
    }
}
