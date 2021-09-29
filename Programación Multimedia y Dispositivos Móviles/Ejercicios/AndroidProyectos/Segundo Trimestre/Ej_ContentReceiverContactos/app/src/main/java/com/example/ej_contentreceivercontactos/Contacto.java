package com.example.ej_contentreceivercontactos;

import android.net.Uri;

public class Contacto {

    private String nombre;
    private Uri fotoPerfil;

    public Contacto(String nombre, Uri fotoPerfil) {
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Uri getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Uri fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
