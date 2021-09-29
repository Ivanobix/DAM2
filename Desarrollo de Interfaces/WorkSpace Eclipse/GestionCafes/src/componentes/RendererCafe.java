package componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import datos.Cafe;

/**
 * RendererCafe. Clase utilizada para el renderizado de elementos tipo "Café"
 * para su posterior visualizado en diferentes componentes tales como JList.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class RendererCafe extends JPanel implements ListCellRenderer<Cafe> {

	private static final long serialVersionUID = -8668979756669333809L;
	private JLabel lblIdentificador2;
	private JLabel lblNombre2;

	/**
	 * Constructor.
	 */
	public RendererCafe() {
		initComponents();

	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		setForeground(new Color(255, 255, 255));
		setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		lblIdentificador.setForeground(new Color(255, 255, 255));
		add(lblIdentificador);

		lblIdentificador2 = new JLabel("");
		lblIdentificador2.setForeground(new Color(0, 255, 255));
		lblIdentificador2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblIdentificador2);

		JLabel lblSeparador = new JLabel(" // ");
		lblSeparador.setForeground(Color.WHITE);
		lblSeparador.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblSeparador);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblNombre);

		lblNombre2 = new JLabel("");
		lblNombre2.setForeground(new Color(0, 255, 255));
		lblNombre2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblNombre2);
	}

	/**
	 * Obtener el componente asociado a esta clase para su posterior implementación
	 * en una lista.
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Cafe> arg0, Cafe cafe, int index, boolean isSelected,
			boolean hasCellFocus) {
		if (cafe != null) {
			lblIdentificador2.setText(cafe.getIdentificador());
			lblNombre2.setText(cafe.getNombre());
		}
		if (isSelected) {
			this.setBackground(Color.BLACK);
		} else {
			this.setBackground(Color.DARK_GRAY);
		}
		return this;
	}

}
