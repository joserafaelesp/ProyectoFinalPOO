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
import javax.swing.table.DefaultTableModel;

import Logico.Cliente;
import Logico.Empresa;
import Logico.Proyecto;
import Logico.Trabajador;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ListarProyecto extends JDialog {

    private ArrayList<Proyecto> listaProyectos;
    private final JPanel contentPanel = new JPanel();
    private static ListarProyecto listarproyecto;
    private JTable table;

    // Constructor
    public ListarProyecto() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListarProyecto.class.getResource("/imagenes/ListarProyectos.png")));
        setTitle("Listar Proyectos");
        setBounds(100, 100, 573, 350);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Inicializar table aquí
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre del Proyecto", "Descripción"}
        ));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 28, 531, 227);
        contentPanel.add(scrollPane);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(ListarProyecto.class.getResource("/imagenes/fondoRCliente.jpg")));
        lblNewLabel.setBounds(-28, 0, 670, 268);
        contentPanel.add(lblNewLabel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnEliminarP = new JButton("Eliminar Proyecto");
        btnEliminarP.setEnabled(false);
        btnEliminarP.setActionCommand("OK");
        buttonPane.add(btnEliminarP);
        getRootPane().setDefaultButton(btnEliminarP);

        btnEliminarP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Proyecto proyectoSeleccionado = listaProyectos.get(filaSeleccionada);
                    String nombreProyecto = proyectoSeleccionado.getNombre();

                    int opcion = JOptionPane.showConfirmDialog(ListarProyecto.this,
                            "¿Está seguro que desea eliminar el proyecto de " + nombreProyecto + "?",
                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        Empresa.getInstance().eliminarProyecto(proyectoSeleccionado);
                        listaProyectos.remove(proyectoSeleccionado);
                        cargarProyectos(listaProyectos);
                        btnEliminarP.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(ListarProyecto.this, "Debe seleccionar un proyecto para eliminar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        // Asegurar que table se haya inicializado correctamente
        if (table != null) {
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                        btnEliminarP.setEnabled(true);
                    } else {
                        btnEliminarP.setEnabled(false);
                    }
                }
            });
        } else {
            System.err.println("Error: table no ha sido inicializado correctamente.");
        }

        // Inicializar listaProyectos aquí
        obtenerListaDeProyectos();
    }



    // Método para obtener la lista de proyectos
    public void obtenerListaDeProyectos() {
        listaProyectos = Empresa.getInstance().obtenerListaDeProyectos();
        cargarProyectos(listaProyectos);
    }

    public void cargarProyectos(ArrayList<Proyecto> proyectos) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Proyecto proyecto : proyectos) {
            if (proyecto != null) { // Verificar si el proyecto no es nulo
                Object[] rowData = {
                    proyecto.getNombre(), proyecto.getDescripcion()
                };
                model.addRow(rowData);
            } else {
                System.err.println("Error: El objeto Proyecto en la lista es nulo.");
            }
        }
    }


    // Método main para probar la clase
    public static void main(String[] args) {
        try {
            Empresa empresa = Empresa.getInstance();
            ListarProyecto dialog = new ListarProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una instancia de ListarProyecto
    public static ListarProyecto getInstance() {
        if (listarproyecto == null) {
            listarproyecto = new ListarProyecto();
        }
        return listarproyecto;
    }
}
