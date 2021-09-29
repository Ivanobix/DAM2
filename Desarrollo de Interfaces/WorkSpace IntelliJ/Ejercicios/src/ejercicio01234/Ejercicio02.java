package ejercicio01234;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio02 {
    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;
    private JComboBox<String> comboBox1;

    private DefaultComboBoxModel<String> dcbm;

    public Ejercicio02() {
        JFrame frame = new JFrame("Ejercicio02");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.dcbm = new DefaultComboBoxModel<>();

        comboBox1.setModel(dcbm);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                dcbm.addElement(textField1.getText());

            }
        });
    }

    public static void main(String[] args) {

        new Ejercicio02();

    }
}
