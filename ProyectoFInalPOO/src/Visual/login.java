package Visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Empresa;

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
				
				FileInputStream fempresa;
				FileOutputStream fempresa2;
				ObjectInputStream fempresaRead;
				ObjectOutputStream fempresaWrite;
				
				try {
					
					fempresa = new FileInputStream ("empresa.dat");
					fempresaRead = new ObjectInputStream(fempresa);
					Empresa temp = (Empresa)fempresaRead.readObject();
					Empresa.setEmpresa(temp);
					fempresa.close();
					fempresaRead.close();
					
				} catch (FileNotFoundException e) {
					
					try {
						
						fempresa2 = new  FileOutputStream("empresa.dat");
						fempresaWrite = new ObjectOutputStream(fempresa2);
						fempresaWrite.writeObject(Empresa.getInstance());
						fempresa2.close();
						fempresaWrite.close();
						
						
					} catch (FileNotFoundException e1) {
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
					
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				try {
					
					login login = new login();
					login.setVisible(true);
					
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

		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/imagenes/logoicono.png")));
		setTitle("JET Solutions Company");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 647, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 23));
		lblNewLabel.setBounds(238, 54, 162, 32);
		contentPane.add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setIcon(new ImageIcon(login.class.getResource("/imagenes/usuario.png")));
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 14));
		lblUsername.setBounds(134, 117, 100, 20);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setIcon(new ImageIcon(login.class.getResource("/imagenes/contrase\u00F1a.png")));
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPassword.setBounds(134, 168, 100, 17);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(233, 116, 230, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(233, 165, 230, 23);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				String username = txtUsername.getText();

				if (verificarCredenciales(username, password)) {
					
					txtPassword.setText(null);
					txtUsername.setText(null);
		
					LaEmpresa empresa = new LaEmpresa();
					empresa.setVisible(true);
					dispose(); 
				} else {
					JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña inválidos", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				}
			}
		});
		btnLogin.setBounds(115, 252, 89, 23);
		contentPane.add(btnLogin);

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
		btnReset.setBounds(252, 252, 104, 23);
		contentPane.add(btnReset);

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
		btnExit.setBounds(403, 252, 89, 23);
		contentPane.add(btnExit);


		ImageIcon exitIcon = new ImageIcon(login.class.getResource("/imagenes/exitIcon.png"));
		Image imgExit = exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(imgExit));

		JLabel lblRegistrar = new JLabel("<html><u>\u00BFA\u00FAn no tienes cuenta? Reg\u00EDstrate</u></html>");
		lblRegistrar.setForeground(new Color(255, 0, 0));
		lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrar.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblRegistrar.setBounds(238, 190, 230, 20);
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
		separator.setBounds(105, 237, 414, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.windowBorder);
		separator_1.setBounds(97, 95, 414, 2);
		contentPane.add(separator_1);

		JLabel lblImagenCentral = new JLabel("");
		lblImagenCentral.setBounds(0, 0, 628, 358);
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
