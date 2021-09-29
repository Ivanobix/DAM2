package gui.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import base.Coche;
import base.Conductor;
import layouts.WrapLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import barraestado.BarraEstado;

/**
 * Clase que representa la ventana de la aplicacion. Se isntancian
 * los componentes graficos necesarios. 
 * @author Fer
 *
 */
public class Vista extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JTextField txtMatricula;
	JTextField txtModelo;
	JTextField txtKilometros;
	DatePicker datePickerCoche;
	JButton btnNuevoCoche;
	JButton btnEliminarCoche;
	JButton btnGuardar;
	JButton btnCargar;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JMenuItem menuItemCargar;
	JMenuItem menuItemGuardar;
	JTextField txtBuscarVehiculo;
	JButton btnModificar;
	JTextField txtNombreConductor;
	JTextField txtApellidosConductor;
	JTextField txtDniConductor;
	JCheckBox chboxNovel;
	JSpinner spinnerExperiencia;
	JButton btnNuevoConductor;
	BarraEstado barraEstado;
	JPanel panelDinamico;
	
	JComboBox<Conductor> cbConductorCoche;
	JList<Coche> listCoches;
	
	//Modelo de JList y ComboBox
	DefaultListModel<Coche> dlmCoches;
	DefaultComboBoxModel<Conductor> dcbmConductorCoche;
	
	
	/**
	 * Constructor de la clase
	 */
	public Vista() {
		initComponents();
		setVisible(true);
		
		//Para mostrar la ventana en el centro de la pantalla
		setLocationRelativeTo(null);
		
		//Inicio los modelo de lista y combobox
		iniciarListasYCombos();
	}


	/** Metodo que construye los DefaultListModels de JList
	 * y DefaultComboBoxModel de combos
	 */
	private void iniciarListasYCombos() {
		dlmCoches = new DefaultListModel<Coche>();
		listCoches.setModel(dlmCoches);
		
		dcbmConductorCoche =  new DefaultComboBoxModel<Conductor>();
		cbConductorCoche.setModel(dcbmConductorCoche);
	}

	
	/** Metodo encargado de construir y dar valor
	 * a todos los componentes gráficos de la aplicacion
	 */
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Vista.class.getResource("/img/arrow-right.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		menuItemGuardar = new JMenuItem("Guardar");
		mnNewMenu.add(menuItemGuardar);
		
		menuItemCargar = new JMenuItem("Cargar");
		mnNewMenu.add(menuItemCargar);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		//Puedo utilizar esta linea o las siguientes
		panel.setLayout(new WrapLayout(FlowLayout.LEFT, 1, 3));
		
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnGuardar = new JButton("");
		btnGuardar.setToolTipText("Nuevo");
		btnGuardar.setPreferredSize(new Dimension(20, 20));
		btnGuardar.setSize(new Dimension(20, 20));
		btnGuardar.setIcon(new ImageIcon(Vista.class.getResource("/img/arrow-refresh-small.png")));
		panel.add(btnGuardar);
		
		btnCargar = new JButton("");
		btnCargar.setPreferredSize(new Dimension(20, 20));
		btnCargar.setToolTipText("Guardar");
		btnCargar.setIcon(new ImageIcon(Vista.class.getResource("/img/arrow-right-2.png")));

		panel.add(btnCargar);
		
		btnNewButton = new JButton("");
		btnNewButton.setPreferredSize(new Dimension(20, 20));
		btnNewButton.setToolTipText("Cargar");
		btnNewButton.setIcon(new ImageIcon(Vista.class.getResource("/img/arrow-right-3.png")));
		
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setPreferredSize(new Dimension(20, 20));
		btnNewButton_1.setIcon(new ImageIcon(Vista.class.getResource("/img/arrow-right.png")));
		
		panel.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		barraEstado = new BarraEstado();
		panel_3.add(barraEstado);
		
		JLabel lblNewLabel = new JLabel("Fernando Soft 2020");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Coches", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		listCoches = new JList<Coche>();

		scrollPane.setViewportView(listCoches);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		btnNuevoCoche = new JButton("Nuevo");
		btnNuevoCoche.setActionCommand("NuevoCoche");
		panel_4.add(btnNuevoCoche);
		
		btnEliminarCoche = new JButton("Eliminar");
		btnEliminarCoche.setActionCommand("EliminarCoche");
		panel_4.add(btnEliminarCoche);
		
		btnModificar = new JButton("Modificar");
		panel_4.add(btnModificar);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Matricula:");
		panel_7.add(lblNewLabel_1);
		
		txtMatricula = new JTextField();
		panel_7.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Modelo:");
		panel_8.add(lblNewLabel_2);
		
		txtModelo = new JTextField();
		panel_8.add(txtModelo);
		txtModelo.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Kilometros:");
		panel_9.add(lblNewLabel_3);
		
		txtKilometros = new JTextField();
		panel_9.add(txtKilometros);
		txtKilometros.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Fecha Fabricaci\u00F3n:");
		panel_10.add(lblNewLabel_4);
		
		datePickerCoche = new DatePicker();
		datePickerCoche.getComponentToggleCalendarButton().setIcon(new ImageIcon(Vista.class.getResource("/img/view-calendar-month.png")));
		datePickerCoche.getComponentToggleCalendarButton().setText("");
		datePickerCoche.setDateToToday();
		panel_10.add(datePickerCoche);
		
		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Conductor:");
		panel_11.add(lblNewLabel_5);
		
		cbConductorCoche = new JComboBox<Conductor>();
		panel_11.add(cbConductorCoche);
		
		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0};
		gbl_panel_12.rowHeights = new int[]{0};
		gbl_panel_12.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Buscar (matricula):");
		panel_13.add(lblNewLabel_6);
		
		txtBuscarVehiculo = new JTextField();
		txtBuscarVehiculo.setColumns(10);
		panel_13.add(txtBuscarVehiculo);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Conductores", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_5.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15);
		
		JLabel lblNewLabel_9 = new JLabel("DNI:");
		panel_15.add(lblNewLabel_9);
		
		txtDniConductor = new JTextField();
		panel_15.add(txtDniConductor);
		txtDniConductor.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_14.add(panel_16);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre:");
		panel_16.add(lblNewLabel_7);
		
		txtNombreConductor = new JTextField();
		panel_16.add(txtNombreConductor);
		txtNombreConductor.setColumns(10);
		
		JPanel panel_17 = new JPanel();
		panel_14.add(panel_17);
		
		JLabel lblNewLabel_8 = new JLabel("Apellidos:");
		panel_17.add(lblNewLabel_8);
		
		txtApellidosConductor = new JTextField();
		panel_17.add(txtApellidosConductor);
		txtApellidosConductor.setColumns(10);
		
		chboxNovel = new JCheckBox("Conductor Novel");
		chboxNovel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(chboxNovel);
		
		JPanel panel_18 = new JPanel();
		panel_14.add(panel_18);
		
		JLabel lblNewLabel_10 = new JLabel("A\u00F1os Experiencia");
		panel_18.add(lblNewLabel_10);
		
		spinnerExperiencia = new JSpinner();
		spinnerExperiencia.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		panel_18.add(spinnerExperiencia);
		
		btnNuevoConductor = new JButton("Nuevo");

		btnNuevoConductor.setActionCommand("NuevoConductor");
		panel_14.add(btnNuevoConductor);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, BorderLayout.CENTER);
		
		panelDinamico = new JPanel();
		scrollPane_1.setViewportView(panelDinamico);
		panelDinamico.setLayout(new BoxLayout(panelDinamico, BoxLayout.Y_AXIS));
	}

}
