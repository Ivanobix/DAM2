package coffeetime.gui.otros;

import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;

/**
 * AsignacionDeLotesAFabricante. Ventana dedicada a la asignación de lotes a un determinado fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class AsignacionDeLotesAFabricante extends JDialog {

    JList<Lote> listLotesDeOtrosFabricantes;
    JButton btnCambiarFabricante;
    DefaultListModel<Lote> dlm;
    private JPanel contentPane;

    /**
     * Constructor.
     */
    public AsignacionDeLotesAFabricante() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        contentPane.getRootPane().setDefaultButton(btnCambiarFabricante);
        btnCambiarFabricante.requestFocus();

        dlm = new DefaultListModel<>();
        listLotesDeOtrosFabricantes.setModel(dlm);
        listLotesDeOtrosFabricantes.setCellRenderer(new Renderer(Renderer.LOTES));
    }

}
