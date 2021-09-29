package com.fvaldeon.gestionalumnos;

import com.fvaldeon.gestionalumnos.mvc.gui.Controlador;
import com.fvaldeon.gestionalumnos.mvc.gui.Vista;
import com.fvaldeon.gestionalumnos.dialogos.DialogoLogin;
import com.fvaldeon.gestionalumnos.mvc.modelo.Modelo;
import com.fvaldeon.gestionalumnos.util.Util;

import javax.swing.*;
import java.util.Locale;

public class Principal {

    public static void main(String[] args) {
        //Cargar propedades de idioma
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        System.out.println(Locale.getDefault());


        Util.crearSiNoExisteDirectorioDatos();
        //Control de tipo de usuario logueado
        int tipoUsuario = DialogoLogin.mostrarDialogoLogin();

        Vista vista = new Vista(tipoUsuario);
        Modelo modelo = new Modelo();

        Controlador controlador = new Controlador(vista, modelo);
    }

}
