package coffeetime.gui.otros;

import coffeetime.base.Cafe;
import coffeetime.base.Lote;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Asignación de Cafés a Lote. Controlador para la ventana de Asignación de Cafés a Lote
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos, permitiendo de esta forma una asignación de elementos correcta.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorAsignacionDeCafesALote {

    private final AsignacionDeCafesALote ventanaAsignacionDeCafesALote;
    private final Lote lote;
    private final ArrayList<Cafe> cafesSinAsignar;
    private final ArrayList<Cafe> cafesAsignados;

    /**
     * Constructor.
     *
     * @param ventanaAsignacionDeCafesALote Ventana de Asignación de Cafés a Lote
     * @param lote                          Lote al que asignar los cafés.
     * @param cafes                         Lista de cafés.
     */
    public ControladorAsignacionDeCafesALote(AsignacionDeCafesALote ventanaAsignacionDeCafesALote, Lote lote, ArrayList<Cafe> cafes) {
        this.lote = lote;
        this.ventanaAsignacionDeCafesALote = ventanaAsignacionDeCafesALote;
        this.cafesSinAsignar = new ArrayList<>();
        cafesAsignados = new ArrayList<>();

        for (Cafe cafe : cafes) {
            if (lote.getCafes() != null && lote.getCafes().contains(cafe)) {
                cafesAsignados.add(cafe);
            } else {
                cafesSinAsignar.add(cafe);
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
        ventanaAsignacionDeCafesALote.btnAsignarCafe.addActionListener(e -> {
            if (!ventanaAsignacionDeCafesALote.listCafesSinAsignar.isSelectionEmpty()) {
                List<Cafe> seleccion = ventanaAsignacionDeCafesALote.listCafesSinAsignar.getSelectedValuesList();
                for (Cafe cafe : seleccion) {
                    lote.addCafe(cafe);
                    cafesAsignados.add(cafe);
                    cafesSinAsignar.remove(cafe);
                }
                seleccion.clear();
                actualizarListas();
            }

        });

        ventanaAsignacionDeCafesALote.btnEliminarCafe.addActionListener(e -> {
            if (!ventanaAsignacionDeCafesALote.listCafesAsignados.isSelectionEmpty()) {
                List<Cafe> seleccion = ventanaAsignacionDeCafesALote.listCafesAsignados.getSelectedValuesList();
                for (Cafe cafe : seleccion) {
                    lote.deleteCafe(cafe);
                    cafesSinAsignar.add(cafe);
                    cafesAsignados.remove(cafe);
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
        ventanaAsignacionDeCafesALote.btnAsignarCafe.setMnemonic(KeyEvent.VK_1);
        ventanaAsignacionDeCafesALote.btnEliminarCafe.setMnemonic(KeyEvent.VK_2);
    }

    /**
     * Carga los datos necesarios para permitir la asignación de cafés a lotes.
     */
    private void cargarDatos() {
        for (Cafe cafe : cafesSinAsignar) {
            ventanaAsignacionDeCafesALote.dlm.addElement(cafe);
        }

        for (Cafe cafe : cafesAsignados) {
            ventanaAsignacionDeCafesALote.dlm2.addElement(cafe);
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
