package Logico;

public class Planificador extends Trabajadores {
	 
	private int frecuenciaPlani;
	
	public Planificador(int id, String nombre, String apellido, String direccion, char sexo, int edad, float salario,
			String proyecto, String evaluacionAnual, int frecuenciaPlani) {
		super(id, nombre, apellido, direccion, sexo, edad, salario, proyecto, evaluacionAnual);
		
		this.setFrecuenciaPlani(frecuenciaPlani);
	}

	public int getFrecuenciaPlani() {
		return frecuenciaPlani;
	}

	public void setFrecuenciaPlani(int frecuenciaPlani) {
		this.frecuenciaPlani = frecuenciaPlani;
		
		
	}

}
