import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class MiniaturaJuego extends JPanel {

	/**
	 * Create the panel.
	 */
	public MiniaturaJuego() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblPortada = new JLabel("");
		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPortada);
		
		JLabel lblTitulo = new JLabel("");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);

	}

}
