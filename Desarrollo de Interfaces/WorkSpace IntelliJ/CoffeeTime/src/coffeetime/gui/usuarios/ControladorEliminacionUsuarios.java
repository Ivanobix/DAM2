package coffeetime.gui.usuarios;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Controlador de la Eliminación de Usuarios. Controlador para la ventana de Eliminación de Usuarios
 * dedicado a la recogida de eventos, así como el acceso a las funciones de
 * eliminación de usuarios.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorEliminacionUsuarios {
    private final EliminacionUsuarios eliminacionUsuarios;
    private final ResourceBundle idioma;
    private ArrayList<Usuario> usuarios;

    /**
     * Constructor.
     *
     * @param eliminacionUsuarios Ventana de Eliminación de Usuarios.
     */
    public ControladorEliminacionUsuarios(EliminacionUsuarios eliminacionUsuarios) {
        this.eliminacionUsuarios = eliminacionUsuarios;
        idioma = Util.obtenerTraducciones();
        rellenarListaUsuarios();
        crearAtajos();
        initHandlers();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        eliminacionUsuarios.btnCancelar.addActionListener(e -> eliminacionUsuarios.dispose());
        eliminacionUsuarios.btnAceptar.addActionListener(e -> eliminarUsuario());
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        eliminacionUsuarios.btnAceptar.setMnemonic(KeyEvent.VK_1);
        eliminacionUsuarios.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * En caso de existir, rellena el desplegable con los usuarios existentes.
     */
    private void rellenarListaUsuarios() {
        try {
            usuarios = null;

            FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
            ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();
            deserializador.close();

            if (usuarios != null) {
                Properties properties = new Properties();
                properties.load(new FileReader("data/account.conf"));
                int nivelUsuario = Integer.parseInt(properties.getProperty("NivelUsuario"));
                if (nivelUsuario == Usuario.BASICO || nivelUsuario == Usuario.DEFAULT) {
                    String usuario = properties.getProperty("Usuario");
                    for (Usuario usser : usuarios) {
                        if (usser.getUsuario().equals(usuario)) {
                            eliminacionUsuarios.cbUsuario.addItem(usser);
                            break;
                        }
                    }
                } else {
                    for (Usuario usuario : usuarios) {
                        eliminacionUsuarios.cbUsuario.addItem(usuario);
                    }
                }
            }
        } catch (Exception ignore) {
        }

    }

    /**
     * Elimina el usuario seleccionado.
     */
    private void eliminarUsuario() {
        if (eliminacionUsuarios.cbUsuario.getSelectedIndex() != -1) {
            int seguroDeEliminar = Util.mostrarConfirmacion(idioma.getString("confirmacion.eliminarUsuario"));
            if (seguroDeEliminar == JOptionPane.YES_OPTION) {
                try {
                    FileOutputStream flujoSalida = new FileOutputStream("data/usuarios.dat");
                    ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
                    usuarios.remove(eliminacionUsuarios.cbUsuario.getSelectedIndex());
                    serializador.writeObject(usuarios);
                    serializador.close();
                    eliminacionUsuarios.dispose();

                    Properties propiedades = new Properties();

                    propiedades.put("Usuario", "");
                    propiedades.put("Contrasena", "");
                    propiedades.put("NivelUsuario", "");
                    propiedades.store(new FileWriter("data/account.conf"), "Coffe Time");

                    System.exit(0);
                } catch (Exception ignore) {
                }

            }
        } else {
            Util.mostrarError(idioma.getString("error.seleccionarusuario"));
        }

    }
}
