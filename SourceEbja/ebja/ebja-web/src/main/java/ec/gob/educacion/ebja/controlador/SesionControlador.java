package ec.gob.educacion.ebja.controlador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.gob.educacion.ebja.dto.DocenteDTO;
import ec.gob.educacion.ebja.modelo.Usuario;
import ec.gob.educacion.ebja.modelo.seguridades.Recurso;
import ec.gob.educacion.ebja.modelo.seguridades.RolAplicacion;
import ec.gob.educacion.ebja.modelo.seguridades.UsuarioSeg;
import ec.gob.educacion.ebja.security.CustomUser;
import ec.gob.educacion.ebja.security.EducacionUserSecurity;
import ec.gob.educacion.ebja.servicio.UsuarioServicio;
import ec.gob.educacion.ebja.controlador.BaseControlador;

@SessionScoped
public class SesionControlador extends BaseControlador implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;	
	
	@Inject
	private UsuarioServicio usuarioServicio;

	private EducacionUserSecurity userSecurity;
	
	private String roles;
	private String nombreUsuario;
	private UsuarioSeg usuarioSesion;
	private CustomUser usuarioSesionSpringSecurity;
	private DocenteDTO docente;
	private String amieInstitucion;
	private Usuario usuario;
	private List<Recurso> recursos;
	private List<RolAplicacion> rolesAplicacion;
	private boolean menuIniciado;
	private MenuModel menuModelHorizontal;
	private String HostName;
	private String IpAdressLocal;
	private  List<String> listaIngresos;
    private  List<String> listaBloqueados;
    private  HashMap<String,Integer> ListaIntentos;
    private  String valorDuplicado;
	
	
	@PostConstruct
    private void init() {
		recursos = new ArrayList<>();
		rolesAplicacion = new ArrayList<>();
		menuModelHorizontal = null;
		listaIngresos = new ArrayList<String>();
		listaBloqueados = new ArrayList<String>();
		ListaIntentos = new HashMap<>();
    }
	
	@PreDestroy
	private void finalizar() {
		usuarioServicio.actualizarNumeroConexionesPorIdUsuario(this.usuario.getId(), Boolean.FALSE);
	}
	
	public void iniciarHiloBloqueo() {
		Timer tmpGrav = new Timer();
		tmpGrav.schedule(new TimerTask() {
			public void run() {
				getListaBloqueados().remove(getValorDuplicado());
			}
		}, 100000);
	}
	
	public void contarConexiones() {
		usuarioServicio.actualizarNumeroConexionesPorIdUsuario(this.usuario.getId(), Boolean.TRUE);
	}
	

	public EducacionUserSecurity getUserSecurity() {
		return userSecurity;
	}

	public void setUserSecurity(EducacionUserSecurity userSecurity) {
		this.userSecurity = userSecurity;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public DocenteDTO getDocente() {
		return docente;
	}


	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
	}


	public String getAmieInstitucion() {
		return amieInstitucion;
	}


	public void setAmieInstitucion(String amieInstitucion) {
		this.amieInstitucion = amieInstitucion;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public List<RolAplicacion> getRolesAplicacion() {
		return rolesAplicacion;
	}

	public void setRolesAplicacion(List<RolAplicacion> rolesAplicacion) {
		this.rolesAplicacion = rolesAplicacion;
	}

	public CustomUser getUsuarioSesionSpringSecurity() {
		return usuarioSesionSpringSecurity;
	}

	public void setUsuarioSesionSpringSecurity(CustomUser usuarioSesionSpringSecurity) {
		this.usuarioSesionSpringSecurity = usuarioSesionSpringSecurity;
	}

	public UsuarioSeg getUsuarioSesion() {
		return usuarioSesion;
	}

	public void setUsuarioSesion(UsuarioSeg usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	public boolean isMenuIniciado() {
		return menuIniciado;
	}

	public void setMenuIniciado(boolean menuIniciado) {
		this.menuIniciado = menuIniciado;
	}

	public MenuModel getMenuModelHorizontal() {
		return menuModelHorizontal;
	}

	public void setMenuModelHorizontal(MenuModel menuModelHorizontal) {
		this.menuModelHorizontal = menuModelHorizontal;
	}
	
	
	public List<String> getListaIngresos() {
		return listaIngresos;
	}

	public void setListaIngresos(List<String> listaIngresos) {
		this.listaIngresos = listaIngresos;
	}

	public List<String> getListaBloqueados() {
		return listaBloqueados;
	}

	public void setListaBloqueados(List<String> listaBloqueados) {
		this.listaBloqueados = listaBloqueados;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public String getIpAdressLocal() {
		return IpAdressLocal;
	}

	public void setIpAdressLocal(String ipAdressLocal) {
		IpAdressLocal = ipAdressLocal;
	}
	
	

	public HashMap<String, Integer> getListaIntentos() {
		return ListaIntentos;
	}

	public void setListaIntentos(HashMap<String, Integer> listaIntentos) {
		ListaIntentos = listaIntentos;
	}
	

	public String getValorDuplicado() {
		return valorDuplicado;
	}

	public void setValorDuplicado(String valorDuplicado) {
		this.valorDuplicado = valorDuplicado;
	}

	public void iniciarMenu() {
		this.menuModelHorizontal = new DefaultMenuModel();
		for (Recurso recurso : recursos) {
			DefaultSubMenu subMenu = new DefaultSubMenu(recurso.getNombre());
	        
        	for(Recurso hijo : recurso.getRecursosHijos())
        	{
        		try {
        			DefaultMenuItem item = new DefaultMenuItem(StringUtils.capitalize(StringUtils.lowerCase(hijo.getNombre())));
        			item.setUrl(recurso.getUrl() + hijo.getUrl());
        	        item.setIcon("ui-icon-tag");
        			subMenu.addElement(item);
    			} catch (Exception e) {
    				// TODO: handle exception
    				e.printStackTrace();
    			}
        	}
	        menuModelHorizontal.addElement(subMenu);
		}
	}
}