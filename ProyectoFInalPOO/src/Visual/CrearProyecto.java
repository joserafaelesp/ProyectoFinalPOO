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

import com.toedter.calendar.JDateChooser;

import Logico.Cliente;
import Logico.Empresa;
import Logico.Proyecto;
import Logico.Trabajador;


import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.ScrollPane;


public class CrearProyecto extends JDialog {

	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtCodigoPoyecto;
	private JTable tableTrabajadoresDispo;
	private JTable tableClientesReg;
	private Trabajador selectedTrabajador;
	private Cliente selectedCliente;
	private JTextPane textPane_1;
	private JButton btnProgramador;
	private JScrollPane scrollPanePaciReg;
	private Panel panel;
 

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
		
		setTitle("Crear Proyecto");
		setBounds(100, 100, 999, 686);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JPanel panelInfoGeneral = new JPanel();
		panelInfoGeneral.setBorder(new TitledBorder(null, "Informacion general: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoGeneral.setBounds(12, 13, 926, 225);
		panelPrincipal.add(panelInfoGeneral);
		panelInfoGeneral.setLayout(null);
		
		JLabel lblCodigoProyecto = new JLabel("C\u00F3digo del proyecto:");
		lblCodigoProyecto.setBounds(12, 44, 110, 16);
		panelInfoGeneral.add(lblCodigoProyecto);
		
		txtCodigoPoyecto = new JTextField();
		txtCodigoPoyecto.setEditable(false);
		txtCodigoPoyecto.setBounds(129, 41, 116, 22);
		panelInfoGeneral.add(txtCodigoPoyecto);
		
		JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");
		lblNombreProyecto.setBounds(12, 83, 110, 14);
		panelInfoGeneral.add(lblNombreProyecto);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(132, 75, 143, 20);
		panelInfoGeneral.add(textPane_1);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(12, 116, 88, 14);
		panelInfoGeneral.add(lblFechaInicio);
		txtCodigoPoyecto.setColumns(10);
		
		JPanel panelListarTrabajadores = new JPanel();
		panelListarTrabajadores.setBorder(new TitledBorder(null, "Trabajadores disponibles: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListarTrabajadores.setBounds(40, 258, 320, 271);
		panelPrincipal.add(panelListarTrabajadores);
		panelListarTrabajadores.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneDispTrabajadores = new JScrollPane();
		panelListarTrabajadores.add(scrollPaneDispTrabajadores);
		
		tableTrabajadoresDispo = new JTable();
		tableTrabajadoresDispo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
				int DocSelectedColum= tableTrabajadoresDispo.getSelectedRow();
		        if (DocSelectedColum >= 0) {
		          
		        } else {
		        	JOptionPane.showMessageDialog(null, "Seleccion Invalida", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(132, 106, 143, 22);
		panelInfoGeneral.add(dateChooser1);
		
		JLabel lblFechaEntrega = new JLabel("Fecha entrega:");
		lblFechaEntrega.setBounds(12, 156, 88, 14);
		panelInfoGeneral.add(lblFechaEntrega);
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(132, 148, 143, 22);
		panelInfoGeneral.add(dateChooser2);
		
		JLabel lblTecnologia = new JLabel("Tecnologias:");
		lblTecnologia.setBounds(329, 45, 80, 14);
		panelInfoGeneral.add(lblTecnologia);
		
		/*JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "<<Seleccione>>", "Femenino", "Masculino" }));
        comboBox.setBounds(367, 35, 232, 22);
        panelInfoGeneral.add(comboBox);*/
		
		btnProgramador = new JButton("Seleccionar");
        btnProgramador.setBounds(321, 70, 88, 20);
        panelInfoGeneral.add(btnProgramador);
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(449, 45, 73, 14);
        panelInfoGeneral.add(lblDescripcion);
        
        JTextPane textPane = new JTextPane();
        textPane.setText("");
        textPane.setBounds(532, 34, 371, 162);
        panelInfoGeneral.add(textPane);
        
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
		JPanel panelListarCliente = new JPanel();
		panelListarCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clientes registradas:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelListarCliente.setBounds(271, 258, 402, 265);
		panelPrincipal.add(panelListarCliente);
		panelListarCliente.setLayout(new BorderLayout(0,0));
		
		scrollPanePaciReg = new JScrollPane();
		scrollPanePaciReg.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelListarCliente.add(scrollPanePaciReg, BorderLayout.CENTER);
		
		
			  
			  
		  
		
	  //ListarPersona listarReg = new ListarCLiente();
		//String cliete = "Cliente";*/
		
	//	tableTrabaReg = tablaPacienteDefault;
		scrollPanePaciReg.setViewportView(tableClientesReg);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(493, 534, 89, 23);
		panelPrincipal.add(btnSeleccionar);
		
		tableClientesReg = new JTable();
		
		    	
			
		 btnSeleccionar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	panelListarTrabajadores.setVisible(true);
	            	panelListarCliente.setVisible(false);
	                tableTrabajadoresDispo.setVisible(true);
	                tableClientesReg.setVisible(false);
	    
	            }
	        });

	        
		 panelListarTrabajadores.setVisible(false);
	     tableTrabajadoresDispo.setVisible(false);
	     tableClientesReg.setVisible(true);
	
		
		panelPrincipal.add(btnSeleccionar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(363, 534, 89, 23);
		panelPrincipal.add(btnVolver);
		
		panel = new Panel();
		panel.setBounds(687, 264, 286, 265);
		panelPrincipal.add(panel);
		
		btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelListarTrabajadores.setVisible(false);
            	panelListarCliente.setVisible(true);
                tableTrabajadoresDispo.setVisible(false);
                tableClientesReg.setVisible(true);
                
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
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}