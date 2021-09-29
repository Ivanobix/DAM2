package com.fvaldeon.gestionalumnos.mvc.modelo;

import com.fvaldeon.gestionalumnos.base.Alumno;
import com.fvaldeon.gestionalumnos.base.Profesor;

import java.io.*;
import java.util.*;

public class Modelo {
    private List<Profesor> profesores;
    private List<Alumno> alumnos;

    public Modelo() {
        profesores = new ArrayList<>();
        alumnos = new LinkedList<>();
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void anadirProfesor(Profesor profesor){
        profesores.add(profesor);
        Collections.sort(profesores);
    }

    public void anadirAlumno(Alumno alumno){
        alumnos.add(alumno);
        Collections.sort(alumnos);

        //Tambien annado este alumnos a la lista de alumnos de su profesor
        if(alumno.getProfesor() != null){
            alumno.getProfesor().getAlumnos().add(alumno);
            Collections.sort(alumno.getProfesor().getAlumnos());
        }
    }

    public void eliminarAlumno(Alumno alumno){
        //Si tiene profesor tambien elimino al alumnos de la lista de alumnos de su profesor
        if(alumno.getProfesor() != null){
            alumno.getProfesor().getAlumnos().remove(alumno);
        }
        alumnos.remove(alumno);
    }

    public void eliminarProfesor(Profesor profesor){
        profesores.remove(profesor);
    }

    public void cargarDatos(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream deserializador = new ObjectInputStream(fis);
        profesores = (List<Profesor>) deserializador.readObject();
        alumnos = (List<Alumno>) deserializador.readObject();

        deserializador.close();
    }

    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(profesores);
        serializador.writeObject(alumnos);

        serializador.close();
    }

    public boolean existeCodigoProfesor(String codigo) {
        for(Profesor profesor : profesores){
            if(profesor.getCodigo().equalsIgnoreCase(codigo)){
                return true;
            }
        }
        return false;
    }

    public boolean existeDniAlumno(String dni){
        for(Alumno alumno : alumnos){
            if(alumno.getDni().equalsIgnoreCase(dni)){
                return true;
            }
        }
        return false;
    }
}
