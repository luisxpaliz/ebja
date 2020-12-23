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
import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.dto.RegistroEstudianteDTO;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.facade.local.UbicacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Matricula;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import ec.gob.educacion.ebja.recursos.Constantes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ec.gob.educacion.ebja.servlets.CaptchaServlet;
import ec.gob.educacion.ebja.servlets.ReporteJasper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class ConsultaAsignacionBean extends BaseControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private MatriculaFacadeLocal matriculaFacadeLocal;
	@EJB
	private UbicacionFacadeLocal ubicacionFacadeLocal;

	private Matricula matricula;
	private RegistroEstudianteDTO registroEstudianteDTO;
	private Ubicacion ubicacion;
	
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
	           	buscarAsignacion(textoBuscar, "");
	        } else {
            	//Proceso busqueda por nombre.
            	buscarAsignacion("", textoBuscar);
	        }
	        
	        //Verificar si encuentra el registro.
	        if (existeRegistro) {
    			RequestContext context = RequestContext.getCurrentInstance();
    			context.execute("PF('dConsultaAsignacion').show();");
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

			Map<String, String> registroEstudianteDTOMap = BeanUtils.describe(registroEstudianteDTO);
			parametros.put("registroEstudianteDTO", registroEstudianteDTOMap);

			parametros.put("fechaCreacion", fechaCreacion);

			List<Object> reportList = new ArrayList<Object>();
			reportList.add("0");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
			report.generarReporteNavegador(parametros, dataSource, "asignacionReporte");
			
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	private void buscarAsignacion(String numeroIdentificacion, String nombresApellidos) {
		//Buscar inscripción del aspirante.
		matricula = new Matricula(); 
		matricula = matriculaFacadeLocal.obtenerAsignacion(numeroIdentificacion, nombresApellidos);
		if (matricula != null) {
			existeRegistro = true;
			datosParaReporte();
		}
	}
	
	public void datosParaReporte() {
		registroEstudianteDTO = new RegistroEstudianteDTO();
		
		//Datos de la fecha de inicio clases desde programaEbja.
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		if (matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getFechaInicioClases() == null) {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(new Date()));
		} else {
			registroEstudianteDTO.setFechaInicioClases(formateador.format(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getFechaInicioClases()));
		}
		
		if (matricula.getFechaCreacion() == null) {
			fechaCreacion = formateador.format(new Date());
		} else {
			fechaCreacion = formateador.format(matricula.getFechaCreacion());
		}
		
		registroEstudianteDTO.setApellidosNombres(matricula.getEstudiante().getRegistroEstudiante().getApellidosNombres());
		registroEstudianteDTO.setNumeroIdentificacion(matricula.getEstudiante().getRegistroEstudiante().getNumeroIdentificacion());
		registroEstudianteDTO.setCorreoElectronico(matricula.getEstudiante().getRegistroEstudiante().getCorreoElectronico());
		registroEstudianteDTO.setTelefono(matricula.getEstudiante().getRegistroEstudiante().getTelefono());
		registroEstudianteDTO.setNomProgramaEbja(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja().getNombre());
		registroEstudianteDTO.setIdInscripcion(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getId());
		registroEstudianteDTO.setApellidosNombresUsuarioTmp(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getApellidosNombresUsuario());
		registroEstudianteDTO.setNumeroIdentificacionUsuarioTmp(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getNumeroIdentificacionUsuario());
		registroEstudianteDTO.setCodRegistroEstudiante(matricula.getEstudiante().getRegistroEstudiante().getId().toString());

		//Obtener la ubicación de la inscripción.
		ubicacion = new Ubicacion();
		ubicacion = ubicacionFacadeLocal.buscarPorIdInscripcion(matricula.getEstudiante().getRegistroEstudiante().getInscripcion().getId());
		if (ubicacion != null) {
			registroEstudianteDTO.setNomPais(ubicacion.getPais().getNombre());
			if (ubicacion.getParroquia() != null) {
				registroEstudianteDTO.setNomProvincia(ubicacion.getParroquia().getIdCanton().getIdProvincia().getDescripcion());
			}
			
			if (ubicacion.getCircuito() != null) {
				registroEstudianteDTO.setNomCanton(ubicacion.getCircuito().getCodigoSenpladesCircuito());
				registroEstudianteDTO.setCodigoPostal(ubicacion.getCircuito().getIdDistrito().getCodigoSenpladesDistrito());
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

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
}
