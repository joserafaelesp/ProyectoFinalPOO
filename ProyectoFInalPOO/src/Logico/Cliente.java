package Logico;

import java.io.Serializable;
import java.util.ArrayList;

import Logico.Proyecto;

public class Cliente implements Serializable {  
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private ArrayList<Proyecto> proyectos;
    private String estado; 

    public Cliente(String estado, String id, String nombre, String apellido, String direccion, int cantidadProyectos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.estado = estado != null ? estado : "Disponible";
        this.proyectos = new ArrayList<>();
        
        if (cantidadProyectos > 0) {
            for (int i = 0; i < cantidadProyectos; i++) {
                try {
                    agregarProyecto(new Proyecto("Nombre", "Descripcion"));
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            }
        }
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void agregarProyecto(Proyecto proyecto) throws Exception {
        if (proyectos.size() < 5) {
            proyectos.add(proyecto);
            if (proyectos.size() >= 5) {
                this.estado = "No Disponible";
            }
        } else {
            throw new Exception("El cliente ya tiene el máximo número de proyectos.");
        }
    }
    
    public int cantidadDeProyectosCliente() {
        return proyectos.size();
    }

}
