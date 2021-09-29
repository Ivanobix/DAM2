package com.fvaldeon.gestionalumnos.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profesor implements Comparable<Profesor>, Serializable{

    private String codigo;
    private String nombre;
    private String apellidos;
    private List<Alumno> alumnos;

    public Profesor(String codigo, String nombre, String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        alumnos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return codigo + " - " +  apellidos + ", " + nombre;
    }

    @Override
    public int compareTo(Profesor profesor) {

        if(profesor == null){
            throw new NullPointerException("profesor es null");
        }

        if(profesor.getClass() != Profesor.class){
            throw new ClassCastException("el objeto no es de tipo Profesor");
        }

        if(apellidos.equalsIgnoreCase(profesor.apellidos)){
            return nombre.compareTo(profesor.nombre);
        }
        return apellidos.compareTo(profesor.apellidos);
    }
}
