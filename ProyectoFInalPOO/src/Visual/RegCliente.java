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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
import Logico.Diseñador;
import Logico.Empresa;
import Logico.JefeProyecto;
import Logico.Planificador;
import Logico.Programador;
import Logico.Proyecto;
import Logico.Trabajador;



import javax.swing.JTextPane;
import javax.swing.UIManager;

public class RegCliente extends JDialog {

	private ArrayList<Cliente> listaDeClientes = new ArrayList<>();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegCliente.class.getResource("/imagenes/registrarCliente.png")));
		setTitle("Registrar Cliente");
		setBounds(100, 100, 650, 326);
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
				"Información General", TitledBorder.LEADING, TitledBorder.TOP);
		border.setTitleFont(new Font("Arial Black", Font.PLAIN, 14));
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30)), "Informaci\u00F3n General",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(139, 69, 19)));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		Label labelTittle = new Label("Informaci\u00F3n General");
		labelTittle.setFont(new Font("Dialog", Font.BOLD, 13));
		labelTittle.setForeground(new Color(210, 105, 30));
		labelTittle.setBackground(new Color(255, 255, 255));
		labelTittle.setBounds(237, 22, 145, 24);
		panel_1.add(labelTittle);

		JLabel lblId = new JLabel("No. Id:");
		lblId.setBounds(26, 109, 46, 14);
		panel_1.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(84, 105, 219, 22);
		panel_1.add(txtId);
		txtId.setColumns(10);

		txtId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtId.getText().length() != 11 && isVisible()) {
					JOptionPane.showMessageDialog(null, "El ID debe tener exactamente 11 dígitos.", "Error ID",
							JOptionPane.ERROR_MESSAGE);
					txtId.setText("");
					txtId.requestFocus();
				} else {
					Cliente cliente = Empresa.getInstance().buscarClientePorId(txtId.getText());
					if (cliente != null) {
						JOptionPane.showMessageDialog(null, "El ID utilizado ya existe.", "Error ID",
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
		lblNombre.setBounds(26, 70, 50, 16);
		panel_1.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(84, 70, 219, 22);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(311, 70, 56, 16);
		panel_1.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(371, 70, 219, 22);
		panel_1.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblDirreccion = new JLabel("Dirección:");
		lblDirreccion.setBounds(311, 108, 60, 16);
		panel_1.add(lblDirreccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(371, 105, 219, 22);
		panel_1.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c)) {
                    e.consume(); 
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese solo letras.", "Error Nombre", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

      
        txtApellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c)) { 
                    e.consume(); 
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese solo letras.", "Error Apellido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
		btnCrear = new JButton("Crear proyecto");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProyecto crearProyecto = new CrearProyecto();
				crearProyecto.setModal(true);
				crearProyecto.setVisible(true);
			}
		});
		btnCrear.setForeground(new Color(0, 0, 0));
		btnCrear.setBackground(UIManager.getColor("Button.background"));
		btnCrear.setBounds(160, 147, 285, 31);
		panel_1.add(btnCrear);
		btnCrear.setIcon(new ImageIcon(RegCliente.class.getResource("/imagenes/proyecto.png")));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton agregarButton = new JButton("Agregar");
		agregarButton.setActionCommand("Agregar");
		buttonPane.add(agregarButton);
		getRootPane().setDefaultButton(agregarButton);

		agregarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		            return; 
		        }
				
				Cliente nuevoCliente = new Cliente(txtId.getText(), txtNombre.getText(), txtApellido.getText(), txtDireccion.getText(), null);	
                empresa.agregarCliente(nuevoCliente);
                JOptionPane.showMessageDialog(null, "El Cliente ha sido registrado satisfactoriamente.", "",JOptionPane.INFORMATION_MESSAGE);
                cargarClientesEnLista();
                limpiarCampos();
            }

            private void limpiarCampos() {
                txtId.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtDireccion.setText("");
            }
        });

		JButton cancelButton = new JButton("Salir");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegCliente.class.getResource("/imagenes/fondoRCliente.jpg")));
		lblNewLabel.setBounds(-28, 0, 761, 234);
		panel_1.add(lblNewLabel);
		
		empresa = Empresa.getInstance();
	}
	
	private void cargarClientesEnLista() {
        ListarCliente listarCliente = ListarCliente.getInstance(); 
        listarCliente.cargarClientes(listaDeClientes); 
    }
}
