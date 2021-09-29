package coffeetime.gui.visualizado;

import coffeetime.base.Cafe;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;
import coffeetime.util.Util;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Ver Cafés. Ventana dedicada a la visualización de todos los cafés que pertenecen a un determinado lote.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class VerCafes extends JDialog {
    private ResourceBundle idioma;
    private JPanel contentPane;
    private JList<Cafe> listaCafes;
    private DefaultListModel<Cafe> dlm;


    /**
     * Constructor.
     *
     * @param lote Lote al que pertenecen los cafés.
     */
    public VerCafes(Lote lote) {
        initComponents();
        rellenarLista(lote);
    }


    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);

        idioma = Util.obtenerTraducciones();
        setTitle(idioma.getString("submenu.cafes"));

        dlm = new DefaultListModel<>();
        listaCafes.setModel(dlm);
        listaCafes.setCellRenderer(new Renderer(Renderer.CAFES));

    }

    /**
     * Rellena la lista con todos los cafés que pertenecen al lote.
     *
     * @param lote Lote al que pertenecen los cafés.
     */
    private void rellenarLista(Lote lote) {
        for (Cafe cafe : lote.getCafes()) {
            dlm.addElement(cafe);
        }
    }

}
