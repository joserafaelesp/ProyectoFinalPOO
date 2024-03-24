package Logico;

import java.util.ArrayList;

public class Empresa {
	
	private JefeProyecto jefeProyecto;
	private Diseņador diseņador;
	private Planificador planificador;
	private ArrayList<Programador> programador;
	
	public Empresa(JefeProyecto jefeProyecto, Diseņador diseņador, Planificador planificador,
			ArrayList<Programador> programador) {
		super();
		this.jefeProyecto = jefeProyecto;
		this.diseņador = diseņador;
		this.planificador = planificador;
		this.programador = programador;
	}

	public JefeProyecto getJefeProyecto() {
		return jefeProyecto;
	}

	public void setJefeProyecto(JefeProyecto jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}

	public Diseņador getDiseņador() {
		return diseņador;
	}

	public void setDiseņador(Diseņador diseņador) {
		this.diseņador = diseņador;
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
