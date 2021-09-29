package com.fvaldeon.gestionalumnos.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Util {

    public static void crearSiNoExisteDirectorioDatos(){
        File directorio = new File("data");
        if(!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public static Locale obtenerLocale() {

        Locale locale = null;

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");
            String idioma = properties.getProperty("idioma");

            if(pais.equals("UK")){
                locale = new Locale("en", "UK");
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Fichero de configuración inexistente: no existen configuraciones previas guardadas");
        }

        //Si no se ha podido cargar el fichero, idioma spanish
        if(locale == null){
            locale = new Locale("es", "ES");
        }

        return locale;
    }


    public static ImageIcon escalarImageIcon( ImageIcon icon, int alto, int ancho){
        //Paso 1: obtengo el objeto Image
        Image imagen = icon.getImage();

        //Paso 2: obtengo otro objeto image escalado
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_FAST);

        //Paso 3: creo un objeto ImageIcon a partir de Image escalado
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        return iconoEscalado;
    }
}
