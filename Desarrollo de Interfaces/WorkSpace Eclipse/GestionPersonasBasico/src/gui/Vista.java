package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.CellRendererPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import datos.Persona;
import panelesPrueba.PersonaRenderer;

public class Vista extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7273283295721959312L;
	JPanel contentPane;
	JTextField txtNombre;
	JTextField txtApellidos;
	JButton btnNuevaPersonaJlist;
	JButton btnEliminarPersonaJlist;
	JButton btnNuevaPersonaComboBox;
	JButton btnEliminarPersonaComboBox;
	JComboBox<Persona> comboBox;
	DefaultComboBoxModel<Persona> dcbm;
	JList<Persona> list;
	DefaultListModel<Persona> dlm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {

		initComponents();
		
		dlm = new DefaultListModel<>();
		dcbm = new DefaultComboBoxModel<>();
		list.setModel(dlm);
		list.setCellRenderer(new PersonaRenderer());
		comboBox.setModel(dcbm);
		comboBox.setRenderer(new PersonaRenderer());
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(55, 10, 107, 18);
		contentPane.add(lblNombre);

		JLabel lblNewLabel = new JLabel("Apellidos:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(55, 34, 107, 17);
		contentPane.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setBounds(244, 8, 180, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(244, 34, 180, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		btnNuevaPersonaJlist = new JButton("A\u00F1adir persona a JList");
		btnNuevaPersonaJlist.setActionCommand("NuevaPersonaJlist");
		btnNuevaPersonaJlist.setBounds(10, 82, 159, 23);
		contentPane.add(btnNuevaPersonaJlist);

		btnEliminarPersonaJlist = new JButton("Eliminar persona de JList");
		btnEliminarPersonaJlist.setActionCommand("EliminarPersonaJlist");
		btnEliminarPersonaJlist.setBounds(10, 121, 159, 23);
		contentPane.add(btnEliminarPersonaJlist);

		btnNuevaPersonaComboBox = new JButton("A\u00F1adir persona a Combobox");
		btnNuevaPersonaComboBox.setActionCommand("NuevaPersonaComboBox");
		btnNuevaPersonaComboBox.setBounds(244, 82, 180, 23);
		contentPane.add(btnNuevaPersonaComboBox);

		btnEliminarPersonaComboBox = new JButton("Eliminar persona de ComboBox");
		btnEliminarPersonaComboBox.setActionCommand("EliminarPersonaComboBox");
		btnEliminarPersonaComboBox.setBounds(244, 121, 180, 23);
		contentPane.add(btnEliminarPersonaComboBox);

		comboBox = new JComboBox<Persona>();
		comboBox.setBounds(244, 171, 180, 22);
		contentPane.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 159, 79);
		contentPane.add(scrollPane);

		list = new JList<Persona>();
		scrollPane.setViewportView(list);
		
		btnEliminarPersonaComboBox.addActionListener(this);
		btnEliminarPersonaJlist.addActionListener(this);
		btnNuevaPersonaComboBox.addActionListener(this);
		btnNuevaPersonaJlist.addActionListener(this);
		list.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		case "NuevaPersonaJlist":
			dlm.addElement(new Persona(txtNombre.getText(), txtApellidos.getText()));
			break;
		case "EliminarPersonaJlist":
			dlm.removeElement(list.getSelectedValue());
			break;
		case "NuevaPersonaComboBox":
			dcbm.addElement(new Persona(txtNombre.getText(), txtApellidos.getText()));
			break;
		case "EliminarPersonaComboBox":
			dcbm.removeElement(comboBox.getSelectedItem());
			break;

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (list.getSelectedValue() != null) {
				dlm.removeElement(list.getSelectedValue());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {	}
}
