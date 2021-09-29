package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.gui.otros.AsignacionDeLotesAFabricante;
import coffeetime.gui.otros.ControladorAsignacionDeLotesAFabricantes;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controlador Gestión Fabricante. Controlador para la ventana de Gestión de Fabricante
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos e insercción de los datos en cada campo de texto (en el caso de
 * modificación).
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorGestionFabricantes implements ActionListener {

    private final GestionFabricantes ventanaGestionFabricantes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Fabricante fabricanteAModificar;
    private ResourceBundle idioma;

    /**
     * Constructor para crear un nuevo Fabricante.
     *
     * @param modelo                    Modelo de la aplicación.
     * @param ventanaGestionFabricantes Ventana de Gestión de Fabricante a controlar.
     */
    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = false;
        fabricanteAModificar = null;
        idioma = Util.obtenerTraducciones();
        ventanaGestionFabricantes.dpFechaAlta.setDate(LocalDate.now());
        crearAtajos();
        initHandlers();
    }

    /**
     * Constructor para modificar un Fabricante.
     *
     * @param modelo                    Modelo de la aplicación.
     * @param ventanaGestionFabricantes Ventana de Gestión de Fabricante a controlar.
     * @param fabricante                Fabricante a modificar.
     */
    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo, Fabricante fabricante) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = true;
        fabricanteAModificar = fabricante;
        crearAtajos();
        initHandlers();
        rellenarDatos();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaGestionFabricantes.btnGestionar.addActionListener(this);
        ventanaGestionFabricantes.btnCancelar.addActionListener(this);

        if (modificando) {
            ventanaGestionFabricantes.btnGestionarLotes.addActionListener(this);
        }
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        ventanaGestionFabricantes.btnGestionar.setMnemonic(KeyEvent.VK_1);
        ventanaGestionFabricantes.btnCancelar.setMnemonic(KeyEvent.VK_2);
        ventanaGestionFabricantes.btnGestionarLotes.setMnemonic(KeyEvent.VK_3);
    }

    /**
     * Rellena los datos de todos los campos en función del fabricante a modificar.
     */
    private void rellenarDatos() {
        ventanaGestionFabricantes.txtNombre.setText(fabricanteAModificar.getNombre());
        ventanaGestionFabricantes.txtDireccion.setText(fabricanteAModificar.getDireccion());
        ventanaGestionFabricantes.txtTrabajadores.setText(String.valueOf(fabricanteAModificar.getTrabajadores()));

        ventanaGestionFabricantes.dpFechaAlta.setDate(fabricanteAModificar.getFechaAlta());

        if (fabricanteAModificar.isInternacional()) {
            ventanaGestionFabricantes.chbxInternacional.setSelected(true);
        }

        ventanaGestionFabricantes.lblLotesFabricados.setVisible(true);
        ventanaGestionFabricantes.btnGestionarLotes.setVisible(true);
    }

    /**
     * Gestiona el Fabricante generado tras la comprobación de datos.
     */
    private void gestionarFabricante() {
        Fabricante fabricante = comprobarDatos();
        if (fabricante != null) {
            if (modificando) {
                modelo.modificarFabricante(fabricanteAModificar, fabricante.getNombre(),
                        fabricante.getDireccion(), fabricante.getTrabajadores(),
                        fabricante.getFechaAlta(), fabricante.isInternacional());
            } else {
                modelo.anadirFabricante(fabricante);
            }
            ventanaGestionFabricantes.dispose();

        }
    }

    /**
     * Comprobación de todos los datos introducidos para la creación o modificación
     * de un Fabricante.
     *
     * @return Fabricante generado a partir de los datos introducidos en la ventana.
     */
    private Fabricante comprobarDatos() {
        Fabricante fabricante = null;
        if (comprobarInt(ventanaGestionFabricantes.txtTrabajadores.getText())) {
            if (ventanaGestionFabricantes.txtNombre.getText().replace(" ", "").length() != 0) {
                if (ventanaGestionFabricantes.txtDireccion.getText().replace(" ", "").length() != 0) {
                    if (ventanaGestionFabricantes.dpFechaAlta.getDate() != null) {
                        int trabajadores = Integer.parseInt(ventanaGestionFabricantes.txtTrabajadores.getText());
                        if (trabajadores > 0 && trabajadores < 1500000) {
                            String nombre = ventanaGestionFabricantes.txtNombre.getText();
                            String direccion = ventanaGestionFabricantes.txtDireccion.getText();
                            LocalDate fechaCreacion = ventanaGestionFabricantes.dpFechaAlta.getDate();
                            boolean internacional = false;
                            if (ventanaGestionFabricantes.chbxInternacional.isSelected()) {
                                internacional = true;
                            }
                            fabricante = new Fabricante(nombre, direccion, trabajadores, fechaCreacion, internacional);
                        } else {
                            Util.mostrarError(idioma.getString("error.numTrabajadores"));
                        }


                    } else {
                        Util.mostrarError(idioma.getString("error.faltaFechaAlta"));
                    }
                } else {
                    Util.mostrarError(
                            idioma.getString("error.direccionVacia"));
                }
            } else {
                Util.mostrarError(idioma.getString("error.nombreVacioFabricante"));
            }

        } else {
            Util.mostrarError(idioma.getString("error.formatoTrabajadores"));
        }

        return fabricante;
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
                gestionarFabricante();
                break;
            case "btnCancelar":
                ventanaGestionFabricantes.dispose();
                break;
            case "btnGestionarLotes":
                new ControladorAsignacionDeLotesAFabricantes(new AsignacionDeLotesAFabricante(), fabricanteAModificar, modelo.getLotes());
                ventanaGestionFabricantes.dispose();
        }
    }
}
