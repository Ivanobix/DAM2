package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import datos.Cafe;

/**
 * Visualizar Datos Café. Ventana dedicada a la visualización de objetos tipo
 * café.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class VisualizarDatosCafe extends JDialog {

	private static final long serialVersionUID = -7577049462372683023L;
	private JLabel lblIdentificador2;
	private JLabel lblNombre2;
	private JLabel lblArabico2;
	private JLabel lblRobusta2;
	private JLabel lblFechaEnvasado2;
	private JButton btnVerFabricante;

	/**
	 * Constructor.
	 * 
	 * @param cafeAVisualizar Café a visualizar.
	 */
	public VisualizarDatosCafe(Cafe cafeAVisualizar) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();

		lblIdentificador2.setText(cafeAVisualizar.getIdentificador());
		lblNombre2.setText(cafeAVisualizar.getNombre());
		lblArabico2.setText(String.valueOf(cafeAVisualizar.getPorcentajeArabico()));
		lblRobusta2.setText(String.valueOf(cafeAVisualizar.getPorcentajeRobusta()));
		lblFechaEnvasado2.setText(cafeAVisualizar.getFechaEnvasado().toString());
		btnVerFabricante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizarDatosFabricante visualizarDatosFabricante = new VisualizarDatosFabricante(
						cafeAVisualizar.getFabricante());
				visualizarDatosFabricante.setVisible(true);

			}
		});

	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		getContentPane().setBackground(ControladorPrincipal.COLOR_FONDO);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VisualizarDatosCafe.class.getResource("/media/Logo_Cafe.png")));
		setTitle("Visualizar Datos Caf\u00E9");
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
					JLabel lblArabico = new JLabel("--- Ar\u00E1bico (%): ---");
					lblArabico.setForeground(Color.WHITE);
					lblArabico.setFont(new Font("Courier New", Font.BOLD, 16));
					lblArabico.setHorizontalAlignment(SwingConstants.CENTER);
					pnArabico.add(lblArabico, BorderLayout.NORTH);
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
					lblArabico2 = new JLabel("ASD");
					lblArabico2.setHorizontalAlignment(SwingConstants.CENTER);
					lblArabico2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblArabico2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnArabico.add(lblArabico2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnRobusta = new JPanel();
				pnRobusta.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnRobusta);
				pnRobusta.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblRobusta = new JLabel("--- Robusta (%): ---");
					lblRobusta.setForeground(Color.WHITE);
					lblRobusta.setFont(new Font("Courier New", Font.BOLD, 16));
					lblRobusta.setHorizontalAlignment(SwingConstants.CENTER);
					pnRobusta.add(lblRobusta, BorderLayout.NORTH);
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
					lblRobusta2 = new JLabel("ASD");
					lblRobusta2.setHorizontalAlignment(SwingConstants.CENTER);
					lblRobusta2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblRobusta2.setFont(new Font("Courier New", Font.BOLD, 14));
					pnRobusta.add(lblRobusta2, BorderLayout.CENTER);
				}
			}
			{
				JPanel pnFechaEnvasado = new JPanel();
				pnFechaEnvasado.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnFechaEnvasado);
				pnFechaEnvasado.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFechaEnvasado = new JLabel("--- Fecha Envasado: ---");
					lblFechaEnvasado.setForeground(Color.WHITE);
					lblFechaEnvasado.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFechaEnvasado.setHorizontalAlignment(SwingConstants.CENTER);
					pnFechaEnvasado.add(lblFechaEnvasado, BorderLayout.NORTH);
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
					lblFechaEnvasado2 = new JLabel("ASD");
					pnFechaEnvasado.add(lblFechaEnvasado2, BorderLayout.CENTER);
					lblFechaEnvasado2.setHorizontalAlignment(SwingConstants.CENTER);
					lblFechaEnvasado2.setForeground(ControladorPrincipal.COLOR_FONDO);
					lblFechaEnvasado2.setFont(new Font("Courier New", Font.BOLD, 14));
				}
			}
			{
				JPanel pnFabricante = new JPanel();
				pnFabricante.setBackground(Color.DARK_GRAY);
				pnDatos.add(pnFabricante);
				pnFabricante.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFabricante = new JLabel("--- Fabricante: ---");
					lblFabricante.setForeground(Color.WHITE);
					lblFabricante.setFont(new Font("Courier New", Font.BOLD, 16));
					lblFabricante.setHorizontalAlignment(SwingConstants.CENTER);
					pnFabricante.add(lblFabricante, BorderLayout.NORTH);
				}
				{
					btnVerFabricante = new JButton("Ver Fabricante");
					btnVerFabricante.setFocusable(false);
					btnVerFabricante.setFont(new Font("Courier New", Font.BOLD, 12));
					btnVerFabricante.setForeground(Color.BLACK);
					btnVerFabricante.setBackground(Color.WHITE);
					btnVerFabricante.setActionCommand("VerFabricante");
					pnFabricante.add(btnVerFabricante, BorderLayout.SOUTH);
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
