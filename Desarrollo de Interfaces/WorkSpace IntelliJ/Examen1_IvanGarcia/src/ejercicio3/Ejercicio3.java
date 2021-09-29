package ejercicio3;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Ejercicio3 {
    private JCheckBox chbxBebida;
    private JRadioButton rbEfectivo;
    private JRadioButton rbTarjeta;
    private JButton btnProcesar;
    private JPanel contentPane;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new Ejercicio3();
    }

    public Ejercicio3() {
        JFrame frame = new JFrame("Ejercicio3");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ResourceBundle bundle = ResourceBundle.getBundle("idiomas");

        btnProcesar.addActionListener(e -> {
            String mensaje = bundle.getString("incluirBebida");
            if (chbxBebida.isSelected())
                mensaje += bundle.getString("si");
            else mensaje += bundle.getString("no");

            mensaje += "  //  " + bundle.getString("metodoPago");

            if (rbEfectivo.isSelected())
                mensaje += bundle.getString("pagoEfectivo");
            else mensaje += bundle.getString("pagoTarjeta");

            JOptionPane.showConfirmDialog(frame, mensaje);
        });

    }

}
