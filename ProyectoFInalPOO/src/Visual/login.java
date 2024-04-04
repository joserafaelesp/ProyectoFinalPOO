package Visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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

	public login() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(LaEmpresa.class.getResource("/imagenes/jetLogo.png")));
		setTitle("JET Solutions");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JLabel lblNewLabel = new JLabel("Login Systems");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel.setBounds(173, 11, 162, 32);
		contentPane.add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsername.setBounds(37, 78, 78, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPassword.setBounds(37, 147, 78, 14);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(134, 75, 230, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(134, 144, 230, 20);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				String username = txtUsername.getText();

				if (verificarCredenciales(username, password)) {
					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Detalles de Login Invalidos", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				}
			}
		});
		btnLogin.setBounds(54, 227, 89, 23);
		contentPane.add(btnLogin);

		// Agregar icono al botón Login
		ImageIcon loginIcon = new ImageIcon(login.class.getResource("/imagenes/loginIcon.png"));
		Image imgLogin = loginIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnLogin.setIcon(new ImageIcon(imgLogin));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		btnReset.setBounds(206, 227, 104, 23);
		contentPane.add(btnReset);

		// Agregar icono al botón Reset
		ImageIcon resetIcon = new ImageIcon(login.class.getResource("/imagenes/resetIcon.png"));
		Image imgReset = resetIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnReset.setIcon(new ImageIcon(imgReset));

		JButton btnExit = new JButton("Salir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(354, 227, 89, 23);
		contentPane.add(btnExit);

		// Agregar icono al botón Exit
		ImageIcon exitIcon = new ImageIcon(login.class.getResource("/imagenes/exitIcon.png"));
		Image imgExit = exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(imgExit));

		JLabel lblRegistrar = new JLabel("<html><u>¿Aun no te has registrado? Regístrate</u></html>");
		lblRegistrar.setForeground(Color.BLUE);
		lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRegistrar.setBounds(37, 172, 230, 20);
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registroUsuarios registro = new registroUsuarios();
				registro.setVisible(true);
			}
		});
		contentPane.add(lblRegistrar);

		JSeparator separator = new JSeparator();
		separator.setBounds(37, 196, 414, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 54, 414, 2);
		contentPane.add(separator_1);

		JLabel lblImagenCentral = new JLabel("");
		lblImagenCentral.setBounds(10, -18, 509, 313);
		lblImagenCentral.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenCentral.setIcon(new ImageIcon(login.class.getResource("/imagenes/fondoLogin.png")));
		lblImagenCentral.setOpaque(false);
		contentPane.add(lblImagenCentral);

		lblImagenCentral.setIcon(new ImageIcon(new ImageIcon(login.class.getResource("/imagenes/fondoLogin.png")).getImage().getScaledInstance(lblImagenCentral.getWidth(), lblImagenCentral.getHeight(), Image.SCALE_SMOOTH)));


	}

	private boolean verificarCredenciales(String username, String password) {
		try {
			File file = new File("usuarios.txt");
			if (!file.exists()) {
				return false;
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
					br.close();
					return true;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
