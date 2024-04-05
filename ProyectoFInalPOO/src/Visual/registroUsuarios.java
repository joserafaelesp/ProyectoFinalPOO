package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class registroUsuarios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registroUsuarios frame = new registroUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private JTextField txtNewUsername;
	private JPasswordField txtNewPassword;

	  public registroUsuarios() {
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setTitle("Crear Cuenta");
	        setBounds(200, 200, 500, 310);
	        getContentPane().setLayout(null);
	        
	        Label labelRegistro = new Label("Register System");
	        labelRegistro.setForeground(Color.BLACK);
	        labelRegistro.setFont(new Font("Cambria", Font.BOLD, 23));
	        labelRegistro.setBounds(147, 35, 193, 29);
	        getContentPane().add(labelRegistro);

	        JLabel lblNewLabel = new JLabel("Nombre de usuario:");
	        lblNewLabel.setIcon(new ImageIcon(registroUsuarios.class.getResource("/imagenes/usuario.png")));
	        lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 14));
	        lblNewLabel.setBounds(42, 86, 155, 29);
	        getContentPane().add(lblNewLabel);

	        txtNewUsername = new JTextField();
	        txtNewUsername.setBounds(197, 90, 200, 20);
	        getContentPane().add(txtNewUsername);
	        txtNewUsername.setColumns(10);

	        JLabel lblNewLabel_1 = new JLabel("Contraseña:");
	        lblNewLabel_1.setIcon(new ImageIcon(registroUsuarios.class.getResource("/imagenes/contrase\u00F1a.png")));
	        lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 14));
	        lblNewLabel_1.setBounds(42, 128, 130, 29);
	        getContentPane().add(lblNewLabel_1);

	        txtNewPassword = new JPasswordField();
	        txtNewPassword.setBounds(197, 132, 200, 20);
	        getContentPane().add(txtNewPassword);

	        ImageIcon registerIcon = new ImageIcon(registroUsuarios.class.getResource("/imagenes/registrarIcon.png"));
	        JButton btnRegister = new JButton("Registrar", new ImageIcon(registerIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	        btnRegister.setFont(new Font("Cambria", Font.PLAIN, 13));
	        btnRegister.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String username = txtNewUsername.getText();
	                String password = new String(txtNewPassword.getPassword());

	                if (!username.isEmpty() && !password.isEmpty()) {
	                   
	                    guardarUsuario(username, password);
	                    JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
	                    dispose(); 
	                } else {
	                    JOptionPane.showMessageDialog(null, "Por favor ingrese nombre de usuario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });
	        btnRegister.setBounds(173, 203, 120, 23);
	        getContentPane().add(btnRegister);

	        ImageIcon resetIcon = new ImageIcon(registroUsuarios.class.getResource("/imagenes/resetIcon.png"));
	        JButton btnReset = new JButton("Reset", new ImageIcon(resetIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	        btnReset.setFont(new Font("Cambria", Font.PLAIN, 13));
	        btnReset.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                txtNewUsername.setText("");
	                txtNewPassword.setText("");
	            }
	        });
	        btnReset.setBounds(32, 203, 100, 23);
	        getContentPane().add(btnReset);

	        ImageIcon exitIcon = new ImageIcon(registroUsuarios.class.getResource("/imagenes/exitIcon.png"));
	        JButton btnExit = new JButton("Salir", new ImageIcon(exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	        btnExit.setFont(new Font("Cambria", Font.PLAIN, 13));
	        btnExit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        btnExit.setBounds(322, 203, 100, 23);
	        getContentPane().add(btnExit);
	        
	        JSeparator separator = new JSeparator();
	        separator.setForeground(SystemColor.windowBorder);
	        separator.setBounds(32, 188, 437, 2);
	        getContentPane().add(separator);
	        
	        JSeparator separator_1 = new JSeparator();
	        separator_1.setForeground(SystemColor.windowBorder);
	        separator_1.setBounds(32, 71, 437, 2);
	        getContentPane().add(separator_1);
	        
	        JLabel lblImagenCentral = new JLabel("");
	        lblImagenCentral.setBounds(0, -15, 500, 300);
	        lblImagenCentral.setHorizontalAlignment(SwingConstants.CENTER);
	        lblImagenCentral.setIcon(new ImageIcon(registroUsuarios.class.getResource("/imagenes/fondoRegistro.png")));
	        lblImagenCentral.setOpaque(false);
	        getContentPane().add(lblImagenCentral);
	        
	        lblImagenCentral.setIcon(new ImageIcon(new ImageIcon(registroUsuarios.class.getResource("/imagenes/fondoRegistro.png")).getImage().getScaledInstance(lblImagenCentral.getWidth(), lblImagenCentral.getHeight(), Image.SCALE_SMOOTH)));
	    }

	private void guardarUsuario(String username, String password) {
		try {
			FileWriter writer = new FileWriter("usuarios.txt", true);
			writer.write(username + "," + password + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
