package ejercicio2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio2 {
    private JPanel contentPane;
    private JButton btnAnadirImagen;
    private JList<String> listImagenes;
    private JButton btnMostrarGrafica;
    private JLabel lblImagen;
    private DefaultListModel<String> dlm;
    private ArrayList<String> imagenes;

    public Ejercicio2() {
        JFrame frame = new JFrame("Ejercicio2");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dlm = new DefaultListModel<>();
        listImagenes.setModel(dlm);
        imagenes = new ArrayList<>();

        btnAnadirImagen.addActionListener(e -> anadirImagen());
        btnMostrarGrafica.addActionListener(e -> mostrarGrafica());
        listImagenes.addListSelectionListener(e -> mostrarImagen());
    }

    public static void main(String[] args) {
        new Ejercicio2();
    }

    private void anadirImagen() {
        JFileChooser jFileChooser = new JFileChooser();
        int resultado = jFileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String ruta = jFileChooser.getSelectedFile().getAbsolutePath();
            dlm.addElement(ruta);
            imagenes.add(ruta);
        }
    }

    private void mostrarImagen() {
        String ruta = dlm.elementAt(listImagenes.getSelectedIndex());
        lblImagen.setIcon(new ImageIcon(ruta));
    }

    private void mostrarGrafica() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<String> extensiones = new ArrayList<>();

        for (String ruta : imagenes) {
            String[] separacion = ruta.split("\\.");
            String extension = separacion[separacion.length - 1];
            if (!extensiones.contains(extension)) {
                extensiones.add(extension);
            }
        }

        int contador = 0;
        for (String extension : extensiones) {
            for (String ruta : imagenes) {
                if (ruta.endsWith(extension)) {
                    contador++;
                }
            }
            dataset.setValue(extension, contador);
            contador = 0;
        }

        JFreeChart grafica = ChartFactory.createPieChart("Gráfica", dataset);
        ChartFrame ventana = new ChartFrame("Extensiones", grafica);
        ventana.pack();
        ventana.setVisible(true);
    }
}
