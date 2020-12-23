package ec.gob.educacion.ebja.facade.local;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Usuario;



@Local
public interface UsuarioFacadeLocal {
	
	Usuario buscarPorUsuario(String usuario, String estado, boolean todos);
	void actualizarNumeroConexionesPorIdUsuario(Integer idUsuario, Boolean aumentar);
	
	public Usuario buscarPorUsuarioPorIdentificacionPersona(String identificacion, String estado);
	public Usuario buscarUsuarioPorPersona(Integer idPersona, String estado);
	
	void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);
    
    public Usuario buscarPorUsuarioPorId(Integer idUsuario, String estado);
}
