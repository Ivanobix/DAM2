package coffeetime.gui.otros;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Asignación de Lotes a Fabricantes. Controlador para la ventana de Asignación de Lotes a Fabricantes
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos, permitiendo de esta forma una asignación de elementos correcta.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorAsignacionDeLotesAFabricantes {

    private final AsignacionDeLotesAFabricante ventanaAsignacionDeLotesAFabricante;
    private final Fabricante fabricante;
    private final ArrayList<Lote> lotes;

    /**
     * Constructor.
     *
     * @param ventanaAsignacionDeLotesAFabricante Ventana de Asignación de Lotes a Fabricante
     * @param fabricante                          Fabricante al que asignar los lotes.
     * @param lotes                               Lista de lotes.
     */
    public ControladorAsignacionDeLotesAFabricantes(AsignacionDeLotesAFabricante ventanaAsignacionDeLotesAFabricante, Fabricante fabricante, ArrayList<Lote> lotes) {
        this.fabricante = fabricante;
        this.ventanaAsignacionDeLotesAFabricante = ventanaAsignacionDeLotesAFabricante;
        this.lotes = new ArrayList<>();
        this.lotes.addAll(lotes);

        initHandlers();
        crearAtajos();
        cargarDatos();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaAsignacionDeLotesAFabricante.btnCambiarFabricante.addActionListener(e -> {
            if (!ventanaAsignacionDeLotesAFabricante.listLotesDeOtrosFabricantes.isSelectionEmpty()) {
                List<Lote> seleccion = ventanaAsignacionDeLotesAFabricante.listLotesDeOtrosFabricantes.getSelectedValuesList();
                for (Lote lote : seleccion) {
                    lote.setFabricante(fabricante);
                }
                seleccion.clear();
                actualizarLista();
            }

        });
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        ventanaAsignacionDeLotesAFabricante.btnCambiarFabricante.setMnemonic(KeyEvent.VK_1);
    }

    /**
     * Carga los datos necesarios para permitir la asignación de lotes a fabricantes.
     */
    private void cargarDatos() {
        lotes.removeIf(lote -> lote.getFabricante().equals(fabricante));
        for (Lote lote : lotes) {
            ventanaAsignacionDeLotesAFabricante.dlm.addElement(lote);
        }

    }

    /**
     * Actualiza la lista de lotes tras una asignación de uno de ellos al fabricante actual.
     */
    private void actualizarLista() {
        ventanaAsignacionDeLotesAFabricante.dlm.clear();
        cargarDatos();
    }
}
