package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;

import datos.Fabricante;
import layouts.WrapLayout;

/**
 * Ventana Gestión Fabricante. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo fabricante.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class VentanaGestionFabricante extends JDialog {

	private static final long serialVersionUID = 7567085602587536227L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Fabricante fabricanteAModificar;
	JTextField txtNombre;
	JTextField txtDireccion;
	JTextField txtTrabajadores;
	DatePicker dtFechaCreacion;
	JButton btnAnadir;
	JButton btnCancelar;
	JRadioButton rbtnSi;
	JRadioButton rbtnNo;

	/**
	 * Constructor para la creación de un nuevo fabricante.
	 */
	public VentanaGestionFabricante() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();
		fabricanteAModificar = null;
	}

	/**
	 * Constructor para la modificación de un fabricante.
	 * 
	 * @param fabricanteAModificar Frabricante a gestionar.
	 */
	public VentanaGestionFabricante(Fabricante fabricanteAModificar) {
		initComponents();
		txtNombre.setText(fabricanteAModificar.getNombre());
		txtDireccion.setText(fabricanteAModificar.getDireccion());
		txtTrabajadores.setText(String.valueOf(fabricanteAModificar.getTrabajadores()));
		dtFechaCreacion.setDate(fabricanteAModificar.getFechaCreacion());
		if (fabricanteAModificar.isInternacional()) {
			rbtnSi.setSelected(true);
		}
		btnAnadir.setText("Modificar");
		btnAnadir.setActionCommand("Modificar");
		this.fabricanteAModificar = fabricanteAModificar;
	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		getContentPane().setBackground(ControladorPrincipal.COLOR_FONDO);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaGestionFabricante.class.getResource("/media/Logo_Cafe.png")));
		setTitle("Gesti\u00F3n Fabricante");
		setBounds(100, 100, 520, 325);
		{
			JPanel pnDatos = new JPanel();
			getContentPane().add(pnDatos, BorderLayout.CENTER);
			pnDatos.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel pnIdentificador = new JPanel();
				pnIdentificador.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnIdentificador);
				pnIdentificador.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNombre = new JLabel("Nombre:");
					lblNombre.setFont(new Font("Courier New", Font.BOLD, 16));
					lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
					pnIdentificador.add(lblNombre, BorderLayout.NORTH);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setToolTipText("");
					txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
					txtNombre.setColumns(10);
					pnIdentificador.add(txtNombre, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnIdentificador.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnIdentificador.add(horizontalStrut, BorderLayout.EAST);
				}
			}
			{
				JPanel pnNombre = new JPanel();
				pnNombre.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnNombre);
				pnNombre.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
					lblDireccion.setFont(new Font("Courier New", Font.BOLD, 16));
					lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
					pnNombre.add(lblDireccion, BorderLayout.NORTH);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
					txtDireccion.setColumns(10);
					pnNombre.add(txtDireccion, BorderLayout.CENTER);
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
				JPanel pnTrabajadores = new JPanel();
				pnTrabajadores.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnTrabajadores);
				pnTrabajadores.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblTrabajadores = new JLabel("Trabajadores:");
					lblTrabajadores.setFont(new Font("Courier New", Font.BOLD, 16));
					lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
					pnTrabajadores.add(lblTrabajadores, BorderLayout.NORTH);
				}
				{
					txtTrabajadores = new JTextField();
					txtTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
					txtTrabajadores.setColumns(10);
					pnTrabajadores.add(txtTrabajadores, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnTrabajadores.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnTrabajadores.add(horizontalStrut, BorderLayout.EAST);
				}
			}
			{
				JPanel pnFechaCreacion = new JPanel();
				pnFechaCreacion.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnFechaCreacion);
				pnFechaCreacion.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFechaCreacion = new JLabel("Fecha Creaci\u00F3n:");
					lblFechaCreacion.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFechaCreacion.setHorizontalAlignment(SwingConstants.CENTER);
					pnFechaCreacion.add(lblFechaCreacion, BorderLayout.NORTH);
				}
				{
					dtFechaCreacion = new DatePicker();
					dtFechaCreacion.getComponentDateTextField().setEditable(false);
					dtFechaCreacion.getComponentDateTextField().setHorizontalAlignment(SwingConstants.CENTER);
					dtFechaCreacion.setDate(LocalDate.now());
					pnFechaCreacion.add(dtFechaCreacion, BorderLayout.CENTER);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaCreacion.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaCreacion.add(horizontalStrut, BorderLayout.EAST);
				}
			}
			{
				JPanel pnInternacional = new JPanel();
				pnInternacional.setBackground(ControladorPrincipal.COLOR_FONDO);
				pnDatos.add(pnInternacional);
				pnInternacional.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblInternacional = new JLabel("Internacional:");
					lblInternacional.setFont(new Font("Courier New", Font.BOLD, 16));
					lblInternacional.setHorizontalAlignment(SwingConstants.CENTER);
					pnInternacional.add(lblInternacional, BorderLayout.NORTH);
				}
				{
					JPanel pnBotonesInternacional = new JPanel();
					pnBotonesInternacional.setBackground(ControladorPrincipal.COLOR_FONDO);
					pnInternacional.add(pnBotonesInternacional, BorderLayout.CENTER);
					{
						rbtnSi = new JRadioButton("Si");
						rbtnSi.setBackground(ControladorPrincipal.COLOR_FONDO);
						buttonGroup.add(rbtnSi);
						pnBotonesInternacional.add(rbtnSi);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(50);
						pnBotonesInternacional.add(horizontalStrut);
					}
					{
						rbtnNo = new JRadioButton("No");
						rbtnNo.setBackground(ControladorPrincipal.COLOR_FONDO);
						buttonGroup.add(rbtnNo);
						rbtnNo.setSelected(true);
						pnBotonesInternacional.add(rbtnNo);
					}
				}
			}
		}
		{
			JPanel pnAcciones = new JPanel();
			pnAcciones.setBackground(ControladorPrincipal.COLOR_FONDO);
			getContentPane().add(pnAcciones, BorderLayout.SOUTH);
			WrapLayout wl_pnAcciones = new WrapLayout(FlowLayout.LEFT, 5, 3);
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
		{
			Component verticalStrut = Box.createVerticalStrut(10);
			getContentPane().add(verticalStrut, BorderLayout.NORTH);
		}
	}

}
