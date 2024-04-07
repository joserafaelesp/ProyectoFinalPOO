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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
	private ArrayList<Trabajador> listaDeTrabajadores = new ArrayList<>();
    private Empresa empresa;
    private Trabajador trabajador;
    
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JDateChooser dateChooser;
    private JSpinner spinnerPagoHora;
    private JComboBox<String> comboBox;
    private JCheckBox checkBoxDiseñador;
    private JCheckBox checkBoxProgramador;
    private JCheckBox checkBoxJProyecto;
    private JCheckBox checkBoxPlanificador;
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String sexo;
    private float salario;
    private float pagohora;
    private Date fechaNacim;
    private String proyecto;
    private int edad;
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
        
    	trabajador = new Diseñador("Disponible","","","","","",0,0,0,"");
    	trabajador = new Programador("Disponible", "", "", "", "", "", 0, 0, 0, "", new ArrayList<String>());
    	trabajador = new JefeProyecto("Disponible", "", "", "", "", "", 0, 0, 0, "", 0);
    	trabajador = new Planificador("Disponible", "", "", "", "", "", 0, 0, 0, "", 0);
    	
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegTrabajador.class.getResource("/imagenes/agregar.png")));
        setTitle("Nuevo Trabajador");
        setBounds(100, 100, 719, 569);
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
                
                textFieldSalario = new JTextField();
                textFieldSalario.setFont(new Font("Tahoma", Font.BOLD, 13));
                textFieldSalario.setEditable(false);
                textFieldSalario.setBounds(564, 166, 93, 22);
                panelMain.add(textFieldSalario);
                textFieldSalario.setColumns(10);
                
                Panel panelPlanificador = new Panel();
                panelPlanificador.setBounds(45, 348, 612, 65);
                panelMain.add(panelPlanificador);
                panelPlanificador.setLayout(null);
                
                JSpinner spinnerPlanificador = new JSpinner();
                spinnerPlanificador.setBounds(303, 22, 122, 22);
                panelPlanificador.add(spinnerPlanificador);
                

                Label lblcantDias = new Label("Cantidad de Días para la Planificación:");
                lblcantDias.setForeground(new Color(0, 0, 0));
                lblcantDias.setFont(new Font("Tahoma", Font.BOLD, 12));
                lblcantDias.setBounds(79, 20, 222, 24);
                panelPlanificador.add(lblcantDias);
                lblcantDias.setVisible(false);
                spinnerPlanificador.setVisible(false);

                Panel panelProgramador = new Panel();
                panelProgramador.setBounds(45, 348, 612, 63);
                panelMain.add(panelProgramador);
                panelProgramador.setLayout(null);
                
                Panel panelJP = new Panel();
                panelJP.setBounds(45, 348, 612, 60);
                panelMain.add(panelJP);
                panelJP.setLayout(null);

                JLabel lblId = new JLabel("Id:");
                lblId.setBounds(43, 74, 46, 14);
                panelMain.add(lblId);

                txtId = new JTextField();
                txtId.setBounds(103, 70, 219, 22);
                panelMain.add(txtId);
                txtId.setColumns(10);

                txtId.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        if (txtId.getText().length() != 11 && isVisible()) {
                            JOptionPane.showMessageDialog(null, "El ID debe tener exactamente 11 dígitos.", "Error ID",
                                    JOptionPane.ERROR_MESSAGE);
                            txtId.setText("");
                            txtId.requestFocus();
                        }
                    }
                });

                JLabel lblNombre = new JLabel("Nombre:");
                lblNombre.setBounds(43, 104, 50, 16);
                panelMain.add(lblNombre);

                txtNombre = new JTextField();
                txtNombre.setBounds(423, 101, 232, 22);
                panelMain.add(txtNombre);
                txtNombre.setColumns(10);

                JLabel lblApellido = new JLabel("Apellido:");
                lblApellido.setBounds(355, 104, 56, 16);
                panelMain.add(lblApellido);

                txtApellido = new JTextField();
                txtApellido.setBounds(103, 101, 219, 22);
                panelMain.add(txtApellido);
                txtApellido.setColumns(10);

                JLabel lblDirreccion = new JLabel("Direcci\u00F3n:");
                lblDirreccion.setBounds(43, 136, 60, 16);
                panelMain.add(lblDirreccion);

                txtDireccion = new JTextField();
                txtDireccion.setBounds(103, 133, 552, 22);
                panelMain.add(txtDireccion);
                txtDireccion.setColumns(10);

                JLabel lblSexo = new JLabel("Sexo:");
                lblSexo.setBounds(366, 73, 56, 16);
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
                comboBox.setBounds(423, 70, 232, 22);
                panelMain.add(comboBox);

                comboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JComboBox<String> cb = (JComboBox<String>) e.getSource();
                        String selectedOption = (String) cb.getSelectedItem();
                        if (selectedOption.equals("<<Seleccione>>")) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción de sexo válida.",
                                    "Error Sexo", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JLabel lblFechaNac = new JLabel("Fecha de Nacimiento:");
                lblFechaNac.setBounds(43, 165, 132, 24);
                panelMain.add(lblFechaNac);

                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setBorder(new CompoundBorder());
                dateChooser.setBounds(172, 167, 132, 22);
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
                                JOptionPane.showMessageDialog(null,"Por favor, ingresa una fecha válida para un trabajador mayor de 18 años.","Error Fecha", JOptionPane.ERROR_MESSAGE);
                                dateChooser.setDate(null);
                                dateChooser.requestFocus();
                            }
                        }
                    }
                });

                Label labelPagoHora = new Label("Pago por Hora:");
                labelPagoHora.setForeground(SystemColor.desktop);
                labelPagoHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
                labelPagoHora.setBounds(310, 165, 101, 24);
                panelMain.add(labelPagoHora);

                JPanel panelTipoTrabajador = new JPanel();
                panelTipoTrabajador.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(222, 184, 135)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(100, 100, 100)), "Tipo de Trabajador:", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.inactiveCaptionText));
                panelTipoTrabajador.setBounds(43, 214, 612, 90);
                panelMain.add(panelTipoTrabajador);
                panelTipoTrabajador.setLayout(new GridLayout(1, 0));
                
                JButton btnProgramador = new JButton("Seleccionar Lenguaje de Programación");
                btnProgramador.setBounds(114, 13, 357, 35);
                panelProgramador.add(btnProgramador);
               
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
                
                JCheckBox checkBoxDiseñador = new JCheckBox("Diseñador");
                checkBoxDiseñador.setFont(new Font("Tahoma", Font.BOLD, 13));
                checkBoxDiseñador.setForeground(new Color(210, 105, 30));
                checkBoxDiseñador.setBackground(new Color(245, 222, 179));
                JCheckBox checkBoxProgramador = new JCheckBox("Programador");
                checkBoxProgramador.setFont(new Font("Tahoma", Font.BOLD, 13));
                checkBoxProgramador.setForeground(new Color(210, 105, 30));
                checkBoxProgramador.setBackground(new Color(245, 222, 179));
                JCheckBox checkBoxJProyecto = new JCheckBox("Jefe de Proyecto");
                checkBoxJProyecto.setFont(new Font("Tahoma", Font.BOLD, 13));
                checkBoxJProyecto.setForeground(new Color(210, 105, 30));
                checkBoxJProyecto.setBackground(new Color(245, 222, 179));
                JCheckBox checkBoxPlanificador = new JCheckBox("Planificador");
                checkBoxPlanificador.setFont(new Font("Tahoma", Font.BOLD, 13));
                checkBoxPlanificador.setForeground(new Color(210, 105, 30));
                checkBoxPlanificador.setBackground(new Color(245, 222, 179));

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

                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(128, 128, 128));
                separator.setBounds(45, 328, 612, 2);
                panelMain.add(separator);
                
                Label label_1Salario = new Label("Salario:");
                label_1Salario.setForeground(SystemColor.desktop);
                label_1Salario.setFont(new Font("Tahoma", Font.PLAIN, 13));
                label_1Salario.setBounds(512, 165, 46, 24);
                panelMain.add(label_1Salario);
                
                JSpinner spinnerPagoHora = new JSpinner();
                spinnerPagoHora.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
                spinnerPagoHora.setForeground(SystemColor.desktop);
                spinnerPagoHora.setBounds(405, 168, 93, 22);
                panelMain.add(spinnerPagoHora);
                
                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setIcon(new ImageIcon(RegTrabajador.class.getResource("/imagenes/fondoTrab.jpg")));
                lblNewLabel.setBounds(0, 0, 691, 477);
                panelMain.add(lblNewLabel);

                spinnerPagoHora.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if (trabajador != null) {
                        	 Number value = (Number) spinnerPagoHora.getValue();
                             float pagoPorHora = value.floatValue();
                            trabajador.setPagoPorHora(pagoPorHora);
                            float salario = trabajador.calcularSalarioDiario();
                            textFieldSalario.setText(String.valueOf(salario));
                        } else {
                            System.out.println("Error: El objeto trabajador no ha sido inicializado.");
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
                    	
                    	if (comboBox.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione el sexo.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    	
                        if (txtApellido.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre.", "Error Nombre", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (txtNombre.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese el apellido.", "Error Apellido", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (txtDireccion.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese la dirección.", "Error Direccion", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (dateChooser.getDate() == null) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione la fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                       
                        float pagoHora = (float) spinnerPagoHora.getValue();
                        if (pagoHora <= 0) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor válido para el pago por hora.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        if (!checkBoxDiseñador.isSelected() && !checkBoxProgramador.isSelected() && !checkBoxJProyecto.isSelected() && !checkBoxPlanificador.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione el tipo de trabajador.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        Trabajador trabajador = null;
                        
                        id = txtId.getText();
                        nombre = txtNombre.getText();
                        apellido = txtApellido.getText();
                        direccion = txtDireccion.getText();
                        sexo = (String) comboBox.getSelectedItem();
                        fechaNacim = dateChooser.getDate();
                        String proyecto = null;
                        edad = calcularEdad(fechaNacim);
                        
                        
                        String salarioText = textFieldSalario.getText();
                        float salario = 0.0f;
                        if (!salarioText.isEmpty()) {
                            salario = Float.parseFloat(salarioText);
                        }
                        
                        pagohora = (float) spinnerPagoHora.getValue();
                        
                        if (checkBoxDiseñador.isSelected()) {
                            trabajador = new Diseñador("Disponible", id, nombre, apellido, direccion, sexo, edad, pagohora, salario, proyecto);
                        } else if (checkBoxProgramador.isSelected()) {
                            ArrayList<String> lenguajes = lenguajesProgramacion.getLenguajesSeleccionados();
                            trabajador = new Programador("Disponible", id, nombre, apellido, direccion, sexo, edad, pagohora, salario, proyecto, lenguajes);
                        } else if (checkBoxJProyecto.isSelected()) {
                            int cantPersonas = (int) spinnerJP.getValue();
                            trabajador = new JefeProyecto("Disponible", id, nombre, apellido, direccion, sexo, edad, pagohora, salario, proyecto, cantPersonas);
                        } else if (checkBoxPlanificador.isSelected()) {
                            int cantDias = (int) spinnerPlanificador.getValue();
                            trabajador = new Planificador("Disponible", id, nombre, apellido, direccion, sexo, edad, pagohora, salario, proyecto, cantDias);
                        }
                        
                        empresa.agregarTrabajador(trabajador);

                        listaDeTrabajadores.add(trabajador);
                        JOptionPane.showMessageDialog(null, "El trabajador ha sido agregado satisfactoriamente.", "", JOptionPane.INFORMATION_MESSAGE);
                        cargarTrabajadoresEnLista();
                        limpiarCampos();
                    }

                    private void limpiarCampos() {
                        txtId.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDireccion.setText("");
                        textFieldSalario.setText("");
                        dateChooser.setDate(null);
                        spinnerPagoHora.setValue(0);
                        //comboBox.setSelectedIndex(0);
                        checkBoxDiseñador.setSelected(false);
                        checkBoxProgramador.setSelected(false);
                        checkBoxJProyecto.setSelected(false);
                        checkBoxPlanificador.setSelected(false);
                    }
                });

                JButton cancelButton = new JButton("Salir");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
            
        }
        
        empresa = Empresa.getInstance();

    }
  
    private void cargarTrabajadoresEnLista() {
        ListarTrabajador listarTrabajador = ListarTrabajador.getInstance(); 
        listarTrabajador.cargarTrabajadores(listaDeTrabajadores); 
    }
    
    private int calcularEdad(Date fechaNacimiento) {
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNac, fechaActual);
        return periodo.getYears();
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