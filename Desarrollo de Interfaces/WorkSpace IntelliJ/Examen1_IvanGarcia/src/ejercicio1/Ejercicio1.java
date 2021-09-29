package ejercicio1;

import com.github.lgooddatepicker.components.DatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

public class Ejercicio1 {
    private JPanel contentPane;
    private JComboBox<LocalDate> cbFechas;
    private JButton btnAnadir;
    private DatePicker dpFecha;
    private JButton btnVerGrafica;
    private DefaultComboBoxModel<LocalDate> dcbm;

    public static void main(String[] args) {
        new Ejercicio1();
    }

    public Ejercicio1() {
        initComponents();
        initHandlers();

    }

    private void initComponents() {
        JFrame frame = new JFrame("Ejercicio1");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dcbm = new DefaultComboBoxModel<>();
        cbFechas.setModel(dcbm);
    }

    private void initHandlers() {
        btnAnadir.addActionListener(e -> dcbm.addElement(dpFecha.getDate()));
        btnVerGrafica.addActionListener(e -> visualizarGrafica());

        cbFechas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    dcbm.removeElement(cbFechas.getSelectedItem());
                }
            }
        });
    }

    private void visualizarGrafica() {
        int contadorAnteriores = 0;
        int contadorPosteriores = 0;


        for (int i = 0; i < dcbm.getSize(); i++) {
            if (cbFechas.getItemAt(i).getYear() < 2015) contadorAnteriores++;
            else contadorPosteriores++;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Anteriores a 2015", contadorAnteriores);
        dataset.setValue("Posteriores a 2015", contadorPosteriores);

        JFreeChart grafica = ChartFactory.createPieChart("Grafica", dataset);
        ChartFrame ventana = new ChartFrame("Fechas", grafica);
        ventana.pack();
        ventana.setVisible(true);
    }
}
