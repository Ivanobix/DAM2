package ejercicio1;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1 {
    private JPanel contentPane;
    private JTextField txtNombre;
    private JButton btnAnadir;
    private JComboBox<Persona> cbPersonas;
    private DatePicker dpFechaNacimiento;
    private JPanel pnListado;
    private DefaultComboBoxModel<Persona> dcbm;

    public Ejercicio1() {
        JFrame frame = new JFrame("Ejercicio1");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dcbm = new DefaultComboBoxModel<>();
        cbPersonas.setModel(dcbm);

        btnAnadir.addActionListener(e -> anadirPersona());
    }

    public static void main(String[] args) {
        new Ejercicio1();
    }

    private void anadirPersona() {
        Persona persona = new Persona(txtNombre.getText(), dpFechaNacimiento.getDate());
        dcbm.addElement(persona);
        pnListado.add(new Checkbox(persona.getNombre()));
        contentPane.validate();
    }


}
