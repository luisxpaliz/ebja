package ec.gob.educacion.ebja.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.AdminAlfabModuloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.OpcionSiNo;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class ProgramaGradoBean {

	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private ProgramaEbjaFacadeLocal programasEbja;
	@EJB
	private ProgramaGradoFacadeLocal programaGrado;
	@EJB
	private GradoFacadeLocal gradoFacadeLocal;
	@EJB
	private ProgramaEducativoFacadeLocal programasEducativos;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucion;
	@EJB
	private GrupoFaseFacadeLocal grupoFases;

	private List<Object[]> listaProgramaGrado;
	private List<ProgramaEbja> listaProgramaEbja;
	private int crearOeditar = 2;
	private List<Grado> listaGrado;
	private String programaSeleccionado;
	private String programaNuevoSeleccionado;
	private String programaGradoSeleccionado;
	private ProgramaGrado programaGradoEntity;
	private ProgramaGrado programaGradoEdicion;
	private ProgramaGrado programaGradoEdicionBorrar;
	private ProgramaGrado programaGradoEdicionEliminar;
	private Integer secuenciaGrado;
    private Integer secPack;
    private Integer gradoInicial;
    private int esVisible;
    private List<OpcionSiNo> listaSiNO;
    private String tipoProyectoSeleccionadoBusqueda;
    private List<ProgramaEducativo> listaProgramaEducativo;
    private String tipoFaseSeleccionadaBusqueda;
    private List<GrupoFasePrograma> listaGrupoFaseProg;

	@PostConstruct
	public void init() {
		consultarProgramaEbjaGrado();
	}

	public void setearCrearGrado() {
		crearOeditar = 1;
		setProgramaNuevoSeleccionado("Seleccionar Programa");
		setProgramaGradoSeleccionado("Seleccionar Grado");
		setEsVisible(3);
		setGradoInicial(0);
		setSecuenciaGrado(0);
		setSecPack(0);
		
	}
	
	public void obtenerFasesdeProyectoBusqueda() {
		
		if(tipoProyectoSeleccionadoBusqueda.isEmpty()) {
			listaGrupoFaseProg = new ArrayList<GrupoFasePrograma>();
		}else {
			listaGrupoFaseProg = grupoFases.buscarGrupoFaseProgActInternosXProyecto(tipoProyectoSeleccionadoBusqueda);
		}
	}
	
	public void obtenerOfertaBusqueda() {
		listaProgramaEbja = programasEbja.obtenerProgramaEbjaGrupoFaseExtraordinariaNemonico(tipoFaseSeleccionadaBusqueda);
	}
	
	public void buscarGrado() {
		
		if (programaNuevoSeleccionado == null && programaSeleccionado == null) {
		}else {
			listaProgramaGrado = programaGrado.buscarTodosGradosPorPrograma(programaSeleccionado);
		}
	}
	
	public void buscarGradoGuardado() {
		setProgramaSeleccionado(programaNuevoSeleccionado);
		listaProgramaGrado = programaGrado.buscarTodosGradosPorPrograma(programaNuevoSeleccionado);
	}
	
	public void buscarGradoActivado() {
		listaProgramaGrado = programaGrado.buscarTodosGradosPorPrograma(programaSeleccionado);
	}

	public void consultarProgramaEbjaGrado() {
		listaProgramaEducativo = new ArrayList();
		
		listaGrado = gradoFacadeLocal.obtenerGradoEbja();
		listaSiNO = new ArrayList<>();
		listaSiNO.add(new OpcionSiNo("Sí", "1"));
		listaSiNO.add(new OpcionSiNo("NO", "0"));
		listaProgramaEducativo = programasEducativos.buscarTodosProgramaEducativoActivos();
	}

	public void ejecutarCrearOEditarRegistro() {

		if (getCrearOeditar() == 1) {
			crearRegistroGrado();
		} else {
			editarRegla();
		}
	}

	public void crearRegistroGrado() {

		try {
			programaGradoEntity = new ProgramaGrado();
			cargarGrado();
			programaGrado.create(programaGradoEntity);
			buscarGradoGuardado();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {

			ex.printStackTrace();
			if(Util.getRootException(ex).getLocalizedMessage().contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_REGISTRO_DUPLICADO, ""));
			}else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}

		}
	}

	public void cargarGrado() {
		programaGradoEntity.setFechaCreacion(new Date());
		programaGradoEntity.setIpUsuario(sesionControlador.getIpAdressLocal());
		programaGradoEntity.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		programaGradoEntity.setEstado("1");
		programaGradoEntity.setIdpack(secPack);
		programaGradoEntity.setVisible(esVisible);
		programaGradoEntity.setGradoInicial(gradoInicial);
		programaGradoEntity.setSecuenciaGrado(secuenciaGrado);
		ProgramaEbja programaEbja = programasEbja.obtenerProgramaEbja(programaNuevoSeleccionado);
		programaGradoEntity.setProgramaEbja(programaEbja);
		Grado grado = gradoFacadeLocal.obtenerGrado(programaGradoSeleccionado);
		programaGradoEntity.setGrado(grado);
	}

	public void editarRegla() {

		try {
			
			programaGradoEdicion.setProgramaEbja(programasEbja.obtenerProgramaEbja(programaNuevoSeleccionado));
			programaGradoEdicion.setGrado(gradoFacadeLocal.obtenerGrado(programaGradoSeleccionado));
			programaGradoEdicion.setIdpack(secPack);
			programaGradoEdicion.setSecuenciaGrado(secuenciaGrado);
			programaGradoEdicion.setGradoInicial(gradoInicial);
			programaGradoEdicion.setVisible(esVisible);
			programaGrado.edit(programaGradoEdicion);
			buscarGradoGuardado();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}

	}

	public void gradoSeleccionadoEditar(Object[] object) {
		setearGrado();
		this.programaGradoEdicion = ((ProgramaGrado) (object[0]));
	    setProgramaNuevoSeleccionado(programaGradoEdicion.getProgramaEbja().getNemonico());
		setEsVisible(programaGradoEdicion.getVisible());
		setGradoInicial(programaGradoEdicion.getGradoInicial());
		setSecPack(programaGradoEdicion.getIdpack());
		setSecuenciaGrado(programaGradoEdicion.getSecuenciaGrado());
		setProgramaGradoSeleccionado(programaGradoEdicion.getGrado().getNemonico());
	}

	public void borrarGrado() {
		try {
			
			if(programaGradoEdicionBorrar.getEstado().contentEquals("0")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));

			}else {
				this.programaGradoEdicionBorrar.setEstado("0");
				programaGrado.edit(programaGradoEdicionBorrar);
				buscarGradoActivado();
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
			}
			
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
	
		public void eliminarGradoLogico() {
		
		if (buscarDependenciaPorCodigoProgramaEbja(programaGradoEdicionBorrar)) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo", new FacesMessage(
					FacesMessage.SEVERITY_FATAL,
					"No se puede eliminar el registro, porque existen datos relacionados en Ofertas Educativas", ""));
		} else {
			this.programaGradoEdicionBorrar.setEstado("3");
			programaGrado.edit(programaGradoEdicionBorrar);
			buscarGradoActivado();
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ELIMINO_EXITOSAMENTE, ""));
		}
	}
		
		public boolean buscarDependenciaPorCodigoProgramaEbja(ProgramaGrado tmpProgramaGrado) {
			
			boolean tmpcoincidenciaAcuerdo = false;
			for(ProgramaInstitucion tmpProgInst: programaInstitucion.buscarProgramaInstitucionActivos()) {
			      // if (tmpProgInst.getProgramaGrado().getId() == tmpProgramaGrado.getId()) {
				if (tmpProgInst.getId() == tmpProgramaGrado.getId()) {
			    	   tmpcoincidenciaAcuerdo = true;
			    	   break;
			       }
				
				if(tmpcoincidenciaAcuerdo == true) {
			    	   break;
			    }
			}
			return tmpcoincidenciaAcuerdo;
		}
	

	public void activarGrado() {
		try {
			if(programaGradoEdicion.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));

			}else {
				this.programaGradoEdicion.setEstado("1");
				programaGrado.edit(programaGradoEdicion);
				buscarGradoActivado();
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			
			}
			} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messagePagGrado",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}

	public void gradoSeleccionadoActivar(Object[] object) {
		this.programaGradoEdicion = ((ProgramaGrado) (object[0]));
	}

	public void gradoSeleccionadoBorrar(Object[] object) {
		this.programaGradoEdicionBorrar = ((ProgramaGrado) (object[0]));
	}
	
	public void gradoSeleccionadoEliminar(Object[] object) {
		this.programaGradoEdicionBorrar = ((ProgramaGrado) (object[0]));
	}

	private void setearGrado() {
		setCrearOeditar(2);
	}

	public List<Object[]> getListaProgramaGrado() {
		return listaProgramaGrado;
	}

	public void setListaProgramaGrado(List<Object[]> listaProgramaGrado) {
		this.listaProgramaGrado = listaProgramaGrado;
	}

	public List<Grado> getListaGrado() {
		return listaGrado;
	}

	public void setListaGrado(List<Grado> listaGrado) {
		this.listaGrado = listaGrado;
	}

	public List<ProgramaEbja> getListaProgramaEbja() {
		return listaProgramaEbja;
	}

	public void setListaProgramaEbja(List<ProgramaEbja> listaProgramaEbja) {
		this.listaProgramaEbja = listaProgramaEbja;
	}

	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramaSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}

	public String getProgramaGradoSeleccionado() {
		return programaGradoSeleccionado;
	}

	public void setProgramaGradoSeleccionado(String programaGradoSeleccionado) {
		this.programaGradoSeleccionado = programaGradoSeleccionado;
	}

	public String getProgramaNuevoSeleccionado() {
		return programaNuevoSeleccionado;
	}

	public void setProgramaNuevoSeleccionado(String programaNuevoSeleccionado) {
		this.programaNuevoSeleccionado = programaNuevoSeleccionado;
	}

	public int getCrearOeditar() {
		return crearOeditar;
	}

	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
	}

	public ProgramaGrado getProgramaGradoEdicion() {
		return programaGradoEdicion;
	}

	public void setProgramaGradoEdicion(ProgramaGrado programaGradoEdicion) {
		this.programaGradoEdicion = programaGradoEdicion;
	}

	public Integer getSecuenciaGrado() {
		return secuenciaGrado;
	}

	public void setSecuenciaGrado(Integer secuenciaGrado) {
		this.secuenciaGrado = secuenciaGrado;
	}

	public Integer getSecPack() {
		return secPack;
	}

	public void setSecPack(Integer secPack) {
		this.secPack = secPack;
	}

	public Integer getGradoInicial() {
		return gradoInicial;
	}

	public void setGradoInicial(Integer gradoInicial) {
		this.gradoInicial = gradoInicial;
	}

	public int getEsVisible() {
		return esVisible;
	}

	public void setEsVisible(int esVisible) {
		this.esVisible = esVisible;
	}

	public List<OpcionSiNo> getListaSiNO() {
		return listaSiNO;
	}

	public void setListaSiNO(List<OpcionSiNo> listaSiNO) {
		this.listaSiNO = listaSiNO;
	}

	public ProgramaGrado getProgramaGradoEdicionEliminar() {
		return programaGradoEdicionEliminar;
	}

	public void setProgramaGradoEdicionEliminar(ProgramaGrado programaGradoEdicionEliminar) {
		this.programaGradoEdicionEliminar = programaGradoEdicionEliminar;
	}

	public String getTipoProyectoSeleccionadoBusqueda() {
		return tipoProyectoSeleccionadoBusqueda;
	}

	public void setTipoProyectoSeleccionadoBusqueda(String tipoProyectoSeleccionadoBusqueda) {
		this.tipoProyectoSeleccionadoBusqueda = tipoProyectoSeleccionadoBusqueda;
	}

	public List<ProgramaEducativo> getListaProgramaEducativo() {
		return listaProgramaEducativo;
	}

	public void setListaProgramaEducativo(List<ProgramaEducativo> listaProgramaEducativo) {
		this.listaProgramaEducativo = listaProgramaEducativo;
	}

	public String getTipoFaseSeleccionadaBusqueda() {
		return tipoFaseSeleccionadaBusqueda;
	}

	public void setTipoFaseSeleccionadaBusqueda(String tipoFaseSeleccionadaBusqueda) {
		this.tipoFaseSeleccionadaBusqueda = tipoFaseSeleccionadaBusqueda;
	}

	public List<GrupoFasePrograma> getListaGrupoFaseProg() {
		return listaGrupoFaseProg;
	}

	public void setListaGrupoFaseProg(List<GrupoFasePrograma> listaGrupoFaseProg) {
		this.listaGrupoFaseProg = listaGrupoFaseProg;
	}
	
	
	
	
	
}
