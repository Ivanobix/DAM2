package prubaAntesExamen;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ventana {

    private JLabel lblPrueba;
    private JPanel panel;

    public ventana() {
        JFrame frame = new JFrame("ventana");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pruebaIdioma");
        lblPrueba.setText(resourceBundle.getString("texto"));
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new ventana();
    }
}
