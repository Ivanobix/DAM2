package com.example.ej13;

public class Disco {
    private int idPortada;
    private String titulo;
    private String autor;

    public Disco(int idPortada, String titulo, String autor) {
        this.idPortada = idPortada;
        this.titulo = titulo;
        this.autor = autor;
    }

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
}
