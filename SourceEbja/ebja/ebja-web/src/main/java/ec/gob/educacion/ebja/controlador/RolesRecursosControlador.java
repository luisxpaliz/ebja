package ec.gob.educacion.ebja.controlador;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import ec.gob.educacion.ebja.controlador.BaseControlador;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.gob.educacion.ebja.modelo.seguridades.Recurso;
import ec.gob.educacion.ebja.modelo.seguridades.RolAplicacion;
import ec.gob.educacion.ebja.recursos.ClienteServicioWeb;
import ec.gob.educacion.ebja.recursos.Condiciones;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.security.CustomUser;



@Named
@ViewScoped
public class RolesRecursosControlador extends BaseControlador implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SesionControlador sesionControlador;
	
	private String usuario;
	private String password;
	private CustomUser usuarioAD;
	private String mensajeInicio;
	private List<RolAplicacion> roles;
	private String rolIniciado;
	private String nombreUsuarioLogueado;
	

	@PostConstruct
	public void init() {
		if (sesionControlador.getUsuarioSesion()!=null) {
			nombreUsuarioLogueado = sesionControlador.getUsuarioSesion().getNombre();
			mensajeInicio = new Condiciones().getStringFromPage(Constantes.PATH_MENSAJE_INICIO);
			roles = sesionControlador.getRolesAplicacion();
			rolIniciado = sesionControlador.getRoles();
			if(rolIniciado == null) {
				iniciarCargaRolesAplicacionMenu();
			}
		}else {
			redireccionarPagina("/faces/login.xhtml");
		}
	}
	
	public String ObtenerHostNameCliente() {
		
		try {
			return java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "HostName no encontrado";	
		}
	}
	
	public String ObtenerIPCliente() {
		
		try {
			return java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "IpAdress no encontrado";
			
		}
	}
	
	public void iniciarCargaRolesAplicacionMenu() {
		if(!roles.isEmpty() && roles.size()>1) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('rolesDlg').show();");
		}else if(!roles.isEmpty() && roles.size()==1){
			cargarRecursosPorRol(roles.get(0));
		}
	}
	
	
	public void cargarRecursosPorRol(RolAplicacion rol) {
		sesionControlador.setRecursos(filtroRecursos(rol));
		rolIniciado = rol.getNombre();
		sesionControlador.setRoles(rolIniciado);	
		sesionControlador.setHostName(ObtenerHostNameCliente());
		sesionControlador.setIpAdressLocal(ObtenerIPCliente());
		sesionControlador.iniciarMenu();
				
		redireccionarPagina("/faces/paginas/principal.xhtml");
	}
	
	public List<Recurso> filtroRecursos(RolAplicacion rol){
		List<Recurso> recursos = new ArrayList<>();
		Gson gson = new Gson();
        Type type = new TypeToken<Long>() {}.getType();
        Type typeRespuesta = new TypeToken<List<Recurso>>() {}.getType();
        String jsonEnvio = gson.toJson(rol.getCodigo(), type);
        //String jsonRespuesta = ClienteServicioWeb.servicioWebPost(ClienteServicioWeb.getURLWebServiceRecursos(FacesContext.getCurrentInstance().getExternalContext()), jsonEnvio);
        String jsonRespuesta = ClienteServicioWeb.servicioWebPost("http://10.200.10.15:80/serviciosEducacion-web/resources/ValidacionUsuarioLdapSeguridadesJdc/ObtenerRecursosDeRol", jsonEnvio);
        recursos = gson.fromJson(jsonRespuesta, typeRespuesta);
		return recursos;
	}
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public CustomUser getUsuarioAD() {
		return usuarioAD;
	}


	public void setUsuarioAD(CustomUser usuarioAD) {
		this.usuarioAD = usuarioAD;
	}

	public String getMensajeInicio() {
		
		return mensajeInicio;
	}

	public void setMensajeInicio(String mensajeInicio) {
		this.mensajeInicio = mensajeInicio;
	}

	public List<RolAplicacion> getRoles() {
		return roles;
	}

	public void setRoles(List<RolAplicacion> roles) {
		this.roles = roles;
	}



	public String getRolIniciado() {
		return rolIniciado;
	}



	public void setRolIniciado(String rolIniciado) {
		this.rolIniciado = rolIniciado;
	}



	public String getNombreUsuarioLogueado() {
		return nombreUsuarioLogueado;
	}



	public void setNombreUsuarioLogueado(String nombreUsuarioLogueado) {
		this.nombreUsuarioLogueado = nombreUsuarioLogueado;
	}
}
