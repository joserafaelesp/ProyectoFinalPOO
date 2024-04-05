package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import Logico.Cliente;
import Logico.Dise�ador;
import Logico.Empresa;
import Logico.JefeProyecto;
import Logico.Planificador;
import Logico.Programador;
import Logico.Proyecto;
import Logico.Trabajador;



import javax.swing.JTextPane;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private Empresa empresa;
	private Trabajador trabajador;
	private Cliente cliente;
	private JButton btnCrear;

	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegCliente.class.getResource("/imagenes/agregar.png")));
		setTitle("Registrar Cliente");
		setBounds(100, 100, 650, 509);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Informaci�n General", TitledBorder.LEADING, TitledBorder.TOP);
		border.setTitleFont(new Font("Arial Black", Font.PLAIN, 14));
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30)), "Informaci\u00F3n General",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(139, 69, 19)));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(22, 108, 46, 14);
		panel_1.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(84, 104, 219, 22);
		panel_1.add(txtId);
		txtId.setColumns(10);

		txtId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtId.getText().length() != 11 && isVisible()) {
					JOptionPane.showMessageDialog(null, "El ID debe tener exactamente 11 d�gitos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtId.setText("");
					txtId.requestFocus();
				} else {
					Cliente cliente = Empresa.getInstance().buscarClientePorId(txtId.getText());
					if (cliente != null) {
						JOptionPane.showMessageDialog(null, "El ID utilizado ya existe.", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtId.setText("");
						txtId.requestFocus();
					} else {
						txtNombre.setEnabled(true);
						txtDireccion.setEnabled(true);
						txtApellido.setEnabled(true);
					}
				}
			}
		});
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 151, 50, 16);
		panel_1.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(382, 148, 217, 22);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(316, 148, 56, 16);
		panel_1.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(84, 148, 219, 22);
		panel_1.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblDirreccion = new JLabel("Direcci�n:");
		lblDirreccion.setBounds(22, 197, 60, 16);
		panel_1.add(lblDirreccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(84, 194, 515, 22);
		panel_1.add(txtDireccion);
		txtDireccion.setColumns(10);

		btnCrear = new JButton("Crear proyecto");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrear.setForeground(new Color(0, 0, 0));
		btnCrear.setBackground(Color.ORANGE);
		btnCrear.setBounds(186, 236, 285, 31);
		panel_1.add(btnCrear);
		btnCrear.setIcon(new ImageIcon(RegTrabajador.class.getResource("/imagenes/proyecto.png")));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton agregarButton = new JButton("Agregar");
		agregarButton.setActionCommand("Agregar");
		buttonPane.add(agregarButton);
		getRootPane().setDefaultButton(agregarButton);

		agregarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente nuevoCliente = new Cliente(txtId.getText(), txtNombre.getText(), txtApellido.getText(), txtDireccion.getText(), null);
				JOptionPane.showMessageDialog(null, "El cliente ha sido registrado satisfactoriamente.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegCliente.class.getResource("/imagenes/fondoTrabajador.png")));
		lblNewLabel.setBounds(0, 0, 622, 428);
		panel_1.add(lblNewLabel);
	}

	
}
