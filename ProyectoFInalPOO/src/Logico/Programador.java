package Logico;

import java.util.ArrayList;

public class Programador extends Trabajador {

	private ArrayList<String> lenguajes;
	
	public Programador(String estado, String id, String nombre, String apellido, String direccion, String sexo, int edad, float pagoPorHora, float salario,
	        String proyecto, ArrayList<String> lenguajes) {
	    super(estado, id, nombre, apellido, direccion, sexo, edad, pagoPorHora, salario, proyecto);
	    this.lenguajes = lenguajes;
	}

	public ArrayList<String> getLenguajes() {
		return lenguajes;
	}


	public void setLenguajes(ArrayList<String> lenguajes) {
		this.lenguajes = lenguajes;
	}


}
