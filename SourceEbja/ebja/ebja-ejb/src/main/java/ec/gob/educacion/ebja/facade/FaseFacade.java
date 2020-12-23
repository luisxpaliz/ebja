package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.FaseFacadeLocal;
import ec.gob.educacion.ebja.modelo.Fase;

@Stateless
public class FaseFacade extends AbstractFacade<Fase> implements FaseFacadeLocal {

	
	private List<Fase> listaFasesResultado;

	
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public FaseFacade() {
		super(Fase.class);
		
	}
	
	public FaseFacade(Class<Fase> entityClass) {
		super(entityClass);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fase> findAllActive() {
		listaFasesResultado = em.createNamedQuery("Fase.findAllActivo").getResultList();
		return listaFasesResultado;
	}
	
	@Override
	public Fase obtenerFase(String valorVariable) {
		return (Fase) em.createNamedQuery("Fase.findByNemonico").setParameter("nemonico", valorVariable).getSingleResult();
	}
	
	
	
	
	
	

}
