package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.gob.educacion.ebja.modelo.Asignatura;
import ec.gob.educacion.ebja.modelo.zeus.Area;
import ec.gob.educacion.ebja.modelo.zeus.Materia;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;
import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.AsignaturaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CatalogoFacadeLocal;

@ManagedBean
@ViewScoped
public class AsignaturaBean extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Asignatura> listaAsignatura;
	private List<Area> listaArea;
	private List<Materia> listaMateria;
	
	private Asignatura asignatura;
	private String busquedaContenido = "";
	private int crearOeditar = 2;
	private int busquedaTipo = 1;
	
	private String idArea;
	private String idMateria;
	private String nemonico;

	@EJB
	private AsignaturaFacadeLocal asignaturaFacadeLocal;
	@EJB
	private CatalogoFacadeLocal catalogoFacadeLocal;
	@Inject
	private SesionControlador sesionControlador;

	public AsignaturaBean() {
		listaAsignatura = new ArrayList<>();
		listaArea = new ArrayList<>();
		listaMateria = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		this.cargarListas();

	}
	
	public void buscarAsignatura() {

		switch (busquedaTipo) {
		case 1: {
			listaAsignatura = asignaturaFacadeLocal.findByArea(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaAsignatura = asignaturaFacadeLocal.findByMateria(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}
		
	}

	public void ejecutarCrearOEditarRegistro() {

		if (!validarCamposVacios()) {
			if (crearOeditar == 1)
				crearRegistroAsignatura();
			else 
				editarAsignatura();
			    buscarAsignatura();
			}
		 else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_CAMPOS_VACIOS, ""));
		}
	}

	public void borrarAsignatura() {
		try {
			borrarAsignaturaLogico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}

	public void activarAsignatura() {
		try {
			
			if (asignatura.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {
			
			asignatura.setEstado("1");
			asignaturaFacadeLocal.edit(asignatura);
			buscarAsignatura();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
		}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargaDatosRegistro() {

		asignatura.setArea(catalogoFacadeLocal.ObtenerArea(idArea));
		asignatura.setMateria(catalogoFacadeLocal.ObtenerMateria(idMateria));
		asignatura.setNemonico(nemonico);
		asignatura.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		asignatura.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		asignatura.setIpUsuario(sesionControlador.getIpAdressLocal());
		asignatura.setEstado("1");
		
	}

	public void setearCrearAsignatura() {
		crearOeditar = 1;
		this.setIdArea("");
		this.setIdMateria("");
		this.setNemonico("");
	}

	public void seteareditarAsignatura() {
		crearOeditar = 2;
	}
	
	public void asignaturaSeleccionadoEditar(Asignatura asignatura) {
		seteareditarAsignatura();
		this.asignatura = asignatura;
		setNemonico(asignatura.getNemonico());
		setIdArea(asignatura.getArea().getId().toString());
		setIdMateria(asignatura.getMateria().getId().toString());
	}
	

	private boolean validarCamposVacios() {
		return (idArea.toString().isEmpty() || idArea == null || getNemonico().isEmpty() ||idMateria.toString().isEmpty() || idMateria == null) ? true : false;
	}

	public void crearRegistroAsignatura() {
		try {
			asignatura = new Asignatura();
			cargaDatosRegistro();
			asignaturaFacadeLocal.create(asignatura);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}	
		}
	}
	
	
	public void editarAsignatura() {
		try {
			
			asignatura.setArea(catalogoFacadeLocal.ObtenerArea(idArea));
			asignatura.setMateria(catalogoFacadeLocal.ObtenerMateria(idMateria));
			asignatura.setNemonico(nemonico);
			setNemonico(asignatura.getNemonico());
			setIdArea(asignatura.getArea().getId().toString());
			setIdMateria(asignatura.getMateria().getId().toString());
			asignaturaFacadeLocal.edit(asignatura);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}}
	}

	public void asignaturaSeleccionadoActivar(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public void asignaturaSeleccionadoBorrar(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	public void borrarAsignaturaLogico() {
			
		if (asignatura.getEstado().contentEquals("0")) {
		    asignatura.setEstado("0");
			asignaturaFacadeLocal.edit(asignatura);
			buscarAsignatura();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
		}else {	
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAsignatura",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
		}
   }
	
	
	public void cargarListas() {
		listaArea = catalogoFacadeLocal.listaArea();
		listaMateria = catalogoFacadeLocal.listaMateria();
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public int getBusquedaTipo() {
		return busquedaTipo;
	}

	public int getCrearOeditar() {
		return crearOeditar;
	}

	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
	}

	public void setBusquedaTipo(int busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}

	public String getBusquedaContenido() {
		return busquedaContenido;
	}

	public void setBusquedaContenido(String busquedaContenido) {
		this.busquedaContenido = busquedaContenido;
	}

	public List<Asignatura> getlistaAsignatura() {
		return listaAsignatura;
	}

	public void setlistaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}

	public List<Area> getListaArea() {
		return listaArea;
	}

	public void setListaArea(List<Area> listaArea) {
		this.listaArea = listaArea;
	}

	public List<Materia> getListaMateria() {
		return listaMateria;
	}

	public void setListaMateria(List<Materia> listaMateria) {
		this.listaMateria = listaMateria;
	}
	
	public String getIdArea() {
		return idArea;
	}

	public void setIdArea(String idArea) {
		this.idArea = idArea; 
	}

	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}
	
	
}
