package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;

import componentes.RendererFabricante;
import datos.Cafe;
import datos.Fabricante;
import layouts.WrapLayout;

import java.awt.Color;

/**
 * Ventana Gestión Fabricante. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo café.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class VentanaGestionCafe extends JDialog {

	private static final long serialVersionUID = -7577049462372683023L;
	Cafe cafeAModificar;
	JTextField txtNombre;
	JTextField txtArabico;
	JTextField txtRobusta;
	DatePicker dtFechaEnvasado;
	JButton btnAnadir;
	JButton btnCancelar;
	JComboBox<Fabricante> cbFabricante;
	DefaultComboBoxModel<Fabricante> dcbm;

	/**
	 * Constructor para la creación de un nuevo café.
	 */
	public VentanaGestionCafe() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();
	}

	/**
	 * Constructor para la modificación de un café.
	 * 
	 * @param cafeAModificar Cafe a gestionar.
	 */
	public VentanaGestionCafe(Cafe cafeAModificar) {
		initComponents();
		this.cafeAModificar = cafeAModificar;
		txtNombre.setText(cafeAModificar.getNombre());
		txtArabico.setText(String.valueOf(cafeAModificar.getPorcentajeArabico()));
		txtRobusta.setText(String.valueOf(cafeAModificar.getPorcentajeRobusta()));
		dtFechaEnvasado.setDate(cafeAModificar.getFechaEnvasado());
		cbFabricante.setSelectedItem(cafeAModificar.getFabricante());
		btnAnadir.setText("Modificar");
		btnAnadir.setActionCommand("Modificar");

	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		getContentPane().setBackground(ControladorPrincipal.COLOR_FONDO);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaGestionCafe.class.getResource("/media/Logo_Cafe.png")));
		setTitle("Gesti\u00F3n Caf\u00E9");
		setBounds(100, 100, 520, 325);
		{
			JPanel pnDatos = new JPanel();
			getContentPane().add(pnDatos, BorderLayout.CENTER);
			pnDatos.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel pnNombre = new JPanel();
				pnNombre.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnNombre);
				pnNombre.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNombre = new JLabel("Nombre:");
					lblNombre.setFont(new Font("Courier New", Font.BOLD, 16));
					lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
					pnNombre.add(lblNombre, BorderLayout.NORTH);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setToolTipText("");
					txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
					txtNombre.setColumns(10);
					pnNombre.add(txtNombre, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnNombre.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnNombre.add(horizontalStrut, BorderLayout.EAST);
				}
			}
			{
				JPanel pnArabico = new JPanel();
				pnArabico.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnArabico);
				pnArabico.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblArabico = new JLabel("Ar\u00E1bico (%):");
					lblArabico.setFont(new Font("Courier New", Font.BOLD, 16));
					lblArabico.setHorizontalAlignment(SwingConstants.CENTER);
					pnArabico.add(lblArabico, BorderLayout.NORTH);
				}
				{
					txtArabico = new JTextField();
					txtArabico.setHorizontalAlignment(SwingConstants.CENTER);
					txtArabico.setColumns(10);
					pnArabico.add(txtArabico, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnArabico.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnArabico.add(horizontalStrut, BorderLayout.WEST);
				}
			}
			{
				JPanel pnRobusta = new JPanel();
				pnRobusta.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnRobusta);
				pnRobusta.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblRobusta = new JLabel("Robusta (%):");
					lblRobusta.setFont(new Font("Courier New", Font.BOLD, 16));
					lblRobusta.setHorizontalAlignment(SwingConstants.CENTER);
					pnRobusta.add(lblRobusta, BorderLayout.NORTH);
				}
				{
					txtRobusta = new JTextField();
					txtRobusta.setHorizontalAlignment(SwingConstants.CENTER);
					txtRobusta.setColumns(10);
					pnRobusta.add(txtRobusta, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnRobusta.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnRobusta.add(horizontalStrut, BorderLayout.WEST);
				}
			}
			{
				JPanel pnFechaEnvasado = new JPanel();
				pnFechaEnvasado.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnFechaEnvasado);
				pnFechaEnvasado.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFechaEnvasado = new JLabel("Fecha Envasado:");
					lblFechaEnvasado.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFechaEnvasado.setHorizontalAlignment(SwingConstants.CENTER);
					pnFechaEnvasado.add(lblFechaEnvasado, BorderLayout.NORTH);
				}
				{
					dtFechaEnvasado = new DatePicker();
					dtFechaEnvasado.getComponentDateTextField().setEditable(false);
					dtFechaEnvasado.getComponentDateTextField().setHorizontalAlignment(SwingConstants.CENTER);
					dtFechaEnvasado.setDate(LocalDate.now());
					pnFechaEnvasado.add(dtFechaEnvasado, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaEnvasado.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaEnvasado.add(horizontalStrut, BorderLayout.EAST);
				}
			}
			{
				JPanel pnFabricante = new JPanel();
				pnFabricante.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnFabricante);
				pnFabricante.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFabricante = new JLabel("Fabricante:");
					lblFabricante.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFabricante.setHorizontalAlignment(SwingConstants.CENTER);
					pnFabricante.add(lblFabricante, BorderLayout.NORTH);
				}
				{
					cbFabricante = new JComboBox<Fabricante>();
					cbFabricante.setBackground(Color.DARK_GRAY);
					pnFabricante.add(cbFabricante, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFabricante.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFabricante.add(horizontalStrut, BorderLayout.WEST);
				}
			}
		}
		{
			Component verticalStrut = Box.createVerticalStrut(10);
			getContentPane().add(verticalStrut, BorderLayout.NORTH);
		}
		{
			JPanel pnAcciones = new JPanel();
			pnAcciones.setBackground(ControladorPrincipal.COLOR_FONDO);
			getContentPane().add(pnAcciones, BorderLayout.SOUTH);
			WrapLayout wl_pnAcciones = new WrapLayout(0, 5, 3);
			wl_pnAcciones.setAlignment(FlowLayout.CENTER);
			pnAcciones.setLayout(wl_pnAcciones);
			{
				btnAnadir = new JButton("A\u00F1adir");
				btnAnadir.setActionCommand("Anadir");
				btnAnadir.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
				pnAcciones.add(btnAnadir);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(35);
				pnAcciones.add(verticalStrut);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
				pnAcciones.add(btnCancelar);
			}
		}
		dcbm = new DefaultComboBoxModel<Fabricante>();
		cbFabricante.setModel(dcbm);
		cbFabricante.setRenderer(new RendererFabricante());
	}

}
