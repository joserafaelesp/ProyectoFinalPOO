package Logico;

import java.sql.Date;

public class Evaluacion {

	private Trabajador trabajador;
	private Date a�o;
	private String resultado;

	public Evaluacion(Trabajador trabajador, String proyecto, Date a�o, String resultado) {
		super();
		this.trabajador = trabajador;
		this.a�o = a�o;
		this.resultado = resultado;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Date getA�o() {
		return a�o;
	}

	public void setA�o(Date a�o) {
		this.a�o = a�o;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



}
