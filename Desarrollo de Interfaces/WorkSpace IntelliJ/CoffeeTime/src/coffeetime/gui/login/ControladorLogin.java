package coffeetime.gui.login;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Controlador Login. Controlador para la ventana de login
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos, permitiendo de esta forma un control efectivo de acceso a la aplicación.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorLogin {

    private final Login login;
    private ArrayList<Usuario> usuarios;
    private ResourceBundle idioma;

    /**
     * Constructor
     */
    public ControladorLogin() {
        login = new Login();
        cargarUsuarios();
        if (usuarios != null) {
            idioma = Util.obtenerTraducciones();
            crearAtajos();
            initHandlers();
            login.setVisible(true);
        }
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        login.btnCancelar.addActionListener(e -> System.exit(0));
        login.btnAceptar.addActionListener(e -> comprobarUsuario());
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        login.btnAceptar.setMnemonic(KeyEvent.VK_1);
        login.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * Comprueba si ya hay una sesión abierta, de ser así permite la ejecución del programa principal.
     * Si la sesión ya no está abierta comprueba si existen usuarios creados.
     * En caso de que no existan permite la ejecución del programa principal,
     * y en el caso contrario restringe el acceso hasta que haya un inicio de sesión satisfactorio.
     */
    private void cargarUsuarios() {
        boolean sesionGuardada = false;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/account.conf"));
            String nivelUsuario = (String) properties.get("NivelUsuario");
            if (!nivelUsuario.equals("")) {
                sesionGuardada = true;
            }
        } catch (Exception ignored) {
        }

        if (sesionGuardada) {
            login.dispose();
        } else {
            try {
                FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
                ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
                usuarios = (ArrayList<Usuario>) deserializador.readObject();
                deserializador.close();
                if (usuarios.size() <= 0) {
                    usuarios = null;
                }
            } catch (Exception e) {
                usuarios = null;
            }
        }

    }

    /**
     * Comprueba que los datos introducidos por el usuario correspondan
     * a uno de los usuarios creados anteriormente.
     */
    private void comprobarUsuario() {
        boolean usuarioEncontrado = false;
        boolean usuarioCorrecto = false;
        Usuario usuarioLogueado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(login.txtUsuario.getText())) {
                StringBuilder contrasena = new StringBuilder();
                for (char caracter : login.txtContrasena.getPassword()) {
                    contrasena.append(caracter);
                }
                if (usuario.getContrasena().equals(contrasena.toString())) {
                    usuarioCorrecto = true;
                    usuarioLogueado = usuario;
                } else {
                    Util.mostrarError(idioma.getString("error.contrasena"));
                }
                usuarioEncontrado = true;
                break;
            }
        }
        if (!usuarioEncontrado) {
            Util.mostrarError(idioma.getString("error.usuario"));
        } else if (usuarioCorrecto) {
            login.dispose();
            guardarInicioDeSesion(usuarioLogueado);
        }
    }

    /**
     * Guarda en un archivo de configuración el último inicio de sesión, de forma que hasta el cierre de la misma
     * la aplicación permita su uso.
     *
     * @param usuario Último usuario logueado.
     */
    private void guardarInicioDeSesion(Usuario usuario) {
        try {
            Properties propiedades = new Properties();
            propiedades.put("Usuario", usuario.getUsuario());
            propiedades.put("Contrasena", usuario.getContrasena());
            propiedades.put("NivelUsuario", String.valueOf(usuario.getTipoUsuario()));
            try {
                propiedades.store(new FileWriter("data/account.conf"), "Coffe Time");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
