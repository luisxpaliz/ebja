package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.CursoParaleloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DocenteCursoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InstitucEstablecFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MallaDocenteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.NotaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Asignatura;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.Notas;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.seguridades.RolAplicacion;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.Institucion;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;

@ManagedBean
@ViewScoped
public class NotasBean extends BaseControlador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Object[]> listaDatos;
	private List<List<Object>> listaNotas;
	private List<Object> listaNotasColumnas;
	private List<Object> listacolumnas;
	private String nombreDocenteLogueado;
	private Long identificacionDocente;
	private String institucionSeleccionada;
	private List<Institucion> listaInstituciones;
	private List<ProgramaEbja> listaProgramas;
	private String programaSeleccionado;
	private List<Paralelo> listaParalelo;
	private String paraleloSeleccionado;
	private List<Grado> listaGrados;
	private String gradoSeleccionado;
	private String asignaturaSeleccionada;
	private List<Asignatura> listaAsignatura;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	private List<CursoParalelo> listaCursoParalelo;
	
	
	
	@Inject
	private SesionControlador sesionControlador;
	@EJB 
	private NotaFacadeLocal notas;
	@EJB
	private DocenteCursoFacadeLocal docenteCurso;
	@EJB
	private ProgramaInstitucionFacadeLocal programaInstitucion;
	@EJB
	private CursoParaleloFacadeLocal cursoParalelo;
	@EJB
	private ProgramaGradoFacadeLocal programaGrado;
	@EJB
	private MallaDocenteFacadeLocal mallaDocente;
	
	@PostConstruct
	public void init() {
		
		consultarInstituciones();
		consultarNotas();
		nombreDocenteLogueado = sesionControlador.getUsuarioSesion().getNombre();
		
	}
	
	public NotasBean() {
		listaDatos = new ArrayList<>();
		listaNotas = new ArrayList<>();
		listaNotasColumnas = new ArrayList<>(); 
		listacolumnas = new ArrayList<>();
		listaProgramas = new ArrayList<>();
		listaProgramaInstitucion = new ArrayList<>();
		listaCursoParalelo = new ArrayList<>();
		
	}
	
	private void consultarInstituciones() {
		identificacionDocente = sesionControlador.getUsuarioSesion().getId();
		listaInstituciones = docenteCurso.buscarInstitucionDocente(Integer.parseInt(identificacionDocente.toString()));
	}
	
	public void consultarPrograma() {
		
		listaProgramas = programaInstitucion.buscarProgramaEbjaInstitucion(institucionSeleccionada);
		
	}
	
	public void consultarParalelo() {
		
		listaParalelo = programaInstitucion.buscarParaleloInstitucion(institucionSeleccionada,programaSeleccionado);
		
	}
	
	public void consultarGrado() {
		
		listaGrados = programaGrado.buscarGradoUnificadosPorPrograma(programaSeleccionado);
	}
	
	public void consultarAsignatura() {
		
		listaProgramaInstitucion = programaInstitucion.buscarProgramaInstitucion(institucionSeleccionada,programaSeleccionado);
		listaCursoParalelo = cursoParalelo.obtenerCursoParaleloPorinstitucion(new Integer(paraleloSeleccionado),listaProgramaInstitucion.get(0).getId());
		listaAsignatura = mallaDocente.buscarAsignatura(listaCursoParalelo.get(0).getId(),listaProgramaInstitucion.get(0).getId());

	}
	
	public void consultarNotas() {
		listacolumnas.add("0");
		listacolumnas.add("1");
		listacolumnas.add("2");
		listacolumnas.add("3");
		listacolumnas.add("4");
		listacolumnas.add("5");
		listacolumnas.add("6");
		listaDatos = notas.findAllPivot();
	}

	public List<Object> getListacolumnas() {
		return listacolumnas;
	}

	public void setListacolumnas(List<Object> listacolumnas) {
		this.listacolumnas = listacolumnas;
	}

	public List<Object[]> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(List<Object[]> listaDatos) {
		this.listaDatos = listaDatos;
	}

	public List<List<Object>> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<List<Object>> listaNotas) {
		this.listaNotas = listaNotas;
	}

	public List<Object> getListaNotasColumnas() {
		return listaNotasColumnas;
	}

	public void setListaNotasColumnas(List<Object> listaNotasColumnas) {
		this.listaNotasColumnas = listaNotasColumnas;
	}

	public String getInstitucionSeleccionada() {
		return institucionSeleccionada;
	}

	public void setInstitucionSeleccionada(String institucionSeleccionada) {
		this.institucionSeleccionada = institucionSeleccionada;
	}

	public List<Institucion> getListaInstituciones() {
		return listaInstituciones;
	}

	public void setListaInstituciones(List<Institucion> listaInstituciones) {
		this.listaInstituciones = listaInstituciones;
	}

	public String getNombreDocenteLogueado() {
		return nombreDocenteLogueado;
	}

	public void setNombreDocenteLogueado(String nombreDocenteLogueado) {
		this.nombreDocenteLogueado = nombreDocenteLogueado;
	}

	public List<ProgramaEbja> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(List<ProgramaEbja> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramaSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}

	public List<Paralelo> getListaParalelo() {
		return listaParalelo;
	}

	public void setListaParalelo(List<Paralelo> listaParalelo) {
		this.listaParalelo = listaParalelo;
	}

	public String getParaleloSeleccionado() {
		return paraleloSeleccionado;
	}

	public void setParaleloSeleccionado(String paraleloSeleccionado) {
		this.paraleloSeleccionado = paraleloSeleccionado;
	}

	public List<Grado> getListaGrados() {
		return listaGrados;
	}

	public void setListaGrados(List<Grado> listaGrados) {
		this.listaGrados = listaGrados;
	}

	public ProgramaGradoFacadeLocal getProgramaGrado() {
		return programaGrado;
	}

	public void setProgramaGrado(ProgramaGradoFacadeLocal programaGrado) {
		this.programaGrado = programaGrado;
	}

	public String getGradoSeleccionado() {
		return gradoSeleccionado;
	}

	public void setGradoSeleccionado(String gradoSeleccionado) {
		this.gradoSeleccionado = gradoSeleccionado;
	}

	public String getAsignaturaSeleccionada() {
		return asignaturaSeleccionada;
	}

	public void setAsignaturaSeleccionada(String asignaturaSeleccionada) {
		this.asignaturaSeleccionada = asignaturaSeleccionada;
	}

	public List<Asignatura> getListaAsignatura() {
		return listaAsignatura;
	}

	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}
	
}
