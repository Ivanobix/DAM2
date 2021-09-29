package com.fvaldeon.gestionalumnos.base;


import javax.swing.Icon;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;

public class Alumno implements Comparable<Alumno>, Serializable{
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private Icon foto;
    private Profesor profesor;

    public Alumno(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, Profesor profesor) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.profesor = profesor;
    }

    public Alumno(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, Icon foto, Profesor profesor) {
        this(dni, nombre, apellidos, fechaNacimiento, profesor);
        this.foto = foto;

    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void establecerProfesorSafe(Profesor profesor) {
        //Si antes ya tenia profesor, lo elimino de su lista
        if(this.profesor != null){
            this.profesor.getAlumnos().remove(this);
        }
        this.profesor = profesor;
        //Tambien annado este alumno a la lista de alumnos de su profesor
        if(profesor != null){
            profesor.getAlumnos().add(this);
            Collections.sort(profesor.getAlumnos());
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Icon getFoto() {
        return foto;
    }

    public void setFoto(Icon foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return dni + " : "  + apellidos + ", " + nombre;
    }

    @Override
    public int compareTo(Alumno alumno) {

        if(apellidos.equals(alumno.apellidos)){
            return nombre.compareTo(alumno.nombre);
        }

        return apellidos.compareTo(alumno.apellidos);
    }
}
