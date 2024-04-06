package Logico;

import java.io.Serializable;

public abstract class Trabajador implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected char sexo;
	protected int edad; 
	protected float pagoPorHora;
	protected float salario;
	protected String proyecto;
	protected String evaluacionAnual;
	protected boolean Seleccionado=false;
	
	public Trabajador(int id, String nombre, String apellido, String direccion, char sexo, int edad, float salario, float pagoPorHora,
			String proyecto, String evaluacionAnual) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.sexo = sexo;
		this.edad = edad;
		this.pagoPorHora = pagoPorHora;
		this.salario = salario;
		this.proyecto = proyecto;
		this.evaluacionAnual = evaluacionAnual;
		this.Seleccionado= false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPagoPorHora() {
		return pagoPorHora;
	}

	public void setPagoPorHora(float pagoPorHora) {
		this.pagoPorHora = pagoPorHora;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getEvaluacionAnual() {
		return evaluacionAnual;
	}

	public void setEvaluacionAnual(String evaluacionAnual) {
		this.evaluacionAnual = evaluacionAnual;
	}

	public boolean getSeleccionado() {
		return Seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.Seleccionado = seleccionado;
	}
	public float calcularSalarioDiario() {
	    float salarioDiario = pagoPorHora * 6;
	    return salarioDiario;
	}
}
