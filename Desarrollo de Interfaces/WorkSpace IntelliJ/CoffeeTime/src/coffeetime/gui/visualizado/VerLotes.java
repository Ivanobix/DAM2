package coffeetime.gui.visualizado;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;
import coffeetime.util.Util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Ver Lotes. Ventana dedicada a la visualización de todos los lotes a los que contienen
 * un café o los que pertenecen a un fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class VerLotes extends JDialog {
    private ResourceBundle idioma;
    private JPanel contentPane;
    private JList<Lote> listaLotes;
    private DefaultListModel<Lote> dlm;
    private final ArrayList<Lote> lotes;

    /**
     * Constructor.
     *
     * @param cafe  Café a buscar.
     * @param lotes Lista de todos los lotes.
     */
    public VerLotes(Cafe cafe, ArrayList<Lote> lotes) {
        this.lotes = lotes;
        initComponents();
        rellenarLista(cafe);
    }

    /**
     * Cosntructor.
     *
     * @param fabricante Fabricante a buscar.
     * @param lotes      Lista de lotes que pertenecen al fabricante.
     */
    public VerLotes(Fabricante fabricante, ArrayList<Lote> lotes) {
        this.lotes = lotes;
        initComponents();
        rellenarLista(fabricante);
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
        setTitle(idioma.getString("submenu.lotes"));

        dlm = new DefaultListModel<>();
        listaLotes.setModel(dlm);
        listaLotes.setCellRenderer(new Renderer(Renderer.LOTES));

    }

    /**
     * Rellena la lista con todos lotes que contienen un café.
     *
     * @param cafe Café contenido en los lotes.
     */
    private void rellenarLista(Cafe cafe) {
        for (Lote lote : lotes) {
            if (lote.getCafes().contains(cafe)) {
                dlm.addElement(lote);
            }
        }
    }

    /**
     * Rellena la lista con todos los lotes que pertenecen al fabricante.
     *
     * @param fabricante Fabricante al que pertenecen los lotes.
     */
    private void rellenarLista(Fabricante fabricante) {
        for (Lote lote : lotes) {
            if (lote.getFabricante().equals(fabricante)) {
                dlm.addElement(lote);
            }
        }
    }
}
