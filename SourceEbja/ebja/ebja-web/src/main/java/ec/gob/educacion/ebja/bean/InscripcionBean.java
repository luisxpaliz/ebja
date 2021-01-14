package ec.gob.educacion.ebja.bean;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.dto.RegistroEstudianteDTO;
import ec.gob.educacion.ebja.facade.local.CantonFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CatalogoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CeduladoMeducacionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.CircuitoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DatoFamiliarFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DiscapacidadFacadeLocal;
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InsParametroFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InscripcionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.InstitucEstablecFacadeLocal;
import ec.gob.educacion.ebja.facade.local.MensajeFacadeLocal;
import ec.gob.educacion.ebja.facade.local.PaisFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ParroquiaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ProvinciaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.facade.local.SuministroLuzFacadeLocal;
import ec.gob.educacion.ebja.facade.local.UbicacionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;
import ec.gob.educacion.ebja.modelo.DatoFamiliar;
import ec.gob.educacion.ebja.modelo.Discapacidad;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.Mensaje;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import ec.gob.educacion.ebja.modelo.asignaciones.CeduladoMeducacion;
import ec.gob.educacion.ebja.modelo.asignaciones.Nacionalidad;
import ec.gob.educacion.ebja.modelo.asignaciones.SuministroLuz;
import ec.gob.educacion.ebja.modelo.zeus.Canton;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;
import ec.gob.educacion.ebja.modelo.zeus.Parroquia;
import ec.gob.educacion.ebja.modelo.zeus.Provincia;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.modelo.Pais;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import ec.gob.educacion.ebja.recursos.CedulaValidator;
import ec.gob.educacion.ebja.recursos.EmailValidator;
import ec.gob.educacion.ebja.recursos.MailUtil;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.richfaces.application.ServiceTracker;
import org.richfaces.focus.FocusManager;

