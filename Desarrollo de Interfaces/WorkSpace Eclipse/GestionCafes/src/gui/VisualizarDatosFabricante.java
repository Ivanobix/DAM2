package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import datos.Fabricante;

/**
 * Visualizar Datos Café. Ventana dedicada a la visualización de objetos tipo
 * fabricante.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class VisualizarDatosFabricante extends JDialog {

	private static final long serialVersionUID = -7577049462372683023L;
	private JLabel lblIdentificador2;
	private JLabel lblNombre2;
	private JLabel lblDireccion2;
	private JLabel lblTrabajadores2;
	private JLabel lblFechaCreacion2;
	private JLabel lblInternacional2;

	/**
	 * Constructor.
	 * 
	 * @param fabricanteAVisualizar Fabricante a visualizar.
	 */
	public VisualizarDatosFabricante(Fabricante fabricanteAVisualizar) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();

		lblIdentificador2.setText(fabricanteAVisualizar.getIdentificador());
		lblNombre2.setText(fabricanteAVisualizar.getNombre());
		lblDireccion2.setText(String.valueOf(fabricanteAVisualizar.getDireccion()));
		lblTrabajadores2.setText(String.valueOf(fabricanteAVisualizar.getTrabajadores()));
		lblFechaCreacion2.setText(fabricanteAVisualizar.getFechaCreacion().toString());
		String internacional = "NO";
		if (fabricanteAVisualizar.isInternacional()) {
			internacional = "SI";
		}
		lblInternacional2.setText(internacional);

	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		getContentPane().setBackground(ControladorPrincipal.COLOR_FONDO);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VisualizarDatosFabricante.class.getResource("/media/Logo_Cafe.png")));
		setTitle("Visualizar Datos Fabricante");
		setBounds(100, 100, 520, 325);
		{
			JPanel pnDatos = new JPanel();
			pnDatos.setBackground(Color.DARK_GRAY);
			getContentPane().add(pnDatos, BorderLayout.CENTER);
			pnDatos.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel pnIdentificador = new JPanel();
				pnIdentificador.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnIdentificador);
				pnIdentificador.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblIdentificador = new JLabel("--- Identificador: ---");
					lblIdentificador.setForeground(Color.WHITE);
					lblIdentificador.setHorizontalAlignment(SwingConstants.CENTER);
					lblIdentificador.setFont(new Font("Courier New", Font.BOLD, 16));
					pnIdentificador.add(lblIdentificador, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnIdentificador.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnIdentificador.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					lblIdentificador2 = new JLabel("ASD");
					lblIdentificador2.setHorizontalAlignment(SwingConstants.CENTER);
					lblIdentificador2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblIdentificador2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnIdentificador.add(lblIdentificador2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnNombre = new JPanel();
				pnNombre.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnNombre);
				pnNombre.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNombre = new JLabel("--- Nombre: ---");
					lblNombre.setForeground(Color.WHITE);
					lblNombre.setFont(new Font("Courier New", Font.BOLD, 16));
					lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
					pnNombre.add(lblNombre, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnNombre.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnNombre.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					lblNombre2 = new JLabel("ASD");
					lblNombre2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblNombre2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNombre2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnNombre.add(lblNombre2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnArabico = new JPanel();
				pnArabico.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnArabico);
				pnArabico.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblDireccion = new JLabel("--- Direcci\u00F3n: ---");
					lblDireccion.setForeground(Color.WHITE);
					lblDireccion.setFont(new Font("Courier New", Font.BOLD, 16));
					lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
					pnArabico.add(lblDireccion, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnArabico.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnArabico.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					lblDireccion2 = new JLabel("ASD");
					lblDireccion2.setHorizontalAlignment(SwingConstants.CENTER);
					lblDireccion2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblDireccion2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnArabico.add(lblDireccion2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnRobusta = new JPanel();
				pnRobusta.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnRobusta);
				pnRobusta.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblTrabajadores = new JLabel("--- Trabajadores: ---");
					lblTrabajadores.setForeground(Color.WHITE);
					lblTrabajadores.setFont(new Font("Courier New", Font.BOLD, 16));
					lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
					pnRobusta.add(lblTrabajadores, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnRobusta.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnRobusta.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					lblTrabajadores2 = new JLabel("ASD");
					lblTrabajadores2.setHorizontalAlignment(SwingConstants.CENTER);
					lblTrabajadores2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblTrabajadores2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnRobusta.add(lblTrabajadores2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnFechaEnvasado = new JPanel();
				pnFechaEnvasado.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnFechaEnvasado);
				pnFechaEnvasado.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFechaCreacion = new JLabel("--- Fecha Creaci\u00F3n: ---");
					lblFechaCreacion.setForeground(Color.WHITE);
					lblFechaCreacion.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFechaCreacion.setHorizontalAlignment(SwingConstants.CENTER);
					pnFechaEnvasado.add(lblFechaCreacion, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaEnvasado.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFechaEnvasado.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					lblFechaCreacion2 = new JLabel("ASD");
					pnFechaEnvasado.add(lblFechaCreacion2, BorderLayout.CENTER);
					lblFechaCreacion2.setHorizontalAlignment(SwingConstants.CENTER);
					lblFechaCreacion2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblFechaCreacion2.setFont(new Font("Courier New", Font.BOLD, 14));
				}
			}
			{
				JPanel pnFabricante = new JPanel();
				pnFabricante.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnFabricante);
				pnFabricante.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblInternacional = new JLabel("--- Internacional: ---");
					lblInternacional.setForeground(Color.WHITE);
					lblInternacional.setFont(new Font("Courier New", Font.BOLD, 16));
					lblInternacional.setHorizontalAlignment(SwingConstants.CENTER);
					pnFabricante.add(lblInternacional, BorderLayout.NORTH);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFabricante.add(horizontalStrut, BorderLayout.EAST);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					pnFabricante.add(horizontalStrut, BorderLayout.WEST);
				}
				{
					lblInternacional2 = new JLabel("ASD");
					lblInternacional2.setHorizontalAlignment(SwingConstants.CENTER);
					lblInternacional2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblInternacional2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnFabricante.add(lblInternacional2, BorderLayout.CENTER);
				}
			}
		}
		{
			Component verticalStrut = Box.createVerticalStrut(10);
			getContentPane().add(verticalStrut, BorderLayout.NORTH);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(10);
			getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		}
	}

}
