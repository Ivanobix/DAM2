package coffeetime.gui.otros;

import coffeetime.gui.principal.ControladorMenuPrincipal;
import coffeetime.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Controlador de Preferencias. Controlador para la ventana de Preferencias
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos, permitiendo de esta forma la configuración de la aplicación.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorPreferencias {

    private final Preferencias preferencias;
    private final ResourceBundle idioma;
    private String guardadoAutomatico;
    private String rutaGuardado;

    /**
     * Constructor.
     *
     * @param preferencias Ventana de preferencias.
     */
    public ControladorPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
        idioma = Util.obtenerTraducciones();
        guardadoAutomatico = "no";
        rutaGuardado = "";
        marcarPreferenciasAnteriores();
        crearAtajos();
        initHandlers();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        preferencias.btnAceptar.addActionListener(e -> {
            guardarPreferencias();
            preferencias.dispose();
        });

        preferencias.btnCancelar.addActionListener(e -> preferencias.dispose());

        preferencias.rbAutoguardadoSi.addActionListener(e -> gestionarAutoGuardado());

        preferencias.rbAutoguardadoNo.addActionListener(e -> gestionarAutoGuardado());
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        preferencias.btnAceptar.setMnemonic(KeyEvent.VK_1);
        preferencias.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * Recoge y guarda todas las preferencias indicadas por el usuario en un archivo externo de configuración.
     */
    private void guardarPreferencias() {
        Properties propiedades = new Properties();

        propiedades.put("TamanoFuente", preferencias.spinFuente.getValue().toString());
        propiedades.put("Idioma", preferencias.cbIdioma.getSelectedItem());

        String tema;
        if (preferencias.rbTemaClaro.isSelected()) tema = "claro";
        else tema = "oscuro";
        propiedades.put("Tema", tema);

        propiedades.put("GuardadoAutomatico", guardadoAutomatico);
        propiedades.put("RutaGuardado", rutaGuardado);

        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Coffe Time");
            Util.mostrarAviso(idioma.getString("aviso.reiniciarAplicacion"));

        } catch (IOException e) {
            Util.mostrarError(idioma.getString("error.guardarPreferencias"));
        }
    }

    /**
     * Rellena los valores de la ventana con aquellas preferencias previamente establecidas por el usuario en caso de existir.
     */
    private void marcarPreferenciasAnteriores() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));

            int tamanoFuente = Integer.parseInt(properties.getProperty("TamanoFuente"));
            preferencias.spinFuente.setValue(tamanoFuente);

            String idioma = properties.getProperty("Idioma");
            preferencias.cbIdioma.setSelectedItem(idioma);

            if (properties.getProperty("Tema").equals("claro")) {
                preferencias.rbTemaClaro.setSelected(true);
            }

            if (properties.getProperty("GuardadoAutomatico").equals("si")) {
                preferencias.rbAutoguardadoSi.setSelected(true);
            }

        } catch (IOException e) {
            guardarPreferencias();
        }
    }

    /**
     * Activa o desactiva la función de autoguardado de la aplicación.
     * En caso de activación mostrará un diálogo para la selección de una ruta de guardado por defecto.
     */
    private void gestionarAutoGuardado() {
        if (preferencias.rbAutoguardadoSi.isSelected()) {
            JFileChooser selector = new JFileChooser();
            selector.setAcceptAllFileFilterUsed(false);
            selector.setFileFilter(new FileNameExtensionFilter(ControladorMenuPrincipal.EXTENSION_FICHEROS, ControladorMenuPrincipal.NOMBRE_EXTENSION_FICHEROS));
            int seleccion = selector.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = new File(selector.getSelectedFile().getAbsolutePath());
                guardadoAutomatico = "si";
                rutaGuardado = fichero.getAbsolutePath();
            } else {
                preferencias.rbAutoguardadoNo.setSelected(true);
            }

        } else {
            guardadoAutomatico = "no";
            rutaGuardado = "";
        }


    }
}
