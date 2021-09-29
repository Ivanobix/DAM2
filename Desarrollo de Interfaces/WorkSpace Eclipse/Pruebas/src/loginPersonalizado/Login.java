package loginPersonalizado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7862181992940342400L;
	private final JPanel contentPanel = new JPanel();
	private String usuario;
	private String contrasena;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public Login(String usuario, String contrasena) {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();
		this.usuario = usuario;
		this.contrasena = contrasena;
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	private String getUsuario() {
		return txtUsuario.getText();
	}

	private String getContrasena() {
		return String.valueOf(txtContrasena.getPassword());
	}

	private void initComponents() {
		setTitle("Inicio de Sesi\u00F3n");
		getContentPane().setFocusable(false);
		setBounds(100, 100, 450, 300);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		getContentPane().setLayout(borderLayout);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.GRAY);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblTitulo = new JLabel("");
				lblTitulo.setIcon(new ImageIcon(Login.class.getResource("/loginPersonalizado/im_login2.png")));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 5));
				lblTitulo.setFocusable(false);
				lblTitulo.setBackground(Color.DARK_GRAY);
				panel.add(lblTitulo);
			}
		}
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setFocusable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		{
			JPanel pnUsuario = new JPanel();
			pnUsuario.setBackground(Color.GRAY);
			contentPanel.add(pnUsuario);
			pnUsuario.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblNombre = new JLabel("Usuario:");
				lblNombre.setBackground(Color.WHITE);
				lblNombre.setFocusable(false);
				lblNombre.setFont(new Font("Courier New", Font.BOLD, 16));
				lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
				pnUsuario.add(lblNombre);
			}
			{
				txtUsuario = new JTextField();
				txtUsuario.setFont(new Font("Courier New", Font.PLAIN, 16));
				txtUsuario.setBackground(Color.WHITE);
				txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
				pnUsuario.add(txtUsuario);
				txtUsuario.setColumns(10);
			}
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
			contentPanel.add(rigidArea);
		}
		{
			JPanel pnContrasena = new JPanel();
			pnContrasena.setBackground(Color.GRAY);
			contentPanel.add(pnContrasena);
			pnContrasena.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
				lblContrasena.setBackground(Color.WHITE);
				lblContrasena.setFocusable(false);
				lblContrasena.setFont(new Font("Courier New", Font.BOLD, 16));
				lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
				pnContrasena.add(lblContrasena);
			}
			{
				txtContrasena = new JPasswordField();
				txtContrasena.setFont(new Font("Courier New", Font.PLAIN, 16));
				txtContrasena.setBackground(Color.WHITE);
				txtContrasena.setHorizontalAlignment(SwingConstants.CENTER);
				pnContrasena.add(txtContrasena);
			}
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
			contentPanel.add(rigidArea);
		}
		{
			JPanel pnBotones = new JPanel();
			pnBotones.setBackground(Color.DARK_GRAY);
			pnBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnBotones, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.setFocusable(false);
				btnAceptar.setBackground(Color.GRAY);
				btnAceptar.setFont(new Font("Courier New", Font.PLAIN, 16));
				pnBotones.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(70);
				pnBotones.add(horizontalStrut);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFocusable(false);
				btnCancelar.setBackground(Color.GRAY);
				btnCancelar.setFont(new Font("Courier New", Font.PLAIN, 16));
				pnBotones.add(btnCancelar);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Aceptar")) {
			if (getUsuario().equals(usuario) && getContrasena().equals(contrasena)) {
				dispose();
			} else {
				txtUsuario.setBackground(Color.RED);
				txtContrasena.setBackground(Color.RED);
			}
		} else {
			System.exit(0);
		}

	}

}
