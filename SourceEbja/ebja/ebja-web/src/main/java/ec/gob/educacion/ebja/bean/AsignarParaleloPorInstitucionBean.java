package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
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
import org.primefaces.context.RequestContext;
import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.dto.MatriculaDTO;
import ec.gob.educacion.ebja.facade.local.CursoParaleloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.Matricula;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class AsignarParaleloPorInstitucionBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private CursoParaleloFacadeLocal cursoParaleloFacadeLocal;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucionFacadeLocal;
	@EJB
	private MatriculaFacadeLocal matriculaFacadeLocal;

	private List<CursoParalelo> listaCursoParalelo;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<Matricula> listaMatricula;
	private List<Matricula> listaMatriculaFilter;
	private List<Matricula> listaMatriculaInstitucion;
	private List<MatriculaDTO> listaMatriculaDTO;
	private Matricula matricula;
	
	private ProgramaInstitucion programaInstitucion;
	private CursoParalelo cursoParalelo;

	private Integer idProgramaInstitucion;
	private Integer idCursoParalelo;
	private int cupoDisponibleOrigen;
	private int indice;

	private String nombreProgramaEbja = "";
	private String nombreInstitucion = "";
	private String amie;
	private String fechaProceso;

	private boolean disabledCbAsignar;
	private boolean disabledClExcel;
	private boolean asignarParaleloTodos1;
	private boolean asignarParaleloTodos2;
	private boolean verParaleloTodos1;
	private boolean existeListaMatricula = false;

	@PostConstruct
	public void init() {
		programaInstitucion = new ProgramaInstitucion();
		matricula = new Matricula();
		disabledCbAsignar = true;
		disabledClExcel = true;
		verParaleloTodos1 = true;
		cupoDisponibleOrigen = 0;

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		fechaProceso = formateador.format(new Date());
		
		//Asignar identificación del usuario logueado.
		if (sesionControlador.getUsuarioSesion().getIdentificacion() != null) {
			amie = sesionControlador.getUsuarioSesion().getIdentificacion();
		} else {
			amie = "01H01618";
		}
		
		cargarListas();
	}
	
	public void obtenerParalelo() {
		if (existeListaMatricula) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('popConfirmarOferta').show();");
			return;
		}
		
		obtenerParaleloContinuar();
	}
	
	public void obtenerParaleloCancelar() {
		idProgramaInstitucion = programaInstitucion.getId();
	}
	
	public void obtenerParaleloContinuar() {
		disabledCbAsignar = true;
		disabledClExcel = true;
		verParaleloTodos1 = true;
		asignarParaleloTodos1 = false;
		idCursoParalelo = null;
		existeListaMatricula = false;

		listaMatricula = new ArrayList<>();
		
		programaInstitucion = new ProgramaInstitucion();
		programaInstitucion = programaInstitucionFacadeLocal.obtenerProgramaInstitucionPorId(idProgramaInstitucion);
		nombreInstitucion = programaInstitucion.getInstitucEstablec().getIdInstitucion().getDescripcion();
	//	nombreProgramaEbja = programaInstitucion.getProgramaGrado().getProgramaEbja().getNombre();
		cupoDisponibleOrigen = programaInstitucion.getCupoDisponible();
		
		listaCursoParalelo = new ArrayList<>();
		listaCursoParalelo = cursoParaleloFacadeLocal.obtenerCursoParaleloPorInstitucion(amie, idProgramaInstitucion);
	}
	
	public void obtenerEstudiante() {
		if (existeListaMatricula) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('popConfirmarParalelo').show();");
			return;
		}
		
		obtenerEstudianteContinuar();
	}
	
	public void obtenerEstudianteCancelar() {
		idCursoParalelo = cursoParalelo.getId();
	}

	public void obtenerEstudianteContinuar() {
		disabledCbAsignar = true;
		disabledClExcel = true;
		verParaleloTodos1 = true;
		asignarParaleloTodos1 = false;
		existeListaMatricula = false;

		programaInstitucion.setCupoDisponible(cupoDisponibleOrigen);
		
		cursoParalelo = new CursoParalelo();
		cursoParalelo = cursoParaleloFacadeLocal.find(idCursoParalelo);
		
		listaMatricula = new ArrayList<>();
		listaMatricula = matriculaFacadeLocal.obtenerEstudiantesAsignarParalelo(idCursoParalelo, cursoParalelo.getParalelo().getId());
	}
	
	public void asignarParaleloTodos1() {
		asignarParaleloTodos2 = true;
		verParaleloTodos1 = false;
		
		if (listaMatricula.size() > 0) {
			int indice = 0;
			//Asignar paralelo a todos los registros de la lista.
			for (Matricula matriculaAux : listaMatricula) {
            	if (matriculaAux.getCursoParalelo() == null) {
            		if (cursoParalelo.getNumeroMatriculado() < cursoParalelo.getNumeroBanca()) {
            			matriculaAux.setCursoParalelo(cursoParalelo);
            			cursoParalelo.setNumeroMatriculado(cursoParalelo.getNumeroMatriculado() + 1);
            			programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() - 1);

            			//Actualizar en la lista el check.
                		listaMatricula.set(indice, matriculaAux);

                		disabledCbAsignar = false;
                		disabledClExcel = true;
            		}
				}
        		
        		indice++;
			}
		}
	}
	
	public void asignarParaleloTodos2() {
		asignarParaleloTodos1 = false;
		verParaleloTodos1 = true;

		if (listaMatricula.size() > 0) {
			int indice = 0;
			//Asignar paralelo a todos los registros de la lista.
			for (Matricula matriculaAux : listaMatricula) {
            	if (matriculaAux.getCursoParalelo() != null) {
            		if (cursoParalelo.getNumeroMatriculado() > 0) {
                		matriculaAux.setCursoParalelo(null);
            			cursoParalelo.setNumeroMatriculado(cursoParalelo.getNumeroMatriculado() - 1);
            			programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() + 1);

            			//Actualizar en la lista el check.
                		listaMatricula.set(indice, matriculaAux);
                		
                		disabledCbAsignar = false;
                		disabledClExcel = true;
            		}
				}
        		
        		indice++;
			}
		}
	}
	
	public void asignarParalelo(Matricula matriculaAux, int indice) {
		existeListaMatricula = false;
		if (matriculaAux.getAsignarParalelo()) {
			if (cursoParalelo.getNumeroMatriculado() > 0) {
				matriculaAux.setCursoParalelo(null);
    			cursoParalelo.setNumeroMatriculado(cursoParalelo.getNumeroMatriculado() - 1);
				programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() + 1);

				existeListaMatricula = true;
			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('popConfirmarCupo').show();");
				return;
			}
		} else {
			if (cursoParalelo.getNumeroMatriculado() < (cursoParalelo.getNumeroBanca() + 1)) {
				matriculaAux.setCursoParalelo(cursoParalelo);
    			cursoParalelo.setNumeroMatriculado(cursoParalelo.getNumeroMatriculado() + 1);
				programaInstitucion.setCupoDisponible(programaInstitucion.getCupoDisponible() - 1);

				existeListaMatricula = true;
			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('popConfirmarCupo').show();");
				return;
			}
		}
		
		disabledCbAsignar = false;
		disabledClExcel = true;
		
		//Actualizar en la lista el check.
		listaMatricula.set(indice, matriculaAux);
	}

	public void asignarEstudianteParalelo() {
		if (listaMatricula != null) {
			try {
				//Actualizar el cursoParalelo en la matricula del aspirante.
				for (Matricula matriculaAux : listaMatricula) {
					matriculaFacadeLocal.edit(matriculaAux);
				}
				
				//Actualizar valores en el programaInstitucion.
				programaInstitucionFacadeLocal.edit(programaInstitucion);

				//Actualizar valores en el curso paralelo.
				cursoParaleloFacadeLocal.edit(cursoParalelo);
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO, ""));
				
				disabledClExcel = false;
				disabledCbAsignar = true;
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						 new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.PROCESO_NO_CORRECTO, ""));
			}
			
			//Actualizar datos de programaInstitucion y lista de matriculados.
			obtenerParaleloContinuar();
		}
	}
	
	public void exportarExcelParalelo() {
		exportarArchivo(listaMatricula, Constantes.nombreReportePorParalelo, cursoParalelo.getParalelo().getDescripcion(), "excel");
	}
	
	public void exportarExcelInstitucion() {
		listaMatriculaInstitucion = new ArrayList<>();
		listaMatriculaInstitucion = matriculaFacadeLocal.obtenerEstudiantesInstitucion(amie);
		
		exportarArchivo(listaMatriculaInstitucion, Constantes.nombreReportePorInstitucion, null, "excel");
	}
	
	public void exportarPdfParalelo() {
		exportarArchivo(listaMatricula, Constantes.nombreReportePorParalelo, cursoParalelo.getParalelo().getDescripcion(), "pdf");
	}
	
	public void exportarPdfInstitucion() {
		listaMatriculaInstitucion = new ArrayList<>();
		listaMatriculaInstitucion = matriculaFacadeLocal.obtenerEstudiantesInstitucion(amie);
		
		exportarArchivo(listaMatriculaInstitucion, Constantes.nombreReportePorInstitucion, null, "pdf");
	}

	public void exportarArchivo(List<Matricula> listaMatriculaAux, String nombreReporte, String paralelo, String tipoReporte) {
		//Verificar si existen registros en listaMatricula.
		if (listaMatricula.size() > 0) {
			int indice = 0;
			listaMatriculaDTO = new ArrayList<>();
			
			//Mover registros a lista DTO.
			for (Matricula matriculaAux : listaMatricula) {
				MatriculaDTO matriculaDTO = new MatriculaDTO();

				if (paralelo == null || (paralelo != null && matriculaAux.getCursoParalelo() != null)) {
					indice++;
					matriculaDTO.setIndice(indice);
					matriculaDTO.setIdInscripcion(matriculaAux.getEstudiante().getInscripcion().getId());
					matriculaDTO.setIdMatricula(matriculaAux.getId());
					matriculaDTO.setNumeroIdentificacion(matriculaAux.getEstudiante().getRegistroEstudiante().getNumeroIdentificacion());
					matriculaDTO.setApellidosNombres(matriculaAux.getEstudiante().getRegistroEstudiante().getApellidosNombres());
	            	if (matriculaAux.getCursoParalelo() != null) {
	            		matriculaDTO.setDescripcionParalelo(matriculaAux.getCursoParalelo().getParalelo().getDescripcion());
	    			} else {
	    				matriculaDTO.setDescripcionParalelo("Sin Paralelo");
	    			}
					
					listaMatriculaDTO.add(matriculaDTO);
				}
			}
			
			try {
				Map<String, Object> parametros = new HashMap<String, Object>();
				ReporteJasper report = new ReporteJasper();

				parametros.put("fechaProceso", fechaProceso);
				parametros.put("amie", amie);
				parametros.put("nombreInstitucion", nombreInstitucion);
				parametros.put("nombreProgramaEbja", nombreProgramaEbja);
				parametros.put("nombreReporte", nombreReporte);

				//Convertir List/recordset a JRBeanCollectionDataSource. (En irepot se lo define como: net.sf.jasperreports.engine.data.JRBeanCollectionDataSource)
				JRBeanCollectionDataSource listaMatriculaDTOJRBean = new JRBeanCollectionDataSource(listaMatriculaDTO);
				parametros.put("listaEstudianteParalelo", listaMatriculaDTOJRBean);

				List<Object> reportList = new ArrayList<Object>();
				reportList.add("0");
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
				
				//Verificar el tipo de reporte.
				if (tipoReporte.equals("excel")) {
					report.generarReporteExcel(parametros, dataSource, Constantes.nombreArchivoJRXML_ASIG_PAR);
				} else {
					report.generarReporteNavegador(parametros, dataSource, Constantes.nombreArchivoJRXML_ASIG_PAR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.NO_GENERO_ARCHIVO + e.getMessage(), ""));
			}
		}			
	}
	
	public void activarRegistro(Matricula matriculaAux, int indiceAux) {
		matricula = matriculaAux;
		matricula.setEstado("1");
		indice = indiceAux;
	}

	public void inactivarRegistro(Matricula matriculaAux, int indiceAux) {
		matricula = matriculaAux;
		matricula.setEstado("0");
		indice = indiceAux;
	}
	
	public void procesarRegistro() {
		//Actualizar en la lista el check.
		listaMatricula.set(indice, matricula);
	}
	
	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	private void cargarListas() {
		listaProgramaInstitucion = programaInstitucionFacadeLocal.obtenerProgramaInstitucionPorInstitucion(amie);
	}

	/*------------------------------------Getters and Setters---------------------------------------*/
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

	public CursoParalelo getCursoParalelo() {
		return cursoParalelo;
	}

	public void setCursoParalelo(CursoParalelo cursoParalelo) {
		this.cursoParalelo = cursoParalelo;
	}

	public String getAmie() {
		return amie;
	}

	public void setAmie(String amie) {
		this.amie = amie;
	}

	public Integer getIdCursoParalelo() {
		return idCursoParalelo;
	}

	public void setIdCursoParalelo(Integer idCursoParalelo) {
		this.idCursoParalelo = idCursoParalelo;
	}

	public Integer getIdProgramaInstitucion() {
		return idProgramaInstitucion;
	}

	public void setIdProgramaInstitucion(Integer idProgramaInstitucion) {
		this.idProgramaInstitucion = idProgramaInstitucion;
	}

	public ProgramaInstitucion getProgramaInstitucion() {
		return programaInstitucion;
	}

	public void setProgramaInstitucion(ProgramaInstitucion programaInstitucion) {
		this.programaInstitucion = programaInstitucion;
	}

	public List<Matricula> getListaMatricula() {
		return listaMatricula;
	}

	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}

	public List<Matricula> getListaMatriculaFilter() {
		return listaMatriculaFilter;
	}

	public void setListaMatriculaFilter(List<Matricula> listaMatriculaFilter) {
		this.listaMatriculaFilter = listaMatriculaFilter;
	}

	public boolean isDisabledCbAsignar() {
		return disabledCbAsignar;
	}

	public void setDisabledCbAsignar(boolean disabledCbAsignar) {
		this.disabledCbAsignar = disabledCbAsignar;
	}

	public boolean isDisabledClExcel() {
		return disabledClExcel;
	}

	public void setDisabledClExcel(boolean disabledClExcel) {
		this.disabledClExcel = disabledClExcel;
	}

	public boolean isAsignarParaleloTodos1() {
		return asignarParaleloTodos1;
	}

	public void setAsignarParaleloTodos1(boolean asignarParaleloTodos1) {
		this.asignarParaleloTodos1 = asignarParaleloTodos1;
	}

	public boolean isAsignarParaleloTodos2() {
		return asignarParaleloTodos2;
	}

	public void setAsignarParaleloTodos2(boolean asignarParaleloTodos2) {
		this.asignarParaleloTodos2 = asignarParaleloTodos2;
	}

	public boolean isVerParaleloTodos1() {
		return verParaleloTodos1;
	}

	public void setVerParaleloTodos1(boolean verParaleloTodos1) {
		this.verParaleloTodos1 = verParaleloTodos1;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getNombreProgramaEbja() {
		return nombreProgramaEbja;
	}

	public void setNombreProgramaEbja(String nombreProgramaEbja) {
		this.nombreProgramaEbja = nombreProgramaEbja;
	}

}