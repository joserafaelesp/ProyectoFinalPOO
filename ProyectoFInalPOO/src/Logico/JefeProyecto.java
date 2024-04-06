package Logico;

public class JefeProyecto extends Trabajador {
	
	private int CantPersonas;

	public JefeProyecto(String estado, String id, String nombre, String apellido, String direccion, String sexo, int edad, float pagoPorHora, float salario,
			String proyecto,int CantPersonas) {
		super(estado, id, nombre, apellido, direccion, sexo, edad, pagoPorHora, salario, proyecto);
		
		this.setCantPersonas(CantPersonas);
	}

	public int getCantPersonas() {
		return CantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		CantPersonas = cantPersonas;
	}
	
}
