package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.sql.Timestamp;
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
import ec.gob.educacion.ebja.facade.local.FaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.modelo.Fase;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class ReglasNegocioBean extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReglaNegocio reglaNegocio;
	private ReglaNegocio reglaNegocioBorrar;

	@EJB
	private ReglaNegocioFacadeLocal reglasNegocios;
	@EJB
	private ProgramaEbjaFacadeLocal programasEbja;
	@EJB
	private FaseFacadeLocal fases;
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private ProgramaEducativoFacadeLocal programasEducativos;
	@EJB
	private GrupoFaseFacadeLocal grupoFases;

	private String ModuloSeleccionado;
	private String ModuloNuevoRegistro;
	private String FaseSeleccionada;
	private List<ProgramaEbja> listaModulos;
	private List<ReglaNegocio> listaModulosActivos;
	private List<Fase> listaFases;
	private List<Object[]> listReglas;
	private int crearOEditar = 2;
	private Date fechaInicio;
	private Date fechaFin;
	private ProgramaEbja programaEbjaVar;
	private Fase faseVar;
	private String tipoProyectoSeleccionadoBusqueda;
	private List<ProgramaEducativo> listaProgramaEducativo;
	private List<GrupoFasePrograma> listaGrupoFaseProg;
	private String tipoFaseSeleccionadaBusqueda;

	@PostConstruct
	public void init() {
		
		consultarFases();
	}

	public void buscarModulo() {

		if (ModuloNuevoRegistro == null && ModuloSeleccionado == null) {
		} else {
			listReglas = reglasNegocios.findByProgramaEbja(ModuloSeleccionado);
		}
	}

	public void buscarModuloGuardado() {
		setModuloSeleccionado(ModuloNuevoRegistro);
		listReglas = reglasNegocios.findByProgramaEbja(ModuloNuevoRegistro);
	}

	public void obtenerFasesdeProyectoBusqueda() {

		if (tipoProyectoSeleccionadoBusqueda.isEmpty()) {
			listaGrupoFaseProg = new ArrayList<GrupoFasePrograma>();
		} else {
			listaGrupoFaseProg = grupoFases.buscarGrupoFaseProgActInternosXProyecto(tipoProyectoSeleccionadoBusqueda);
		}
	}

	public void buscarModuloActivado() {
		listReglas = reglasNegocios.findByProgramaEbja(ModuloSeleccionado);
	}

	public void consultarFases() {
		listaFases = fases.findAllActive();
		listaProgramaEducativo = new ArrayList();
		listaProgramaEducativo = programasEducativos.buscarTodosProgramaEducativoActivos();
	}

	public void setearCrearRegla() {
		setModuloNuevoRegistro("");
		setFaseSeleccionada("");
		setModuloSeleccionado("");
		setCrearOEditar(1); // bandera de crear acuerdo
		setFechaInicio(new Date());
		setFechaFin(new Date());
	}

	private void setearRegla() {
		setCrearOEditar(2);

	}

	public void ejecutarCrearOEditarRegistro() {

		if (validarFechas()) {
			if (getCrearOEditar() == 1) {
				crearRegistroRegla();
			} else {
				editarRegla();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error: La Fecha Fin debe ser mayor que la Fecha Inicio", ""));
		}
	}

	private boolean validarFechas() {

		return (fechaInicio.getTime() < fechaFin.getTime()) ? true : false;

	}

	public void reglaSeleccionadoEditar(Object[] object) {
		setearRegla();
		this.reglaNegocio = ((ReglaNegocio) (object[0]));
		setFechaFin(reglaNegocio.getFechaFin());
		setFechaInicio(reglaNegocio.getFechaInicio());
		setModuloNuevoRegistro(reglaNegocio.getProgramaEbja().getNemonico());
		setFaseSeleccionada(reglaNegocio.getFase().getNemonico());

	}

	public void reglaSeleccionadoActivar(Object[] object) {
		this.reglaNegocio = ((ReglaNegocio) (object[0]));
	}

	public void reglaSeleccionadoBorrar(Object[] object) {
		this.reglaNegocioBorrar = ((ReglaNegocio) (object[0]));
	}

	public void borrarRegla() {
		borrarReglaLogico();
	}

	public void activarRegla() {

		if (reglaNegocio.getEstado().contentEquals("1")) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
		} else {
			this.reglaNegocio.setEstado("1");
			reglasNegocios.edit(reglaNegocio);
			buscarModuloActivado();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
		}

	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	private void borrarReglaLogico() {

		if (reglaNegocioBorrar.getEstado().contentEquals("0")) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
		} else {
			this.reglaNegocioBorrar.setEstado("0");
			reglasNegocios.edit(reglaNegocioBorrar);
			buscarModuloActivado();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
		}

	}

	

	public void crearRegistroRegla() {
		try {

			reglaNegocio = new ReglaNegocio();
			cargarRegla();

			if (!validarRegistroExistentes(programaEbjaVar, faseVar, fechaInicio, fechaFin)) {

				reglasNegocios.create(reglaNegocio);
				buscarModuloGuardado();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));

			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No se puede crear, ya existe un registro con la misma Oferta, Fase y Periodo", ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
		}

	}

	public boolean validarRegistroExistentes(ProgramaEbja modalidadVar, Fase faseVar, Date fechaInicioVar,
			Date fechaFinVar) {
		listaModulosActivos = reglasNegocios.findByProgramaEbjaValido(modalidadVar.getNemonico());

		boolean registroRepetido = true;

		if (getCrearOEditar() == 2) {

			for (int i = 0; i < listaModulosActivos.size(); i++) {
				if (listaModulosActivos.get(i).getFase().getNemonico().contentEquals(faseVar.getNemonico())) {
					listaModulosActivos.remove(i);
				}
			}
		}

		if (listaModulosActivos.isEmpty()) {
			registroRepetido = false;
		} else {

			for (ReglaNegocio var : listaModulosActivos) {

				if (var.getFase().getNemonico().contentEquals(faseVar.getNemonico())) {
					registroRepetido = true;
					break;
				} else {
					if (faseVar.getFase() == null) {
						registroRepetido = false;
					} else {

						if (var.getFase().getNemonico().contentEquals(faseVar.getFase().getNemonico())) {

							if (fechaInicioVar.after(var.getFechaInicio()) && fechaInicioVar.after(var.getFechaFin())
									&& fechaFinVar.after(var.getFechaInicio())
									&& fechaFinVar.after(var.getFechaFin())) {
								registroRepetido = false;
							} else {
								registroRepetido = true;
								break;
							}

						} else {
							registroRepetido = false;
						}

					}
				}
			}

		}
		return registroRepetido;
	}

	public void editarRegla() {

		try {
			reglaNegocio.setFechaInicio(new Timestamp(fechaInicio.getTime()));
			reglaNegocio.setFechaFin(new Timestamp(fechaFin.getTime()));
			faseVar = fases.obtenerFase(FaseSeleccionada);
			reglaNegocio.setFase(faseVar);
			programaEbjaVar = programasEbja.obtenerProgramaEbja(ModuloNuevoRegistro);
			reglaNegocio.setProgramaEbja(programaEbjaVar);

			if (!validarRegistroExistentes(programaEbjaVar, faseVar, fechaInicio, fechaFin)) {
				reglasNegocios.edit(reglaNegocio);
				buscarModuloGuardado();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));

			} else {
				buscarModuloGuardado();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No se puede crear, ya existe un registro con la misma Oferta, Fase y Periodo", ""));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}
	}

	public void cargarRegla() {

		reglaNegocio.setEstado("1");
		faseVar = (Fase) fases.obtenerFase(FaseSeleccionada);
		reglaNegocio.setFase(faseVar);
		reglaNegocio.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		reglaNegocio.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		reglaNegocio.setFechaFin(new Timestamp(fechaFin.getTime()));
		reglaNegocio.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		reglaNegocio.setIpUsuario(sesionControlador.getIpAdressLocal());
		programaEbjaVar = (ProgramaEbja) programasEbja.obtenerProgramaEbja(ModuloNuevoRegistro);
		reglaNegocio.setProgramaEbja(programaEbjaVar);

	}
	
	public void obtenerOfertaBusqueda() {
		listaModulos = programasEbja.obtenerProgramaEbjaGrupoFaseExtraordinariaNemonico(tipoFaseSeleccionadaBusqueda);
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public String getModuloSeleccionado() {
		return ModuloSeleccionado;
	}

	public void setModuloSeleccionado(String moduloSelecionado) {
		ModuloSeleccionado = moduloSelecionado;
	}

	public List<ProgramaEbja> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<ProgramaEbja> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public int getCrearOEditar() {
		return crearOEditar;
	}

	public void setCrearOEditar(int crearOEditar) {
		this.crearOEditar = crearOEditar;
	}

	public String getFaseSeleccionada() {
		return FaseSeleccionada;
	}

	public void setFaseSeleccionada(String faseSeleccionada) {
		FaseSeleccionada = faseSeleccionada;
	}

	public List<Fase> getListaFases() {
		return listaFases;
	}

	public void setListaFases(List<Fase> listaFase) {
		this.listaFases = listaFase;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Object[]> getListReglas() {
		return listReglas;
	}

	public void setListReglas(List<Object[]> listReglas) {
		this.listReglas = listReglas;
	}

	public String getModuloNuevoRegistro() {
		return ModuloNuevoRegistro;
	}

	public void setModuloNuevoRegistro(String moduloNuevoRegistro) {
		ModuloNuevoRegistro = moduloNuevoRegistro;
	}

	public String getTipoProyectoSeleccionadoBusqueda() {
		return tipoProyectoSeleccionadoBusqueda;
	}

	public void setTipoProyectoSeleccionadoBusqueda(String tipoProyectoSeleccionadoBusqueda) {
		this.tipoProyectoSeleccionadoBusqueda = tipoProyectoSeleccionadoBusqueda;
	}

	public List<ProgramaEducativo> getListaProgramaEducativo() {
		return listaProgramaEducativo;
	}

	public void setListaProgramaEducativo(List<ProgramaEducativo> listaProgramaEducativo) {
		this.listaProgramaEducativo = listaProgramaEducativo;
	}

	public List<GrupoFasePrograma> getListaGrupoFaseProg() {
		return listaGrupoFaseProg;
	}

	public void setListaGrupoFaseProg(List<GrupoFasePrograma> listaGrupoFaseProg) {
		this.listaGrupoFaseProg = listaGrupoFaseProg;
	}

	public String getTipoFaseSeleccionadaBusqueda() {
		return tipoFaseSeleccionadaBusqueda;
	}

	public void setTipoFaseSeleccionadaBusqueda(String tipoFaseSeleccionadaBusqueda) {
		this.tipoFaseSeleccionadaBusqueda = tipoFaseSeleccionadaBusqueda;
	}
	
	

}
