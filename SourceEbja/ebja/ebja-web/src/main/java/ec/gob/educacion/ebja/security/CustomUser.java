package ec.gob.educacion.ebja.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	private static final long serialVersionUID = 6698231753971757890L;
	
	private String cedula;
	private String description;
	private String nombreCompleto;
	private String rolActiveDirectory;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, 
			String cedula, String description, String nombreCompleto, String rolActiveDirectory) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.cedula = cedula;
		this.description = description;
		this.nombreCompleto = nombreCompleto;
		this.rolActiveDirectory = rolActiveDirectory;
	}
	

	public String getDescription() {
		return description;
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	public String getRolActiveDirectory() {
		return rolActiveDirectory;
	}


	public void setRolActiveDirectory(String rolActiveDirectory) {
		this.rolActiveDirectory = rolActiveDirectory;
	}



}
