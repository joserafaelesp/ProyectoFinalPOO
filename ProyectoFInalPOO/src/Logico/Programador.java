package Logico;

public class Programador extends Trabajadores {

	private String lenguajes;
	
	
	public Programador(int id, String nombre, String apellido, String direccion, char sexo, int edad, float salario,
			String proyecto, String evaluacionAnual, String lenguajes) {
		super(id, nombre, apellido, direccion, sexo, edad, salario, proyecto, evaluacionAnual);
		
		this.setLenguajes(lenguajes);
	}


	public String getLenguajes() {
		return lenguajes;
	}


	public void setLenguajes(String lenguajes) {
		this.lenguajes = lenguajes;
	}
	
	

}
