package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Diseñador;
import Logico.Empresa;
import Logico.JefeProyecto;
import Logico.Planificador;
import Logico.Programador;
import Logico.Trabajador;
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
    private Trabajador trabajador;
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField textFieldSalario;

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
        setBounds(100, 100, 650, 509);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JPanel panelMain = new JPanel();
                panelMain.setBackground(SystemColor.menu);
                TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                        "Información General", TitledBorder.LEADING, TitledBorder.TOP);
                border.setTitleFont(new Font("Arial Black", Font.PLAIN, 14));
                panelMain.setBorder(new TitledBorder(new LineBorder(new Color(210, 105, 30)), "Informaci\u00F3n General", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(139, 69, 19)));

                panel.add(panelMain, BorderLayout.CENTER);

                panelMain.setBounds(10, 11, 429, 101);
                panel.add(panelMain);
                panelMain.setLayout(null);

                Panel panelProgramador = new Panel();
                panelProgramador.setBounds(22, 340, 577, 63);
                panelMain.add(panelProgramador);
                panelProgramador.setLayout(null);
                
                Panel panelJP = new Panel();
                panelJP.setBounds(22, 344, 577, 60);
                panelMain.add(panelJP);
                panelJP.setLayout(null);

                JLabel lblId = new JLabel("Id:");
                lblId.setBounds(22, 39, 46, 14);
                panelMain.add(lblId);

                txtId = new JTextField();
                txtId.setBounds(84, 35, 219, 22);
                panelMain.add(txtId);
                txtId.setColumns(10);

                txtId.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        if (txtId.getText().length() != 11 && isVisible()) {
                            JOptionPane.showMessageDialog(null, "El ID debe tener exactamente 11 dígitos.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            txtId.setText("");
                            txtId.requestFocus();
                        }
                    }
                });

                JLabel lblNombre = new JLabel("Nombre:");
                lblNombre.setBounds(22, 69, 50, 16);
                panelMain.add(lblNombre);

                txtNombre = new JTextField();
                txtNombre.setBounds(367, 66, 232, 22);
                panelMain.add(txtNombre);
                txtNombre.setColumns(10);

                JLabel lblApellido = new JLabel("Apellido:");
                lblApellido.setBounds(315, 69, 56, 16);
                panelMain.add(lblApellido);

                txtApellido = new JTextField();
                txtApellido.setBounds(84, 66, 219, 22);
                panelMain.add(txtApellido);
                txtApellido.setColumns(10);

                JLabel lblDirreccion = new JLabel("Direcci\u00F3n:");
                lblDirreccion.setBounds(22, 101, 60, 16);
                panelMain.add(lblDirreccion);

                txtDireccion = new JTextField();
                txtDireccion.setBounds(84, 98, 515, 22);
                panelMain.add(txtDireccion);
                txtDireccion.setColumns(10);

                JLabel lblSexo = new JLabel("Sexo:");
                lblSexo.setBounds(315, 38, 56, 16);
                panelMain.add(lblSexo);

            
	             txtNombre.addKeyListener(new KeyAdapter() {
	                 @Override
	                 public void keyTyped(KeyEvent e) {
	                     char c = e.getKeyChar();
	                     if (!Character.isLetter(c)) {
	                         e.consume(); 
	                         JOptionPane.showMessageDialog(null, "Por favor, ingrese solo letras.", "Error", JOptionPane.ERROR_MESSAGE);
	                     }
	                 }
	             });
	
	           
	             txtApellido.addKeyListener(new KeyAdapter() {
	                 @Override
	                 public void keyTyped(KeyEvent e) {
	                     char c = e.getKeyChar();
	                     if (!Character.isLetter(c)) { 
	                         e.consume(); 
	                         JOptionPane.showMessageDialog(null, "Por favor, ingrese solo letras.", "Error", JOptionPane.ERROR_MESSAGE);
	                     }
	                 }
	             });

                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "<<Seleccione>>", "Femenino", "Masculino" }));
                comboBox.setBounds(367, 35, 232, 22);
                panelMain.add(comboBox);

                comboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JComboBox<String> cb = (JComboBox<String>) e.getSource();
                        String selectedOption = (String) cb.getSelectedItem();
                        if (selectedOption.equals("<<Seleccione>>")) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción de sexo válida.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JLabel lblFechaNac = new JLabel("Fecha de Nacimiento:");
                lblFechaNac.setBounds(22, 130, 132, 24);
                panelMain.add(lblFechaNac);

                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setBorder(new CompoundBorder());
                dateChooser.setBounds(149, 133, 132, 22);
                panelMain.add(dateChooser);

                dateChooser.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        Date fechaNacimiento = dateChooser.getDate();
                        if (fechaNacimiento != null && !esAdulto(fechaNacimiento)) {
                            dateChooser.setDate(null);
                            dateChooser.requestFocus();
                        }
                    }
                });

                dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("date".equals(evt.getPropertyName())) {
                            Date fechaNacimiento = (Date) evt.getNewValue();
                            if (fechaNacimiento != null && !esAdulto(fechaNacimiento)) {
                                JOptionPane.showMessageDialog(null,"Por favor, ingresa una fecha válida para un trabajador mayor de 18 años.","Error", JOptionPane.ERROR_MESSAGE);
                                dateChooser.setDate(null);
                                dateChooser.requestFocus();
                            }
                        }
                    }
                });

                Label labelPagoHora = new Label("Pago por Hora:");
                labelPagoHora.setForeground(SystemColor.desktop);
                labelPagoHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
                labelPagoHora.setBounds(287, 130, 101, 24);
                panelMain.add(labelPagoHora);

                JPanel panelTipoTrabajador = new JPanel();
                panelTipoTrabajador.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(222, 184, 135)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(100, 100, 100)), "Tipo de Trabajador:", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.inactiveCaptionText));
                panelTipoTrabajador.setBounds(22, 179, 577, 75);
                panelMain.add(panelTipoTrabajador);
                panelTipoTrabajador.setLayout(new GridLayout(1, 0));
                
                JButton btnProgramador = new JButton("Seleccionar Lenguaje de Programación");
                btnProgramador.setBounds(114, 13, 332, 35);
                panelProgramador.add(btnProgramador);
                
                Panel panelPlanificador = new Panel();
                panelPlanificador.setBounds(0, 0, 577, 65);
                panelProgramador.add(panelPlanificador);
                panelPlanificador.setLayout(null);

                JSpinner spinnerPlanificador = new JSpinner();
                spinnerPlanificador.setBounds(303, 22, 122, 22);
                panelPlanificador.add(spinnerPlanificador);
                

                Label lblcantDias = new Label("Cantidad de Días para la Planificación:");
                lblcantDias.setForeground(new Color(0, 0, 0));
                lblcantDias.setFont(new Font("Tahoma", Font.BOLD, 12));
                lblcantDias.setBounds(79, 20, 222, 24);
                panelPlanificador.add(lblcantDias);
               
                JLabel lblcantTrabajadores = new JLabel("Cantidad de Trabajadores en su Proyecto:");
                lblcantTrabajadores.setForeground(SystemColor.textText);
                lblcantTrabajadores.setBounds(48, 13, 251, 24);
                panelJP.add(lblcantTrabajadores);
                
                btnProgramador.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        lenguajesProgramacion lenguajes = new lenguajesProgramacion();
                        lenguajes.setModal(true);
                        lenguajes.setVisible(true);
                    }
                });

                JSpinner spinnerJP = new JSpinner();
                spinnerJP.setBounds(295, 14, 132, 22);
                panelJP.add(spinnerJP);
                spinnerJP.setModel(new SpinnerNumberModel(0, 0, 5, 1));
                
                panelProgramador.setVisible(true);
                btnProgramador.setVisible(false);
                spinnerJP.setVisible(false);
                lblcantTrabajadores.setVisible(false);
                lblcantDias.setVisible(false);
                spinnerPlanificador.setVisible(false);
                
                JCheckBox checkBoxDiseñador = new JCheckBox("Diseñador");
                checkBoxDiseñador.setBackground(new Color(255, 239, 213));
                JCheckBox checkBoxProgramador = new JCheckBox("Programador");
                checkBoxProgramador.setBackground(new Color(255, 239, 213));
                JCheckBox checkBoxJProyecto = new JCheckBox("Jefe de Proyecto");
                checkBoxJProyecto.setBackground(new Color(255, 239, 213));
                JCheckBox checkBoxPlanificador = new JCheckBox("Planificador");
                checkBoxPlanificador.setBackground(new Color(255, 239, 213));

                checkBoxJProyecto.addActionListener(e -> {
                    boolean isSelected = checkBoxJProyecto.isSelected();
                    lblcantTrabajadores.setVisible(isSelected);
                    spinnerJP.setVisible(isSelected);
                    panelJP.setVisible(true);
                });
                
                checkBoxProgramador.addActionListener(e -> {
                    boolean isSelected = checkBoxProgramador.isSelected();
                    btnProgramador.setVisible(isSelected);
                    panelProgramador.setVisible(true);
                });
                
                checkBoxPlanificador.addActionListener(e -> {
                    boolean isSelected = checkBoxPlanificador.isSelected();
                    if (isSelected) {
                        panelPlanificador.setVisible(true);
                        spinnerPlanificador.setVisible(true);
                        lblcantDias.setVisible(true);
                    } else {
                        panelPlanificador.setVisible(false);
                        spinnerPlanificador.setVisible(false);
                        lblcantDias.setVisible(false);
                    }
                });
                
                ButtonGroup group = new ButtonGroup();
                group.add(checkBoxDiseñador);
                group.add(checkBoxProgramador);
                group.add(checkBoxJProyecto);
                group.add(checkBoxPlanificador);

                checkBoxDiseñador.addActionListener(e -> {
                    if (checkBoxDiseñador.isSelected()) {
                        checkBoxProgramador.setSelected(false);
                        checkBoxJProyecto.setSelected(false);
                        checkBoxPlanificador.setSelected(false);
                        btnProgramador.setVisible(false);
                        spinnerJP.setVisible(false);
                        lblcantTrabajadores.setVisible(false);
                        panelProgramador.setVisible(false);
                        panelJP.setVisible(false);
                        spinnerPlanificador.setVisible(false);
                        lblcantDias.setVisible(false);
                        panelPlanificador.setVisible(false);

                    }
                });
                checkBoxProgramador.addActionListener(e -> {
                    if (checkBoxProgramador.isSelected()) {
                        checkBoxDiseñador.setSelected(false);
                        checkBoxJProyecto.setSelected(false);
                        checkBoxPlanificador.setSelected(false);
                        btnProgramador.setVisible(true);
                        spinnerJP.setVisible(false);
                        lblcantTrabajadores.setVisible(false);
                        panelProgramador.setVisible(true);
                        panelJP.setVisible(false);
                        spinnerPlanificador.setVisible(false);
                        lblcantDias.setVisible(false);
                        panelPlanificador.setVisible(false);
                    }
                });
                checkBoxJProyecto.addActionListener(e -> {
                    if (checkBoxJProyecto.isSelected()) {
                        checkBoxDiseñador.setSelected(false);
                        checkBoxProgramador.setSelected(false);
                        checkBoxPlanificador.setSelected(false);
                        btnProgramador.setVisible(false);
                        spinnerJP.setVisible(true);
                        lblcantTrabajadores.setVisible(true);
                        panelProgramador.setVisible(false);
                        panelJP.setVisible(true);
                        spinnerPlanificador.setVisible(false);
                        lblcantDias.setVisible(false);
                        panelPlanificador.setVisible(false);
                    }
                });

                checkBoxPlanificador.addActionListener(e -> {
                    if (checkBoxPlanificador.isSelected()) {
                        checkBoxDiseñador.setSelected(false);
                        checkBoxProgramador.setSelected(false);
                        checkBoxJProyecto.setSelected(false);
                        btnProgramador.setVisible(false);
                        spinnerJP.setVisible(false);
                        lblcantTrabajadores.setVisible(false);
                        panelProgramador.setVisible(true);
                        panelJP.setVisible(false);
                        spinnerPlanificador.setVisible(true);
                        lblcantDias.setVisible(true);
                        panelPlanificador.setVisible(true);
                    }
                });

                panelTipoTrabajador.add(checkBoxDiseñador);
                panelTipoTrabajador.add(checkBoxProgramador);
                panelTipoTrabajador.add(checkBoxJProyecto);
                panelTipoTrabajador.add(checkBoxPlanificador);

                JPanel panelbuscarProy = new JPanel();
                panelbuscarProy.setBounds(22, 267, 577, 52);
                panelbuscarProy.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)),"Proyecto al que Pertenece:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                panelMain.add(panelbuscarProy);
                panelbuscarProy.setLayout(new GridLayout(1, 2, 5, 0));

                JTextField txtProyecto = new JTextField();
                panelbuscarProy.add(txtProyecto);

                JButton btnBuscar = new JButton("Buscar Proyecto");
                btnBuscar.setIcon(new ImageIcon(RegTrabajador.class.getResource("/imagenes/Busqueda.png")));
                panelbuscarProy.add(btnBuscar);

                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(210, 105, 30));
                separator.setBounds(22, 332, 577, 2);
                panelMain.add(separator);
                
                Label label_1Salario = new Label("Salario:");
                label_1Salario.setForeground(SystemColor.desktop);
                label_1Salario.setFont(new Font("Tahoma", Font.PLAIN, 13));
                label_1Salario.setBounds(467, 133, 46, 24);
                panelMain.add(label_1Salario);
                
                JSpinner spinnerPagoHora = new JSpinner();
                spinnerPagoHora.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
                spinnerPagoHora.setForeground(SystemColor.desktop);
                spinnerPagoHora.setBounds(379, 133, 82, 22);
                panelMain.add(spinnerPagoHora);

                JTextField textFieldSalario = new JTextField();
                textFieldSalario.setFont(new Font("Tahoma", Font.BOLD, 13));
                textFieldSalario.setForeground(new Color(210, 105, 30));
                textFieldSalario.setEditable(false);
                textFieldSalario.setBounds(519, 133, 80, 22);
                panelMain.add(textFieldSalario);
                textFieldSalario.setColumns(10);
                
                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setIcon(new ImageIcon(RegTrabajador.class.getResource("/imagenes/fondoTrabajador.png")));
                lblNewLabel.setBounds(0, 0, 622, 428);
                panelMain.add(lblNewLabel);

                spinnerPagoHora.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        float pagoPorHora = (float) spinnerPagoHora.getValue();
                        trabajador.setPagoPorHora(pagoPorHora);
                        float salario = trabajador.calcularSalarioDiario();
                        textFieldSalario.setText(String.valueOf(salario));
                    }
                });


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
                        JOptionPane.showMessageDialog(null, "El trabajador ha sido agregado satisfactoriamente.", "",JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                JButton cancelButton = new JButton("Salir");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        empresa = Empresa.getInstance();
        trabajador = new JefeProyecto(0, "", "", "", ' ', 0, 0, 0, "", "",0);
        trabajador = new Diseñador (0, "", "", "", ' ', 0, 0, 0, "", "", 0);
        trabajador = new Programador (0, "", "", "", ' ', 0, 0, 0, "", "", "");
        trabajador = new Planificador (0, "", "", "", ' ', 0, 0, 0, "", "", 0);

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

    private boolean esAdulto(java.util.Date fecha) {

        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(fecha);
        Calendar fechaLimite = Calendar.getInstance();
        fechaLimite.add(Calendar.YEAR, -18);
        return fechaNacimiento.before(fechaLimite);
    }
}
