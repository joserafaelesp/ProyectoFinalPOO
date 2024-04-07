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
import Logico.Diseñador;
import Logico.Empresa;
import Logico.JefeProyecto;
import Logico.Planificador;
import Logico.Programador;
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
	private JButton btnAgregar;
	private JScrollPane scrollPaneTrabajadoresSeleccionados;
	private JTable tableClientes;
	private DefaultTableModel modelo;
	private DefaultTableModel modelo2;
	private DefaultTableModel modelocl;
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
			CrearProyecto creacionproyecto = new CrearProyecto();
			creacionproyecto.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			creacionproyecto.setModal(true);
			creacionproyecto.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public void cargarClientesProyecto(ArrayList<Cliente> clientes) {
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        model.setRowCount(0);

        for (Cliente cliente : clientes) {
            int cantidadProyectos = Empresa.getInstance().cantidadDeProyectosCliente(cliente);
            Object[] rowData = {
                cliente.getEstado(),cliente.getId(), cliente.getApellido(),cliente.getNombre(), cliente.getDireccion(), cantidadProyectos
            };
            model.addRow(rowData);
        }
    }

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
		panelListarCliente.setBounds(160, 264, 715, 265);
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
		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(132, 113, 130, 22);
		panelInfoGeneral.add(dateChooser1);
		
		JLabel lblFechaEntrega = new JLabel("Fecha entrega:");
		lblFechaEntrega.setBounds(12, 156, 88, 14);
		panelInfoGeneral.add(lblFechaEntrega);
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(132, 148, 130, 22);
		panelInfoGeneral.add(dateChooser2);
        
        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setBackground(SystemColor.control);
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
        panelTecnologia.setBackground(SystemColor.control);
        panelTecnologia.setBorder(new TitledBorder(new LineBorder(new Color(160, 160, 160), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelTecnologia.setBounds(336, 41, 194, 129);
        panelInfoGeneral.add(panelTecnologia);
        panelTecnologia.setLayout(null);
        
        JLabel lblTecnologia = new JLabel("Tecnolog\u00EDas:");
        lblTecnologia.setIcon(new ImageIcon(CrearProyecto.class.getResource("/imagenes/Tecnologia.png")));
        lblTecnologia.setBounds(41, 13, 116, 32);
        panelTecnologia.add(lblTecnologia);
        
        JComboBox comboBoxFiltro = new JComboBox();
        comboBoxFiltro.setForeground(new Color(139, 69, 19));
        comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Diseñadores", "Programadores", "Jefes de Proyecto", "Planificadores" }));
        comboBoxFiltro.setBounds(455, 410, 131, 22);
        comboBoxFiltro.setVisible(false);
        panelPrincipal.add(comboBoxFiltro);
        
        comboBoxFiltro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = comboBoxFiltro.getSelectedIndex();
                ArrayList<Trabajador> trabajadoresFiltrados = filtrarTrabajadores(selectedIndex);
                cargarTrabajadores(trabajadoresFiltrados);
            }
        });
        
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
        
        tableTrabajadoresDispo = new JTable();
		tableTrabajadoresDispo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		btnAgregar.setBounds(455, 350, 131, 23);
		btnAgregar.setVisible(false);
		panelPrincipal.add(btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setEnabled(false);
		btnQuitar.setVisible(false);
		btnQuitar.setBounds(455, 380, 131, 23);
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
		
		tableClientes = new JTable(); 
		modelocl= new DefaultTableModel();
		String encabezado[] = { "Estado","No. Id","Nombre", "Apellido", "Dirección", "Cantidad Proyectos" };
		modelocl.setColumnIdentifiers(encabezado);
		tableClientes.setModel(modelocl);
		tableClientes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableClientes.getSelectedRow();
		        if ( Selected >= 1) {
		        	btnQuitar.setEnabled(true);
		        } else {
		        	btnQuitar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Selección Inválida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		scrollPaneClientes.setViewportView(tableClientes);		
		
		scrollPaneTrabajadoresSeleccionados = new JScrollPane();
		scrollPaneTrabajadoresSeleccionados.setBounds(12, 23, 306, 242);
		panelTrabajadoresSeleccionados.add(scrollPaneTrabajadoresSeleccionados);
		scrollPaneTrabajadoresSeleccionados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		tableTrabajaadoreSelec = new JTable(); 
		modelo2= new DefaultTableModel();
		String headers1[] = { "Tipo","Nombre", "Apellido", "Costo" };
		modelo2.setColumnIdentifiers(headers1);
		tableTrabajaadoreSelec.setModel(modelo2);
		tableTrabajaadoreSelec.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableTrabajaadoreSelec.getSelectedRow();
		        if ( Selected >= 0) {
		        	btnQuitar.setEnabled(true);
		        } else {
		        	btnQuitar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Selección Inválida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		scrollPaneTrabajadoresSeleccionados.setViewportView(tableTrabajaadoreSelec);
				
		panelListarTrabajadores.add(scrollPaneDispTrabajadores);
		scrollPaneDispTrabajadores.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	    modelo= new DefaultTableModel();
		String headers2[] = { "Tipo","Nombre", "Apellido", "Costo" };
		modelo.setColumnIdentifiers(headers2);
		tableTrabajadoresDispo.setModel(modelo);
		tableTrabajadoresDispo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int Selected= tableTrabajadoresDispo.getSelectedRow();
		        if ( Selected >= 0) {
		        	btnAgregar.setEnabled(true);
		        } else {
		        	btnAgregar.setEnabled(false);
		        	JOptionPane.showMessageDialog(null, "Selección Inválida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		scrollPaneDispTrabajadores.setViewportView(tableTrabajadoresDispo);
		
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
	                	comboBoxFiltro.setVisible(true);
	                }
	                boton--;                	
	            }
	     });

		 tabledefault();    
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
                	comboBoxFiltro.setVisible(false);
                }
                btnVolver.setVisible(false);
                boton2++;                            
            }
        });
			
		btnAgregar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableTrabajadoresDispo.getSelectedRow();
		        if (selectedRow >= 0) {	           
		            noselectedTrabajador = selectedRow;
		            Empresa.getInstance().getTrabajadoresNoSeleccionados().get(selectedRow).setSeleccionado(true);
		            disponiblesTableUpdate();
		            seleccionadosTableUpdate();
		        } else {
		            JOptionPane.showMessageDialog(null, "Selección Inválida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int selectedRow = tableTrabajaadoreSelec.getSelectedRow();
			        if (selectedRow >= 0) {
			            selectedTrabajador = selectedRow;
			            Empresa.getInstance().getTrabajadorsSeleccionados().get(selectedRow).setSeleccionado(false);
			            disponiblesTableUpdate();
			            seleccionadosTableUpdate();
			        } else {
			            JOptionPane.showMessageDialog(null, "Selección Inválida", "Error", JOptionPane.ERROR_MESSAGE);
			        }
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
		ClientesRegistrados();
		disponiblesTableUpdate();
		seleccionadosTableUpdate();
	}
		
	public void disponiblesTableUpdate() {
		modelo.setRowCount(0);
	    ArrayList<Trabajador> trabajadoresNoSeleccionados = Empresa.getInstance().getTrabajadoresNoSeleccionados();

	    for (Trabajador trabajador : trabajadoresNoSeleccionados) {
	        if (!trabajador.getSeleccionado()) {
	            Object[] rowData = {
	                trabajador.getClass().getSimpleName(),
	                trabajador.getApellido(),
	                trabajador.getNombre(),
	                trabajador.getSalario()
	            };
	            modelo.addRow(rowData);
	        }
	    }
	}

	
	public void seleccionadosTableUpdate() {	
		modelo2.setRowCount(0); 
		ArrayList<Trabajador> trabajadoresSeleccionados = Empresa.getInstance().getTrabajadorsSeleccionados();

	    for (Trabajador trabajador : trabajadoresSeleccionados) {
	        if (trabajador.getSeleccionado()) {
	            Object[] rowData = {
	                trabajador.getClass().getSimpleName(),
	                trabajador.getApellido(),
	                trabajador.getNombre(),
	                trabajador.getSalario()
	            };
	            modelo2.addRow(rowData);
	        }
	    }		
	}
	
	public void ClientesRegistrados() {
	    modelocl.setRowCount(0);
	    ArrayList<Cliente> clientesRegistrados = Empresa.getInstance().getClientesRegistrados();
	    
	    for (Cliente clientesReg : clientesRegistrados) {
	        if (!clientesReg.getEstado().equals("Disponible") || !clientesReg.getEstado().equals("No Disponible")) {
	            int cantidadProyectos = Empresa.getInstance().cantidadDeProyectosCliente(clientesReg);
	            Object[] rowData = {
	            		clientesReg.getEstado(),
	            		clientesReg.getId(),
	            		clientesReg.getNombre(), 
	            		clientesReg.getApellido(), 
	            		clientesReg.getDireccion(),
		    		    cantidadProyectos
	            };
	            modelocl.addRow(rowData);
	        }
	    }
	  
	}

	
	private void tabledefault() {
		for (Trabajador trabajador : Empresa.getInstance().getTrabajadorsSeleccionados())
			trabajador.setSeleccionado(true);

	}
	private ArrayList<Trabajador> filtrarTrabajadores(int selectedIndex) {
        ArrayList<Trabajador> trabajadoresFiltrados = new ArrayList<>();
        ArrayList<Trabajador> todosLosTrabajadores = Empresa.getInstance().obtenerListaDeTrabajadores();

        switch (selectedIndex) {
            case 0:
                trabajadoresFiltrados = todosLosTrabajadores;
                break;
            case 1:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t instanceof Diseñador) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
            case 2:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t instanceof Programador) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
            case 3:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t instanceof JefeProyecto) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
            case 4:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t instanceof Planificador) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
            case 5:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t.getEstado().equalsIgnoreCase("Disponibles")) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
            case 6:
                for (Trabajador t : todosLosTrabajadores) {
                    if (t.getEstado().equalsIgnoreCase("No Disponibles")) {
                        trabajadoresFiltrados.add(t);
                    }
                }
                break;
        }
        return trabajadoresFiltrados;
	}
	
	 public void cargarClientes(ArrayList<Cliente> clientes) {
			
		 int[] selectedRowsBefore = tableClientes.getSelectedRows();
		    ArrayList<Integer> selectedIndicesBefore = new ArrayList<>();
		    for (int row : selectedRowsBefore) {
		        selectedIndicesBefore.add(row);
		    }

		    modelocl.setRowCount(0);

		    for (Cliente cliente : clientes) {
		    	int cantidadProyectos = Empresa.getInstance().cantidadDeProyectosCliente(cliente);
		    	Object[] rowData = {
		    		    cliente.getEstado(),
		    		    cliente.getId(),
		    		    cliente.getNombre(), 
		    		    cliente.getApellido(), 
		    		    cliente.getDireccion(),
		    		    cantidadProyectos
		    		};
		        modelocl.addRow(rowData);
		    }

		    for (int i = 0; i < tableClientes.getRowCount(); i++) {
		        if (selectedIndicesBefore.contains(i)) {
		        	tableClientes.addRowSelectionInterval(i, i);
		        }
		    }
	 	}
	 
	
	 public void cargarTrabajadores(ArrayList<Trabajador> trabajadores) {
	
		 int[] selectedRowsBefore = tableTrabajadoresDispo.getSelectedRows();
		    ArrayList<Integer> selectedIndicesBefore = new ArrayList<>();
		    for (int row : selectedRowsBefore) {
		        selectedIndicesBefore.add(row);
		    }

		    modelo.setRowCount(0);

		    for (Trabajador trabajador : trabajadores) {
		        Object[] rowData = {
		            trabajador.getClass().getSimpleName(),
		            trabajador.getApellido(),
		            trabajador.getNombre(),
		            trabajador.getSalario()
		        };
		        modelo.addRow(rowData);
		    }

		    for (int i = 0; i < tableTrabajadoresDispo.getRowCount(); i++) {
		        if (selectedIndicesBefore.contains(i)) {
		            tableTrabajadoresDispo.addRowSelectionInterval(i, i);
		        }
		    }
	 	}
}	 