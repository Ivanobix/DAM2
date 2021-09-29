package com.fvaldeon.gestionalumnos.dialogos;

import com.fvaldeon.gestionalumnos.base.Alumno;
import com.fvaldeon.gestionalumnos.base.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogoMatricularAlumnos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Alumno> listAlumnosMatriculados;
    private DefaultListModel<Alumno> matriculadosDlm;
    private DefaultListModel<Alumno> noMatriculadosDlm;
    private JList<Alumno> listAlumnosNoMatriculados;
    private JButton matricularBtn;
    private JButton desmatricularBtn;
    private JLabel profesorLbl;
    private JLabel ayudaLbl;
    private Profesor profesor;
    private List<Alumno> listaTemporalMatriculados;
    private List<Alumno> listaTemporalNoMatriculados;

    public DialogoMatricularAlumnos(Profesor profesor, List<Alumno> alumnosTotales) {
        this.profesor = profesor;

        //Creo dos listas temporales. Si finalmente acepto los cambios, se hacen efectivas
        listaTemporalMatriculados = new ArrayList<>(profesor.getAlumnos());
        //System.out.println(listaTemporalMatriculados.size());
        listaTemporalNoMatriculados = new ArrayList<>(alumnosTotales);
        //System.out.println(listaTemporalNoMatriculados.size());
        //Elimino los alumnos que ya tiene el profesor de la lista de no matriculados
        listaTemporalNoMatriculados.removeAll(listaTemporalMatriculados);
        //System.out.println(listaTemporalNoMatriculados.size());

        matriculadosDlm = new DefaultListModel<>();
        noMatriculadosDlm = new DefaultListModel<>();
        listAlumnosMatriculados.setModel(matriculadosDlm);
        listAlumnosNoMatriculados.setModel(noMatriculadosDlm);

        listarAlumnosMatriculados();
        listarAlumnosNoMatriculados();

        initUI();
    }

    private void listarAlumnosMatriculados(){
        Collections.sort(listaTemporalMatriculados);

        matriculadosDlm.clear();
        for(Alumno alumno : listaTemporalMatriculados){
            matriculadosDlm.addElement(alumno);
            System.out.println(alumno);
        }
    }

    private void listarAlumnosNoMatriculados(){
        Collections.sort(listaTemporalNoMatriculados);

        noMatriculadosDlm.clear();
        for(Alumno alumno : listaTemporalNoMatriculados){
            noMatriculadosDlm.addElement(alumno);
        }
    }

    private void matricularAlumnosSeleccionados() {
        //Obtengo todos los alumnos seleccionados de la lista de no matriculados
        List<Alumno> seleccionados = listAlumnosNoMatriculados.getSelectedValuesList();
        //Los elimino de su lista
        listaTemporalNoMatriculados.removeAll(seleccionados);
        //Y los annado a la lista del profesor
        listaTemporalMatriculados.addAll(seleccionados);

        listarAlumnosMatriculados();
        listarAlumnosNoMatriculados();
    }

    private void desmatricularAlumnosSeleccionados() {
        List<Alumno> seleccionados = listAlumnosMatriculados.getSelectedValuesList();
        listaTemporalMatriculados.removeAll(seleccionados);
        listaTemporalNoMatriculados.addAll(seleccionados);

        listarAlumnosMatriculados();
        listarAlumnosNoMatriculados();
    }

    private void onOK() {
        realizarCambios();
        dispose();
    }

    private void realizarCambios() {
        //Los unicos cambios que afectan son los relativos a la lista definitiva de alumnos del profesor

        //Si finalmente acepto los cambios debo recorrer a los alumnos antiguos del profesor y quitarles el profesor
        //Y  recorrerlos para indicarles el nuevo profesor matriculados y cambiarles el profesor

        for(Alumno alumno : profesor.getAlumnos()){
            alumno.setProfesor(null);
        }

        for(Alumno alumno : listaTemporalMatriculados){
            alumno.establecerProfesorSafe(profesor);
        }

        profesor.setAlumnos(listaTemporalMatriculados);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);
        setTitle(profesor.getCodigo() + " - " + profesor.getNombre() + " " + profesor.getApellidos());

        profesorLbl.setText(profesor.toString());
        ayudaLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        matricularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desmatricularAlumnosSeleccionados();
            }
        });
        desmatricularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matricularAlumnosSeleccionados();
            }
        });

        pack();
        setVisible(true);
    }

}
