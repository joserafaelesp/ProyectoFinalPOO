package Logico;

import java.sql.Date;
import java.time.LocalDate;
import java.io.Serializable;

public class Contrato implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private Cliente cliente;
    private Proyecto proyecto;
    private Date fechaInicio;
    private Date fechaEntrega;
    private double penalizacion;
    private double costo;

    public Contrato(String id, Cliente cliente, Proyecto proyecto, Date fechaInicio, Date fechaEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.proyecto = proyecto;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.penalizacion = 0; 
        this.costo = 0; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(double penalizacion) {
        this.penalizacion = penalizacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    public LocalDate getFechaInicioLocalDate() {
        return fechaInicio.toLocalDate();
    }
    public LocalDate getFechaEntregaLocalDate() {
        return fechaEntrega.toLocalDate();
    }
	
}
