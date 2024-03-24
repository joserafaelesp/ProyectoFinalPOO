package Logico;

public class Dise�ador extends Trabajadores {
	
	private int experiencia;

	public Dise�ador(int id, String nombre, String apellido, String direccion, char sexo, int edad, float salario,
			String proyecto, String evaluacionAnual, int experiencia) {
		super(id, nombre, apellido, direccion, sexo, edad, salario, proyecto, evaluacionAnual);
		
		this.setExperiencia(experiencia);
	}
	
	
	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

}
