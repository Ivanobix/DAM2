package ejercicio12;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class Ejer12 {
    private JButton seleccionarButton;
    private JPanel panel1;


    public Ejer12() {

        JFrame frame = new JFrame("Ejer12");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                realizarAccion();
            }
        });
    }

    private void realizarAccion() {

        JFileChooser selector = new JFileChooser();
        selector.showOpenDialog(null);
        File fichero = selector.getSelectedFile();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundleIdioma");
        System.out.println(resourceBundle.getString("mensaje.ruta.fichero") + fichero.getPath());


    }

    public static void main(String[] args) {

        //Locale.setDefault(Locale.US);
       //Locale.setDefault(Locale.CANADA_FRENCH);

        new Ejer12();

    }
}
