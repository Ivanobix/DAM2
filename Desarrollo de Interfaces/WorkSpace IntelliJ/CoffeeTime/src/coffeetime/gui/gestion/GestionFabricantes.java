package coffeetime.gui.gestion;

import coffeetime.util.Util;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Gestión Fabricante. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionFabricantes extends JDialog {
    private final ResourceBundle idioma;
    JPanel contentPane;
    JTextField txtNombre;
    JTextField txtDireccion;
    JTextField txtTrabajadores;
    DatePicker dpFechaAlta;
    JCheckBox chbxInternacional;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnGestionarLotes;
    JLabel lblLotesFabricados;

    /**
     * Constructor.
     */
    public GestionFabricantes() {
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
        setTitle(idioma.getString("ver.fabricante"));

        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();
    }

}
