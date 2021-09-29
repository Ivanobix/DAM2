package com.fvaldeon.gestionalumnos.mvc.gui;

import barraestado.BarraEstado;
import com.fvaldeon.gestionalumnos.base.Alumno;
import com.fvaldeon.gestionalumnos.base.Profesor;
import com.fvaldeon.gestionalumnos.base.Usuario;
import com.fvaldeon.gestionalumnos.layouts.WrapLayout;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class Vista {
    JFrame frame;
    JPanel contentPane;
    JTabbedPane tabbedPane1;
    JButton guardarFicheroBtn;
    JButton settingsBtn;
    JButton abrirFicheroBtn;
    JPanel panelNorte;
    JTextField codigoProfesorTxt;
    JTextField nombreProfesorTxt;
    JTextField apellidosProfesorTxt;
    JList<Alumno> listAlumnosProfesor;
    JList<Profesor> listProfesor;
    JButton modificarProfesorBtn;
    JButton nuevoProfesorBtn;
    JButton eliminarProfesorBtn;
    JButton dialogoRelacionesBtn;
    JButton diagrama1Btn;
    JButton diagrama2Btn;
    JButton informe1Btn;
    JTextField dniAlumnoTxt;
    JTextField nombreAlumnoTxt;
    JTextField apellidosAlumnoTxt;
    DatePicker alumnoDatePicker;
    JLabel fotoAlumnoLbl;
    JButton seleccionarFotoAlumnoBtn;
    JComboBox<Profesor> profesorDeAlumnoCb;
    JList<Alumno> listAlumnos;
    JButton modificarAlumnoBtn;
    JButton nuevoAlumnoBtn;
    JButton eliminarAlumnoBtn;
    BarraEstado barraEstado;
    JButton gestionUsuariosBtn;
    JButton matricularAlumnosProfesorBtn;
    JButton eliminarFotoAlumnoBtn;
    JMenuItem itemSalir;
    JMenuItem itemGuardar;
    JMenuItem itemCargar;
    JMenuItem itemGestionUsuarios;
    JMenuItem itemPreferencias;
    private int tipoUsuario;
    private ResourceBundle resourceBundle;
    DefaultListModel<Profesor> profesorDlm;
    DefaultListModel<Alumno> alumnosDeProfesorDlm;
    DefaultListModel<Alumno> alumnosDlm;
    DefaultComboBoxModel<Profesor> profesorDeAlumnoDcbm;



    public Vista(int tipoUsuario) {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        this.tipoUsuario = tipoUsuario;
        frame = new JFrame("Gestion Alumnos 1.0");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/24x24/edit-user-24.png")).getImage());

        initUIComponents();
        crearMenu();
        iniciarModelos();
        configurarDatePicker();
        activarControlPorTeclado();
        barraEstado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        controlarTipoUsuario();

        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);


    }

    private void iniciarModelos() {
        alumnosDeProfesorDlm = new DefaultListModel<>();
        alumnosDlm = new DefaultListModel<>();
        profesorDlm = new DefaultListModel<>();
        profesorDeAlumnoDcbm = new DefaultComboBoxModel<>();

        listProfesor.setModel(profesorDlm);
        listAlumnosProfesor.setModel(alumnosDeProfesorDlm);
        listAlumnos.setModel(alumnosDlm);
        profesorDeAlumnoCb.setModel(profesorDeAlumnoDcbm);
    }

    private void controlarTipoUsuario() {
        //Dependiendo del tipo de usuario activo o desactivo ciertos botones
        boolean activado = tipoUsuario == Usuario.USUARIO_ADMIN ? true : false;

        itemGestionUsuarios.setEnabled(activado);
        itemGuardar.setEnabled(activado);
        itemCargar.setEnabled(activado);
        nuevoProfesorBtn.setEnabled(activado);
        eliminarProfesorBtn.setEnabled(activado);

    }


    private void crearMenu(){

        //Barra de menu
        JMenuBar barra = new JMenuBar();
        frame.setJMenuBar(barra);

        //Menu Archivo
        JMenu menuArchivo = new JMenu(resourceBundle.getString("menu.archivo"));
        barra.add(menuArchivo);

        itemSalir = new JMenuItem(resourceBundle.getString("menu.salir"));
        itemSalir.setActionCommand("Salir");
        itemSalir.setIcon(new ImageIcon(getClass().getResource("/16x16/system-shutdown-6.png")));

        itemGuardar = new JMenuItem(resourceBundle.getString("vista.menuitem.guardar"));
        itemGuardar.setActionCommand("Guardar");
        itemGuardar.setIcon(new ImageIcon(getClass().getResource("/16x16/document-save-5.png")));

        itemCargar = new JMenuItem(resourceBundle.getString("vista.menuitem.abrir"));
        itemCargar.setActionCommand("Abrir");
        itemCargar.setIcon(new ImageIcon(getClass().getResource("/16x16/document-open-5.png")));

        menuArchivo.add(itemCargar);
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemSalir);

        //Menu Editar
        JMenu menuEditar = new JMenu(resourceBundle.getString("vista.menu.editar"));
        barra.add(menuEditar);

        itemGestionUsuarios = new JMenuItem(resourceBundle.getString("vista.menuitem.gestion.usuarios"));
        itemGestionUsuarios.setActionCommand("Usuarios");
        itemGestionUsuarios.setIcon(new ImageIcon(getClass().getResource("/16x16/kgpg_identity.png")));

        itemPreferencias = new JMenuItem(resourceBundle.getString("vista.menuitem.preferencias"));
        itemPreferencias.setActionCommand("Preferencias");
        itemPreferencias.setIcon(new ImageIcon(getClass().getResource("/16x16/system-settings.png")));

        menuEditar.add(itemGestionUsuarios);
        menuEditar.add(itemPreferencias);

    }

    private void initUIComponents() {
       panelNorte.setLayout(new WrapLayout(FlowLayout.LEADING, 3, 3));
    }

    private void activarControlPorTeclado(){
        //Acceso Mnemónico
        modificarProfesorBtn.setMnemonic(KeyEvent.VK_M);
        nuevoProfesorBtn.setMnemonic(KeyEvent.VK_N);
        eliminarProfesorBtn.setMnemonic(KeyEvent.VK_DELETE);
        dialogoRelacionesBtn.setMnemonic(KeyEvent.VK_R);

        //Aceleradores
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        itemGestionUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        itemCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        itemPreferencias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));

        //Boton por defecto de la aplicacion
        nuevoProfesorBtn.getRootPane().setDefaultButton(nuevoProfesorBtn);
    }

    private void configurarDatePicker(){
        JButton button = alumnoDatePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/16x16/date-2.png")));
    }

}
