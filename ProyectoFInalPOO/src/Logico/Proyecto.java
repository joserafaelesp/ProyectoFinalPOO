package Logico;

import java.io.Serializable;

public class Proyecto implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	
	public Proyecto(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
