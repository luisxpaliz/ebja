package ec.gob.educacion.ebja.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.educacion.ebja.facade.local.UsuarioFacadeLocal;
import ec.gob.educacion.ebja.modelo.Usuario;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servicio.UsuarioServicio;


@Stateless
public class UsuarioServicioImpl implements UsuarioServicio {

	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	
	@Override
	public Usuario buscarPorUsuario(String usuario, String estado, boolean todos) {
		return usuarioFacadeLocal.buscarPorUsuario(usuario, estado, todos);
	}
	
	@Override
	public void actualizarNumeroConexionesPorIdUsuario(Integer idUsuario, Boolean aumentar) {
		usuarioFacadeLocal.actualizarNumeroConexionesPorIdUsuario(idUsuario,aumentar);
	}

	public Usuario buscarPorUsuarioPorIdentificacionPersona(String identificacion, String estado) {
		return usuarioFacadeLocal.buscarPorUsuarioPorIdentificacionPersona(identificacion, estado);
	}
	
	public Usuario buscarUsuarioPorPersona(Integer idPersona, String estado) {
		return usuarioFacadeLocal.buscarUsuarioPorPersona(idPersona, estado);
	}
	
	public Usuario buscarUsuarioPorPersonaRegistroRepresentanteEstudiante(Integer idPersona) {
		Usuario usuario = usuarioFacadeLocal.buscarUsuarioPorPersona(idPersona, Constantes.ESTADO_REGISTRO_ACTIVO);
		if(usuario == null) {
			usuario = usuarioFacadeLocal.buscarUsuarioPorPersona(idPersona, Constantes.ESTADO_REGISTRO_INACTIVO);
		}
		return usuario;
	}
	
	public void crearUsuario(Usuario usuario) {
		usuarioFacadeLocal.create(usuario);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuarioFacadeLocal.edit(usuario);
	}
	
	public Usuario buscarPorUsuarioPorId(Integer idUsuario, String estado) {
		return usuarioFacadeLocal.buscarPorUsuarioPorId(idUsuario, estado);
	}
}
