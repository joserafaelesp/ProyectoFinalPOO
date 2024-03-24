package Logico;

import java.util.ArrayList;

public class Empresa {
	
	private JefeProyecto jefeProyecto;
	private Diseñador diseñador;
	private Planificador planificador;
	private ArrayList<Programador> programador;
	
	public Empresa(JefeProyecto jefeProyecto, Diseñador diseñador, Planificador planificador,
			ArrayList<Programador> programador) {
		super();
		this.jefeProyecto = jefeProyecto;
		this.diseñador = diseñador;
		this.planificador = planificador;
		this.programador = programador;
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

	public ArrayList<Programador> getProgramador() {
		return programador;
	}

	public void setProgramador(ArrayList<Programador> programador) {
		this.programador = programador;
	}

}
