package com.fvaldeon.gestionalumnos.mvc.gui;

import com.fvaldeon.gestionalumnos.base.Alumno;
import com.fvaldeon.gestionalumnos.base.Profesor;
import com.fvaldeon.gestionalumnos.dialogos.DialogoGestionUsuarios;
import com.fvaldeon.gestionalumnos.dialogos.DialogoMatricularAlumnos;
import com.fvaldeon.gestionalumnos.dialogos.DialogoPreferencias;
import com.fvaldeon.gestionalumnos.dialogos.DialogoRelaciones;
import com.fvaldeon.gestionalumnos.excepciones.DatosIntroducidosIncorrectosException;
import com.fvaldeon.gestionalumnos.excepciones.ElementoNoSeleccionadoException;
import com.fvaldeon.gestionalumnos.mvc.modelo.Modelo;
import com.fvaldeon.gestionalumnos.util.Util;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.activation.MimetypesFileTypeMap;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class Controlador implements ActionListener, FocusListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        anadirActionListeners(this);
        anadirFocusListeners(this);
        anadirListSelectionListeners(this);
    }

    /**
     * Metodo que asocia componentes con el listener ListSelectionListener
     *
     * @param listener un objeto ListSelectionListener
     */
    private void anadirListSelectionListeners(ListSelectionListener listener) {
        vista.listAlumnos.addListSelectionListener(listener);
        vista.listAlumnosProfesor.addListSelectionListener(listener);
        vista.listProfesor.addListSelectionListener(listener);
    }

    /**
     * Metodo que asocia componentes con el listener ActionListener
     *
     * @param listener un objeto ActionListener
     */
    private void anadirActionListeners(ActionListener listener) {
        vista.modificarProfesorBtn.addActionListener(listener);
        vista.nuevoProfesorBtn.addActionListener(listener);
        vista.eliminarProfesorBtn.addActionListener(listener);
        vista.itemCargar.addActionListener(listener);
        vista.itemGuardar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.itemGestionUsuarios.addActionListener(listener);
        vista.itemPreferencias.addActionListener(listener);
        vista.abrirFicheroBtn.addActionListener(listener);
        vista.guardarFicheroBtn.addActionListener(listener);
        vista.eliminarAlumnoBtn.addActionListener(listener);
        vista.nuevoAlumnoBtn.addActionListener(listener);
        vista.modificarAlumnoBtn.addActionListener(listener);
        vista.settingsBtn.addActionListener(listener);
        vista.gestionUsuariosBtn.addActionListener(listener);
        vista.matricularAlumnosProfesorBtn.addActionListener(listener);
        vista.seleccionarFotoAlumnoBtn.addActionListener(listener);
        vista.eliminarFotoAlumnoBtn.addActionListener(listener);
        vista.diagrama1Btn.addActionListener(listener);
        vista.diagrama2Btn.addActionListener(listener);
        vista.informe1Btn.addActionListener(listener);
    }

    /**
     * Metodo que asocia componentes con el listener FocusListener
     *
     * @param listener un objeto FocusListener
     */
    private void anadirFocusListeners(FocusListener listener) {
        vista.codigoProfesorTxt.addFocusListener(listener);
        vista.nombreProfesorTxt.addFocusListener(listener);
        vista.apellidosProfesorTxt.addFocusListener(listener);
        vista.apellidosAlumnoTxt.addFocusListener(listener);
        vista.dniAlumnoTxt.addFocusListener(listener);
        vista.nombreAlumnoTxt.addFocusListener(listener);
    }

    /**
     * Metodo que responde a los ActionEvent producidos sobre los componentes asociados.
     * Dependiendo de la propiedad actionCommand del evento se reconoce que componente se ha pulsado.
     *
     * @param actionEvent Evento recibido por el método
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String comando = actionEvent.getActionCommand();
        try {
            switch (comando) {
                case "EliminarProfesor": {
                    eliminarProfesor();
                    break;
                }

                case "NuevoProfesor": {
                    nuevoProfesor();
                    break;
                }

                case "ModificarProfesor": {
                    modificarProfesor();
                    break;
                }

                case "NuevoAlumno": {
                    nuevoAlumno();
                    break;
                }

                case "EliminarAlumno": {
                    eliminarAlumno();
                    break;
                }

                case "ModificarAlumno": {
                    modificarAlumno();
                    break;
                }

                case "MatricularAlumnos": {
                    matricularAlumnos();
                    break;
                }

                case "Guardar": {
                    guardarDatos();
                    break;
                }

                case "Abrir": {
                    cargarDatos();
                    break;
                }

                case "Usuarios": {
                    DialogoGestionUsuarios dialogo = new DialogoGestionUsuarios();
                    break;
                }
                case "Preferencias": {
                    DialogoPreferencias dialogoPreferencias = new DialogoPreferencias();
                    break;
                }

                case "MostrarRelaciones": {
                    DialogoRelaciones dialogoRelaciones = new DialogoRelaciones();
                    break;
                }

                case "SeleccionarFoto": {
                    seleccionarFoto();
                    break;
                }

                case "EliminarFoto": {
                    vista.fotoAlumnoLbl.setIcon(null);
                    break;
                }

                case "Diagrama1": {
                    mostrarDiagrama1();
                    break;
                }

                case "Diagrama2": {
                    mostrarDiagrama2();
                    break;
                }

                case "Informe1": {
                    mostrarInforme();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Util.mensajeError("Error de entrada/salida");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ElementoNoSeleccionadoException e) {
            e.printStackTrace();
            Util.mensajeError(e.getMessage());
        } catch (DatosIntroducidosIncorrectosException e) {
            e.printStackTrace();
            //Mostrar mensajes localizados (i18n)
            Util.mensajeError(e.getLocalizedMessage());
        }
    }

    private void mostrarInforme() {
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeProfesores.jasper"));
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getAlumnos());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, coleccion);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    ///
    /// Metodos de barra de botones
    ///

    /**
     * Metodo para mostrar un diagrama de barras (diagrama2)
     */
    private void mostrarDiagrama2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Integer> map = new HashMap<>();

        for (Alumno alumno : modelo.getAlumnos()) {
            if (map.containsKey(alumno.getFechaNacimiento().getYear())) {

                int cantidad = map.get(alumno.getFechaNacimiento().getYear());
                map.replace(alumno.getFechaNacimiento().getYear(), ++cantidad);

            } else {
                map.put(alumno.getFechaNacimiento().getYear(), 1);
            }
        }

        for (Map.Entry<Integer, Integer> entrada : map.entrySet()) {
            dataset.setValue(entrada.getValue(), entrada.getKey(), entrada.getKey());
        }

        JFreeChart jFreeChart = ChartFactory.createBarChart("Cantidad alumnos por anno nacimiento", "annos", "num alumnos", dataset);

        ChartFrame frame = new ChartFrame("Diagrama", jFreeChart);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodo apra mostrar un diagrama de sectores (diagrama1)
     */
    private void mostrarDiagrama1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Profesor profesor : modelo.getProfesores()) {
            dataset.addValue(profesor.getAlumnos().size(), profesor.getNombre(), profesor.getNombre());
        }

        JFreeChart jFreeChart = ChartFactory.createBarChart("Cantidad alumnos por profesor", "profesores", "num alumnos", dataset);

        ChartFrame frame = new ChartFrame("Diagrama", jFreeChart);
        frame.pack();
        frame.setVisible(true);
    }

    ///
    ///   Metodos de seccion profesores
    ///

    /**
     * Metodo que refresca el JList de profesores de la seccion Profesores
     */
    private void listarProfesoresJlist() {
        vista.profesorDlm.clear();
        for (Profesor profesor : modelo.getProfesores()) {
            vista.profesorDlm.addElement(profesor);
        }
    }

    /**
     * Metodo que despliega el cuadro de dialogo para matricular alumnos a un profesor seleccionado
     *
     * @throws ElementoNoSeleccionadoException
     */
    private void matricularAlumnos() throws ElementoNoSeleccionadoException {
        if (vista.listProfesor.isSelectionEmpty()) {
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un profesor para modificar");
        }
        Profesor profesor = vista.listProfesor.getSelectedValue();
        List<Alumno> alumnosTotales = modelo.getAlumnos();
        DialogoMatricularAlumnos dialogo = new DialogoMatricularAlumnos(profesor, alumnosTotales);

        //Despues de los cambios relisto sus alumnos
        listarAlumnosDeProfesor(profesor);
    }

    /**
     * Metodo que refresca el comboBox de profesores de la seccion de alumnos
     */
    private void listarProfesoresComboBox() {
        vista.profesorDeAlumnoDcbm.removeAllElements();
        vista.profesorDeAlumnoDcbm.addElement(null);
        for (Profesor profesor : modelo.getProfesores()) {
            vista.profesorDeAlumnoDcbm.addElement(profesor);
        }
    }

    /**
     * Lista en el Jlist de alumnos de un profesor, los alumnos que tiene dicho profesor
     *
     * @param profesor El profesor del que queremos listar los alumnos
     */
    private void listarAlumnosDeProfesor(Profesor profesor) {
        vista.alumnosDeProfesorDlm.clear();
        for (Alumno alumno : profesor.getAlumnos()) {
            vista.alumnosDeProfesorDlm.addElement(alumno);
        }
    }

    /**
     * Metodo que modifica los valores el profesor seleccionado en el JList con los datos introducidos
     * en los campos del profesor.
     *
     * @throws ElementoNoSeleccionadoException       Se lanza si no hay un profesor seleccionado en el JList
     * @throws DatosIntroducidosIncorrectosException Se lanza si los datos introducidos no son correctos
     *                                               atendiendo al metodo datosProfesorCorrectos()
     */
    private void modificarProfesor() throws ElementoNoSeleccionadoException, DatosIntroducidosIncorrectosException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");

        if (vista.listProfesor.isSelectionEmpty()) {
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un profesor para modificar");
        }

        if (!datosProfesorCorrectos()) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de profesor incorrectos: datos en blanco");

            exception.setLocalizedMessage(resourceBundle.getString("DatosIntroducidosIncorrectosException.datos.profesor.vacios"));

            throw exception;
        }

        Profesor profesor = vista.listProfesor.getSelectedValue();

        if (!vista.codigoProfesorTxt.getText().equalsIgnoreCase(profesor.getCodigo())
                && modelo.existeCodigoProfesor(vista.codigoProfesorTxt.getText())) {

            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de profesor incorrectos: codigo repetido");

            exception.setLocalizedMessage(resourceBundle.getString("DatosIntroducidosIncorrectosException.codigo.profesor.repetido"));

            throw exception;
        }

        profesor.setNombre(vista.nombreProfesorTxt.getText());
        profesor.setApellidos(vista.apellidosProfesorTxt.getText());
        profesor.setCodigo(vista.codigoProfesorTxt.getText());

        listarProfesoresComboBox();
        listarProfesoresJlist();

    }

    /**
     * Metodo que comprueba si los datos introducidos en los campos de un profesor son correctos.
     * Son correctos si el código no está repetido y si no estan en blanco
     *
     * @return true si todos los datos on correctos, false en caso contrario
     */
    private boolean datosProfesorCorrectos() {
        if (vista.nombreProfesorTxt.getText().isEmpty()
                || vista.apellidosProfesorTxt.getText().isEmpty()
                || vista.codigoProfesorTxt.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Metodo que crea un profesor nuevo a partir de los datos introducidos en sus campos de texto.
     *
     * @throws DatosIntroducidosIncorrectosException Se lanza si los datos introducidos en los campos de texto
     *                                               no son correctos atendiendo al metodo datosProfesorCorrectos()
     */
    private void nuevoProfesor() throws DatosIntroducidosIncorrectosException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");

        if (!datosProfesorCorrectos()) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de profesor incorrectos: datos en blanco");

            exception.setLocalizedMessage(resourceBundle.getString("DatosIntroducidosIncorrectosException.datos.profesor.vacios"));

            throw exception;
        }
        if (modelo.existeCodigoProfesor(vista.codigoProfesorTxt.getText())) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de profesor incorrectos: codigo repetido");

            exception.setLocalizedMessage(resourceBundle.getString("DatosIntroducidosIncorrectosException.codigo.profesor.repetido"));

            throw exception;
        }

        Profesor profesor = new Profesor(vista.codigoProfesorTxt.getText(), vista.nombreProfesorTxt.getText(), vista.apellidosProfesorTxt.getText());
        modelo.anadirProfesor(profesor);
        listarProfesoresComboBox();
        listarProfesoresJlist();

        //Lo selecciono en su lista
        vista.listProfesor.setSelectedValue(profesor, true);

    }

    /**
     * Metodo que elimina el profesor seleccionado en su JList.
     *
     * @throws ElementoNoSeleccionadoException Se lanza si no hay ningun profesor seleccionado en el JList
     */
    private void eliminarProfesor() throws ElementoNoSeleccionadoException {

        if (vista.listProfesor.isSelectionEmpty()) {
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un profesor para eliminar");
        }

        Profesor profesor = vista.listProfesor.getSelectedValue();

        modelo.eliminarProfesor(profesor);

        listarProfesoresComboBox();
        listarProfesoresJlist();
    }

    /**
     * Muestra en los campos de introduccion de datos de profesor, los datos del profesor seleccionado.
     * Si no hay ningun profesor seleccionado, vacia los campos
     */
    private void mostrarDatosProfesor() {
        Profesor profesor = vista.listProfesor.getSelectedValue();
        if (profesor == null) {
            borrarCamposProfesor();
        } else {
            vista.codigoProfesorTxt.setText(profesor.getCodigo());
            vista.nombreProfesorTxt.setText(profesor.getNombre());
            vista.apellidosProfesorTxt.setText(profesor.getApellidos());
            listarAlumnosDeProfesor(profesor);
        }
    }

    /**
     * Vacia los campos de introduccion de datos de profesor
     */
    private void borrarCamposProfesor() {
        vista.codigoProfesorTxt.setText("");
        vista.nombreProfesorTxt.setText("");
        vista.apellidosProfesorTxt.setText("");
        vista.alumnosDeProfesorDlm.clear();
    }

    ///
    // Metodos de seccion de alumno
    ///


    /**
     * Muestra en la label del alumno la imagen seleccionada. Se ejecuto al pulsar
     * sobre el boton seleccionar imagen de la seccion de alumno
     */
    private void seleccionarFoto() {
        JFileChooser selector = new JFileChooser();
        int opt = selector.showOpenDialog(null);
        if (opt == JFileChooser.APPROVE_OPTION) {

            File file = selector.getSelectedFile();

            //Con esta linea puedo mostrar el tipo de archivo que he seleccionado
            System.out.println(new MimetypesFileTypeMap().getContentType(file));

            ImageIcon icono = new ImageIcon(file.getPath());

            ImageIcon iconoEscalado = Util.escalarImageIcon(icono, 90, 90);

            vista.fotoAlumnoLbl.setIcon(iconoEscalado);
        }
    }

    /**
     * Refresca el Jlist de Alumnos
     */
    private void listarAlumnos() {
        vista.alumnosDlm.clear();
        for (Alumno alumno : modelo.getAlumnos()) {
            vista.alumnosDlm.addElement(alumno);
        }
    }

    /**
     * Modifica el alumno seleccionado en el Jlist siempre que los datos introducidos en sus campos sean correctos,
     * atendiendo al método datosAlumnoCorrectos().
     *
     * @throws DatosIntroducidosIncorrectosException Se lanza si los datos introducidos no son correctos
     * @throws ElementoNoSeleccionadoException       Se lanza si no se ha seleccionado ningun alumno en el JList
     */
    private void modificarAlumno() throws DatosIntroducidosIncorrectosException, ElementoNoSeleccionadoException {
        if (vista.listAlumnos.isSelectionEmpty()) {
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un alumno para modificar");
        }

        if (!datosAlumnoCorrectos()) {
            throw new DatosIntroducidosIncorrectosException("Datos de alumno incorrector: datos en blanco");
        }

        Alumno alumno = vista.listAlumnos.getSelectedValue();

        if (!vista.dniAlumnoTxt.getText().equalsIgnoreCase(alumno.getDni()) && modelo.existeDniAlumno(vista.dniAlumnoTxt.getText())) {
            throw new DatosIntroducidosIncorrectosException("Datos de alumno incorrector: dni repetido");
        }

        alumno.setDni(vista.dniAlumnoTxt.getText());
        alumno.setNombre(vista.nombreAlumnoTxt.getText());
        alumno.setApellidos(vista.apellidosAlumnoTxt.getText());
        alumno.setFechaNacimiento(vista.alumnoDatePicker.getDate());
        alumno.setFoto(vista.fotoAlumnoLbl.getIcon());
        alumno.establecerProfesorSafe((Profesor) vista.profesorDeAlumnoCb.getSelectedItem());

        vista.listAlumnos.requestFocus();
    }

    /**
     * Metodo que indica si los datos introducidos en los campos de un alumno son correctos. Para que sean correctos
     * no pueden estar vacios ni se debe repetir un dni
     *
     * @return true si los datos son correctos, false en caso contrario
     */
    private boolean datosAlumnoCorrectos() {
        if (vista.dniAlumnoTxt.getText().isEmpty()
                || vista.nombreAlumnoTxt.getText().isEmpty()
                || vista.apellidosAlumnoTxt.getText().isEmpty()
                || vista.alumnoDatePicker.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Metodo que elimina el alumno seleccionado en su Jlist
     *
     * @throws ElementoNoSeleccionadoException Se lanza si se intenta eliminar un alumno sin que se haya
     *                                         seleccionado ninguno en el JList
     */
    private void eliminarAlumno() throws ElementoNoSeleccionadoException {
        if (vista.listAlumnos.isSelectionEmpty()) {
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un alumno para eliminar");
        }
        Alumno alumno = vista.listAlumnos.getSelectedValue();
        modelo.eliminarAlumno(alumno);
        listarAlumnos();

    }

    /**
     * Metodo que crea un nuevo alumno a partir de los datos introducidos en los campos del alumno
     *
     * @throws DatosIntroducidosIncorrectosException Se lanza la excepcion si alguno de los datos introducidos
     *                                               del alumno no es correcto
     */
    private void nuevoAlumno() throws DatosIntroducidosIncorrectosException {
        if (!datosAlumnoCorrectos()) {
            throw new DatosIntroducidosIncorrectosException("Datos de alumno incorrectos: datos en blanco");
        }

        if (modelo.existeDniAlumno(vista.dniAlumnoTxt.getText())) {
            throw new DatosIntroducidosIncorrectosException("Datos de alumno incorrectos: dni repetido");
        }

        Alumno alumno = new Alumno(vista.dniAlumnoTxt.getText(), vista.nombreAlumnoTxt.getText(),
                vista.apellidosAlumnoTxt.getText(), vista.alumnoDatePicker.getDate(),
                (Profesor) vista.profesorDeAlumnoCb.getSelectedItem());

        //Annado al alumno tambien el valor de la imagen
        alumno.setFoto(vista.fotoAlumnoLbl.getIcon());

        modelo.anadirAlumno(alumno);

        listarAlumnos();
        //Lo selecciono en su lista
        vista.listAlumnos.setSelectedValue(alumno, true);
    }


    /**
     * Metodo que selecciona el alumno del Jlist de alumnos, cuando lo seleccionamos en el Jlist de los
     * alumnos de un profesor
     */
    private void seleccionarAlumnoDeLista() {
        Alumno alumno = vista.listAlumnosProfesor.getSelectedValue();
        if (alumno != null) {
            //Vacio la seleccion de la lista de alumnos de profesor
            vista.listAlumnosProfesor.clearSelection();
            //Voy a la pestaña de alumnos
            vista.tabbedPane1.setSelectedIndex(1);
            //Selecciono dicho alumno del JList de alumnos (por lo que se mostraran sus datos)
            vista.listAlumnos.setSelectedValue(alumno, true);
        }
    }

    /**
     * Metodo que muestra los datos en los campos, cuando seleccionamos un alumno en un Jlist
     */
    private void mostrarDatosAlumno() {
        Alumno alumno = vista.listAlumnos.getSelectedValue();
        if (alumno == null) {
            borrarCamposAlumno();
        } else {
            vista.dniAlumnoTxt.setText(alumno.getDni());
            vista.nombreAlumnoTxt.setText(alumno.getNombre());
            vista.apellidosAlumnoTxt.setText(alumno.getApellidos());
            vista.alumnoDatePicker.setDate(alumno.getFechaNacimiento());
            vista.fotoAlumnoLbl.setIcon(alumno.getFoto());
            vista.profesorDeAlumnoDcbm.setSelectedItem(alumno.getProfesor());
        }
    }

    /**
     * Metodo que borra los valores de los campos de introducción de datos de alumno
     */
    private void borrarCamposAlumno() {
        vista.dniAlumnoTxt.setText("");
        vista.nombreAlumnoTxt.setText("");
        vista.apellidosAlumnoTxt.setText("");
        vista.alumnoDatePicker.setText("");
        vista.fotoAlumnoLbl.setIcon(null);
        vista.profesorDeAlumnoDcbm.setSelectedItem(null);

    }

    //Guardado y carga de datos

    /**
     * Metodo que muestra un dialogo de seleccion de fichero para cargar los datos
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void cargarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(vista.frame);
        if (opt == JFileChooser.APPROVE_OPTION) {
            modelo.cargarDatos(fileChooser.getSelectedFile());
        }
        listarProfesoresComboBox();
        listarProfesoresJlist();
        listarAlumnos();
    }

    /**
     * Metodo que muestra un dialogo de seleccion de fichero para guardar los datos
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void guardarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showSaveDialog(vista.frame);
        if (opt == JFileChooser.APPROVE_OPTION) {
            modelo.guardarDatos(fileChooser.getSelectedFile());
        }
    }

    // Metodos listeners

    /**
     * Metodo para seleccionar el texto de un campo de texto cuando este gana el foco
     *
     * @param focusEvent
     */
    @Override
    public void focusGained(FocusEvent focusEvent) {
        //Para seleccionar el texto de un campo de texto al pulsar sobre el
        if (focusEvent.getSource().getClass() == JTextField.class) {
            JTextField campoTexto = (JTextField) focusEvent.getSource();
            campoTexto.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    /**
     * Metodo que responde a las selecciones de elementos en los JList
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vista.listProfesor) {
            mostrarDatosProfesor();
        } else if (e.getSource() == vista.listAlumnos) {
            mostrarDatosAlumno();
        } else if (e.getSource() == vista.listAlumnosProfesor) {
            seleccionarAlumnoDeLista();
        }
    }


}
