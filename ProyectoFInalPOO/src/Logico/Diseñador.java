package Logico;

import java.util.Date;
import java.io.Serializable;

public class Dise�ador extends Trabajador implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dise�ador(String estado, int id, String nombre, String apellido, String direccion, String sexo, int edad, float pagoPorHora, float salario,
			String proyecto) {
		super(estado, id, nombre, apellido, direccion, sexo, edad, pagoPorHora, salario, proyecto);
		
	}

}
