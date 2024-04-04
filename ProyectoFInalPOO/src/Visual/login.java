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
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 23));
		lblNewLabel.setBounds(170, 13, 162, 32);
		contentPane.add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setIcon(new ImageIcon(login.class.getResource("/imagenes/usuario.png")));
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 14));
		lblUsername.setBounds(33, 75, 100, 20);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setIcon(new ImageIcon(login.class.getResource("/imagenes/contrase\u00F1a.png")));
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPassword.setBounds(33, 126, 100, 17);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(134, 75, 230, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(134, 123, 230, 20);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				String username = txtUsername.getText();

				if (verificarCredenciales(username, password)) {
					JOptionPane.showMessageDialog(null, "Inicio de sesi�n exitoso", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Detalles de Login Invalidos", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				}
			}
		});
		btnLogin.setBounds(47, 211, 89, 23);
		contentPane.add(btnLogin);

		// Agregar icono al bot�n Login
		ImageIcon loginIcon = new ImageIcon(login.class.getResource("/imagenes/loginIcon.png"));
		Image imgLogin = loginIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnLogin.setIcon(new ImageIcon(imgLogin));

		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		btnReset.setBounds(184, 211, 104, 23);
		contentPane.add(btnReset);

		// Agregar icono al bot�n Reset
		ImageIcon resetIcon = new ImageIcon(login.class.getResource("/imagenes/resetIcon.png"));
		Image imgReset = resetIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnReset.setIcon(new ImageIcon(imgReset));

		JButton btnExit = new JButton("Salir");
		btnExit.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(335, 211, 89, 23);
		contentPane.add(btnExit);

		// Agregar icono al bot�n Exit
		ImageIcon exitIcon = new ImageIcon(login.class.getResource("/imagenes/exitIcon.png"));
		Image imgExit = exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(imgExit));

		JLabel lblRegistrar = new JLabel("<html><u>\u00BFA\u00FAn no tienes cuenta? Reg\u00EDstrate</u></html>");
		lblRegistrar.setForeground(new Color(255, 0, 0));
		lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrar.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblRegistrar.setBounds(134, 146, 230, 20);
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registroUsuarios registro = new registroUsuarios();
				registro.setVisible(true);
			}
		});
		contentPane.add(lblRegistrar);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.windowBorder);
		separator.setBounds(37, 196, 414, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.windowBorder);
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
