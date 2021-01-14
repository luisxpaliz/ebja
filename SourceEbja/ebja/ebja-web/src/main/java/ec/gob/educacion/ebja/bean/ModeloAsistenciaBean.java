package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ModeloAsistenciaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;
import ec.gob.educacion.ebja.modelo.ModeloAsistencia;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;


@ManagedBean
@ViewScoped
public class ModeloAsistenciaBean  extends BaseControlador implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Object[]> ListaAsistencia;
	private ModeloAsistencia modeloAsistencia;
	private String archivoPdf;
	private int asistenciaParcial;
	private int asistenciaMateria;
	private UploadedFile archivoParaGuardar;
	private int crearOeditar = 2;
	private String programaSeleccionado;
	private String programaSeleccionadoGuardar;
	private String tipoAsistenciaSeleccionada;
	private List<ProgramaEbja> listaPrograma;
	private List<CatalogoEbja> ListaCatalogo;
	private String nombre;

	@EJB
	private ModeloAsistenciaFacadeLocal asistencia;
	@EJB
	private ProgramaEbjaFacadeLocal programaEbja;
	@EJB
	private CatalogoEbjaFacadeLocal catalogo;
	@Inject
	private SesionControlador sesionControlador;
	

	@PostConstruct
	public void init() {
		consultarAcuerdosModalidad();
		programaSeleccionado ="";
	}
	

	public void consultarAcuerdosModalidad() {
		
		listaPrograma = programaEbja.findAllActive();
		ListaCatalogo = catalogo.obtenerTipoAsistencia();

	}
	
	public ModeloAsistenciaBean() {
		ListaAsistencia = new ArrayList<Object[]>();
		listaPrograma = new ArrayList<>();
		
	}

	public void buscarAcuerdo() {
		if(programaSeleccionado!=null) {
			ListaAsistencia = asistencia.buscarTodosModelosAsistencia(programaSeleccionado);
			
		}
			
	}

	public void ejecutarCrearOEditarRegistro() {

			if (crearOeditar == 1) {
				crearRegistroAsistencia();
			}
			else {
				editarAsistencia();
			    buscarAcuerdo();
			}
		
	}

	public void borrarAsistencia() {
		try {
			borrarAsistenciaLogico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Inactivar el registro", ""));
		}
	}

	public void activarAcuerdo() {
		try {
			modeloAsistencia.setEstado("1");
			asistencia.edit(modeloAsistencia);
			buscarAcuerdo();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Activar el registro", ""));
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void cargaDatosRegistro() {

		modeloAsistencia.setEstado("1");
		modeloAsistencia.setCatalogoTipoAsistencia(((CatalogoEbja)catalogo.obtenerTipoAsistenciaNemonico(tipoAsistenciaSeleccionada)).getId());
		modeloAsistencia.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		modeloAsistencia.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		modeloAsistencia.setPorcentajeAsistenciaMateria(asistenciaMateria);
		modeloAsistencia.setPorcentajeAsistenciaParcial(asistenciaParcial);
		//modeloAsistencia.setProgramaEbja(programaEbja.obtenerProgramaEbja(programaSeleccionadoGuardar));
		modeloAsistencia.setIpUsuario(sesionControlador.getIpAdressLocal());		
	}

	public void setearCrearAsistencia() {
		crearOeditar = 1; // bandera de crear acuerdo
		
	}

	public void setearEditarAsistencia() {
		crearOeditar = 2; // bandera de editar acuerdo
	}


	public void crearRegistroAsistencia() {
		try {
			modeloAsistencia = new ModeloAsistencia();
			cargaDatosRegistro();
			asistencia.create(modeloAsistencia);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de guardó exitosamente", ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al guardar el registro", ""));
		}
	}

	public void asistenciaSeleccionadoEditar(Object[] object) {
		setearEditarAsistencia();
		this.modeloAsistencia = (ModeloAsistencia) (object[0]);
	//	setProgramaSeleccionadoGuardar(modeloAsistencia.getProgramaEbja().getNemonico());
		setTipoAsistenciaSeleccionada(((CatalogoEbja)catalogo.find(modeloAsistencia.getCatalogoTipoAsistencia())).getNemonico());
		setAsistenciaMateria(modeloAsistencia.getPorcentajeAsistenciaMateria());
		setAsistenciaParcial(modeloAsistencia.getPorcentajeAsistenciaParcial());
	}

	public void asistenciaSeleccionadoActivar(Object[] object) {
		this.modeloAsistencia = (ModeloAsistencia) (object[0]);
	}

	public void asistenciaSeleccionadoBorrar(Object[] object) {
		this.modeloAsistencia = (ModeloAsistencia) (object[0]);
	}

	public void editarAsistencia() {

		try {
			modeloAsistencia.setCatalogoTipoAsistencia(((CatalogoEbja)catalogo.obtenerTipoAsistenciaNemonico(tipoAsistenciaSeleccionada)).getId());
		    modeloAsistencia.setPorcentajeAsistenciaMateria(asistenciaMateria);
		    modeloAsistencia.setPorcentajeAsistenciaParcial(asistenciaParcial);
		 //   modeloAsistencia.setProgramaEbja(programaEbja.obtenerProgramaEbja(programaSeleccionadoGuardar));
		    asistencia.edit(modeloAsistencia);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de Editó exitosamente", ""));

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al Inactivar el registro", ""));
		}

	}

	public void borrarAsistenciaLogico() {
		modeloAsistencia.setEstado("0");	
		asistencia.edit(modeloAsistencia);
		buscarAcuerdo();
	}

	public void borrarAsistenciaFisico() {
		asistencia.remove(modeloAsistencia);
		buscarAcuerdo();
	}

