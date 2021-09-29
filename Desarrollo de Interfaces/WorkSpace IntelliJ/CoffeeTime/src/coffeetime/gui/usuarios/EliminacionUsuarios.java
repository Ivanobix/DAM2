package coffeetime.gui.usuarios;

import coffeetime.base.Usuario;

import javax.swing.*;

/**
 * Eliminación de Usuarios. Ventana dedicada a la eliminación de usuarios.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class EliminacionUsuarios extends JDialog {
    JButton btnAceptar;
    JButton btnCancelar;
    JComboBox<Usuario> cbUsuario;
    private JPanel contentPane;

    /**
     * Constructor.
     */
    public EliminacionUsuarios() {
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
        btnAceptar.requestFocus();
    }

}
