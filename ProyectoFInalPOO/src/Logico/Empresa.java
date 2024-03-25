package Logico;

import java.util.ArrayList;

public class Empresa {
	private JefeProyecto jefeProyecto;
	private Dise�ador dise�ador;
	private Planificador planificador;
	private ArrayList<Programador> programadores;
	private ArrayList<Evaluacion> evaluaciones;
	private ArrayList<Contrato> contratos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Proyecto> proyectos;

	public Empresa() {
		super();
		this.programadores = new ArrayList<>();
		this.evaluaciones = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.proyectos = new ArrayList<>();
	}

	public JefeProyecto getJefeProyecto() {
		return jefeProyecto;
	}

	public void setJefeProyecto(JefeProyecto jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}

	public Dise�ador getDise�ador() {
		return dise�ador;
	}

	public void setDise�ador(Dise�ador dise�ador) {
		this.dise�ador = dise�ador;
	}

	public Planificador getPlanificador() {
		return planificador;
	}

	public void setPlanificador(Planificador planificador) {
		this.planificador = planificador;
	}

	public ArrayList<Programador> getProgramadores() {
		return programadores;
	}

	public void agregarProgramador(Programador programador) throws Exception {
		if (programadores.size() < 3) {
			programadores.add(programador);
		} else {
			throw new Exception("No se pueden agregar m�s programadores.");
		}
	}

	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void agregarEvaluacion(Evaluacion evaluacion) {
		evaluaciones.add(evaluacion);
	}

	public double calcularSalarioTotal() {
		double salarioTotal = 0;
		
		for (Programador programador : programadores) {
			salarioTotal += programador.getSalario();
		}
		if (jefeProyecto != null) {
			salarioTotal += jefeProyecto.getSalario();
		}
		if (dise�ador != null) {
			salarioTotal += dise�ador.getSalario();
		}
		if (planificador != null) {
			salarioTotal += planificador.getSalario();
		}
		return salarioTotal;
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
}
