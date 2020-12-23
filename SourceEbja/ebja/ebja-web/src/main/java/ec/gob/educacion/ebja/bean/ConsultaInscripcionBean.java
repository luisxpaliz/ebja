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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;

import com.itextpdf.text.log.SysoCounter;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.dto.RegistroEstudianteDTO;
import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.facade.local.UbicacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.recursos.Constantes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ec.gob.educacion.ebja.servlets.CaptchaServlet;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class ConsultaInscripcionBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RegistroEstudianteFacadeLocal registroEstudianteFacadeLocal;
	@EJB
	private UbicacionFacadeLocal ubicacionFacadeLocal;
	@EJB
	private DistritoFacadeLocal distritoFacadeLocal;

	private RegistroEstudiante registroEstudiante;
	private RegistroEstudianteDTO registroEstudianteDTO;
	private Ubicacion ubicacion;
	private Distrito distrito;
	
	private String textoBuscar;
	private String textoImagen;
	private String fechaCreacion;
	
	private boolean existeRegistro;

	private int opcionValor;

    @PostConstruct
    public void init() {
        opcionValor = 1;
        textoImagen="";
        textoBuscar="";
    }

    public void resetearTextoImagen() {
        this.textoImagen = "";
    }

    public void buscar() {
		existeRegistro = false;
        if (opcionValor == 2) {
        	textoBuscar = textoBuscar.toUpperCase();
        }
		if (validarCaptcha()) {
	        if (opcionValor == 1) {
	           	//Proceso busqueda por identificacion.
	           	buscarRegistroEstudiante(textoBuscar, "");
	        } else {
            	//Proceso busqueda por nombre.
            	buscarRegistroEstudiante("", textoBuscar);
	        }
	        
	        //Verificar si encuentra el registro.
	        if (existeRegistro) {
    			RequestContext context = RequestContext.getCurrentInstance();
    			context.execute("PF('dConsultaInscripcion').show();");
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_REGISTROS, ""));
			}
		} else {
            textoImagen = "";
			FacesContext.getCurrentInstance().addMessage("frmForm:mensaje",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.TEXTO_IMAGEN_INCORRECTO, ""));
		}
    }
	
	public boolean validarCaptcha() {
		boolean isResponseCorrect = false;
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        javax.servlet.http.HttpSession session = request.getSession();
	        
	        String captchaGenerate = (String) session.getAttribute(CaptchaServlet.CAPTCHA_KEY);
	        if (textoImagen.equals(captchaGenerate)) {
	        	isResponseCorrect = true;
	        }
		} catch (Exception e) {
			e.getStackTrace();
		}
		
        return isResponseCorrect;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void verCertificado() {
		try {
			Map<String, Object> parametros = new HashMap();
			ReporteJasper report = new ReporteJasper();
			String programa=registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getGrupoFasePrograma().getProgramaEducativo().getNombrePrograma();
			String periodo=registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getGrupoFasePrograma().getNombre();
			String codInscripcion=registroEstudiante.getInscripcion().getNemonico();
			
			Map<String, String> registroEstudianteDTOMap = BeanUtils.describe(registroEstudianteDTO);
			parametros.put("registroEstudianteDTO", registroEstudianteDTOMap);
			parametros.put("fechaCreacion", fechaCreacion);
			parametros.put("programa", programa);
			parametros.put("periodo", periodo);
			parametros.put("codInscripcion", codInscripcion);
			parametros.put("fechaCreacion", fechaCreacion);

			List<Object> reportList = new ArrayList<Object>();
			reportList.add("0");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
			report.generarReporteNavegador(parametros, dataSource, "inscripcionReporte");
			
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	private void buscarRegistroEstudiante(String numeroIdentificacion, String nombresApellidos) {
		//Buscar inscripción del aspirante.
		registroEstudiante = new RegistroEstudiante(); 
		registroEstudiante = registroEstudianteFacadeLocal.buscarInscripcionPendiente(numeroIdentificacion, nombresApellidos);
		if (registroEstudiante != null) {
			existeRegistro = true;
			datosParaReporte();
		}
	}
	
	public void datosParaReporte() {
		registroEstudianteDTO = new RegistroEstudianteDTO();
		
		//Datos de la fecha de inicio clases desde programaEbja.
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		if (registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getFechaInicioClases() == null) {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(new Date()));
		} else {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getFechaInicioClases()));
		}
		
		if (registroEstudiante.getFechaCreacion() == null) {
			fechaCreacion = formateador.format(new Date());
		} else {
			fechaCreacion = formateador.format(registroEstudiante.getFechaCreacion());
		}
		
		registroEstudianteDTO.setApellidosNombres(registroEstudiante.getApellidosNombres());
		registroEstudianteDTO.setNumeroIdentificacion(registroEstudiante.getNumeroIdentificacion());
		registroEstudianteDTO.setCorreoElectronico(registroEstudiante.getCorreoElectronico());
		registroEstudianteDTO.setTelefono(registroEstudiante.getTelefono());
		registroEstudianteDTO.setNomProgramaEbja(registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getNombre());
		registroEstudianteDTO.setIdInscripcion(registroEstudiante.getInscripcion().getId());
		registroEstudianteDTO.setApellidosNombresUsuarioTmp(registroEstudiante.getInscripcion().getApellidosNombresUsuario());
		registroEstudianteDTO.setNumeroIdentificacionUsuarioTmp(registroEstudiante.getInscripcion().getNumeroIdentificacionUsuario());
		registroEstudianteDTO.setCodRegistroEstudiante(registroEstudiante.getId().toString());
		ProgramaGrado programaGrado = registroEstudiante.getInscripcion().getProgramaGrado();
		registroEstudianteDTO.setNomProgramaEbja( programaGrado.getProgramaEbja().getNombre() + " - " + programaGrado.getGrado().getDescripcion());
		
		//Obtener la ubicación de la inscripción.
		ubicacion = new Ubicacion();
		distrito= new Distrito();
		ubicacion = ubicacionFacadeLocal.buscarPorIdInscripcion(registroEstudiante.getInscripcion().getId());
		if (ubicacion != null) {
			registroEstudianteDTO.setNomPais(ubicacion.getPais().getNombre());
			if (ubicacion.getParroquia() != null) {
				registroEstudianteDTO.setNomProvincia(ubicacion.getParroquia().getIdCanton().getIdProvincia().getDescripcion());
			}
			
			if (ubicacion.getCircuito() != null) {
				registroEstudianteDTO.setNomCanton(ubicacion.getCircuito().getCodigoSenpladesCircuito());
				registroEstudianteDTO.setCodigoPostal(ubicacion.getCircuito().getIdDistrito().getCodigoSenpladesDistrito());
				}
			if(ubicacion.getCircuito().getIdDistrito()!=null) {
				distrito=distritoFacadeLocal.buscarPorProvCantParrCirc(ubicacion.getParroquia().getIdCanton().getIdProvincia().getId(), ubicacion.getParroquia().getIdCanton().getId(), ubicacion.getParroquia().getId(), ubicacion.getCircuito().getId());
				registroEstudianteDTO.setCodigoSenpladesDistrito(distrito.getCodigoSenpladesDistrito());
			}
			registroEstudianteDTO.setCallePrincipal(ubicacion.getCallePrincipal());
		}
	}
	
	/*------------------------------------Getters and Setters---------------------------------------*/
    public int getOpcionValor() {
        return opcionValor;
    }

    public void setOpcionValor(int opcionValor) {
        this.opcionValor = opcionValor;
    }
    
    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

	public String getTextoImagen() {
		return textoImagen;
	}

	public void setTextoImagen(String textoImagen) {
		this.textoImagen = textoImagen;
	}

	public RegistroEstudiante getRegistroEstudiante() {
		return registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}
}
