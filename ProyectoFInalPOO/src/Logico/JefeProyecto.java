package Logico;

public class JefeProyecto extends Trabajadores {
	
	private int CantPersonas;

	public JefeProyecto(int id, String nombre, String apellido, String direccion, char sexo, int edad, float salario,
			String proyecto, String evaluacionAnual, int CantPersonas) {
		super(id, nombre, apellido, direccion, sexo, edad, salario, proyecto, evaluacionAnual);
		
		this.setCantPersonas(CantPersonas);
	}

	public int getCantPersonas() {
		return CantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		CantPersonas = cantPersonas;
	}

}
