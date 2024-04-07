package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logico.Cliente;
import Logico.Empresa;
import Logico.Trabajador;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ListarCliente extends JDialog {

	private ArrayList<Cliente> listaClientes;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static ListarCliente listarcliente;
	 

	    public static ListarCliente getInstance() {
	        if (listarcliente == null) {
	        	listarcliente = new ListarCliente();
	        }
	        return listarcliente;
	    }

	    public void obtenerListaDeTrabajadores() {
	    	listaClientes = Empresa.getInstance().obtenerListaDeClientes();
	        cargarClientes(listaClientes);
	    }

	    public void cargarClientes(ArrayList<Cliente> clientes) {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0);

	        for (Cliente cliente : clientes) {
	        	int cantidadProyectos = Empresa.getInstance().cantidadDeProyectosCliente(cliente);
	            Object[] rowData = {
	                cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDireccion(), cantidadProyectos
	            };
	            model.addRow(rowData);
	        }
	    }

	/**
	 * Create the dialog.
	 */
	public ListarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarCliente.class.getResource("/imagenes/listarClientes.png")));
		setTitle("Listar Clientes");
		setBounds(100, 100, 759, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 32, 701, 209);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"No. Id", "Nombre", "Apellido", "Direcci\u00F3n", "Cantidad de Proyectos"
			}
		));
		
		obtenerListaDeTrabajadores();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/fondoListarCliente.jpg")));
		lblNewLabel.setBounds(0, 0, 741, 266);
		contentPanel.add(lblNewLabel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnCrearProyecto = new JButton("Crear Proyecto");
			btnCrearProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CrearProyecto crearPro = new CrearProyecto();
					crearPro.setModal(true);
					crearPro.setVisible(true);
				}
			});
			buttonPane.add(btnCrearProyecto);
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent event) {
	                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {                
	                    btnCrearProyecto.setEnabled(true);
	                } else {
	                    btnCrearProyecto.setEnabled(false);
	                }
	            }
	        });
			
			{
				JButton btnEliminar = new JButton("Eliminar Cliente");
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
				
				btnEliminar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                int filaSeleccionada = table.getSelectedRow();
		                if (filaSeleccionada != -1) {
		                    Cliente clienteSeleccionado = listaClientes.get(filaSeleccionada);
		                    String idCliente = clienteSeleccionado.getId();
		                    
		                    int opcion = JOptionPane.showConfirmDialog(ListarCliente.this, 
		                            "¿Está seguro que desea eliminar el cliente con el ID: " + idCliente + "?",
		                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
		                    
		                    
		                    if (opcion == JOptionPane.YES_OPTION) {
		                        Empresa.getInstance().eliminarCliente(clienteSeleccionado);
		                        listaClientes.remove(clienteSeleccionado);
		                        cargarClientes(listaClientes);
		                        btnEliminar.setEnabled(false);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(ListarCliente.this, "Debe seleccionar un cliente para eliminar.",
		                            "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        });


		        btnEliminar.setEnabled(false);
		        btnCrearProyecto.setEnabled(false);
		        JButton cancelButton = new JButton("Salir");
		        cancelButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		                dispose();
		            }
		        });
		        
		        cancelButton.setActionCommand("Cancel");
		        buttonPane.add(cancelButton);

		        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		            public void valueChanged(ListSelectionEvent event) {
		               
		                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {                
		                    btnEliminar.setEnabled(true);
		                } else {
		                    btnEliminar.setEnabled(false);
		                }
		            }
		        });

		    }
		}
			
	}
}
