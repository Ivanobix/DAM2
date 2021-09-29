package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import componentes.RendererCafe;
import componentes.RendererFabricante;
import datos.Cafe;
import datos.Fabricante;
import layouts.WrapLayout;

/**
 * Ventana Principal. Ventana principal de la aplicación desde la que se permite
 * el acceso a todas las operaciones establecidas para el usuario, así como la
 * visualización de datos y personalización de la misma.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 7050761703848842391L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JTabbedPane tabbedPane;
	JPanel pnCopyright;
	JPanel pnHerramientas;
	JPanel panelPrincipal;
	JPanel pnVentas;

	JMenuItem mnitNuevo;
	JMenuItem mnitGuardar;
	JMenuItem mnitCargar;
	JMenuItem mnitColorDeFondo;

	JButton btnNuevo;
	JButton btnEliminar;
	JButton btnModificar;
	JButton btnBuscar;

	JRadioButton rbtnNombre;
	JRadioButton rbtnIdentificador;

	JTextField txtBuscar;

	JList<Cafe> listCafe;
	JList<Fabricante> listFabricantes;
	JComboBox<Fabricante> cbInspeccionar;

	DefaultListModel<Cafe> dlmCafe;
	DefaultListModel<Fabricante> dlmFabricante;
	DefaultComboBoxModel<Fabricante> dcbm;

	Component horizontalStrut;
	private JPanel panel;

	/**
	 * Constructor.
	 */
	public VentanaPrincipal() {
		initComponents();
		dlmCafe = new DefaultListModel<>();
		dlmFabricante = new DefaultListModel<>();
		listCafe.setModel(dlmCafe);
		listCafe.setCellRenderer(new RendererCafe());
		listFabricantes.setModel(dlmFabricante);
		listFabricantes.setCellRenderer(new RendererFabricante());

	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/media/Logo_Cafe.png")));
		setTitle("Gesti\u00F3n de Caf\u00E9s");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 375);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Courier New", Font.PLAIN, 12));
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Courier New", Font.PLAIN, 12));
		menuBar.add(mnArchivo);

		mnitNuevo = new JMenuItem("Nuevo");
		mnitNuevo.setFont(new Font("Courier New", Font.PLAIN, 12));
		mnitNuevo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_nuevo.png")));
		mnArchivo.add(mnitNuevo);

		mnitGuardar = new JMenuItem("Guardar");
		mnitGuardar.setFont(new Font("Courier New", Font.PLAIN, 12));
		mnitGuardar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_guardar.png")));
		mnArchivo.add(mnitGuardar);

		mnitCargar = new JMenuItem("Cargar");
		mnitCargar.setFont(new Font("Courier New", Font.PLAIN, 12));
		mnitCargar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_cargar.png")));
		mnArchivo.add(mnitCargar);

		JMenu mnVentana = new JMenu("Ventana");
		mnVentana.setFont(new Font("Courier New", Font.PLAIN, 12));
		menuBar.add(mnVentana);

		mnitColorDeFondo = new JMenuItem("Color de Fondo");
		mnitColorDeFondo.setFont(new Font("Courier New", Font.PLAIN, 12));
		mnitColorDeFondo.setActionCommand("ColorFondo");
		mnitColorDeFondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_color.png")));
		mnVentana.add(mnitColorDeFondo);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);

		pnHerramientas = new JPanel();
		pnHerramientas.setBackground(Color.WHITE);
		panelPrincipal.add(pnHerramientas, BorderLayout.NORTH);
		WrapLayout wl_pnHerramientas = new WrapLayout(FlowLayout.LEFT, 5, 3);
		pnHerramientas.setLayout(wl_pnHerramientas);

		btnNuevo = new JButton("");
		btnNuevo.setBackground(Color.WHITE);
		btnNuevo.setToolTipText("A\u00F1adir");
		btnNuevo.setActionCommand("Anadir");
		btnNuevo.setPreferredSize(new Dimension(24, 24));
		btnNuevo.setSize(new Dimension(16, 16));
		btnNuevo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_anadir.png")));
		pnHerramientas.add(btnNuevo);

		btnEliminar = new JButton("");
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.setPreferredSize(new Dimension(24, 24));
		btnEliminar.setSize(new Dimension(16, 16));
		btnEliminar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_eliminar.png")));
		pnHerramientas.add(btnEliminar);

		btnModificar = new JButton("");
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setToolTipText("Modificar");
		btnModificar.setActionCommand("Modificar");
		btnModificar.setPreferredSize(new Dimension(24, 24));
		btnModificar.setSize(new Dimension(16, 16));
		btnModificar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_modificar.png")));
		pnHerramientas.add(btnModificar);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setToolTipText("Mostrar");
		btnBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/media/ico_buscar.png")));
		btnBuscar.setSize(new Dimension(16, 16));
		btnBuscar.setPreferredSize(new Dimension(24, 24));
		btnBuscar.setActionCommand("Buscar");
		pnHerramientas.add(btnBuscar);

		horizontalStrut = Box.createHorizontalStrut(40);
		pnHerramientas.add(horizontalStrut);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscar.setPreferredSize(new Dimension(0, 24));
		txtBuscar.setToolTipText("Filtrar");
		txtBuscar.setColumns(10);
		pnHerramientas.add(txtBuscar);

		rbtnNombre = new JRadioButton("Nombre");
		rbtnNombre.setActionCommand("CambiarFiltrado");
		rbtnNombre.setSelected(true);
		rbtnNombre.setFont(new Font("Courier New", Font.PLAIN, 11));
		buttonGroup.add(rbtnNombre);
		rbtnNombre.setBackground(Color.WHITE);
		pnHerramientas.add(rbtnNombre);

		rbtnIdentificador = new JRadioButton("Identificador");
		rbtnIdentificador.setActionCommand("CambiarFiltrado");
		rbtnIdentificador.setFont(new Font("Courier New", Font.PLAIN, 11));
		buttonGroup.add(rbtnIdentificador);
		rbtnIdentificador.setBackground(Color.WHITE);
		pnHerramientas.add(rbtnIdentificador);

		cbInspeccionar = new JComboBox<Fabricante>();
		cbInspeccionar.setBackground(Color.DARK_GRAY);
		cbInspeccionar.setToolTipText("Ver caf\u00E9s del fabricante");
		cbInspeccionar.setVisible(false);
		cbInspeccionar.setPreferredSize(new Dimension(500, 30));
		pnHerramientas.add(cbInspeccionar);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Courier New", Font.PLAIN, 12));
		panelPrincipal.add(tabbedPane, BorderLayout.CENTER);

		JPanel pnCafes = new JPanel();
		tabbedPane.addTab("Cafés", null, pnCafes, null);
		pnCafes.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollCafes = new JScrollPane();
		pnCafes.add(scrollCafes);

		listCafe = new JList<Cafe>();
		listCafe.setForeground(Color.DARK_GRAY);
		listCafe.setBackground(Color.DARK_GRAY);
		scrollCafes.setViewportView(listCafe);

		JPanel pnFabricantes = new JPanel();
		tabbedPane.addTab("Fabricantes", null, pnFabricantes, null);
		pnFabricantes.setLayout(new BoxLayout(pnFabricantes, BoxLayout.X_AXIS));

		JScrollPane scrollFabricantes = new JScrollPane();
		pnFabricantes.add(scrollFabricantes);

		listFabricantes = new JList<Fabricante>();
		listFabricantes.setBackground(Color.DARK_GRAY);
		scrollFabricantes.setViewportView(listFabricantes);

		pnCopyright = new JPanel();
		pnCopyright.setBackground(Color.WHITE);
		panelPrincipal.add(pnCopyright, BorderLayout.SOUTH);
		pnCopyright.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCopyright = new JLabel("Iv\u00E1n Garc\u00EDa Prieto \u00A9 ");
		lblCopyright.setFont(new Font("Courier New", Font.PLAIN, 14));
		pnCopyright.add(lblCopyright);

		panel = new JPanel();
		tabbedPane.addTab("Productos", null, panel, null);
		panel.setLayout(new BorderLayout());

		JScrollPane scrollVentas = new JScrollPane();
		panel.add(scrollVentas, BorderLayout.CENTER);

		pnVentas = new JPanel();
		pnVentas.setBackground(Color.DARK_GRAY);
		scrollVentas.setViewportView(pnVentas);
		pnVentas.setLayout(new BoxLayout(pnVentas, BoxLayout.Y_AXIS));

		dcbm = new DefaultComboBoxModel<Fabricante>();
		cbInspeccionar.setModel(dcbm);
		cbInspeccionar.setRenderer(new RendererFabricante());
	}

}
