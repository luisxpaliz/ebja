package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.dto.RegistroEstudianteDTO;
import ec.gob.educacion.ebja.dto.TotalesMatriculaDTO;
import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.EstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MensajeFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Mensaje;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class AsignacionAutomaticaBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ProgramaEbjaFacadeLocal programaEbjaFacadeLocal;
	@EJB
	private ZonaFacadeLocal zonaFacadeLocal;
	@EJB
	private MatriculaFacadeLocal matriculaFacadeLocal;
	@EJB
	private EstudianteFacadeLocal estudianteFacadeLocal;
	@EJB
	private RegistroEstudianteFacadeLocal registroEstudianteFacadeLocal;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucionFacadeLocal;
	@EJB
	private CatalogoEbjaFacadeLocal catalogoEbjaFacadeLocal;
	@EJB
	private MensajeFacadeLocal mensajeFacadeLocal;
	@EJB
	private ReglaNegocioFacadeLocal reglaNegocioFacadeLocal;
	@Inject
	private SesionControlador sesionControlador;
	
	private List<ProgramaEbja> listaProgramaEbja;
	private List<Zona> listaZona;
	private List<Object[]> listaEstudiantesParaMatricula;
	private List<Object[]> listaEstudiantesParaMatriculaFilter;
	private List<Object[]> listaEstudiantesNoMatricula;
	private List<RegistroEstudianteDTO> listaEstudiantesNoMatriculaDTO;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<ProgramaInstitucion> listaProgramaInstitucionFilter;

	private TotalesMatriculaDTO totalesMatriculaDTO;
	private RegistroEstudianteDTO registroEstudianteDTO; 
	private Mensaje mensaje;
	private ReglaNegocio reglaNegocio;
	
	private boolean disabledCbProcesar = true;
	
	private Date fechaActual = new Date();
	
	//Contadores.
	private Integer numInscrito;
	private Integer numEstudiante;
	private Integer numMatricula;
	private Integer numNoMatricula;

	private Integer idProgramaEbja;
	private Integer idZona;
	private Integer cantidadEstudiantesParaMatricula;
	
	private String nombreProgramaEbja;
	private String motivoNoMatricula;
	private String rutaArchivo;
	private String fechaProceso;
	private String tituloPagina;

	private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

	@PostConstruct
	public void init() {
		//Obtener el título de la página.
		mensaje = mensajeFacadeLocal.obtenerTituloPagina(Constantes.NEMONICO_ASIGNACION_AUTOMATICA);
		if (mensaje != null) {
			tituloPagina = mensaje.getCabecera();
		} else {
			tituloPagina = Constantes.TITULO_PAGINA_ASIGNACION_AUTOMATICA;
		}

		fechaProceso = formateador.format(new Date());
		idProgramaEbja = null;
		idZona = null;
		cantidadEstudiantesParaMatricula = 0;

		consultarProgramas();
		consultarZonas();
	}
	
	public void resetearObjetos() {
		disabledCbProcesar = true;
		listaProgramaInstitucion = null;
		listaEstudiantesParaMatricula = null;
		listaEstudiantesNoMatricula = null;
		
		//Contadores.
		numInscrito = 0;
		numEstudiante = 0;
		numMatricula = 0;
		numNoMatricula = 0;
	}
	
	public void obtenerOferta() {
		resetearObjetos();
		idZona = null;
	}
	
	public void obtenerZona() {
		resetearObjetos();
	}

	public void buscarEstudiantesParaMatricula() {
		resetearObjetos();
		
		if (!validarReglaNegocio()) {
			return;
		}
		
		actualizarEstudiantesParaMatricula();
		
		if (cantidadEstudiantesParaMatricula > 0) {
			disabledCbProcesar = false;
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se van a procesar " + cantidadEstudiantesParaMatricula + " aspirantes inscritos", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_REGISTROS, ""));
		}
	}
	
	public boolean validarReglaNegocio() {
		//Obtener regla_negocio, relacionado con la oferta más la fase.
		reglaNegocio = reglaNegocioFacadeLocal.buscarPorProgramaEbjaFase(idProgramaEbja, Constantes.NEMONICO_ASIGNACION);
		if (reglaNegocio == null || reglaNegocio.getFechaFin() == null || reglaNegocio.getFechaInicio() == null) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.NO_EXISTE_REGLA_NEGOCIO, ""));
			return false;
		}
		
		//Validar que fecha actual se encuentre en el rango de fecha inicio y fin de la FASE.
		//Incluir en el rango la fecha actual. (20190715)
		try {
			Date fechaDateActual, fechaDateInicio, fechaDateFin;

			fechaDateActual = formateador.parse(formateador.format(fechaActual));
			fechaDateInicio = formateador.parse(formateador.format(reglaNegocio.getFechaInicio()));
			fechaDateFin = formateador.parse(formateador.format(reglaNegocio.getFechaFin()));

			if (fechaDateActual.equals(fechaDateInicio) || 
				fechaDateActual.equals(fechaDateFin)) {
			} else {
				if (fechaActual.before(reglaNegocio.getFechaInicio()) || fechaActual.after(reglaNegocio.getFechaFin())) { 
					FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.FECHA_FASE_FUERA_RANGO, ""));
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public void procesarAsignacionAutomaticaFuncion() {
		totalesMatriculaDTO = new TotalesMatriculaDTO();
		
		//Invocar a la función de postgresql, para realizar la asignación automática. (jbrito-20181026)
		totalesMatriculaDTO = matriculaFacadeLocal.procesoMatriculaAutomaticaFuncion(idProgramaEbja, idZona, sesionControlador.getUsuarioSesion().getId().intValue(), sesionControlador.getIpAdressLocal());
		
		numInscrito = totalesMatriculaDTO.getNumInscrito();
		numEstudiante = totalesMatriculaDTO.getNumEstudiante();
		numMatricula = totalesMatriculaDTO.getNumMatricula();
		numNoMatricula = totalesMatriculaDTO.getNumNoMatricula();
		
		//Obtener los inscritos que no han sido asignados o matriculados..
		listaEstudiantesNoMatricula = matriculaFacadeLocal.obtenerEstudiantesParaMatricula(idProgramaEbja, idZona);
		
		//Tratar mensajes, según resultados obtenidos.
		resultadoProcesoFuncion();

		disabledCbProcesar = true;
	}
	
	public void resultadoProcesoFuncion() {
		if (numInscrito > 0) {
			if (numInscrito == numEstudiante && numEstudiante == numMatricula) {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO, ""));
			} else {
				if (numMatricula > 0) {
					FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO + ", pero existen registros con novedades", ""));
				} else {
					FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_NO_CORRECTO, ""));
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.NO_EXISTE_REGISTROS, ""));
		}
	}
	
	public void exportarExcel() {
        String nombreArchivoExcel = "estudiantes_no_asignados.csv";
		SimpleDateFormat formateador1 = new SimpleDateFormat("yyyy");
		rutaArchivo = Constantes.pathArchivoLocal + formateador1.format(new Date()) + "\\" + nombreArchivoExcel;
		
		if (!listaEstudiantesNoMatricula.isEmpty()) {
			listaEstudiantesNoMatriculaDTO = new ArrayList<>();
        	//Crear c/registro de la lista.
			int rowIndex = 1;
            for (Object[] object : listaEstudiantesNoMatricula) {
    			registroEstudianteDTO = new RegistroEstudianteDTO();

    			registroEstudianteDTO.setIndice(rowIndex);
                registroEstudianteDTO.setIdInscripcion(((RegistroEstudiante) (object[2])).getInscripcion().getId());
                registroEstudianteDTO.setNumeroIdentificacion(((RegistroEstudiante) (object[2])).getNumeroIdentificacion());
                registroEstudianteDTO.setApellidosNombres(((RegistroEstudiante) (object[2])).getApellidosNombres());
                registroEstudianteDTO.setMotivoNoMatricula(((RegistroEstudiante) (object[2])).getMotivoNoMatricula());
                listaEstudiantesNoMatriculaDTO.add(registroEstudianteDTO);
                rowIndex++;
            }
			try {
				Map<String, Object> parametros = new HashMap<String, Object>();
				ReporteJasper report = new ReporteJasper();

				parametros.put("fechaProceso", fechaProceso);
				parametros.put("nombreProgramaEbja", nombreProgramaEbja);
				parametros.put("nombreReporte", Constantes.nombreReporteNoMatriculados);

				//Convertir List/recordset a JRBeanCollectionDataSource. (En irepot se lo define como: net.sf.jasperreports.engine.data.JRBeanCollectionDataSource)
				JRBeanCollectionDataSource listaEstudiantesNoMatriculaDTOJRBean = new JRBeanCollectionDataSource(listaEstudiantesNoMatriculaDTO);
				parametros.put("listaEstudiantesNoMatricula", listaEstudiantesNoMatriculaDTOJRBean);

				List<Object> reportList = new ArrayList<Object>();
				reportList.add("0");
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
				
				report.generarReporteExcel(parametros, dataSource, Constantes.nombreArchivoJRXML_NO_ASIG);
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.NO_GENERO_ARCHIVO + e.getMessage(), ""));
			}
		}
	}
	
	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	public void actualizarEstudiantesParaMatricula() {
		nombreProgramaEbja = programaEbjaFacadeLocal.find(idProgramaEbja).getNombre();

		//Obtener instituciones del programaEbja.
		listaProgramaInstitucion = programaInstitucionFacadeLocal.listaProgramaInstitucionPorProgramaEbja(idProgramaEbja, idZona);

		if (!listaProgramaInstitucion.isEmpty()) {
			//Obtener la cantidad de inscritos en el programaEbja y zona.
			cantidadEstudiantesParaMatricula = matriculaFacadeLocal.cantidadEstudiantesParaMatricula(idProgramaEbja, idZona);
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje", 
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_INSTITUCIONES, ""));
		}
	}
	
	private void consultarProgramas() {
		listaProgramaEbja = programaEbjaFacadeLocal.findAllActive();
	}

	public void consultarZonas() {
		listaZona = zonaFacadeLocal.findAll();
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

	public boolean isDisabledCbProcesar() {
		return disabledCbProcesar;
	}

	public void setDisabledCbProcesar(boolean disabledCbProcesar) {
		this.disabledCbProcesar = disabledCbProcesar;
	}

	public List<Object[]> getListaEstudiantesParaMatricula() {
		return listaEstudiantesParaMatricula;
	}

	public void setListaEstudiantesParaMatricula(List<Object[]> listaEstudiantesParaMatricula) {
		this.listaEstudiantesParaMatricula = listaEstudiantesParaMatricula;
	}

	public List<ProgramaInstitucion> getListaProgramaInstitucion() {
		return listaProgramaInstitucion;
	}

	public void setListaProgramaInstitucion(List<ProgramaInstitucion> listaProgramaInstitucion) {
		this.listaProgramaInstitucion = listaProgramaInstitucion;
	}

	public Integer getNumInscrito() {
		return numInscrito;
	}

	public void setNumInscrito(Integer numInscrito) {
		this.numInscrito = numInscrito;
	}

	public Integer getNumEstudiante() {
		return numEstudiante;
	}

	public void setNumEstudiante(Integer numEstudiante) {
		this.numEstudiante = numEstudiante;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getNumNoMatricula() {
		return numNoMatricula;
	}

	public void setNumNoMatricula(Integer numNoMatricula) {
		this.numNoMatricula = numNoMatricula;
	}

	public List<ProgramaInstitucion> getListaProgramaInstitucionFilter() {
		return listaProgramaInstitucionFilter;
	}

	public void setListaProgramaInstitucionFilter(List<ProgramaInstitucion> listaProgramaInstitucionFilter) {
		this.listaProgramaInstitucionFilter = listaProgramaInstitucionFilter;
	}

	public List<Object[]> getListaEstudiantesParaMatriculaFilter() {
		return listaEstudiantesParaMatriculaFilter;
	}

	public void setListaEstudiantesParaMatriculaFilter(List<Object[]> listaEstudiantesParaMatriculaFilter) {
		this.listaEstudiantesParaMatriculaFilter = listaEstudiantesParaMatriculaFilter;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public List<Object[]> getListaEstudiantesNoMatricula() {
		return listaEstudiantesNoMatricula;
	}

	public void setListaEstudiantesNoMatricula(List<Object[]> listaEstudiantesNoMatricula) {
		this.listaEstudiantesNoMatricula = listaEstudiantesNoMatricula;
	}

	public String getMotivoNoMatricula() {
		return motivoNoMatricula;
	}

	public void setMotivoNoMatricula(String motivoNoMatricula) {
		this.motivoNoMatricula = motivoNoMatricula;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}
}