@ManagedBean
@ViewScoped
public class InscripcionBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private CeduladoMeducacionFacadeLocal ceduladoMeducacionFacadeLocal;
	@EJB
	private RegistroEstudianteFacadeLocal registroEstudianteFacadeLocal;
	@EJB
	private InscripcionFacadeLocal inscripcionFacadeLocal;
	@EJB
	private SuministroLuzFacadeLocal suministroLuzFacadeLocal;
	@EJB
	private ParroquiaFacadeLocal parroquiaFacadeLocal;
	@EJB
	private CantonFacadeLocal cantonFacadeLocal;
	@EJB
	private ProvinciaFacadeLocal provinciaFacadeLocal;
	@EJB
	private InsParametroFacadeLocal insParametroFacadeLocal;
	@EJB
	private CatalogoEbjaFacadeLocal catalogoEbjaFacadeLocal;
	@EJB
	private ZonaFacadeLocal zonaFacadeLocal;
	@EJB
	private DistritoFacadeLocal distritoFacadeLocal;
	@EJB
	private CircuitoFacadeLocal circuitoFacadeLocal;
	@EJB
	private UbicacionFacadeLocal ubicacionFacadeLocal;
	@EJB
	private CatalogoFacadeLocal catalogoFacadeLocal;
	@EJB
	private GradoFacadeLocal gradoFacadeLocal;
	@EJB
	private PaisFacadeLocal paisFacadeLocal;
	@EJB
	private DatoFamiliarFacadeLocal datoFamiliarFacadeLocal;
	@EJB
	private DiscapacidadFacadeLocal discapacidadFacadeLocal;
	@EJB
	private ProgramaEbjaFacadeLocal programaEbjaFacadeLocal;
	@EJB
	private ReglaNegocioFacadeLocal reglaNegocioFacadeLocal;
	@EJB
	private MensajeFacadeLocal mensajeFacadeLocal;
	@EJB
	private ProgramaEducativoFacadeLocal programaEducativoFacadeLocal;
	@EJB
	private GrupoFaseFacadeLocal grupoFaseProgramaFacadeLocal;
	@EJB
	private ProgramaGradoFacadeLocal programaGradoFacadeLocal;
	@EJB
	private InstitucEstablecFacadeLocal institucionEstablecimiento;

	private boolean visiblePanelA;
	private boolean visiblePanelB;
	private boolean visiblePanelC;
	private boolean visiblePanelD;
	private boolean visibleEstado;
	private boolean visibleZona;
	private boolean visibleZonaDetalle;
	private boolean visibleBotonEnviar;
	private boolean visiblePgParentesco;
	private boolean visiblePgNacionalidadIndigena;
	private boolean visiblePgDocumentoPresentado;
	private boolean visiblePgDocumentoMotivo;
	private boolean visiblePgCargarPDF;
	private boolean visibleMensajeDH;
	private boolean visibleTieneDH;
	private boolean visibleClVerFormulario;
	private boolean visibleUbicacionCUE;
	private boolean visibleNacionalidad;
	private boolean visibleCaracteristicasOE;
	private boolean visiblePanelCUE;
	private boolean visibleEdadAspirante;

	private boolean disabledPanelB;
	private boolean disabledIdentificacionAspirante;
	private boolean disabledIngresarIdentificacion;
	private boolean disabledCmbProvincia;
	private boolean disabledCmbCanton;
	private boolean disabledCmbParroquia;
	private boolean disabledItDireccion;
	private boolean disabledItBusquedaPromocion;
	private boolean disabledCmbEspecialidad;
	private boolean disabledCmbPais;
	private boolean disabledCmbEstadoUsa;
	private boolean disabledCmbCiudadUsa;
	private boolean disabledItCalle;
	private boolean disabledItCodigoPostal;
	private boolean disabledQuinceAniosOMas;
	private boolean disabledRezagoEducativo;
	private boolean disabledTipoDocumento;
	private boolean disabledIngresarDatosAdicionales;
	private boolean disabledUbicacionGeografica;
	private boolean disabledInformacionRegistro;
	private boolean disabledGenero;
	private boolean disabledAceptarInformacion;
	private boolean disabledEstadoCivil;
	private boolean disabledEtnia;
	private boolean disabledSituacionLaboral;
	private boolean disabledActividadEconomica;
	private boolean disabledDatosFamiliares;
	private boolean disabledDiscapacidad;
	private boolean disabledBono;
	private boolean disabledNacionalidad;
	private boolean disabledTelefono;
	private boolean disabledEmail;
	private boolean disabledUltimoAnioAprobado;
	private boolean disabledRegistroEstudiante;
	private boolean disabledRezagoEducativo1;
	private boolean disabledCumpleDocumentacion;
	private boolean disabledParentesco;
	private boolean disabledTiempoEstudio;
	private boolean disabledNacionalidadIndigena;
	private boolean disabledDocumentoHabilitante;
	private boolean disabledDocumentoPresentado;
	private boolean disabledCmbDocumentoPresentado;
	private boolean disabledDocumentoMotivoI;
	private boolean disabledCmbDocumentoMotivo;
	private boolean readonlyNumeroIdentificacion;
	private boolean readonlyCue;
	private boolean readonlyApellidosNombres;
	private boolean readonlyUbicacionCUE;
	private boolean readonlyFechaNacimiento;
	private boolean readonlyApellidosNombresRegistra;
	private boolean readonlyNumeroIdentificacionRegistra;
	private boolean renderedMensajeGuardar;
	private boolean numeroHijos;
	private boolean errorIngresarDatoFamiliar;
	private boolean errorIngresarDatoDiscapacidad;
	private boolean visibleCbReiniciar;
	private boolean disabledPgCargarPDF;
	private boolean visibleNumeroIdentificacion;
	private boolean disabledGradoOfertaAprobadoCmb = false;
	private boolean procesarInscripcionPendiente;
	private boolean existeInscripcionPendiente;
	private boolean existeUbicacion;
	private boolean disabledFechaNacimiento;
	private boolean disabledBtnAceptarOferta;
	private String nombreTipoDocumento;
	private String nombreEtnia;
	private String nombrePais;
	private String nombreGenero;
	private String cedulaBuscar;
	private String cueBuscar;
	private String desTipoDocumento;
	private String textosalida;
	private String textosalidaPanelA;
	private String textoFormularioSatisfactorio;
	private String tieneDiscapacidad;
	private String tieneBono;
	private String tieneCUE;
	private String cobertura;
	private String desEstadoCivil = "";
	private String tituloPagina;
	private String rutaArchivo = "";
	private String mensajeContinuar = "";
	private String programaOfertaSeleccionado;
	private String programaOfertaInscripcion;
	private String urlCorreo;
	private String mensajeEnvioCorreo;
	private boolean disabledBotonEnviarCorreo;
	private boolean requiredEmail;

	private Integer gradoOfertaSeleccionadoInscripcion = 0;
	private Integer gradoOfertaSeleccionado = 0;
	private int tieneQuinceAniosOMas;
	private int cumpleDocumentacion;
	private int tieneRezagoEducativo;
	private int idTipoDocumento;
	private int edadAspirante;
	private int codGenero;
	private int codEstadoCivil;
	private int edadMinima;
	private long proyectoSelecionado;
	private long faseProgramaSeleccionado;
	private long faseProgramaSeleccionadoUltimo;
	private Integer secuencialInscripcion;
	private Integer gradoSucesor;
	private Date fechaActual = new Date();
	private Provincia provincia;
	private Canton canton;
	private Parroquia parroquia;
	private Zona zona;
	private Distrito distrito;
	private Circuito circuito;
	private RegistroEstudiante registroEstudiante;
	private RegistroEstudiante registroEstudianteEdit;
	private Inscripcion inscripcion;
	private Inscripcion inscripcionEdit;
	private Ubicacion ubicacion;
	private Ubicacion ubicacionEdit;
	private DatoFamiliar datoFamiliar;
	private Discapacidad discapacidad;
	private ProgramaEbja programaEbja;
	private ReglaNegocio reglaNegocio;
	private Mensaje mensaje;
	private CeduladoMeducacion ceduladoMeducacion;
	private SuministroLuz suministroLuz;
	private Nacionalidad nacionalidad;
	private RegistroEstudianteDTO registroEstudianteDTO;
	private List<Pais> listaPaisUbicacion = new ArrayList<Pais>();
	private List<Pais> listaPais;
	private List<Provincia> listaProvincia = new ArrayList<>();
	private List<Canton> listaCanton = new ArrayList<>();
	private List<Parroquia> listaParroquia = new ArrayList<>();
	private List<Zona> listaZona = new ArrayList<>();
	private List<Distrito> listaDistrito = new ArrayList<>();
	private List<Circuito> listaCircuito = new ArrayList<>();

	// Catalogo zeus
	private List<Catalogo> listaTipoDocumento;
	private List<Catalogo> listaGenero;
	private List<Catalogo> listaEstadoCivil;
	private List<Catalogo> listaNacionalidadIndigena;

	// Catalogo zeus-ebja
	private List<CatalogoEbja> listaActividadEconomica;
	private List<CatalogoEbja> listaSituacionLaboral;
	private List<CatalogoEbja> listaDatosFamiliares;
	private List<CatalogoEbja> listaDiscapacidadCatalogo;
	private List<CatalogoEbja> listaRezagoEducativo;
	private List<CatalogoEbja> listaDocumentoPresentado;
	private List<CatalogoEbja> listaDocumentoMotivo;
	private List<Discapacidad> listaDiscapacidad;
	private List<ProgramaEducativo> listaProgramasEducativos;

	private List<Etnia> listaEtnia;
	private List<Grado> listaGrado;
	private List<Grado> listaGradoInscripcion;
	private List<ProgramaEbja> listaProgramaEbja;
	private List<ProgramaEbja> listaProgramaEbjaInscripcion;
	private List<ProgramaEbja> listaProgramaEbjaAprobado;
	private List<GrupoFasePrograma> listaGradoFasePrograma;
	private List<GrupoFasePrograma> listaGradoFaseProgramaUltimaAprobado;
	private List<InstitucEstablec> listaEstablecimientos;
	private String nemonicoGrupoFaseProg;
	private Integer idInstitucion;

	private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	private static final Logger LOGGER = Logger.getLogger(InscripcionBean.class.getName());
	private Date ahoraTmp;

	public InscripcionBean() {
	}

	@PostConstruct
	public void init() {
		// Cargar Catalogos.
		cargarCatalogos();
		// Inicializar objetos.
		inicializarObjetos();
	}

	public void inicializarObjetos() {

		ceduladoMeducacion = new CeduladoMeducacion();
		provincia = new Provincia();
		canton = new Canton();
		parroquia = new Parroquia();
		zona = new Zona();
		distrito = new Distrito();
		circuito = new Circuito();
		ubicacion = new Ubicacion();
		ubicacion.setPais(new Pais());

		// Objetos temporales para edicion
		inscripcionEdit = new Inscripcion();
		registroEstudianteEdit = new RegistroEstudiante();
		ubicacionEdit = new Ubicacion();

		registroEstudiante = new RegistroEstudiante();
		registroEstudiante.setCatalogoActividadEconomica(new CatalogoEbja());
		registroEstudiante.setCatalogoEstadoCivil(new Catalogo());
		registroEstudiante.setCatalogoGenero(new Catalogo());
		registroEstudiante.setCatalogoNacionalidad(new Catalogo());
		registroEstudiante.setCatalogoSituacionLaboral(new CatalogoEbja());
		registroEstudiante.setCatalogoTipoIdentificacion(new Catalogo());
		registroEstudiante.setPais(new Pais());
		registroEstudiante.setEtnia(new Etnia());
		registroEstudiante.setCatalogoDocumento(new CatalogoEbja());
		registroEstudiante.setCatalogoMotivo(new CatalogoEbja());
		registroEstudianteDTO = new RegistroEstudianteDTO();

		inscripcion = new Inscripcion();
		inscripcion.setCatalogoRezago(new CatalogoEbja());
		inscripcion.setGradoAprobado(new Grado());

		inscripcion.setProgramaGrado(new ProgramaGrado());

		listaProgramaEbjaInscripcion = new ArrayList<>();
		
		

		visiblePanelA = false;
		visiblePanelB = false;
		visiblePanelC = false;
		visiblePanelD = false;
		visibleBotonEnviar = false;
		tieneQuinceAniosOMas = 0;
		tieneRezagoEducativo = 0;
		cumpleDocumentacion = 0;
		disabledIdentificacionAspirante = true;
		visiblePanelCUE = false;
		readonlyNumeroIdentificacion = false;
		readonlyCue = false;
		disabledItBusquedaPromocion = false;
		setDisabledCmbPais(false);
		setDisabledCmbEstadoUsa(false);
		setDisabledItCalle(false);
		setDisabledItCodigoPostal(false);
		setDisabledCmbCiudadUsa(false);
		setDisabledCmbProvincia(false);
		setDisabledCmbCanton(false);
		setDisabledCmbParroquia(false);
		setDisabledItDireccion(false);
		setDisabledCmbEspecialidad(false);
		disabledQuinceAniosOMas = false;
		disabledRezagoEducativo = true;
		disabledCumpleDocumentacion = true;
		idTipoDocumento = 0;
		desTipoDocumento = "";
		readonlyApellidosNombres = true;
		disabledTipoDocumento = false;
		readonlyFechaNacimiento = true;
		disabledIngresarIdentificacion = true;
		disabledIngresarDatosAdicionales = true;
		disabledUbicacionGeografica = true;
		readonlyApellidosNombresRegistra = false;
		readonlyNumeroIdentificacionRegistra = false;
		edadAspirante = 0;
		visibleEdadAspirante = false;
		codGenero = 0;
		codEstadoCivil = 0;

		disabledFechaNacimiento = false;
		disabledBtnAceptarOferta = false;
		disabledAceptarInformacion = false;
		disabledGenero = false;
		disabledEstadoCivil = false;
		disabledEtnia = false;
		disabledSituacionLaboral = false;
		disabledActividadEconomica = false;
		disabledDatosFamiliares = false;
		disabledDiscapacidad = false;
		disabledBono = false;
		disabledNacionalidad = false;
		setDisabledDocumentoHabilitante(false);
		disabledParentesco = false;
		disabledTiempoEstudio = false;
		disabledNacionalidadIndigena = false;
		disabledTelefono = false;
		disabledEmail = false;
		disabledUltimoAnioAprobado = false;
		disabledRegistroEstudiante = false;
		disabledRezagoEducativo1 = false;
		setRenderedMensajeGuardar(false);
		textosalida = "";
		textosalidaPanelA = "";
		disabledInformacionRegistro = false;
		textoFormularioSatisfactorio = "";
		nombreTipoDocumento = "";
		nombreEtnia = "";
		cedulaBuscar = null;
		visibleEstado = false;
		visibleZona = false;
		visibleZonaDetalle = false;
		setVisibleMensajeDH(false);
		visibleTieneDH = false;
		setVisiblePgParentesco(false);
		visiblePgNacionalidadIndigena = false;
		visiblePgDocumentoPresentado = false;
		visiblePgDocumentoMotivo = false;
		visiblePgCargarPDF = false;
		visibleClVerFormulario = false;
		visibleUbicacionCUE = false;
		readonlyUbicacionCUE = false;
		tieneCUE = "";
		listaProgramaEbja = new ArrayList<>();
		nombrePais = "";
		visibleNacionalidad = false;
		visibleCaracteristicasOE = false;
		cobertura = "";
		visibleCbReiniciar = false;
		disabledPgCargarPDF = false;
		visibleNumeroIdentificacion = true;
		procesarInscripcionPendiente = false;
		existeInscripcionPendiente = false;
		existeUbicacion = false;
		mensajeContinuar = "";
		disabledPanelB = false;
		disabledCmbDocumentoPresentado = false;
		disabledCmbDocumentoMotivo = false;
		proyectoSelecionado = 0;
		faseProgramaSeleccionado = 0;
		faseProgramaSeleccionadoUltimo = 0;
		programaOfertaSeleccionado = "";
		programaOfertaInscripcion = "";
		gradoOfertaSeleccionado = 0;
		gradoOfertaSeleccionadoInscripcion = 0;
		ahoraTmp = new Date();

	}

	public void cargarCatalogos() {

		/* Inicilizar los combos de proyecto, fase,oferta y grado */
		listaProgramasEducativos = new ArrayList<>();
		listaGradoFaseProgramaUltimaAprobado = new ArrayList<>();
		listaProgramaEbjaAprobado = new ArrayList<>();
		listaGrado = new ArrayList<>();
		listaGradoFasePrograma = new ArrayList<>();
		listaProgramaEbjaInscripcion = new ArrayList<>();
		listaGradoInscripcion = new ArrayList<>();

		// Obtener el título de la página.
		mensaje = mensajeFacadeLocal.obtenerTituloPagina(Constantes.NEMONICO_INSCRIPCION_FORMULARIO);

		if (mensaje != null) {
			tituloPagina = mensaje.getCabecera();
		} else {
			tituloPagina = Constantes.TITULO_PAGINA_INSCRIPCION;
		}

		// Obtener lista pais.
		listaPaisUbicacion = catalogoFacadeLocal.listaPaisUbicacion();

		// Obtener zona.
		listaZona = zonaFacadeLocal.findAll();

		// Obtener lista provincia.
		listaProvincia = provinciaFacadeLocal.findAll();

		// Obtener lista tipo documento.
		listaTipoDocumento = catalogoFacadeLocal.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_TIPO_DOCUMENTO);

		// Obtener lista genero.
		listaGenero = catalogoFacadeLocal.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_GENERO);

		// Obtener lista estado civil.
		listaEstadoCivil = catalogoFacadeLocal.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_ESTADO_CIVIL);

		// Obtener lista etnia.
		listaEtnia = catalogoFacadeLocal.listaEtnia();

		// Obtener lista nacionalidad indigena.
		listaNacionalidadIndigena = catalogoFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_NACIONALIDAD_INDIGENA);

		// Obtener lista actividad economica.
		listaActividadEconomica = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_ACTIVIDAD_ECONOMICA);

		// Obtener lista situacion laboral.
		listaSituacionLaboral = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_SITUACION_LABORAL);

		// Obtener lista etnia.
		listaPais = catalogoFacadeLocal.listaPais();

		// Obtener lista rezago educativo.
		listaRezagoEducativo = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_REZAGO_EDUCATIVO);

		// Obtener lista distrito.
		listaDistrito = distritoFacadeLocal.findAll();

		// Obtener lista circuito.
		listaCircuito = circuitoFacadeLocal.findAll();

		// Obtener lista documento presentado.
		listaDocumentoPresentado = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DOCUMENTO_PRESENTADO);

		// Obtener lista documento motivo.
		listaDocumentoMotivo = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DOCUMENTO_MOTIVO);

		// Obtener lista Programas Educativos.
		listaProgramasEducativos = programaEducativoFacadeLocal.buscarTodosProgramaEducativoActivos();

	}

	public void obtenerFasesProyectos() {
		
		/* Limpiar listas de periodo, oferta y grado */
		faseProgramaSeleccionado = 0;
		programaOfertaSeleccionado = "";
		gradoOfertaSeleccionado = 0;
		programaOfertaInscripcion = "";
		gradoOfertaSeleccionadoInscripcion = 0;
		faseProgramaSeleccionadoUltimo = 0;

		listaGradoFaseProgramaUltimaAprobado.clear();
		listaProgramaEbjaAprobado.clear();
		listaGrado.clear();
		listaGradoFasePrograma.clear();
		listaProgramaEbjaInscripcion.clear();
		listaGradoInscripcion.clear();

		listaGradoFasePrograma = grupoFaseProgramaFacadeLocal.buscarTodosGrupoFaseProgramaActivos(proyectoSelecionado);
	}

	public void obtenerFasesProyectosUltimoAprobado() {

		/* Limpiar listas de periodo, oferta y grado */
		faseProgramaSeleccionado = 0;
		programaOfertaSeleccionado = "";
		gradoOfertaSeleccionado = 0;
		programaOfertaInscripcion = "";
		gradoOfertaSeleccionadoInscripcion = 0;
		

		listaGradoFaseProgramaUltimaAprobado.clear();
		listaProgramaEbjaAprobado.clear();
		listaGrado.clear();
		listaProgramaEbjaInscripcion.clear();
		listaGradoInscripcion.clear();

       listaGradoFaseProgramaUltimaAprobado = grupoFaseProgramaFacadeLocal
				.buscarTodosGrupoFaseProgramaActivosExternos(proyectoSelecionado);
		

	}

	public void obtenerOfertaAprobada() {
	
		/* Limpiar listas de periodo, oferta y grado */
		
		nemonicoGrupoFaseProg ="";
		programaOfertaSeleccionado = "";
		gradoOfertaSeleccionado = 0;
		programaOfertaInscripcion = "";
		gradoOfertaSeleccionadoInscripcion = 0;
		
		listaProgramaEbjaAprobado.clear();
		listaGrado.clear();
		listaProgramaEbjaInscripcion.clear();
		listaGradoInscripcion.clear();

		//listaProgramaEbjaAprobado = programaEbjaFacadeLocal.obtenerProgramaEbjaGrupoFase(faseProgramaSeleccionadoUltimo,faseProgramaSeleccionado);
		
		nemonicoGrupoFaseProg = grupoFaseProgramaFacadeLocal.find(faseProgramaSeleccionado).getNemonico();

		if(nemonicoGrupoFaseProg.contains("NING")|| (nemonicoGrupoFaseProg.contains("ORD")&&!nemonicoGrupoFaseProg.contains("EXT"))) {
			//si es ninguno			
			listaProgramaEbjaAprobado = programaEbjaFacadeLocal.obtenerProgramaEbjaNinguno();
		}else if(nemonicoGrupoFaseProg.contains("EXT")){
			// si es ext
			listaProgramaEbjaAprobado = programaEbjaFacadeLocal.obtenerProgramaEbjaGrupoFaseExtraordinaria(faseProgramaSeleccionadoUltimo); 
		}

	}

	public void obtenerOferta() {

		listaProgramaEbjaInscripcion = programaEbjaFacadeLocal
				.obtenerProgramaEbjaGrupoFase(faseProgramaSeleccionadoUltimo,faseProgramaSeleccionadoUltimo);
		gradoOfertaSeleccionado = 0;
		gradoOfertaSeleccionadoInscripcion = 0;
		programaOfertaInscripcion = "";
		programaOfertaSeleccionado = "";

	}

	public void buscarOfertaGrado() {
		gradoOfertaSeleccionadoInscripcion = 0;
		programaOfertaInscripcion = "";
		listaProgramaEbjaInscripcion.clear();
		listaGradoInscripcion.clear();
		ProgramaEbja progEbjaTemp = null;
		
		//Si no tiene ninguna instruccion
		if(gradoOfertaSeleccionado==50) { //eligio ninguna oferta aprobada y ningun grado
			
			List<ProgramaEbja> listaProgramaEbjaInscripcionTemp = programaGradoFacadeLocal
					.buscarPrimerProgramaPorPeriodo(faseProgramaSeleccionadoUltimo);
			progEbjaTemp = (listaProgramaEbjaInscripcionTemp.isEmpty() == false)
					? listaProgramaEbjaInscripcionTemp.get(0)
					: null;	
			
		}else {
			List<ProgramaEbja> listaProgramaEbjaInscripcionTemp = programaGradoFacadeLocal
					.buscarProgramaEbjaPorGrado(faseProgramaSeleccionadoUltimo, gradoOfertaSeleccionado);
			progEbjaTemp = (listaProgramaEbjaInscripcionTemp.isEmpty() == false)
					? listaProgramaEbjaInscripcionTemp.get(0)
					: null;	
		}
		

		if (progEbjaTemp != null) {

			/*
			 * Entra por primera vez a Ebja Oferta NINGUNO Grado NINGUNO debe ir al inicio
			 * de la primera oferta y primer grado
			 */
			/* Resto Ofertas y Grados */
			if (programaGradoFacadeLocal.programaEbjaTieneAsignadoPackCurso(progEbjaTemp.getNemonico()) == 1) {
				/* Si es pack */
				Integer gradoTemp;
				
				if(gradoOfertaSeleccionado ==50) {// si es grado aprobado ninguno
					gradoTemp = programaGradoFacadeLocal
							.buscarProgramaGradoInicial(progEbjaTemp.getNemonico()).getGradoInicial();
				}else {
					gradoTemp = programaGradoFacadeLocal
							.buscarProgramaGrado(gradoOfertaSeleccionado, progEbjaTemp.getNemonico()).getGradoInicial();
				}
				
				
				/* Si es pack y si es grado es inicial =1 o =2 devolver oferta que selecciono */
				if (gradoTemp == 1 || gradoTemp == 2) { // grado = 1 y 2 programaGradoFacadeLocal.buscar

					listaProgramaEbjaInscripcion.add(progEbjaTemp);
					programaOfertaInscripcion = progEbjaTemp.getNemonico();
					listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(progEbjaTemp.getNemonico());
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoPrimero(programaOfertaInscripcion)).getId();

				} else { // grado =3

					/*
					 * Busco el programa que tiene un unico grado dentro de una fase que contiene
					 * una oferta
					 */
					ProgramaEbja progEbjaInscripTemp = programaGradoFacadeLocal.buscarProgramaEbjaSiguienteInscripcion(
							faseProgramaSeleccionadoUltimo, progEbjaTemp.getId());
					listaProgramaEbjaInscripcion.add(progEbjaInscripTemp);
					programaOfertaInscripcion = progEbjaInscripTemp.getNemonico();
					/* Obtengo la Oferta de inscripcion de la fase actual y se busca el grado */
					listaGradoInscripcion = programaGradoFacadeLocal
							.buscarGradoUnificados(progEbjaInscripTemp.getNemonico());
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoPrimero(programaOfertaInscripcion)).getId();
				}

			} else {

				/*
				 * Busco el programa que tiene un unico grado dentro de una fase que contiene
				 * una oferta
				 */

				listaProgramaEbjaInscripcion.add(progEbjaTemp);
				programaOfertaInscripcion = progEbjaTemp.getNemonico();
				listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
				ProgramaGrado tmpProgramaGrado = programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionado,
						programaOfertaInscripcion);
				Integer grado = tmpProgramaGrado.getGradoInicial();

				// grado inicial = 1 grado intermedio = 2
				if (grado == 1 || grado == 2) {

					// buscar siguiente grado con la misma oferta.
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoSiguienteOferta(programaOfertaInscripcion, gradoOfertaSeleccionado)).getId();

					// grado final = 3
				} else {

					// buscar primer grado de la siguiente oferta
					/* Obtengo la Oferta de inscripcion de la fase actual y se busca el grado */

					ProgramaEbja progEbjaInscripTemp = programaGradoFacadeLocal.buscarProgramaEbjaSiguienteInscripcion(
							faseProgramaSeleccionadoUltimo, progEbjaTemp.getId());
					listaProgramaEbjaInscripcion = new ArrayList<>();
					/* Si no hay siguiente programa se termina el flujo */
					if (progEbjaInscripTemp != null) {

						listaProgramaEbjaInscripcion.add(progEbjaInscripTemp);
						programaOfertaInscripcion = progEbjaInscripTemp.getNemonico();
						/* Obtengo la Oferta de inscripcion de la fase actual y se busca el grado */
						listaGradoInscripcion = programaGradoFacadeLocal
								.buscarGradoUnificados(progEbjaInscripTemp.getNemonico());
						gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
								.buscarGradoPrimero(programaOfertaInscripcion)).getId();
					} else {
						programaOfertaInscripcion = "";
						listaGradoInscripcion = new ArrayList<>();
						gradoOfertaSeleccionadoInscripcion = 0;
					}

				}
			}
		}

	}

	/* Deprecado */
	public void obtenerGradoPack() {

		gradoOfertaSeleccionado = 0;
		gradoOfertaSeleccionadoInscripcion = 0;
		programaOfertaInscripcion = "";
		listaGrado = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaSeleccionado);

		if (programaGradoFacadeLocal.programaEbjaTieneAsignadoPackCurso(programaOfertaSeleccionado) == 1) {
			listaProgramaEbjaInscripcion = listaProgramaEbjaAprobado;
			gradoOfertaSeleccionado = ((Grado) programaGradoFacadeLocal.buscarGradoUltimo(programaOfertaSeleccionado))
					.getId();
			programaOfertaInscripcion = programaEbjaFacadeLocal
					.buscarPrograma(programaEbjaFacadeLocal.buscarSiguientePrograma(programaOfertaSeleccionado))
					.getNemonico();
			listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
			gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
					.buscarGradoPrimero(programaOfertaInscripcion)).getId();
			disabledGradoOfertaAprobadoCmb = true;
			setDisabledGradoOfertaAprobadoCmb(true);
		}

	}

	/* Deprecado */
	public void obtenerGradoSinPack() {

		gradoOfertaSeleccionadoInscripcion = 0;
		programaOfertaInscripcion = "";

		if (programaGradoFacadeLocal.programaEbjaTieneAsignadoPackCurso(programaOfertaSeleccionado) == 0) {

			if (grupoFaseProgramaFacadeLocal.buscarFaseExterna(faseProgramaSeleccionado) == 1) {

				List<ProgramaEbja> listProgTemp = programaGradoFacadeLocal
						.buscarProgramaEbjaPorGrado(faseProgramaSeleccionado, gradoOfertaSeleccionado);
				programaOfertaInscripcion = listProgTemp.get(0).getNemonico();
				listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
				ProgramaGrado tmpProgramaGrado = programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionado,
						programaOfertaInscripcion);
				Integer grado = tmpProgramaGrado.getGradoInicial();

				// grado inicial = 1 grado intermedio = 2
				if (grado == 1 || grado == 2) {

					// buscar siguiente grado con la misma oferta.
					listaProgramaEbjaInscripcion = listProgTemp;
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoSiguienteOferta(programaOfertaInscripcion, gradoOfertaSeleccionado)).getId();

					// grado final = 3
				} else {

					// buscar primer grado de la siguiente oferta
					Integer programa = programaEbjaFacadeLocal.buscarSiguientePrograma(programaOfertaInscripcion);
					listaProgramaEbjaInscripcion = new ArrayList<>();
					listaProgramaEbjaInscripcion.add(programaEbjaFacadeLocal.buscarPrograma(programa));
					programaOfertaInscripcion = programaEbjaFacadeLocal
							.buscarPrograma(programaEbjaFacadeLocal.buscarSiguientePrograma(programaOfertaInscripcion))
							.getNemonico();
					listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoPrimero(programaOfertaInscripcion)).getId();
				}

			} else {

				listaProgramaEbjaInscripcion = listaProgramaEbjaAprobado;
				ProgramaGrado tmpProgramaGrado = programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionado,
						programaOfertaSeleccionado);
				Integer grado = tmpProgramaGrado.getGradoInicial();

				// grado inicial = 1 grado intermedio = 2
				if (grado == 1 || grado == 2) {

					// buscar siguiente grado con la misma oferta.
					programaOfertaInscripcion = programaOfertaSeleccionado;
					listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaSeleccionado);
					gradoOfertaSeleccionadoInscripcion = tmpProgramaGrado.getSecuenciaGrado();

					// grado final = 3
				} else {

					// buscar primer grado de la siguiente oferta
					programaOfertaInscripcion = programaEbjaFacadeLocal
							.buscarPrograma(programaEbjaFacadeLocal.buscarSiguientePrograma(programaOfertaSeleccionado))
							.getNemonico();
					listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
					gradoOfertaSeleccionadoInscripcion = ((Grado) programaGradoFacadeLocal
							.buscarGradoPrimero(programaOfertaInscripcion)).getId();
				}
			}
		}
	}

	public void obtenerGradoInscripcion() {

		listaGradoInscripcion = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaInscripcion);
		gradoOfertaSeleccionadoInscripcion = 0;
	}

	public void obtenerGrado() {

		gradoOfertaSeleccionado = 0;
		programaOfertaInscripcion = "";
		gradoOfertaSeleccionadoInscripcion = 0;
		listaGrado.clear();
		listaProgramaEbjaInscripcion.clear();
		listaGradoInscripcion.clear();

		
		
		if(programaOfertaSeleccionado.contains("NING")) {
			
			if(nemonicoGrupoFaseProg.contains("NING"))  {
				// AÑADE NUNGUNO
				
				listaGrado = programaGradoFacadeLocal.buscarGradoNinguno();
				
			}else if(nemonicoGrupoFaseProg.contains("ORD") ){
				
				listaGrado = programaGradoFacadeLocal.buscarGradoUnificadosPorProgramaOrdinario(faseProgramaSeleccionadoUltimo);
			}
			
		}else{ // OFERTA EXTRAORDINARIA
			
			listaGrado = programaGradoFacadeLocal.buscarGradoUnificados(programaOfertaSeleccionado);
		}
		
		//verificar ninguno en sistema edu y ninguno oferta
		//pone ninguno en grado aprobado y elige el primero de la oferta inscripcion
		
		// ordinaria    ninguno    lista de intensivo costa de todos los grados
		
		// extraordinaria   lista ofertas lista de grados

	}

	public void aceptarQuinceAniosOMas(ValueChangeEvent evt) {
		try {
			tieneQuinceAniosOMas = ((Integer) evt.getNewValue()).intValue();
			if (tieneQuinceAniosOMas == 1) {
				disabledQuinceAniosOMas = true;
				disabledRezagoEducativo = false;
			}
		} catch (Exception localException) {
		}
	}

	public void aceptarRezagoEducativo(ValueChangeEvent evt) {
		try {
			tieneRezagoEducativo = ((Integer) evt.getNewValue()).intValue();
			if (tieneRezagoEducativo == 1) {
				disabledRezagoEducativo = true;
				disabledCumpleDocumentacion = false;

			}
		} catch (Exception localException) {
		}
	}

	public void aceptarCumpleDocumentacion(ValueChangeEvent evt) {
		try {
			cumpleDocumentacion = ((Integer) evt.getNewValue()).intValue();
			if (cumpleDocumentacion == 1) {
				disabledCumpleDocumentacion = true;
				visiblePanelA = true;
				visibleCbReiniciar = true;
			}
		} catch (Exception localException) {
		}

		FacesContext.getCurrentInstance().addMessage("frmForm:messageInicio",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.CAMPOS_OBLIGATORIOS, ""));
	}

	public void obtenerTipoDocumento() {

		nombreTipoDocumento = catalogoFacadeLocal.find(registroEstudiante.getCatalogoTipoIdentificacion().getId())
				.getNombre();

		if (nombreTipoDocumento.equals("C")) {
			disabledIdentificacionAspirante = false;
			readonlyApellidosNombres = true;
			readonlyFechaNacimiento = true;
			disabledIngresarIdentificacion = true;
		} else {
			disabledIdentificacionAspirante = true;
			readonlyApellidosNombres = false;
			readonlyFechaNacimiento = false;
			disabledIngresarIdentificacion = false;
		}

		registroEstudiante.setFechaNacimiento(null);
		readonlyNumeroIdentificacion = false;
		visibleEdadAspirante = false;
		visibleNumeroIdentificacion = true;

		switch (nombreTipoDocumento) {
		case "C":
			registroEstudiante.setFechaNacimiento(new Date());
			listaProgramasEducativos = (List<ProgramaEducativo>) programaEducativoFacadeLocal.buscarTodosProgramaEducativoActivos();
			break;
			
		case "N":
			readonlyNumeroIdentificacion = true;
			visibleNumeroIdentificacion = false;

			registroEstudiante.setNacionalidadEcuatoriana("1");
			visibleNacionalidad = false;
			disabledNacionalidad = false;
			disabledParentesco = false;
			registroEstudiante.setPais(new Pais());
			break;
		case "P":
		case "R":
			registroEstudiante.setNacionalidadEcuatoriana("0");
			visibleNacionalidad = true;
			disabledNacionalidad = true;
			disabledParentesco = false;
			break;
		case "S":
			listaProgramasEducativos = (List<ProgramaEducativo>) programaEducativoFacadeLocal.buscarProgramaCPL();
			registroEstudiante.setNacionalidadEcuatoriana("0");
			visibleNacionalidad = false;
			disabledNacionalidad = true;
			visiblePgParentesco = true;
			disabledParentesco = false;
			break;
		
		}

		cedulaBuscar = "";
		registroEstudiante.setApellidosNombres("");
		textosalidaPanelA = "";
	}

	public void buscarIdentificacionAspirante() {

		textosalidaPanelA = "";
		if (nombreTipoDocumento.equals("C")) {
			CedulaValidator numeroIdentificacion = new CedulaValidator(cedulaBuscar);
			if (!numeroIdentificacion.validate()) {
				textosalidaPanelA = Constantes.ERROR_NUMERO_CEDULA;
				return;
			}
		}
		ceduladoMeducacion = ceduladoMeducacionFacadeLocal.findCeduladoMeducacion(cedulaBuscar);
		
		
		if (ceduladoMeducacion != null) {
			
			if(ceduladoMeducacion.getFechaFallecimiento()==null) {
			
			// Validar si número de identificación, pertenece a la condición de fallecido.
			if (ceduladoMeducacion.getCodCondicionCedulado() != null && ceduladoMeducacion.getCodCondicionCedulado()
					.equals(new BigDecimal(Constantes.CONDICION_FALLECIDO_INT))) {
				textosalidaPanelA = Constantes.CONDICION_FALLECIDO_MSG;
				return;
			}

			// Obtener nacionalidad desde Registro Civil.
			nacionalidad = ceduladoMeducacionFacadeLocal
					.buscarNacionalidadPorCodigo(ceduladoMeducacion.getCodNacionalidad());
			if (nacionalidad != null) {
				disabledNacionalidad = true;
				if (nacionalidad.getDesNacionalidad().equals("ECUATORIANA")) {
					registroEstudiante.setNacionalidadEcuatoriana("1");
					visibleNacionalidad = false;
					registroEstudiante.setPais(new Pais());
				} else {
					registroEstudiante.setNacionalidadEcuatoriana("0");
					visibleNacionalidad = true;
				}
			}

			disabledIdentificacionAspirante = true;
			disabledTipoDocumento = true;
			readonlyNumeroIdentificacion = true;
			readonlyApellidosNombres = true;
			readonlyFechaNacimiento = true;

			registroEstudiante.setNumeroIdentificacion(cedulaBuscar);
			registroEstudiante.setApellidosNombres(ceduladoMeducacion.getNombres());
			registroEstudiante.setFechaNacimiento(ceduladoMeducacion.getFechaNacimiento());

			codGenero = ceduladoMeducacion.getCodSexo().intValue();
			descripcionGenero();
			disabledGenero = true;

			codEstadoCivil = ceduladoMeducacion.getCodEstadoCivil().intValue();
			descripcionEstadoCivil();
			disabledEstadoCivil = true;

			if (!calcularEdad()) {
				textosalidaPanelA = Constantes.ERROR_EDAD;
				visiblePanelB = false;
				disabledIngresarIdentificacion = true;
			}
			// Validar existencia del registro de inscripción pendiente.
			// (estadoAsignación=2)
			validarInscripcionPendiente();
			
           }else {
           textosalidaPanelA = Constantes.ERROR_INSCRIPCION_FALLECIDO;
		}
			
		} else {
			textosalidaPanelA = Constantes.ERROR_NUMERO_CEDULA;
		}
	}

	public void validarInscripcionPendiente() {

		// Buscar inscripción pendiente del aspirante.
		RegistroEstudiante registroEstudianteAux = new RegistroEstudiante();
		registroEstudianteAux = registroEstudianteFacadeLocal.buscarInscripcionPendiente(cedulaBuscar,
				registroEstudiante.getApellidosNombres());

		// Verificar si la inscripción del aspirante ya existe y si esta en estado
		// pendiente.
		if (registroEstudianteAux != null) {
			registroEstudiante = registroEstudianteAux;
			registroEstudiante.setEstadoAsignacion(
					registroEstudiante.getEstadoAsignacion() == null ? "0" : registroEstudiante.getEstadoAsignacion());
			cedulaBuscar = registroEstudiante.getNumeroIdentificacion();

			// Verificar si el registro esta en pendiente o ya inscrito.
			if (registroEstudiante.getEstadoAsignacion().equals("2")) {
				// Confirmar si se continua con la inscripción del aspirante o no.
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dConfirmarVerificarInscripcion').show();");
			} else {

				// Para proceso de actualizar inscripción. (I)
				if (registroEstudiante.getEstadoAsignacion().equals("1")) {
					visiblePanelB = false;
					textosalidaPanelA = "El aspirante se encuentra matriculado en la oferta educativa: "
							+ registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getNombre();
					FacesContext.getCurrentInstance().addMessage("frmForm:messageInicio",
							new FacesMessage(FacesMessage.SEVERITY_INFO, textosalidaPanelA, ""));
				} else {
					// Confirmar si se modifica los datos de la inscripción del aspirante o no.
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('dConfirmarModificarInscripcion').show();");
				}
				// Para proceso de actualizar inscripción. (F)
			}
		} else {
			visiblePanelB = true;
			// Obtener listas de datoFamiliar y Discapacidad sin Inscripción.
			obtenerDatosFamiliaresYDiscapacidadSinIP();
		}
	}

	public void validarInscripcionPendienteContinuar() {

		existeInscripcionPendiente = true;
		visiblePanelB = true;
		mensajeContinuar = "";

		if (registroEstudiante.getEstadoAsignacion().equals("0")) {
			// mensajeContinuar = Constantes.MENSAJE_CONTINUAR;
		}

		edadAspirante = registroEstudiante.getEdad() == null ? 0 : registroEstudiante.getEdad();
		if (registroEstudiante.getCatalogoNacionalidad() == null) {
			registroEstudiante.setCatalogoNacionalidad(new Catalogo());
		}

		if (registroEstudiante.getCatalogoMotivo() == null) {
			registroEstudiante.setCatalogoMotivo(new CatalogoEbja());
		}

		if (registroEstudiante.getCatalogoDocumento() == null) {
			registroEstudiante.setCatalogoDocumento(new CatalogoEbja());
		}

		if (registroEstudiante.getPais() == null) {
			registroEstudiante.setPais(new Pais());
		}

		if (registroEstudiante.getEtnia() == null) {
			registroEstudiante.setEtnia(new Etnia());
		}

		// Obtener listas de datoFamiliar y Discapacidad con Inscripción.
		obtenerDatosFamiliaresYDiscapacidadConIP();
		registroEstudianteEdit = registroEstudianteFacadeLocal.buscarInscripcionPendiente(cedulaBuscar,
				registroEstudiante.getApellidosNombres());
		inscripcionEdit = registroEstudianteEdit.getInscripcion();// almacena la inscripcion antes de la edición

		inscripcion = registroEstudiante.getInscripcion();
		cargarDatosInscripcion();

		if (inscripcion.getGradoAprobado() == null) {
			inscripcion.setGradoAprobado(new Grado());
		}

		// Para proceso de actualizar inscripción. (I)
		if (inscripcion.getId() != null) {
			// Validar si existe registro de ubicación.
			ubicacion = ubicacionFacadeLocal.buscarPorIdInscripcion(inscripcion.getId());
			if (ubicacion != null) {
				if (ubicacion.getId() != null) {
					mostrarPanelCUEExisteUbicacion();
				}
			} else {
				ubicacion = new Ubicacion();
				ubicacion.setPais(new Pais());
			}
			ubicacionEdit = ubicacionFacadeLocal.buscarPorIdInscripcion(inscripcionEdit.getId());
			;
		}

		// Para proceso de actualizar inscripción. (F)

		// Validar información del registro leido.
		obtenerNacionalidadIndigena();
		obtenerPais();
		validarPresentaCertificado();
		obtenerProgramaEbja();
		validarProgramaEbja();
		visibleCaracteristicasOE = false;
		visiblePgDocumentoPresentado = false;
		visiblePgDocumentoMotivo = false;

	}

	public void validarCorreo() {
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			String url2 = req.getRequestURL().toString();

			urlCorreo = getDominioCorreo(url2);
			System.out.println(urlCorreo);

			boolean estadoEnvio = false;
			estadoEnvio = MailUtil.EnviarCorreoValidacion(urlCorreo, "notificaciones.educacion.gob.ec",
					"bachiller.virtual@educacion.gob.ec", "BV123***",
					"Correo de validación al registro de inscripción Modalidad Educativa a Distancia-Virtual",
					registroEstudiante);
			if (estadoEnvio) {

				mensajeEnvioCorreo = "Correo enviado";
				disabledBotonEnviarCorreo = true;

			} else
				mensajeEnvioCorreo = "Error al enviar correo";

		} catch (Exception e) {
			e.printStackTrace();
			disabledBotonEnviarCorreo = false;
			// disabledInformacionRegistro = true;
			mensajeEnvioCorreo = "Error al enviar correo vuelva a intentar";
		}
	}

	public static String getDominioCorreo(String urlActual) throws URISyntaxException {

		URI uri = new URI(urlActual);
		System.out.println(uri.toString().substring(0, uri.toString().indexOf("ebja-web")));

		return uri.toString().substring(0, uri.toString().indexOf("ins-virtual-web"));
	}

	public void cargarDatosInscripcion() {

		proyectoSelecionado = inscripcion.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
				.getProgramaEducativo().getIdProgramaEducativo();
		obtenerFasesProyectosUltimoAprobado();
		if (inscripcion.getFaseAprobado() != 0) {
			faseProgramaSeleccionado = inscripcion.getFaseAprobado();
		} else {// para datos historicos
			faseProgramaSeleccionado = listaGradoFaseProgramaUltimaAprobado.get(1).getIdGrupoFaseNotas();

		}
		obtenerOfertaAprobada();

		if (inscripcion.getProgramaAprobado() != 0) {
			// busca el nemonico de la id almacenada
			programaOfertaSeleccionado = programaEbjaFacadeLocal
					.buscarPrograma((int) (inscripcion.getProgramaAprobado())).getNemonico();
		} else {

			programaOfertaSeleccionado = listaProgramaEbjaAprobado.get(0).getNemonico();
		}
		obtenerGrado();
		gradoOfertaSeleccionado = inscripcion.getGradoAprobado().getId();
		obtenerFasesProyectos();
		faseProgramaSeleccionadoUltimo = inscripcion.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
				.getIdGrupoFaseNotas();
		buscarOfertaGrado();

		if (inscripcion.getProgramaAprobado() != 0) {// si es un registro historico no busca progama ni grado, se
														// calcula solo
			programaOfertaInscripcion = inscripcion.getProgramaGrado().getProgramaEbja().getNemonico();
			obtenerGradoInscripcion();
			gradoOfertaSeleccionadoInscripcion = inscripcion.getProgramaGrado().getGrado().getId();
		}

	}

	public void mostrarPanelCUEExisteUbicacion() {

		disabledUbicacionGeografica = false;
		if (ubicacion.getNumeroSuministro() != null) {
			cueBuscar = ubicacion.getNumeroSuministro();
			tieneCUE = "1";

			existeUbicacion = true;
			visibleUbicacionCUE = true;
			visibleZonaDetalle = false;

			ubicacion.setCircuito(null);
			ubicacion.setParroquia(null);

			buscarUbicacionCUE();
		} else {
			if (ubicacion.getCircuito() != null && ubicacion.getParroquia() != null) {
				cueBuscar = "";
				tieneCUE = "0";

				existeUbicacion = true;
				visibleUbicacionCUE = false;
				visibleZonaDetalle = true;

				ubicacion.setNumeroSuministro(null);
				ubicacion.setCodigoPostal(null);
				ubicacion.setCoordenadaX(null);
				ubicacion.setCoordenadaY(null);

				parroquia = ubicacion.getParroquia();
				canton = parroquia.getIdCanton();

				if (canton != null) {
					listaParroquia = parroquiaFacadeLocal.listaParroquiaPorCanton(canton.getCodigoCanton());

					provincia = canton.getIdProvincia();
					listaCanton = cantonFacadeLocal.listaCantonPorProvincia(provincia.getCodigoProvincia());
				}

				circuito = ubicacion.getCircuito();
				distrito = circuito.getIdDistrito();
				if (distrito != null) {
					zona = distrito.getIdZona();
				}
			}
		}
	}

	public void obtenerDatosFamiliaresYDiscapacidadConIP() {

		// Obtener lista datos familiares.
		listaDatosFamiliares = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DATOS_FAMILIARES);
		if (!listaDatosFamiliares.isEmpty()) {
			for (CatalogoEbja catalogoEbja : listaDatosFamiliares) {
				DatoFamiliar datoFamiliar = new DatoFamiliar();
				datoFamiliar = datoFamiliarFacadeLocal.buscarDatoFamiliar(registroEstudiante.getId(),
						catalogoEbja.getId());
				if (datoFamiliar != null) {
					if (registroEstudiante.getTieneHijo().equals("1")) {
						catalogoEbja.setIdUsuarioCreacion(1);
					} else {
						catalogoEbja.setIdUsuarioCreacion(0);
					}
				} else {
					catalogoEbja.setIdUsuarioCreacion(null);
				}
			}
		}

		// Obtener lista discapacidad general.
		listaDiscapacidadCatalogo = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DISCAPACIDAD_GENERAL);

		if (!listaDiscapacidadCatalogo.isEmpty()) {
			listaDiscapacidad = new ArrayList<Discapacidad>();
			for (CatalogoEbja catalogoEbja : listaDiscapacidadCatalogo) {

				discapacidad = new Discapacidad();
				Discapacidad discapacidadAux = new Discapacidad();
				discapacidadAux = discapacidadFacadeLocal.buscarDiscapacidad(registroEstudiante.getId(),
						catalogoEbja.getId());
				discapacidad.setCatalogoDiscapacidad(catalogoEbja);

				if (discapacidadAux != null) {
					if (registroEstudiante.getTieneDiscapacidad().equals("1")) {
						discapacidad.setPorcentaje(discapacidadAux.getPorcentaje());
						discapacidad.setIdUsuarioCreacion(1);
					} else {
						discapacidad.setPorcentaje(BigDecimal.valueOf(0.0));
						discapacidad.setIdUsuarioCreacion(0);
					}
				} else {
					discapacidad.setPorcentaje(BigDecimal.valueOf(0.0));
					discapacidad.setIdUsuarioCreacion(null);
				}

				listaDiscapacidad.add(discapacidad);
			}
		}

	}

	public void obtenerDatosFamiliaresYDiscapacidadSinIP() {
		// Obtener lista datos familiares.
		listaDatosFamiliares = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DATOS_FAMILIARES);
		if (!listaDatosFamiliares.isEmpty()) {
			for (CatalogoEbja datoFamiliar : listaDatosFamiliares) {
				datoFamiliar.setIdUsuarioCreacion(null);
			}
		}

		// Obtener lista discapacidad general.
		listaDiscapacidadCatalogo = catalogoEbjaFacadeLocal
				.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_DISCAPACIDAD_GENERAL);
		if (!listaDiscapacidadCatalogo.isEmpty()) {
			listaDiscapacidad = new ArrayList<Discapacidad>();
			for (CatalogoEbja catalogoEbja : listaDiscapacidadCatalogo) {
				discapacidad = new Discapacidad();
				discapacidad.setCatalogoDiscapacidad(catalogoEbja);
				discapacidad.setPorcentaje(BigDecimal.valueOf(0.0));
				discapacidad.setIdUsuarioCreacion(null);
				listaDiscapacidad.add(discapacidad);
			}
		}
	}

	public void ingresarIdentificacionAspirante() {
		textosalidaPanelA = "";
		// Validar existencia del registro de inscripción pendiente.
		// (estadoAsignación=2)
		validarInscripcionPendiente();

		// Obtener lista Programas Educativos.
		listaProgramasEducativos = programaEducativoFacadeLocal.buscarTodosProgramaEducativoActivos();
		
		registroEstudiante.setNumeroIdentificacion(cedulaBuscar);

		if (!calcularEdad()) {
			visiblePanelB = false;
			textosalidaPanelA = Constantes.ERROR_EDAD;
		} else {
			disabledIdentificacionAspirante = true;
			disabledIngresarIdentificacion = true;
			disabledTipoDocumento = true;
			readonlyNumeroIdentificacion = true;
			readonlyApellidosNombres = true;
			readonlyFechaNacimiento = true;
		}

		switch (nombreTipoDocumento) {
		case "P":
		case "R":
			registroEstudiante.setNacionalidadEcuatoriana("0");
			visibleNacionalidad = false;
			disabledNacionalidad = true;

			visiblePgParentesco = true;
			disabledParentesco = false;
			break;
		case "S":
			listaProgramasEducativos = (List<ProgramaEducativo>) programaEducativoFacadeLocal.buscarProgramaCPL();
			registroEstudiante.setNacionalidadEcuatoriana("0");
			visibleNacionalidad = false;
			disabledNacionalidad = true;

			visiblePgParentesco = true;
			disabledParentesco = false;
			break;
		}
	}

	// Calcular edad del aspirante, a la fecha actual.
	private boolean calcularEdad() {
		Calendar fecNacimiento = Calendar.getInstance();
		fecNacimiento.setTime(registroEstudiante.getFechaNacimiento());

		Calendar fecActual = Calendar.getInstance();

		int anio = fecActual.get(1) - fecNacimiento.get(1);
		int mes = fecActual.get(2) - fecNacimiento.get(2);
		int dia = fecActual.get(5) - fecNacimiento.get(5);

		if (mes < 0 || mes == 0 && dia < 0) {
			anio--;
		}

		edadAspirante = anio;
		registroEstudiante.setEdad(anio);
		visibleEdadAspirante = true;

		if (anio >= Constantes.ANIO_MINIMO) {
			return true;
		}

		return false;
	}

	public void descripcionGenero() {
		nombreGenero = " ";
		switch (codGenero) {
		case 1:
			nombreGenero = "MASCULINO";
			break;
		case 2:
			nombreGenero = "FEMENINO";
			break;
		}

		for (Catalogo catalogo : listaGenero) {
			if (catalogo.getNombre().equals(nombreGenero)) {
				registroEstudiante.getCatalogoGenero().setId(catalogo.getId());
				break;
			}
		}
	}

	public void obtenerGenero() {
		for (Catalogo catalogo : listaGenero) {
			if (catalogo.getId().equals(registroEstudiante.getCatalogoGenero().getId())) {
				nombreGenero = catalogo.getNombre();
				break;
			}
		}
	}

	public void descripcionEstadoCivil() {
		desEstadoCivil = "";
		switch (codEstadoCivil) {
		case 1:
			desEstadoCivil = "SOLTERO";
			break;
		case 2:
			desEstadoCivil = "CASADO";
			break;
		case 3:
			desEstadoCivil = "DIVORCIADO";
			break;
		case 4:
			desEstadoCivil = "VIUDO";
			break;
		case 5:
			desEstadoCivil = "UNION DE HECHO";
			break;
		case 6:
			desEstadoCivil = "UNION LIBRE";
			break;
		}

		for (Catalogo catalogo : listaEstadoCivil) {
			if (catalogo.getNombre().equals(desEstadoCivil)) {
				registroEstudiante.getCatalogoEstadoCivil().setId(catalogo.getId());
				break;
			}
		}
	}

	public void obtenerDiscapacidad() {
		textosalida = "";

		// if (registroEstudiante.getTieneDiscapacidad().equals("0")) {
		for (Discapacidad discapacidad1 : listaDiscapacidad) {
			discapacidad1.setIdUsuarioCreacion(null);
			discapacidad1.setPorcentaje(BigDecimal.valueOf(0.0));
			discapacidad.setPorcentaje(BigDecimal.valueOf(0.0));
		}
		// }
	}

	public void obtenerBono() {
		textosalida = "";
	}

	public void obtenerDatosFamiliares1(CatalogoEbja datoFamiliar) {
		textosalida = "";
		if (nombreGenero != null) {
			if (nombreGenero.equals("MASCULINO") && datoFamiliar.getNemonico().equals("ESTGES")
					&& datoFamiliar.getIdUsuarioCreacion() == 1) {
				textosalida = "Error: La opción '" + datoFamiliar.getNombre() + "' no es compatible con el Género: '"
						+ nombreGenero + "'.";
			}
		}
	}

	public void obtenerDatosFamiliares() {
		textosalida = "";
		if (registroEstudiante.getTieneHijo().equals("0")) {
			for (CatalogoEbja datoFamiliar : listaDatosFamiliares) {
				datoFamiliar.setIdUsuarioCreacion(null);
			}
		}
	}

	public void validarEmail(FacesContext arg0, UIComponent arg1, Object arg2) {
		EmailValidator emailValidator = new EmailValidator((String) arg2);
		if (!emailValidator.isEmailValid()) {
			textosalida = "Error: e-mail incorrecto.";
		}
	}

	public void obtenerProgramaEbjaView() {

		// Verificar si seleccionó presenta o no certificado.
		if (inscripcion.getPresentaCertificado() == null) {
			textosalida = "Debe seleccionar si presenta o no certificado";
			FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
			focusManager.focus("frmForm:sorPresentaCertificado1");
			return;
		}

		// Tomar Programa Grado con los valores seleccionados del ID
		inscripcion.setProgramaGrado(programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion,
				programaOfertaInscripcion));

		// edadMinima = programaEbja.getEdadMinima();
		edadMinima = inscripcion.getProgramaGrado().getProgramaEbja().getEdadMinima();

		// Validar parametros del programa/modulo/
		if (edadAspirante < edadMinima) {
			textosalida = Constantes.ERROR_EDAD;
			FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
			focusManager.focus("frmForm:olEdadAspirante");
			return;
		}
		if (!existeInscripcionPendiente) {
			ProgramaGrado programaGrado = new ProgramaGrado();
			programaGrado.setProgramaEbja(new ProgramaEbja());
			inscripcion.setProgramaGrado(new ProgramaGrado());
		}
		validarPresentaCertificado();
		obtenerProgramaEbja();

		if (textosalida == "") {
			// SI LA OFERTA ES TIPO PROGRAMA= 3 O 4 BASICA O BACHILLERATO ENTONCES ES
			// REQUERIDO
			if (inscripcion.getProgramaGrado().getProgramaEbja().getTipoPrograma().getId() == 3
					|| inscripcion.getProgramaGrado().getProgramaEbja().getTipoPrograma().getId() == 4) {

				if ((registroEstudiante.getCorreoElectronico() == null)) {
					disabledCmbDocumentoPresentado = true;
					disabledCmbDocumentoMotivo = true;
					requiredEmail = true;
					textosalida = "El Correo Electrónico es obligatorio para esta oferta ";

					FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
					focusManager.focus("frmForm:itEmail");
					return;
				} else {
					if (registroEstudiante.getCorreoElectronico().length() == 0) {
						requiredEmail = true;
						disabledCmbDocumentoPresentado = true;
						disabledCmbDocumentoMotivo = true;
						textosalida = "El Correo Electrónico es obligatorio para esta oferta ";

						FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
						focusManager.focus("frmForm:itEmail");
						return;
					} else {
						requiredEmail = false;
						disabledCmbDocumentoPresentado = false;
						disabledCmbDocumentoMotivo = false;
					}
				}

			} else {
				requiredEmail = false;
				disabledCmbDocumentoPresentado = false;
				disabledCmbDocumentoMotivo = false;
			}
		}

	}

	public void obtenerProgramaEbja() {

		listaProgramaEbja = new ArrayList<ProgramaEbja>();
		textosalida = "";
		disabledIngresarDatosAdicionales = true;
		TipoPrograma tipoPrograma = null;

		if (registroEstudiante.getInscripcion() != null) {
			// Verifica si existe un programa en un registro existente en estado pendiente.
			tipoPrograma = registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getTipoPrograma();

		} else {
			// Obtiene programa si es un registro nuevo.
			tipoPrograma = programaGradoFacadeLocal
					.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
					.getProgramaEbja().getTipoPrograma();

		}

		if (tipoPrograma != null && nombreTipoDocumento.equals("N")
				&& (tipoPrograma.getNemonico().contentEquals("ME-TP-03") || // se saca de la oferta de bachillerato
																			// (ME-TP-04) (1 2 3 bgu) y basica ( 8910
																			// egb) (ME-TP-03)
						tipoPrograma.getNemonico().contentEquals("ME-TP-04"))) {
			textosalida = Constantes.DEBE_TENER_IDENTIFICACION;
			visiblePgDocumentoPresentado = false;
			return;
		}

		if (!existeInscripcionPendiente) {
			registroEstudiante.setCatalogoDocumento(new CatalogoEbja());
			registroEstudiante.setCatalogoMotivo(new CatalogoEbja());
		}

		if (!(registroEstudiante.getInscripcion() == null)) {

			if (registroEstudiante.getInscripcion().getGradoAprobado().getId() > 4
					&& registroEstudiante.getInscripcion().getPresentaCertificado().equals("0")) {
				visibleMensajeDH = true;
			} else {
				visibleMensajeDH = false;
			}
		}

		// Tomar Programa Grado con los valores seleccionados del ID
		inscripcion.setProgramaGrado(programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion,
				programaOfertaInscripcion));

		/* revisar */
		if (!existeInscripcionPendiente) {
			reglaNegocio = reglaNegocioFacadeLocal.buscarPorProgramaEbjaFase(programaGradoFacadeLocal
					.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
					.getProgramaEbja().getId(), Constantes.NEMONICO_INSCRIPCION);

		}
		//Cobertura de ProgramaEbja
		if (inscripcion.getProgramaGrado().getProgramaEbja().getCobertura() == null) {
			cobertura = "Nacional";
		} else {
			if (inscripcion.getProgramaGrado().getProgramaEbja().getCobertura().equals("0"))
				cobertura = "Nacional";
			else
				cobertura = "InterNacional";
		}
		
		visibleCaracteristicasOE = true;

		if (inscripcion.getPresentaCertificado() == null) {

			if (registroEstudiante.getInscripcion().getPresentaCertificado().equals("1")) {
				visiblePgDocumentoPresentado = true;
				visiblePgDocumentoMotivo = false;
				disabledDocumentoPresentado = false;
				disabledDocumentoMotivoI = false;
			} else {
				visiblePgDocumentoPresentado = false;
				visiblePgDocumentoMotivo = true;
				disabledDocumentoPresentado = false;
				disabledDocumentoMotivoI = true;
			}

		} else {

			if (inscripcion.getPresentaCertificado().equals("1")) {
				visiblePgDocumentoPresentado = true;
				visiblePgDocumentoMotivo = false;
				disabledDocumentoPresentado = false;
				disabledDocumentoMotivoI = false;
			} else {
				visiblePgDocumentoPresentado = false;
				visiblePgDocumentoMotivo = true;
				disabledDocumentoPresentado = false;
				disabledDocumentoMotivoI = true;
			}
		}
		if (existeInscripcionPendiente) {
			disabledIngresarDatosAdicionales = false;
		}
	}

	public void validarProgramaEbja() {
		disabledIngresarDatosAdicionales = true;
		textosalida = "";

		// Obtener parametros del programa/modulo/ vs grado al que va el aspirante.
		edadMinima = 0;
		programaEbja = new ProgramaEbja();

		if (registroEstudiante != null) {
			programaEbja = registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja();

		} else {
			// Obtiene programa si es un registro nuevo.
			programaEbja = programaEbjaFacadeLocal.find(programaGradoFacadeLocal
					.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
					.getProgramaEbja().getId());

		}

		// Al momento de seleccionar “modalidad virtual”. El sistema valida el documento
		// de identidad sea:
		// cédula, pasaporte o carnet Refugiado. (jbrito - 20190729)
		if (programaEbja.getModalidad().getNemonico().equals("MOD-03") && nombreTipoDocumento.equals("N")) {
			textosalida = Constantes.DEBE_TENER_IDENTIFICACION;
			return;
		}

		visibleCaracteristicasOE = true;

		// Obtener regla_negocio, relacionado con la oferta más la FASE.
		reglaNegocio = reglaNegocioFacadeLocal.buscarPorProgramaEbjaFase(programaEbja.getId(),
				Constantes.NEMONICO_INSCRIPCION);
		if (reglaNegocio == null || reglaNegocio.getFechaFin() == null || reglaNegocio.getFechaInicio() == null) {
			textosalida = Constantes.NO_EXISTE_REGLA_NEGOCIO;
			return;
		}

		// Validar que fecha actual se encuentre en el rango de fecha inicio y fin de la
		// FASE.
		// Incluir en el rango la fecha actual. (20190715)
		try {
			Date fechaDateActual, fechaDateInicio, fechaDateFin;
			fechaDateActual = formateador.parse(formateador.format(fechaActual));
			fechaDateInicio = formateador.parse(formateador.format(reglaNegocio.getFechaInicio()));
			fechaDateFin = formateador.parse(formateador.format(reglaNegocio.getFechaFin()));

			if (fechaDateActual.equals(fechaDateInicio) || fechaDateActual.equals(fechaDateFin)) {
			} else {
				if (fechaActual.after(reglaNegocio.getFechaFin())
						|| fechaActual.before(reglaNegocio.getFechaInicio())) {
					textosalida = Constantes.FECHA_FASE_FUERA_RANGO;
					return;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (programaEbja.getCobertura() == null) {
			programaEbja.setCobertura("0");
		}

		if (programaEbja.getCobertura().equals("0")) {
			cobertura = "Nacional";
		} else {
			cobertura = "InterNacional";
		}

		// valida el grado correcto q debe ser elegido
		// inscripcion.setProgramaGrado(programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion,programaOfertaInscripcion));

		// edadMinima = programaEbja.getEdadMinima();
		edadMinima = inscripcion.getProgramaGrado().getProgramaEbja().getEdadMinima();

		// Validar parametros del programa/modulo/
		if (edadAspirante < edadMinima) {
			textosalida = Constantes.ERROR_EDAD;
			return;
		}

		// Datos de la fecha de inicio clases desde programaEbja.
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		if (programaEbja.getFechaInicioClases() == null) {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(new Date()));
		} else {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(programaEbja.getFechaInicioClases()));
		}

		// Setear datos de DatoFamiliar.
		if (!listaDatosFamiliares.isEmpty()) {
			for (CatalogoEbja catalogoEbja : listaDatosFamiliares) {
				if (catalogoEbja.getIdUsuarioCreacion() == null) {
					catalogoEbja.setIdUsuarioCreacion(0);
				}
			}
		}
		// Setear datos de Discapacidad.
		if (!listaDiscapacidad.isEmpty()) {
			for (Discapacidad discapacidad : listaDiscapacidad) {
				if (discapacidad.getIdUsuarioCreacion() == null) {
					discapacidad.setIdUsuarioCreacion(0);
				}
			}
		}

		if (registroEstudiante.getInscripcion().getPresentaCertificado().equals("1")) {
			visiblePgDocumentoPresentado = true;
			visiblePgDocumentoMotivo = false;
			disabledDocumentoPresentado = false;
			disabledDocumentoMotivoI = false;
		} else {
			visiblePgDocumentoPresentado = false;
			visiblePgDocumentoMotivo = true;
			disabledDocumentoPresentado = false;
			disabledDocumentoMotivoI = true;
		}

		visiblePgCargarPDF = false;
	}

	public void validarNoPresentaCertificado() {
		System.out.println("----------- " + disabledDocumentoMotivoI);
		procesarInscripcionPendiente = true;
	}

	public void activarCargarDocumentoPDF() {
		textosalida = "";
		if (programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
				.getProgramaEbja().getModalidad().getNemonico().equals("MOD-03")
				&& inscripcion.getPresentaCertificado().equals("1")) {
			visiblePgCargarPDF = true;
			disabledPgCargarPDF = false;
		} else {
			visiblePgCargarPDF = false;
		}

		if (catalogoEbjaFacadeLocal.find(registroEstudiante.getCatalogoDocumento().getId()).getNemonico().equals("R")) {
			procesarInscripcionPendiente = true;
			disabledIngresarDatosAdicionales = true;
		} else {
			procesarInscripcionPendiente = false;
			disabledIngresarDatosAdicionales = false;
		}
	}

	public void validarPresentaCertificadoView() {

		if (!existeInscripcionPendiente) {
			listaProgramaEbja = new ArrayList<ProgramaEbja>();
			inscripcion.setGradoAprobado(new Grado());
			inscripcion.setProgramaGrado(new ProgramaGrado());
		}
		validarPresentaCertificado();

	}

	public void validarPresentaCertificado() {
		textosalida = "";
		visibleMensajeDH = false;
		visibleCaracteristicasOE = false;
		disabledIngresarDatosAdicionales = true;
		visiblePgCargarPDF = false;

		visiblePgDocumentoPresentado = false;
		visiblePgDocumentoMotivo = false;
		procesarInscripcionPendiente = false;
	}

	// Guardar el archivo PDF en el server.
	public void cargarArchivo(FileUploadEvent fileUploadEvent) {
		disabledIngresarDatosAdicionales = true;
		if (fileUploadEvent.getFile().getSize() <= (Constantes.TAMANIO_MEGA * Constantes.CANTIDAD_MEGA)) {
			SimpleDateFormat formateador1 = new SimpleDateFormat("yyyy");
			rutaArchivo = "";
			if (sesionControlador.getHostName().equals("DMEDNTICS021")) {
				rutaArchivo = Constantes.pathArchivoLocal + formateador1.format(fechaActual) + "\\"
						+ fileUploadEvent.getFile().getFileName();
			} else {
				rutaArchivo = Constantes.pathArchivoServer + formateador1.format(fechaActual) + "/"
						+ fileUploadEvent.getFile().getFileName();
			}
			try {
				// Grabar archivo pdf en la ruta indicada.
				ReporteJasper report = new ReporteJasper();
				if ((boolean) report.grabarArchivo(fileUploadEvent.getFile().getInputstream(), rutaArchivo)) {
					registroEstudiante.setArchivoPresentado(rutaArchivo);

					disabledPgCargarPDF = true;
					disabledIngresarDatosAdicionales = false;
					textosalida = Constantes.DOCUMENTO_CARGO + fileUploadEvent.getFile().getFileName();
				} else {
					disabledPgCargarPDF = false;
					textosalida = Constantes.DOCUMENTO_NO_CARGO + fileUploadEvent.getFile().getFileName();
					registroEstudiante.setArchivoPresentado(Constantes.DOCUMENTO_NO_CARGO + rutaArchivo);
				}
			} catch (IOException e) {
				e.printStackTrace();
				textosalida = Constantes.DOCUMENTO_NO_CARGO;
			}
		} else {
			textosalida = Constantes.MENSAJE_TAMANIO_MEGA + Constantes.CANTIDAD_MEGA + " M";
		}
	}

	public void habilitarBotonUbicacionGeografica() {
		disabledUbicacionGeografica = false;
	}

	public void aceptarUbicacionGeografica() {
		inscripcion.setNumeroIdentificacionUsuario(sesionControlador.getUsuarioSesion().getCedula());
		inscripcion.setApellidosNombresUsuario(sesionControlador.getUsuarioSesion().getNombre());
		ubicacion.setIdInstitucion(idInstitucion);
		visiblePanelD = true;
		visiblePanelCUE = true;
		readonlyUbicacionCUE = true;
		disabledUbicacionGeografica = true;

		disabledCmbPais = true;
		disabledCmbEstadoUsa = true;
		disabledCmbCiudadUsa = true;
		setDisabledItCalle(true);
		setDisabledItCodigoPostal(true);

		setDisabledCmbProvincia(true);
		setDisabledCmbCanton(true);
		setDisabledCmbParroquia(true);
		setDisabledItDireccion(true);
	}

	public void aceptarInformacionRegistro() {
		disabledUbicacionGeografica = true;
		disabledInformacionRegistro = true;

		readonlyApellidosNombresRegistra = true;
		readonlyNumeroIdentificacionRegistra = true;

	}

	public void aceptarInformacion(ValueChangeEvent evt) {
		int aceptar = ((Integer) evt.getNewValue()).intValue();
		if (aceptar == 1) {
			visibleBotonEnviar = true;
			setDisabledCmbEspecialidad(true);
			disabledAceptarInformacion = true;
		}
	}

	public void validarDatosAdicionales() {
		textosalida = "";
		boolean tmpPeriodoFaseOferta = false;
		// Validar que se ingrese el documento.
		if (visiblePgCargarPDF && !disabledPgCargarPDF) {
			textosalida = Constantes.DOCUMENTO_NO_CARGO + rutaArchivo;
			return;
		}

		if (!existeInscripcionPendiente) {
			// Buscar inscripción del aspirante.
			boolean registroDuplicado = registroEstudianteFacadeLocal.existeRegistroEstudiantePorFiltros(
					registroEstudiante.getNumeroIdentificacion(), registroEstudiante.getApellidosNombres(),
					registroEstudiante.getFechaNacimiento(), registroEstudiante.getCatalogoTipoIdentificacion().getId(),
					nombreTipoDocumento);
			// Verificar si la inscripción del aspirante, ya existe.
			if (registroDuplicado) {
				textosalida = Constantes.INSCRIPCION_REGISTRADA;
				return;
			}
		}

		// Validar si se ingresa o no dato familiar en la lista.
		validarDatoFamiliar();
		if (errorIngresarDatoFamiliar) {
			textosalida = "Error: Ingresar Datos Familiares...";

			FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
			focusManager.focus("edtTieneHijos");
			return;
		}

		// Validar si se ingresa o no dato de discapacidad en la lista.
		validarDatoDiscapacidad();
		if (errorIngresarDatoDiscapacidad) {
			textosalida = "Error: Ingresar Datos de Discapacidad...";
			return;
		}

		// Verificar la cobertura de la oferta.
		if (programaGradoFacadeLocal.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
				.getProgramaEbja().getCobertura().equals("0")) {
			disabledCmbPais = true;
			ubicacion.getPais().setId((short) 345);
			visibleZona = true;
		} else {
			visibleZona = false;
		}

		if (registroEstudiante.getTelefono().length() != 0) {
			if (registroEstudiante.getTelefono().length() < 10) {
				textosalida = "Ingresar dato de Teléfono Celular correcto.";

				FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
				focusManager.focus("frmForm:itTelefono");
				return;
			}
		} else {
			textosalida = "Ingresar el número de Teléfono Celular. ";

			FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
			focusManager.focus("frmForm:itTelefono");
			return;
		}

		if (registroEstudiante.getTelefonoConvencional().length() != 0) {
			if (registroEstudiante.getTelefonoConvencional().length() < 9) {
				textosalida = "Ingresar dato de Teléfono Convencional correcto. ";

				FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
				focusManager.focus("frmForm:itTelefono1");
				return;
			}
		}

		if (registroEstudiante.getCorreoElectronico().length() != 0) {
			EmailValidator emailValidator = new EmailValidator(registroEstudiante.getCorreoElectronico());
			if (!emailValidator.isEmailValid()) {
				textosalida = "Ingresar dato de Correo Electrónico correcto. ";

				FocusManager focusManager = (FocusManager) ServiceTracker.getService(FocusManager.class);
				focusManager.focus("frmForm:itEmail");
				return;
			}
		}

		// Setear datos adicionales a disabled.
		setearDatosAdicionales();

		// validación que las reglas de negocio entren dentro de la fase del proyecto.
//		if (!validarRangoFechaFaseReglaNegocio()) {
//			textosalida = "La Oferta Educativa no se encuentra dentro de la Fase de Inscripción y/o el día de hoy no se encuentra dentro del rango de la Fase de Inscripción";
//			tmpPeriodoFaseOferta = true;
//		}

		// Verificar si se invoca al proceso de grabar inscripcion pendiente.
		if (procesarInscripcionPendiente) {
			// Guardar la inscripción en estado pendiente.
			enviarInformacion();
			textosalida = Constantes.PRESENTAR_DOCUMENTACION;
			procesarInscripcionPendiente = false;
		} else {
			if (tmpPeriodoFaseOferta) {
				visiblePanelC = false;
			} else {
				visiblePanelC = true;
			}
		}
	}

	public boolean validarRangoFechaFaseReglaNegocio() {

		ProgramaEbja programaEbja = null;

		if (registroEstudiante.getInscripcion() != null) {
			// Verifica si existe un programa en un registro existente en estado pendiente.
			programaEbja = registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja();

		} else {
			// Obtiene programa si es un registro nuevo.
			programaEbja = programaGradoFacadeLocal
					.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
					.getProgramaEbja();
		}

		reglaNegocio = reglaNegocioFacadeLocal.buscarPorProgramaEbjaFase(programaEbja.getId(),
				Constantes.NEMONICO_INSCRIPCION);

		ahoraTmp = new Date();
		
		if (reglaNegocio.getFechaInicio().after(programaEbja.getGrupoFasePrograma().getFechaInicio()) == true && 
			reglaNegocio.getFechaFin().before(programaEbja.getGrupoFasePrograma().getFechaFin()) == true ) {	
			if((ahoraTmp.after(reglaNegocio.getFechaInicio()) == true 
				&& ahoraTmp.before(reglaNegocio.getFechaFin()) == true)
				|| ahoraTmp.compareTo(reglaNegocio.getFechaInicio())==0 
				|| ahoraTmp.compareTo(reglaNegocio.getFechaFin())==0) {  
				return true;
			}else {
				return false;
			}			
		} else {
			return false;
		}
	}

	public void setearDatosAdicionales() {
		disabledIngresarDatosAdicionales = true;
		disabledGenero = true;
		disabledEstadoCivil = true;
		disabledEtnia = true;
		disabledSituacionLaboral = true;
		disabledActividadEconomica = true;
		disabledDatosFamiliares = true;
		disabledNacionalidad = true;
		disabledParentesco = true;
		disabledTiempoEstudio = true;
		disabledNacionalidadIndigena = true;
		disabledDocumentoHabilitante = true;
		disabledTelefono = true;
		disabledEmail = true;
		disabledUltimoAnioAprobado = true;
		disabledRegistroEstudiante = true;
		disabledRezagoEducativo1 = true;
		disabledItBusquedaPromocion = true;
		disabledDiscapacidad = true;
		disabledBono = true;
		disabledDocumentoPresentado = true;
		disabledDocumentoMotivoI = true;
		disabledPgCargarPDF = true;
	}

	// Validar que se ingrese datos familiares, en caso de escoger "SI".
	public void validarDatoFamiliar() {
		errorIngresarDatoFamiliar = false;
		if (registroEstudiante.getTieneHijo() == null | registroEstudiante.getTieneHijo().equals("0")
				| listaDatosFamiliares.isEmpty()) {
			return;
		}

		errorIngresarDatoFamiliar = true;
		for (CatalogoEbja catalogoAux : listaDatosFamiliares) {
			if (catalogoAux.getIdUsuarioCreacion() != null) {
				if (catalogoAux.getIdUsuarioCreacion().equals(1)) {
					errorIngresarDatoFamiliar = false;
					break;
				}
			}
		}
	}

	// Validar que se ingrese datos de discapacidades, en caso de escoger "SI".
	public void validarDatoDiscapacidad() {
		errorIngresarDatoDiscapacidad = false;
		if (registroEstudiante.getTieneDiscapacidad() == null | registroEstudiante.getTieneDiscapacidad().equals("0")
				| listaDiscapacidad.isEmpty()) {
			return;
		}

		errorIngresarDatoDiscapacidad = true;
		for (Discapacidad discapacidadAux : listaDiscapacidad) {
			if (discapacidadAux.getIdUsuarioCreacion() != null) {
				if (discapacidadAux.getIdUsuarioCreacion().equals(1)) {
					if (!discapacidadAux.getPorcentaje().equals(BigDecimal.valueOf(0.0))) {
						errorIngresarDatoDiscapacidad = false;
						break;
					}
				}
			}
		}
	}

	public void obtenerNacionalidad() {
		if (registroEstudiante.getNacionalidadEcuatoriana().equals("0")) {
			setVisiblePgParentesco(true);
		} else {
			setVisiblePgParentesco(false);
		}
	}

	public void obtenerPais() {
		if (registroEstudiante.getNacionalidadEcuatoriana().equals("0")) {
			setVisiblePgParentesco(true);
		} else {
			setVisiblePgParentesco(false);
			registroEstudiante.setPais(new Pais());
		}
	}

	public void obtenerNacionalidadIndigena() {
		for (Etnia etnia : listaEtnia) {
			if (etnia.getId().equals(registroEstudiante.getEtnia().getId())) {
				nombreEtnia = etnia.getNombre();
				break;
			}
		}

		if (nombreEtnia.equals("Indígena")) {
			visiblePgNacionalidadIndigena = true;
		} else {
			visiblePgNacionalidadIndigena = false;
			registroEstudiante.setCatalogoNacionalidad(new Catalogo());
		}
	}

	public void bloquearCamposFinalizar() {

		visiblePanelA = true;
		visiblePanelB = false;
		visiblePanelC = false;
		visiblePanelD = true;
		disabledFechaNacimiento = true;
	}

	public void enviarInformacion() {
		// Datos auditables
		datosAuditables();

		// Datos de registro estudiante.
		tratarCatalogosRegistroEstudiante();

		try {
			// Asignar datos de inscripcion.
			asignarDatosInscripcion();

			if (existeInscripcionPendiente) {
				// Actualizar inscripcion.
				if (inscripcionEdit.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
						.getIdGrupoFaseNotas() == faseProgramaSeleccionadoUltimo)
					inscripcionFacadeLocal.edit(inscripcion);

				else {
					inscripcion.setId(null);
					inscripcionFacadeLocal.create(inscripcion);
					inscripcionEdit.setEstado("0");// inhabilita la inscripcion anterior
					inscripcionFacadeLocal.edit(inscripcionEdit);

				}

			} else {
				// Crear inscripcion.
				inscripcionFacadeLocal.create(inscripcion);
			}

			// Asignar datos de registro estudiante.
			asignarDatosRegistroEstudiante();

			if (existeInscripcionPendiente) {
				// Actualizar registro estudiante.
				if (inscripcionEdit.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
						.getIdGrupoFaseNotas() == faseProgramaSeleccionadoUltimo) {
					registroEstudianteFacadeLocal.edit(registroEstudiante);

				} else {
					registroEstudiante.setId(null);
					registroEstudianteFacadeLocal.create(registroEstudiante);// crea un registro con los datos nuevos
					registroEstudianteEdit.setEstado("0");// inhabilita los datos del anterior
					registroEstudianteFacadeLocal.edit(registroEstudianteEdit);// guarda el cambio de estado

				}

			} else {
				// Crear registro estudiante.
				registroEstudianteFacadeLocal.create(registroEstudiante);
			}

			// Verificar si se invoca al proceso de grabar inscripcion pendiente.
			if (!procesarInscripcionPendiente) {
				// Asignar datos de ubicación.
				asignarDatosUbicacion();

				// Verificar si existe la ubicacion.
				if (!existeUbicacion) {
					// Registrar ubicacion.
					ubicacionFacadeLocal.create(ubicacion);
				} else {
					if (inscripcionEdit.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
							.getIdGrupoFaseNotas() == faseProgramaSeleccionadoUltimo) {
						ubicacionFacadeLocal.edit(ubicacion);// Actualizar ubicacion.

					} else {

						// Registrar ubicacion.
						ubicacion.setId(null);
						ubicacionFacadeLocal.create(ubicacion);
						ubicacionEdit.setEstado("0");// se inhabilita la ubicacion relacionada con la inscricpcion
														// inhabilitada anterior
						ubicacionFacadeLocal.edit(ubicacionEdit);
					}
				}
			}

			// Guardar entidades: Dato Familiar y Discapacidad
			guardarDatoFamiliarYDiscapacidad();

			setRenderedMensajeGuardar(true);

			visibleBotonEnviar = false;
			visibleClVerFormulario = true;

			disabledPanelB = true;
			// visiblePanelA=false;
			// visiblePanelB=false;
			// visiblePanelC=false;

		} catch (Exception e) {
			textoFormularioSatisfactorio = Constantes.NO_GUARDO_INSCRIPCION;
			FacesContext.getCurrentInstance().addMessage("frmForm:messageInicio",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, textoFormularioSatisfactorio, ""));
			FacesContext.getCurrentInstance().addMessage("frmForm:messageFinal",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, textoFormularioSatisfactorio, ""));
			if (registroEstudiante.getId() != null) {
				registroEstudianteFacadeLocal.remove(registroEstudiante);
			}
			if (ubicacion.getId() != null) {
				ubicacionFacadeLocal.remove(ubicacion);
			}
			if (inscripcion.getId() != null) {
				inscripcionFacadeLocal.remove(inscripcion);
			}
			e.printStackTrace();
		}

		if (visibleClVerFormulario) {
			datosParaReporte();
		}
	}

	public void asignarDatosInscripcion() {

		Integer tempIdInscripcion = inscripcion.getId();

		if (tempIdInscripcion != null) {
			secuencialInscripcion = inscripcion.getId();
		} else {
			secuencialInscripcion = inscripcionFacadeLocal.secuencialInscripcion();
		}

		if (nombreTipoDocumento.equals("N")) {
			nemonicoInscripcion();
		}
		// Nemmonico de inscripcion se usa para realizar el codigo de barras en el pdf
		// certificado
		inscripcion.setNemonico(inscripcion.getProgramaGrado().getProgramaEbja().getNemonico()
				+ secuencialInscripcion.toString() + registroEstudiante.getNumeroIdentificacion());

		inscripcion.setCatalogoRezago(catalogoEbjaFacadeLocal.find(inscripcion.getCatalogoRezago().getId()));

		inscripcion.setFaseAprobado(faseProgramaSeleccionado);
		inscripcion.setProgramaAprobado(
				programaEbjaFacadeLocal.obtenerProgramaPorNemonico(programaOfertaSeleccionado).getId());

		inscripcion.setGradoAprobado(gradoFacadeLocal.find(gradoOfertaSeleccionado));

		inscripcion.setNumeroIdentificacionUsuario(sesionControlador.getUsuarioSesion().getCedula());
		inscripcion.setApellidosNombresUsuario(sesionControlador.getUsuarioSesion().getNombre());
	}

	public void nemonicoInscripcion() {
		String nemonicoInscripcion = "INS";

		int longitud = secuencialInscripcion.toString().length();
		for (int i = longitud; i < 8; i++) {
			nemonicoInscripcion += "0";
		}

		registroEstudiante.setNumeroIdentificacion(nemonicoInscripcion + secuencialInscripcion.toString());
	}

	public void asignarDatosRegistroEstudiante() {
		registroEstudiante.setInscripcion(inscripcion);
		registroEstudiante.setAutorepresentado("0");

		// Verificar si se invoca al proceso de grabar inscripcion pendiente.
		if (procesarInscripcionPendiente) {
			// Para inscripción pendiente en estado asignación = 2.
			// Para inscripción con matricula asiganacion = 1.
			// Para inscripción sin asignacion = 0.
			registroEstudiante.setEstadoAsignacion("2");// no presenta documentos
		} else {
			registroEstudiante.setEstadoAsignacion("0");// se terminó todo el proceso de inscripcion
		}
	}

	public void asignarDatosUbicacion() {
		ubicacion.setPais(paisFacadeLocal.find(ubicacion.getPais().getId()));
		ubicacion.setInscripcion(inscripcion);
	}

	public void guardarDatoFamiliarYDiscapacidad() {
		boolean procesoEliminar = false;
		if (!listaDatosFamiliares.isEmpty()) {
			// Eliminar todos los registros para luego crearlos, según condiciones.
			procesoEliminar = datoFamiliarFacadeLocal.eliminarDatoFamiliar(registroEstudiante.getId());

			if (procesoEliminar) {
				for (CatalogoEbja catalogoEbja : listaDatosFamiliares) {
					if ((catalogoEbja.getIdUsuarioCreacion() == null ? 0 : catalogoEbja.getIdUsuarioCreacion()) == 1) {
						datoFamiliar = new DatoFamiliar();

						datoFamiliar.setRegistroEstudiante(registroEstudiante);
						datoFamiliar.setCatalogoDatoFamiliar(catalogoEbja);

						datoFamiliar.setEstado("1");
						datoFamiliar.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
						datoFamiliar.setIpUsuario(sesionControlador.getIpAdressLocal());
						datoFamiliar.setFechaCreacion(new Date());

						// Registrar dato familiar.
						datoFamiliarFacadeLocal.create(datoFamiliar);
					}
				}
			}
		}

		if (!listaDiscapacidad.isEmpty()) {
			// Eliminar todos los registros para luego crearlos, según condiciones.
			procesoEliminar = discapacidadFacadeLocal.eliminarDiscapacidad(registroEstudiante.getId());

			if (procesoEliminar) {
				for (Discapacidad discapacidadAux : listaDiscapacidad) {
					if ((discapacidadAux.getIdUsuarioCreacion() == null ? 0
							: discapacidadAux.getIdUsuarioCreacion()) == 1) {
						discapacidad = new Discapacidad();

						discapacidad.setCatalogoDiscapacidad(discapacidadAux.getCatalogoDiscapacidad());
						discapacidad.setPorcentaje(discapacidadAux.getPorcentaje());
						discapacidad.setRegistroEstudiante(registroEstudiante);
						discapacidad.setCarnetConadis("");

						discapacidad.setEstado("1");
						discapacidad.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
						discapacidad.setIpUsuario(sesionControlador.getIpAdressLocal());
						discapacidad.setFechaCreacion(new Date());

						// Registrar dato discapacidad.
						discapacidadFacadeLocal.create(discapacidad);
					}
				}
			}
		}
	}

	public void datosAuditables() {
		inscripcion.setEstado("1");
		inscripcion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		inscripcion.setFechaCreacion(new Date());
		inscripcion.setIpUsuario(sesionControlador.getIpAdressLocal());

		registroEstudiante.setEstado("1");
		registroEstudiante.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		registroEstudiante.setFechaCreacion(new Date());
		registroEstudiante.setIpUsuario(sesionControlador.getIpAdressLocal());

		ubicacion.setEstado("1");
		ubicacion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		ubicacion.setFechaCreacion(new Date());
		ubicacion.setIpUsuario(sesionControlador.getIpAdressLocal());
	}

	public void tratarCatalogosRegistroEstudiante() {
		registroEstudiante.setCatalogoActividadEconomica(
				catalogoEbjaFacadeLocal.find(registroEstudiante.getCatalogoActividadEconomica().getId()));
		registroEstudiante
				.setCatalogoEstadoCivil(catalogoFacadeLocal.find(registroEstudiante.getCatalogoEstadoCivil().getId()));
		registroEstudiante.setCatalogoGenero(catalogoFacadeLocal.find(registroEstudiante.getCatalogoGenero().getId()));

		if (registroEstudiante.getCatalogoNacionalidad().getId() == null) {
			registroEstudiante.setCatalogoNacionalidad(null);
		}

		registroEstudiante.setCatalogoSituacionLaboral(
				catalogoEbjaFacadeLocal.find(registroEstudiante.getCatalogoSituacionLaboral().getId()));
		registroEstudiante.setCatalogoTipoIdentificacion(
				catalogoFacadeLocal.find(registroEstudiante.getCatalogoTipoIdentificacion().getId()));

		if (registroEstudiante.getPais().getId() == null) {
			registroEstudiante.setPais(null);
		}

		if (registroEstudiante.getCatalogoDocumento().getId() == null) {
			registroEstudiante.setCatalogoDocumento(null);
		}

		if (registroEstudiante.getCatalogoMotivo().getId() == null) {
			registroEstudiante.setCatalogoMotivo(null);
		}

		// Validar datos de Catalogo Documento y Catalogo Motivo.
		if (inscripcion.getPresentaCertificado().equals("1")) {
			registroEstudiante.setCatalogoMotivo(null);
		} else {
			registroEstudiante.setCatalogoDocumento(null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void verCertificado() {
		try {
			Map<String, Object> parametros = new HashMap();
			ReporteJasper report = new ReporteJasper();

			Map<String, String> registroEstudianteDTOMap = BeanUtils.describe(registroEstudianteDTO);

			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			String fechaCreacion = formateador.format(new Date());

			String programa = inscripcion.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma()
					.getProgramaEducativo().getNombrePrograma();
			String periodo = inscripcion.getProgramaGrado().getProgramaEbja().getGrupoFasePrograma().getNombre();
			String codInscripcion = inscripcion.getNemonico();

			parametros.put("fechaCreacion", fechaCreacion);
			parametros.put("programa", programa);
			parametros.put("periodo", periodo);
			parametros.put("codInscripcion", codInscripcion);

			parametros.put("registroEstudianteDTO", registroEstudianteDTOMap);

			List<Object> reportList = new ArrayList<Object>();
			reportList.add("0");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
			programaEbja = programaEbjaFacadeLocal.find(programaGradoFacadeLocal
					.buscarProgramaGrado(gradoOfertaSeleccionadoInscripcion, programaOfertaInscripcion)
					.getProgramaEbja().getId());
			if (programaEbja.getModalidad().getNemonico().equals("MOD-03")) {
				report.generarReporteNavegador(parametros, dataSource, "inscripcionVirtual");
			} else {
				report.generarReporteNavegador(parametros, dataSource, "inscripcionReporte");
			}

			visibleClVerFormulario = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void datosParaReporte() {
		registroEstudianteDTO.setApellidosNombres(registroEstudiante.getApellidosNombres());
		registroEstudianteDTO.setNumeroIdentificacion(registroEstudiante.getNumeroIdentificacion());
		registroEstudianteDTO.setCorreoElectronico(registroEstudiante.getCorreoElectronico());
		registroEstudianteDTO.setTelefono(registroEstudiante.getTelefono());
		ProgramaGrado programaGrado = inscripcion.getProgramaGrado();
		registroEstudianteDTO.setNomProgramaEbja(
				programaGrado.getProgramaEbja().getNombre() + " - " + programaGrado.getGrado().getDescripcion());
		registroEstudianteDTO.setNomPais(ubicacion.getPais().getNombre());
		registroEstudianteDTO.setCodigoSenpladesDistrito(distrito.getCodigoSenpladesDistrito());
		registroEstudianteDTO.setCodigoPostal(ubicacion.getCodigoPostal());
		registroEstudianteDTO.setNomProvincia(provincia.getDescripcion());
		registroEstudianteDTO.setApellidosNombresUsuarioTmp(inscripcion.getApellidosNombresUsuario());
		registroEstudianteDTO.setIdInscripcion(inscripcion.getId());
		registroEstudianteDTO.setNumeroIdentificacionUsuarioTmp(inscripcion.getNumeroIdentificacionUsuario());
		registroEstudianteDTO.setCallePrincipal(ubicacion.getCallePrincipal());
		registroEstudianteDTO.setNomCanton(circuito.getCodigoSenpladesCircuito());
		registroEstudianteDTO.setCodRegistroEstudiante(registroEstudiante.getId().toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		registroEstudianteDTO
				.setFechaInicioClases(dateFormat.format(programaGrado.getProgramaEbja().getFechaInicioClases()));
	}

	public void obtenerEstadoZona() {
		nombrePais = paisFacadeLocal.find(ubicacion.getPais().getId()).getNombre();
		visibleZonaDetalle = false;
		if (nombrePais.equals("ECUADOR")) {
			visibleZona = true;
			visibleEstado = false;
		} else {
			visibleZona = false;
			visibleEstado = true;
		}
		ubicacion.setCallePrincipal("");
	}

	public void obtenerZona() {
		if (zona.getId() != null) {
			zona = zonaFacadeLocal.find(zona.getId());
			listaProvincia = new ArrayList<Provincia>();
			listaProvincia = provinciaFacadeLocal.listaProvinciaPorZona(zona.getCodigoSenpladesZona());
		}
	}

	public void obtenerProvincia() {
		provincia = provinciaFacadeLocal.find(provincia.getId());
		listaCanton = cantonFacadeLocal.listaCantonPorProvincia(provincia.getCodigoProvincia());
		zona.setId(null);
		canton.setId(null);
		distrito.setId(null);
		parroquia.setId(null);
		circuito.setId(null);
	}

	public void obtenerCanton() {
		canton = cantonFacadeLocal.find(canton.getId());
		listaParroquia = parroquiaFacadeLocal.listaParroquiaPorCanton(canton.getCodigoCanton());
		zona.setId(null);
		distrito.setId(null);
		parroquia.setId(null);
		circuito.setId(null);
	}

	public void obtenerParroquia() {
		parroquia = parroquiaFacadeLocal.find(parroquia.getId());
		ubicacion.setParroquia(parroquia);

		// Obtener lista específica del circuito. (20180914)
		List<Circuito> listaCircuitoAux = new ArrayList<>();
		listaCircuitoAux = circuitoFacadeLocal.buscarPorProvCantParr(provincia.getId(), canton.getId(),
				parroquia.getId());
		if (!listaCircuitoAux.isEmpty()) {
			circuito = listaCircuitoAux.get(0);
			ubicacion.setCircuito(circuito);

			// Obtener el distrito. (20180914)
			distrito = distritoFacadeLocal.buscarPorProvCantParrCirc(provincia.getId(), canton.getId(),
					parroquia.getId(), circuito.getId());
			if (distrito != null) {
				zona = zonaFacadeLocal.find(distrito.getIdZona().getId());
			}
		}
	}

	// Mostrar Panel de acuerdo a lo solicitado en el OneMenu. (jbrito-20180904)
	public void mostrarPanelCUE() {
		cueBuscar = "";
		ubicacion.setCallePrincipal("");

		disabledUbicacionGeografica = true;

		provincia = new Provincia();
		canton = new Canton();
		parroquia = new Parroquia();

		zona = new Zona();
		distrito = new Distrito();
		circuito = new Circuito();

		if (tieneCUE.equals("1")) {
			visibleUbicacionCUE = true;
			visibleZonaDetalle = false;

			ubicacion.setCircuito(null);
			ubicacion.setParroquia(null);
		} else {
			visibleUbicacionCUE = false;
			visibleZonaDetalle = true;

			ubicacion.setNumeroSuministro(null);
			ubicacion.setCodigoPostal(null);
			ubicacion.setCoordenadaX(null);
			ubicacion.setCoordenadaY(null);

			listaCanton = null;
			listaParroquia = null;

			disabledCmbProvincia = false;
			disabledCmbCanton = false;
			disabledCmbParroquia = false;
		}
	}

	// Obtener datos del lugar donde desea trasladarse el Docente. (jbrito-20151222)
	public void buscarUbicacionCUE() {
		suministroLuz = suministroLuzFacadeLocal.obtenerSuministroXCUE(cueBuscar);
		if (suministroLuz != null) {
			visibleZonaDetalle = true;

			// Poblar listas con todos los registros.
			listaCanton = cantonFacadeLocal.findAll();
			listaParroquia = parroquiaFacadeLocal.findAll();

			ubicacion.setNumeroSuministro(cueBuscar);
			zona = zonaFacadeLocal.buscarPorCodigoSenplades(suministroLuz.getZona());
			provincia = provinciaFacadeLocal.buscarPorCodigoProvincia(suministroLuz.getDpaProvin());
			if (provincia != null) {
				disabledCmbProvincia = true;
			}
			canton = cantonFacadeLocal.buscarPorCodigoCanton(suministroLuz.getDpaCanton());
			if (canton != null) {
				disabledCmbCanton = true;
			}
			parroquia = parroquiaFacadeLocal.buscarPorCodigoParroquia(suministroLuz.getDpaParroq());
			if (parroquia != null) {
				disabledCmbParroquia = true;
				ubicacion.setParroquia(parroquia);
			}

			distrito = distritoFacadeLocal.buscarPorCodigoDistrito(suministroLuz.getCodDistri());

			circuito = circuitoFacadeLocal.buscarPorCodigoCircuito(suministroLuz.getCodCircui());
			if (circuito != null) {
				ubicacion.setCircuito(circuito);
				listaEstablecimientos = institucionEstablecimiento.institucionFindByCircuito(circuito.getId());
			}

			// Asignar coordenadas desde suministroLuz a ubicacion.
			ubicacion.setCoordenadaX(suministroLuz.getCX());
			ubicacion.setCoordenadaY(suministroLuz.getCY());

			
		}
	}

	/*
	 * Generate Getters and Setters
	 */
	public List<Canton> getListaCanton() {
		return listaCanton;
	}

	public void setListaCanton(List<Canton> listaCanton) {
		this.listaCanton = listaCanton;
	}

	public String getCedulaBuscar() {
		return cedulaBuscar;
	}

	public void setCedulaBuscar(String cedulaBuscar) {
		this.cedulaBuscar = cedulaBuscar;
	}

	public CeduladoMeducacion getCeduladoMeducacion() {
		return ceduladoMeducacion;
	}

	public void setCeduladoMeducacion(CeduladoMeducacion ceduladoMeducacion) {
		this.ceduladoMeducacion = ceduladoMeducacion;
	}

	public String getCueBuscar() {
		return cueBuscar;
	}

	public void setCueBuscar(String cueBuscar) {
		this.cueBuscar = cueBuscar;
	}

	public boolean isVisiblePanelA() {
		return visiblePanelA;
	}

	public void setVisiblePanelA(boolean visiblePanelA) {
		this.visiblePanelA = visiblePanelA;
	}

	public boolean isVisiblePanelB() {
		return visiblePanelB;
	}

	public void setVisiblePanelB(boolean visiblePanelB) {
		this.visiblePanelB = visiblePanelB;
	}

	public boolean isVisiblePanelC() {
		return visiblePanelC;
	}

	public void setVisiblePanelC(boolean visiblePanelC) {
		this.visiblePanelC = visiblePanelC;
	}

	public boolean isVisiblePanelD() {
		return visiblePanelD;
	}

	public void setVisiblePanelD(boolean visiblePanelD) {
		this.visiblePanelD = visiblePanelD;
	}

	public boolean isVisibleBotonEnviar() {
		return visibleBotonEnviar;
	}

	public void setVisibleBotonEnviar(boolean visibleBotonEnviar) {
		this.visibleBotonEnviar = visibleBotonEnviar;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
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

	public RegistroEstudiante getregistroEstudiante() {
		return registroEstudiante;
	}

	public void setregistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

	public boolean isdisabledIdentificacionAspirante() {
		return disabledIdentificacionAspirante;
	}

	public void setdisabledIdentificacionAspirante(boolean disabledIdentificacionAspirante) {
		this.disabledIdentificacionAspirante = disabledIdentificacionAspirante;
	}

	public boolean isVisiblePanelCUE() {
		return visiblePanelCUE;
	}

	public void setVisiblePanelCUE(boolean visiblePanelCUE) {
		this.visiblePanelCUE = visiblePanelCUE;
	}

	public boolean isReadonlyNumeroIdentificacion() {
		return readonlyNumeroIdentificacion;
	}

	public void setReadonlyNumeroIdentificacion(boolean readonlyNumeroIdentificacion) {
		this.readonlyNumeroIdentificacion = readonlyNumeroIdentificacion;
	}

	public boolean isReadonlyCue() {
		return readonlyCue;
	}

	public void setReadonlyCue(boolean readonlyCue) {
		this.readonlyCue = readonlyCue;
	}

	public boolean isDisabledCmbProvincia() {
		return disabledCmbProvincia;
	}

	public void setDisabledCmbProvincia(boolean disabledCmbProvincia) {
		this.disabledCmbProvincia = disabledCmbProvincia;
	}

	public boolean isDisabledCmbCanton() {
		return disabledCmbCanton;
	}

	public void setDisabledCmbCanton(boolean disabledCmbCanton) {
		this.disabledCmbCanton = disabledCmbCanton;
	}

	public boolean isDisabledCmbParroquia() {
		return disabledCmbParroquia;
	}

	public void setDisabledCmbParroquia(boolean disabledCmbParroquia) {
		this.disabledCmbParroquia = disabledCmbParroquia;
	}

	public boolean isDisabledCmbEspecialidad() {
		return disabledCmbEspecialidad;
	}

	public void setDisabledCmbEspecialidad(boolean disabledCmbEspecialidad) {
		this.disabledCmbEspecialidad = disabledCmbEspecialidad;
	}

	public String getTextosalida() {
		return textosalida;
	}

	public void setTextosalida(String textosalida) {
		this.textosalida = textosalida;
	}

	public int getTieneQuinceAniosOMas() {
		return tieneQuinceAniosOMas;
	}

	public void setTieneQuinceAniosOMas(int tieneQuinceAniosOMas) {
		this.tieneQuinceAniosOMas = tieneQuinceAniosOMas;
	}

	public int getTieneRezagoEducativo() {
		return tieneRezagoEducativo;
	}

	public void setTieneRezagoEducativo(int tieneRezagoEducativo) {
		this.tieneRezagoEducativo = tieneRezagoEducativo;
	}

	public boolean isDisabledQuinceAniosOMas() {
		return disabledQuinceAniosOMas;
	}

	public void setDisabledQuinceAniosOMas(boolean disabledQuinceAniosOMas) {
		this.disabledQuinceAniosOMas = disabledQuinceAniosOMas;
	}

	public boolean isDisabledRezagoEducativo() {
		return disabledRezagoEducativo;
	}

	public void setDisabledRezagoEducativo(boolean disabledRezagoEducativo) {
		this.disabledRezagoEducativo = disabledRezagoEducativo;
	}

	public String getDesTipoDocumento() {
		return desTipoDocumento;
	}

	public void setDesTipoDocumento(String desTipoDocumento) {
		this.desTipoDocumento = desTipoDocumento;
	}

	public boolean isReadonlyApellidosNombres() {
		return readonlyApellidosNombres;
	}

	public void setReadonlyApellidosNombres(boolean readonlyApellidosNombres) {
		this.readonlyApellidosNombres = readonlyApellidosNombres;
	}

	public boolean isDisabledTipoDocumento() {
		return disabledTipoDocumento;
	}

	public void setDisabledTipoDocumento(boolean disabledTipoDocumento) {
		this.disabledTipoDocumento = disabledTipoDocumento;
	}

	public boolean isReadonlyFechaNacimiento() {
		return readonlyFechaNacimiento;
	}

	public void setReadonlyFechaNacimiento(boolean readonlyFechaNacimiento) {
		this.readonlyFechaNacimiento = readonlyFechaNacimiento;
	}

	public boolean isDisabledIngresarIdentificacion() {
		return disabledIngresarIdentificacion;
	}

	public void setDisabledIngresarIdentificacion(boolean disabledIngresarIdentificacion) {
		this.disabledIngresarIdentificacion = disabledIngresarIdentificacion;
	}

	public RegistroEstudiante getRegistroEstudiante() {
		return registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

	public boolean isDisabledIngresarDatosAdicionales() {
		return disabledIngresarDatosAdicionales;
	}

	public void setDisabledIngresarDatosAdicionales(boolean disabledIngresarDatosAdicionales) {
		this.disabledIngresarDatosAdicionales = disabledIngresarDatosAdicionales;
	}

	public boolean isDisabledUbicacionGeografica() {
		return disabledUbicacionGeografica;
	}

	public void setDisabledUbicacionGeografica(boolean disabledUbicacionGeografica) {
		this.disabledUbicacionGeografica = disabledUbicacionGeografica;
	}

	public boolean isReadonlyApellidosNombresRegistra() {
		return readonlyApellidosNombresRegistra;
	}

	public void setReadonlyApellidosNombresRegistra(boolean readonlyApellidosNombresRegistra) {
		this.readonlyApellidosNombresRegistra = readonlyApellidosNombresRegistra;
	}

	public boolean isReadonlyNumeroIdentificacionRegistra() {
		return readonlyNumeroIdentificacionRegistra;
	}

	public void setReadonlyNumeroIdentificacionRegistra(boolean readonlyNumeroIdentificacionRegistra) {
		this.readonlyNumeroIdentificacionRegistra = readonlyNumeroIdentificacionRegistra;
	}

	public boolean isDisabledInformacionRegistro() {
		return disabledInformacionRegistro;
	}

	public void setDisabledInformacionRegistro(boolean disabledInformacionRegistro) {
		this.disabledInformacionRegistro = disabledInformacionRegistro;
	}

	public int getEdadAspirante() {
		return edadAspirante;
	}

	public void setEdadAspirante(int edadAspirante) {
		this.edadAspirante = edadAspirante;
	}

	public boolean isVisibleEdadAspirante() {
		return visibleEdadAspirante;
	}

	public void setVisibleEdadAspirante(boolean visibleEdadAspirante) {
		this.visibleEdadAspirante = visibleEdadAspirante;
	}

	public int getCodGenero() {
		return codGenero;
	}

	public void setCodGenero(int codGenero) {
		this.codGenero = codGenero;
	}

	public int getCodEstadoCivil() {
		return codEstadoCivil;
	}

	public void setCodEstadoCivil(int codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public boolean isDisabledGenero() {
		return disabledGenero;
	}

	public void setDisabledGenero(boolean disabledGenero) {
		this.disabledGenero = disabledGenero;
	}

	public boolean isDisabledAceptarInformacion() {
		return disabledAceptarInformacion;
	}

	public void setDisabledAceptarInformacion(boolean disabledAceptarInformacion) {
		this.disabledAceptarInformacion = disabledAceptarInformacion;
	}

	public boolean isDisabledEstadoCivil() {
		return disabledEstadoCivil;
	}

	public void setDisabledEstadoCivil(boolean disabledEstadoCivil) {
		this.disabledEstadoCivil = disabledEstadoCivil;
	}

	public boolean isDisabledEtnia() {
		return disabledEtnia;
	}

	public void setDisabledEtnia(boolean disabledEtnia) {
		this.disabledEtnia = disabledEtnia;
	}

	public boolean isDisabledSituacionLaboral() {
		return disabledSituacionLaboral;
	}

	public void setDisabledSituacionLaboral(boolean disabledSituacionLaboral) {
		this.disabledSituacionLaboral = disabledSituacionLaboral;
	}

	public boolean isDisabledActividadEconomica() {
		return disabledActividadEconomica;
	}

	public void setDisabledActividadEconomica(boolean disabledActividadEconomica) {
		this.disabledActividadEconomica = disabledActividadEconomica;
	}

	public boolean isDisabledDatosFamiliares() {
		return disabledDatosFamiliares;
	}

	public void setDisabledDatosFamiliares(boolean disabledDatosFamiliares) {
		this.disabledDatosFamiliares = disabledDatosFamiliares;
	}

	public boolean isDisabledNacionalidad() {
		return disabledNacionalidad;
	}

	public void setDisabledNacionalidad(boolean disabledNacionalidad) {
		this.disabledNacionalidad = disabledNacionalidad;
	}

	public boolean isDisabledTelefono() {
		return disabledTelefono;
	}

	public void setDisabledTelefono(boolean disabledTelefono) {
		this.disabledTelefono = disabledTelefono;
	}

	public boolean isDisabledEmail() {
		return disabledEmail;
	}

	public void setDisabledEmail(boolean disabledEmail) {
		this.disabledEmail = disabledEmail;
	}

	public boolean isDisabledUltimoAnioAprobado() {
		return disabledUltimoAnioAprobado;
	}

	public void setDisabledUltimoAnioAprobado(boolean disabledUltimoAnioAprobado) {
		this.disabledUltimoAnioAprobado = disabledUltimoAnioAprobado;
	}

	public boolean isDisabledRegistroEstudiante() {
		return disabledRegistroEstudiante;
	}

	public void setDisabledRegistroEstudiante(boolean disabledRegistroEstudiante) {
		this.disabledRegistroEstudiante = disabledRegistroEstudiante;
	}

	public boolean isDisabledRezagoEducativo1() {
		return disabledRezagoEducativo1;
	}

	public void setDisabledRezagoEducativo1(boolean disabledRezagoEducativo1) {
		this.disabledRezagoEducativo1 = disabledRezagoEducativo1;
	}

	public boolean isDisabledItDireccion() {
		return disabledItDireccion;
	}

	public void setDisabledItDireccion(boolean disabledItDireccion) {
		this.disabledItDireccion = disabledItDireccion;
	}

	public boolean isRenderedMensajeGuardar() {
		return renderedMensajeGuardar;
	}

	public void setRenderedMensajeGuardar(boolean renderedMensajeGuardar) {
		this.renderedMensajeGuardar = renderedMensajeGuardar;
	}

	public String getTextoFormularioSatisfactorio() {
		return textoFormularioSatisfactorio;
	}

	public void setTextoFormularioSatisfactorio(String textoFormularioSatisfactorio) {
		this.textoFormularioSatisfactorio = textoFormularioSatisfactorio;
	}

	public boolean isDisabledCmbPais() {
		return disabledCmbPais;
	}

	public void setDisabledCmbPais(boolean disabledCmbPais) {
		this.disabledCmbPais = disabledCmbPais;
	}

	public List<Pais> getListaPais() {
		return listaPais;
	}

	public void setListaPais(List<Pais> listaPais) {
		this.listaPais = listaPais;
	}

	public boolean isVisibleEstado() {
		return visibleEstado;
	}

	public void setVisibleEstado(boolean visibleEstado) {
		this.visibleEstado = visibleEstado;
	}

	public boolean isVisibleZona() {
		return visibleZona;
	}

	public void setVisibleZona(boolean visibleZona) {
		this.visibleZona = visibleZona;
	}

	public boolean isDisabledParentesco() {
		return disabledParentesco;
	}

	public void setDisabledParentesco(boolean disabledParentesco) {
		this.disabledParentesco = disabledParentesco;
	}

	public boolean isDisabledDocumentoHabilitante() {
		return disabledDocumentoHabilitante;
	}

	public void setDisabledDocumentoHabilitante(boolean disabledDocumentoHabilitante) {
		this.disabledDocumentoHabilitante = disabledDocumentoHabilitante;
	}

	public boolean isVisibleMensajeDH() {
		return visibleMensajeDH;
	}

	public void setVisibleMensajeDH(boolean visibleMensajeDH) {
		this.visibleMensajeDH = visibleMensajeDH;
	}

	public boolean isDisabledCmbEstadoUsa() {
		return disabledCmbEstadoUsa;
	}

	public void setDisabledCmbEstadoUsa(boolean disabledCmbEstadoUsa) {
		this.disabledCmbEstadoUsa = disabledCmbEstadoUsa;
	}

	public boolean isDisabledCmbCiudadUsa() {
		return disabledCmbCiudadUsa;
	}

	public void setDisabledCmbCiudadUsa(boolean disabledCmbCiudadUsa) {
		this.disabledCmbCiudadUsa = disabledCmbCiudadUsa;
	}

	public boolean isDisabledItCalle() {
		return disabledItCalle;
	}

	public void setDisabledItCalle(boolean disabledItCalle) {
		this.disabledItCalle = disabledItCalle;
	}

	public boolean isDisabledItCodigoPostal() {
		return disabledItCodigoPostal;
	}

	public void setDisabledItCodigoPostal(boolean disabledItCodigoPostal) {
		this.disabledItCodigoPostal = disabledItCodigoPostal;
	}

	public boolean isVisiblePgParentesco() {
		return visiblePgParentesco;
	}

	public void setVisiblePgParentesco(boolean visiblePgParentesco) {
		this.visiblePgParentesco = visiblePgParentesco;
	}

	public boolean isVisibleClVerFormulario() {
		return visibleClVerFormulario;
	}

	public void setVisibleClVerFormulario(boolean visibleClVerFormulario) {
		this.visibleClVerFormulario = visibleClVerFormulario;
	}

	public boolean isDisabledNacionalidadIndigena() {
		return disabledNacionalidadIndigena;
	}

	public void setDisabledNacionalidadIndigena(boolean disabledNacionalidadIndigena) {
		this.disabledNacionalidadIndigena = disabledNacionalidadIndigena;
	}

	public boolean isDisabledTiempoEstudio() {
		return disabledTiempoEstudio;
	}

	public void setDisabledTiempoEstudio(boolean disabledTiempoEstudio) {
		this.disabledTiempoEstudio = disabledTiempoEstudio;
	}

	public boolean isDisabledItBusquedaPromocion() {
		return disabledItBusquedaPromocion;
	}

	public void setDisabledItBusquedaPromocion(boolean disabledItBusquedaPromocion) {
		this.disabledItBusquedaPromocion = disabledItBusquedaPromocion;
	}

	public boolean isVisibleTieneDH() {
		return visibleTieneDH;
	}

	public void setVisibleTieneDH(boolean visibleTieneDH) {
		this.visibleTieneDH = visibleTieneDH;
	}

	public String getTextosalidaPanelA() {
		return textosalidaPanelA;
	}

	public void setTextosalidaPanelA(String textosalidaPanelA) {
		this.textosalidaPanelA = textosalidaPanelA;
	}

	public List<Catalogo> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<Catalogo> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public List<Catalogo> getListaGenero() {
		return listaGenero;
	}

	public void setListaGenero(List<Catalogo> listaGenero) {
		this.listaGenero = listaGenero;
	}

	public List<Catalogo> getListaEstadoCivil() {
		return listaEstadoCivil;
	}

	public void setListaEstadoCivil(List<Catalogo> listaEstadoCivil) {
		this.listaEstadoCivil = listaEstadoCivil;
	}

	public List<Etnia> getListaEtnia() {
		return listaEtnia;
	}

	public void setListaEtnia(List<Etnia> listaEtnia) {
		this.listaEtnia = listaEtnia;
	}

	public boolean isNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(boolean numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public boolean isDisabledDiscapacidad() {
		return disabledDiscapacidad;
	}

	public void setDisabledDiscapacidad(boolean disabledDiscapacidad) {
		this.disabledDiscapacidad = disabledDiscapacidad;
	}

	public String getTieneDiscapacidad() {
		return tieneDiscapacidad;
	}

	public void setTieneDiscapacidad(String tieneDiscapacidad) {
		this.tieneDiscapacidad = tieneDiscapacidad;
	}

	public List<Discapacidad> getListaDiscapacidad() {
		return listaDiscapacidad;
	}

	public void setListaDiscapacidad(List<Discapacidad> listaDiscapacidad) {
		this.listaDiscapacidad = listaDiscapacidad;
	}

	public String getTieneBono() {
		return tieneBono;
	}

	public void setTieneBono(String tieneBono) {
		this.tieneBono = tieneBono;
	}

	public boolean isDisabledBono() {
		return disabledBono;
	}

	public void setDisabledBono(boolean disabledBono) {
		this.disabledBono = disabledBono;
	}

	public List<Grado> getListaGrado() {
		return listaGrado;
	}

	public void setListaGrado(List<Grado> listaGrado) {
		this.listaGrado = listaGrado;
	}

	public List<ProgramaEbja> getListaProgramaEbja() {
		return listaProgramaEbja;
	}

	public void setListaProgramaEbja(List<ProgramaEbja> listaProgramaEbja) {
		this.listaProgramaEbja = listaProgramaEbja;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	public List<Pais> getListaPaisUbicacion() {
		return listaPaisUbicacion;
	}

	public void setListaPaisUbicacion(List<Pais> listaPaisUbicacion) {
		this.listaPaisUbicacion = listaPaisUbicacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isVisibleUbicacionCUE() {
		return visibleUbicacionCUE;
	}

	public void setVisibleUbicacionCUE(boolean visibleUbicacionCUE) {
		this.visibleUbicacionCUE = visibleUbicacionCUE;
	}

	public boolean isReadonlyUbicacionCUE() {
		return readonlyUbicacionCUE;
	}

	public void setReadonlyUbicacionCUE(boolean readonlyUbicacionCUE) {
		this.readonlyUbicacionCUE = readonlyUbicacionCUE;
	}

	public String getTieneCUE() {
		return tieneCUE;
	}

	public void setTieneCUE(String tieneCUE) {
		this.tieneCUE = tieneCUE;
	}

	public List<Zona> getListaZona() {
		return listaZona;
	}

	public void setListaZona(List<Zona> listaZona) {
		this.listaZona = listaZona;
	}

	public List<Provincia> getListaProvincia() {
		return listaProvincia;
	}

	public void setListaProvincia(List<Provincia> listaProvincia) {
		this.listaProvincia = listaProvincia;
	}

	public List<Parroquia> getListaParroquia() {
		return listaParroquia;
	}

	public void setListaParroquia(List<Parroquia> listaParroquia) {
		this.listaParroquia = listaParroquia;
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

	public boolean isVisibleZonaDetalle() {
		return visibleZonaDetalle;
	}

	public void setVisibleZonaDetalle(boolean visibleZonaDetalle) {
		this.visibleZonaDetalle = visibleZonaDetalle;
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

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public boolean isVisibleNacionalidad() {
		return visibleNacionalidad;
	}

	public void setVisibleNacionalidad(boolean visibleNacionalidad) {
		this.visibleNacionalidad = visibleNacionalidad;
	}

	public boolean isVisibleCaracteristicasOE() {
		return visibleCaracteristicasOE;
	}

	public void setVisibleCaracteristicasOE(boolean visibleCaracteristicasOE) {
		this.visibleCaracteristicasOE = visibleCaracteristicasOE;
	}

	public ProgramaEbjaFacadeLocal getProgramaEbjaFacadeLocal() {
		return programaEbjaFacadeLocal;
	}

	public void setProgramaEbjaFacadeLocal(ProgramaEbjaFacadeLocal programaEbjaFacadeLocal) {
		this.programaEbjaFacadeLocal = programaEbjaFacadeLocal;
	}

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public ReglaNegocio getReglaNegocio() {
		return reglaNegocio;
	}

	public void setReglaNegocio(ReglaNegocio reglaNegocio) {
		this.reglaNegocio = reglaNegocio;
	}

	public List<Catalogo> getListaNacionalidadIndigena() {
		return listaNacionalidadIndigena;
	}

	public void setListaNacionalidadIndigena(List<Catalogo> listaNacionalidadIndigena) {
		this.listaNacionalidadIndigena = listaNacionalidadIndigena;
	}

	public List<CatalogoEbja> getListaActividadEconomica() {
		return listaActividadEconomica;
	}

	public void setListaActividadEconomica(List<CatalogoEbja> listaActividadEconomica) {
		this.listaActividadEconomica = listaActividadEconomica;
	}

	public List<CatalogoEbja> getListaSituacionLaboral() {
		return listaSituacionLaboral;
	}

	public void setListaSituacionLaboral(List<CatalogoEbja> listaSituacionLaboral) {
		this.listaSituacionLaboral = listaSituacionLaboral;
	}

	public void setListaDatosFamiliares(List<CatalogoEbja> listaDatosFamiliares) {
		this.listaDatosFamiliares = listaDatosFamiliares;
	}

	public void setListaDiscapacidadCatalogo(List<CatalogoEbja> listaDiscapacidadCatalogo) {
		this.listaDiscapacidadCatalogo = listaDiscapacidadCatalogo;
	}

	public void setListaRezagoEducativo(List<CatalogoEbja> listaRezagoEducativo) {
		this.listaRezagoEducativo = listaRezagoEducativo;
	}

	public boolean isVisibleCbReiniciar() {
		return visibleCbReiniciar;
	}

	public void setVisibleCbReiniciar(boolean visibleCbReiniciar) {
		this.visibleCbReiniciar = visibleCbReiniciar;
	}

	public boolean isVisiblePgNacionalidadIndigena() {
		return visiblePgNacionalidadIndigena;
	}

	public void setVisiblePgNacionalidadIndigena(boolean visiblePgNacionalidadIndigena) {
		this.visiblePgNacionalidadIndigena = visiblePgNacionalidadIndigena;
	}

	public List<CatalogoEbja> getListaDocumentoPresentado() {
		return listaDocumentoPresentado;
	}

	public void setListaDocumentoPresentado(List<CatalogoEbja> listaDocumentoPresentado) {
		this.listaDocumentoPresentado = listaDocumentoPresentado;
	}

	public boolean isVisiblePgDocumentoPresentado() {
		return visiblePgDocumentoPresentado;
	}

	public void setVisiblePgDocumentoPresentado(boolean visiblePgDocumentoPresentado) {
		this.visiblePgDocumentoPresentado = visiblePgDocumentoPresentado;
	}

	public boolean isDisabledDocumentoPresentado() {
		return disabledDocumentoPresentado;
	}

	public void setDisabledDocumentoPresentado(boolean disabledDocumentoPresentado) {
		this.disabledDocumentoPresentado = disabledDocumentoPresentado;
	}

	public List<CatalogoEbja> getListaDocumentoMotivo() {
		return listaDocumentoMotivo;
	}

	public void setListaDocumentoMotivo(List<CatalogoEbja> listaDocumentoMotivo) {
		this.listaDocumentoMotivo = listaDocumentoMotivo;
	}

	public boolean isVisiblePgDocumentoMotivo() {
		return visiblePgDocumentoMotivo;
	}

	public void setVisiblePgDocumentoMotivo(boolean visiblePgDocumentoMotivo) {
		this.visiblePgDocumentoMotivo = visiblePgDocumentoMotivo;
	}

	public boolean isDisabledDocumentoMotivoI() {
		return disabledDocumentoMotivoI;
	}

	public void setDisabledDocumentoMotivoI(boolean disabledDocumentoMotivoI) {
		this.disabledDocumentoMotivoI = disabledDocumentoMotivoI;
	}

	public List<CatalogoEbja> getListaDatosFamiliares() {
		return listaDatosFamiliares;
	}

	public List<CatalogoEbja> getListaDiscapacidadCatalogo() {
		return listaDiscapacidadCatalogo;
	}

	public List<CatalogoEbja> getListaRezagoEducativo() {
		return listaRezagoEducativo;
	}

	public boolean isVisiblePgCargarPDF() {
		return visiblePgCargarPDF;
	}

	public void setVisiblePgCargarPDF(boolean visiblePgCargarPDF) {
		this.visiblePgCargarPDF = visiblePgCargarPDF;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public boolean isDisabledPgCargarPDF() {
		return disabledPgCargarPDF;
	}

	public void setDisabledPgCargarPDF(boolean disabledPgCargarPDF) {
		this.disabledPgCargarPDF = disabledPgCargarPDF;
	}

	public boolean isVisibleNumeroIdentificacion() {
		return visibleNumeroIdentificacion;
	}

	public void setVisibleNumeroIdentificacion(boolean visibleNumeroIdentificacion) {
		this.visibleNumeroIdentificacion = visibleNumeroIdentificacion;
	}

	public boolean isProcesarInscripcionPendiente() {
		return procesarInscripcionPendiente;
	}

	public void setProcesarInscripcionPendiente(boolean procesarInscripcionPendiente) {
		this.procesarInscripcionPendiente = procesarInscripcionPendiente;
	}

	public String getMensajeContinuar() {
		return mensajeContinuar;
	}

	public void setMensajeContinuar(String mensajeContinuar) {
		this.mensajeContinuar = mensajeContinuar;
	}

	public List<ProgramaEducativo> getListaProgramasEducativos() {
		return listaProgramasEducativos;
	}

	public void setListaProgramasEducativos(List<ProgramaEducativo> listaProgramasEducativos) {
		this.listaProgramasEducativos = listaProgramasEducativos;
	}

	public long getFaseProgramaSeleccionado() {
		return faseProgramaSeleccionado;
	}

	public void setFaseProgramaSeleccionado(long faseProgramaSeleccionado) {
		this.faseProgramaSeleccionado = faseProgramaSeleccionado;
	}

	public long getProyectoSelecionado() {
		return proyectoSelecionado;
	}

	public void setProyectoSelecionado(long proyectoSelecionado) {
		this.proyectoSelecionado = proyectoSelecionado;
	}

	public void setProyectoSelecionado(Integer proyectoSelecionado) {
		this.proyectoSelecionado = proyectoSelecionado;
	}

	public List<GrupoFasePrograma> getListaGradoFasePrograma() {
		return listaGradoFasePrograma;
	}

	public void setListaGradoFasePrograma(List<GrupoFasePrograma> listaGradoFasePrograma) {
		this.listaGradoFasePrograma = listaGradoFasePrograma;
	}

	public List<ProgramaEbja> getListaProgramaEbjaInscripcion() {
		return listaProgramaEbjaInscripcion;
	}

	public void setListaProgramaEbjaInscripcion(List<ProgramaEbja> listaProgramaEbjaInscripcion) {
		this.listaProgramaEbjaInscripcion = listaProgramaEbjaInscripcion;
	}

	public String getProgramaOfertaSeleccionado() {
		return programaOfertaSeleccionado;
	}

	public void setProgramaOfertaSeleccionado(String programaOfertaSeleccionado) {
		this.programaOfertaSeleccionado = programaOfertaSeleccionado;
	}

	public String getProgramaOfertaInscripcion() {
		return programaOfertaInscripcion;
	}

	public void setProgramaOfertaInscripcion(String programaOfertaInscripcion) {
		this.programaOfertaInscripcion = programaOfertaInscripcion;
	}

	public List<Grado> getListaGradoInscripcion() {
		return listaGradoInscripcion;
	}

	public void setListaGradoInscripcion(List<Grado> listaGradoInscripcion) {
		this.listaGradoInscripcion = listaGradoInscripcion;
	}

	public Integer getGradoOfertaSeleccionado() {
		return gradoOfertaSeleccionado;
	}

	public void setGradoOfertaSeleccionado(Integer gradoOfertaSeleccionado) {
		this.gradoOfertaSeleccionado = gradoOfertaSeleccionado;
	}

	public boolean isDisabledGradoOfertaAprobadoCmb() {
		return disabledGradoOfertaAprobadoCmb;
	}

	public void setDisabledGradoOfertaAprobadoCmb(boolean disabledGradoOfertaAprobada) {
		this.disabledGradoOfertaAprobadoCmb = disabledGradoOfertaAprobada;
	}

	public Integer getGradoOfertaSeleccionadoInscripcion() {
		return gradoOfertaSeleccionadoInscripcion;
	}

	public void setGradoOfertaSeleccionadoInscripcion(Integer gradoOfertaSeleccionadoInscripcion) {
		this.gradoOfertaSeleccionadoInscripcion = gradoOfertaSeleccionadoInscripcion;
	}

	public Integer getGradoSucesor() {
		return gradoSucesor;
	}

	public void setGradoSucesor(Integer gradoSucesor) {
		this.gradoSucesor = gradoSucesor;
	}

	public List<ProgramaEbja> getListaProgramaEbjaAprobado() {
		return listaProgramaEbjaAprobado;
	}

	public void setListaProgramaEbjaAprobado(List<ProgramaEbja> listaProgramaEbjaAprobado) {
		this.listaProgramaEbjaAprobado = listaProgramaEbjaAprobado;
	}

	public long getFaseProgramaSeleccionadoUltimo() {
		return faseProgramaSeleccionadoUltimo;
	}

	public void setFaseProgramaSeleccionadoUltimo(long faseProgramaSeleccionadoUltimo) {
		this.faseProgramaSeleccionadoUltimo = faseProgramaSeleccionadoUltimo;
	}

	public List<GrupoFasePrograma> getListaGradoFaseProgramaUltimaAprobado() {
		return listaGradoFaseProgramaUltimaAprobado;
	}

	public void setListaGradoFaseProgramaUltimaAprobado(List<GrupoFasePrograma> listaGradoFaseProgramaUltimaAprobado) {
		this.listaGradoFaseProgramaUltimaAprobado = listaGradoFaseProgramaUltimaAprobado;
	}

	/**
	 * @return the inscripcionEdit
	 */
	public Inscripcion getInscripcionEdit() {
		return inscripcionEdit;
	}

	/**
	 * @param inscripcionEdit the inscripcionEdit to set
	 */
	public void setInscripcionEdit(Inscripcion inscripcionEdit) {
		this.inscripcionEdit = inscripcionEdit;
	}

	/**
	 * @return the ubicacionEdit
	 */
	public Ubicacion getUbicacionEdit() {
		return ubicacionEdit;
	}

	/**
	 * @param ubicacionEdit the ubicacionEdit to set
	 */
	public void setUbicacionEdit(Ubicacion ubicacionEdit) {
		this.ubicacionEdit = ubicacionEdit;
	}

	/**
	 * @return the registroEstudianteEdit
	 */
	public RegistroEstudiante getRegistroEstudianteEdit() {
		return registroEstudianteEdit;
	}

	/**
	 * @param registroEstudianteEdit the registroEstudianteEdit to set
	 */
	public void setRegistroEstudianteEdit(RegistroEstudiante registroEstudianteEdit) {
		this.registroEstudianteEdit = registroEstudianteEdit;
	}

	/**
	 * @return the urlCorreo
	 */
	public String getUrlCorreo() {
		return urlCorreo;
	}

	/**
	 * @param urlCorreo the urlCorreo to set
	 */
	public void setUrlCorreo(String urlCorreo) {
		this.urlCorreo = urlCorreo;
	}

	/**
	 * @return the mensajeEnvioCorreo
	 */
	public String getMensajeEnvioCorreo() {
		return mensajeEnvioCorreo;
	}

	/**
	 * @param mensajeEnvioCorreo the mensajeEnvioCorreo to set
	 */
	public void setMensajeEnvioCorreo(String mensajeEnvioCorreo) {
		this.mensajeEnvioCorreo = mensajeEnvioCorreo;
	}

	/**
	 * @return the disabledBotonEnviarCorreo
	 */
	public boolean isDisabledBotonEnviarCorreo() {
		return disabledBotonEnviarCorreo;
	}

	/**
	 * @param disabledBotonEnviarCorreo the disabledBotonEnviarCorreo to set
	 */
	public void setDisabledBotonEnviarCorreo(boolean disabledBotonEnviarCorreo) {
		this.disabledBotonEnviarCorreo = disabledBotonEnviarCorreo;
	}

	/**
	 * @return the requiredEmail
	 */
	public boolean isRequiredEmail() {
		return requiredEmail;
	}

	/**
	 * @param requiredEmail the requiredEmail to set
	 */
	public void setRequiredEmail(boolean requiredEmail) {
		this.requiredEmail = requiredEmail;
	}

	/**
	 * @return the disabledCumpleDocumentacion
	 */
	public boolean isDisabledCumpleDocumentacion() {
		return disabledCumpleDocumentacion;
	}

	/**
	 * @param disabledCumpleDocumentacion the disabledCumpleDocumentacion to set
	 */
	public void setDisabledCumpleDocumentacion(boolean disabledCumpleDocumentacion) {
		this.disabledCumpleDocumentacion = disabledCumpleDocumentacion;
	}

	/**
	 * @return the cumpleDocumentacion
	 */
	public int getCumpleDocumentacion() {
		return cumpleDocumentacion;
	}

	/**
	 * @param cumpleDocumentacion the cumpleDocumentacion to set
	 */
	public void setCumpleDocumentacion(int cumpleDocumentacion) {
		this.cumpleDocumentacion = cumpleDocumentacion;
	}

	/**
	 * @return the disabledPanelB
	 */
	public boolean isDisabledPanelB() {
		return disabledPanelB;
	}

	/**
	 * @param disabledPanelB the disabledPanelB to set
	 */
	public void setDisabledPanelB(boolean disabledPanelB) {
		this.disabledPanelB = disabledPanelB;
	}

	/**
	 * @return the disabledBtnAceptarOferta
	 */
	public boolean isDisabledBtnAceptarOferta() {
		return disabledBtnAceptarOferta;
	}

	/**
	 * @param disabledBtnAceptarOferta the disabledBtnAceptarOferta to set
	 */
	public void setDisabledBtnAceptarOferta(boolean disabledBtnAceptarOferta) {
		this.disabledBtnAceptarOferta = disabledBtnAceptarOferta;
	}

	/**
	 * @return the disabledFechaNacimiento
	 */
	public boolean isDisabledFechaNacimiento() {
		return disabledFechaNacimiento;
	}

	/**
	 * @param disabledFechaNacimiento the disabledFechaNacimiento to set
	 */
	public void setDisabledFechaNacimiento(boolean disabledFechaNacimiento) {
		this.disabledFechaNacimiento = disabledFechaNacimiento;
	}

	/**
	 * @return the disabledCmbDocumentoPresentado
	 */
	public boolean isDisabledCmbDocumentoPresentado() {
		return disabledCmbDocumentoPresentado;
	}

	/**
	 * @param disabledCmbDocumentoPresentado the disabledCmbDocumentoPresentado to
	 *                                       set
	 */
	public void setDisabledCmbDocumentoPresentado(boolean disabledCmbDocumentoPresentado) {
		this.disabledCmbDocumentoPresentado = disabledCmbDocumentoPresentado;
	}

	/**
	 * @return the disabledCmbDocumentoMotivo
	 */
	public boolean isDisabledCmbDocumentoMotivo() {
		return disabledCmbDocumentoMotivo;
	}

	/**
	 * @param disabledCmbDocumentoMotivo the disabledCmbDocumentoMotivo to set
	 */
	public void setDisabledCmbDocumentoMotivo(boolean disabledCmbDocumentoMotivo) {
		this.disabledCmbDocumentoMotivo = disabledCmbDocumentoMotivo;
	}

	public List<InstitucEstablec> getListaEstablecimientos() {
		return listaEstablecimientos;
	}

	public void setListaEstablecimientos(List<InstitucEstablec> listaEstablecimientos) {
		this.listaEstablecimientos = listaEstablecimientos;
	}

	public Integer getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Integer idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	

}
