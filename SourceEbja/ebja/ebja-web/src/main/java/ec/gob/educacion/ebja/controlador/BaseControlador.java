package ec.gob.educacion.ebja.controlador;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseControlador {
	
    protected void agregarMensajeError(String resumen, String detalle) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(resumen);
        errorMessage.setDetail(detalle);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }
    
    protected void agregarMensajeError(String id, String resumen, String detalle, boolean validacionFallida) {
        agregarMensajeError(id, resumen, detalle);
        if(validacionFallida) {
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

     protected void agregarMensajeError(String id, String resumen, String detalle) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(resumen);
        errorMessage.setDetail(detalle);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(id, errorMessage);
    }
     
     protected void agregarMensajeError(Exception ex) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(ex.getMessage());
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }

    protected void agregarMensajeInformacion(String resumen, String detalle) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(resumen);
        infoMessage.setDetail(detalle);
        infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, infoMessage);
    }

    protected void agregarMensajeInformacion(String id, String resumen, String detalle) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(resumen);
        infoMessage.setDetail(detalle);
        infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(id, infoMessage);
    }

    protected void agregarMensajeAdvertencia(String resumen, String detalle) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(resumen);
        infoMessage.setDetail(detalle);
        infoMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext.getCurrentInstance().addMessage(null, infoMessage);
    }

    protected void agregarMensajeAdvertencia(String resumen, String detalle, boolean enModal) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(resumen);
        infoMessage.setDetail(detalle);
        infoMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        if(enModal) {
        	FacesContext.getCurrentInstance().addMessage("idMensaje", infoMessage);
        } else {
        	FacesContext.getCurrentInstance().addMessage(null, infoMessage);
        }
    }
    
    protected void agregarMensajeAdvertencia(String id, String resumen, String detalle) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(resumen);
        infoMessage.setDetail(detalle);
        infoMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext.getCurrentInstance().addMessage(id, infoMessage);
    }

    public HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    protected String getHttpRequestParameter(final String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get(parameter);
    }

    protected HttpSession getHttpSession() {
        return (HttpSession) getExternalContext().getSession(false);
    }

    protected ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public String getContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestContextPath();
    }

    public Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    protected void redireccionarPagina(String pagina) {
        try {
            getExternalContext().redirect(getHttpRequest().getContextPath().concat(pagina));         
        } catch (IOException e) {
            agregarMensajeError("No se puede redireccionar a " + pagina, e.getLocalizedMessage());
        }
    }
	
	protected String cortarCadena(String cadena, int limite) {
		String cadenaCortada = cadena;
		if (cadena.length() > limite) {
			cadenaCortada = cadena.substring(0, limite).concat("...");
		}
		return cadenaCortada;
	}

	protected String getPaginaActual(){
    	String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo();
    	String [] arregloPagina = pagina.split("/");
    	String paginaActual = (arregloPagina.length==3)?arregloPagina[2]:"";
    	return paginaActual;
    }
	
	public static void resetForm(UIComponent form) {
	  for (UIComponent uic : form.getChildren()) {
	    if (uic instanceof EditableValueHolder) {
	      EditableValueHolder evh=(EditableValueHolder)uic;
	      evh.resetValue();
	    }
	    resetForm(uic);
	  }
	}
}
