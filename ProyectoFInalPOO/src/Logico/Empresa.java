package Logico;

import java.util.ArrayList;

public class Empresa {

	private ArrayList<Trabajadores> trabajadores;
	private ArrayList<Evaluacion> evaluaciones;
	private ArrayList<Contrato> contratos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Proyecto> proyectos;
	public static int idTrabajador = 1;
	public static int idCliente = 1;
	public static int idContrato = 1;
	public static int idProyecto = 1;
	public static int idEvaluacion = 1;

	public Empresa() {
		super();
		this.trabajadores = new ArrayList<>();
		this.evaluaciones = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.proyectos = new ArrayList<>();
	}

	public ArrayList<Trabajadores> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(ArrayList<Trabajadores> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;

	}

	public static int getIdTrabajador() {
		return idTrabajador;
	}

	public static void setIdTrabajador(int idTrabajador) {
		Empresa.idTrabajador = idTrabajador;
	}

	public static int getIdCliente() {
		return idCliente;
	}

	public static void setIdCliente(int idCliente) {
		Empresa.idCliente = idCliente;
	}

	public static int getIdContrato() {
		return idContrato;
	}

	public static void setIdContrato(int idContrato) {
		Empresa.idContrato = idContrato;
	}

	public static int getIdProyecto() {
		return idProyecto;
	}

	public static void setIdProyecto(int idProyecto) {
		Empresa.idProyecto = idProyecto;
	}

	public static int getIdEvaluacion() {
		return idEvaluacion;
	}

	public static void setIdEvaluacion(int idEvaluacion) {
		Empresa.idEvaluacion = idEvaluacion;
	}

	public void agregarContrato(Contrato contrato) {
		contratos.add(contrato);
		idContrato++;
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
		idCliente++;
	}

	public void agregarProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
		idProyecto++;
	}

	public void agregarTrabajador(Trabajadores trabajador) {
		trabajadores.add(trabajador);
		idTrabajador++;
	}

	public void agregarEvaluacion(Evaluacion evaluacion) {
		evaluaciones.add(evaluacion);
		idEvaluacion++;
	}

	public ArrayList<Programador> buscarProgramadoresbyLenguaje(String lenguaje) {
		ArrayList<Programador> programadoresbyLenguaje = new ArrayList<>();

		for (Trabajadores trabajador : trabajadores) {

			if (trabajador instanceof Programador) { 

				Programador programador = (Programador) trabajador; 

				if (programador.getLenguajes().equalsIgnoreCase(lenguaje)) {

					programadoresbyLenguaje.add(programador);

				}
			}
		}
		return programadoresbyLenguaje;
	}

	private int contarProyectosAsignados(Trabajadores trabajador, String nombreProyecto, ArrayList<Trabajadores> trabajadores) {
		int proyectosAsignados = 0;
		for (Trabajadores t : trabajadores) {
			if (t.getNombre() != null && t.getNombre().equals(nombreProyecto)) {
				proyectosAsignados++;
			}
		}
		return proyectosAsignados;
	}

	public void asignarTrabajadorAProyecto(Trabajadores trabajador, String nombreProyecto, ArrayList<Trabajadores> trabajadores) {
		int proyectosAsignados = contarProyectosAsignados(trabajador, nombreProyecto, trabajadores);

		if (trabajador instanceof JefeProyecto && proyectosAsignados >= 2) {
			System.out.println("El jefe de proyecto ya tiene asignados 2 proyectos activos.");
			return;
		} else if (trabajador instanceof Diseñador && proyectosAsignados >= 2) {
			System.out.println("El diseñador ya tiene asignados 2 proyectos activos.");
			return;
		} else if (trabajador instanceof Programador && proyectosAsignados > 0) {
			System.out.println("El programador ya tiene asignado un proyecto activo.");
			return;
		}

		trabajador.setProyecto(nombreProyecto);
	}








}
