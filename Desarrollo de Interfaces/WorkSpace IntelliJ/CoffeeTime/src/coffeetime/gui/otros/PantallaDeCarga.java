package coffeetime.gui.otros;

import javax.swing.*;

/**
 * Pantalla de Carga. Ventana dedicada a la representación visual del proceso de carga inicial.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class PantallaDeCarga extends JDialog implements Runnable {
    private JPanel contentPane;
    private JProgressBar barraProgreso;

    /**
     * Constructor.
     */
    public PantallaDeCarga() {
        setContentPane(contentPane);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Aumenta el valor de la barra de progreso a medida que pasa el tiempo establecido.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                barraProgreso.setValue(i);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        dispose();
    }
}
