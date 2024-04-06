package Logico;

import java.util.Date;
import java.io.Serializable;

public class Diseñador extends Trabajador implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Diseñador(String estado, int id, String nombre, String apellido, String direccion, String sexo, int edad, float pagoPorHora, float salario,
			String proyecto) {
		super(estado, id, nombre, apellido, direccion, sexo, edad, pagoPorHora, salario, proyecto);
		
	}

}
