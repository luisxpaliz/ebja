package ec.gob.educacion.ebja.security;

import java.util.Collection;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;


import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {
	
	
	private String cedula = null;
	private String description = null;
	private String nombreCompleto = null;
	private String rolActiveDirectory = null;
	
	private CustomUser userDetails = null;

	public CustomUserDetailsContextMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		
		Attributes attributes = ctx.getAttributes();
		try {
			cedula = (String) attributes.get("postalCode").get();
			description = (String) attributes.get("description").get();
			nombreCompleto = (String) attributes.get("cn").get();
			rolActiveDirectory = (String) attributes.get("Description").get();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CustomUser userDetails = new CustomUser(username, "", true, true, true, true, authorities, cedula, description, nombreCompleto, rolActiveDirectory);
		this.userDetails = userDetails;
		return userDetails;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub

	}
	
	public CustomUser getUserDetails() {
		return this.userDetails;
	}

	public String getRolActiveDirectory() {
		return rolActiveDirectory;
	}

	public void setRolActiveDirectory(String rolActiveDirectory) {
		this.rolActiveDirectory = rolActiveDirectory;
	}

}
