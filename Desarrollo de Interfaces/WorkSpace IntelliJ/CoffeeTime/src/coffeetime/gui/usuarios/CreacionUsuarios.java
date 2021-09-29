package coffeetime.gui.usuarios;

import javax.swing.*;

/**
 * Creación de Usuarios. Ventana dedicada a la creación de usuarios.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class CreacionUsuarios extends JDialog {
    JButton btnAceptar;
    JButton btnCancelar;
    JPasswordField txtContrasena;
    JTextField txtUsuario;
    JRadioButton rbAdmin;
    JRadioButton rbNormal;
    JRadioButton rbInvitado;
    private JPanel contentPane;

    /**
     * Constructor.
     */
    public CreacionUsuarios() {
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
