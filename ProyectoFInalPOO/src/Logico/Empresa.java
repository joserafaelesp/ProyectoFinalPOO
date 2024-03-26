package Logico;

import java.util.ArrayList;

public class Empresa {

	private ArrayList<Trabajadores> trabajadores;
	private ArrayList<Evaluacion> evaluaciones;
	private ArrayList<Contrato> contratos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Proyecto> proyectos;

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

	public void agregarContrato(Contrato contrato) {
		contratos.add(contrato);
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
	}

	public void agregarTrabajador(Trabajadores trabajador) {
		trabajadores.add(trabajador);
	}

	public void agregarEvaluacion(Evaluacion evaluacion) {
		evaluaciones.add(evaluacion);
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







}
