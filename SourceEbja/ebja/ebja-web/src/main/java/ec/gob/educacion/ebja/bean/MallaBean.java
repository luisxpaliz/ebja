package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.AsignaturaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MallaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.modelo.Asignatura;
import ec.gob.educacion.ebja.modelo.Malla;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class MallaBean extends BaseControlador implements Serializable{

	private static final long serialVersionUID = 1L;
	private String busquedaContenido = "";
	private int busquedaTipo = 1;
	private List<Object[]> listaMalla;
	private List<ProgramaEbja> listaPrograma;
	private List<Grado> listaGrado;
	private List<Asignatura> listaAsignatura;
	private String nemonico;
	private String descripcion;
	private String programaSeleccionado;
	private String asignaturaSeleccionada;
	private String gradoSeleccionado;
	private int crearOeditar = 2;
	private Malla nuevaMalla;
	private Malla malla;
	private Integer horasClase;
	
	@EJB
	private ProgramaEbjaFacadeLocal programaEbjaFacadeLocal;
	
	@EJB
	private AsignaturaFacadeLocal asignaturaFacadeLocal;
	
	@EJB
	private GradoFacadeLocal gradoFacadeLocal;
	
	@EJB
	private MallaFacadeLocal mallaFacadeLocal;
	
	@EJB
	private ProgramaGradoFacadeLocal programaGrado;
	
	@Inject
	private SesionControlador sesionControlador;
	
	
	@PostConstruct
	public void init() {
		
		consultarProgramaAsignatura();
		horasClase =0;
	}
	
	private List<Object[]> listaMallas;
	
	
	public void consultarProgramaAsignatura() {
		listaPrograma = programaEbjaFacadeLocal.findAllActive();
		listaAsignatura = asignaturaFacadeLocal.buscarTodasAsignaturasActivas();
		//listaGrado = gradoFacadeLocal.findAllActive();
		
	}
	
	
	public MallaBean() {
		listaMallas = new ArrayList<Object[]>();
		listaPrograma = new ArrayList<ProgramaEbja>();
		listaAsignatura = new ArrayList<Asignatura>();
		listaGrado = new ArrayList<Grado>();
	}
	
	
	public void buscarMalla() {
		
		switch (busquedaTipo) {
		case 1: {
			
			listaMalla = mallaFacadeLocal.findByCodigo(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaMalla = mallaFacadeLocal.findByDescripcion(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}

		mallaFacadeLocal.findByDescripcion(busquedaContenido);
	}
	
	public void setearCrearMalla() {
		setCrearOeditar(1);
		setNemonico("");
		setDescripcion("");
		setProgramaSeleccionado("");
		setAsignaturaSeleccionada("");
		setGradoSeleccionado("");
	}
	
	public void setearEditarMalla() {
		setCrearOeditar(2); 	
	}
	
	public void borrarMallaLogico() {
			
			if (malla.getEstado().contentEquals("0")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
			} else {
				malla.setEstado("0");
				mallaFacadeLocal.edit(malla);
				buscarMalla();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
			}
			
	}
	
	public void borrarMalla() {
		try {
			borrarMallaLogico();
		}catch(Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
		
	}
	
	public boolean validarCamposVacios() {
		return (getDescripcion().isEmpty() || getNemonico().isEmpty() || horasClase == null || programaSeleccionado.isEmpty()
				|| asignaturaSeleccionada.isEmpty() || gradoSeleccionado.isEmpty()) ? true : false;
	}
	
	public void ejecutarCrearOEditarRegistro() {
		
		if(!validarCamposVacios()) {
			if (crearOeditar == 1)
				crearRegistroMalla();
			else
				editarMalla();
			buscarMalla();
		}else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos vacios en el registro", ""));
		}
		
		
	}
	
	public void cargarComboGrado() {
		listaGrado = programaGrado.buscarGradoUnificadosPorPrograma(programaSeleccionado);
	}
	
	public void cargaDatosRegistro(){
		
		nuevaMalla.setDescripcion(getDescripcion());
		nuevaMalla.setEstado("1");
		nuevaMalla.setAsignatura(asignaturaFacadeLocal.findByNemonico(asignaturaSeleccionada));
		nuevaMalla.setFechaCreacion(new Date());
		nuevaMalla.setHorasClase(horasClase);
		nuevaMalla.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		nuevaMalla.setProgramaGrado((programaGrado.buscarProgramaGrado((Grado)gradoFacadeLocal.obtenerGrado(gradoSeleccionado), programaEbjaFacadeLocal.obtenerProgramaEbja(programaSeleccionado))).get(0));
		nuevaMalla.setIpUsuario(sesionControlador.getIpAdressLocal());
		nuevaMalla.setNemonico(getNemonico());
		
	}
	
	public void editarMalla() {
		
		try {		
			malla.setDescripcion(getDescripcion());
		    malla.setAsignatura(asignaturaFacadeLocal.findByNemonico(asignaturaSeleccionada));
			malla.setHorasClase(horasClase);
			malla.setProgramaGrado((programaGrado.buscarProgramaGrado((Grado)gradoFacadeLocal.obtenerGrado(gradoSeleccionado), programaEbjaFacadeLocal.obtenerProgramaEbja(programaSeleccionado))).get(0));			
			malla.setNemonico(getNemonico());
			mallaFacadeLocal.edit(malla);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}
		}
	}
	
	public void crearRegistroMalla() {
		try {
		nuevaMalla = new Malla();
		cargaDatosRegistro();
		mallaFacadeLocal.create(nuevaMalla);
		FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			}else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}
		}
	}
	
	public void activarMalla() {
		
		try {
			if (malla.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {
			malla.setEstado("1");
			mallaFacadeLocal.edit(malla);
			buscarMalla();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageMalla",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
		
		
	}
	
	
	
	public void mallaSeleccionadaEditar(Object[] object) {
		  setearEditarMalla();
		  this.malla = (Malla)(object[0]);
		  setNemonico(malla.getNemonico());
		  setDescripcion(malla.getDescripcion());
		  setHorasClase(malla.getHorasClase());
		  setProgramaSeleccionado(malla.getProgramaGrado().getProgramaEbja().getNemonico());
		  setAsignaturaSeleccionada(malla.getAsignatura().getNemonico());
		 // setGradoSeleccionado(malla.getProgramaGrado().getGradoUnificado().getGrado().getNemonico());
	}
	
    public void mallaSeleccionadoActivar(Object[] object) {
    	this.malla = (Malla)(object[0]);
	}
    
    public void mallaSeleccionadoBorrar(Object[] object) {
    	this.malla = (Malla)(object[0]);
	} 

	public List<Object[]> getListaMallas() {
		return listaMallas;
	}

	public void setListaMallas(List<Object[]> listaMallas) {
		this.listaMallas = listaMallas;
	}

	public String getBusquedaContenido() {
		return busquedaContenido;
	}

	public void setBusquedaContenido(String busquedaContenido) {
		this.busquedaContenido = busquedaContenido;
	}

	public int getBusquedaTipo() {
		return busquedaTipo;
	}

	public void setBusquedaTipo(int busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}

	public List<Object[]> getListaMalla() {
		return listaMalla;
	}

	public void setListaMalla(List<Object[]> listaMalla) {
		this.listaMalla = listaMalla;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public List<ProgramaEbja> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaEbja> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramaSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}

	public List<Asignatura> getListaAsignatura() {
		return listaAsignatura;
	}

	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}

	public String getAsignaturaSeleccionada() {
		return asignaturaSeleccionada;
	}

	public void setAsignaturaSeleccionada(String asignaturaSeleccionada) {
		this.asignaturaSeleccionada = asignaturaSeleccionada;
	}

	public List<Grado> getListaGrado() {
		return listaGrado;
	}

	public void setListaGrado(List<Grado> listaGrado) {
		this.listaGrado = listaGrado;
	}

	public String getGradoSeleccionado() {
		return gradoSeleccionado;
	}

	public void setGradoSeleccionado(String gradoSeleccionado) {
		this.gradoSeleccionado = gradoSeleccionado;
	}


	public int getCrearOeditar() {
		return crearOeditar;
	}


	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
	}


	public Integer getHorasClase() {
		return horasClase;
	}


	public void setHorasClase(Integer horasClase) {
		this.horasClase = horasClase;
	}
		
	
	
	
}
