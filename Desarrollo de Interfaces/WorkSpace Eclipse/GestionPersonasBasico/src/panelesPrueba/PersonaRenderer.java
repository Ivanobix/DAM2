package panelesPrueba;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import datos.Persona;
import java.awt.Color;

public class PersonaRenderer extends JPanel implements ListCellRenderer<Persona> {

	private static final long serialVersionUID = 3724311619597954404L;
	private JTextField txtNombre;
	private JTextField txtApellidos;

	public PersonaRenderer() {
		initUI();

	}

	private void initUI() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre);

		txtNombre = new JTextField();
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos:");
		add(lblApellidos);

		txtApellidos = new JTextField();
		add(txtApellidos);
		txtApellidos.setColumns(10);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Persona> arg0, Persona persona, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (persona != null) {
			txtNombre.setText(persona.getNombre());
			txtApellidos.setText(persona.getApellido());
		}
		if(isSelected) {
			this.setBackground(arg0.getSelectionBackground());
			this.setForeground(arg0.getSelectionForeground());
		} else {
			this.setBackground(arg0.getBackground());
			this.setForeground(arg0.getForeground());
		}
		return this;
	}

}
