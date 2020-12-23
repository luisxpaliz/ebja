package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.util.Date;
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
import ec.gob.educacion.ebja.facade.local.InscripcionFacadeLocal;
import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.UbicacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import ec.gob.educacion.ebja.recursos.Constantes;

@ManagedBean
@ViewScoped
public class EliminarInscripcionBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private UbicacionFacadeLocal ubicacionFacadeLocal;
	@EJB
	private RegistroEstudianteFacadeLocal registroEstudianteFacadeLocal;
	@EJB
	private InscripcionFacadeLocal inscripcionFacadeLocal;

	private Inscripcion inscripcion;
	private RegistroEstudiante registroEstudiante;
	private Ubicacion ubicacion;
	private Object[] aspiranteInscripcion;
	
	private Integer tipoDocumento;
	
	private String numeroInscripcion = "";
	
	private boolean readonlyNumeroInscripcion;
	private boolean readonlyMotivoEliminar;
	private boolean habPanelA;
	
	private boolean disabledCbBuscarAspirante;
	private boolean disabledCbEliminarAspirante;
	private boolean disabledSbcProcesar;

	@PostConstruct
	public void init() {
		// Inicializar objetos.
		inicializarObjetos();
	}
	
	public void inicializarObjetos() {
		numeroInscripcion = "";

		habPanelA = false;
		disabledCbBuscarAspirante = false;
		readonlyNumeroInscripcion = false;
		disabledSbcProcesar = false;
		disabledCbEliminarAspirante = true;

		inscripcion = new Inscripcion();
		ubicacion = new Ubicacion();
		registroEstudiante = new RegistroEstudiante();
	}
	
	public void buscarAspiranteInscrito() {
		habPanelA = false;
		if (inscripcion.getId() == null) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.INGRESE_DATOS, ""));
			return;
		}
		
		// Obtener la Inscripcion, Ubicacion y Registro Estudiante con el id de la Inscripcion
		aspiranteInscripcion = inscripcionFacadeLocal.obtenerInscripcionPorId(inscripcion.getId());
		
		if ((Inscripcion) aspiranteInscripcion[0] == null) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.NO_EXISTE_REGISTROS + " o Aspirante eliminado." , ""));
			return;
		}
		
		inscripcion = (Inscripcion) aspiranteInscripcion[0];
		ubicacion = (Ubicacion) aspiranteInscripcion[1];
		registroEstudiante = (RegistroEstudiante) aspiranteInscripcion[2];

		habPanelA = true;
		
		disabledCbBuscarAspirante = true;
		readonlyNumeroInscripcion = true;
	}

	public void habilitarBotonEliminarAspirante() {
		disabledCbEliminarAspirante = false;
	}
	
	public void eliminarAspiranteInscrito() {
		if (registroEstudiante.getMotivoNoMatricula().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.INGRESE_DATOS, ""));
		} else {
			// Confirmar si se continua con el proceso o no.
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('popConfirmarProceso').show();");
		}
	}
	
	// Actualizar el estado = 0 -> registro inactivo en Inscripción, Ubicación y Registro Estudiante.
	public void procesarContinuar() {
		// Datos auditables
		datosAuditables();
		
		try {
			// Actualizar estado = 0 Inscripcion.
			inscripcion.setEstado("0");
			inscripcionFacadeLocal.edit(inscripcion);
			
			// Actualizar estado = 0 Ubicacion.
			ubicacion.setEstado("0");
			ubicacionFacadeLocal.edit(ubicacion);
			
			// Actualizar estado = 0 Registro Estudiante.
			registroEstudiante.setEstado("0");
			registroEstudianteFacadeLocal.edit(registroEstudiante);

			disabledCbEliminarAspirante = true;

			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PROCESO_CORRECTO + ", eliminar aspirante", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.PROCESO_NO_CORRECTO + ", eliminar aspirante", ""));
		}
	}

	public void datosAuditables() {
		// Inscripción.
		inscripcion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		inscripcion.setFechaCreacion(new Date());
		inscripcion.setIpUsuario(sesionControlador.getIpAdressLocal());

		// Ubicación.
		ubicacion.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		ubicacion.setFechaCreacion(new Date());
		ubicacion.setIpUsuario(sesionControlador.getIpAdressLocal());

		// Registro Estudiante.
		registroEstudiante.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		registroEstudiante.setFechaCreacion(new Date());
		registroEstudiante.setIpUsuario(sesionControlador.getIpAdressLocal());
	}
	
	public void procesarCancelar() {
		// Implementar imprevistos
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public boolean isReadonlyNumeroInscripcion() {
		return readonlyNumeroInscripcion;
	}

	public void setReadonlyNumeroInscripcion(boolean readonlyNumeroInscripcion) {
		this.readonlyNumeroInscripcion = readonlyNumeroInscripcion;
	}

	public boolean isHabPanelA() {
		return habPanelA;
	}

	public void setHabPanelA(boolean habPanelA) {
		this.habPanelA = habPanelA;
	}

	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}

	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public boolean isDisabledCbBuscarAspirante() {
		return disabledCbBuscarAspirante;
	}

	public void setDisabledCbBuscarAspirante(boolean disabledCbBuscarAspirante) {
		this.disabledCbBuscarAspirante = disabledCbBuscarAspirante;
	}

	public boolean isDisabledSbcProcesar() {
		return disabledSbcProcesar;
	}

	public void setDisabledSbcProcesar(boolean disabledSbcProcesar) {
		this.disabledSbcProcesar = disabledSbcProcesar;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	public RegistroEstudiante getRegistroEstudiante() {
		return registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isReadonlyMotivoEliminar() {
		return readonlyMotivoEliminar;
	}

	public void setReadonlyMotivoEliminar(boolean readonlyMotivoEliminar) {
		this.readonlyMotivoEliminar = readonlyMotivoEliminar;
	}

	public boolean isDisabledCbEliminarAspirante() {
		return disabledCbEliminarAspirante;
	}

	public void setDisabledCbEliminarAspirante(boolean disabledCbEliminarAspirante) {
		this.disabledCbEliminarAspirante = disabledCbEliminarAspirante;
	}
}
