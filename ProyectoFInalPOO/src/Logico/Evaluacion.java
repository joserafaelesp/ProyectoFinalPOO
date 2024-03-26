package Logico;

import java.sql.Date;

public class Evaluacion {

	private Trabajador trabajador;
	private Date año;
	private String resultado;

	public Evaluacion(Trabajador trabajador, String proyecto, Date año, String resultado) {
		super();
		this.trabajador = trabajador;
		this.año = año;
		this.resultado = resultado;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Date getAño() {
		return año;
	}

	public void setAño(Date año) {
		this.año = año;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



}
