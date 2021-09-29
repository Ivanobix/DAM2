package coffeetime.gui.usuarios;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador de la Creación de Usuarios. Controlador para la ventana de Creación de Usuarios
 * dedicado a la recogida de eventos, así como el acceso a las funciones de
 * creación de usuarios.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorCreacionUsuarios {

    private final CreacionUsuarios creacionUsuarios;
    private final ResourceBundle idioma;

    /**
     * Constructor.
     *
     * @param creacionUsuarios Ventana de Creación de Usuarios.
     */
    public ControladorCreacionUsuarios(CreacionUsuarios creacionUsuarios) {
        this.creacionUsuarios = creacionUsuarios;
        idioma = Util.obtenerTraducciones();
        crearAtajos();
        initHandlers();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        creacionUsuarios.btnCancelar.addActionListener(e -> creacionUsuarios.dispose());
        creacionUsuarios.btnAceptar.addActionListener(e -> crearUsuario());
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        creacionUsuarios.btnAceptar.setMnemonic(KeyEvent.VK_1);
        creacionUsuarios.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * Creación del usuario en función de los datos recogidos en la ventana.
     */
    private void crearUsuario() {
        Usuario usuario = recogerDatos();
        if (usuario != null) {
            ArrayList<Usuario> usuarios;
            try {
                FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
                ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
                usuarios = (ArrayList<Usuario>) deserializador.readObject();
                deserializador.close();

            } catch (Exception e) {
                usuarios = new ArrayList<>();
            }

            try {
                FileOutputStream flujoSalida = new FileOutputStream("data/usuarios.dat");
                ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
                usuarios.add(usuario);
                serializador.writeObject(usuarios);
                serializador.close();
                creacionUsuarios.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Recoge los datos introducidos, los verifica y crea el usuario correspondiente.
     *
     * @return Devuelve el usuario creado tras la recogida de datos.
     */
    private Usuario recogerDatos() {
        Usuario usuario = null;
        String nombreUsuario = creacionUsuarios.txtUsuario.getText().replaceAll(" ", "");
        if ((nombreUsuario.length() > 0)) {
            StringBuilder contrasena = new StringBuilder();
            for (char caracter : creacionUsuarios.txtContrasena.getPassword()) {
                contrasena.append(caracter);
            }
            if (contrasena.toString().replaceAll(" ", "").length() > 0) {
                int nivelUsuario;
                if (creacionUsuarios.rbAdmin.isSelected()) {
                    nivelUsuario = Usuario.ADMIN;
                } else if (creacionUsuarios.rbNormal.isSelected()) {
                    nivelUsuario = Usuario.DEFAULT;
                } else {
                    nivelUsuario = Usuario.BASICO;
                }
                usuario = new Usuario(nombreUsuario, contrasena.toString(), nivelUsuario);
            } else {
                Util.mostrarError(idioma.getString("error.contrasenaVacia"));
            }
        } else {
            Util.mostrarError(idioma.getString("error.usuarioVacio"));
        }
        return usuario;
    }
}
