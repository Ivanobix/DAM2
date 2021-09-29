package coffeetime.gui.visualizado;

import coffeetime.base.Cafe;
import coffeetime.base.Lote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Resumen Café. Ventana dedicada a la visualización de todas las propiedades presentes en un elemento de tipo Café.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ResumenCafe extends JDialog {

    private final ResourceBundle idioma;

    private JPanel contentPane;
    private JButton btnVerImagen;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel lblArabico;
    private JLabel lblRobusta;
    private JButton btnVerLotes;

    /**
     * Constructor.
     *
     * @param cafe Café a visualizar.
     * @param lotes Lista de lotes.
     */
    public ResumenCafe(Cafe cafe, ArrayList<Lote> lotes) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        idioma = Util.obtenerTraducciones();
        setTitle(idioma.getString("ver.cafe"));
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());

        contentPane.getRootPane().setDefaultButton(btnVerImagen);
        btnVerImagen.requestFocus();

        lblIdentificador.setText(cafe.getIdentificador());
        lblNombre.setText(cafe.getNombre());
        lblArabico.setText(cafe.getPorcentajeArabico() + "%");
        lblRobusta.setText(cafe.getPorcentajeRobusta() + "%");

        btnVerImagen.addActionListener(e -> Util.mostrarImagen(cafe.getImagenPromocional()));
        btnVerImagen.setMnemonic(KeyEvent.VK_1);

        btnVerLotes.addActionListener(e -> new VerLotes(cafe, lotes));
        btnVerLotes.setMnemonic(KeyEvent.VK_2);
    }
}
