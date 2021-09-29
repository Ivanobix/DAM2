package ejercicio01234;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ejercicio01 {
    private JPanel panel1;
    private JTextField textField1;
    private JButton annadirButton;
    private JList<String> list1;
    private DefaultListModel<String> dlm;
    private ArrayList<String> listaStrings;


    public Ejercicio01(){
        JFrame frame = new JFrame("Ejercicio01");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        listaStrings = new ArrayList<>();

        dlm = new DefaultListModel();
        list1.setModel(dlm);

        annadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listaStrings.add(textField1.getText());

                listar();
            }
        });
    }

    private void listar() {
        dlm.clear();

        for(String string : listaStrings){
            dlm.addElement(string);
        }

    }

    public static void main(String[] args) {

        new Ejercicio01();

    }


}
