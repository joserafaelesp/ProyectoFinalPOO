package Visual;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Logico.*;

public class ListarTrabajador extends JDialog {

    private ArrayList<Trabajador> listaTrabajadores;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private static ListarTrabajador listartrabajador;
    private JButton btnEliminar;

    public static ListarTrabajador getInstance() {
        if (listartrabajador == null) {
            listartrabajador = new ListarTrabajador();
        }
        return listartrabajador;
    }

    public void obtenerListaDeTrabajadores() {
        listaTrabajadores = Empresa.getInstance().obtenerListaDeTrabajadores();
        cargarTrabajadores(listaTrabajadores);
    }

    public void cargarTrabajadores(ArrayList<Trabajador> trabajadores) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Trabajador trabajador : trabajadores) {
            Object[] rowData = { trabajador.getEstado(), trabajador.getClass().getSimpleName(),
                    trabajador.getId(), trabajador.getApellido(), trabajador.getNombre(), trabajador.getDireccion(),
                    trabajador.getSexo(), trabajador.getEdad(), trabajador.getSalario(), trabajador.getPagoPorHora(),
                    trabajador.getProyecto() };
            model.addRow(rowData);
        }
    }

    public ListarTrabajador() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajador.class.getResource("/imagenes/listarClientes.png")));
        setTitle("Lista de Trabajadores");
        setBounds(100, 100, 1182, 479);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(210, 105, 30), null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        table = new JTable();
        table.setBackground(new Color(245, 245, 245));
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Estado", "Tipo", "Id", "Nombre",
                "Apellido", "Dirección", "Sexo", "Edad", "Pago por Hora", "Salario", "Proyecto" }));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(210, 105, 30)));
        scrollPane.setToolTipText("Tipo\r\nId\r\n");
        scrollPane.setBounds(33, 89, 1105, 284);
        contentPanel.add(scrollPane);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Filtro");
        lblNewLabel.setIcon(new ImageIcon(ListarTrabajador.class.getResource("/imagenes/filtrar.png")));
        lblNewLabel.setBounds(33, 23, 84, 22);
        contentPanel.add(lblNewLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(new Color(139, 69, 19));
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Diseñadores", "Programadores",
                "Jefes de Proyecto", "Planificadores", "Disponibles", "No Disponibles" }));
        comboBox.setBounds(31, 47, 131, 22);
        contentPanel.add(comboBox);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(ListarTrabajador.class.getResource("/imagenes/fondoListTrabajador.jpg")));
        lblNewLabel_1.setBounds(-11, 0, 1175, 397);
        contentPanel.add(lblNewLabel_1);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = comboBox.getSelectedIndex();
                ArrayList<Trabajador> trabajadoresFiltrados = filtrarTrabajadores(selectedIndex);
                cargarTrabajadores(trabajadoresFiltrados);
            }
        });

        obtenerListaDeTrabajadores();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnEliminar = new JButton("Eliminar Trabajador");
        btnEliminar.setActionCommand("OK");
        buttonPane.add(btnEliminar);
        getRootPane().setDefaultButton(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Trabajador trabajadorSeleccionado = listaTrabajadores.get(filaSeleccionada);
                    String idTrabajador = trabajadorSeleccionado.getId();
                    
                    int opcion = JOptionPane.showConfirmDialog(ListarTrabajador.this, 
                            "¿Está seguro que desea eliminar el trabajador con el ID " + idTrabajador + "?",
                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    
                    
                    if (opcion == JOptionPane.YES_OPTION) {
                        Empresa.getInstance().eliminarTrabajador(trabajadorSeleccionado);
                        listaTrabajadores.remove(trabajadorSeleccionado);
                        cargarTrabajadores(listaTrabajadores);
                        btnEliminar.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(ListarTrabajador.this, "Debe seleccionar un trabajador para eliminar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnEliminar.setEnabled(false);
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
}