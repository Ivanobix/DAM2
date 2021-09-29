package ventana;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import datos.Coche;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Vista extends JFrame {

	private static final long serialVersionUID = -6330553860728930188L;
	JPanel contentPane;
	JTextField txtFMatricula;
	JTextField txtFModelo;
	JTextField txtFKm;
	DatePicker dateFab;
	JButton btnAnadir;
	JButton btnBorrar;
	JButton btnListar;
	JMenuItem mnitCargar;
	JMenuItem mnitGuardar;
	JMenuItem mnitSalir;
	JList<Coche> listConsola;
	DefaultListModel<Coche> dlm;

	public Vista() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar mnPrincipal = new JMenuBar();
		setJMenuBar(mnPrincipal);

		JMenu mnArchivo = new JMenu("Archivo");
		mnPrincipal.add(mnArchivo);

		mnitCargar = new JMenuItem("Cargar");
		mnArchivo.add(mnitCargar);

		mnitGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mnitGuardar);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnPrincipal.add(mnAyuda);

		mnitSalir = new JMenuItem("Salir");
		mnAyuda.add(mnitSalir);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setForeground(Color.WHITE);
		lblMatricula.setFont(new Font("Rockwell Nova Light", Font.BOLD, 15));
		lblMatricula.setBounds(20, 20, 100, 20);
		contentPane.add(lblMatricula);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Rockwell Nova Light", Font.BOLD, 15));
		lblModelo.setBounds(20, 60, 100, 20);
		contentPane.add(lblModelo);

		JLabel lblKm = new JLabel("Kil\u00F3metros:");
		lblKm.setForeground(Color.WHITE);
		lblKm.setFont(new Font("Rockwell Nova Light", Font.BOLD, 15));
		lblKm.setBounds(20, 100, 100, 20);
		contentPane.add(lblKm);

		JLabel lblAnoFab = new JLabel("Fabricaci\u00F3n:");
		lblAnoFab.setForeground(Color.WHITE);
		lblAnoFab.setFont(new Font("Rockwell Nova Light", Font.BOLD, 15));
		lblAnoFab.setBounds(20, 140, 100, 20);
		contentPane.add(lblAnoFab);

		txtFMatricula = new JTextField();
		txtFMatricula.setBounds(120, 20, 170, 20);
		contentPane.add(txtFMatricula);
		txtFMatricula.setColumns(10);

		txtFModelo = new JTextField();
		txtFModelo.setColumns(10);
		txtFModelo.setBounds(120, 60, 170, 20);
		contentPane.add(txtFModelo);

		txtFKm = new JTextField();
		txtFKm.setColumns(10);
		txtFKm.setBounds(120, 100, 170, 20);
		contentPane.add(txtFKm);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setActionCommand("Nuevo");
		btnAnadir.setBounds(320, 20, 80, 20);
		contentPane.add(btnAnadir);

		dateFab = new DatePicker();
		dateFab.setBounds(120, 140, 200, 20);
		contentPane.add(dateFab);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(320, 60, 80, 20);
		contentPane.add(btnBorrar);

		btnListar = new JButton("Listar");
		btnListar.setBounds(320, 100, 80, 20);
		contentPane.add(btnListar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 57);
		contentPane.add(scrollPane);

		dlm = new DefaultListModel<Coche>();
		listConsola = new JList<Coche>(dlm);
		listConsola.setForeground(Color.WHITE);
		listConsola.setBackground(Color.BLACK);
		scrollPane.setViewportView(listConsola);

	}
}
