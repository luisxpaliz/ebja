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
import ec.gob.educacion.ebja.facade.local.FormularioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MensajeFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Formulario;
import ec.gob.educacion.ebja.modelo.Mensaje;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class MensajeBean extends BaseControlador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Object[]> listaMensajes;
	private Mensaje mensaje;
	private String cabecera;
	private String nemonico;
	private String busquedaContenido = "";
	private int crearOeditar = 2;
	private int busquedaTipo = 1;
	private List<Formulario> listaFormularios;
	private List<ProgramaEbja> listaPrograma;
	private String programaSeleccionado;
	private String formularioSeleccionado;
	
	@EJB
	private MensajeFacadeLocal mensajes;
	@EJB
	private ProgramaEbjaFacadeLocal programaEbja;
	@EJB
	private FormularioFacadeLocal formularios;
	@Inject
	private SesionControlador sesionControlador;
	
	
	@PostConstruct
	public void init() {
		consultarFormularioPrograma();

	}
	
	private void consultarFormularioPrograma() {
		listaFormularios = formularios.findAllActive();
		listaPrograma = programaEbja.findAllActive();
	}

	public MensajeBean() {
		listaMensajes = new ArrayList<Object[]>();
		listaFormularios = new ArrayList<>();
		listaPrograma = new ArrayList<>();
	}

	public void buscarMensaje() {

		switch (busquedaTipo) {
		case 1: {
			listaMensajes = mensajes.buscarMensajesNemonico(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaMensajes = mensajes.buscarMensajesNombre(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}
	}

	public void ejecutarCrearOEditarRegistro() {

		if (!validarCamposVacios()) {
			if (crearOeditar == 1)
				crearRegistroMensaje();
			else
				editarMensaje();
			    buscarMensaje();
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}

	public void borrarMensaje() {
		try {
			borrarMensajeLogico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}

	public void activarMensaje() {
		try {
			
			if (this.mensaje.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {
				
		    mensaje.setEstado("1");
			mensajes.edit(mensaje);
			buscarMensaje();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargaDatosRegistro() {

		mensaje.setCabecera(cabecera);
		mensaje.setNemonico(nemonico);
		mensaje.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue()); // PENDIENTE : esto se definira cuando de determine el sistema de seguridad.
		mensaje.setEstado("1"); // Activo
		mensaje.setFechaCreacion(new Date());
		mensaje.setIpUsuario(sesionControlador.getIpAdressLocal());
		mensaje.setProgramaEbja(programaEbja.obtenerProgramaEbja(programaSeleccionado));
		mensaje.setFormulario(formularios.obtenerFormulario(formularioSeleccionado));
		
	}

	public void setearCrearMensaje() {
		crearOeditar = 1; // bandera de crear mensaje
		setCabecera("");
		setNemonico("");
	}

	public void setearEditarMensaje() {
		crearOeditar = 2; // bandera de editar mensaje
	}

	private boolean validarCamposVacios() {
		return (cabecera.isEmpty() || nemonico.isEmpty()) ? true : false;
	}

	public void crearRegistroMensaje() {
		try {
			mensaje = new Mensaje();
			cargaDatosRegistro();
			mensajes.create(mensaje);
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {
			
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}
		}
	}

	public void mensajeSeleccionadoEditar(Object[] object) {
		setearEditarMensaje();
		this.mensaje = (Mensaje) (object[0]);
		setProgramaSeleccionado(mensaje.getProgramaEbja().getNemonico());
		setFormularioSeleccionado(mensaje.getFormulario().getNemonico());
		setNemonico(mensaje.getNemonico());
		setCabecera(mensaje.getCabecera());

	}

	public void mensajeSeleccionadoActivar(Object[] object) {
		this.mensaje = (Mensaje) (object[0]);
	}

	public void mensajeSeleccionadoBorrar(Object[] object) {
		this.mensaje = (Mensaje) (object[0]);
	}

	public void editarMensaje() {

		try {
			mensaje.setCabecera(cabecera);
			mensaje.setNemonico(nemonico);
			mensajes.edit(mensaje);
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));

		} catch (Exception ex) {
			
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));	
			}
		}

	}

	public void borrarMensajeLogico() {
		    
		if (this.mensaje.getEstado().contentEquals("0")) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
		}else {
			mensaje.setEstado("0");
			mensajes.edit(mensaje);
			buscarMensaje();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
		}
	}

	public void borrarMensajeFisico() {
		mensajes.remove(mensaje);
		buscarMensaje();
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

	public List<Object[]> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<Object[]> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public List<Formulario> getListaFormularios() {
		return listaFormularios;
	}

	public void setListaFormularios(List<Formulario> listaFormularios) {
		this.listaFormularios = listaFormularios;
	}

	public List<ProgramaEbja> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaEbja> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramaSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}

	public String getFormularioSeleccionado() {
		return formularioSeleccionado;
	}

	public void setFormularioSeleccionado(String formularioSeleccionado) {
		this.formularioSeleccionado = formularioSeleccionado;
	}
	
	
	
}
