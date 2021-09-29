package coffeetime.gui.principal;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Submenú. Ventana dedicada a la creación, eliminación, modificación y visualización
 * de Cafés, Lotes y Fabricantes, así como la representación de gráficas para cada uno de dichos elementos.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class Submenu extends JDialog {
    public static final int TYPE_CAFES = 0;
    public static final int TYPE_LOTES = 1;
    public static final int TYPE_FABRICANTES = 2;
    final int tipo;
    private final ResourceBundle idioma;
    JPanel contentPane;
    JPanel pnListado;
    JPanel pnEstadisticas;
    JPanel pnInforme;
    JTabbedPane tabbedPane;
    JButton btnAnadir;
    JButton btnModificar;
    JButton btnEliminar;
    JButton btnMostrarInfoAdicional;
    JButton btnCambiarInforme;
    JTextField txtFiltro;
    JComboBox<String> cbFiltrado;
    JList listaElementos;
    DefaultListModel dlm;

    /**
     * Constructor.
     *
     * @param tipo Tipo de elemento a gestionar.
     */
    public Submenu(int tipo) {
        this.tipo = tipo;
        idioma = ResourceBundle.getBundle("idioma");
        setContentPane(contentPane);
        initComponents();
        internacionalizar();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        this.getRootPane().setDefaultButton(btnAnadir);
        btnAnadir.requestFocus();
    }

    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        if (tipo == TYPE_CAFES) {
            setTitle(idioma.getString("submenu.cafes"));
        } else if (tipo == TYPE_LOTES) {
            setTitle(idioma.getString("submenu.lotes"));
            btnCambiarInforme.setVisible(true);
        } else {
            setTitle(idioma.getString("submenu.fabricantes"));
        }

        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        rellenarFiltros();

    }

    /**
     * Establece los filtros aplicables en función del elemento gestionado.
     */
    private void rellenarFiltros() {
        if (tipo == TYPE_CAFES) {
            rellenarFiltrosCafe();
        } else if (tipo == TYPE_LOTES) {
            rellenarFiltrosLote();
        } else {
            rellenarFiltrosFabricante();
        }
    }

    /**
     * Establece los filtros disponibles para aplicar en la gestión de cafés.
     */
    private void rellenarFiltrosCafe() {
        cbFiltrado.addItem(idioma.getString("general.nombre"));
        cbFiltrado.addItem(idioma.getString("general.arabico"));
        cbFiltrado.addItem(idioma.getString("general.robusta"));
    }

    /**
     * Establece los filtros disponibles para aplicar en la gestión de lotes.
     */
    private void rellenarFiltrosLote() {
        cbFiltrado.addItem(idioma.getString("general.unidades"));
        cbFiltrado.addItem(idioma.getString("general.coste"));
        cbFiltrado.addItem(idioma.getString("general.fabricante"));
    }

    /**
     * Establece los filtros disponibles para aplicar en la gestión de fabricantes.
     */
    private void rellenarFiltrosFabricante() {
        cbFiltrado.addItem(idioma.getString("general.nombre"));
        cbFiltrado.addItem(idioma.getString("general.direccion"));
        cbFiltrado.addItem(idioma.getString("general.trabajadores"));
    }

    /**
     * Internacionaliza los textos de la ventana.
     */
    private void internacionalizar() {
        tabbedPane.setToolTipTextAt(0, idioma.getString("ver.Listado"));
        if (tipo != TYPE_CAFES) {
            tabbedPane.setToolTipTextAt(1, idioma.getString("ver.Estadisticas"));
        }
    }

    /**
     * Establece las propiedades de la lista en la que se muestran los elementos en función del elemento a gestionar.
     */
    private void createUIComponents() {
        if (tipo == TYPE_CAFES) {
            listaElementos = new JList<Cafe>();
            dlm = new DefaultListModel<Cafe>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.CAFES));
        } else if (tipo == TYPE_LOTES) {
            listaElementos = new JList<Lote>();
            dlm = new DefaultListModel<Lote>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.LOTES));
        } else {
            listaElementos = new JList<Fabricante>();
            dlm = new DefaultListModel<Fabricante>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.FABRICANTES));
        }

    }
}
