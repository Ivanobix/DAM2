package com.fvaldeon.gestionalumnos.dialogos;

import com.fvaldeon.gestionalumnos.base.Usuario;
import com.fvaldeon.gestionalumnos.util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DialogoGestionUsuarios extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Usuario> list;
    private JButton nuevoUsuarioButton;
    private JButton eliminarUsuarioButton;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JCheckBox mostrarContrasenaCheckBox;
    private JComboBox tipoUsuarioComboB;
    private ArrayList<Usuario> usuarios;
    private DefaultListModel<Usuario> dlm;
    private ResourceBundle resourceBundle;

    public DialogoGestionUsuarios() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");

        dlm = new DefaultListModel<>();
        list.setModel(dlm);
        initDialog();
        cargarUsuarios();
        listarUsuarios();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);


    }

    private void mostrarDatos() {
        Usuario usuarioSeleccionado = list.getSelectedValue();
        if(usuarioSeleccionado != null){
            txtLogin.setText(usuarioSeleccionado.getLogin());
            txtPassword.setText(usuarioSeleccionado.getPassword());
            tipoUsuarioComboB.setSelectedIndex(usuarioSeleccionado.getRol());
        } else {
            txtPassword.setText("");
            txtLogin.setText("");
            tipoUsuarioComboB.setSelectedIndex(Usuario.USUARIO_ADMIN);
        }
    }

    private void mostrarContrasena() {
        if(mostrarContrasenaCheckBox.isSelected()){
            txtPassword.setEchoChar((char)0);
        } else {
            txtPassword.setEchoChar('*');
        }
    }

    private void listarUsuarios() {
        dlm.clear();
        for(Usuario usuario : usuarios){
            dlm.addElement(usuario);
        }
    }

    private void initDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle(resourceBundle.getString("dialogo.usuarios.titulo"));
        getRootPane().setDefaultButton(buttonOK);
        txtPassword.setEchoChar('*');

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
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eliminarUsuario();
            }
        });
        nuevoUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevoUsuario();
            }
        });

        mostrarContrasenaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarContrasena();
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarDatos();
            }
        });
    }

    private void nuevoUsuario() {

        String login = txtLogin.getText();
        String password = String.valueOf(txtPassword.getPassword());
        //0 para ADMIN, 1 para Standard
        int rol = tipoUsuarioComboB.getSelectedIndex();
        usuarios.add(new Usuario(login, password, rol));

        listarUsuarios();
    }

    private void eliminarUsuario() {
        Usuario usuarioEliminar = list.getSelectedValue();
        if(usuarioEliminar != null){
            usuarios.remove(usuarioEliminar);
            listarUsuarios();
        }
    }

    private void onOK() {
        // add your code here
        guardarUsuarios();
        dispose();
    }

    private void guardarUsuarios()  {
        FileOutputStream fos = null;
        ObjectOutputStream serializador = null;
        try {
            fos = new FileOutputStream("data/users.dat");
            serializador = new ObjectOutputStream(fos);
            serializador.writeObject(usuarios);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(serializador != null) {
                    serializador.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void cargarUsuarios(){
        FileInputStream fileInputStream = null;
        ObjectInputStream deserializador = null;

        try {
            fileInputStream = new FileInputStream("data/users.dat");
            deserializador = new ObjectInputStream(fileInputStream);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();

        } catch (Exception e){
            //e.printStackTrace();
            System.err.println("Fichero de usuarios inexistente: no existen usuarios guardados");

            usuarios =  new ArrayList<>();
        } finally {
            try {
                if(deserializador != null) {
                    deserializador.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
