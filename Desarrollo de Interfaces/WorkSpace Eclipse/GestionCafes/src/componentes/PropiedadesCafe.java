package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ContainerEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import datos.Cafe;
import gui.ControladorPrincipal;
import gui.VisualizarDatosCafe;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Propiedades Café. Clase utilizada para la visualización de elementos tipo
 * "Café" en al ventana de Ventas.
 * 
 * @author Iván García Prieto
 * @version 13.11.2020
 *
 */
public class PropiedadesCafe extends JPanel {

	private static final long serialVersionUID = -8668979756669333809L;
	private JLabel lblIdentificador2;
	private JLabel lblNombre2;
	private JButton btnInfo;
	private JButton btnRemover;
	private Cafe cafe;
	private ControladorPrincipal controladorPrincipal;

	/**
	 * Constructor.
	 * 
	 * @param controladorPrincipal Controlador principal de la aplicación.
	 * @param cafe                 Café a mostrar.
	 */
	public PropiedadesCafe(ControladorPrincipal controladorPrincipal, Cafe cafe) {
		this.cafe = cafe;
		this.controladorPrincipal = controladorPrincipal;
		initComponents();
		initHandlers();
		lblIdentificador2.setText(cafe.getIdentificador());
		lblNombre2.setText(cafe.getNombre());
	}

	/**
	 * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
	 * los que dispone esta clase y establece sus propiedades.
	 */
	private void initComponents() {
		setForeground(new Color(255, 255, 255));
		setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		btnInfo = new JButton("");

		btnInfo.setToolTipText("Mostrar Informaci\u00F3n");
		btnInfo.setActionCommand("VerInfo");

		btnInfo.setPreferredSize(new Dimension(20, 20));
		btnInfo.setIcon(new ImageIcon(PropiedadesCafe.class.getResource("/media/ico_info.png")));
		btnInfo.setBackground(Color.DARK_GRAY);
		add(btnInfo);

		btnRemover = new JButton("");

		btnRemover.setToolTipText("Eliminar");
		btnRemover.setIcon(new ImageIcon(PropiedadesCafe.class.getResource("/media/ico_papelera.png")));
		btnRemover.setPreferredSize(new Dimension(20, 20));
		btnRemover.setBackground(Color.DARK_GRAY);
		btnRemover.setActionCommand("Remover");
		add(btnRemover);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		lblIdentificador.setForeground(new Color(255, 255, 255));
		add(lblIdentificador);

		lblIdentificador2 = new JLabel("");
		lblIdentificador2.setForeground(new Color(0, 255, 255));
		lblIdentificador2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblIdentificador2);

		JLabel lblSeparador = new JLabel(" // ");
		lblSeparador.setForeground(Color.WHITE);
		lblSeparador.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblSeparador);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblNombre);

		lblNombre2 = new JLabel("");
		lblNombre2.setForeground(new Color(0, 255, 255));
		lblNombre2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		add(lblNombre2);
	}

	/**
	 * Inicializar Manejadores. Inicializa todos los manejadores de eventos
	 * necesarios para el correcto funcionamiento de la aplicación.
	 */
	private void initHandlers() {
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verInfo();
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
	}

	/**
	 * Ver Información. Muestra la información detallada de un café concreto.
	 */
	private void verInfo() {
		VisualizarDatosCafe visualizarDatosCafe = new VisualizarDatosCafe(cafe);
		visualizarDatosCafe.setVisible(true);
	}

	/**
	 * Eliminar. Elimina el panel creado y el café asociado a este.
	 */
	private void eliminar() {
		JPanel contenedor = (JPanel) getParent();
		contenedor.dispatchEvent(new ContainerEvent(this, ContainerEvent.COMPONENT_REMOVED, this));
		controladorPrincipal.eliminarElemento(cafe);
	}

}
