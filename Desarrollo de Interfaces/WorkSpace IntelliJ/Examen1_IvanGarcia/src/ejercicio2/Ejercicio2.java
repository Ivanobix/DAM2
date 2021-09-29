package ejercicio2;

import javax.swing.*;
import java.awt.*;

public class Ejercicio2 {
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JTextField txtEstilo;
    private JList<Pelicula> listPeliculas;
    private JButton btnDarDeAlta;
    private JPanel contentPane;
    private JButton btnVerImagen;
    private DefaultListModel<Pelicula> dlm;

    public static void main(String[] args) {
        new Ejercicio2();
    }

    public Ejercicio2() {
        initComponents();

        btnDarDeAlta.addActionListener(e -> darDeAlta());
        btnVerImagen.addActionListener(e -> verImagen());

        listPeliculas.addListSelectionListener(e -> {
            txtTitulo.setText(dlm.get(listPeliculas.getSelectedIndex()).getTitulo());
            txtEstilo.setText(dlm.get(listPeliculas.getSelectedIndex()).getEstilo());
            txtDuracion.setText(String.valueOf(dlm.get(listPeliculas.getSelectedIndex()).getDuracion()));
        });

    }

    private void initComponents() {
        JFrame frame = new JFrame("Ejercicio2");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dlm = new DefaultListModel<>();
        listPeliculas.setModel(dlm);
    }

    private void darDeAlta() {
        String titulo = txtTitulo.getText();
        String estilo = txtEstilo.getText();
        int duracion = Integer.parseInt(txtDuracion.getText());
        String ruta = "";
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(contentPane);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            ruta = chooser.getSelectedFile().getAbsolutePath();
        }
        dlm.addElement(new Pelicula(titulo, estilo, duracion, ruta));
    }

    private void verImagen() {
        JFrame frameImagen = new JFrame("Imagen");
        JPanel panel = new JPanel(new FlowLayout());
        frameImagen.setContentPane(panel);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(listPeliculas.getSelectedValue().getRuta()));
        panel.add(label);

        frameImagen.pack();
        frameImagen.setVisible(true);
    }
}
