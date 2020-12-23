package ec.gob.educacion.ebja.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.TipoProgramaFacadeLocal;
import ec.gob.educacion.ebja.modelo.TipoPrograma;


@Stateless
public class TipoProgramaFacade extends AbstractFacade<TipoPrograma> implements TipoProgramaFacadeLocal {

	public TipoProgramaFacade() {
		super(TipoPrograma.class);
	}

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public TipoPrograma ObtenerPrograma(String valorVariable) {
		return  (TipoPrograma) em.createNamedQuery("TipoPrograma.findByNemonico").setParameter("nemonico", valorVariable).getSingleResult();
	}
}
