package coffeetime.componentes;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Properties;
import java.util.ResourceBundle;

import static coffeetime.gui.principal.MenuPrincipal.TEMA_CLARO;
import static coffeetime.gui.principal.MenuPrincipal.TEMA_OSCURO;

/**
 * Renderer. Clase utilizada para el renderizado de elementos tipo "Café", "Lote" y "Fabricante"
 * para su posterior visualizado en diferentes componentes tales como JList.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class Renderer implements ListCellRenderer {

    public static final int CAFES = Color.CYAN.getRGB();
    public static final int LOTES = Color.GREEN.getRGB();
    public static final int FABRICANTES = Color.RED.getRGB();

    private final ResourceBundle idioma;

    private JLabel lblIdentificador;
    private JLabel lblTituloUno;
    private JLabel lblTituloDos;
    private JLabel lblInfoUno;
    private JLabel lblInfoDos;
    private JPanel renderer;
    private JSeparator separadorInferior;
    private JSeparator separadorSuperior;
    private JPanel pnOeste;
    private JPanel pnEste;
    private JPanel pnNorte;
    private JPanel pnCentral;

    /**
     * Constructor.
     *
     * @param tipo Tipo de elemento a mostrar en el Renderer.
     */
    public Renderer(int tipo) {
        idioma = Util.obtenerTraducciones();
        Color color = new Color(tipo);
        separadorSuperior.setForeground(color);
        separadorInferior.setForeground(color);
    }

    /**
     * Obtener el componente asociado a esta clase para su posterior implementación
     * en una lista.
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Cafe) {
            rellenarDatosCafe((Cafe) value);
        } else if (value instanceof Lote) {
            rellenarDatosLote((Lote) value);
        } else {
            rellenarDatosFabricante((Fabricante) value);
        }

        if (isSelected) {
            resaltar();

        } else {
            cargarTema();
        }

        return renderer;
    }

    /**
     * Rellena los datos mostrados en el Renderer para aquellos objetos de tipo "Café".
     *
     * @param cafe Café a mostrar.
     */
    private void rellenarDatosCafe(Cafe cafe) {
        lblIdentificador.setText(cafe.getIdentificador());

        lblTituloUno.setText("");
        lblTituloUno.setVisible(false);
        lblInfoUno.setText("");
        lblInfoUno.setIcon(Util.escalarImagen(new ImageIcon(cafe.getImagenPromocional()), 60, 60));

        lblTituloDos.setText(idioma.getString("renderer.nombre"));
        lblInfoDos.setText(cafe.getNombre());

    }

    /**
     * Rellena los datos mostrados en el Renderer para aquellos objetos de tipo "Lote".
     *
     * @param lote Lote a mostrar.
     */
    private void rellenarDatosLote(Lote lote) {
        lblIdentificador.setText(lote.getIdentificador());

        lblTituloUno.setText(idioma.getString("renderer.unidades"));
        lblInfoUno.setText(String.valueOf(lote.getNumeroUnidades()));

        lblTituloDos.setText(idioma.getString("renderer.coste"));
        lblInfoDos.setText(String.valueOf(lote.getCosteTotal()));

    }

    /**
     * Rellena los datos mostrados en el Renderer para aquellos objetos de tipo "Fabricante".
     *
     * @param fabricante Fabricante a mostrar.
     */
    private void rellenarDatosFabricante(Fabricante fabricante) {
        lblIdentificador.setText(fabricante.getIdentificador());

        lblTituloUno.setText(idioma.getString("renderer.nombre"));
        lblInfoUno.setText(fabricante.getNombre());

        lblTituloDos.setText(idioma.getString("renderer.direccion"));
        lblInfoDos.setText(fabricante.getDireccion());

    }

    /**
     * Establece el color del Renderer cuando este ha sido seleccionado.
     */
    private void resaltar() {
        Color color = pnCentral.getBackground().darker().darker();
        pnEste.setBackground(color);
        pnOeste.setBackground(color);
        pnNorte.setBackground(color);
        pnCentral.setBackground(color);
    }


    /**
     * Carga el tema seleccionado por el usuario, y en caso de no existir establece uno por defecto.
     */
    private void cargarTema() {
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
     * @param tema Color a aplicar.
     */
    private void establecerTema(Color tema) {
        pnEste.setBackground(tema);
        pnOeste.setBackground(tema);
        pnNorte.setBackground(tema);
        pnCentral.setBackground(tema);
        renderer.setBackground(tema);
    }
}
