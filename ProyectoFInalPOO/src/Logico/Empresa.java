package Logico;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;


import java.io.Serializable;

public class Empresa implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Trabajador> trabajadores;
	private ArrayList<Evaluacion> evaluaciones;
	private ArrayList<Contrato> contratos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Proyecto> proyectos;
	public static int idTrabajador = 1;
	public static int idCliente = 1;
	public static int idContrato = 1;
	public static int idProyecto = 1;
	public static int idEvaluacion = 1;
	private static Empresa empresa;

	public Empresa() {
		super();
		this.trabajadores = new ArrayList<>();
		this.evaluaciones = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.proyectos = new ArrayList<>();
	}

	public static Empresa getInstance(){
		if(empresa == null){
			empresa= new Empresa();
		}
		return empresa;
	}

	public static void setEmpresa(Empresa empresa)
	{
		Empresa.empresa = empresa;
	}

	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;

	}

	public static int getIdTrabajador() {
		return idTrabajador;
	}

	public static void setIdTrabajador(int idTrabajador) {
		Empresa.idTrabajador = idTrabajador;
	}

	public static int getIdCliente() {
		return idCliente;
	}

	public static void setIdCliente(int idCliente) {
		Empresa.idCliente = idCliente;
	}

	public static int getIdContrato() {
		return idContrato;
	}

	public static void setIdContrato(int idContrato) {
		Empresa.idContrato = idContrato;
	}

	public static int getIdProyecto() {
		return idProyecto;
	}

	public static void setIdProyecto(int idProyecto) {
		Empresa.idProyecto = idProyecto;
	}

	public static int getIdEvaluacion() {
		return idEvaluacion;
	}

	public static void setIdEvaluacion(int idEvaluacion) {
		Empresa.idEvaluacion = idEvaluacion;
	}

	public void agregarContrato(Contrato contrato) {
		contratos.add(contrato);
		idContrato++;
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
		idCliente++;
	}

	public void agregarProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
		idProyecto++;
	}

	public void agregarTrabajador(Trabajador trabajador) {
		if (!trabajadores.contains(trabajador)) {
			trabajadores.add(trabajador);
			idTrabajador++;
		} else {
			System.out.println("El trabajador ya está presente en la lista.");
		}
	}


	public void agregarEvaluacion(Evaluacion evaluacion) {
		evaluaciones.add(evaluacion);
		idEvaluacion++;
	}


	private int contarProyectosAsignados(Trabajador trabajador, String nombreProyecto, ArrayList<Trabajador> trabajadores) {
		int proyectosAsignados = 0;
		for (Trabajador t : trabajadores) {
			if (t.getNombre() != null && t.getNombre().equals(nombreProyecto)) {
				proyectosAsignados++;
			}
		}
		return proyectosAsignados;
	}

	public void asignarTrabajadorAProyecto(Trabajador trabajador, String nombreProyecto, ArrayList<Trabajador> trabajadores) throws Exception{
		int proyectosAsignados = contarProyectosAsignados(trabajador, nombreProyecto, trabajadores);

		if (trabajador instanceof JefeProyecto && proyectosAsignados >= 2){
			throw new Exception("El jefe de proyecto ya tiene asignados 2 proyectos activos.");

		} else if (trabajador instanceof Diseñador && proyectosAsignados >= 2) {
			throw new Exception ("El diseñador ya tiene asignados 2 proyectos activos.");

		} else if (trabajador instanceof Programador && proyectosAsignados > 0) {
			throw new Exception("El programador ya tiene asignado un proyecto activo.");

		}

		trabajador.setProyecto(nombreProyecto);
	}

	public void eliminarCliente(Cliente selected) {
		clientes.remove(selected);
	}

	public void eliminarProyecto(Proyecto selected) {
		proyectos.remove(selected);
	}

	public void eliminarTrabajador(Trabajador selected) {
		trabajadores.remove(selected);
	}

	public void eliminarContrato(Contrato selected) {
		contratos.remove(selected);
	}

	public void calcularCostoProyecto(Contrato contrato) {

		LocalDate fechaInicio = contrato.getFechaInicioLocalDate();
		LocalDate fechaEntrega = contrato.getFechaEntregaLocalDate();

		long diasDiferencia = ChronoUnit.DAYS.between(fechaInicio, fechaEntrega);

		double costoProyecto = 0;
		for (Trabajador t : trabajadores) {
			costoProyecto += t.getSalario() * 6 * diasDiferencia;
		}
		costoProyecto *= 0.25;
		contrato.setCosto(costoProyecto);
	}

	public void aplicarPenalizacion(Contrato contrato) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaEntrega = contrato.getFechaEntregaLocalDate(); 

		if (fechaActual.isAfter(fechaEntrega)) {
			long diasAtraso = ChronoUnit.DAYS.between(fechaEntrega, fechaActual);
			double penalizacion = contrato.getCosto() * (diasAtraso * 0.01); 
			contrato.setPenalizacion(penalizacion);
		} else {
			contrato.setPenalizacion(0); 
		}
	}

	public Trabajador buscarTrabajadorPorId(String id) {
		for (Trabajador trabajador : trabajadores) {
			if (trabajador.getId().equals(id)) {
				return trabajador;
			}
		}
		return null;
	}

	public Contrato buscarContratoPorId(String id) {
		for (Contrato contrato : contratos) {
			if (contrato.getId().equals(id)) {
				return contrato;
			}
		}
		return null; 
	}

	public double calcularSalarioTotal() {
		double salarioTotal = 0.0;

		for (Trabajador trabajador : trabajadores) {
			salarioTotal += trabajador.getSalario();
		}

		return salarioTotal;
	}

	public Cliente buscarClientePorId(String id) {
		for (Cliente cliente : clientes) {
			if (cliente.getId().equals(id)) {
				return cliente;
			}
		}
		return null; 
	}

	public ArrayList<Evaluacion> obtenerEvaluacionesPorTrabajador(Trabajador trabajador) {
		ArrayList<Evaluacion> evaluacionesTrabajador = new ArrayList<>();

		for (Evaluacion evaluacion : evaluaciones) {

			if (evaluacion.getTrabajador().getId() == trabajador.getId()) {
				evaluacionesTrabajador.add(evaluacion);
			}
		}
		return evaluacionesTrabajador;
	}

	public void eliminarEvaluacion(Evaluacion selected) {
		evaluaciones.remove(selected);
	}

	public float calcularSalarioDiarioTrabajadores() {
		float salarioTotal = 0;
		for (Trabajador trabajador : trabajadores) {
			salarioTotal += trabajador.calcularSalarioDiario();
		}
		return salarioTotal;
	}

	public ArrayList<Trabajador> getTrabajadorsSeleccionados() {
		ArrayList<Trabajador> trabajadorsSeleccionados = new ArrayList<Trabajador>();
		for (Trabajador trabajador : trabajadores) 
			if (trabajador.getSeleccionado())
				trabajadorsSeleccionados.add(trabajador);


		return trabajadorsSeleccionados;
	}

	public ArrayList<Trabajador> getTrabajadoresNoSeleccionados() {
		ArrayList<Trabajador> TrabajadoresNoSeleccionados = new ArrayList<Trabajador>();
		for (Trabajador trabajador : trabajadores) 
			if (!trabajador.getSeleccionado())
				TrabajadoresNoSeleccionados.add(trabajador);


		return TrabajadoresNoSeleccionados;
	}

	public ArrayList<Cliente> getClientesRegistrados() {
		ArrayList<Cliente> clientesRegistrados = new ArrayList<>();
		for (Cliente cliente : clientes) {
			String estado = cliente.getEstado();
			if ("Disponible".equals(estado) || "No Disponible".equals(estado)) {
				clientesRegistrados.add(cliente);
			}
		}
		return clientesRegistrados;
	}

	/*public Proyecto buscarProyectoPorNombre(String nombreProyecto) {
	    for (Proyecto proyecto : proyectos) {
	        if (proyecto.getNombre().equalsIgnoreCase(nombreProyecto)) {
	            return proyecto;
	        }
	    }
	    return null;
	}*/

	public ArrayList<Trabajador> obtenerListaDeTrabajadores() {
		return this.trabajadores;
	}

	public ArrayList<Cliente> obtenerListaDeClientes() {
		return this.clientes;
	}

	public int cantidadDeProyectosCliente(Cliente cliente) {
		return cliente.getProyectos().size();
	}

	public Cliente obtenerCliente(String id) {
		return buscarClientePorId(id);
	}
	public ArrayList<Proyecto> obtenerListaDeProyectos() {
		return this.proyectos;
	}

	public double calcularCostoContrato() {
		double costoTotal = 0;
		ArrayList<Trabajador> trabajadoresSeleccionados = getTrabajadorsSeleccionados();

		for (Trabajador trabajador : trabajadoresSeleccionados) {
			costoTotal += trabajador.getSalario();
		}

		return costoTotal;
	}

}