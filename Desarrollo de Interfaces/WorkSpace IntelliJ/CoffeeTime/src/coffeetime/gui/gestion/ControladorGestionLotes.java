package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.gui.otros.AsignacionDeCafesALote;
import coffeetime.gui.otros.ControladorAsignacionDeCafesALote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador Gestión Lote. Controlador para la ventana de Gestión de Lote
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos e insercción de los datos en cada campo de texto (en el caso de
 * modificación).
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorGestionLotes implements ActionListener {

    private final GestionLotes ventanaGestionLotes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Lote loteAModificar;
    private ResourceBundle idioma;

    /**
     * Constructor para crear un nuevo Lote.
     *
     * @param modelo              Modelo de la aplicación.
     * @param ventanaGestionLotes Ventana de Gestión de Lote a controlar.
     */
    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = false;
        loteAModificar = null;
        idioma = Util.obtenerTraducciones();
        crearAtajos();
        initHandlers();
        cargarFabricantes();

        ventanaGestionLotes.dpCaducidad.setDate(LocalDate.now());
        ventanaGestionLotes.dpEnvasado.setDate(LocalDate.now());
    }

    /**
     * Constructor para modificar un Lote.
     *
     * @param modelo              Modelo de la aplicación.
     * @param ventanaGestionLotes Ventana de Gestión de Lote a controlar.
     * @param lote                Lote a modificar.
     */
    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo, Lote lote) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = true;
        loteAModificar = lote;
        crearAtajos();
        initHandlers();
        cargarFabricantes();
        rellenarDatos();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaGestionLotes.btnGestionar.addActionListener(this);
        ventanaGestionLotes.btnCancelar.addActionListener(this);
        ventanaGestionLotes.btnGestionarCafes.addActionListener(this);
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        ventanaGestionLotes.btnGestionar.setMnemonic(KeyEvent.VK_1);
        ventanaGestionLotes.btnCancelar.setMnemonic(KeyEvent.VK_2);
        ventanaGestionLotes.btnGestionarCafes.setMnemonic(KeyEvent.VK_3);
    }

    /**
     * Rellena los datos de todos los campos en función del Lote a modificar.
     */
    private void rellenarDatos() {
        ventanaGestionLotes.txtCoste.setText(String.valueOf(loteAModificar.getCosteTotal()));
        ventanaGestionLotes.txtUnidades.setText(String.valueOf(loteAModificar.getNumeroUnidades()));
        ventanaGestionLotes.dpEnvasado.setDate(loteAModificar.getFechaDeEnvasado());
        ventanaGestionLotes.dpCaducidad.setDate(loteAModificar.getFechaDeCaducidad());

        ventanaGestionLotes.cbFabricante.setSelectedItem(loteAModificar.getFabricante());

        ventanaGestionLotes.btnGestionarCafes.setVisible(true);
        ventanaGestionLotes.lblCafes.setVisible(true);
    }

    /**
     * Añade al desplegable todos los Fabricantes existentes.
     */
    private void cargarFabricantes() {
        ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
        for (Fabricante fabricante : fabricantes) {
            ventanaGestionLotes.dcbm.addElement(fabricante);
        }
    }

    /**
     * Gestiona el Lote generado tras la comprobación de datos.
     */
    private void gestionarLote() {
        Lote lote = comprobarDatos();
        if (lote != null) {
            if (modificando) {
                modelo.modificarLote(loteAModificar, lote.getNumeroUnidades(), lote.getCosteTotal(),
                        lote.getFechaDeEnvasado(), lote.getFechaDeCaducidad(), lote.getFabricante());
            } else {
                modelo.anadirLote(lote);
            }
            ventanaGestionLotes.dispose();

        }
    }

    /**
     * Comprobación de todos los datos introducidos para la creación o modificación
     * de un Lote.
     *
     * @return Lote generado a partir de los datos introducidos en la ventana.
     */
    private Lote comprobarDatos() {
        Lote lote = null;
        if (comprobarInt(ventanaGestionLotes.txtUnidades.getText())) {
            if (comprobarDouble(ventanaGestionLotes.txtCoste.getText())) {
                if (ventanaGestionLotes.cbFabricante.getSelectedItem() != null) {
                    if (ventanaGestionLotes.dpEnvasado.getDate() != null) {
                        if (ventanaGestionLotes.dpCaducidad.getDate() != null) {
                            double coste = Double.parseDouble(ventanaGestionLotes.txtCoste.getText());
                            if (coste > 0) {
                                int unidades = Integer.parseInt(ventanaGestionLotes.txtUnidades.getText());
                                if (unidades > 0) {
                                    LocalDate envasado = ventanaGestionLotes.dpEnvasado.getDate();
                                    LocalDate caducidad = ventanaGestionLotes.dpCaducidad.getDate();
                                    if (envasado.isBefore(caducidad)) {
                                        Fabricante fabricante = (Fabricante) ventanaGestionLotes.cbFabricante.getSelectedItem();
                                        lote = new Lote(unidades, coste, envasado, caducidad, fabricante);
                                    } else {
                                        Util.mostrarError(idioma.getString("error.caducidadAntesDeEnvasado"));
                                    }
                                } else {
                                    Util.mostrarError(idioma.getString("error.unidadesMenorQueCero"));
                                }
                            } else {
                                Util.mostrarError(idioma.getString("error.costeMenorQueCero"));
                            }
                        } else {
                            Util.mostrarError(idioma.getString("error.faltaCaducidad"));
                        }

                    } else {
                        Util.mostrarError(idioma.getString("error.faltaEnvasado"));
                    }
                } else {
                    Util.mostrarError(idioma.getString("error.fabricante"));
                }
            } else {
                Util.mostrarError(idioma.getString("error.coste"));
            }
        } else {
            Util.mostrarError(idioma.getString("error.unidades"));
        }
        return lote;
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
     * Comprobación para saber si un String es un Int.
     *
     * @param aComprobar String a comprobar.
     * @return Resultado de la comprobación.
     */
    private boolean comprobarInt(String aComprobar) {
        boolean aDevolver = true;
        try {
            Integer.parseInt(aComprobar);
        } catch (NumberFormatException nfe) {
            aDevolver = false;
        }
        return aDevolver;
    }

    /**
     * Procedimientos a seguir en caso de que un botón haya sido pulsado.
     *
     * @param e Evento de acción creado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnGestionar":
                gestionarLote();
                break;
            case "btnCancelar":
                ventanaGestionLotes.dispose();
                break;

            case "btnGestionarCafes":
                new ControladorAsignacionDeCafesALote(new AsignacionDeCafesALote(), loteAModificar, modelo.getCafes());
                ventanaGestionLotes.dispose();
                break;
        }
    }
}
