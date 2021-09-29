package ejercicio3;

import com.github.lgooddatepicker.components.DatePicker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Ejercicio3 {
    private JPanel contentPane;
    private JTextField txtNombrePersona;
    private JButton btnAnadirPersona;
    private JButton btnMostrarInforme;
    private DatePicker dpFechaNac;
    private JTextField txtAnoNacimiento;
    private JButton btnEliminarPersona;
    private ArrayList<Persona> personas;

    public Ejercicio3() {
        JFrame frame = new JFrame("Ejercicio3");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        personas = new ArrayList<>();

        btnAnadirPersona.addActionListener(e -> anadirPersona());
        btnEliminarPersona.addActionListener(e -> eliminarPersonas());
        btnMostrarInforme.addActionListener(e -> mostrarInforme());
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new Ejercicio3();
    }

    private void anadirPersona() {
        personas.add(new Persona(txtNombrePersona.getText(), dpFechaNac.getDate()));
    }

    private void mostrarInforme() {
        if (!personas.isEmpty()) {
            try {
                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Personas.jasper"));
                JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(personas);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, coleccion);
                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception ignored) {
            }
        } else {
            ResourceBundle idioma = ResourceBundle.getBundle("idiomas");
            JOptionPane.showMessageDialog(null, idioma.getString("avisoPersonas"), idioma.getString("aviso"), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarPersonas() {
        personas.removeIf(persona -> persona.getFechaNacimiento().getYear() == Integer.parseInt(txtAnoNacimiento.getText()));
    }
}
