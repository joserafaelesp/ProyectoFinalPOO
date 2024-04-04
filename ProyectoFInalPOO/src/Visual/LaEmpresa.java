package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sun.java.swing.plaf.windows.resources.windows;

import Logico.Trabajador;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class LaEmpresa extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JLabel lblImagenCentral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaEmpresa frame = new LaEmpresa();
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
	public LaEmpresa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LaEmpresa.class.getResource("/imagenes/jetLogo.png")));
		setTitle("JET Solutions");
		
		dim = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setSize(dim.width-500 , dim.height-300);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuGestion = new JMenu("Trabajadores");
		menuGestion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		menuGestion.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/trabajador.png")));
		menuBar.add(menuGestion);
		
		JMenuItem itemAgregarTrabajador = new JMenuItem("Agregar Trabajador");
		itemAgregarTrabajador.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		
		itemAgregarTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegTrabajador agregarTrabajador = new RegTrabajador();
				agregarTrabajador.setModal(true);
				agregarTrabajador.setVisible(true);
			}
		});
		
		itemAgregarTrabajador.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/agregar.png")));
		menuGestion.add(itemAgregarTrabajador);
		
		JMenuItem itemListarTrabajadores = new JMenuItem("Listar Trabajadores");
		itemListarTrabajadores.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		itemListarTrabajadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				ListarTrabajadores listarTrabajadores = new ListarTrabajadores();
				listarTrabajadores.setModal(true);
				listarTrabajadores.setVisible(true);
				*/
			}
		});
		itemListarTrabajadores.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/listarTrabajador.png")));
		menuGestion.add(itemListarTrabajadores);
		
		JMenu menuClientes = new JMenu("Clientes");
		menuClientes.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		menuClientes.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/cliente.png")));
		menuBar.add(menuClientes);
		
		JMenuItem itemRegCliente = new JMenuItem("Registrar Cliente");
		itemRegCliente.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		itemRegCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				RegCliente registrarCliente = new RegCliente(null, false);
				registrarCliente.setModal(true);
				registrarCliente.setVisible(true);
				*/
				
			}
		});
		itemRegCliente.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/registrarCliente.png")));
		menuClientes.add(itemRegCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				ListarCliente listarCliente = new ListarCliente();
				listarCliente.setModal(true);
				listarCliente.setVisible(true);
				*/
				
			}
		});
		mntmListarClientes.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/listarClientes.png")));
		menuClientes.add(mntmListarClientes);
		
		JMenu menuProyectos = new JMenu("Proyectos");
		menuProyectos.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		menuProyectos.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/proyecto.png")));
		menuBar.add(menuProyectos);
		
		JMenuItem itemNuevoProyecto = new JMenuItem("Nuevo Proyecto");
		itemNuevoProyecto.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/agregar.png")));
		itemNuevoProyecto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		itemNuevoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	
				NuevoProyecto nuevoProyecto = new NuevoProyecto();
				nuevoProyecto.setModal(true);
				nuevoProyecto.setVisible(true);
				*/
			}
		});
		
		menuProyectos.add(itemNuevoProyecto);
		
		JMenuItem itemListarProyectos = new JMenuItem("Listar Proyectos");
		itemListarProyectos.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/ListarProyectos.png")));
		itemListarProyectos.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		itemListarProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*ListarProyectos listarProyectos = new ListarProyectos();
				listarProyectos.setModal(true);
				listarProyectos.setVisible(true);
				*/
			}
		});
		
		menuProyectos.add(itemListarProyectos);
		
		JMenu menuContratos = new JMenu("Contratos");
		menuContratos.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/contrato.png")));
		menuBar.add(menuContratos);
		
		JMenuItem ItemNuevoContrato = new JMenuItem("Nuevo Contrato");
		ItemNuevoContrato.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/agregar.png")));
		menuContratos.add(ItemNuevoContrato);
		
		JMenuItem ItemListarContratos = new JMenuItem("Listar Contratos");
		ItemListarContratos.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/ListarProyectos.png")));
		menuContratos.add(ItemListarContratos);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblImagenCentral = new JLabel("");
		lblImagenCentral.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenCentral.setIcon(new ImageIcon(LaEmpresa.class.getResource("/imagenes/jetLogo.png")));
		panel_1.add(lblImagenCentral, BorderLayout.CENTER);
	}
}
