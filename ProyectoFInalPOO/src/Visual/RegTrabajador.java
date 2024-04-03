package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Empresa;
import Logico.Proyecto;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Color;
import java.util.Date;
import java.util.Calendar;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class RegTrabajador extends JDialog {

	/**
	 * 
	 */
	private Empresa empresa;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegTrabajador dialog = new RegTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegTrabajador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegTrabajador.class.getResource("/imagenes/agregar.png")));
		setTitle("Agregar Trabajador");
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(SystemColor.menu);
				TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Información General", TitledBorder.LEADING, TitledBorder.TOP);
		        border.setTitleFont(new Font("Arial Black", Font.PLAIN, 14));
		        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informaci\u00F3n General", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

				panel.add(panel_1, BorderLayout.CENTER);
				
				
				panel_1.setBounds(10, 11, 429, 101);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblId = new JLabel("Id:");
				lblId.setBounds(22, 39, 46, 14);
				panel_1.add(lblId);
				
				txtId = new JTextField();
				txtId.setBounds(84, 35, 219, 22);
				panel_1.add(txtId);
				txtId.setColumns(10);
				
				txtId.addFocusListener(new FocusAdapter() {
				    @Override
				    public void focusLost(FocusEvent e) {
				        if (txtId.getText().length() != 11 && isVisible()) {
				            JOptionPane.showMessageDialog(null, "El ID debe tener exactamente 11 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
				            txtId.setText("");
				            txtId.requestFocus(); 
				        }
				    }
				});
		        
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(22, 69, 50, 16);
				panel_1.add(lblNombre);
				
				txtNombre = new JTextField();
				txtNombre.setBounds(367, 66, 219, 22);
				panel_1.add(txtNombre);
				txtNombre.setColumns(10);
				
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setBounds(315, 69, 56, 16);
				panel_1.add(lblApellido);
				
				txtApellido = new JTextField();
				txtApellido.setBounds(84, 66, 219, 22);
				panel_1.add(txtApellido);
				txtApellido.setColumns(10);
				
				JLabel lblDirreccion = new JLabel("Direcci\u00F3n:");
				lblDirreccion.setBounds(22, 101, 60, 16);
				panel_1.add(lblDirreccion);
				
				txtDireccion = new JTextField();
				txtDireccion.setBounds(84, 98, 505, 22);
				panel_1.add(txtDireccion);
				txtDireccion.setColumns(10);
				
				JLabel lblSexo = new JLabel("Sexo:");
				lblSexo.setBounds(315, 38, 56, 16);
				panel_1.add(lblSexo);
				
				JComboBox comboBox = new JComboBox();
		        comboBox.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Femenino", "Masculino"}));
		        comboBox.setBounds(367, 35, 219, 22);
		        panel_1.add(comboBox);
			        
				JLabel lblFechaNac = new JLabel("Fecha de Nacimiento:");
		        lblFechaNac.setBounds(22, 130, 132, 24);
		        panel_1.add(lblFechaNac);

		        JDateChooser dateChooser = new JDateChooser();
		        dateChooser.setBounds(148, 132, 159, 22);
		        panel_1.add(dateChooser);
		        
		        Label label = new Label("Salario:");
		        label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		        label.setBounds(315, 130, 46, 24);
		        panel_1.add(label);
		        
		        JSpinner spinner_1 = new JSpinner();
		        spinner_1.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		        spinner_1.setBounds(367, 131, 222, 22);
		        panel_1.add(spinner_1);
		        
		        JPanel panel_2 = new JPanel();
		        panel_2.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Tipo de Trabajador:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		        panel_2.setBounds(22, 177, 567, 75);
		        panel_1.add(panel_2);
		        panel_2.setLayout(new GridLayout(1, 0));

		        JCheckBox checkBox1 = new JCheckBox("Diseñador");
		        JCheckBox checkBox2 = new JCheckBox("Programador");
		        JCheckBox checkBox3 = new JCheckBox("Jefe de Proyecto");
		        JCheckBox checkBox4 = new JCheckBox("Planificador");
		        
		        ButtonGroup group = new ButtonGroup();
		        group.add(checkBox1);
		        group.add(checkBox2);
		        group.add(checkBox3);
		        group.add(checkBox4);

		        checkBox1.addActionListener(e -> {
		            if (checkBox1.isSelected()) {
		                checkBox2.setSelected(false);
		                checkBox3.setSelected(false);
		                checkBox4.setSelected(false);
		            }
		        });
		        checkBox2.addActionListener(e -> {
		            if (checkBox2.isSelected()) {
		                checkBox1.setSelected(false);
		                checkBox3.setSelected(false);
		                checkBox4.setSelected(false);
		            }
		        });
		        checkBox3.addActionListener(e -> {
		            if (checkBox3.isSelected()) {
		                checkBox1.setSelected(false);
		                checkBox2.setSelected(false);
		                checkBox4.setSelected(false);
		            }
		        });

		        checkBox4.addActionListener(e -> {
		            if (checkBox4.isSelected()) {
		                checkBox1.setSelected(false);
		                checkBox2.setSelected(false);
		                checkBox3.setSelected(false);
		            }
		        });

		        panel_2.add(checkBox1);
		        panel_2.add(checkBox2);
		        panel_2.add(checkBox3);
		        panel_2.add(checkBox4);

		        JPanel panel_3 = new JPanel();
		        panel_3.setBounds(22, 265, 564, 52);
		        panel_3.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Proyecto al que Pertenece:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		        panel_1.add(panel_3);
		        panel_3.setLayout(new GridLayout(1, 2, 5, 0)); 

		        JTextField txtProyecto = new JTextField();
		        panel_3.add(txtProyecto);

		        JButton btnBuscar = new JButton("Buscar Proyecto");
		        btnBuscar.setIcon(new ImageIcon(RegTrabajador.class.getResource("/imagenes/Busqueda.png")));
		        panel_3.add(btnBuscar);

		        btnBuscar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                String nombreProyecto = txtProyecto.getText();
		                Proyecto proyectoEncontrado = buscarProyectoPorNombre(nombreProyecto);
		                if (proyectoEncontrado != null) {
		                    JOptionPane.showMessageDialog(null, "El proyecto " + nombreProyecto + " fue encontrado.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "El proyecto " + nombreProyecto + " no fue encontrado.");
		                }
		            }
		        });
		        
		        JPanel buttonPane = new JPanel();
		        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		        getContentPane().add(buttonPane, BorderLayout.SOUTH);

		        JButton agregarButton = new JButton("Agregar");
		        agregarButton.setActionCommand("Agregar");
		        buttonPane.add(agregarButton);
		        getRootPane().setDefaultButton(agregarButton);

		        agregarButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                java.util.Date fechaNacimiento = dateChooser.getDate();


		                if (fechaNacimiento != null && esAdulto(fechaNacimiento)) {
		                    JOptionPane.showMessageDialog(null, "Fecha de nacimiento válida. Puedes continuar.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una fecha válida para un trabajador mayor de 18 años.");
		                } 
		            }
		        });
		        
		        JButton cancelButton = new JButton("Cancelar");
		        cancelButton.setActionCommand("Cancel");
		        buttonPane.add(cancelButton);
			    }
			}
		empresa = Empresa.getInstance();
		}
	
		public Proyecto buscarProyectoPorNombre(String nombreProyecto) {
			ArrayList<Proyecto> proyectos = empresa.getProyectos();
		    for (Proyecto proyecto : proyectos) {
		        if (proyecto.getNombre().equals(nombreProyecto)) {
		            return proyecto;
		        }
		    }
		    return null;
		}

	 	private boolean esAdulto(java.util.Date fecha){
		  
	        Calendar fechaNacimiento = Calendar.getInstance();
	        fechaNacimiento.setTime(fecha);
	        Calendar fechaLimite = Calendar.getInstance();
	        fechaLimite.add(Calendar.YEAR, -18); 
	        return fechaNacimiento.before(fechaLimite);
	    }
}