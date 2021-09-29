package coffeetime.gui.otros;

import coffeetime.base.Cafe;
import coffeetime.base.Lote;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Asignación de Cafés a Lote 2. Controlador para la ventana de Asignación de Cafés a Lote 2
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos, permitiendo de esta forma una asignación de elementos correcta.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorAsignacionDeCafesALote2 {
    private final AsignacionDeCafesALote2 ventanaAsignacionDeCafesALote;
    private final Cafe cafe;
    private final ArrayList<Lote> lotesSinAsignar;
    private final ArrayList<Lote> lotesAsignados;

    /**
     * Constructor.
     *
     * @param ventanaAsignacionDeCafesALote Ventana de Asignación de Cafés a Lote
     * @param cafe                          Café al que cambiar los lotes
     * @param lotes                         Lista de lotes.
     */
    public ControladorAsignacionDeCafesALote2(AsignacionDeCafesALote2 ventanaAsignacionDeCafesALote, Cafe cafe, ArrayList<Lote> lotes) {
        this.cafe = cafe;
        this.ventanaAsignacionDeCafesALote = ventanaAsignacionDeCafesALote;
        this.lotesSinAsignar = new ArrayList<>();
        lotesAsignados = new ArrayList<>();

        for (Lote lote : lotes) {
            if (lote.getCafes() != null && lote.getCafes().contains(cafe)) {
                lotesAsignados.add(lote);
            } else {
                lotesSinAsignar.add(lote);
            }
        }

        initHandlers();
        crearAtajos();
        cargarDatos();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaAsignacionDeCafesALote.btnAsignarLote.addActionListener(e -> {
            if (!ventanaAsignacionDeCafesALote.listLotesSinAsignar.isSelectionEmpty()) {
                List<Lote> seleccion = ventanaAsignacionDeCafesALote.listLotesSinAsignar.getSelectedValuesList();
                for (Lote lote : seleccion) {
                    lote.addCafe(cafe);
                    lotesAsignados.add(lote);
                    lotesSinAsignar.remove(lote);
                }
                seleccion.clear();
                actualizarListas();
            }

        });

        ventanaAsignacionDeCafesALote.btnEliminarLote.addActionListener(e -> {
            if (!ventanaAsignacionDeCafesALote.listLotesAsignados.isSelectionEmpty()) {
                List<Lote> seleccion = ventanaAsignacionDeCafesALote.listLotesAsignados.getSelectedValuesList();
                for (Lote lote : seleccion) {
                    lote.deleteCafe(cafe);
                    lotesSinAsignar.add(lote);
                    lotesAsignados.remove(lote);
                }
                seleccion.clear();
                actualizarListas();
            }
        });
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        ventanaAsignacionDeCafesALote.btnAsignarLote.setMnemonic(KeyEvent.VK_1);
        ventanaAsignacionDeCafesALote.btnEliminarLote.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * Carga los datos necesarios para permitir la asignación de cafés a lotes.
     */
    private void cargarDatos() {
        for (Lote lote : lotesSinAsignar) {
            ventanaAsignacionDeCafesALote.dlm.addElement(lote);
        }

        for (Lote lote : lotesAsignados) {
            ventanaAsignacionDeCafesALote.dlm2.addElement(lote);
        }
    }

    /**
     * Actualiza la lista de lotes tras una asignación de uno de ellos al fabricante actual.
     */
    private void actualizarListas() {
        ventanaAsignacionDeCafesALote.dlm.clear();
        ventanaAsignacionDeCafesALote.dlm2.clear();
        cargarDatos();
    }

}
