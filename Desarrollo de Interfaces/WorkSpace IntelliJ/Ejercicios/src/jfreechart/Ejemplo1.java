package jfreechart;

import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Ejemplo1 {
    private JPanel panel1;
    private JTextField nombre1Txt;
    private JTextField nombre2Txt;
    private JTextField nombre3Txt;
    private JTextField valor1Txt;
    private JTextField valor2Txt;
    private JTextField valor3Txt;
    private JButton mostrarGraficaButton;
    private JPanel panelDiagrama;

    public Ejemplo1() {
        JFrame frame = new JFrame("Ejemplo1");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        mostrarGraficaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarGrafica();
            }
        });
    }

    private void mostrarGrafica() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue(nombre1Txt.getText(), Double.parseDouble(valor1Txt.getText()));
        dataset.setValue(nombre2Txt.getText(), Double.parseDouble(valor2Txt.getText()));
        dataset.setValue(nombre3Txt.getText(), Double.parseDouble(valor3Txt.getText()));

        JFreeChart diagrama = ChartFactory.createPieChart3D("grafico", dataset);
/*
        ChartFrame frame  = new ChartFrame("Ventana", diagrama);

        frame.setSize(500,500);

        frame.setVisible(true);
*/

        ChartPanel panel = new ChartPanel(diagrama);
        panelDiagrama.add(panel);

        try {
            ChartUtilities.saveChartAsJPEG(new File("diagrama.jpg"), diagrama, 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Ejemplo1 ejemplo1 = new Ejemplo1();
    }
}
