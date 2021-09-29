package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Vista extends JFrame {

	private static final long serialVersionUID = -3134897616589440422L;
	private JPanel contentPane;
	JTextField txtCodSucursal;
	JTextField txtDirector;
	JTextField txtNumTrabajadores;
	JTextField txtDireccion;
	JTextField txtTelefono;

	JButton btnPrimero;
	JButton btnSiguiente;
	JButton btnAnterior;
	JButton btnUltimo;
	JButton btnRefrescar;

	public Vista() {
		initComponents();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("DATOS DE SUCURSALES");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnPrimero = new JButton("Primero");
		btnPrimero.setActionCommand("btnPrimero");
		panel_1.add(btnPrimero);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setActionCommand("btnSiguiente");
		panel_1.add(btnSiguiente);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setActionCommand("btnAnterior");
		panel_1.add(btnAnterior);

		btnUltimo = new JButton("\u00DAltimo");
		btnUltimo.setActionCommand("btnUltimo");
		panel_1.add(btnUltimo);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setActionCommand("btnRefrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnRefrescar);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("COD_SUCURSAL");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);

		txtCodSucursal = new JTextField();
		txtCodSucursal.setEditable(false);
		panel.add(txtCodSucursal);
		txtCodSucursal.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("DIRECTOR");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);

		txtDirector = new JTextField();
		txtDirector.setEditable(false);
		panel.add(txtDirector);
		txtDirector.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("NUM_TRABAJADORES");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);

		txtNumTrabajadores = new JTextField();
		txtNumTrabajadores.setEditable(false);
		panel.add(txtNumTrabajadores);
		txtNumTrabajadores.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("DIRECCION");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("TELEFONO");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_5);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
	}

}
