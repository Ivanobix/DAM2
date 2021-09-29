package coffeetime.gui.otros;

import coffeetime.base.Cafe;
import coffeetime.componentes.Renderer;

import javax.swing.*;

/**
 * AsignacionDeCafesALote. Ventana dedicada a la asignación de lotes a un determinado fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class AsignacionDeCafesALote extends JDialog {

    JList<Cafe> listCafesSinAsignar;
    JList<Cafe> listCafesAsignados;
    JButton btnAsignarCafe;
    JButton btnEliminarCafe;
    DefaultListModel<Cafe> dlm;
    DefaultListModel<Cafe> dlm2;
    private JPanel contentPane;


    /**
     * Constructor.
     */
    public AsignacionDeCafesALote() {
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
        contentPane.getRootPane().setDefaultButton(btnAsignarCafe);
        btnAsignarCafe.requestFocus();

        dlm = new DefaultListModel<>();
        listCafesSinAsignar.setModel(dlm);
        listCafesSinAsignar.setCellRenderer(new Renderer(Renderer.CAFES));

        dlm2 = new DefaultListModel<>();
        listCafesAsignados.setModel(dlm2);
        listCafesAsignados.setCellRenderer(new Renderer(Renderer.CAFES));
    }

}
