package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.util.Util;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Gestión Lotes. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Lote.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionLotes extends JDialog {
    private final ResourceBundle idioma;
    JPanel contentPane;
    JTextField txtUnidades;
    JTextField txtCoste;
    DatePicker dpCaducidad;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnGestionarCafes;
    JComboBox<Fabricante> cbFabricante;
    DatePicker dpEnvasado;
    JLabel lblCafes;
    DefaultComboBoxModel<Fabricante> dcbm;

    /**
     * Constructor.
     */
    public GestionLotes() {
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
        setTitle(idioma.getString("ver.lote"));

        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();

        dcbm = new DefaultComboBoxModel<>();
        cbFabricante.setModel(dcbm);
    }
}
