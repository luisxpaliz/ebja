package ec.gob.educacion.ebja.servicio;

import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.Usuario;

@Local
public interface UsuarioServicio {
	
	Usuario buscarPorUsuario(String usuario, String estado, boolean todos);
	void actualizarNumeroConexionesPorIdUsuario(Integer idUsuario, Boolean aumentar);
	
	public Usuario buscarPorUsuarioPorIdentificacionPersona(String identificacion, String estado);
	public Usuario buscarUsuarioPorPersona(Integer idPersona, String estado);
	
	public Usuario buscarUsuarioPorPersonaRegistroRepresentanteEstudiante(Integer idPersona);
	
	public void crearUsuario(Usuario usuario);
	
	public void actualizarUsuario(Usuario usuario);
	
	public Usuario buscarPorUsuarioPorId(Integer idUsuario, String estado);
	
}
