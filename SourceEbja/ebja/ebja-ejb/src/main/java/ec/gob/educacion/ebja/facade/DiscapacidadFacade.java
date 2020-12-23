package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.DiscapacidadFacadeLocal;
import ec.gob.educacion.ebja.modelo.Discapacidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DiscapacidadFacade extends AbstractFacade<Discapacidad> implements DiscapacidadFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public DiscapacidadFacade() {
        super(Discapacidad.class);
    }

	public Discapacidad buscarDiscapacidad(Integer idRegistroEstudiante, Integer idCatalogoDiscapacidad) {
		Discapacidad discapacidad = new Discapacidad();
		try {
			String script = "Select d From Discapacidad d "
					      + " Where d.estado = '1' "
					      + "   and d.registroEstudiante.id = :idRegistroEstudiante "
		                  + "   and d.catalogoDiscapacidad.id = :idCatalogoDiscapacidad ";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("idRegistroEstudiante", idRegistroEstudiante);
			query.setParameter("idCatalogoDiscapacidad", idCatalogoDiscapacidad);

			discapacidad = (Discapacidad) query.getSingleResult();
		} catch (NoResultException e) {
			//e.printStackTrace();
			discapacidad = null;
		}
		
		return discapacidad;
	}

	public boolean eliminarDiscapacidad(Integer idRegistroEstudiante) {
		boolean procesoEliminar;
		try {
			String script = "delete from Discapacidad d "
					      + " Where d.registroEstudiante.id = :idRegistroEstudiante ";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("idRegistroEstudiante", idRegistroEstudiante);

			query.executeUpdate();
			procesoEliminar = true;
		} catch (NoResultException e) {
			e.printStackTrace();
			procesoEliminar = false;
		}
		
		return procesoEliminar;
	}

}
