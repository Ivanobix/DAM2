package com.acme.ejer5_08;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipo implements Parcelable {

    public static final Parcelable.Creator<Equipo> CREATOR = new Parcelable.Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel source) {
            return new Equipo(source);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };
    private String nombre, ciudad, conferencia, division;

    public Equipo(String nombre, String ciudad, String conferencia, String division) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.conferencia = conferencia;
        this.division = division;
    }

    public Equipo() {
        this.nombre = "";
        this.ciudad = "";
        this.conferencia = "";
        this.division = "";
    }

    public Equipo(Parcel source) {
        this.nombre = source.readString();
        this.ciudad = source.readString();
        this.conferencia = source.readString();
        this.division = source.readString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(ciudad);
        dest.writeString(conferencia);
        dest.writeString(division);
    }
}
