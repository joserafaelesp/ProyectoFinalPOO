package Logico;

import java.io.Serializable;

public class Planificador extends Trabajador implements Serializable  {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int frecuenciaPlani;
	
	public Planificador(String estado, int id, String nombre, String apellido, String direccion, String sexo, int edad, float pagoPorHora, float salario,
			String proyecto, int frecuenciaPlani) {
		super(estado, id, nombre, apellido, direccion, sexo, edad, pagoPorHora, salario, proyecto);
		
		this.setFrecuenciaPlani(frecuenciaPlani);
	}

	public int getFrecuenciaPlani() {
		return frecuenciaPlani;
	}

	public void setFrecuenciaPlani(int frecuenciaPlani) {
		this.frecuenciaPlani = frecuenciaPlani;
	}
	
}
