import coffeetime.gui.login.ControladorLogin;
import coffeetime.gui.otros.PantallaDeCarga;
import coffeetime.gui.principal.ControladorMenuPrincipal;
import coffeetime.gui.principal.MenuPrincipal;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Coffee Time. Aplicación diseñada para la gestión y relación de cafés, lotes y
 * sus fabricantes. Permite la insercción, eliminación, modificación, relación y
 * visualización de dichos elementos. Para una mayor productividad permite a su
 * vez guardar y cargar elementos, así como modificar el aspecto visual de la
 * interfaz.
 *
 * @author Iván García Prieto
 * @version 14.01.2021
 */
public class Launcher {

    /**
     * Constructor.
     */
    private Launcher() {
        Util.crearDirectorioData();
        cargarPreferencias();
        new ControladorLogin();

        Thread pantallaDeCarga = new Thread(new PantallaDeCarga());
        pantallaDeCarga.start();

        try {
            pantallaDeCarga.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ControladorMenuPrincipal(new MenuPrincipal(), new Modelo());
    }

    /**
     * Constructor. Inicializa la aplicación. Tras un inicio de sesión satisfactorio
     * muestra la ventana principal desde la cual se accede a todas las
     * funcionalidades de la aplicación.
     *
     * @param args Argumentos de inicialización.
     */
    public static void main(String[] args) {
        new Launcher();
    }

    /**
     * Cambia el tamaño de letra de todos los componentes de la aplicación.
     *
     * @param tamano Tamaño de letra.
     */
    public static void cambiarTamanoFuente(int tamano) {
        String[] componentes = {"Label.font", "Button.font", "ToggleButton.font", "RadioButton.font",
                "CheckBox.font", "ComboBox.font", "List.font", "MenuBar.font", "MenuItem.font",
                "RadioButtonMenuItem.font", "CheckBoxMenuItem.font", "Menu.font", "PopupMenu.font",
                "OptionPane.font", "Panel.font", "ProgressBar.font", "ScrollPane.font", "Viewport.font",
                "TabbedPane.font", "Table.font", "TableHeader.font", "TextField.font", "PasswordField.font",
                "TextArea.font", "TextPane.font", "EditorPane.font", "TitledBorder.font", "ToolBar.font",
                "ToolTip.font", "Tree.font", "Spinner.font"};

        for (String componente : componentes) {
            UIManager.put(componente, new FontUIResource(new Font(null, Font.PLAIN, tamano)));
        }

    }

    /**
     * Si existen, carga las preferencias establecidas por el usuario.
     * Si no existen, carga unas preferencias por defecto.
     */
    private void cargarPreferencias() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));

            int tamanoFuente = Integer.parseInt(properties.getProperty("TamanoFuente"));
            cambiarTamanoFuente(tamanoFuente);

            String idioma = properties.getProperty("Idioma");
            switch (idioma) {
                case "EN":
                    Locale.setDefault(new Locale("EN"));
                    break;

                case "FR":
                    Locale.setDefault(new Locale("FR"));
                    break;

                default:
                    Locale.setDefault(new Locale("ES"));
                    break;
            }

            if (properties.getProperty("Tema").equals("claro")) {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            }

        } catch (IOException | UnsupportedLookAndFeelException e) {
            cambiarTamanoFuente(12);
            Locale.setDefault(new Locale("ES"));
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                unsupportedLookAndFeelException.printStackTrace();
            }
        }

    }
}
