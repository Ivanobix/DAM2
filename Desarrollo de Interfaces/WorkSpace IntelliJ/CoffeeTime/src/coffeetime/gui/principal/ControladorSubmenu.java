package coffeetime.gui.principal;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.base.Usuario;
import coffeetime.gui.gestion.*;
import coffeetime.gui.visualizado.ResumenCafe;
import coffeetime.gui.visualizado.ResumenFabricante;
import coffeetime.gui.visualizado.ResumenLote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Controlador del Submenú. Controlador para la ventana del Submenú
 * dedicado a la recogida de eventos, así como el acceso a las funciones de
 * creación, eliminación, modificación, visualizado y filtrado de elementos.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ControladorSubmenu implements ActionListener, KeyListener, ChangeListener {

    private final Submenu submenu;
    private final Modelo modelo;
    private final ResourceBundle idioma;
    private int informeActual;

    /**
     * Constructor.
     *
     * @param submenu Ventana del Submenú.
     * @param modelo  Modelo de la aplicación.
     */
    public ControladorSubmenu(Submenu submenu, Modelo modelo) {
        this.submenu = submenu;
        this.modelo = modelo;
        informeActual = 1;
        idioma = Util.obtenerTraducciones();
        cargarDatos();
        initHandlers();
        crearAtajos();
        cargarUsuario();
    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        submenu.btnAnadir.addActionListener(this);
        submenu.btnEliminar.addActionListener(this);
        submenu.btnModificar.addActionListener(this);
        submenu.btnMostrarInfoAdicional.addActionListener(this);
        submenu.btnCambiarInforme.addActionListener(this);

        submenu.txtFiltro.addKeyListener(this);
        submenu.tabbedPane.addChangeListener(this);
    }

    /**
     * Establece los atajos de teclado para todos los botones existentes.
     */
    private void crearAtajos() {
        submenu.btnAnadir.setMnemonic(KeyEvent.VK_1);
        submenu.btnEliminar.setMnemonic(KeyEvent.VK_2);
        submenu.btnModificar.setMnemonic(KeyEvent.VK_3);
        submenu.btnMostrarInfoAdicional.setMnemonic(KeyEvent.VK_4);
        submenu.btnCambiarInforme.setMnemonic(KeyEvent.VK_5);
    }

    /**
     * Rellena la lista de los elementos existentes en función de su tipo.
     */
    private void cargarDatos() {
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            ArrayList<Cafe> cafes = modelo.getCafes();
            for (Cafe cafe : cafes) {
                submenu.dlm.addElement(cafe);
            }
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            ArrayList<Lote> lotes = modelo.getLotes();
            for (Lote lote : lotes) {
                submenu.dlm.addElement(lote);
            }
        } else {
            ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
            for (Fabricante fabricante : fabricantes) {
                submenu.dlm.addElement(fabricante);
            }
        }
    }

    /**
     * Genera los gráficos en función del tipo de elemento gestionado.
     */
    private void crearEstadisticas() {
        submenu.pnEstadisticas.removeAll();
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoCafe(new DefaultPieDataset())));
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoLote(new DefaultCategoryDataset())));
        } else {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoFabricante(new DefaultPieDataset())));
        }

        submenu.repaint();
    }

    /**
     * Genera los informes en función del tipo de elemento gestionado.
     */
    private void crearInformes() {
        submenu.pnInforme.removeAll();
        try {
            JasperReport report;
            JRBeanCollectionDataSource coleccion;
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Cafes.jasper"));
                coleccion = new JRBeanCollectionDataSource(modelo.getCafes());
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                String nombreInforme = "/Lotes" + informeActual + ".jasper";
                report = (JasperReport) JRLoader.loadObject(getClass().getResource(nombreInforme));
                coleccion = new JRBeanCollectionDataSource(modelo.getLotes());
            } else {
                report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Fabricantes.jasper"));
                coleccion = new JRBeanCollectionDataSource(modelo.getFabricantes());
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, coleccion);
            JRViewer visor = new JRViewer(jasperPrint);
            submenu.pnInforme.add(visor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        submenu.repaint();
    }

    private void mostrarSiguienteInforme() {
        if (informeActual == 1) informeActual = 2;
        else informeActual = 1;
        crearInformes();
    }

    /**
     * Genera los gráficos correspondientes a elementos de tipo Café.
     *
     * @param dataset Conjunto de datos.
     * @return Gráfica con datos de tipo Café.
     */
    private JFreeChart crearGraficoCafe(DefaultPieDataset dataset) {
        String tituloGrafica = idioma.getString("grafico.cafe");
        double arabico = 0;
        double robusta = 0;

        for (Cafe cafe : modelo.getCafes()) {
            arabico += cafe.getPorcentajeArabico();
            robusta += cafe.getPorcentajeRobusta();
        }
        dataset.setValue(idioma.getString("grafico.arabico"), arabico);
        dataset.setValue(idioma.getString("grafico.robusta"), robusta);

        return ChartFactory.createPieChart(tituloGrafica, dataset);
    }

    /**
     * Genera los gráficos correspondientes a elementos de tipo Lote.
     *
     * @param dataset Conjunto de datos.
     * @return Gráfica con datos de tipo Lote.
     */
    private JFreeChart crearGraficoLote(DefaultCategoryDataset dataset) {
        String tituloGrafica = idioma.getString("grafico.lote");
        for (Lote lote : modelo.getLotes()) {
            dataset.setValue(lote.getCosteTotal(), lote.getFabricante().getNombre(), lote.getIdentificador());
        }
        return ChartFactory.createBarChart(tituloGrafica, idioma.getString("general.lote"), idioma.getString("general.coste"), dataset);
    }

    /**
     * Genera los gráficos correspondientes a elementos de tipo Fabricante.
     *
     * @param dataset Conjunto de datos.
     * @return Gráfica con datos de tipo Fabricante.
     */
    private JFreeChart crearGraficoFabricante(DefaultPieDataset dataset) {
        String tituloGrafica = idioma.getString("grafico.fabricante1");
        int unidadesVendidas = 0;
        for (Fabricante fabricante : modelo.getFabricantes()) {
            for (Lote lote : modelo.getLotes()) {
                if (lote.getFabricante().equals(fabricante)) {
                    unidadesVendidas += lote.getNumeroUnidades();
                }
            }
            dataset.setValue(fabricante.getNombre(), unidadesVendidas);
            unidadesVendidas = 0;
        }

        return ChartFactory.createPieChart(tituloGrafica, dataset);
    }


    /**
     * Actualiza la lista de elementos tras realizar cambios en ella.
     */
    private void actualizarLista() {
        submenu.dlm.clear();
        cargarDatos();
    }

    /**
     * Crea un nuevo elemento en función de su tipo a la lista y lo añade a la aplicación.
     */
    private void anadir() {
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            new ControladorGestionCafes(new GestionCafes(), modelo);
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            new ControladorGestionLotes(new GestionLotes(), modelo);
        } else {
            new ControladorGestionFabricantes(new GestionFabricantes(), modelo);
        }

        submenu.dispose();
    }

    /**
     * Elimina un elemento en función de su tipo de la lista y lo borra de la aplicación.
     */
    private void eliminar() {
        int seguroDeEliminar = Util.mostrarConfirmacion(idioma.getString("confirmacion.eliminarElemento"));
        if (seguroDeEliminar == JOptionPane.YES_OPTION) {
            Object objeto = submenu.listaElementos.getSelectedValue();
            if (objeto != null) {
                if (submenu.tipo == Submenu.TYPE_CAFES) {
                    modelo.eliminarCafe((Cafe) objeto);
                } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                    modelo.eliminarLote((Lote) objeto);
                } else {
                    modelo.eliminarFabricante((Fabricante) objeto);
                }
            } else {
                Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
            }

            actualizarLista();
        }

    }

    /**
     * Modifica un elemento en función de su tipo de la lista y lo actualiza en la aplicación.
     */
    private void modificar() {
        Object objeto = submenu.listaElementos.getSelectedValue();
        if (objeto != null) {
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                new ControladorGestionCafes(new GestionCafes(), modelo, (Cafe) objeto);
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                new ControladorGestionLotes(new GestionLotes(), modelo, (Lote) objeto);
            } else {
                new ControladorGestionFabricantes(new GestionFabricantes(), modelo, (Fabricante) objeto);
            }
            submenu.dispose();
        } else {
            Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
        }

    }

    /**
     * Muestra todos los datos de un elemento en función de su tipo.
     */
    private void mostrarInfoAdicional() {
        Object objeto = submenu.listaElementos.getSelectedValue();
        if (objeto != null) {
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                new ResumenCafe((Cafe) objeto, modelo.getLotes());
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                new ResumenLote((Lote) objeto, modelo.getLotes());
            } else {
                new ResumenFabricante((Fabricante) objeto, modelo.getLotes());
            }

        } else {
            Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
        }
    }

    /**
     * Filtra la lista en función del filtro y filtrado seleccionados.
     */
    private void filtrar() {
        String filtro = submenu.txtFiltro.getText().trim().toLowerCase();
        if (!filtro.replaceAll(" ", "").equals("")) {
            submenu.dlm.clear();
            int filtrado = submenu.cbFiltrado.getSelectedIndex() + 1;
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                buscarCafes(filtrado, filtro);
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                buscarLotes(filtrado, filtro);
            } else {
                buscarFabricantes(filtrado, filtro);
            }

        } else {
            actualizarLista();
        }

    }

    /**
     * Busca todos los elementos de tipo Café que cumplan con el filtro seleccionado.
     *
     * @param filtrado Filtrado seleccionado.
     * @param filtro   Filtro a aplicar.
     */
    private void buscarCafes(int filtrado, String filtro) {
        for (Cafe cafe : modelo.getCafes()) {
            switch (filtrado) {
                case 1: //Nombre
                    if (cafe.getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
                case 2: //Arabico
                    if (String.valueOf(cafe.getPorcentajeArabico()).contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
                case 3: //Robusta
                    if (String.valueOf(cafe.getPorcentajeRobusta()).contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
            }
        }
    }

    /**
     * Busca todos los elementos de tipo Lote que cumplan con el filtro seleccionado.
     *
     * @param filtrado Filtrado seleccionado.
     * @param filtro   Filtro a aplicar.
     */
    private void buscarLotes(int filtrado, String filtro) {
        for (Lote lote : modelo.getLotes()) {
            switch (filtrado) {
                case 1: //Unidades
                    if (String.valueOf(lote.getNumeroUnidades()).contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;
                case 2: //Coste
                    if (String.valueOf(lote.getCosteTotal()).contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;
                case 3: //Fabricante
                    if (lote.getFabricante().getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;

            }
        }
    }

    /**
     * Busca todos los elementos de tipo Fabricante que cumplan con el filtro seleccionado.
     *
     * @param filtrado Filtrado seleccionado.
     * @param filtro   Filtro a aplicar.
     */
    private void buscarFabricantes(int filtrado, String filtro) {
        for (Fabricante fabricante : modelo.getFabricantes()) {
            switch (filtrado) {
                case 1: //Nombre
                    if (fabricante.getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
                case 2: //Dirección
                    if (fabricante.getDireccion().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
                case 3: //Trabajadores
                    if (String.valueOf(fabricante.getTrabajadores()).contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
            }
        }
    }

    /**
     * En función de los privilegios del usuario logueado activa o desactiva el acceso a determinadas funciones.
     */
    private void cargarUsuario() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/account.conf"));
            String nivelUsuario = properties.getProperty("NivelUsuario");
            activarFunciones(nivelUsuario);
        } catch (Exception e) {
            activarFunciones(String.valueOf(Usuario.ADMIN));
        }
    }

    /**
     * Establece las funciones disponibles o no para cada uno de los niveles de usuario.
     *
     * @param nivelUsuario Nivel de privilegios del usuario actual.
     */
    private void activarFunciones(String nivelUsuario) {
        if (nivelUsuario.equals(String.valueOf(Usuario.DEFAULT))) {
            submenu.btnEliminar.setEnabled(false);
        } else if (nivelUsuario.equals(String.valueOf(Usuario.BASICO))) {
            submenu.btnAnadir.setEnabled(false);
            submenu.btnEliminar.setEnabled(false);
            submenu.btnModificar.setEnabled(false);
        }
    }

    /**
     * Procedimientos a seguir en caso de que un botón haya sido pulsado.
     *
     * @param e Evento de acción creado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnAnadir":
                anadir();
                break;
            case "btnEliminar":
                eliminar();
                break;
            case "btnModificar":
                modificar();
                break;
            case "btnMostrarInfoAdicional":
                mostrarInfoAdicional();
                break;
            case "btnCambiarInforme":
                mostrarSiguienteInforme();
                break;
        }
    }

    /**
     * Procedimientos a seguir en caso de que una tecla haya sido pulsada.
     *
     * @param e Evento de teclado creado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        filtrar();
    }

    /**
     * Procedimientos a seguir en caso de que el panel que se muestra en un determinado cambia.
     *
     * @param e Evento de cambio de estado creado.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (submenu.tabbedPane.getSelectedIndex() == 2) crearInformes();
        else if (submenu.tabbedPane.getSelectedIndex() == 1) crearEstadisticas();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
