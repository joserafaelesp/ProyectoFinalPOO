package Logico;

import java.sql.Date;

public class Contrato {

	private String id;
	private Cliente cliente;
	private Proyecto proyecto;
	private Date fechaInicio;
	private Date fechaEntrega;
	
	public Contrato(String id, Cliente cliente, Proyecto proyecto, Date fechaInicio, Date fechaEntrega) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.proyecto = proyecto;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
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
	
	
	
}
