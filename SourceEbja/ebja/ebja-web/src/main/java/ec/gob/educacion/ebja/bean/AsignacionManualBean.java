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
import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.EstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MensajeFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Estudiante;
import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.Matricula;
import ec.gob.educacion.ebja.modelo.Mensaje;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class AsignacionManualBean extends BaseControlador implements Serializable {
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
	private List<RegistroEstudianteDTO> listaEstudiantesNoMatricula;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<ProgramaInstitucion> listaProgramaInstitucionFilter;
	private List<Object[]> listaMatriculadosPorOfertaYZona;

	private RegistroEstudiante registroEstudiante;
	private Estudiante estudiante;
	private Matricula matricula;
	private Ubicacion ubicacion;
	private RegistroEstudianteDTO registroEstudianteDTO;
	private Mensaje mensaje;
	private Inscripcion inscripcion;
	private ReglaNegocio reglaNegocio;

	private Integer idProgramaEbja;
	private Integer idZona;
	
	private boolean disabledCbProcesar = true;
	private boolean noMatricula;
	
	private Date fechaActual = new Date();
	
	//Contadores.
	private Integer numInscrito;
	private Integer numEstudiante;
	private Integer numMatricula;
	private Integer numNoMatricula;
	private Integer numMatriculados;
	
	private String nombreProgramaEbja;
	private String motivoNoMatricula;
	private String rutaArchivo;
	private String fechaProceso;
	private String tituloPagina;

	private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

	@PostConstruct
	public void init() {
		//Obtener el título de la página.
		mensaje = mensajeFacadeLocal.obtenerTituloPagina(Constantes.NEMONICO_ASIGNACION_MANUAL);
		if (mensaje != null) {
			tituloPagina = mensaje.getCabecera();
		} else {
			tituloPagina = Constantes.TITULO_PAGINA_ASIGNACION_MANUAL;
		}

		fechaProceso = formateador.format(new Date());
		idProgramaEbja = null;
		idZona = null;
		
		consultarProgramas();
		consultarZonas();
	}
	
	public void resetearObjetos() {
		disabledCbProcesar = true;
		listaProgramaInstitucion = null;
		listaEstudiantesParaMatricula = null;
		listaEstudiantesNoMatricula = null;
		
		resetearContadores();
	}

	public void resetearContadores() {
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

		if (listaEstudiantesParaMatricula != null) {
			if (!listaEstudiantesParaMatricula.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se van a procesar " + listaEstudiantesParaMatricula.size() 
																					  + " aspirantes inscritos" 
																					  + ", y se encuentran matriculados "
																					  + listaMatriculadosPorOfertaYZona.size(), ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_REGISTROS, ""));
			}
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

	public void procesarAsignacionManual() {
		//Validar la existencia de instituciones del programa.
		if (listaProgramaInstitucion == null ||
			listaProgramaInstitucion.isEmpty()) {
			motivoNoMatricula = Constantes.NO_EXISTE_INSTITUCIONES;
			return;
		} else {
			//Validar que se haya seleccionado por lo menos una institución.
			boolean noEscogeInstitucion = true;
			for (ProgramaInstitucion programaInstitucion : listaProgramaInstitucion) {
				if (programaInstitucion.getInstitucEstablec().getEstado().equals("true")) {
					noEscogeInstitucion = false;
					break;
				}
			}
			if (noEscogeInstitucion) {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje", 
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_INSTITUCIONES, ""));
				return;
			}
		}
		
		resetearContadores();
		listaEstudiantesNoMatricula = new ArrayList<RegistroEstudianteDTO>();
		//Validar si existen estudiantes para procesar.
		if (listaEstudiantesParaMatricula == null || 
			listaEstudiantesParaMatricula.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.NO_EXISTE_REGISTROS, ""));
			return;
		}

		numInscrito = listaEstudiantesParaMatricula.size();
		int indice = 0;
		//Recorrer la lista de inscritos.
		for (Object[] object : listaEstudiantesParaMatricula) {
			// Validar que haya seleccionado el estudiante.
			if (((Inscripcion) (object[0])).getEstadoProceso()) {
				try {
					motivoNoMatricula = "";
					
					// Respaldar inscripcion para utilizar en estudiante.
					inscripcion = new Inscripcion();
					inscripcion = ((Inscripcion) (object[0]));

					// Respaldar registroEstudiante para utilizar en estudiante.
					registroEstudiante = new RegistroEstudiante();
					registroEstudiante = (RegistroEstudiante) (object[2]);

					// Respaldar ubicación para validar con la de la institución.
					ubicacion = new Ubicacion();
					ubicacion = (Ubicacion) (object[1]);

					// Instanciar la clase estudiante.
					estudiante = new Estudiante();

					// ProcesarMatricula.
					procesarMatriculaManual();
					
					if (noMatricula) {
						if (estudiante.getId() != null) {
							estudianteFacadeLocal.remove(estudiante);
							numEstudiante -= 1;
						}

						// Actualizar el motivo de la no matricula.
						registroEstudiante.setMotivoNoMatricula(motivoNoMatricula);
						registroEstudianteFacadeLocal.edit(registroEstudiante);
						
						numNoMatricula = numNoMatricula + 1;
						//Datos de aspirante No Matriculado.
						registroEstudianteDTO = new RegistroEstudianteDTO();
						registroEstudianteDTO.setNumeroIdentificacion(registroEstudiante.getNumeroIdentificacion());
						registroEstudianteDTO.setApellidosNombres(registroEstudiante.getApellidosNombres());
						registroEstudianteDTO.setIdInscripcion(registroEstudiante.getInscripcion().getId());
						registroEstudianteDTO.setMotivoNoMatricula(motivoNoMatricula);
						indice++;
						registroEstudianteDTO.setIndice(indice);
						listaEstudiantesNoMatricula.add(registroEstudianteDTO);
					}
				} catch (Exception e) {
					if (estudiante.getId() != null) {
						estudianteFacadeLocal.remove(estudiante);
						numEstudiante -= 1;
					}
					e.printStackTrace();
				}
			}
		}
		
		//Tratar resultados del proceso.
		resultadoProceso();
		
		//Actualizar las listas utilizadas.
		actualizarEstudiantesParaMatricula();

		disabledCbProcesar = true;
	}
	
	public void resultadoProceso() {
		if (numNoMatricula == 0 && numEstudiante == numMatricula) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje", 
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO, ""));
		} else {
			if (numMatricula > 0) {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO + ", pero existen registros con novedades", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje", 
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_NO_CORRECTO + ", existen registros con novedades", ""));
			}
		}
	}

	public void procesarMatriculaManual() {
		noMatricula = true;
		
		// Recorrer las instituciones del programaEbja y asignar a los estudiantes.
		for (ProgramaInstitucion programaInstitucion : listaProgramaInstitucion) {
			// Validar que haya seleccionado la institución del programa y que esa institucion tenga cupo disponible.
			if (programaInstitucion.getInstitucEstablec().getEstado().equals("true") &&
				programaInstitucion.getCupoDisponible() > 0) {
				try {
					// Tratar datos estudiante.
					estudiante.setInscripcion(inscripcion);
					estudiante.setRegistroEstudiante(registroEstudiante);
					estudiante.setCodigoEstudiante(codigoEstudiante());
					estudiante.setEstado("1");
					estudiante.setFechaCreacion(new Date());
					estudiante.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
					estudiante.setIpUsuario(sesionControlador.getIpAdressLocal());
					// Crear estudiante.
					estudianteFacadeLocal.create(estudiante);
					numEstudiante += 1;

					// Asignar datos a matricula.
					asignarDatosMatricula();
					matricula.setProgramaInstitucion(programaInstitucion);
					
					// Crear asignacion o matricula.
					matriculaFacadeLocal.create(matricula);

					// Actualizar a "1" el campo de estadoAsignacion.
					registroEstudiante.setEstadoAsignacion("1");
					registroEstudianteFacadeLocal.edit(registroEstudiante);

					// Decrementar en 1 el cupo disponible de la institucion del programa.
					programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() - 1);
					programaInstitucionFacadeLocal.edit(programaInstitucion);

					noMatricula = false;
					numMatricula += 1;

					break;
				} catch (Exception e) {
					motivoNoMatricula = "Problemas en crear Estudiante y/o Matricula...";
				}
			} else {
				motivoNoMatricula = "No ha seleccionado Institución o cupo no esta disponible";
			}
		}
	}
	
	// Asignar datos a matricula.
	public void asignarDatosMatricula() {
		matricula = new Matricula();
		matricula.setEstudiante(estudiante);
		matricula.setCursoParalelo(null);
		matricula.setPromover("0");
		matricula.setEstado("1");
		matricula.setFechaCreacion(new Date());
		matricula.setFechaProceso(new Date());
		matricula.setCatalogoTipoProceso(catalogoEbjaFacadeLocal.obtenerCatalogoPorTipoCatalogo("TIPPRO", "M"));
		matricula.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		matricula.setIpUsuario(sesionControlador.getIpAdressLocal());
	}
	
	public void exportarExcel() {
        try{
			Map<String, Object> parametros = new HashMap<String, Object>();
			ReporteJasper report = new ReporteJasper();

			parametros.put("fechaProceso", fechaProceso);
			parametros.put("nombreProgramaEbja", nombreProgramaEbja);
			parametros.put("nombreReporte", "Estudiantes No Matriculados");

			//Convertir List/recordset a JRBeanCollectionDataSource. (En irepot se lo define como: net.sf.jasperreports.engine.data.JRBeanCollectionDataSource)
			JRBeanCollectionDataSource listaEstudiantesNoMatriculaJRBean = new JRBeanCollectionDataSource(listaEstudiantesNoMatricula);
			parametros.put("listaEstudiantesNoMatricula", listaEstudiantesNoMatriculaJRBean);

			List<Object> reportList = new ArrayList<Object>();
			reportList.add("0");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
			
			//Verificar el tipo de reporte.
			report.generarReporteExcel(parametros, dataSource, "EstudiantesNoMatriculados");
        }catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se generó el archivo..."+e.getMessage(), ""));
        }
	}
	
	public void procesarRegistroEstudiante(Object[] objeto, int index) {
		if (((Inscripcion)(objeto[0])).getEstadoProceso()) {
			disabledCbProcesar = false;
		}
	}
	
	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	public void actualizarEstudiantesParaMatricula() {
		nombreProgramaEbja = programaEbjaFacadeLocal.find(idProgramaEbja).getNombre();

		//Resetear listas.
		listaProgramaInstitucion = new ArrayList<>();
		listaEstudiantesParaMatricula = new ArrayList<>();

		//Obtener instituciones del programaEbja.
		listaProgramaInstitucion = programaInstitucionFacadeLocal.listaProgramaInstitucionPorProgramaEbja(idProgramaEbja, idZona);
		if (!listaProgramaInstitucion.isEmpty()) {
			//Obtener los inscritos en el programaEbja y zona.
			listaEstudiantesParaMatricula = matriculaFacadeLocal.obtenerEstudiantesParaMatriculaSU(idProgramaEbja, idZona);
			//Obtener los matriculados en el programaEbja y zona.
			listaMatriculadosPorOfertaYZona = matriculaFacadeLocal.listaMatriculadosPorOfertaYZona(idProgramaEbja, idZona);
			if (!listaMatriculadosPorOfertaYZona.isEmpty()) {
				numMatriculados = listaMatriculadosPorOfertaYZona.size();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje", 
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_INSTITUCIONES, ""));
		}
	}

	public String codigoEstudiante() {
		String codigoEstudiante = "EE";
		int longitud = estudianteFacadeLocal.secuencialEstudiante().toString().length(); 
		for (int i = longitud; i < 13; i++) {
			codigoEstudiante = codigoEstudiante + "0";
		}
		codigoEstudiante = codigoEstudiante + estudianteFacadeLocal.secuencialEstudiante().toString();
		
		return codigoEstudiante;
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

	public boolean isDisabledCbProcesar() {
		return disabledCbProcesar;
	}

	public void setDisabledCbProcesar(boolean disabledCbProcesar) {
		this.disabledCbProcesar = disabledCbProcesar;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getMotivoNoMatricula() {
		return motivoNoMatricula;
	}

	public void setMotivoNoMatricula(String motivoNoMatricula) {
		this.motivoNoMatricula = motivoNoMatricula;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getNombreProgramaEbja() {
		return nombreProgramaEbja;
	}

	public void setNombreProgramaEbja(String nombreProgramaEbja) {
		this.nombreProgramaEbja = nombreProgramaEbja;
	}

	public List<RegistroEstudianteDTO> getListaEstudiantesNoMatricula() {
		return listaEstudiantesNoMatricula;
	}

	public void setListaEstudiantesNoMatricula(List<RegistroEstudianteDTO> listaEstudiantesNoMatricula) {
		this.listaEstudiantesNoMatricula = listaEstudiantesNoMatricula;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public Integer getNumMatriculados() {
		return numMatriculados;
	}

	public void setNumMatriculados(Integer numMatriculados) {
		this.numMatriculados = numMatriculados;
	}
}