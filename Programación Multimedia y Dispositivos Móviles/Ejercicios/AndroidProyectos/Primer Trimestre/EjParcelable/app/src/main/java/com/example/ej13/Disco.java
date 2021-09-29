package com.example.ej13;

import android.os.Parcel;
import android.os.Parcelable;

public class Disco  implements Parcelable {
    private int idPortada;
    private String titulo;
    private String autor;

    public Disco(int idPortada, String titulo, String autor) {
        this.idPortada = idPortada;
        this.titulo = titulo;
        this.autor = autor;
    }

    protected Disco(Parcel in) {
        titulo = in.readString();
        autor = in.readString();
        idPortada = in.readInt();
    }

    public static final Creator<Disco> CREATOR = new Creator<Disco>() {
        @Override
        public Disco createFromParcel(Parcel in) {
            return new Disco(in);
        }

        @Override
        public Disco[] newArray(int size) {
            return new Disco[size];
        }
    };

    public int getPortada() {
        return idPortada;
    }

    public void setPortada(int portada) {
        this.idPortada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(autor);
        dest.writeInt(idPortada);
    }
}
