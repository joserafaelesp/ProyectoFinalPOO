package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Logico.Cliente;
import Logico.Empresa;
import Logico.Proyecto;
import Logico.Trabajador;


import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;


public class CrearProyecto extends JDialog {

	
	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtCodigoPoyecto;
	private JTable tableTrabajadoresDispo;
	private JTable tableTrabajaadoreSelec;
	private int selectedTrabajador;
	private int noselectedTrabajador;
	private Cliente selectedCliente;
	private JButton btnProgramador;
	private JScrollPane scrollPaneTrabajadoresSeleccionados;
	private JTable tableClientes;
	private DefaultTableModel modelo;
	private DefaultTableModel modelo2;
	private JTextField textFieldNombreProyecto;
	private JTextField textField;
	private Object[] dispRow;
	private Object[] selecRow;
	private JButton btnCrear;
	

	/**
	 * Launch the application.u
	 */
	public static void main(String[] args) {
		try {
			CrearProyecto dialog = new CrearProyecto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearProyecto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearProyecto.class.getResource("/imagenes/agregar.png")));
		
		setTitle("Crear Proyecto");
		setBounds(100, 100, 1000, 686);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		JPanel panelListarCliente = new JPanel();
		panelListarCliente.setBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30)), "Clientes Registrados:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(160, 82, 45)));
		panelListarCliente.setBounds(289, 264, 402, 265);
		panelPrincipal.add(panelListarCliente);
		panelListarCliente.setLayout(new BorderLayout(0,0));
		
		JPanel panelInfoGeneral = new JPanel();
		panelInfoGeneral.setBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30), 2, true), "Informaci\u00F3n General: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(210, 105, 30)));
		panelInfoGeneral.setBounds(33, 20, 926, 225);
		panelPrincipal.add(panelInfoGeneral);
		panelInfoGeneral.setLayout(null);
		
		JLabel lblCodigoProyecto = new JLabel("C\u00F3digo del proyecto:");
		lblCodigoProyecto.setBounds(12, 44, 127, 16);
		panelInfoGeneral.add(lblCodigoProyecto);
		
		txtCodigoPoyecto = new JTextField();
		txtCodigoPoyecto.setEditable(false);
		txtCodigoPoyecto.setBounds(132, 41, 130, 22);
		panelInfoGeneral.add(txtCodigoPoyecto);
		
		JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");
		lblNombreProyecto.setBounds(12, 83, 110, 14);
		panelInfoGeneral.add(lblNombreProyecto);
		
		textFieldNombreProyecto = new JTextField();
		textFieldNombreProyecto.setBounds(132, 79, 130, 22);
		panelInfoGeneral.add(textFieldNombreProyecto);
		textFieldNombreProyecto.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(12, 121, 88, 14);
		panelInfoGeneral.add(lblFechaInicio);
		txtCodigoPoyecto.setColumns(10);
		
		JPanel panelListarTrabajadores = new JPanel();
		panelListarTrabajadores.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2), "Trabajadores Disponibles: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		panelListarTrabajadores.setBounds(75, 258, 320, 271);
		panelPrincipal.add(panelListarTrabajadores);
		panelListarTrabajadores.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneDispTrabajadores = new JScrollPane();
		panelListarTrabajadores.add(scrollPaneDispTrabajadores);
		
		
		tableTrabajadoresDispo = new JTable();
		tableTrabajaadoreSelec = new JTable();
		tableClientes = new JTable();

		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(132, 113, 130, 22);
		panelInfoGeneral.add(dateChooser1);
		
		JLabel lblFechaEntrega = new JLabel("Fecha entrega:");
		lblFechaEntrega.setBounds(12, 156, 88, 14);
		panelInfoGeneral.add(lblFechaEntrega);
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(132, 148, 130, 22);
		panelInfoGeneral.add(dateChooser2);
		
		/*JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "<<Seleccione>>", "Femenino", "Masculino" }));
        comboBox.setBounds(367, 35, 232, 22);
        panelInfoGeneral.add(comboBox);*/
        
        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setBorder(new LineBorder(new Color(255, 235, 205), 1, true));
        panelDescripcion.setBackground(new Color(255, 228, 196));
        panelDescripcion.setBounds(554, 44, 339, 126);
        panelInfoGeneral.add(panelDescripcion);
        panelDescripcion.setLayout(null);
        
        JLabel lblDescripcion = new JLabel("Descripci\u00F3n del Proyecto:");
        lblDescripcion.setBounds(87, 13, 185, 28);
        panelDescripcion.add(lblDescripcion);
        lblDescripcion.setIcon(new ImageIcon(CrearProyecto.class.getResource("/imagenes/descripcion.png")));
        
        textField = new JTextField();
        textField.setBounds(12, 54, 315, 42);
        panelDescripcion.add(textField);
        textField.setColumns(10);
        
        JPanel panelTecnologia = new JPanel();
        panelTecnologia.setBackground(new Color(255, 228, 196));
        panelTecnologia.setBorder(new TitledBorder(new LineBorder(new Color(255, 235, 205), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelTecnologia.setBounds(336, 41, 194, 129);
        panelInfoGeneral.add(panelTecnologia);
        panelTecnologia.setLayout(null);
        
        JLabel lblTecnologia = new JLabel("Tecnolog\u00EDas:");
        lblTecnologia.setIcon(new ImageIcon(CrearProyecto.class.getResource("/imagenes/Tecnologia.png")));
        lblTecnologia.setBounds(41, 13, 116, 32);
        panelTecnologia.add(lblTecnologia);
        
        btnProgramador = new JButton("Seleccionar Lenguaje");
        btnProgramador.setBounds(12, 58, 170, 32);
        panelTecnologia.add(btnProgramador);
        
        btnProgramador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                lenguajesProgramacion lenguajes = new lenguajesProgramacion();
                lenguajes.setModal(true);
                lenguajes.setVisible(true);
            }
        });
        
		tableTrabajadoresDispo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		//ListarPersona listarTrabajadorDispo = new ListarCLiente();
		String modo  = "Cliente";
		//JTable tableDefault  = listarTRabajadorDispo.getTable(modo);
	//	tableTrabajadoresDispo = tableDefault;
		panelListarTrabajadores.add(scrollPaneDispTrabajadores, BorderLayout.CENTER);
		
		scrollPaneDispTrabajadores.setViewportView(tableTrabajadoresDispo);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(415, 535, 134, 23);
		panelPrincipal.add(btnSeleccionar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setVisible(false);
		btnVolver.setBounds(40, 563, 89, 23);
		panelPrincipal.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(455, 350, 89, 23);
		btnAgregar.setVisible(false);
		panelPrincipal.add(btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setEnabled(false);
		btnQuitar.setVisible(false);
		btnQuitar.setBounds(455, 380, 89, 23);
		panelPrincipal.add(btnQuitar);
		
		JPanel panelTrabajadoresSeleccionados = new JPanel();
		panelTrabajadoresSeleccionados.setForeground(new Color(105, 105, 105));
		panelTrabajadoresSeleccionados.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Trabajadores Seleccionados:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		panelTrabajadoresSeleccionados.setBounds(610, 258, 326, 271);
		panelPrincipal.add(panelTrabajadoresSeleccionados);
		panelTrabajadoresSeleccionados.setLayout(null);
		
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setViewportBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPaneClientes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelListarCliente.add(scrollPaneClientes, BorderLayout.CENTER);
		
		DefaultTableModel modelocl= new DefaultTableModel();
		String encabezado[] = { "Identifiacion","Nombre", "Apellido", "Direccion", "Cantidad Proyectos" };
		modelocl.setColumnIdentifiers(encabezado);
		tableClientes.setModel(modelocl);
		tableClientes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableClientes.getSelectedRow();
		        if ( Selected >= 0) {
		        	btnQuitar.setEnabled(true);
		        } else {
		        	btnQuitar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Seleccion Invalida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		scrollPaneClientes.setViewportView(tableClientes);
		
		
		scrollPaneTrabajadoresSeleccionados = new JScrollPane();
		scrollPaneTrabajadoresSeleccionados.setBounds(12, 23, 306, 242);
		panelTrabajadoresSeleccionados.add(scrollPaneTrabajadoresSeleccionados);
		scrollPaneTrabajadoresSeleccionados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		modelo= new DefaultTableModel();
		String headers1[] = { "Nombre", "Apellido", "Costo" };
		modelo.setColumnIdentifiers(headers1);
		tableTrabajaadoreSelec.setModel(modelo);
		tableTrabajaadoreSelec.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableTrabajaadoreSelec.getSelectedRow();
		        if ( Selected >= 0) {
		        	btnQuitar.setEnabled(true);
		        } else {
		        	btnQuitar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Seleccion Invalida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		scrollPaneTrabajadoresSeleccionados.setViewportView(tableTrabajaadoreSelec);
		
		
		panelListarTrabajadores.add(scrollPaneDispTrabajadores);
		scrollPaneDispTrabajadores.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		
	    modelo2= new DefaultTableModel();
		String headers2[] = { "Nombre", "Apellido", "Costo" };
		modelo.setColumnIdentifiers(headers1);
		tableTrabajadoresDispo.setModel(modelo);
		tableTrabajadoresDispo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableTrabajadoresDispo.getSelectedRow();
		        if ( Selected >= 0) {
		        	btnQuitar.setEnabled(true);
		        } else {
		        	btnQuitar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Seleccion Invalida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		scrollPaneDispTrabajadores.setViewportView(tableTrabajadoresDispo);
			  
		
		
		
		tableTrabajaadoreSelec = new JTable();
		
		    	
			
		 btnSeleccionar.addActionListener(new ActionListener() {
	           
	            int boton = 0;
	            public void actionPerformed(ActionEvent e) {
	            	boton++;
	            	panelListarTrabajadores.setVisible(true);
	            	panelListarCliente.setVisible(false);
	            	panelTrabajadoresSeleccionados.setVisible(true);
	                tableTrabajadoresDispo.setVisible(true);
	                tableTrabajaadoreSelec.setVisible(true);
	               
	                if(boton!=0) {
	                	btnSeleccionar.setVisible(false);
	                	btnVolver.setVisible(true);
	                	btnAgregar.setVisible(true);
	                	btnQuitar.setVisible(true);
	                }
	                boton--;
	                	
	            }
	        });

	        
		 panelListarTrabajadores.setVisible(false);
		 panelTrabajadoresSeleccionados.setVisible(false);
	     tableTrabajadoresDispo.setVisible(false);
	     tableTrabajaadoreSelec.setVisible(true);
	
		
		panelPrincipal.add(btnSeleccionar);
		
		JLabel lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon(CrearProyecto.class.getResource("/imagenes/fondoCP.jpg")));
		lblFondo.setBounds(12, -140, 957, 900);
		panelPrincipal.add(lblFondo);
		
		
		
		btnVolver.addActionListener(new ActionListener() {
            
            int boton2=1;
            public void actionPerformed(ActionEvent e) {
            	boton2--;
            	panelListarTrabajadores.setVisible(false);
            	panelTrabajadoresSeleccionados.setVisible(false);
            	panelListarCliente.setVisible(true);
                tableTrabajadoresDispo.setVisible(false);
                tableTrabajaadoreSelec.setVisible(true);
                if(boton2==0) {
                	btnSeleccionar.setVisible(true);
                	btnAgregar.setVisible(false);
                	btnQuitar.setVisible(false);
                }
                btnVolver.setVisible(false);
                boton2++;
                
                
            }
        });
		
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAgregar.setEnabled(false);
				 if (Empresa.getInstance().getTrabajadorsSeleccionados().size() >= 5) {
			            JOptionPane.showMessageDialog(null, "Ya se han seleccionado el máximo de 5 trabajadores.", "Error", JOptionPane.ERROR_MESSAGE);
			        } else {
			           
			            Empresa.getInstance().getTrabajadoresNoSeleccionados().get(noselectedTrabajador).setSeleccionado(true);
			            disponiblesTableUpdate();
			            seleccionadosTableUpdate();
				
			        }	
			}
		});
	
		
		
		
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQuitar.setEnabled(false);
				if(Empresa.getInstance().getTrabajadorsSeleccionados().get(selectedTrabajador)!=null)
					Empresa.getInstance().getTrabajadorsSeleccionados().get(selectedTrabajador).setSeleccionado(false);
				btnQuitar.setEnabled(false);
				disponiblesTableUpdate();
				seleccionadosTableUpdate();
			
			}
		});
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton crearProyecto = new JButton("Crear pryecto");
				crearProyecto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				
			}
			{
				JButton cancelarBtn = new JButton("Salir");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnCrear = new JButton("Crear");
				buttonPane.add(btnCrear);
				
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	
	public void disponiblesTableUpdate() {
		modelo.setRowCount(0);
		dispRow = new Object[tableTrabajadoresDispo.getColumnCount()];

		for (Trabajador trabajador : Empresa.getInstance().getTrabajadoresNoSeleccionados()) {
			dispRow[0] = trabajador.getNombre();
			dispRow[1] = trabajador.getApellido();
			dispRow[2] = trabajador.calcularSalarioDiario();
			modelo.addRow(dispRow);

		}
	}

	public void seleccionadosTableUpdate() {
		
		modelo2.setRowCount(0);
		selecRow = new Object[tableTrabajaadoreSelec.getColumnCount()];

		for (Trabajador Trabajador : Empresa.getInstance().getTrabajadorsSeleccionados()) {
			selecRow[0] = Trabajador.getNombre();
			selecRow[1] = Trabajador.getApellido();
			selecRow[2] = Trabajador.calcularSalarioDiario();

			modelo2.addRow(selecRow);

		}
		
	}
}