//	/*------------------------------------Getters and Setters---------------------------------------*/

	

	public int getCrearOeditar() {
		return crearOeditar;
	}

	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
	}

	
	public List<Object[]> getListaAsistencia() {
		return ListaAsistencia;
	}

	public void setListaAsistencia(List<Object[]> listaAsistencia) {
		ListaAsistencia = listaAsistencia;
	}

	public String getArchivoPdf() {
		return archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	

	public ModeloAsistenciaFacadeLocal getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(ModeloAsistenciaFacadeLocal asistencia) {
		this.asistencia = asistencia;
	}

	public int getAsistenciaParcial() {
		return asistenciaParcial;
	}

	public void setAsistenciaParcial(int asistenciaParcial) {
		this.asistenciaParcial = asistenciaParcial;
	}

	public int getAsistenciaMateria() {
		return asistenciaMateria;
	}

	public void setAsistenciaMateria(int asistenciaMateria) {
		this.asistenciaMateria = asistenciaMateria;
	}

	public UploadedFile getArchivoParaGuardar() {
		return archivoParaGuardar;
	}

	public void setArchivoParaGuardar(UploadedFile archivoParaGuardar) {
		this.archivoParaGuardar = archivoParaGuardar;
	}

	public ModeloAsistencia getModeloAsistencia() {
		return modeloAsistencia;
	}

	public void setModeloAsistencia(ModeloAsistencia modeloAsistencia) {
		this.modeloAsistencia = modeloAsistencia;
	}

	public List<ProgramaEbja> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaEbja> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}


	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}


	public void setProgramaSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getProgramaSeleccionadoGuardar() {
		return programaSeleccionadoGuardar;
	}


	public void setProgramaSeleccionadoGuardar(String programaSeleccionadoGuardar) {
		this.programaSeleccionadoGuardar = programaSeleccionadoGuardar;
	}


	public String getTipoAsistenciaSeleccionada() {
		return tipoAsistenciaSeleccionada;
	}


	public void setTipoAsistenciaSeleccionada(String tipoAsistenciaSeleccionada) {
		this.tipoAsistenciaSeleccionada = tipoAsistenciaSeleccionada;
	}


	public List<CatalogoEbja> getListaCatalogo() {
		return ListaCatalogo;
	}


	public void setListaCatalogo(List<CatalogoEbja> listaCatalogo) {
		ListaCatalogo = listaCatalogo;
	}
	
	
	
}
