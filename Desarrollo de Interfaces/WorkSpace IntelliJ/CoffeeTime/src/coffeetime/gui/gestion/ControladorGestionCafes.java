package coffeetime.gui.gestion;

import coffeetime.base.Cafe;
import coffeetime.gui.otros.AsignacionDeCafesALote2;
import coffeetime.gui.otros.ControladorAsignacionDeCafesALote2;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

/**
 * Controlador Gestión Café. Controlador para la ventana de Gestión de Café
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos e insercción de los datos en cada campo de texto (en el caso de
 * modificación).
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorGestionCafes implements ActionListener {

    private final GestionCafes ventanaGestionCafe;
    private final Modelo modelo;
    private final boolean modificando;
    private final Cafe cafeAModificar;
    private ResourceBundle idioma;

    /**
     * Constructor para crear un nuevo Café.
     *
     * @param modelo             Modelo de la aplicación.
     * @param ventanaGestionCafe Ventana de Gestión de Café a controlar.
     */
    public ControladorGestionCafes(GestionCafes ventanaGestionCafe, Modelo modelo) {
        this.ventanaGestionCafe = ventanaGestionCafe;
        this.modelo = modelo;
        modificando = false;
        cafeAModificar = null;
        idioma = Util.obtenerTraducciones();
        crearAtajos();
        initHandlers();

    }

    /**
     * Constructor para modificar un Café.
     *
     * @param modelo             Modelo de la aplicación.
     * @param ventanaGestionCafe Ventana de Gestión de Café a controlar.
     * @param cafe               Café a modificar.
     */
    public ControladorGestionCafes(GestionCafes ventanaGestionCafe, Modelo modelo, Cafe cafe) {
        this.ventanaGestionCafe = ventanaGestionCafe;
        this.modelo = modelo;
        modificando = true;
        cafeAModificar = cafe;
        crearAtajos();
        initHandlers();
        rellenarDatos();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaGestionCafe.btnSeleccionarImagen.addActionListener(this);
        ventanaGestionCafe.btnGestionar.addActionListener(this);
        ventanaGestionCafe.btnCancelar.addActionListener(this);
        ventanaGestionCafe.btnAsignarLotesACafe.addActionListener(this);
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        ventanaGestionCafe.btnGestionar.setMnemonic(KeyEvent.VK_1);
        ventanaGestionCafe.btnCancelar.setMnemonic(KeyEvent.VK_2);
        ventanaGestionCafe.btnSeleccionarImagen.setMnemonic(KeyEvent.VK_3);
        ventanaGestionCafe.btnAsignarLotesACafe.setMnemonic(KeyEvent.VK_4);
    }

    /**
     * Rellena los datos de todos los campos en función del Café a modificar.
     */
    private void rellenarDatos() {
        ventanaGestionCafe.txtNombre.setText(cafeAModificar.getNombre());
        ventanaGestionCafe.txtArabico.setText(String.valueOf(cafeAModificar.getPorcentajeArabico()));
        ventanaGestionCafe.txtRobusta.setText(String.valueOf(cafeAModificar.getPorcentajeRobusta()));
        ventanaGestionCafe.txtRutaImagen.setText(cafeAModificar.getImagenPromocional());

        ImageIcon iconoOriginal = new ImageIcon(ventanaGestionCafe.txtRutaImagen.getText());
        ventanaGestionCafe.imgPromocional.setIcon(Util.escalarImagen(iconoOriginal, 70, 70));

        ventanaGestionCafe.btnAsignarLotesACafe.setVisible(true);
        ventanaGestionCafe.lblLotes.setVisible(true);
    }


    /**
     * Permite al usuario seleccionar una imagen de su equipo.
     */
    private void selecionarImagen() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("JPG & PNG", "jpg", "png"));
        int opcion = selector.showOpenDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File fichero = selector.getSelectedFile();
            String ruta = fichero.getAbsolutePath();
            ImageIcon iconoAMostrar = Util.escalarImagen(new ImageIcon(ruta), 70, 70);
            ventanaGestionCafe.imgPromocional.setIcon(iconoAMostrar);
            ventanaGestionCafe.txtRutaImagen.setText(ruta);
        }
    }

    /**
     * Gestiona el Café generado tras la comprobación de datos.
     */
    private void gestionarCafe() {
        Cafe cafe = comprobarDatos();
        if (cafe != null) {
            if (modificando) {
                modelo.modificarCafe(cafeAModificar, cafe.getNombre(), cafe.getImagenPromocional(),
                        cafe.getPorcentajeArabico(), cafe.getPorcentajeRobusta());
            } else {
                modelo.anadirCafe(cafe);
            }
            ventanaGestionCafe.dispose();

        }
    }

    /**
     * Comprobación de todos los datos introducidos para la creación o modificación
     * de un Café.
     *
     * @return Café generado a partir de los datos introducidos en la ventana.
     */
    private Cafe comprobarDatos() {
        Cafe cafe = null;
        if (comprobarDouble(ventanaGestionCafe.txtArabico.getText())
                && comprobarDouble(ventanaGestionCafe.txtRobusta.getText())) {
            if (ventanaGestionCafe.txtNombre.getText().replace(" ", "").length() != 0) {
                if (!ventanaGestionCafe.txtRutaImagen.getText().equals("")) {
                    double arabico = Double.parseDouble(ventanaGestionCafe.txtArabico.getText());
                    double robusta = Double.parseDouble(ventanaGestionCafe.txtRobusta.getText());
                    if (arabico + robusta <= 100 && arabico + robusta >= 0) {
                        String nombre = ventanaGestionCafe.txtNombre.getText();
                        String rutaImagen = generarNuevaRutaImagen();
                        cafe = new Cafe(nombre, rutaImagen, arabico, robusta);

                    } else {
                        Util.mostrarError(idioma.getString("error.mezclaNoCorrecta"));
                    }
                } else {
                    Util.mostrarError(idioma.getString("error.imagenNoSeleccionada"));
                }
            } else {
                Util.mostrarError(idioma.getString("error.nombreVacio"));
            }
        } else {
            Util.mostrarError(idioma.getString("error.formatoMezcla"));
        }
        return cafe;
    }

    /**
     * Comprobación para saber si un String es un Double.
     *
     * @param aComprobar String a comprobar.
     * @return Resultado de la comprobación.
     */
    private boolean comprobarDouble(String aComprobar) {
        boolean aDevolver = true;
        try {
            Double.parseDouble(aComprobar);
        } catch (NumberFormatException nfe) {
            aDevolver = false;
        }
        return aDevolver;
    }

    /**
     * Crea una copia de la imagen seleccionada y genera la nueva ruta.
     *
     * @return Ruta de la copia.
     */
    private String generarNuevaRutaImagen() {
        String rutaDestino = "";
        try {
            File origen = new File(ventanaGestionCafe.txtRutaImagen.getText());
            rutaDestino = "img/" + origen.getName();
            Util.crearDirectorioImagenes();
            File destino = new File(rutaDestino);
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ignored) {

        }
        return rutaDestino;

    }

    /**
     * Procedimientos a seguir en caso de que un botón haya sido pulsado.
     *
     * @param e Evento de acción creado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnSeleccionar":
                selecionarImagen();
                break;
            case "btnCancelar":
                ventanaGestionCafe.dispose();
                break;
            case "btnGestionar":
                gestionarCafe();
                break;
            case "btnAsignarLotesACafe":
                new ControladorAsignacionDeCafesALote2(new AsignacionDeCafesALote2(), cafeAModificar, modelo.getLotes());
                ventanaGestionCafe.dispose();
                break;
        }
    }
}
