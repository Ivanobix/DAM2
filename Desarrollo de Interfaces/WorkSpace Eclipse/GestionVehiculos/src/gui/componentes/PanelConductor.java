package gui.componentes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

import base.Conductor;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que representa un conductor
 * 
 * @author Fer
 *
 */
public class PanelConductor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JTextField txtExperiencia;
	private JCheckBox chbNovel;
	private JButton btnModificar;
	private JButton btnEliminar;

	private Conductor conductor;
	/**
	 * Create the panel.
	 */
	public PanelConductor(Conductor conductor) {
		this.conductor = conductor;
		
		initUI();
		
		mostrarDatosConductor();
		
	}

	protected void eliminarConductor() {
		
		
	}
	
	protected void modificarConductor() {
		conductor.setNombre(txtNombre.getText());
		conductor.setApellidos(txtApellidos.getText());
		conductor.setDni(txtDni.getText());
		conductor.setNovel(chbNovel.isSelected());
		conductor.setAnnosExperiencia(Integer.parseInt(txtExperiencia.getText()));
		
	}
	
	private void mostrarDatosConductor() {
		txtDni.setText(conductor.getDni());
		txtExperiencia.setText(String.valueOf(conductor.getAnnosExperiencia()));
		txtApellidos.setText(conductor.getApellidos());
		txtNombre.setText(conductor.getNombre());
		chbNovel.setSelected(conductor.isNovel());
	}

	private void initUI() {
		setBorder(new LineBorder(Color.BLUE, 2));
		
		JLabel lblNewLabel_2 = new JLabel("Dni:");
		add(lblNewLabel_2);
		
		txtDni = new JTextField();
		add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		add(lblNewLabel);
		
		txtNombre = new JTextField();
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		add(lblNewLabel_1);
		
		txtApellidos = new JTextField();
		add(txtApellidos);
		txtApellidos.setColumns(10);
		
		chbNovel = new JCheckBox("novel");
		add(chbNovel);
		
		JLabel lblNewLabel_3 = new JLabel("Annos Exp:");
		add(lblNewLabel_3);
		
		txtExperiencia = new JTextField();
		add(txtExperiencia);
		txtExperiencia.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificarConductor();
				
			}
		});
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eliminarConductor();
			
			}
		});
		add(btnEliminar);
	}



	

}
