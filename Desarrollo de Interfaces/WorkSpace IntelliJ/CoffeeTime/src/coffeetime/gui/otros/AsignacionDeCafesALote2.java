package coffeetime.gui.otros;

import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;

public class AsignacionDeCafesALote2 extends JDialog {

    JList<Lote> listLotesSinAsignar;
    JList<Lote> listLotesAsignados;
    JButton btnAsignarLote;
    JButton btnEliminarLote;
    DefaultListModel<Lote> dlm;
    DefaultListModel<Lote> dlm2;
    private JPanel contentPane;


    /**
     * Constructor.
     */
    public AsignacionDeCafesALote2() {
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
        contentPane.getRootPane().setDefaultButton(btnAsignarLote);
        btnAsignarLote.requestFocus();

        dlm = new DefaultListModel<>();
        listLotesSinAsignar.setModel(dlm);
        listLotesSinAsignar.setCellRenderer(new Renderer(coffeetime.componentes.Renderer.CAFES));

        dlm2 = new DefaultListModel<>();
        listLotesAsignados.setModel(dlm2);
        listLotesAsignados.setCellRenderer(new Renderer(Renderer.CAFES));
    }
}
