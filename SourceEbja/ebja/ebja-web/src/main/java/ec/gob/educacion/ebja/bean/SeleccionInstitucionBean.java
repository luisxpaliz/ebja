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
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InstitucEstablecFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;

@ManagedBean
@ViewScoped
public class SeleccionInstitucionBean extends BaseControlador implements Serializable {
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
	private InstitucEstablecFacadeLocal institucEstablecFacadeLocal;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucionFacadeLocal;
	
	private List<ProgramaEbja> listaProgramaEbja;
	private List<Zona> listaZona;
	private List<Distrito> listaDistrito;
	private List<Circuito> listaCircuito;
	private List<InstitucEstablec> listaInstituciones;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	
	private ProgramaInstitucion programaInstitucion;

	private Integer idProgramaEbja;
	private Integer idZona;
	private Integer idDistrito;
	private Integer idCircuito;
	private String nombrePrograma;	
	
	private boolean disabledCbAgregar = true;
	
	private int busquedaTipo = 1;
	private String busquedaContenido = "";


	@PostConstruct
	public void init() {
		cargarListas();
	}
	
	public void reseteaObjetos() {
		disabledCbAgregar = true;
		cargarListaProgramaInstitucion();
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
	
	public void buscarInstituciones() {
		cargarListaProgramaInstitucion();

		if (this.getBusquedaContenido().length() > 0) {		
			switch (this.getBusquedaTipo()) {
			case 1: {
				if(!(this.getBusquedaContenido() == null)) {
					listaInstituciones = institucEstablecFacadeLocal.institucionFindByAmie(this.getBusquedaContenido());
				}
				this.setBusquedaContenido("");
				break;
			}
			case 2: {
				listaInstituciones = institucEstablecFacadeLocal.institucionFindByCircuito(this.getIdCircuito());
				break;
			}
			}
		}else {				
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "El criterio de busqueda no debe estar vacío.", ""));
		}
	}
	
	public void procesarAgregarInstitucion() {
		int registrosAgregados = 0;
		int registrosExistentes = 0;
		int registrosErrores = 0;
		int contador = 0;
		Long validacion = (long) 0;
		if (listaInstituciones != null) {
			if (listaInstituciones.size() > 0) {
				for (InstitucEstablec institucEstablec : listaInstituciones) {
					if (institucEstablec.getEstado().equals("true")) {
						try {
							validacion = programaInstitucionFacadeLocal.validarExisteProgramaInstitucion(idProgramaEbja, institucEstablec.getId());
							if (validacion == 0) {
								programaInstitucion = new ProgramaInstitucion();
								
								//programaInstitucion.setProgramaEbja(programaEbjaFacadeLocal.find(idProgramaEbja));
								programaInstitucion.setInstitucEstablec(institucEstablec);
								programaInstitucion.setTotalAforo(0);
								programaInstitucion.setTotalBanca(0);
								programaInstitucion.setCupoDisponible(0);
								programaInstitucion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
								programaInstitucion.setFechaCreacion(new Date());
								programaInstitucion.setIpUsuario(sesionControlador.getIpAdressLocal());
								programaInstitucion.setEstado("1");
								
								programaInstitucionFacadeLocal.create(programaInstitucion);
								
								registrosAgregados++;
							} else {
								registrosExistentes++;
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							registrosErrores++;
						} finally {
							contador++;
						}
					}
				}
				buscarInstituciones();
				if (contador > 0) {
					if (registrosAgregados == contador) {						
						FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Las " + registrosAgregados + " instituciones seleccionadas se agregarón exitosamente", ""));
					} else if (registrosErrores == contador){
						FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
								new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al agregar las instituciones seleccionadas.", ""));
					} else if (registrosErrores == 0){
						FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
								new FacesMessage(FacesMessage.SEVERITY_WARN, "De las " + contador + " instituciones seleccionadas: " + registrosAgregados + " se agregarón exitosamente; " + registrosExistentes + " no se agregarón, porque ya se encontraban agregados.", ""));
					} else {
						FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
								new FacesMessage(FacesMessage.SEVERITY_WARN, "De las " + contador + " instituciones seleccionadas: " + registrosAgregados + " se agregarón exitosamente; " + registrosExistentes + " no se agregarón, porque ya se encontraban agregados y "+ registrosErrores + " no se agregarón por error en inserción a la base. \n Por favor verifique y vuelava a intentar.", ""));
					}
				}				
			}
		}
		
	}
	
	public void procesarRegistroInstitucion(InstitucEstablec institucEstablec) {		
		if (institucEstablec.getEstado().equals("true")) {
			disabledCbAgregar = false;
		}
	}
	
	public void programaInstitucionInactivar(ProgramaInstitucion programaInstitucionAux) {
		this.programaInstitucion = programaInstitucionAux;
	}
	
	public void inactivarProgramaInstitucion() {
		int totalAforo = 0;
		totalAforo = this.programaInstitucion.getTotalAforo();
		
		if (totalAforo > 0) {			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "El registro no se Inactivó, porque existen paralelos asociados a esta Institución Educativa.", ""));			
		} else {		
			this.programaInstitucion.setEstado("0");
			programaInstitucionFacadeLocal.edit(programaInstitucion);
			cargarListaProgramaInstitucion();			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se Inactivó exitosamente", ""));
		}
	}
	
	public void cargarListaProgramaInstitucion() {
		listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdProgramaEbja(this.getIdProgramaEbja());
		this.setNombreProgramaEbja(programaEbjaFacadeLocal.find(this.getIdProgramaEbja()).getNombre());
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

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
	
	public List<InstitucEstablec> getListaInstituciones() {
		return listaInstituciones;
	}

	public void setListaInstituciones(List<InstitucEstablec> listaInstituciones) {
		this.listaInstituciones = listaInstituciones;
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
}