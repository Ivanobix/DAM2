package com.example.garcaprietoivn_2020_12_03;

import android.os.Parcel;
import android.os.Parcelable;

public class Mascota implements Parcelable {
    public static final Creator<Mascota> CREATOR = new Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };
    String nombre;
    String raza;
    String estado;
    int anoNacimiento;
    int idFoto;

    public Mascota(String nombre, String raza, String estado, int anoNacimiento, int idFoto) {
        this.nombre = nombre;
        this.raza = raza;
        this.estado = estado;
        this.anoNacimiento = anoNacimiento;
        if (idFoto == -1) {
            switch (raza) {
                case "Alaska":
                    this.idFoto = R.drawable.raza_alaska;
                    break;
                case "Bulldog Francés":
                    this.idFoto = R.drawable.raza_bulldogfrances;
                    break;
                case "Galgo":
                    this.idFoto = R.drawable.raza_galgo;
                    break;
                case "Pastor Alemán":
                    this.idFoto = R.drawable.raza_pastoraleman;
                    break;
                case "Otro":
                    this.idFoto = R.drawable.raza_nodisponible;
                    break;
            }
        } else {
            this.idFoto = idFoto;
        }

    }

    protected Mascota(Parcel in) {
        nombre = in.readString();
        raza = in.readString();
        estado = in.readString();
        anoNacimiento = in.readInt();
        idFoto = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(raza);
        dest.writeString(estado);
        dest.writeInt(anoNacimiento);
        dest.writeInt(idFoto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
