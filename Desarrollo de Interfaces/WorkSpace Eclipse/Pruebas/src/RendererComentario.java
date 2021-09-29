import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RendererComentario extends DefaultListCellRenderer {

	public RendererComentario() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNombreUsuario = new JLabel("Usuario");
		panel.add(lblNombreUsuario);

		JLabel lblFechaPublicacion = new JLabel("2021/04/24");
		panel.add(lblFechaPublicacion, BorderLayout.EAST);

		JTextArea textArea = new JTextArea();
		add(textArea, BorderLayout.CENTER);

	}

}
