package Logico;

import java.util.ArrayList;
import java.io.Serializable;

public class Cliente implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String apellido;
	private String direccion;
	private ArrayList<Proyecto>proyectos;
	
	public Cliente(String id, String nombre,String apellido, String direccion, ArrayList<Proyecto> proyectos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.proyectos = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
	public void agregarProyecto(Proyecto proyecto) throws Exception {
        if (proyectos.size() < 5) {
            proyectos.add(proyecto);
        } else {
            throw new Exception ("El cliente ya tiene el maximo numero de proyectos.");
        }
    }
}
