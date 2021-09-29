package coffeetime.gui.gestion;

import coffeetime.util.Util;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Gestión Café. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Café.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionCafes extends JDialog {
    private final ResourceBundle idioma;
    JPanel contentPane;
    JTextField txtNombre;
    JTextField txtArabico;
    JTextField txtRobusta;
    JTextField txtRutaImagen;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnSeleccionarImagen;
    JLabel imgPromocional;
    JButton btnAsignarLotesACafe;
    JLabel lblLotes;

    /**
     * Constructor.
     */
    public GestionCafes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        idioma = Util.obtenerTraducciones();
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
        setTitle(idioma.getString("ver.cafe"));

        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();
    }

}
