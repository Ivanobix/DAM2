package ventana;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextField txtFDni;
	JTextField txtFNombre;
	JTextArea txtAComentarios;
	JButton btnNuevo;
	JButton btnEliminar;
	JButton btnListar;
	DatePicker datePicker;
	JMenuItem mnitGuardar;
	JMenuItem mnitCargar;
	JMenuItem mnitSalir;

	public Ventana() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);

		mnitGuardar = new JMenuItem("Guardar");
		menuArchivo.add(mnitGuardar);

		mnitCargar = new JMenuItem("Cargar");
		menuArchivo.add(mnitCargar);

		JMenu menuAyuda = new JMenu("Ayuda");
		menuBar.add(menuAyuda);

		mnitSalir = new JMenuItem("Salir");
		menuAyuda.add(mnitSalir);

		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		lblDni.setBounds(33, 27, 46, 21);
		contentPane.add(lblDni);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		lblNombre.setBounds(33, 70, 54, 14);
		contentPane.add(lblNombre);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		lblFechaNacimiento.setBounds(33, 106, 132, 21);
		contentPane.add(lblFechaNacimiento);

		txtFDni = new JTextField();
		txtFDni.setBounds(201, 26, 165, 20);
		contentPane.add(txtFDni);
		txtFDni.setColumns(10);

		txtFNombre = new JTextField();
		txtFNombre.setColumns(10);
		txtFNombre.setBounds(201, 66, 165, 20);
		contentPane.add(txtFNombre);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(30, 138, 89, 23);
		contentPane.add(btnNuevo);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(170, 138, 89, 23);
		contentPane.add(btnEliminar);

		btnListar = new JButton("Listar");
		btnListar.setBounds(318, 138, 89, 23);
		contentPane.add(btnListar);

		txtAComentarios = new JTextArea();
		txtAComentarios.setForeground(Color.WHITE);
		txtAComentarios.setBackground(Color.BLACK);
		txtAComentarios.setEditable(false);
		txtAComentarios.setBounds(33, 182, 374, 46);
		contentPane.add(txtAComentarios);

		datePicker = new DatePicker();
		datePicker.setBounds(201, 106, 194, 20);
		contentPane.add(datePicker);
	}
}
