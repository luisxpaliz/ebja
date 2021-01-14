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

import org.primefaces.context.RequestContext;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.CantonFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CeduladoMeducacionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CircuitoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DetalleTrasladoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ParroquiaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProvinciaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.SuministroLuzFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.DetalleTraslado;
import ec.gob.educacion.ebja.modelo.Matricula;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;
import ec.gob.educacion.ebja.modelo.asignaciones.SuministroLuz;
import ec.gob.educacion.ebja.modelo.zeus.Canton;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.Parroquia;
import ec.gob.educacion.ebja.modelo.zeus.Provincia;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servicio.CatalogoServicio;

@ManagedBean
@ViewScoped
public class TrasladoBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private CatalogoServicio catalogoServicio;
	@EJB
	private CatalogoEbjaFacadeLocal catalogoEbjaFacadeLocal;
	@EJB
	private CeduladoMeducacionFacadeLocal ceduladoMeducacionFacadeLocal;
	@EJB
	private MatriculaFacadeLocal matriculaFacadeLocal;
	@EJB
	private SuministroLuzFacadeLocal suministroLuzFacadeLocal;
	@EJB
	private ProvinciaFacadeLocal provinciaFacadeLocal;
	@EJB
	private CantonFacadeLocal cantonFacadeLocal;
	@EJB
	private ParroquiaFacadeLocal parroquiaFacadeLocal;
	@EJB
	private ZonaFacadeLocal zonaFacadeLocal;
	@EJB
	private DistritoFacadeLocal distritoFacadeLocal;
	@EJB
	private CircuitoFacadeLocal circuitoFacadeLocal;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucionFacadeLocal;
	@EJB
	private DetalleTrasladoFacadeLocal detalleTrasladoFacadeLocal;
	@EJB
	private ReglaNegocioFacadeLocal reglaNegocioFacadeLocal;

	private List<Catalogo> listaTipoDocumento;
	private List<Provincia> listaProvincia = new ArrayList<>();
	private List<Canton> listaCanton = new ArrayList<>();
	private List<Parroquia> listaParroquia = new ArrayList<>();
	private List<Zona> listaZona = new ArrayList<>();
	private List<Distrito> listaDistrito = new ArrayList<>();
	private List<Circuito> listaCircuito = new ArrayList<>();
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<ProgramaInstitucion> listaProgramaInstitucionFilter;
	
	private SuministroLuz suministroLuz;
	private Matricula matricula;
	private Provincia provincia = new Provincia();
	private Canton canton = new Canton();
	private Parroquia parroquia = new Parroquia();
	private Zona zona = new Zona();;
	private Distrito distrito = new Distrito();
	private Circuito circuito = new Circuito();
	private DetalleTraslado detalleTraslado = new DetalleTraslado();
	private ProgramaInstitucion programaInstitucion = new ProgramaInstitucion();
	private ReglaNegocio reglaNegocio;
	
	private Integer tipoDocumento;
	private int indice;
	
	private Date fechaActual = new Date();

	private String numeroIdentificacion = "";
	private String apellidosNombres = "";
	private String cueBuscar;
	private String tieneCUE;
	
	private boolean readonlyNumeroIdentificacion;
	private boolean readonlyApellidosNombres;
	private boolean habPanelA;
	private boolean habPanelB;
	private boolean habPanelC;
	private boolean habPanelD;
	private boolean visiblePanelCUE;
	private boolean visibleUbicacionCUE;
	
	private boolean disabledCbBuscarEstudiante;
	private boolean disabledSbcProcesar;
	private boolean protejerObjetos;
	private boolean protejerObjetosDA;
	private boolean estadoProceso;

	@PostConstruct
	public void init() {
		cargarCatalogos();
		
		//Inicializar objetos.
		inicializarObjetos();
	}
	
	public void inicializarObjetos() {
		habPanelA = false;
		habPanelB = false;
		habPanelC = false;
		
		disabledCbBuscarEstudiante = false;
		readonlyNumeroIdentificacion = false;
		readonlyApellidosNombres = false;

		protejerObjetosDA = true;
		
		numeroIdentificacion = "";
		apellidosNombres = "";
		tieneCUE = "";
		visibleUbicacionCUE = false;

		provincia = new Provincia();
		canton = new Canton();
		parroquia = new Parroquia();
		
		zona = new Zona();
		distrito = new Distrito();
		circuito = new Circuito();
		
		listaProgramaInstitucion = null;
		disabledSbcProcesar = false;
		protejerObjetos = false;
	}
	
	public void buscarEstudiante() {
		habPanelA = false;
		if (numeroIdentificacion.equals("") && apellidosNombres.equals("")) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.INGRESE_DATOS, ""));
			return;
		}

		matricula = new Matricula();
		matricula = matriculaFacadeLocal.obtenerEstudianteParaTraslado(numeroIdentificacion, apellidosNombres);
		if (matricula == null) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.ESTUDIANTE_NO_MATRICULADO, ""));
			return;
		}

		habPanelA = true;
		
		if (!validarReglaNegocio()) {
			return;
		}
		
		habPanelB = true;
		disabledCbBuscarEstudiante = true;
		readonlyNumeroIdentificacion = true;
		readonlyApellidosNombres = true;
	}
	
	public boolean validarReglaNegocio() {
		reglaNegocio = new ReglaNegocio();
		
		//Obtener regla_negocio, relacionado con la oferta más la fase.
		reglaNegocio = reglaNegocioFacadeLocal.buscarPorProgramaEbjaFase(matricula.getEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getId(), Constantes.NEMONICO_TRASLADO);
		
		if (reglaNegocio == null || reglaNegocio.getFechaFin() == null || reglaNegocio.getFechaInicio() == null) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.NO_EXISTE_REGLA_NEGOCIO, ""));
			return false;
		}
		
		//Validar que fecha actual se encuentre en el rango de fecha inicio y fin de la FASE.
		if (fechaActual.after(reglaNegocio.getFechaFin()) || fechaActual.before(reglaNegocio.getFechaInicio())) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.FECHA_FASE_FUERA_RANGO, ""));
			return false;
		}
		
		return true;
	}

	//Mostrar Panel de acuerdo a lo solicitado en el OneMenu. (jbrito-20190311)
	public void mostrarPanelCUE() {
		cueBuscar = "";
		
		provincia = new Provincia();
		canton = new Canton();
		parroquia = new Parroquia();
		
		zona = new Zona();
		distrito = new Distrito();
		circuito = new Circuito();
		
		if (tieneCUE.equals("1")) {
			visibleUbicacionCUE = true;
			protejerObjetosDA = true;
		} else {
			visibleUbicacionCUE = false;
			protejerObjetosDA = false;
		}
	}

	public void obtenerProvincia() {
		provincia = provinciaFacadeLocal.find(provincia.getId());
		listaCanton = cantonFacadeLocal.listaCantonPorProvincia(provincia.getCodigoProvincia());
		
		canton.setId(null);
		parroquia.setId(null);

		zona.setId(null);
		distrito.setId(null);
		circuito.setId(null);
	}

	public void obtenerCanton() {
		canton = cantonFacadeLocal.find(canton.getId());
		listaParroquia = parroquiaFacadeLocal.listaParroquiaPorCanton(canton.getCodigoCanton());
		
		parroquia.setId(null);

		zona.setId(null);
		distrito.setId(null);
		circuito.setId(null);
	}

	public void obtenerParroquia() {
		parroquia = parroquiaFacadeLocal.find(parroquia.getId());

		zona.setId(null);
		distrito.setId(null);
		circuito.setId(null);
		
		//Obtener lista específica del circuito. (20180914)
		List<Circuito> listaCircuitoAux = new ArrayList<>();
		listaCircuitoAux = circuitoFacadeLocal.buscarPorProvCantParr(provincia.getId(), canton.getId(), parroquia.getId());
		if (!listaCircuitoAux.isEmpty()) {
			circuito = listaCircuitoAux.get(0);
			
			//Obtener el distrito. (20180914)
			distrito = distritoFacadeLocal.buscarPorProvCantParrCirc(provincia.getId(), canton.getId(), parroquia.getId(), circuito.getId());
			if (distrito != null) {
				zona = zonaFacadeLocal.find(distrito.getIdZona().getId());
			}

			institucionesProgramaEbjaCircuitoParroquia("parroquia");		
		}
	}

	//Obtener datos del lugar donde desea trasladarse el Docente. (jbrito-20151222)
	public void buscarUbicacionCUE(){
		suministroLuz = suministroLuzFacadeLocal.obtenerSuministroXCUE(cueBuscar);
		if (suministroLuz != null) {
			//Poblar listas con todos los registros.
			listaCanton = cantonFacadeLocal.findAll();
			listaParroquia = parroquiaFacadeLocal.findAll();
			
			provincia = provinciaFacadeLocal.buscarPorCodigoProvincia(suministroLuz.getDpaProvin());
			canton = cantonFacadeLocal.buscarPorCodigoCanton(suministroLuz.getDpaCanton());
			parroquia = parroquiaFacadeLocal.buscarPorCodigoParroquia(suministroLuz.getDpaParroq());
			
			zona = zonaFacadeLocal.buscarPorCodigoSenplades(suministroLuz.getZona());
			distrito = distritoFacadeLocal.buscarPorCodigoDistrito(suministroLuz.getCodDistri());
			circuito = circuitoFacadeLocal.buscarPorCodigoCircuito(suministroLuz.getCodCircui());
			
			institucionesProgramaEbjaCircuitoParroquia("circuito");		
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.CUE_NO_EXISTE, ""));
		}
	}
	
	public void verificarTraslado(ProgramaInstitucion programaInstitucionAux, int indiceAux) {
		indice = indiceAux;
		programaInstitucion = programaInstitucionAux;
		if (programaInstitucion.getCupoDisponible() <= 0) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.CUPO_NO_EXISTE, ""));
			return;
		}
		
		//Confirmar si se continua con el proceso o no.
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popConfirmarProceso').show();");
	}
	
	public void procesarContinuar() {
		//Crear el registro en detalle_traslado.
		crearDetalleTraslado();
		
		//Actualizar datos en matricula con los nuevos datos.
		if (estadoProceso) {
			actualizarMatricula();
			if (estadoProceso) {
				//Deshabilitar la tabla de instituciones.
				disabledSbcProcesar = true;
				
				//Actualizar el cupo disponible en programa_institución.
				programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() - 1);
				programaInstitucionFacadeLocal.edit(programaInstitucion);
			}
		}
		
		if (!estadoProceso) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.PROBLEMAS_BASE_DATOS, ""));
		}

	}
	
	public void actualizarMatricula() {
		estadoProceso = false;
		try {
			//Tratar datos de matricula con los nuevos datos.
			matricula.setProgramaInstitucion(programaInstitucion);
			matricula.setCursoParalelo(null);
			matricula.setCatalogoTipoProceso(catalogoEbjaFacadeLocal.obtenerCatalogoPorTipoCatalogo("TIPPRO", "T"));

			//Registrar detalle_traslado.
			matriculaFacadeLocal.edit(matricula);

			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO + ", actualizar matricula", ""));
			estadoProceso = true;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.PROCESO_NO_CORRECTO + ", actualizar matricula", ""));
		}
	}
	
	public void crearDetalleTraslado() {
		estadoProceso = false;
		try {
			detalleTraslado = new DetalleTraslado();
			//Tratar datos de detalle_traslado desde la matricula actual.
			detalleTraslado.setMatricula(matricula);
			detalleTraslado.setCursoParalelo(matricula.getCursoParalelo());
			detalleTraslado.setProgramaInstitucion(matricula.getProgramaInstitucion());
			detalleTraslado.setCatalogoTipoProceso(matricula.getCatalogoTipoProceso());
			detalleTraslado.setSuministroLuz(cueBuscar);
			detalleTraslado.setDpaParroquia(parroquia.getCodigoParroquia());

			//Datos auditables
			datosAuditables();

			//Registrar detalle_traslado.
			detalleTrasladoFacadeLocal.create(detalleTraslado);

			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO + ", crear detalle_traslado", ""));
			estadoProceso = true;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.PROCESO_NO_CORRECTO + ", crear detalle_traslado", ""));
		}
	}

	public void datosAuditables() {
		detalleTraslado.setEstado("1");
		detalleTraslado.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		detalleTraslado.setFechaCreacion(new Date());
		detalleTraslado.setIpUsuario(sesionControlador.getIpAdressLocal());
	}
	
	public void procesarCancelar() {
		//Actualizar el Check.
		//programaInstitucion.setEstadoProceso(false);
		listaProgramaInstitucion.set(indice, programaInstitucion);
	}
	
	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	public void cargarCatalogos() {
		// Obtener lista tipo documento.
		listaTipoDocumento = catalogoServicio.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_TIPO_DOCUMENTO);

		// Obtener lista provincia
		listaProvincia = provinciaFacadeLocal.findAll();

		// Obtener lista distrito.
		listaDistrito = distritoFacadeLocal.findAll();

		// Obtener lista zona.
		listaZona = zonaFacadeLocal.findAll();

		// Obtener lista cicuito.
		listaCircuito = circuitoFacadeLocal.findAll();
	}

	public void institucionesProgramaEbjaCircuitoParroquia(String tipoBusqueda) {
		if (tipoBusqueda.equals("circuito")) {
			//Obtener instituciones del programaEbja y circuito.
			listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdProgramaEbjaCircuito(matricula.getEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getId(), circuito.getId());
		} else {
			//Obtener instituciones del programaEbja y parroquia.
			listaProgramaInstitucion = programaInstitucionFacadeLocal.findByIdProgramaEbjaParroquia(matricula.getEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getId(), parroquia.getId());
		}

		if (!listaProgramaInstitucion.isEmpty()) {
			habPanelC = true;
			protejerObjetos = true;
			protejerObjetosDA = true;
		}
	}
	/*------------------------------------Getters and Setters---------------------------------------*/

	public boolean isReadonlyNumeroIdentificacion() {
		return readonlyNumeroIdentificacion;
	}

	public void setReadonlyNumeroIdentificacion(boolean readonlyNumeroIdentificacion) {
		this.readonlyNumeroIdentificacion = readonlyNumeroIdentificacion;
	}

	public List<Catalogo> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<Catalogo> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public boolean isReadonlyApellidosNombres() {
		return readonlyApellidosNombres;
	}

	public void setReadonlyApellidosNombres(boolean readonlyApellidosNombres) {
		this.readonlyApellidosNombres = readonlyApellidosNombres;
	}

	public boolean isHabPanelA() {
		return habPanelA;
	}

	public void setHabPanelA(boolean habPanelA) {
		this.habPanelA = habPanelA;
	}

	public boolean isHabPanelB() {
		return habPanelB;
	}

	public void setHabPanelB(boolean habPanelB) {
		this.habPanelB = habPanelB;
	}

	public boolean isHabPanelC() {
		return habPanelC;
	}

	public void setHabPanelC(boolean habPanelC) {
		this.habPanelC = habPanelC;
	}

	public boolean isHabPanelD() {
		return habPanelD;
	}

	public void setHabPanelD(boolean habPanelD) {
		this.habPanelD = habPanelD;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public MatriculaFacadeLocal getMatriculaFacadeLocal() {
		return matriculaFacadeLocal;
	}

	public void setMatriculaFacadeLocal(MatriculaFacadeLocal matriculaFacadeLocal) {
		this.matriculaFacadeLocal = matriculaFacadeLocal;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCueBuscar() {
		return cueBuscar;
	}

	public void setCueBuscar(String cueBuscar) {
		this.cueBuscar = cueBuscar;
	}

	public boolean isVisiblePanelCUE() {
		return visiblePanelCUE;
	}

	public void setVisiblePanelCUE(boolean visiblePanelCUE) {
		this.visiblePanelCUE = visiblePanelCUE;
	}

	public boolean isVisibleUbicacionCUE() {
		return visibleUbicacionCUE;
	}

	public void setVisibleUbicacionCUE(boolean visibleUbicacionCUE) {
		this.visibleUbicacionCUE = visibleUbicacionCUE;
	}

	public String getTieneCUE() {
		return tieneCUE;
	}

	public void setTieneCUE(String tieneCUE) {
		this.tieneCUE = tieneCUE;
	}

	public List<Provincia> getListaProvincia() {
		return listaProvincia;
	}

	public void setListaProvincia(List<Provincia> listaProvincia) {
		this.listaProvincia = listaProvincia;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public List<Canton> getListaCanton() {
		return listaCanton;
	}

	public void setListaCanton(List<Canton> listaCanton) {
		this.listaCanton = listaCanton;
	}

	public List<Parroquia> getListaParroquia() {
		return listaParroquia;
	}

	public void setListaParroquia(List<Parroquia> listaParroquia) {
		this.listaParroquia = listaParroquia;
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

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	public List<ProgramaInstitucion> getListaProgramaInstitucion() {
		return listaProgramaInstitucion;
	}

	public void setListaProgramaInstitucion(List<ProgramaInstitucion> listaProgramaInstitucion) {
		this.listaProgramaInstitucion = listaProgramaInstitucion;
	}

	public List<ProgramaInstitucion> getListaProgramaInstitucionFilter() {
		return listaProgramaInstitucionFilter;
	}

	public void setListaProgramaInstitucionFilter(List<ProgramaInstitucion> listaProgramaInstitucionFilter) {
		this.listaProgramaInstitucionFilter = listaProgramaInstitucionFilter;
	}

	public boolean isDisabledCbBuscarEstudiante() {
		return disabledCbBuscarEstudiante;
	}

	public void setDisabledCbBuscarEstudiante(boolean disabledCbBuscarEstudiante) {
		this.disabledCbBuscarEstudiante = disabledCbBuscarEstudiante;
	}

	public boolean isDisabledSbcProcesar() {
		return disabledSbcProcesar;
	}

	public void setDisabledSbcProcesar(boolean disabledSbcProcesar) {
		this.disabledSbcProcesar = disabledSbcProcesar;
	}

	public boolean isProtejerObjetos() {
		return protejerObjetos;
	}

	public void setProtejerObjetos(boolean protejerObjetos) {
		this.protejerObjetos = protejerObjetos;
	}

	public boolean isProtejerObjetosDA() {
		return protejerObjetosDA;
	}

	public void setProtejerObjetosDA(boolean protejerObjetosDA) {
		this.protejerObjetosDA = protejerObjetosDA;
	}
}
