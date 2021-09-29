import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;

public class MiniaturaDescarga extends JPanel {

	/**
	 * Create the panel.
	 */
	public MiniaturaDescarga(String urlPortada, String titulo) {
		setLayout(new BorderLayout(10, 0));

		JPanel pnIzquierda = new JPanel();
		add(pnIzquierda, BorderLayout.WEST);
		pnIzquierda.setLayout(new BorderLayout(0, 0));

		JLabel lblPortadaJuego = new JLabel((new ImageIcon(urlPortada)));
		pnIzquierda.add(lblPortadaJuego);

		JLabel lblTituloJuego = new JLabel(titulo);
		pnIzquierda.add(lblTituloJuego, BorderLayout.SOUTH);

		JPanel pnIDerecha = new JPanel();
		add(pnIDerecha, BorderLayout.EAST);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setPreferredSize(new Dimension(100, 30));
		btnIniciar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnDetener = new JButton("Detener");
		btnDetener.setPreferredSize(new Dimension(100, 30));
		btnDetener.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetener.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnIDerecha.setLayout(new BoxLayout(pnIDerecha, BoxLayout.Y_AXIS));

		Component separador3 = Box.createVerticalGlue();
		pnIDerecha.add(separador3);
		pnIDerecha.add(btnIniciar);

		Component separador5 = Box.createVerticalStrut(20);
		pnIDerecha.add(separador5);
		pnIDerecha.add(btnDetener);

		Component separador4 = Box.createVerticalGlue();
		pnIDerecha.add(separador4);

		JPanel pnCentral = new JPanel();
		add(pnCentral, BorderLayout.CENTER);
		pnCentral.setLayout(new BoxLayout(pnCentral, BoxLayout.Y_AXIS));

		Component separador1 = Box.createVerticalGlue();
		pnCentral.add(separador1);

		JProgressBar pbDescarga = new JProgressBar();
		pbDescarga.setPreferredSize(new Dimension(146, 30));
		pbDescarga.setStringPainted(true);
		pnCentral.add(pbDescarga);

		Component separador2 = Box.createVerticalGlue();
		pnCentral.add(separador2);

	}

}
