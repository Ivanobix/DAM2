package com.fvaldeon.gestionalumnos.dialogos;

import com.fvaldeon.gestionalumnos.base.Usuario;
import com.fvaldeon.gestionalumnos.util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DialogoLogin extends JDialog {
    private static final String ADMIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private ArrayList<Usuario> usuarios;
    private int estado;
    private ResourceBundle resourceBundle;

    private DialogoLogin() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        cargarUsuarios();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setUndecorated(true);

        initHandlers();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static int mostrarDialogoLogin(){
        DialogoLogin login = new DialogoLogin();
        return login.estado;
    }

    private void initHandlers() {
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
    }

    private void onOK() {
        // add your code here
        comprobarUsuarios();
    }

    private void comprobarUsuarios() {

        if(txtLogin.getText().equals(ADMIN)
                && String.valueOf(txtPassword.getPassword()).equals(ADMIN_PASSWORD)){
            estado = Usuario.USUARIO_ADMIN;
            dispose();
        } else if(usuarios != null){
            if(comprobarUsuarioRegistrados(txtLogin.getText(), txtPassword.getPassword())){
                dispose();

            } else {
                Util.mensajeError(resourceBundle.getString("mensaje.login.incorrecto"));
                txtLogin.setText("");
                txtPassword.setText("");
                txtLogin.requestFocus();
            }

        } else {
            Util.mensajeError(resourceBundle.getString("mensaje.login.incorrecto"));
            txtLogin.setText("");
            txtPassword.setText("");
            txtLogin.requestFocus();
        }

    }

    private void cargarUsuarios(){
        FileInputStream fis = null;
        ObjectInputStream deserializador = null;


        try {
            fis = new FileInputStream("data/users.dat");
            deserializador = new ObjectInputStream(fis);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Fichero de usuarios inexistente: no existen usuarios guardados");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(deserializador != null){
                try {
                    deserializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private boolean comprobarUsuarioRegistrados(String login, char[] password){

        for(Usuario usuario : usuarios){
            if(usuario.getLogin().equals(login) && usuario.getPassword().equals(String.valueOf(password))){
                estado = usuario.getRol();
                return true;
            }
        }
        return false;
    }

    private void onCancel() {
        // add your code here if necessary
        System.exit(0);
    }
}
