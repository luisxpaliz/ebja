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
import ec.gob.educacion.ebja.facade.local.CursoParaleloJTAFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.FaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InstitucEstablecFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ParaleloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ParaleloGradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionJTAFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.Fase;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ParaleloGrado;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;
import com.rits.cloning.Cloner;

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
	@EJB
	private ProgramaInstitucionJTAFacadeLocal programaInstitucionJTAFacadeLocal;
	@EJB
	private ProgramaEducativoFacadeLocal programasEducativos;
	@EJB
	private GrupoFaseFacadeLocal grupoFases;
	@EJB
	private ProgramaEbjaFacadeLocal programasEbja;
	@EJB 
	private ParaleloFacadeLocal paraleloFacadeLocal;
	@EJB
	private CursoParaleloJTAFacadeLocal cursoParaleloJTAFacade;
	@EJB
	private CursoParaleloFacadeLocal cursoParaleloFacade;
	@EJB
	private ParaleloGradoFacadeLocal paraleloGradoFacadeLocal;

	
	private List<ProgramaEbja> listaProgramaEbja;
	private List<Zona> listaZona;
	private List<Distrito> listaDistrito;
	private List<Circuito> listaCircuito;
	private List<InstitucEstablec> listaInstituciones;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private String tipoProyectoSeleccionadoBusqueda;
	private String tipoFaseSeleccionadaBusqueda;
	private List<GrupoFasePrograma> listaGrupoFaseProg;
	private List<Integer> ListaNumeroParalelos;
	private Integer aforo;
	private Integer numeroBancas;
	CursoParalelo clonerParalelo = null;
	private String nombreFase; 

	private Integer numeroMatriculados;
	private List<ProgramaEducativo> listaProgramaEducativo;
	private Integer numeroParalelo;
	private ProgramaInstitucion programaInstitucion;
	private CursoParalelo cursoParalelo;
	private Integer idProgramaEbja;
	private Integer idZona;
	private Integer idDistrito;
	private Integer idCircuito;
	private String nombrePrograma;	
	private boolean disabledCbAgregar = true;
	private int busquedaTipo = 1;
	private int buscarOagregar = 1;
	private String busquedaContenido = "";
	private String institucion;
	private ProgramaInstitucion programaEditar;
	private List<CursoParalelo> listaParalelos;
	private List<CursoParalelo> listParaleloGrado;
	private CursoParalelo paralelo;
	private ProgramaInstitucion programaInstitucionSeleccionado;
	private ProgramaInstitucion programaInstitucionObject;
	private List<Paralelo>ListaParalelosDetalle;

	@PostConstruct
	public void init() {
		cargarListas();
	}
	
	public void reseteaObjetos() {
		disabledCbAgregar = true;
		//cargarListaProgramaInstitucion();
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
	
	public void vaciarRegistroPopUp() {

		numeroParalelo=0;
		aforo = 0;
		numeroBancas = 0;
	}
	
	public void institucionSeleccionadoExpandir(ProgramaInstitucion object) {
		programaInstitucionSeleccionado =  object;
		listParaleloGrado = cursoParaleloFacade.findByIdProgramaInstitucion(programaInstitucionSeleccionado.getId());
	}
	
    public void eliminarRegistro(ProgramaInstitucion object){
		this.programaEditar =object;
	}
    
    public void activarRegistro(ProgramaInstitucion object){
    	this.programaEditar = object;
	}
    
    public void eliminarRegistro(Object[] object){
    	this.programaEditar = (ProgramaInstitucion)(object[0]);
	}
    
    public void anadirParalelo(CursoParalelo object,ProgramaInstitucion object2){
    	this.paralelo = object;
        this.programaInstitucionObject= object2;
	}
    
    public void editarParalelo(CursoParalelo object){
    	this.paralelo = object;
	}
    
    public void activarParalelo(CursoParalelo object){
    	this.paralelo = object;
	}
    
    public void borrarParalelo(CursoParalelo object){
    	this.paralelo = object;
	}
    
    public void eliminarParalelo(CursoParalelo object){
    	this.paralelo = object;
	}
    
    public void activarProgramaInstitucion() {
    	
    	try {
			if (programaEditar.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {

				programaEditar.setEstado("1");
				programaInstitucionFacadeLocal.edit(programaEditar);
				buscarInstituciones();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
    	
	}
    public void eliminarProgramaInstitucion() {

    	int numeroParalelos = 0;
		numeroParalelos = this.programaInstitucion.getCursoParalelos().size();
		if (numeroParalelos > 0) {			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "El registro no se eliminó, porque existen paralelos asociados a esta Institución Educativa.", ""));			
		} else {		
			try {
				    
					this.programaEditar.setEstado("3");
					programaInstitucionFacadeLocal.edit(programaInstitucion);	
					buscarInstituciones();
					FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
							new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
				
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
			}	
		}		
    	
	}
    
    public void inactivarParaleloGrado() {
    	try {
    	if (paralelo.getEstado().contentEquals("0")) {
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
    					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
    		} else if (buscarDependenciaPorCodigoParalelo(paralelo)) {
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion", new FacesMessage(
    					FacesMessage.SEVERITY_FATAL,
    					"No se puede inactivar el registro, porque existen datos relacionados en Ofertas Educativas", ""));
    		} else {
    			paralelo.setEstado("0");
    			cursoParaleloFacade.edit(paralelo);
    			institucionSeleccionadoExpandir(programaInstitucionSeleccionado);
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
    					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
    		}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
    
    public void activarParaleloGrado() {
    	try {
    	if (paralelo.getEstado().contentEquals("1")) {
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
    					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
    		} else {
    			paralelo.setEstado("1");
    			cursoParaleloFacade.edit(paralelo);
    			institucionSeleccionadoExpandir(programaInstitucionSeleccionado);
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
    					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
    		}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
    
    public void eliminarParaleloGrado() {
    	try {
     if (buscarDependenciaPorCodigoParalelo(paralelo)) {
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion", new FacesMessage(
    					FacesMessage.SEVERITY_FATAL,
    					"No se puede Eliminar el registro, porque existen datos relacionados en Ofertas Educativas", ""));
    		} else {
    			paralelo.setEstado("3");
    			cursoParaleloFacade.edit(paralelo);
    			institucionSeleccionadoExpandir(programaInstitucionSeleccionado);
    			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
    					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_BORRAR_EXITOSAMENTE, ""));
    		}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
    
    
    
	public boolean buscarDependenciaPorCodigoParalelo(CursoParalelo tmpParalelo) {
		
		boolean tmpcoincidenciaParalelo = false;

			if(paraleloGradoFacadeLocal.obtenerParaleloGradoPorParalelo(tmpParalelo.getId()).size()!=0) {
				 tmpcoincidenciaParalelo = true;
			}else {
				 tmpcoincidenciaParalelo = false;
			}

		return tmpcoincidenciaParalelo;
	}
	
	public void anadirParaleloAInstitucion() {
		try {
		cursoParalelo = new CursoParalelo();
		cursoParalelo.setAforo(aforo);
		cursoParalelo.setNumeroBanca(numeroBancas);
		cursoParalelo.setNumeroMatriculado(0);// aforo = total - matriculados
		cursoParalelo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		cursoParalelo.setFechaCreacion(new Date());
		cursoParalelo.setIpUsuario(sesionControlador.getIpAdressLocal());
		cursoParalelo.setEstado("1");
		List<CursoParalelo> listaCursoParalelo = new ArrayList<CursoParalelo>();
		listaCursoParalelo = clonarParalelos(programaInstitucionObject,listaCursoParalelo,numeroParalelo,cursoParalelo);
		cursoParaleloJTAFacade.mergeList(listaCursoParalelo);
		institucionSeleccionadoExpandir(programaInstitucionObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarParaleloAInstitucion() {
		try {
		
		paralelo.setAforo(aforo);
		paralelo.setNumeroBanca(numeroBancas);
		paralelo.setNumeroMatriculado(0);// aforo = total - matriculados
		paralelo.setFechaCreacion(new Date());
		paralelo.setIpUsuario(sesionControlador.getIpAdressLocal());
		paralelo.setParalelo(paraleloFacadeLocal.find(numeroParalelo));
		cursoParaleloFacade.edit(paralelo);
		
		institucionSeleccionadoExpandir(programaInstitucionObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public void crearParalelos() {
		//editar o crear
		
			// Grabar Institucion
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
									programaInstitucion = new ProgramaInstitucion();
									programaInstitucion.setInstitucEstablec(institucEstablec);
									programaInstitucion.setTotalAforo(aforo);
									programaInstitucion.setTotalBanca(numeroBancas);
									programaInstitucion.setCupoDisponible(0);// total - los matriculados
									programaInstitucion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
									programaInstitucion.setFechaCreacion(new Date());
									programaInstitucion.setIpUsuario(sesionControlador.getIpAdressLocal());
									programaInstitucion.setEstado("1");
									programaInstitucion.setIdSostenimiento(0);
									programaInstitucion.setIdGrupoFasePrograma(Integer.parseInt(tipoFaseSeleccionadaBusqueda));
									
									cursoParalelo = new CursoParalelo();
									cursoParalelo.setAforo(aforo);
									cursoParalelo.setNumeroBanca(numeroBancas);
									cursoParalelo.setNumeroMatriculado(0);// aforo = total - matriculados
									cursoParalelo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
									cursoParalelo.setFechaCreacion(new Date());
									cursoParalelo.setIpUsuario(sesionControlador.getIpAdressLocal());
									cursoParalelo.setEstado("1");
									
									List<CursoParalelo> listaCursoParalelo = new ArrayList<CursoParalelo>();
									listaCursoParalelo = clonarParalelos(programaInstitucion,listaCursoParalelo,numeroParalelo,cursoParalelo);
									
									//Método que crea una lista de paralelos y le asigna a una institución.
									//programaInstitucionJTAFacadeLocal.createParalelosInstituciones(programaInstitucion,listaCursoParalelo);
									//programaInstitucionJTAFacadeLocal.create(programaInstitucion);
									cursoParaleloJTAFacade.createList(listaCursoParalelo);
									buscarInstitucionesInscritas();
								
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
					}

				}
			}	
	}
	
	private List<CursoParalelo> clonarParalelos (ProgramaInstitucion programaInstitucionClone, List<CursoParalelo> listaCursoParalelo, int numeroParalelos,CursoParalelo cursoClonar) {
		
		Cloner cloner = new Cloner();
		int sizeParaleloscatalogo;
		int sizeParalelosDetalle;
		cursoParalelo.setParalelo(null);
		CursoParalelo clonerParalelo = null;
		
		sizeParaleloscatalogo = ListaParalelosDetalle.size();
		List<CursoParalelo>ListaCursoParaleloDetalle =  cursoParaleloFacade.findByIdProgramaInstitucion(programaInstitucionClone.getId());
		
		if(ListaCursoParaleloDetalle==null) {
			//No existen registros se insertan nuevos
			for(int a=0;a<numeroParalelos;a++) {
				clonerParalelo = cloner.deepClone(cursoClonar);
				clonerParalelo.setParalelo(ListaParalelosDetalle.get(a));
				clonerParalelo.setProgramaInstitucion(programaInstitucionClone);
				listaCursoParalelo.add(clonerParalelo);
			}
		}else {
			//añade los paralelos restantes disponibles en el catalgo paralelos
			sizeParalelosDetalle=ListaCursoParaleloDetalle.size();
			for(int a=sizeParalelosDetalle;(a<sizeParalelosDetalle+numeroParalelos&&a<=sizeParaleloscatalogo);a++) {
				clonerParalelo = cloner.deepClone(cursoClonar);
				clonerParalelo.setParalelo(ListaParalelosDetalle.get(a));
				clonerParalelo.setProgramaInstitucion(programaInstitucionClone);
				listaCursoParalelo.add(clonerParalelo);
			}
		}
		
		
		
		return listaCursoParalelo;
	}
	
	public void resetearTablas() {
		listaProgramaInstitucion = new ArrayList<>();
		listaInstituciones = new ArrayList<>();
	}
	
	public void buscarInstituciones() {

		if(buscarOagregar==1) {
			
			buscarInstitucionesInscritas();
			
		}else if(buscarOagregar==2) {
			
			listaProgramaInstitucion = new ArrayList<>();
			
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
	}
	
	public void buscarInstitucionesInscritas() {
		cargarListaProgramaInstitucion();
		
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
								programaInstitucion.setIdGrupoFasePrograma(Integer.parseInt(tipoFaseSeleccionadaBusqueda));
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
			institucion = institucEstablec.getIdInstitucion().getAmie()+" - "+institucEstablec.getIdInstitucion().getDescripcion();
		}
	}
	
	public void inactivarRegistro(ProgramaInstitucion programaInstitucionAux) {
		this.programaInstitucion = programaInstitucionAux;
		System.out.println();
	}
	
	public void inactivarProgramaInstitucion() {
		int numeroParalelos = 0;
		List<CursoParalelo> listaCursosParalelos = this.programaInstitucion.getCursoParalelos();
		numeroParalelos = listaCursosParalelos.size();
		if (numeroParalelos > 0) {			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro no se Inactivó, porque existen paralelos asociados a esta Institución Educativa.", ""));			
		} else {		
			
			try {
				if (programaEditar.getEstado().contentEquals("0")) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
				} else {
					this.programaInstitucion.setEstado("0");
					programaInstitucionFacadeLocal.edit(programaInstitucion);	
					buscarInstituciones();
					FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
							new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageSeleccionInstitucion",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
			}	
		}	
	}
	
	public void cargarListaProgramaInstitucion() {
		listaProgramaInstitucion = programaInstitucionFacadeLocal.findByFase(Integer.parseInt(tipoFaseSeleccionadaBusqueda));
		//this.setNombreProgramaEbja(programaEbjaFacadeLocal.find(this.getIdProgramaEbja()).getNombre());
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargarListas() {
		ListaNumeroParalelos = new ArrayList<>();
		listaDistrito = new ArrayList<>();
		listaCircuito = new ArrayList<>();
		ListaParalelosDetalle = paraleloFacadeLocal.findAllActive();
		for(int i = 1; i<=25 ;i++) {
			ListaNumeroParalelos.add(i);
		}
		numeroMatriculados = new Integer(0);
		
		consultarFases();
		listaZona = zonaFacadeLocal.findByEstado(Constantes.ESTADO_REGISTRO_ACTIVO);
		
	}
	
	public void consultarFases() {
	
		
		listaProgramaEducativo = programasEducativos.buscarTodosProgramaEducativoActivos();
	}
	
	public void obtenerNombreFase() {
		
		for(GrupoFasePrograma grupoFasePrograma :listaGrupoFaseProg) {
			if(grupoFasePrograma.getIdGrupoFaseNotas()==Long.parseLong(tipoFaseSeleccionadaBusqueda)) {
				this.nombreFase = grupoFasePrograma.getNombre();
				break;
			}
		}
		
	}
	
	
	public void obtenerFasesdeProyectoBusqueda() {

		if (tipoProyectoSeleccionadoBusqueda.isEmpty()) {
			listaGrupoFaseProg = new ArrayList<GrupoFasePrograma>();
		} else {
			listaGrupoFaseProg = grupoFases.buscarGrupoFaseProgActInternosXProyecto(tipoProyectoSeleccionadoBusqueda);
		}
	}
	
	public void obtenerOfertaBusqueda() {
		listaProgramaEbja = programasEbja.obtenerProgramaEbjaGrupoFaseExtraordinariaNemonico(tipoFaseSeleccionadaBusqueda);
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

	public List<ProgramaEducativo> getListaProgramaEducativo() {
		return listaProgramaEducativo;
	}

	public void setListaProgramaEducativo(List<ProgramaEducativo> listaProgramaEducativo) {
		this.listaProgramaEducativo = listaProgramaEducativo;
	}

	public String getTipoProyectoSeleccionadoBusqueda() {
		return tipoProyectoSeleccionadoBusqueda;
	}

	public void setTipoProyectoSeleccionadoBusqueda(String tipoProyectoSeleccionadoBusqueda) {
		this.tipoProyectoSeleccionadoBusqueda = tipoProyectoSeleccionadoBusqueda;
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

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}


	public List<Integer> getListaNumeroParalelos() {
		return ListaNumeroParalelos;
	}

	public void setListaNumeroParalelos(List<Integer> listaNumeroParalelos) {
		ListaNumeroParalelos = listaNumeroParalelos;
	}

	public Integer getNumeroParalelo() {
		return numeroParalelo;
	}

	public void setNumeroParalelo(Integer numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}

	public Integer getNumeroMatriculados() {
		return numeroMatriculados;
	}

	public void setNumeroMatriculados(Integer numeroMatriculados) {
		this.numeroMatriculados = numeroMatriculados;
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public Integer getNumeroBancas() {
		return numeroBancas;
	}

	public void setNumeroBancas(Integer numeroBancas) {
		this.numeroBancas = numeroBancas;
	}

	public String getNombreFase() {
		return nombreFase;
	}

	public void setNombreFase(String nombreFase) {
		this.nombreFase = nombreFase;
	}

	public int getBuscarOagregar() {
		return buscarOagregar;
	}

	public void setBuscarOagregar(int buscarOagregar) {
		this.buscarOagregar = buscarOagregar;
	}

	public List<CursoParalelo> getListParaleloGrado() {
		return listParaleloGrado;
	}

	public void setListParaleloGrado(List<CursoParalelo> listParaleloGrado) {
		this.listParaleloGrado = listParaleloGrado;
	}

	public CursoParalelo getParalelo() {
		return paralelo;
	}

	public void setParalelo(CursoParalelo paralelo) {
		this.paralelo = paralelo;
	}

	public List<Paralelo> getListaParalelosDetalle() {
		return ListaParalelosDetalle;
	}

	public void setListaParalelosDetalle(List<Paralelo> listaParalelosDetalle) {
		ListaParalelosDetalle = listaParalelosDetalle;
	}
	
	
	
}