package Logico;

public class Evaluacion {

	private Trabajadores trabajador;
	private String proyecto;
	private int año;
	private String resultado;

	public Evaluacion(Trabajadores trabajador, String proyecto, int año, String resultado) {
		super();
		this.trabajador = trabajador;
		this.proyecto = proyecto;
		this.año = año;
		this.resultado = resultado;
	}

	public Trabajadores getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajadores trabajador) {
		this.trabajador = trabajador;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



}
