package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import ec.gob.educacion.ebja.facade.local.UsuarioFacadeLocal;
import ec.gob.educacion.ebja.modelo.Usuario;
import ec.gob.educacion.ebja.recursos.Constantes;



@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
	
	private static final Logger LOGGER = Logger.getLogger(UsuarioFacade.class.getName());

	@PersistenceContext(unitName = "zeusPU")
    private EntityManager em;
	
	@Override
    protected EntityManager getEntityManager() {
        return em;
    }
	
	public UsuarioFacade() {
		super(Usuario.class);
	}
	
	@Override
	public Usuario buscarPorUsuario(String usuario, String estado, boolean todos) {
		Usuario outUsuario = null;
		String s = "select u from Usuario u, Persona p where u.usuario = :usuario and u.idPersona = p.id and p.estado = :estadoActivo ";
		if(!todos) {
			s = s + " and u.estado = :estado ";
		}
		TypedQuery<Usuario> q = em.createQuery(s,Usuario.class)
				.setParameter("usuario", usuario)
				.setParameter("estadoActivo", Constantes.ESTADO_REGISTRO_ACTIVO);
		if(!todos) {
			q.setParameter("estado", estado);
		}
		
		List<Usuario> l = q.getResultList();
		if(null != l && !l.isEmpty()) {
			outUsuario = l.get(0);
		}
		return outUsuario;
	}

	@Override
	public void actualizarNumeroConexionesPorIdUsuario(Integer idUsuario, Boolean aumentar) {	
		Integer total;
		String s = "select u from Usuario u where u.id = :idUsuario ";
		Usuario usuario = em.createQuery(s,Usuario.class)
				.setParameter("idUsuario", idUsuario).getSingleResult();
		if(aumentar) {
			total = usuario.getNumeroConexiones()+1;
			usuario.setNumeroConexiones(total.shortValue());
		}else {
			total = usuario.getNumeroConexiones()-1;
			usuario.setNumeroConexiones(total.shortValue());
		}
		em.merge(usuario);
	}
	
	public Usuario buscarPorUsuarioPorIdentificacionPersona(String identificacion, String estado) {
		Usuario usuario = null;
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct u from Persona p, Usuario u ");
			sentencia.append("where p.numeroIdentificacion = :identificacion ");
			sentencia.append("and p.estado = :estadoActivo and u.idPersona = p.id and u.estado = :estado "); 
			
			usuario = (Usuario) em.createQuery(sentencia.toString())
    						.setParameter("estado", estado)
    						.setParameter("estadoActivo", Constantes.ESTADO_REGISTRO_ACTIVO)
    						.setParameter("identificacion", identificacion)
    						.getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			usuario = null;
			LOGGER.info("Error al buscar el usuario " + e.getMessage());
		}
    	return usuario;
	}
	
	public Usuario buscarUsuarioPorPersona(Integer idPersona, String estado) {
		Usuario usuario = null;
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct u from Usuario u ");
			sentencia.append("where u.idPersona = :idPersona ");
			sentencia.append("and u.estado = :estado "); 
			
			usuario = (Usuario) em.createQuery(sentencia.toString())
    						.setParameter("estado", estado)
    						.setParameter("idPersona", idPersona)
    						.getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			usuario = null;
			LOGGER.debug("Error en buscarUsuarioPorPersona " + e.getMessage());
		}
    	return usuario;
	}
	
	public Usuario buscarPorUsuarioPorId(Integer idUsuario, String estado) {
		Usuario usuario = null;
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct u from Usuario u ");
			sentencia.append("where u.id = :idUsuario ");
			sentencia.append("and u.estado = :estado "); 
			
			usuario = (Usuario) em.createQuery(sentencia.toString())
    						.setParameter("estado", estado)
    						.setParameter("idUsuario", idUsuario)
    						.getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			usuario = null;
			LOGGER.debug("Error en buscarPorUsuarioPorId " + e.getMessage());
		}
    	return usuario;
	}

}
