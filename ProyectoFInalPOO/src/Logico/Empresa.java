package Logico;

import java.util.ArrayList;

public class Empresa {
	
	private JefeProyecto jefeProyecto;
	private Dise�ador dise�ador;
	private Planificador planificador;
	private ArrayList<Programador> programador;
	
	public Empresa(JefeProyecto jefeProyecto, Dise�ador dise�ador, Planificador planificador,
			ArrayList<Programador> programador) {
		super();
		this.jefeProyecto = jefeProyecto;
		this.dise�ador = dise�ador;
		this.planificador = planificador;
		this.programador = programador;
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

	public ArrayList<Programador> getProgramador() {
		return programador;
	}

	public void setProgramador(ArrayList<Programador> programador) {
		this.programador = programador;
	}

}
