package ec.gob.educacion.ebja.controlador;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import ec.gob.educacion.ebja.facade.local.AdminAlfabAcuRelFacadeLocal;
import ec.gob.educacion.ebja.facade.local.AdminAlfabModuloFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InscripcionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ModalidadFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.TipoProgramaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.Modalidad;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.OpcionSiNo;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class adminAlfabModuloControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Object[]> listaModulos;
	private List<Acuerdo> listaAcuerdosAnadir;
	private int busquedaTipo = 1;
	private String busquedaContenido = "";
	private int crearOeditar = 2;
	private String nombre;
	private String nemonico;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaInicioClases;
	private Acuerdo acuerdo;
	private List<Acuerdo> listaAcuerdos;
	private List<Modalidad> listaModalidad;
	private ProgramaEbja nuevoModulo;
	private Integer edadMinima;
	private Integer regazoMinimo;
	private String acuerdoSeleccionado;
	private String modalidadSeleccionada;
	private int coberturaExtrajera;
	private Modalidad modalidadValidar;
	private Acuerdo acuerdoValidar;
	private TipoPrograma tipoProgramaValidar;
	private ProgramaEducativo tipoProyectoValidar;
	private List<OpcionSiNo> listaSiNO;
	private List<TipoPrograma> listaTipoProgramas;
	private String tipoProgramaSeleccionado;
	private String tipoProyectoSeleccionado;
	private Acuerdo acuerdoLista;
	private List<Acuerdo> listaAcuerdosAnadirEditar;
	private ProgramaEbja moduloInactivar;
	private ProgramaEbja moduloEliminar;
	private ProgramaEbja modulo; 
	private List<ProgramaEbja> listaProgramaEbjaActivos;
	private List<Boolean> tmpAcuerdoRepetido;
	private List<ProgramaEducativo> listaProgramaEducativo; 

	@EJB
	private AdminAlfabModuloFacadeLocal modulos;
	@EJB
	private AdminAlfabAcuRelFacadeLocal recursoAcuerdo;
	@EJB
	private TipoProgramaFacadeLocal tipoPrograma;
	@EJB
	private ModalidadFacadeLocal recursoModalidad;
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private ProgramaGradoFacadeLocal programaGrado;
	@EJB
	private ReglaNegocioFacadeLocal reglasNegocios;
	@EJB
	private InscripcionFacadeLocal inscripcionFacadeLocal;
	@EJB
	private ProgramaEducativoFacadeLocal programasEducativos;


	@PostConstruct
	public void init() {
		consultarAcuerdosModalidad();

	}

	public adminAlfabModuloControlador() {
		listaAcuerdos = new ArrayList<>();
		listaModulos = new ArrayList<>();
		listaModalidad = new ArrayList<>();
		listaTipoProgramas = new ArrayList<>();
		listaSiNO = new ArrayList<>();
		listaAcuerdosAnadir = new ArrayList<>();
		acuerdoLista = new Acuerdo();
		tmpAcuerdoRepetido = new ArrayList();
		listaProgramaEducativo = new ArrayList();

	}
	
	
	public void anadirAcuerdoLista() {
		
		if(acuerdoSeleccionado ==null || acuerdoSeleccionado.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModuloAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe escojer un Acuerdo para guardar el registro", ""));
		}else {
			acuerdoLista = recursoAcuerdo.findByCodigoSoloAcuerdo(acuerdoSeleccionado);
			if(buscarListaAcuerdosAnadir(acuerdoLista,listaAcuerdosAnadir)) 
				listaAcuerdosAnadir.add(acuerdoLista);
				else
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModuloAcuerdo",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,Constantes.REGISTRO_BBDD_REGISTROYAEXISTENTE, ""));	
		}
	}
	
	public boolean buscarListaAcuerdosAnadir(Acuerdo acuerdoBuscado, List<Acuerdo> tmpAcuerdoLista) {
		
		boolean tmpEstado = false;
         for(Acuerdo tmpAcuerdo : tmpAcuerdoLista) {
			if(tmpAcuerdo.getNemonico().contentEquals(acuerdoBuscado.getNemonico())) {
				tmpEstado = true;
				 break;
			}
			  
		}
		return !tmpEstado;
	}
	
	public void quitarAcuerdoLista() {
				
		if (listaAcuerdosAnadir.size() == listaAcuerdosAnadirEditar.size()) {
			listaAcuerdosAnadir.clear();
		} else {
			for (int i=0 ; i<listaAcuerdosAnadir.size();i++) {
				for (int y=0; y<listaAcuerdosAnadirEditar.size();y++) {
					if (listaAcuerdosAnadir.get(i).getNemonico().contains(listaAcuerdosAnadirEditar.get(y).getNemonico())) {
						listaAcuerdosAnadir.remove(i);
					}
				}
			}
		}
	}
	
	public void vaciarRegistrosAcuerdos() {
		setAcuerdoSeleccionado("");
		listaAcuerdosAnadir.clear();
	}

	public void buscarModulo() {

		switch (busquedaTipo) {
		case 1: {
			listaModulos = modulos.findByCodigo(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaModulos = modulos.findByNombre(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}
	}
	
	

	public boolean ejecutarCrearOEditarRegistro() {

		if (validarFechas()) {
			if (!validarCamposVacios()) {
				if (crearOeditar == 1)
					crearRegistroModulo();
				else
					editarModulo();
			buscarModulo();
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,Constantes.REGISTRO_BBDD_CAMPOS_VACIOS, ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error: La Fecha Fin debe ser mayor que la Fecha Inicio", ""));
		}
		return true;
	}

	public void borrarModulo() {
		try {
			if (moduloInactivar.getEstado().contentEquals("0")) {
				
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
			}else if(!programaGrado.buscarDependenciaPorCodigoGrado(moduloInactivar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados en Grados", ""));
				
				if(!reglasNegocios.buscarDependenciaPorCodigoReglaNegocio(moduloInactivar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados en Reglas de Negocio", ""));
				}
				if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloInactivar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados con Inscripción", ""));
				
				}
			
			}else if(!reglasNegocios.buscarDependenciaPorCodigoReglaNegocio(moduloInactivar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados en Reglas de Negocio", ""));
				
				if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloInactivar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados con Inscripción", ""));
				
				}
			
			}else if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloInactivar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados con Inscripción", ""));
			
			}
			
			else {
				moduloInactivar.setEstado("0");
				modulos.edit(moduloInactivar);
				buscarModulo();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
	
	public void eliminarModulo() {
		try {
			if(!programaGrado.buscarDependenciaPorCodigoGrado(moduloEliminar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados en Grados", ""));
				
				if(!reglasNegocios.buscarDependenciaPorCodigoReglaNegocio(moduloEliminar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados en Reglas de Negocio", ""));
				}
				if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloEliminar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados con Inscripción", ""));
				
				}
			
			}else if(!reglasNegocios.buscarDependenciaPorCodigoReglaNegocio(moduloEliminar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados en Reglas de Negocio", ""));
				
				if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloEliminar.getNemonico())) {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados con Inscripción", ""));
				
				}
			
			}else if (!inscripcionFacadeLocal.buscarInscripcionPorOferta(moduloEliminar.getNemonico())) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede eliminar el registro, porque existen datos relacionados con Inscripción", ""));
			
			}
			
			else {
				moduloEliminar.setEstado("3");
				modulos.edit(moduloEliminar);
				buscarModulo();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ELIMINO_EXITOSAMENTE, ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ELIMINO_ERROR, ""));
		}
	}

	public void activarModulo() {
		try {

			if (modulo.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo", new FacesMessage(
						FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			}else if(buscarAcuerdosDependencias(modulo)) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede activar el registro, porque el Acuerdo está inactivo", ""));
			}else {
				if (!validarRegistroExistentes(modulo.getModalidad(), modulo.getAcuerdos(),
						modulo.getTipoPrograma(), modulo.getFechaInicio(),
						modulo.getFechaFin(),2,modulo.getNombre())) {
					modulo.setEstado("1");
					modulos.edit(modulo);
					buscarModulo();
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));

				}

				else {

					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"No se puede Activar ya existe un registro con mismo Acuerdo, Modalidad y Periodo",
									""));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}
	
	public boolean buscarAcuerdosDependencias(ProgramaEbja programa) {
			
			for(Acuerdo tmpAcuerdoEvaluado : programa.getAcuerdos()) {
				if(tmpAcuerdoEvaluado.getEstado().contentEquals("0")) {
					tmpAcuerdoRepetido.add(true);
			     }
			}
	
		return (!tmpAcuerdoRepetido.isEmpty());
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	public void setearCrearModulo() {
		setCrearOeditar(1); // bandera de crear acuerdo
		setNombre("");
		setNemonico("");
		setFechaInicio(new Date());
		setFechaInicioClases(new Date());
		setFechaFin(new Date());
		setEdadMinima(0);
		setRegazoMinimo(0);
		setAcuerdoSeleccionado("");
		setTipoProgramaSeleccionado("");
		setTipoProyectoSeleccionado("");
		setModalidadSeleccionada("");
		setCoberturaExtrajera(3);
		getListaAcuerdosAnadir().clear();
		setTipoProyectoSeleccionado("");

	}

	public void setearEditarModulo() {
		setCrearOeditar(2); // bandera de editar acuerdo
	}

	public void consultarAcuerdosModalidad() {
		listaAcuerdos = recursoAcuerdo.buscarTodosAcuerdosActivos();
		listaModalidad = recursoModalidad.buscarTodasModalidadesActivas();
		listaProgramaEbjaActivos =  modulos.buscarProgramaEbjaActivos();
		listaProgramaEducativo = programasEducativos.buscarTodosProgramaEducativoActivos();
		listaTipoProgramas = tipoPrograma.findAll();
		listaSiNO.add(new OpcionSiNo("Sí", "1"));
		listaSiNO.add(new OpcionSiNo("NO", "0"));
		setCoberturaExtrajera(3);
	}

	public void moduloSeleccionadoActivar(Object[] object) {
		this.modulo = (ProgramaEbja) (object[0]);
	}

	private void cargaDatosRegistro() {

		nuevoModulo.setNombre(nombre);
		nuevoModulo.setNemonico(nemonico);
		nuevoModulo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		nuevoModulo.setIpUsuario(sesionControlador.getIpAdressLocal());
		nuevoModulo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		nuevoModulo.setEdadMinima(edadMinima);
		nuevoModulo.setRezagoMinimo(regazoMinimo);
		nuevoModulo.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		nuevoModulo.setFechaFin(new Timestamp(fechaFin.getTime()));
		nuevoModulo.setFechaInicioClases(new Timestamp(fechaInicioClases.getTime()));
		tipoProgramaValidar = tipoPrograma.ObtenerPrograma(tipoProgramaSeleccionado);
		nuevoModulo.setTipoPrograma(tipoProgramaValidar);
		tipoProyectoValidar = programasEducativos.findByCodigoSoloProgramaEducativo(tipoProyectoSeleccionado);
		//nuevoModulo.setProgramaEducativo(tipoProyectoValidar);
		modalidadValidar = (Modalidad) (recursoModalidad.findByCodigo(modalidadSeleccionada).get(0));
		nuevoModulo.setModalidad(modalidadValidar);
		nuevoModulo.setEstado("1"); // Activo
		nuevoModulo.setCobertura(String.valueOf(getCoberturaExtrajera()));
		nuevoModulo.setAcuerdos(new HashSet(listaAcuerdosAnadir));
		setAcuerdoSeleccionado("");
		setTipoProgramaSeleccionado("");
		setTipoProyectoSeleccionado("");
		setModalidadSeleccionada("");
		setCoberturaExtrajera(3);
		
		

	}

	public void moduloSeleccionadoEditar(Object[] object) {
		setearEditarModulo();
		this.modulo = (ProgramaEbja) (object[0]);
		setNombre(modulo.getNombre());
		setNemonico(modulo.getNemonico());
		setEdadMinima(modulo.getEdadMinima());
		setRegazoMinimo(modulo.getRezagoMinimo());
		setFechaInicio(modulo.getFechaInicio());
		setFechaFin(modulo.getFechaFin());
		setFechaInicioClases(modulo.getFechaInicioClases());
		setListaAcuerdosAnadir(modulo.getAcuerdos());
		setTipoProgramaSeleccionado(modulo.getTipoPrograma().getNemonico());
		//setTipoProyectoSeleccionado(modulo.getProgramaEducativo().getNemonico());
		setModalidadSeleccionada(modulo.getModalidad().getNemonico());
		setCoberturaExtrajera(Integer.parseInt(modulo.getCobertura()));

	}

	public void moduloSeleccionadoBorrar(Object[] object) {
		this.moduloInactivar = (ProgramaEbja) (object[0]);
	}
	
	public void moduloSeleccionadoEliminar(Object[] object) {
		this.moduloEliminar = (ProgramaEbja) (object[0]);
	}

	private boolean validarCamposVacios() {
		return (nombre.isEmpty() || nemonico.isEmpty() || edadMinima == null || regazoMinimo == null
				|| listaAcuerdosAnadir.isEmpty()) ? true : false;
	}

	private boolean validarFechas() {

		return (fechaInicio.getTime() < fechaFin.getTime()) ? true : false;

	}
	


	public void crearRegistroModulo() {
		try {
			nuevoModulo = new ProgramaEbja();
			cargaDatosRegistro();
			if (!validarRegistroExistentes(modalidadValidar, listaAcuerdosAnadir , tipoProgramaValidar,fechaInicio,
					fechaFin,1,nuevoModulo.getNombre())) {
				
				if(validarFechaInicioClases (fechaInicio,fechaFin,fechaInicioClases)) {
					modulos.create(nuevoModulo);
										
					FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
							new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));	
				}else {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla", new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "No se puede crear el registro. La fecha de inicio de clases debe estar dentro del periodo de la Oferta", ""));
				}
				
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No se puede crear, ya existe un registro con mismo Acuerdo, Modalidad y Periodo", ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}
		}
	}
	
	public boolean validarFechaInicioClases (Date fechaInicioVar, Date fechaFinVar, Date fechaIniClase)
	{
		if((fechaInicioVar.before(fechaIniClase) && fechaFinVar.after(fechaIniClase))) {
			return true;
		}else if((fechaInicioVar.equals(fechaIniClase)) && fechaFinVar.after(fechaIniClase)) {
			return true;
		}else if((fechaInicioVar.before(fechaIniClase)) && fechaFinVar.equals(fechaIniClase)) {
			return true;
		}
		else {
			return false;
		} 
	}

//	private boolean validarRegistroExistentes(Modalidad modalidadVar, List<Acuerdo> acuerdoVar, TipoPrograma tipoProgVar, ProgramaEducativo tipoProyectoVar,
//			Date fechaInicioVar, Date fechaFinVar, int a,String nombreNuevo) {
	private boolean validarRegistroExistentes(Modalidad modalidadVar, List<Acuerdo> acuerdoVar, TipoPrograma tipoProgVar,
			Date fechaInicioVar, Date fechaFinVar, int a,String nombreNuevo) {

		List<Boolean> validador = new ArrayList<>();
		List<Boolean> validadorNombre = new ArrayList<>();
	
		boolean registroRepetido = true;
		boolean registroNuevoProyecto = false;
		
		
		validador.add(true);
		validador.add(false);

		if(listaProgramaEbjaActivos.isEmpty()) {
			registroRepetido = false;
		}else {
			int tmpNumeroAcuerdos=0;
			for(ProgramaEbja tmpListaProgramaEbja :listaProgramaEbjaActivos) {
				
				//if(tmpListaProgramaEbja.getProgramaEducativo().getNemonico().contentEquals(tipoProyectoVar.getNemonico())) {
				//	if(tmpListaProgramaEbja.getProgramaEducativo().getNemonico().contentEquals(tipoProyectoVar.getNemonico())) {
						
					if(tmpListaProgramaEbja.getNombre().contentEquals(nombreNuevo)) {						
						validadorNombre.add(true);
						for (Acuerdo var : tmpListaProgramaEbja.getAcuerdos()) {
							tmpNumeroAcuerdos++;
							if (validarAcuerdoLista(var,acuerdoVar)
									&& tmpListaProgramaEbja.getModalidad().getNemonico().contentEquals(modalidadVar.getNemonico())
									&& tmpListaProgramaEbja.getTipoPrograma().getNemonico().contentEquals(tipoProgVar.getNemonico())) {
								
								if (fechaInicioVar.before(tmpListaProgramaEbja.getFechaInicio())
										&& fechaInicioVar.before(tmpListaProgramaEbja.getFechaFin())
										&& fechaFinVar.before(tmpListaProgramaEbja.getFechaInicio())
										&& fechaFinVar.before(tmpListaProgramaEbja.getFechaFin())) {

									// registroRepetido = false;
									validador.add(false);

								} else if (fechaInicioVar.after(tmpListaProgramaEbja.getFechaInicio())
										&& fechaInicioVar.after(tmpListaProgramaEbja.getFechaFin())
										&& fechaFinVar.after(tmpListaProgramaEbja.getFechaInicio())
										&& fechaFinVar.after(tmpListaProgramaEbja.getFechaFin())) {

									// registroRepetido = false;
									validador.add(false);

								} else if (fechaFinVar.compareTo(tmpListaProgramaEbja.getFechaFin()) == 0
										&& fechaInicioVar.compareTo(tmpListaProgramaEbja.getFechaInicio()) == 0) {

									// registroRepetido = true;
									validador.add(true);
								}

								else {
									// registroRepetido = true;
									validador.add(true);
								}

							} else {
								// registroRepetido = false;
								validador.add(false);
							}
						}
						
						}
					
//					   else {
//							validadorNombre.add(true);
//							//registroNuevoNombre = true;
//						}
						
						if (tmpListaProgramaEbja.getAcuerdos().isEmpty()) {
							// registroRepetido = false;
							validador.add(false);
						}
						
//					}else {
//						// crear de una porque es nuevo
//						registroNuevoProyecto = true;
//					}
			
//				}else {
//					validadorProyecto.add(true);
//				}
			}

			Map<String, Long> couterMap = validador.stream()
					.collect(Collectors.groupingBy(e -> e.toString(), Collectors.counting()));
			
		    
			if(registroNuevoProyecto) {
				registroRepetido = false;
			}else if(validadorNombre.isEmpty()) {
				registroRepetido = false;
			}else 		
			{				
			// mas de dos entra aca menos de dos aca 					
		    if (couterMap.get("true") > 1 && a == 1) {	// creacion
				registroRepetido = true;
			} else if (couterMap.get("true") == tmpNumeroAcuerdos + 1 && a == 2) { //edicion
				registroRepetido = false;
			}else if (couterMap.get("true") > tmpNumeroAcuerdos + 2 && a == 2) { //edicion
				registroRepetido = true;
			}
		    else if (couterMap.get("false") > 1) {
				registroRepetido = false;
			} else {
				registroRepetido = true;
			}
		    
			}
		}
		
		return registroRepetido;
	}
	
	public boolean validarAcuerdoLista(Acuerdo tmpAcuerdo, List<Acuerdo> tmpListaAcuerdo) {
		
		boolean tmpbanderaAcuerdo = false;
		for(Acuerdo acuerdo: tmpListaAcuerdo) {
			if(acuerdo.getNemonico().contentEquals(tmpAcuerdo.getNemonico())) {
				tmpbanderaAcuerdo = true;
				break;
			}
		}
		return tmpbanderaAcuerdo;
	}

	public void editarModulo() {
		try {
			
			modulo.setNombre(nombre);
			modulo.setNemonico(nemonico);
			modulo.setEdadMinima(edadMinima);
			modulo.setRezagoMinimo(regazoMinimo);
			modulo.setFechaInicio(new Timestamp(fechaInicio.getTime()));
			modulo.setFechaFin(new Timestamp(fechaFin.getTime()));
			modulo.setFechaInicioClases(new Timestamp(fechaInicioClases.getTime()));
			modulo.setModalidad((Modalidad) (recursoModalidad.findByCodigo(modalidadSeleccionada).get(0)));
			modulo.setTipoPrograma(tipoPrograma.ObtenerPrograma(tipoProgramaSeleccionado));
			//modulo.setProgramaEducativo(programasEducativos.findByCodigoSoloProgramaEducativo(tipoProyectoSeleccionado));
			modulo.setCobertura(String.valueOf(getCoberturaExtrajera()));
			modulo.setAcuerdos(new HashSet(listaAcuerdosAnadir));
			
			if (!validarRegistroExistentes((Modalidad) (recursoModalidad.findByCodigo(modalidadSeleccionada).get(0)), 
					modulo.getAcuerdos(),
					tipoPrograma.ObtenerPrograma(tipoProgramaSeleccionado),
					fechaInicio,
					fechaFin,2,nombre)) {
				
				if(validarFechaInicioClases (fechaInicio,fechaFin,fechaInicioClases)) {
					
					if(listaAcuerdosAnadir.isEmpty()) {						
						FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede editar porque debe existir al menos un acuerdo en el registro", ""));	
					}else {
						modulos.edit(modulo);
						FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
								new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));
					}
					
				}else {
					FacesContext.getCurrentInstance().addMessage("frmForm:messageRegla", new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "No se puede editar el registro. La fecha de inicio de clases debe estar dentro del periodo de la Oferta", ""));
				}
				
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No se puede editar, ya existe un registro con mismo Acuerdo, Modalidad y Periodo", ""));
			}
			
			
			
			
		} catch (Exception ex) {

			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messagePagMensaje",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
			
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}
		}
	}

	public void borrarModuloLogico() {

		if (moduloInactivar.getEstado().contentEquals("0")) {
			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));

		}else if(!programaGrado.buscarDependenciaPorCodigoGrado(moduloInactivar.getNemonico())) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageModulo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se puede inactivar el registro, porque existen datos relacionados en Grados", ""));
			
		}else {
			moduloInactivar.setEstado("0");
			modulos.edit(moduloInactivar);
			buscarModulo();
		}

	}

	public void borrarAcuerdoFisico() {
		modulos.remove(modulo);
		buscarModulo();
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public int getBusquedaTipo() {
		return busquedaTipo;
	}

	public void setBusquedaTipo(int busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}

	public List<Object[]> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<Object[]> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public String getBusquedaContenido() {
		return busquedaContenido;
	}

	public void setBusquedaContenido(String busquedaContenido) {
		this.busquedaContenido = busquedaContenido;
	}

	public int getCrearOeditar() {
		return crearOeditar;
	}

	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
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

	public Acuerdo getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(Acuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	public List<Acuerdo> getListaAcuerdos() {
		return listaAcuerdos;
	}

	public void setListaAcuerdos(List<Acuerdo> listaAcuerdos) {
		this.listaAcuerdos = listaAcuerdos;
	}

	public Integer getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(Integer edadMinima) {
		this.edadMinima = edadMinima;
	}

	public Integer getRegazoMinimo() {
		return regazoMinimo;
	}

	public void setRegazoMinimo(Integer regazoMinimo) {
		this.regazoMinimo = regazoMinimo;
	}

	public String getAcuerdoSeleccionado() {
		return acuerdoSeleccionado;
	}

	public void setAcuerdoSeleccionado(String acuerdoSeleccionado) {
		this.acuerdoSeleccionado = acuerdoSeleccionado;
	}

	public String getModalidadSeleccionada() {
		return modalidadSeleccionada;
	}

	public void setModalidadSeleccionada(String modalidadSeleccionada) {
		this.modalidadSeleccionada = modalidadSeleccionada;
	}

	public List<Modalidad> getListaModalidad() {
		return listaModalidad;
	}

	public void setListaModalidad(List<Modalidad> listaModalidad) {
		this.listaModalidad = listaModalidad;
	}

	public int getCoberturaExtrajera() {
		return coberturaExtrajera;
	}

	public void setCoberturaExtrajera(int coberturaExtrajera) {
		this.coberturaExtrajera = coberturaExtrajera;
	}

	public List<OpcionSiNo> getListaSiNO() {
		return listaSiNO;
	}

	public void setListaSiNO(List<OpcionSiNo> listaSiNO) {
		this.listaSiNO = listaSiNO;
	}

	public List<TipoPrograma> getListaTipoProgramas() {
		return listaTipoProgramas;
	}

	public void setListaTipoProgramas(List<TipoPrograma> listaTipoProgramas) {
		this.listaTipoProgramas = listaTipoProgramas;
	}

	public String getTipoProgramaSeleccionado() {
		return tipoProgramaSeleccionado;
	}

	public void setTipoProgramaSeleccionado(String tipoProgramaSeleccionado) {
		this.tipoProgramaSeleccionado = tipoProgramaSeleccionado;
	}

	public Date getFechaInicioClases() {
		return fechaInicioClases;
	}

	public void setFechaInicioClases(Date fechaInicioClases) {
		this.fechaInicioClases = fechaInicioClases;
	}

	public List<Acuerdo> getListaAcuerdosAnadir() {
		return listaAcuerdosAnadir;
	}

	public void setListaAcuerdosAnadir(List<Acuerdo> listaAcuerdosAnadir) {
		this.listaAcuerdosAnadir = listaAcuerdosAnadir;
	}

	public List<Acuerdo> getListaAcuerdosAnadirEditar() {
		return listaAcuerdosAnadirEditar;
	}

	public void setListaAcuerdosAnadirEditar(List<Acuerdo> listaAcuerdosAnadirEditar) {
		this.listaAcuerdosAnadirEditar = listaAcuerdosAnadirEditar;
	}

	public ProgramaEbja getModuloInactivar() {
		return moduloInactivar;
	}

	public void setModuloInactivar(ProgramaEbja moduloInactivar) {
		this.moduloInactivar = moduloInactivar;
	}

	public ProgramaEbja getModulo() {
		return modulo;
	}

	public void setModulo(ProgramaEbja modulo) {
		this.modulo = modulo;
	}

	public List<ProgramaEducativo> getListaProgramaEducativo() {
		return listaProgramaEducativo;
	}

	public void setListaProgramaEducativo(List<ProgramaEducativo> listaProgramaEducativo) {
		this.listaProgramaEducativo = listaProgramaEducativo;
	}

	public String getTipoProyectoSeleccionado() {
		return tipoProyectoSeleccionado;
	}

	public void setTipoProyectoSeleccionado(String tipoProyectoSeleccionado) {
		this.tipoProyectoSeleccionado = tipoProyectoSeleccionado;
	}

	public ProgramaEbja getModuloEliminar() {
		return moduloEliminar;
	}

	public void setModuloEliminar(ProgramaEbja moduloEliminar) {
		this.moduloEliminar = moduloEliminar;
	}
	
	

}
