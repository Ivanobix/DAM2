package coffeetime.gui.principal;

import coffeetime.util.Util;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Menú Principal. Ventana principal de la aplicación que permite el acceso al resto de funcionalidades de la misma.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class MenuPrincipal extends Component {
    public static final Color TEMA_OSCURO = new Color(30, 30, 30);
    public static final Color TEMA_CLARO = new Color(255, 255, 255);
    final JFrame frame;
    private final ResourceBundle idioma;
    JPanel pnPrincipal;
    JPanel pnOpciones;
    JPanel pnDatos;
    JButton btnCafes;
    JButton btnFabricantes;
    JButton btnLotes;
    JMenu mnArchivo;
    JMenu mnEditar;
    JMenu mnAyuda;
    JMenuItem mnitNuevo;
    JMenuItem mnitGuardar;
    JMenuItem mnitCargar;
    JMenuItem mnitDeshacer;
    JMenuItem mnitPreferencias;
    JMenuItem mnitCerrarSesion;
    JMenuItem mnitAddUsuarios;
    JMenuItem mnitRemoveUsuarios;
    JMenuItem mnitAyuda;

    /**
     * Constructor.
     */
    public MenuPrincipal() {
        idioma = Util.obtenerTraducciones();
        frame = new JFrame();
        frame.setContentPane(pnPrincipal);
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        frame.setTitle(idioma.getString("general.nombreAplicacion"));
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        frame.getRootPane().setDefaultButton(btnFabricantes);
        initBarraDeHerramientas();
        establecerEstiloBotones();
        establecerIdiomaBotones();

        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));
            if (properties.getProperty("Tema").equals("claro")) {
                establecerTema(TEMA_CLARO);
            } else {
                establecerTema(TEMA_OSCURO);
            }
        } catch (Exception e) {
            establecerTema(TEMA_OSCURO);
        }
    }

    /**
     * Establece el color de fondo de los componentes principales.
     *
     * @param color Color a aplicar.
     */
    private void establecerTema(Color color) {
        pnDatos.setBackground(color);
        btnLotes.setBackground(color);
        btnFabricantes.setBackground(color);
        btnCafes.setBackground(color);

    }

    /**
     * Establece el estilo específicos de los
     */
    private void establecerEstiloBotones() {
        btnCafes.setBorderPainted(false);
        btnFabricantes.setBorderPainted(false);
        btnLotes.setBorderPainted(false);
    }

    /**
     * Cambia el idioma de los botones en función del idioma seleccionado en las preferencias.
     */
    private void establecerIdiomaBotones() {
        if (Locale.getDefault().getLanguage().equals("en")) {
            btnCafes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/cafes_en.png")));
            btnLotes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/lotes_en.png")));
            btnFabricantes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/fabricantes_en.png")));
        } else if (Locale.getDefault().getLanguage().equals("fr")) {
            btnCafes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/cafes_fr.png")));
            btnLotes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/lotes_fr.png")));
            btnFabricantes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/fabricantes_fr.png")));
        }
    }

    /**
     * Inicializa la barra de herramientas y establece sus propiedades principales.
     */
    private void initBarraDeHerramientas() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnArchivo = new JMenu(idioma.getString("menu.archivo"));
        menuBar.add(mnArchivo);

        mnitGuardar = new JMenuItem(idioma.getString("menu.guardar"), new ImageIcon(this.getClass().getResource("/herramientas/guardar.png")));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem(idioma.getString("menu.cargar"), new ImageIcon(this.getClass().getResource("/herramientas/cargar.png")));
        mnitCargar.setActionCommand("mnitCargar");
        mnArchivo.add(mnitCargar);

        mnitNuevo = new JMenuItem(idioma.getString("menu.nuevo"), new ImageIcon(this.getClass().getResource("/herramientas/nuevo.png")));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnArchivo.add(mnitNuevo);

        mnitCerrarSesion = new JMenuItem(idioma.getString("menu.cerrarSesion"), new ImageIcon(this.getClass().getResource("/herramientas/cerrarSesion.png")));
        mnitCerrarSesion.setActionCommand("mnitCerrarSesion");
        mnArchivo.add(mnitCerrarSesion);

        mnEditar = new JMenu(idioma.getString("menu.editar"));
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem(idioma.getString("menu.deshacer"), new ImageIcon(this.getClass().getResource("/herramientas/deshacer.png")));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnEditar.add(mnitDeshacer);

        mnitPreferencias = new JMenuItem(idioma.getString("menu.preferencias"), new ImageIcon(this.getClass().getResource("/herramientas/preferencias.png")));
        mnitPreferencias.setActionCommand("mnitPreferencias");
        mnEditar.add(mnitPreferencias);

        mnitAddUsuarios = new JMenuItem(idioma.getString("menu.usuarios"), new ImageIcon(this.getClass().getResource("/herramientas/anadir_usuario.png")));
        mnitAddUsuarios.setActionCommand("mnitAddUsuarios");
        mnEditar.add(mnitAddUsuarios);

        mnitRemoveUsuarios = new JMenuItem(idioma.getString("menu.usuarios"), new ImageIcon(this.getClass().getResource("/herramientas/eliminar_usuario.png")));
        mnitRemoveUsuarios.setActionCommand("mnitRemoveUsuarios");
        mnEditar.add(mnitRemoveUsuarios);

        mnAyuda = new JMenu(idioma.getString("menu.ayuda"));
        menuBar.add(mnAyuda);

        mnitAyuda = new JMenuItem(idioma.getString("menu.ayuda"), new ImageIcon(this.getClass().getResource("/herramientas/ayuda.png")));
        mnitAyuda.setActionCommand("mnitAyuda");
        mnAyuda.add(mnitAyuda);
    }

}
