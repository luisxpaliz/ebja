package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.TipoCatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;

@Stateless
public class TipoCatalogoEbjaFacade extends AbstractFacade<TipoCatalogoEbja> implements TipoCatalogoEbjaFacadeLocal{

	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public TipoCatalogoEbjaFacade() {
		super(TipoCatalogoEbja.class);
	}
	
	public TipoCatalogoEbjaFacade(Class<TipoCatalogoEbja> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoCatalogoEbja> tipoCatalogoActivos() { 
		return em.createNamedQuery("TipoCatalogoEbja.findAllEditable").getResultList();
	}

	@Override
	public TipoCatalogoEbja ObtenerTipoCatalogoEbja(String valorVariable) {
		return  (TipoCatalogoEbja) em.createNamedQuery("TipoCatalogoEbja.findByNemonico").setParameter("nemonico", valorVariable).getSingleResult();	
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	
	
}
