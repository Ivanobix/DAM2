package coffeetime.gui.visualizado;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Resumen Fabricante. Ventana dedicada a la visualización de todas las propiedades presentes en un elemento de tipo Fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ResumenFabricante extends JDialog {
    private final ResourceBundle idioma;

    private JPanel contentPane;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblTrabajadores;
    private JLabel lblInternacional;
    private JLabel lblFechaAlta;
    private JButton btnVerLotes;

    /**
     * Constructor.
     *
     * @param fabricante Fabricante a visualizar.
     * @param lotes Lista de lotes.
     */
    public ResumenFabricante(Fabricante fabricante, ArrayList<Lote> lotes) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        idioma = Util.obtenerTraducciones();
        setTitle(idioma.getString("ver.fabricante"));
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());

        lblIdentificador.setText(fabricante.getIdentificador());
        lblNombre.setText(fabricante.getNombre());
        lblDireccion.setText(fabricante.getDireccion());
        lblTrabajadores.setText(String.valueOf(fabricante.getTrabajadores()));
        lblFechaAlta.setText(fabricante.getFechaAlta().toString());

        if (fabricante.isInternacional()) {
            lblInternacional.setText("SI");
        } else {
            lblInternacional.setText("NO");
        }

        btnVerLotes.addActionListener(e -> new VerLotes(fabricante, lotes));
        btnVerLotes.setMnemonic(KeyEvent.VK_1);
    }
}
