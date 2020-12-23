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
import ec.gob.educacion.ebja.facade.local.CircuitoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CursoParaleloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DocenteCursoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DocenteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.DocenteCurso;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.Docente;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;

@ManagedBean
@ViewScoped
public class SeleccionDocenteBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//private static final String FRM_MENSAJE = "frmForm:messageSeleccionDocentes";
	

	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private ProgramaEbjaFacadeLocal programaEbjaFacadeLocal;
	@EJB
	private ZonaFacadeLocal zonaFacadeLocal;
	@EJB
	private DistritoFacadeLocal distritoFacadeLocal;
	@EJB
	private CircuitoFacadeLocal circuitoFacadeLocal;
	@EJB
	private CursoParaleloFacadeLocal cursoParaleloFacadeLocal;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucionFacadeLocal;
	@EJB
	private DocenteFacadeLocal docenteFacadeLocal;
	@EJB
	private DocenteCursoFacadeLocal docenteCursoFacadeLocal;
	
	private List<ProgramaEbja> listaProgramaEbja;
	private List<Zona> listaZona;
	private List<Distrito> listaDistrito;
	private List<Circuito> listaCircuito;
	private List<CursoParalelo> listaCursoParalelo;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<Docente> listaDocente;
	private List<DocenteCurso> listaDocenteCurso;
	
	private ProgramaInstitucion selectedInstitucion;
	private Docente selectedDocente;
	private CursoParalelo cursoParalelo;
	private DocenteCurso docenteCurso;

	private Integer idProgramaEbja;
	private Integer idZona;
	private Integer idDistrito;
	private Integer idCircuito;
	private Integer idCursoParalelo;
	private Integer aforo;
	private Integer numBanca;
	private String nombrePrograma;
	private String nombreInstitucionSeleccionada;
	private String amieInstitucionSeleccionada;
	private String geocodigoInstitucionSeleccionada;
	private String nombreDocenteSeleccionado;
	private String identificacionDocenteSeleccionado;
	
	private boolean disabledCbAgregar = true;
	private boolean disabledCbBuscarInstitucion = true;
	
	private int busquedaTipoDocente = 1;
	private int busquedaTipo = 1;
	private String busquedaContenidoDocente = "";
	private String busquedaContenido = "";
	
	private int crearOEditar = 2;
	


	@PostConstruct
	public void init() {
		cargarListas();
	}
	
	public void reseteaObjetos() {
		disabledCbAgregar = true;
	}
	
	public void reseteaCamposBusqueda() {
		switch (this.getBusquedaTipo()) {
			case 1: {
				this.cargarListas();
				this.setIdZona(null);
				break;
			}
			case 2: {
				this.setBusquedaContenido("");
				break;
			}
		}
	}
	
	public void reseteaCamposBusquedaDocente() {
		this.setBusquedaContenidoDocente("");
	}
	
	public void setearCrearDocente() {
		this.setCrearOEditar(1);
		this.setIdCursoParalelo(null);
	}
	
	public void buscarDocentes() {
		
		if (this.getBusquedaContenidoDocente().length() > 0) {
			switch (this.getBusquedaTipoDocente()) {
				case 1: {
					listaDocente = docenteFacadeLocal.docenteFindByIdentificacion(this.getBusquedaContenidoDocente());
					break;
				}
				case 2: {
					listaDocente = docenteFacadeLocal.docenteFindByNombre(this.getBusquedaContenidoDocente());
					break;
				}
			}
			this.setBusquedaContenidoDocente("");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "El criterio de busqueda no debe estar vacío.", ""));
		}
	}
	
	public void buscarInstituciones() {
		this.setNombreProgramaEbja(programaEbjaFacadeLocal.find(this.getIdProgramaEbja()).getNombre());
		
		switch (this.getBusquedaTipo()) {
			case 1: {
				listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdProgramaEbjaAmie(this.getIdProgramaEbja(), this.getBusquedaContenido());
				this.setBusquedaContenido("");
				break;
			}
			case 2: {
				listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdProgramaEbjaCircuito(this.getIdProgramaEbja(), this.getIdCircuito());
				break;
			}
		}
	}
	
	public void ejecutarCrearOEditarRegistro() {		
		if (this.getCrearOEditar() == 1) {
			crearRegistroDocenteCurso();
		}
		else {
			editarDocenteCurso();
		}
		cargarListaDocentesAsignados();
	}
	
	public void crearRegistroDocenteCurso() {		
		try {
			Long validacion = (long) 0;
			validacion = docenteCursoFacadeLocal.validarExisteDocenteCurso(selectedInstitucion.getId(), selectedDocente.getId());
			if (validacion == 0) {
				docenteCurso = new DocenteCurso();
				cargaDatosRegistro();
				docenteCursoFacadeLocal.create(docenteCurso);
				setearCrearDocente();
				       
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "El docente seleccionado se asignó exitosamente", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "No se asignó el docente seleccionado; el docente ya se encuentra asignado en este programa", ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al asignar el docente seleccionado.", ""));
		}		
	}
	
	public void editarDocenteCurso() {
		try {
			if (idCursoParalelo != null && idCursoParalelo.toString().isEmpty() == false)
				docenteCurso.setCursoParalelo(cursoParaleloFacadeLocal.find(idCursoParalelo));
			else
				docenteCurso.setCursoParalelo(null);
			docenteCursoFacadeLocal.edit(docenteCurso);
			setearCrearDocente();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Editó exitosamente", ""));

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Editar el registro", ""));
		}
	}
	
	public void cargarListaDocentesAsignados() {
		if (selectedInstitucion != null) {
			disabledCbAgregar = false;
			this.setNombreInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdInstitucion().getDescripcion());
			this.setAmieInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdInstitucion().getAmie());
			this.setGeocodigoInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdEstablecimiento().getCodigoCuadrante());
			listaDocenteCurso = docenteCursoFacadeLocal.docenteCursoFindByIdProgramaInstitucion(selectedInstitucion.getId());
			listaCursoParalelo = cursoParaleloFacadeLocal.findByIdProgramaInstitucion(selectedInstitucion.getId());
		}
	}
	
	public void habilitarBotonBuscarInstitucion() {
		if (selectedDocente != null) {
			disabledCbBuscarInstitucion = false;
			this.setNombreDocenteSeleccionado(selectedDocente.getPersona().getNombresApellidos());
			this.setIdentificacionDocenteSeleccionado(selectedDocente.getPersona().getNumeroIdentificacion());
		}
	}
	
	public void docenteSeleccionadoEditar(DocenteCurso docenteCursoAux) {
		this.setCrearOEditar(2);
		this.docenteCurso = docenteCursoAux;
		this.setIdCursoParalelo(cursoParalelo.getParalelo().getId());
	}
	
	public void docenteSeleccionadoInactivar(DocenteCurso docenteCursoAux) {
		this.docenteCurso = docenteCursoAux;
	}
	
	public void docenteSeleccionadoActivar(DocenteCurso docenteCursoAux) {
		this.docenteCurso = docenteCursoAux;
	}
	
	public void inactivarDocenteCurso() {
		docenteCurso.setEstado("0");
		docenteCursoFacadeLocal.edit(docenteCurso);
		cargarListaDocentesAsignados();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Inactivó exitosamente", ""));
	}
	
	public void activarDocenteCurso() {
		docenteCurso.setEstado("1");
		docenteCursoFacadeLocal.edit(docenteCurso);
		cargarListaDocentesAsignados();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Activó exitosamente", ""));
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargaDatosRegistro() {
		docenteCurso.setProgramaInstitucion(selectedInstitucion);
		if (idCursoParalelo != null && idCursoParalelo.toString().isEmpty() == false)
			docenteCurso.setCursoParalelo(cursoParaleloFacadeLocal.find(idCursoParalelo));
		else
			docenteCurso.setCursoParalelo(null);
			
		docenteCurso.setDocente(selectedDocente);
		docenteCurso.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		docenteCurso.setFechaCreacion(new Date());
		docenteCurso.setIpUsuario(sesionControlador.getIpAdressLocal());
		docenteCurso.setEstado("1");
	}
	
	private void cargarListas() {
		listaDistrito = new ArrayList<>();
		listaCircuito = new ArrayList<>();
		
		listaProgramaEbja = programaEbjaFacadeLocal.findAllActive();
		listaZona = zonaFacadeLocal.findByEstado(Constantes.ESTADO_REGISTRO_ACTIVO);
		
	}
	
	public void obtenerZona() {
		listaCircuito = new ArrayList<>();
		
		listaDistrito = distritoFacadeLocal.findByZona(this.getIdZona(), Constantes.ESTADO_REGISTRO_ACTIVO);		
	}
	
	public void obtenerDistrito() {
		listaCircuito = circuitoFacadeLocal.findByDistrito(this.getIdDistrito(), Constantes.ESTADO_REGISTRO_ACTIVO);
	}

	/*------------------------------------Getters and Setters---------------------------------------*/
	public List<ProgramaEbja> getListaProgramaEbja() {
		return listaProgramaEbja;
	}

	public void setListaProgramaEbja(List<ProgramaEbja> listaProgramaEbja) {
		this.listaProgramaEbja = listaProgramaEbja;
	}

	public List<Zona> getListaZona() {
		return listaZona;
	}

	public void setListaZona(List<Zona> listaZona) {
		this.listaZona = listaZona;
	}
	
	public List<Distrito> getListaDistrito() {
		return listaDistrito;
	}

	public void setListaDistrito(List<Distrito> listaDistrito) {
		this.listaDistrito = listaDistrito;
	}
	
	public List<Circuito> getListaCircuito() {
		return listaCircuito;
	}

	public void setListaCircuito(List<Circuito> listaCircuito) {
		this.listaCircuito = listaCircuito;
	}
		
	public List<CursoParalelo> getListaCursoParalelo() {
		return listaCursoParalelo;
	}

	public void setListaCursoParalelo(List<CursoParalelo> listaCursoParalelo) {
		this.listaCursoParalelo = listaCursoParalelo;
	}

	public List<ProgramaInstitucion> getListaProgramaInstitucion() {
		return listaProgramaInstitucion;
	}

	public void setListaProgramaInstitucion(List<ProgramaInstitucion> listaProgramaInstitucion) {
		this.listaProgramaInstitucion = listaProgramaInstitucion;
	}

	public Integer getIdProgramaEbja() {
		return idProgramaEbja;
	}

	public void setIdProgramaEbja(Integer idProgramaEbja) {
		this.idProgramaEbja = idProgramaEbja;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}
	
	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}
	
	public Integer getIdCircuito() {
		return idCircuito;
	}

	public void setIdCircuito(Integer idCircuito) {
		this.idCircuito = idCircuito;
	}
	
	public String getNombreProgramaEbja() {
		return nombrePrograma;
	}

	public void setNombreProgramaEbja(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	
	public String getNombreInstitucionSeleccionada() {
		return nombreInstitucionSeleccionada;
	}

	public void setNombreInstitucionSeleccionada(String nombreInstitucionSeleccionada) {
		this.nombreInstitucionSeleccionada = nombreInstitucionSeleccionada;
	}

	public String getAmieInstitucionSeleccionada() {
		return amieInstitucionSeleccionada;
	}

	public void setAmieInstitucionSeleccionada(String amieInstitucionSeleccionada) {
		this.amieInstitucionSeleccionada = amieInstitucionSeleccionada;
	}

	public String getGeocodigoInstitucionSeleccionada() {
		return geocodigoInstitucionSeleccionada;
	}

	public void setGeocodigoInstitucionSeleccionada(String geocodigoInstitucionSeleccionada) {
		this.geocodigoInstitucionSeleccionada = geocodigoInstitucionSeleccionada;
	}

	public int getBusquedaTipo() {
		return busquedaTipo;
	}
	
	public void setBusquedaTipo(int busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}
	
	public String getBusquedaContenidoDocente() {
		return busquedaContenidoDocente;
	}

	public void setBusquedaContenidoDocente(String busquedaContenidoDocente) {
		this.busquedaContenidoDocente = busquedaContenidoDocente;
	}

	public String getBusquedaContenido() {
		return busquedaContenido;
	}

	public void setBusquedaContenido(String busquedaContenido) {
		this.busquedaContenido = busquedaContenido;
	}

	public boolean isDisabledCbAgregar() {
		return disabledCbAgregar;
	}

	public void setDisabledCbAgregar(boolean disabledCbAgregar) {
		this.disabledCbAgregar = disabledCbAgregar;
	}

	public ProgramaInstitucion getSelectedInstitucion() {
		return selectedInstitucion;
	}

	public void setSelectedInstitucion(ProgramaInstitucion selectedInstitucion) {
		this.selectedInstitucion = selectedInstitucion;
	}

	public CursoParalelo getCursoParalelo() {
		return cursoParalelo;
	}

	public void setCursoParalelo(CursoParalelo cursoParalelo) {
		this.cursoParalelo = cursoParalelo;
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public Integer getNumBanca() {
		return numBanca;
	}

	public void setNumBanca(Integer numBanca) {
		this.numBanca = numBanca;
	}

	public int getCrearOEditar() {
		return crearOEditar;
	}

	public void setCrearOEditar(int crearOEditar) {
		this.crearOEditar = crearOEditar;
	}

	public Integer getIdCursoParalelo() {
		return idCursoParalelo;
	}

	public void setIdCursoParalelo(Integer idCursoParalelo) {
		this.idCursoParalelo = idCursoParalelo;
	}

	public int getBusquedaTipoDocente() {
		return busquedaTipoDocente;
	}

	public void setBusquedaTipoDocente(int busquedaTipoDocente) {
		this.busquedaTipoDocente = busquedaTipoDocente;
	}

	public List<Docente> getListaDocente() {
		return listaDocente;
	}

	public void setListaDocente(List<Docente> listaDocente) {
		this.listaDocente = listaDocente;
	}

	public Docente getSelectedDocente() {
		return selectedDocente;
	}

	public void setSelectedDocente(Docente selectedDocente) {
		this.selectedDocente = selectedDocente;
	}

	public List<DocenteCurso> getListaDocenteCurso() {
		return listaDocenteCurso;
	}

	public void setListaDocenteCurso(List<DocenteCurso> listaDocenteCurso) {
		this.listaDocenteCurso = listaDocenteCurso;
	}

	public DocenteCurso getDocenteCurso() {
		return docenteCurso;
	}

	public void setDocenteCurso(DocenteCurso docenteCurso) {
		this.docenteCurso = docenteCurso;
	}

	public boolean isDisabledCbBuscarInstitucion() {
		return disabledCbBuscarInstitucion;
	}

	public void setDisabledCbBuscarInstitucion(boolean disabledCbBuscarInstitucion) {
		this.disabledCbBuscarInstitucion = disabledCbBuscarInstitucion;
	}

	public String getNombreDocenteSeleccionado() {
		return nombreDocenteSeleccionado;
	}

	public void setNombreDocenteSeleccionado(String nombreDocenteSeleccionado) {
		this.nombreDocenteSeleccionado = nombreDocenteSeleccionado;
	}

	public String getIdentificacionDocenteSeleccionado() {
		return identificacionDocenteSeleccionado;
	}

	public void setIdentificacionDocenteSeleccionado(String identificacionDocenteSeleccionado) {
		this.identificacionDocenteSeleccionado = identificacionDocenteSeleccionado;
	}
}