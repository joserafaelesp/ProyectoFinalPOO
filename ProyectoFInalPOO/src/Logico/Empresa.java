package Logico;

import java.util.ArrayList;

public class Empresa {
	private JefeProyecto jefeProyecto;
	private Diseñador diseñador;
	private Planificador planificador;
	private ArrayList<Programador> programadores;
	private ArrayList<Evaluacion> evaluaciones;

	public Empresa() {
		super();
		this.programadores = new ArrayList<>();
		this.evaluaciones = new ArrayList<>();
	}

	public JefeProyecto getJefeProyecto() {
		return jefeProyecto;
	}

	public void setJefeProyecto(JefeProyecto jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}

	public Diseñador getDiseñador() {
		return diseñador;
	}

	public void setDiseñador(Diseñador diseñador) {
		this.diseñador = diseñador;
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
			throw new Exception("No se pueden agregar más programadores.");
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
		if (diseñador != null) {
			salarioTotal += diseñador.getSalario();
		}
		if (planificador != null) {
			salarioTotal += planificador.getSalario();
		}
		return salarioTotal;
	}

}
