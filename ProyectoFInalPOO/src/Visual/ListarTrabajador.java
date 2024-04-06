package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logico.Trabajador;

public class ListarTrabajador extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarTrabajador dialog = new ListarTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public void cargarTrabajadores(ArrayList<Trabajador> trabajadores) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 
        
        for (Trabajador trabajador : trabajadores) {
            Object[] rowData = {
                trabajador.getId(),
                trabajador.getNombre(),
                trabajador.getApellido(),
                trabajador.getDireccion(),
                trabajador.getSexo(),
                trabajador.getEdad(),
                trabajador.getSalario(),
                trabajador.getProyecto()
            };
            model.addRow(rowData);
        }
    }

	public ListarTrabajador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajador.class.getResource("/imagenes/listarClientes.png")));
		setTitle("Lista de Trabajadores");
		setBounds(100, 100, 1182, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(12, 13, 1140, 356);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Filtrar");
				lblNewLabel.setIcon(new ImageIcon(ListarTrabajador.class.getResource("/imagenes/filtrar.png")));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel.setBounds(12, 13, 100, 32);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Dise\u00F1adores", "Programadores", "Jefes de Proyectos", "Planificadores", "Disponibles", "No Disponibles"}));
				comboBox.setBounds(12, 43, 188, 22);
				panel.add(comboBox);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setToolTipText("Tipo\r\nId\r\n");
				scrollPane.setBounds(12, 78, 1116, 265);
				panel.add(scrollPane);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Estado","Tipo", "Id", "Nombre", "Apellido", "Direcci\u00F3n", "Sexo", "Edad", "Pago por Hora", "Salario", "Proyecto"
					}
				));
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
