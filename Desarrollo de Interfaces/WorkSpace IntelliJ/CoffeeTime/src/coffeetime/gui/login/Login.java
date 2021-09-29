package coffeetime.gui.login;

import javax.swing.*;

/**
 * Login. Ventana dedicada al control de acceso a la aplicación.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class Login extends JDialog {
    JPasswordField txtContrasena;
    JTextField txtUsuario;
    JButton btnAceptar;
    JButton btnCancelar;
    private JPanel contentPane;


    /**
     * Constructor.
     */
    public Login() {
        setContentPane(contentPane);
        setUndecorated(true);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setModal(true);
    }

    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        txtUsuario.requestFocus();
    }
}
