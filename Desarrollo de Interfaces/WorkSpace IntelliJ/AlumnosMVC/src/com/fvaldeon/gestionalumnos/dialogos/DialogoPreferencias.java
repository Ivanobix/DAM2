package com.fvaldeon.gestionalumnos.dialogos;

import com.fvaldeon.gestionalumnos.util.Util;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class DialogoPreferencias extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton rbSpanish;
    private JLabel lblColor;
    private JButton seleccionarButton;
    private JRadioButton rbEnglish;
    private ResourceBundle resourceBundle;

    public DialogoPreferencias() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");

        initDialog();
        cargarConfiguracion();
        pack();
        setVisible(true);

    }

    private void initDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Configuracion");
        setLocationRelativeTo(null);



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Color color = JColorChooser.showDialog(null, resourceBundle.getString("jcolorchooser.titulo"), null);

                lblColor.setForeground(color);
            }
        });
    }

    private void onOK() {

        guardarConfiguracion();
        int opt = Util.mensajeConfirmacion(resourceBundle.getString("dialogo.preferencias.mensaje.reinicio"));

        if(opt == JOptionPane.YES_OPTION){
            System.exit(2);
        }
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void guardarConfiguracion(){
        Properties propiedades = new Properties();
        String idioma;
        String pais;
        if(rbSpanish.isSelected()){
                idioma = "es";
                pais = "ES";
        } else {
            idioma = "en";
            pais = "UK";
        }
        propiedades.setProperty("idioma", idioma);
        propiedades.setProperty("pais", pais);
        propiedades.setProperty("color", String.valueOf(lblColor.getForeground().getRGB()));

        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarConfiguracion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String pais = properties.getProperty("pais");

            if(pais.equals("ES")){
                rbSpanish.setSelected(true);
            }else {
                rbEnglish.setSelected(true);
            }
            Color color = new Color(Integer.parseInt(properties.getProperty("color")));

            lblColor.setForeground(color);

        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Fichero de configuración inexistente: no existen configuraciones previas guardadas");

        }
    }

}
