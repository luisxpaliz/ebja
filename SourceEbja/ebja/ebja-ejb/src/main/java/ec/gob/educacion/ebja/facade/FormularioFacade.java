package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.FormularioFacadeLocal;
import ec.gob.educacion.ebja.modelo.Formulario;

@Stateless
public class FormularioFacade extends AbstractFacade<Formulario> implements FormularioFacadeLocal {

	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public FormularioFacade() {
		super(Formulario.class);
		
	}
	
	public FormularioFacade(Class<Formulario> entityClass) {
		super(entityClass);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Formulario> findAllActive() {
		return em.createQuery("SELECT f FROM Formulario f where f.estado ='1'").getResultList();
	}
	
	@Override
	public Formulario obtenerFormulario (String valorVariable) {
		return (Formulario) em.createNamedQuery("Formulario.findByCodigo").setParameter("nemonico", valorVariable)
				.getSingleResult();
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
