package componentes;

import java.awt.Color;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import datos.Fabricante;

/**
 * RendererFabricante. Clase utilizada para el renderizado de elementos tipo
 * "Fabricante" para su posterior visualizado en diferentes componentes tales
 * como JList.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class RendererFabricante extends JPanel implements ListCellRenderer<Fabricante> {

	private static final long serialVersionUID = -8668979756669333809L;
	private JLabel lblIdentificador2;
	private JLabel lblNombre2;

	/**
	 * Constructor.
	 */
	public RendererFabricante() {
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
		lblIdentificador2.setForeground(Color.RED);
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
		lblNombre2.setForeground(new Color(255, 0, 0));
		lblNombre2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblNombre2);
	}

	/**
	 * Obtener el componente asociado a esta clase para su posterior implementación
	 * en una lista.
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Fabricante> arg0, Fabricante fabricante, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (fabricante != null) {
			lblIdentificador2.setText(fabricante.getIdentificador());
			lblNombre2.setText(fabricante.getNombre());
		}
		if (isSelected) {
			this.setBackground(new Color(0, 0, 0));
		} else {
			this.setBackground(arg0.getBackground());
		}
		return this;
	}

}
