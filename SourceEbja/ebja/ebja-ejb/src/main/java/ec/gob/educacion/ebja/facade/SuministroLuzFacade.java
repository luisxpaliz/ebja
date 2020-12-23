package ec.gob.educacion.ebja.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.educacion.ebja.facade.local.SuministroLuzFacadeLocal;
import ec.gob.educacion.ebja.modelo.asignaciones.SuministroLuz;

/**
 * @author jbrito-20151230
 */
@Stateless
public class SuministroLuzFacade extends AbstractFacade<SuministroLuz> implements SuministroLuzFacadeLocal {

	@PersistenceContext(unitName = "asignacionesPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public SuministroLuzFacade() {
		super(SuministroLuz.class);
	}

	@Override
	public SuministroLuz obtenerSuministroXCUE(String cue) {
		try {
			Query q = em.createQuery("Select s from SuministroLuz s where s.codigounic = :cue");
			q.setParameter("cue", cue);
			return (SuministroLuz) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}
