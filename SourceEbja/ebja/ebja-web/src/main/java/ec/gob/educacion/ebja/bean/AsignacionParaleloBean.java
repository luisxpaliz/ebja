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
import ec.gob.educacion.ebja.facade.local.ParaleloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;

@ManagedBean
@ViewScoped
public class AsignacionParaleloBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;

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
	private ParaleloFacadeLocal paraleloFacadeLocal;
	
	private List<ProgramaEbja> listaProgramaEbja;
	private List<Zona> listaZona;
	private List<Distrito> listaDistrito;
	private List<Circuito> listaCircuito;
	private List<CursoParalelo> listaCursoParalelo;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<Paralelo> listaParalelo;
	
	private ProgramaInstitucion selectedInstitucion;
	private CursoParalelo cursoParalelo;

	private Integer idProgramaEbja;
	private Integer idZona;
	private Integer idDistrito;
	private Integer idCircuito;
	private Integer idParalelo;
	private Integer aforo;
	private Integer numBanca;
	private String nombrePrograma;
	private String nombreInstitucionSeleccionada;
	private String amieInstitucionSeleccionada;
	private String geocodigoInstitucionSeleccionada;
	
	private boolean disabledCbAgregar = true;
	
	private int busquedaTipo = 1;
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
	
	public void setearCrearParalelo() {
		this.setCrearOEditar(1);
		this.setIdParalelo(null);
		this.setAforo(null);
		this.setNumBanca(null);
	}
	
	public void buscarInstituciones() {
		
		listaCursoParalelo = null;
		
		switch (this.getBusquedaTipo()) {
			case 1: {
				listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdInstitucionAmie(this.getBusquedaContenido());
				this.setBusquedaContenido("");
				break;
			}
			case 2: {
				listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdInstitucionCircuito(this.getIdCircuito());
				break;
			}
		}
	}
	
	public void ejecutarCrearOEditarRegistro() {		
		if (this.getCrearOEditar() == 1) {
			crearRegistroCursoParalelo();
		}
		else {
			editarCursoParalelo();
		}
		cargarListaParalelo();
	}
	
	public void crearRegistroCursoParalelo() {		
		try {
			Long validacion = (long) 0;
			validacion = cursoParaleloFacadeLocal.validarExisteCursoParalelo(selectedInstitucion.getId(), idParalelo);
			if (numBanca <= aforo) {
				if (validacion == 0) {
					cursoParalelo = new CursoParalelo();
					cargaDatosRegistro();
					cursoParaleloFacadeLocal.create(cursoParalelo);
					actualizarTotalAforoTotalBancas(selectedInstitucion.getId());
					setearCrearParalelo();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "El paralelo se asignó exitosamente", ""));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "No se asignó el paralelo; el paralelo ya se encuentra asignado", ""));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de 'Bancas' no puede ser mayor al 'Aforo'", ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al asignar el paralelo", ""));
		}		
	}
	
	public void editarCursoParalelo() {
		try {
			if (numBanca <= aforo) {
				cursoParalelo.setAforo(aforo);
				cursoParalelo.setNumeroBanca(numBanca);
				cursoParaleloFacadeLocal.edit(cursoParalelo);
				actualizarTotalAforoTotalBancas(selectedInstitucion.getId());
				setearCrearParalelo();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Editó exitosamente", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de 'Bancas' no puede ser mayor al 'Aforo'", ""));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Editar el registro", ""));
		}
	}
	
	public void cargarListaParalelo() {
		if (selectedInstitucion != null) {
			disabledCbAgregar = false;
			this.setNombreInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdInstitucion().getDescripcion());
			this.setAmieInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdInstitucion().getAmie());
			this.setGeocodigoInstitucionSeleccionada(selectedInstitucion.getInstitucEstablec().getIdEstablecimiento().getCodigoCuadrante());
			listaCursoParalelo = cursoParaleloFacadeLocal.findByIdProgramaInstitucion(selectedInstitucion.getId());
			listaParalelo = paraleloFacadeLocal.findAllActive();
		}
	}
	
	public void paraleloSeleccionadoEditar(CursoParalelo cursoParaleloAux) {
		this.setCrearOEditar(2);
		this.cursoParalelo = cursoParaleloAux;
		this.setIdParalelo(cursoParalelo.getParalelo().getId());
		this.setAforo(cursoParalelo.getAforo());
		this.setNumBanca(cursoParalelo.getNumeroBanca());
	}
	
	public void paraleloSeleccionadoInactivar(CursoParalelo cursoParaleloAux) {
		this.cursoParalelo = cursoParaleloAux;
	}
	
	public void paraleloSeleccionadoActivar(CursoParalelo cursoParaleloAux) {
		this.cursoParalelo = cursoParaleloAux;
	}
	
	public void inactivarCursoParalelo() {
		int numeroMatriculado = 0;
		numeroMatriculado = this.cursoParalelo.getNumeroMatriculado();
		
		if (numeroMatriculado > 0) {			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "El registro no se Inactivó, porque existen estudiantes asignados al paralelo.", ""));			
		} else {
			this.cursoParalelo.setEstado("0");
			cursoParaleloFacadeLocal.edit(cursoParalelo);
			actualizarTotalAforoTotalBancas(selectedInstitucion.getId());
			cargarListaParalelo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Inactivó exitosamente", ""));
		}
	}
	
	public void activarCursoParalelo() {
		this.cursoParalelo.setEstado("1");
		cursoParaleloFacadeLocal.edit(cursoParalelo);
		actualizarTotalAforoTotalBancas(selectedInstitucion.getId());
		cargarListaParalelo();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Activó exitosamente", ""));
	}
	
	public void actualizarTotalAforoTotalBancas(Integer idProgramaInstitucion) {
		try {
			Integer totalAforo = 0, totalBanca = 0, totalMatriculado = 0;
			totalAforo = cursoParaleloFacadeLocal.cuentaTotalAforo(idProgramaInstitucion);
			totalBanca = cursoParaleloFacadeLocal.cuentaTotalBanca(idProgramaInstitucion);
			totalMatriculado = cursoParaleloFacadeLocal.cuentaTotalMatriculado(idProgramaInstitucion);
			selectedInstitucion.setTotalAforo(totalAforo);
			selectedInstitucion.setTotalBanca(totalBanca);
			selectedInstitucion.setCupoDisponible(totalBanca - totalMatriculado);
			programaInstitucionFacadeLocal.edit(selectedInstitucion);			
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Actualizar Totales de Aforo y Bancas.", ""));
		}
		
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargaDatosRegistro() {
		cursoParalelo.setProgramaInstitucion(selectedInstitucion);
		cursoParalelo.setParalelo(paraleloFacadeLocal.find(idParalelo));
		cursoParalelo.setAforo(aforo);
		cursoParalelo.setNumeroBanca(numBanca);
		cursoParalelo.setNumeroMatriculado(0);
		cursoParalelo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		cursoParalelo.setFechaCreacion(new Date());
		cursoParalelo.setIpUsuario(sesionControlador.getIpAdressLocal());
		cursoParalelo.setEstado("1");
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

	public List<Paralelo> getListaParalelo() {
		return listaParalelo;
	}

	public void setListaParalelo(List<Paralelo> listaParalelo) {
		this.listaParalelo = listaParalelo;
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

	public Integer getIdParalelo() {
		return idParalelo;
	}

	public void setIdParalelo(Integer idParalelo) {
		this.idParalelo = idParalelo;
	}
}